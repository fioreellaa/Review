package org.review.com.pe.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.review.com.pe.DTO.UserRegisterDTO;
import org.review.com.pe.Interface.UsuarioInterface;
import org.review.com.pe.Repository.IUsuarioRepository;
import org.review.com.pe.model.Rol;
import org.review.com.pe.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioInterface{
	
	@Autowired
	private IUsuarioRepository userRepo;
	
	@Override
	public Usuario saveUser (Usuario usuario) {
		usuario.setRoles(Arrays.asList(new Rol("ROLE_USER")));	
		usuario.setUserimage("user-no-photo.png");
		usuario.setFechacreacion(new Date());
		usuario.setHoracreacion(new Date());
		Usuario newUser = userRepo.save(usuario);
		return newUser;
	}

	@Override
	public Usuario autenticarUsuario(String user, String pass) {
		Usuario usuario = userRepo.findByUsername(user);

        if (usuario != null && usuario.getUserpassword().equals(pass)) {
            return usuario;
        }
        
        return null;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return userRepo.findAll();
	}

	@Override
	public Usuario buscarUsuarioById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public Usuario saveAUser(Usuario usu) {
		usu.setUserimage("user-no-photo.png");
		usu.setFechacreacion(new Date());
		usu.setHoracreacion(new Date());
		Usuario newUser = userRepo.save(usu);
		return newUser;
	}

	@Override
	public Usuario updateAUser(Usuario usuario) {
		return userRepo.save(usuario);
	}

	
}
