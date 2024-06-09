package org.review.com.pe.Interface;

import java.util.List;
import java.util.Optional;

import org.review.com.pe.model.Comentarios;
import org.review.com.pe.model.Destino;
import org.review.com.pe.model.DestinoInteraccion;
import org.review.com.pe.model.InteraccionDetalle;
import org.review.com.pe.model.Likes;
import org.review.com.pe.model.Usuario;

public interface DestinoInterface {
	
	public List<DestinoInteraccion> listarDestinos1();
	public List<Destino> listarDestinos0();
	public List<Comentarios> listarComentarios();
	public List<Usuario> listarUsuarios();
	public Comentarios saveComment (Comentarios comment);
	public Optional<Destino> findByPlaceId(String id);
	public InteraccionDetalle saveInteraccionUsuario(InteraccionDetalle idetalle);
	//public Optional<DestinoInteraccion> findByDestinoo(String id);
	public DestinoInteraccion updateClics(Destino destino);
	public Likes saveLikeUsuario(Likes likes);
	public DestinoInteraccion updateLikes(Destino destino);
	public DestinoInteraccion findByDestinoo(Destino destino);
}
