package org.review.com.pe.Interface;

import java.util.List;

import org.review.com.pe.DTO.UserRegisterDTO;
import org.review.com.pe.model.Usuario;

public interface UsuarioInterface {
	public Usuario saveUser (Usuario registerDTO);
	public List<Usuario> listarUsuarios ();
	public Usuario autenticarUsuario(String user_mail, String user_password);
	public Usuario buscarUsuarioById (Long id);
	public Usuario saveAUser(Usuario usu);
	public Usuario updateAUser(Usuario usuario);
}
