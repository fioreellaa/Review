package org.review.com.pe.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="T_comentarios")
public class Comentarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdComment;
	@ManyToOne
	@JoinColumn(name="placeId")
	private Destino IdDestino;
	@ManyToOne
	@JoinColumn(name="IdUser")
	private Usuario user;
	private String comentario;
	private String username;
	
	public Comentarios(Long idComment, Destino idDestino, Usuario user, String comentario, String username) {
		IdComment = idComment;
		IdDestino = idDestino;
		this.user = user;
		this.comentario = comentario;
		this.username = username;
	}

	public Comentarios(Destino idDestino, Usuario user, String comentario) {
		IdDestino = idDestino;
		this.user = user;
		this.comentario = comentario;
	}

	public Comentarios() {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getIdComment() {
		return IdComment;
	}

	public void setIdComment(Long idComment) {
		IdComment = idComment;
	}

	public Destino getIdDestino() {
		return IdDestino;
	}

	public void setIdDestino(Destino idDestino) {
		IdDestino = idDestino;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
