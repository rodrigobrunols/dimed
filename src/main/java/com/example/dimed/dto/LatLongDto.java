package com.example.dimed.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO para o par Latitude e Longitude.
 *
 * @author rodrigo
 * @version 1.0.0
 * @since 1.0.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LatLongDto extends DimedDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8702600946585126985L;

	@NotBlank
	private Double lat;
	@NotBlank
	private Double lng;

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

	@Override
	public String toString() {
		return " [lat=" + lat + ", lng=" + lng + "]";
	}

	public LatLongDto() {
		super();
	}


}
