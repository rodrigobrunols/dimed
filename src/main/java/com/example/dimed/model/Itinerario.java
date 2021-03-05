package com.example.dimed.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entidade Itinerario
 * 
 * @author rodrigo
 *
 */
@Entity
public class Itinerario extends DimedEntity {

	/**
	* 
	*/
	private static final long serialVersionUID = -4997819902524956603L;

	@Id
	private Long id;

	private String nome;

    @OneToMany(mappedBy="itinerario", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<LatLong> coordenadas;
    
	/**
	 * 
	 */
	public Itinerario() {
	}
	
    public List<LatLong> getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(List<LatLong> coordenadas) {
		this.coordenadas = coordenadas;
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

	@Override
	public String toString() {
		return "Itinerario [id=" + id + ", nome=" + nome + ", coordenadas=" + coordenadas.toString()+ "]";
	}

	

}
