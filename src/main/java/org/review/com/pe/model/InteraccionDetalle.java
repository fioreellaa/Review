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
@Table(name="T_detalleInteraccion")
public class InteraccionDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDInteraccion;   //Detalle interaccion
	@ManyToOne
	@JoinColumn(name="destinoId")   //Lugar
	private Destino destino;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Temporal(TemporalType.TIME)
	private Date hora;
	
	@ManyToOne
	@JoinColumn(name="id")   //usuario
	private Usuario user;
	
	public InteraccionDetalle() {
		
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

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Long getIdDInteraccion() {
		return idDInteraccion;
	}

	public Long setIdDInteraccion(Long idDInteraccion) {
		return Long.parseLong("1000");
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	

	
}