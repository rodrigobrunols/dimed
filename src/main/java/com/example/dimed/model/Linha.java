package com.example.dimed.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entidade Linha
 * 
 * @author rodrigo
 *
 */
@Entity
public class Linha extends FrameworkEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7369496942983620963L;

	@Id
	private Long id;

	private String codigo;

	private String nome;
//
//	@OneToOne(cascade = CascadeType.DETACH , fetch = FetchType.LAZY)
//	@JoinColumn(name = "itinerario_id", referencedColumnName = "id", nullable = true, updatable = true)
//	private Itinerario itinerario;

	/**
	 * 
	 */
	public Linha() {
	}

	@Override
	public String toString() {
		return "Linha [id=" + id + ", codigo=" + codigo + ", nome=" + nome + "]";
	}

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
