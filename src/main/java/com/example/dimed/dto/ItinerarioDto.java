package com.example.dimed.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * DTO para dados de  Itinerario.
 *
 * @author rodrigo
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItinerarioDto extends FrameworkDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8702600946585126985L;
	
	@NotNull
	private Long id;
	@NotBlank
    @Size(min = 0, max = 20)
	private String nome;
	@NotEmpty
	private List<LatLongDto> coordenadas;

	
	@Override
	public String toString() {
		return "ItinerarioDto [id=" + id + ", nome=" + nome + ", itinerario=" + coordenadas.toString() + "]";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<LatLongDto> getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(List<LatLongDto> coordenadas) {
		this.coordenadas = coordenadas;
	}
	

}
