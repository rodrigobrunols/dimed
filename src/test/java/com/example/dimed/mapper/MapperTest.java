package com.example.dimed.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.example.dimed.dto.LinhaDto;
import com.example.dimed.model.Linha;

public class MapperTest {

	private ModelMapper modelMapper = new ModelMapper();

	public void whenConvertPostEntityToPostDto_thenCorrect() {
		Linha l = new Linha();
		l.setId(1L);
		l.setNome("nome");
		l.setCodigo("codigo");

		LinhaDto postDto = modelMapper.map(l, LinhaDto.class);
		assertEquals(l.getId(), postDto.getId());
		assertEquals(l.getCodigo(), postDto.getCodigo());
		assertEquals(l.getNome(), postDto.getNome());
	}

	@Test
	public void whenConvertPostDtoToPostEntity_thenCorrect() {
		LinhaDto dto = new LinhaDto();
		dto.setId(1L);
		dto.setNome("nome");
		dto.setCodigo("codigo");

		Linha post = modelMapper.map(dto, Linha.class);
		assertEquals(dto.getId(), post.getId());
	}
}
