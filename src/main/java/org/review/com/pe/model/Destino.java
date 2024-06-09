package org.review.com.pe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="T_Destino")
public class Destino {
	@Id
	private String placeId;
	private String touristPlace;
	@Column(columnDefinition = "TEXT")
	private String description;
	private String imagen;
	private String msnm;
	private String dificultad;
	private String consideraciones;
	
	public Destino(String placeId, String touristPlace, String description, String imagen, String msnm,
			String dificultad, String consideraciones) {
		this.placeId = placeId;
		this.touristPlace = touristPlace;
		this.description = description;
		this.imagen = imagen;
		this.msnm = msnm;
		this.dificultad = dificultad;
		this.consideraciones = consideraciones;
	}

	public Destino() {
		
	}
	
	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getTouristPlace() {
		return touristPlace;
	}

	public void setTouristPlace(String touristPlace) {
		this.touristPlace = touristPlace;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getMsnm() {
		return msnm;
	}

	public void setMsnm(String msnm) {
		this.msnm = msnm;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getConsideraciones() {
		return consideraciones;
	}

	public void setConsideraciones(String consideraciones) {
		this.consideraciones = consideraciones;
	}

	
	
	
}
