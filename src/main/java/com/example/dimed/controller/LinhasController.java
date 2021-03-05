package com.example.dimed.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dimed.dto.LinhaDto;
import com.example.dimed.service.LinhasService;

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
@RestController
@RequestMapping("/v1/linhas")
public class LinhasController implements DimedApiController {

	@Autowired
	private LinhasService service;

	@GetMapping
	public List<LinhaDto> list() {
		return service.list();
	}
	
	@PostMapping
	public LinhaDto create(@Valid @RequestBody LinhaDto dto) {
		return service.create(dto);
	}
	
	@GetMapping("/{id}")
	public LinhaDto retrieve(@PathVariable(value = "id") Long id) {
		return service.retrieve(id);
	}

	@PutMapping
	public LinhaDto update(@RequestBody LinhaDto dto) {
		return service.update(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}

}
