package com.example.dimed.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * DTO para Linha.
 *
 * @author rodrigo
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "idItinerario" })
public class LinhaDto extends DimedDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8702600946585126985L;
	
	@NotEmpty
	private Long id;
	@NotEmpty
	private String codigo;
	@NotEmpty
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
