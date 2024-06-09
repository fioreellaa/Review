package org.review.com.pe.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="t_likes")
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdLike;
	@ManyToOne
	@JoinColumn(name = "placeId")
	private Destino destino;
	@ManyToOne
	@JoinColumn(name = "IdUser")
	private Usuario user;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Temporal(TemporalType.TIME)
	private Date hora;

	public Long getIdLike() {
		return IdLike;
	}

	public void setIdLike(Long idLike) {
		IdLike = idLike;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino idDestino) {
		destino = idDestino;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Likes(Long idLike, Destino destino, Usuario user, Date fecha, Date hora) {
		super();
		IdLike = idLike;
		this.destino = destino;
		this.user = user;
		this.fecha = fecha;
		this.hora = hora;
	}

	public Likes() {
	}

}
