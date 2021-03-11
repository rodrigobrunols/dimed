package com.example.dimed.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dimed.dto.PontoTaxiDto;
import com.example.dimed.model.PontoTaxi;
import com.example.dimed.service.PontosTaxiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller para operações de Ponto de Taxi
 * 
 * 
 * Deverão ser fornecidas APIs para criação e consulta de pontos de táxi. A
 * carga deverá ser feita em memória a partir da leitura e processamento local
 * do arquivo disponível no seguinte formato. Esse arquivo deve estar dentro do
 * projeto:
 * 
 * NOME_DO_PONTO#LATITUDE#LONGITUDE#DATA_HORA_CADASTRO
 * 
 * Dados de exemplo
 * 
 * PONTO-ZONA-SUL-1#-30.12373379817800000#-51.22266028234100000#2019-02-10T16:14:34.828
 * PONTO-ZONA-NORTE-1#-30.0103346#-51.1724526#2019-03-10T16:14:34.828
 *
 * Cada nova inserção de ponto de táxi deverá ser persistida no arquivo em disco
 * e atualizada a estrutura em memória.**
 * 
 * @author rodrigo
 * 
 * 
 */
@RequestMapping("/v1/pontos")
@RestController
public class PontosTaxiController implements FrameworkApiController {

	@Autowired
	private PontosTaxiService service;

	@GetMapping
	@Operation(summary = "Listar pontos")
	@ResponseBody
	public List<PontoTaxiDto> list() {
		return service.list();
	}

	@PostMapping
	@Operation(summary = "Criar ponto")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public PontoTaxiDto create(@Valid @RequestBody PontoTaxiDto dto) {
		return service.create(dto);
	}

	@GetMapping("/{nome}")
	@Operation(summary = "Recupera um ponto de taxi pelo nome")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ponto encontrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PontoTaxiDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "404", description = "Id não Econtrado", content = @Content) })
	@ResponseBody
	public List<PontoTaxi> retrieve(@PathVariable(value = "nome") String nome) {
		return service.retrieve(nome);
	}
	
	@GetMapping("/file")
	public List<PontoTaxiDto> listFromFile() {
		return service.listFromFile();
	}

}
