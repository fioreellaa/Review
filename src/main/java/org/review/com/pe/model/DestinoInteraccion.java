package org.review.com.pe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="T_DestinoInteraccion")
public class DestinoInteraccion {
	@Id
	private String interaccionId;
	
	@ManyToOne
	@JoinColumn(name="placeId")  //detalle -> lugar, 
	private Destino destino;
	private int likes;
	private int coments;
	private int clics;

	public DestinoInteraccion(String interaccionId, Destino destino, int likes, int coments, int clics) {
		this.interaccionId = interaccionId;
		this.destino = destino;
		this.likes = likes;
		this.coments = coments;
		this.clics = clics;
	}

	public DestinoInteraccion() {
		
	}

	public String getInteraccionId() {
		return interaccionId;
	}

	public void setInteraccionId(String interaccionId) {
		this.interaccionId = interaccionId;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getComents() {
		return coments;
	}

	public void setComents(int coments) {
		this.coments = coments;
	}

	public int getClics() {
		return clics;
	}

	public void setClics(int clics) {
		this.clics = clics;
	}
	
	
	
	
}
