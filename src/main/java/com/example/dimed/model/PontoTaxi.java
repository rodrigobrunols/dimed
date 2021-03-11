package com.example.dimed.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Entidade PontoTaxi
 * 
 * @author rodrigo
 *
 */
public class PontoTaxi extends DimedEntity {

	private static final int DATETIME_POSITION = 3;

	private static final int LNG_POSITION = 2;

	private static final int LAT_POSITION = 1;

	private static final int NAME_POSITION = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4263883818713011722L;

	private String nome;

	private Double latitude;

	private Double longitude;

	private LocalDateTime dataHoraCadastro;

	/**
	 * 
	 */
	public PontoTaxi() {
	}
	
	/**
	 * 
	 */
	public PontoTaxi(String[] campos) {
		System.out.println(Arrays.toString(campos));
		
		setNome(campos[NAME_POSITION]);
		setLatitude(Double.valueOf(campos[LAT_POSITION]));
		setLongitude(Double.valueOf(campos[LNG_POSITION]));
		setDataHoraCadastro( LocalDateTime.parse(campos[DATETIME_POSITION]));
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public LocalDateTime getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	void criarDataHoraCadastro() {
		setDataHoraCadastro(LocalDateTime.now());
	}

}
