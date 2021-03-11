package com.example.dimed.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.dimed.dto.PontoTaxiDto;
import com.example.dimed.model.PontoTaxi;
import com.example.dimed.repository.PontoTaxiFileRepository;

/**
 * 
 * Serviço para manipulação de Pontos de Taxi
 * 
 * @author rodrigo
 * 
 * @version 1.0.0
 * 
 * @since 1.0.0
 * 
 */
@Service
public class PontosTaxiService implements DimedService {

	private final static Logger LOGGER = Logger.getLogger(PontosTaxiService.class.getName());

	private List<PontoTaxi> memoryRepository = new ArrayList<PontoTaxi>();

	private PontoTaxiFileRepository fileRepository;

	@Autowired
	private ModelMapper mapper;

	@Value("${api.pontotaxi.file}")
	private String arquivo;

	/**
	 * Listar Linhas
	 * 
	 * 
	 * 
	 * @return a lista de linhas cadastradas
	 */
	public List<PontoTaxiDto> list() {
		return memoryRepository.stream().map(linha -> mapper.map(linha, PontoTaxiDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * Serviço que cria um Ponto de Taxi
	 * 
	 * @param dto dados da linha
	 * 
	 * @return a linha
	 * @throws IOException
	 */
	public PontoTaxiDto create(@Valid PontoTaxiDto dto) {
		try {
			if (fileRepository == null) {
				fileRepository = new PontoTaxiFileRepository(arquivo);
			}

			PontoTaxi entity = fileRepository.save(mapper.map(dto, PontoTaxi.class));

			LOGGER.info("Salvo: " + entity.toString());

			listFromFile();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dto;
	}

	/**
	 * Serviço que recupera um ponto
	 * 
	 * @param nome do ponto
	 * 
	 * @return o ponto
	 */
	public List<PontoTaxi> retrieve(String nome) {

		return memoryRepository.stream().filter(dto -> dto.getNome().equals(nome)).collect(Collectors.toList());
	}

	/**
	 * Serviço que recupera lista pontos de taxi no arquivo
	 * 
	 * @return a lista
	 */
	public List<PontoTaxiDto> listFromFile() {

		try {
			if (fileRepository == null) {
				fileRepository = new PontoTaxiFileRepository(arquivo);
			}

			List<PontoTaxi> source = fileRepository.retrieveListTopTaxInFile();

			source.stream().forEach(memoryRepository::add);

			return this.list();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
