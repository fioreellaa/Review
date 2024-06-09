package org.review.com.pe.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.review.com.pe.Interface.DestinoInterface;
import org.review.com.pe.Repository.IDestinoRespository;
import org.review.com.pe.Service.DestinoService;
import org.review.com.pe.model.Comentarios;
import org.review.com.pe.model.Destino;
import org.review.com.pe.model.DestinoInteraccion;
import org.review.com.pe.model.InteraccionDetalle;
import org.review.com.pe.model.Likes;
import org.review.com.pe.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.yaml.snakeyaml.tokens.CommentToken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/tourist-places")
@SessionAttributes({"usuario", "name"})
public class DestinoController {

	@Autowired
	private DestinoInterface idestino;
	
	
	@ModelAttribute
	public String xuser(HttpSession session, Model m) {
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		if(user != null) {
			
			System.out.println("usuario con m "+user.getFullname());
		}else {
			System.out.println("usuario nulo");
		}
		
		return "index";
	}
	
	@GetMapping("/list")
	public String ListarDestinos (Model m) {
		List<DestinoInteraccion> di = idestino.listarDestinos1();
		List<Comentarios> cmm = idestino.listarComentarios();
		List<Usuario> us = idestino.listarUsuarios();
		List<Destino> d = idestino.listarDestinos0();
		
		if(d.isEmpty()) {
			d = new ArrayList<>();
		}
		m.addAttribute("list_interaccion", di);
		m.addAttribute("list_places", d);
		m.addAttribute("list_comments", cmm);
		m.addAttribute("list_users", us);
		return "index";
	}
	
	
	@PostMapping("/comment")
	@ResponseBody
	public Map<String, String> addComment(@RequestParam(name = "placeId") Destino destino, 
			@RequestParam(name = "comentario") String comentario
			,Comentarios comment, HttpSession session) throws JsonProcessingException{
		Map<String, String> newComment = new HashMap<>();
		Usuario user = (Usuario)session.getAttribute("usuario");
		String nombre = (String)session.getAttribute("name");
		
		if(user == null) {
			comment.setUser(null);
			comment.setUsername(nombre);
		}else {
			comment.setUser(user);
			newComment.put("usern", user.getUsername());
			newComment.put("userim", user.getUserimage());
		}

		comment.setIdDestino(destino);
		comment.setComentario(comentario);
		Comentarios com = idestino.saveComment(comment);
		
		if(com == null) {
			newComment.put("MENSAJE", "mal");
			
		}else {
			newComment.put("MENSAJE", "bien");			
			newComment.put("comentario", comentario);
		}
		
		return newComment;
	}
	
	@PostMapping("/tempusername")
	@ResponseBody
	public Map<String, String> addUserName (@RequestParam("username")String name , HttpSession session){
		Map<String, String> tempUser = new HashMap<>();
		
		if(name != null) {
			System.out.println("nombre "+name);
			session.setAttribute("name", name);
			tempUser.put("username", name);
		}else {
			tempUser.put("MENSAJE", "error");
		}
		
		return tempUser;
	}
	
	private String userToJson(Usuario user) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        return objectMapper.writeValueAsString(user);
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@GetMapping("/userget")
	public Map<String, String> getUserName (HttpSession session){
		Map<String, String> getUser = new HashMap<>();
		Usuario user = (Usuario)session.getAttribute("usuario");
		
		if(user != null) {
			System.out.println("usuario existe "+ user.getUsername() );
			getUser.put("usern", user.getUsername());
		}else {
			getUser.put("usern", null);
			System.out.println("usuario no existe " );
		}
		
		return getUser;
	}
	
	
	@PostMapping("/clic")
	@ResponseBody
	public Map<String, String> newInteraction(HttpSession session, InteraccionDetalle detalleU, 
			@RequestParam String destinoValue) {
		Map<String, String> addInte = new HashMap<>();
		String placeId = destinoValue;
		Usuario user = (Usuario)session.getAttribute("usuario");
		Destino des = idestino.findByPlaceId(placeId).orElse(null);
				
		detalleU.setDestino(des);
		detalleU.setFecha(new Date());
		detalleU.setHora(new Date());
		
		if(user == null) {
			detalleU.setUser(null);
			addInte.put("placeId", placeId);
		}else {
			detalleU.setUser(user);
			addInte.put("placeId", placeId);
		}
		idestino.saveInteraccionUsuario(detalleU);
		idestino.updateClics(des);
		return addInte;
	}
	
	@PostMapping("/like")
	@ResponseBody
	public Map<String, String> newLike(HttpSession session, Likes likes, 
			@RequestParam String destinoValue) {
		Map<String, String> addLike = new HashMap<>();
		String placeId = destinoValue;
		Usuario user = (Usuario)session.getAttribute("usuario");
		Destino des = idestino.findByPlaceId(placeId).orElse(null);
		DestinoInteraccion di = idestino.findByDestinoo(des);

		
		likes.setDestino(des);
		likes.setFecha(new Date());
		likes.setHora(new Date());
		
		if(user == null) {
			likes.setUser(null);
			addLike.put("placeId", placeId);
			addLike.put("nlikes", String.valueOf(di.getLikes() + 1));
		}else {
			likes.setUser(user);
			addLike.put("placeId", placeId);
			addLike.put("nlikes", String.valueOf(di.getLikes()));
		}
		
		idestino.saveLikeUsuario(likes);
		idestino.updateLikes(des);
		return addLike;
	}
}
