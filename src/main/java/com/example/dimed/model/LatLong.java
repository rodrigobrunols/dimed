package com.example.dimed.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entidade LatLong
 * 
 * @author rodrigo
 *
 */
@Entity
public class LatLong extends DimedEntity {
	/**
	* 
	*/
	private static final long serialVersionUID = -3613868567546123485L;

	@Id
	@GeneratedValue
	private Long id;

	private Double lat;

	private Double lng;
	
	private Long itinerarioId;
	
	public LatLong() {
		super();
	}
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="itinerarioId", referencedColumnName = "id", insertable = false, updatable = false)
	private Itinerario itinerario;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	@Override
	public String toString() {
		return "[lat=" + lat + ", lng=" + lng + "]";
	}

	public Long getItinerarioId() {
		return itinerarioId;
	}

	public void setItinerarioId(Long itinerarioId) {
		this.itinerarioId = itinerarioId;
	}


}
