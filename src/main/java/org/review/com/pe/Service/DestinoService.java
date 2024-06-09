package org.review.com.pe.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.review.com.pe.Interface.DestinoInterface;
import org.review.com.pe.Repository.IDestinoComentariosRepository;
import org.review.com.pe.Repository.IDestinoInteraccionDetalleRepository;
import org.review.com.pe.Repository.IDestinoInteraccionRepository;
import org.review.com.pe.Repository.IDestinoLikesRepository;
import org.review.com.pe.Repository.IDestinoRespository;
import org.review.com.pe.Repository.IUsuarioRepository;
import org.review.com.pe.model.Comentarios;
import org.review.com.pe.model.Destino;
import org.review.com.pe.model.DestinoInteraccion;
import org.review.com.pe.model.InteraccionDetalle;
import org.review.com.pe.model.Likes;
import org.review.com.pe.model.Rol;
import org.review.com.pe.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinoService implements DestinoInterface{
	@Autowired
	private IDestinoRespository dr;
	
	@Autowired
	private IDestinoComentariosRepository cr;
	
	@Autowired
	private IDestinoLikesRepository lr;
	
	@Autowired 
	private IDestinoInteraccionRepository dir;
	
	@Autowired 
	private IDestinoInteraccionDetalleRepository didr;
	
	@Autowired IUsuarioRepository ur;
	
	@Override
	public List<DestinoInteraccion> listarDestinos1(){
		return dir.findAll();
	}
	
	@Override
	public List<Destino> listarDestinos0(){
		return dr.findAll();
	}
	
	@Override 
	public List<Comentarios> listarComentarios(){
		return cr.findAll();
	}
	
	@Override
	public Comentarios saveComment (Comentarios comment) {
		Comentarios newComment = cr.save(comment);
		return newComment;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return ur.findAll();
	}

	@Override
	public Optional<Destino> findByPlaceId(String id) {
		return dr.findById(id);
	}

	@Override
	public InteraccionDetalle saveInteraccionUsuario(InteraccionDetalle idetalle) {
		InteraccionDetalle newInteraccion = didr.save(idetalle);
		return newInteraccion;
	}

	@Override
	public DestinoInteraccion updateClics(Destino destino) {
		DestinoInteraccion newClic = dir.findByDestino(destino);
		
		if (newClic != null) {
	        newClic.setClics(newClic.getClics() + 1);
	        return dir.save(newClic);
	    } else {
	        throw new RuntimeException("No se encontró el destino con el ID: " + destino.getPlaceId());
	    }
	}

	@Override
	public Likes saveLikeUsuario(Likes likes) {
		Likes newLike = lr.save(likes);
		return newLike;
	}

	@Override
	public DestinoInteraccion updateLikes(Destino destino) {
		DestinoInteraccion newLike = dir.findByDestino(destino);
		
		if (newLike != null) {
			newLike.setLikes(newLike.getLikes() + 1);
	        return dir.save(newLike);
	    } else {
	        throw new RuntimeException("No se encontró el destino con el ID: " + destino.getPlaceId());
	    }
	}

	@Override
	public DestinoInteraccion findByDestinoo(Destino destino) {
		return dir.findByDestino(destino);
	}
	
	
}
