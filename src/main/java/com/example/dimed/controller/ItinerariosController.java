package com.example.dimed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dimed.dto.ItinerarioDto;
import com.example.dimed.service.ItinerariosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller para operações de Onibus
 * 
 * @author rodrigo
 * 
 *         Criar um CRUD de linhas e itinerários, onde seja possível realizar
 * 
 *         consultas e cadastros desses itens. Para o cadastro deve ser
 * 
 *         realizada a consulta para verificar se a linha ou o itinerário já
 * 
 *         está cadastrada ou se tem alguma diferença de dados (linha ou
 * 
 *         itinerário), caso tenha deve ser atualizada ou caso não tenha deve
 * 
 *         ser cadastrada no sistema.
 *
 */
@RequestMapping("/v1/itinerarios")
@RestController
public class ItinerariosController implements DimedApiController {

	@Autowired
	private ItinerariosService service;

	
	@GetMapping
	public List<ItinerarioDto> list() {
		return service.list();
	}
	
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,
	        	  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Operation(summary = "Criar itinerario por id")
	public ItinerarioDto create(@RequestBody ItinerarioDto dto) {
		return service.create(dto);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Recupera itinerario por id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Itinerario encontrado", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = ItinerarioDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Id não econtrado", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Not found", 
			    content = @Content) })
	public ItinerarioDto retrieve(@PathVariable(value = "id") Long id) {
		return service.retrieve(id);
	}

	@PutMapping
	@Operation(summary = "Atualiza itinerario")
	public ItinerarioDto update(@RequestBody ItinerarioDto dto) {
		return service.update(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Remove um itineraro pelo Id")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}

}
