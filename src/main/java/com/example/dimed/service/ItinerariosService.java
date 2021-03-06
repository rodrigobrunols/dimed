package com.example.dimed.service;

import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dimed.dto.ItinerarioDto;
import com.example.dimed.dto.LatLongDto;
import com.example.dimed.exception.NotFoundException;
import com.example.dimed.model.Itinerario;
import com.example.dimed.model.LatLong;
import com.example.dimed.repository.ItinerariosRepository;

/**
 * 
 * Serviço para manipulação das operações com Linhas
 * 
 * @author rodrigo
 * 
 * @version 1.0.0
 * 
 * @since 1.0.0
 * 
 *        3) Parte 3 Criar um CRUD de linhas e itinerários, onde seja possível
 *        realizar consultas e cadastros desses itens. Para o cadastro deve ser
 *        realizada a consulta para verificar se a linha ou o itinerário já está
 *        cadastrada ou se tem alguma diferença de dados (linha ou itinerário),
 *        caso tenha deve ser atualizada ou caso não tenha deve ser cadastrada
 *        no sistema.
 *
 */
@Service
public class ItinerariosService implements DimedService {

	private final static Logger LOGGER = Logger.getLogger(ItinerariosService.class.getName());

	@Autowired
	private ItinerariosRepository repository;

	@Autowired
	private ModelMapper mapper;

	/**
	 * Listar itinerarios
	 * 
	 * @return a lista de itinerarios
	 */
	public List<ItinerarioDto> list() {
		return repository.list().stream().map(itinerario -> toItinerarioDto.apply(itinerario))
				.collect(Collectors.toList());
	}

	/**
	 * Serviço que cria um itinerario
	 * 
	 * @param dto dados de itinerario
	 * 
	 * @return o itinerario
	 */
	public ItinerarioDto create(@Valid ItinerarioDto dto) {

		Itinerario entity = repository.save(toItinerario.apply(dto));

		LOGGER.info("Salvo: " + entity.toString());

		return toItinerarioDto.apply(entity);
	}

	/**
	 * Serviço que recupera um itinerario
	 * 
	 * @param id itinerario
	 * 
	 * @return itinerario
	 */
	public ItinerarioDto retrieve(Long id) {
		return repository.findById(id).map(itinerario -> toItinerarioDto.apply(itinerario))
				.orElseThrow(NotFoundException::new);
	}

	/**
	 * Serviço que atualiza itinerario caso exista ou insere caso contrário
	 * 
	 * Para o cadastro deve ser realizada a consulta para verificar se a linha ou o
	 * itinerário já está cadastrada ou se tem alguma diferença de dados (linha ou
	 * itinerário), caso tenha deve ser atualizada ou caso não tenha deve ser
	 * cadastrada no sistema.
	 * 
	 * @param dto dados da linha
	 * 
	 * @return dto da linha
	 */
	public ItinerarioDto update(ItinerarioDto dto) {

		repository.findById(dto.getId()).ifPresent(itinerario -> {

			itinerario.setNome(dto.getNome());

			List<LatLong> listaLatLong = dto.getCoordenadas().stream()
					.map(latLongDto -> mapper.map(latLongDto, LatLong.class)).collect(Collectors.toList());

			itinerario.setCoordenadas(listaLatLong);

			repository.save(itinerario);
		});

		repository.save(toItinerario.apply(dto));

		return dto;
	}

	/**
	 * Serviço que remove uma Linha
	 * 
	 * @param id id da linha
	 */
	public void delete(Long id) {

		repository.delete(id);

		LOGGER.info("Removido: " + id);
	}

	/**
	 * Converte dto para entidade
	 */
	Function<ItinerarioDto, Itinerario> toItinerario = x -> {
		Itinerario map = mapper.map(x, Itinerario.class);
		
		map.setCoordenadas(x.getCoordenadas().stream().map(latLong -> { 
			LatLong latEntity = mapper.map(latLong, LatLong.class);
			latEntity.setItinerarioId(x.getId());
			return latEntity;
		}).collect(Collectors.toList()));
		
		return map;

	};

	/**
	 * Converte entidade para dto
	 */
	Function<Itinerario, ItinerarioDto> toItinerarioDto = entity -> {
		ItinerarioDto map = mapper.map(entity, ItinerarioDto.class);
		
		map.setCoordenadas(entity.getCoordenadas().stream().map(latLong -> mapper.map(latLong, LatLongDto.class))
				.collect(Collectors.toList()));
		
		return map;

	};

}
