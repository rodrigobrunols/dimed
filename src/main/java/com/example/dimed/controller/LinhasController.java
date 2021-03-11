package com.example.dimed.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dimed.dto.LinhaDto;
import com.example.dimed.service.LinhasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller para operações de Linhas
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
@RequestMapping("/v1/linhas")
@RestController
public class LinhasController implements FrameworkApiController {

	@Autowired
	private LinhasService service;

	@GetMapping
	@Operation(summary = "Listar linhas")
	@ResponseBody
	public List<LinhaDto> list() {
		return service.list();
	}
	
	@PostMapping
	@Operation(summary = "Criar Linha")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public LinhaDto create(@Valid @RequestBody LinhaDto dto) {
		return service.create(dto);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Recupera uma linha pelo Id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Linha encontrada", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = LinhaDto.class)) }),
			  @ApiResponse(responseCode = "400", description = "Erro na requisição", 
			    content = @Content), 
			  @ApiResponse(responseCode = "404", description = "Id não encontrado", 
			    content = @Content) })
	@ResponseBody
	public LinhaDto retrieve(@PathVariable(value = "id") Long id) {
		return service.retrieve(id);
	}

	@PutMapping
	@ResponseBody
	@Operation(summary = "Atualiza uma linha")
	public LinhaDto update(@RequestBody LinhaDto dto) {
		return service.update(dto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Remove uma linha pelo Id")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}

}
