package com.example.dimed.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dimed.dto.LinhaDto;
import com.example.dimed.exception.NotFoundException;
import com.example.dimed.model.Linha;
import com.example.dimed.repository.LinhasRepository;

/**
 * 
 * Serviço para manipulação de Linhas
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
public class LinhasService implements DimedService {

	private final static Logger LOGGER = Logger.getLogger(LinhasService.class.getName());

	@Autowired
	private LinhasRepository repository;

	@Autowired
	private ModelMapper mapper;


	/**
	 * Listar Linhas 
	 * 
	 * 
	 * 
	 * @return a lista de linhas cadastradas
	 */
	public List<LinhaDto> list() {
		return repository.list().stream().map(linha -> mapper.map(linha, LinhaDto.class)).collect(Collectors.toList());
	}

	/**
	 * Serviço que cria uma linha
	 * 
	 * @param dto dados da linha
	 * 
	 * @return a linha
	 */
	public LinhaDto create(@Valid LinhaDto dto) {
		Linha entity = repository.save(mapper.map(dto, Linha.class));

		LOGGER.info("Salvo: " + entity.toString());

		return mapper.map(entity, LinhaDto.class);
	}

	/**
	 * Serviço que recupera uma linha
	 * 
	 * @param id da linha
	 * 
	 * @return a linha
	 */
	public LinhaDto retrieve(Long id) {
		return repository.findById(id).map(linha -> mapper.map(linha, LinhaDto.class))
				.orElseThrow(NotFoundException::new);
	}
	
	/**
	 * Serviço que atualiza uma linha caso exista ou insere caso contrário
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
	public LinhaDto update(LinhaDto dto) {

		repository.findById(dto.getId()).ifPresent(linha -> {
			
			linha.setCodigo(dto.getCodigo());
			linha.setNome(dto.getNome());
			repository.save(linha);
		});

		Linha atualizado = repository.save(mapper.map(dto,Linha.class));
		
		LOGGER.info("Atualizado: " + atualizado.toString());
		
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

	

}
