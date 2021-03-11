package com.example.dimed.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class LinhaDto extends FrameworkDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8702600946585126985L;
	
	@NotBlank
	private Long id;
	@NotBlank
    @Size(min = 0, max = 20)
	private String codigo;
	@NotBlank
    @Size(min = 0, max = 100)
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
