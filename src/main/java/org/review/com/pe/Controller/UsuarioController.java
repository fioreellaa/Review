package org.review.com.pe.Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.review.com.pe.DTO.UserRegisterDTO;
import org.review.com.pe.Interface.UsuarioInterface;
import org.review.com.pe.Service.UsuarioService;
import org.review.com.pe.model.Rol;
import org.review.com.pe.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@SessionAttributes({"usuario", "fechaFormateada"})
public class UsuarioController {
	
	@Autowired
	private UsuarioInterface iuser;
	
	@ModelAttribute("usuario")
	public Usuario newUser() {
		return new Usuario();
	}
	
	@GetMapping("/register")
	public String showRegister() {
		return "login";
	}
	
	@PostMapping("/register")
	public String registerNewUser(Usuario usu) {
		iuser.saveUser(usu);
		return "redirect:/user/login";
	}
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@PostMapping("/login")
    public String autenticarUsuario(String username, String userpassword, Model m, HttpSession session, Rol rol) {
        Usuario usuario = iuser.autenticarUsuario(username, userpassword);
        
        if (usuario != null) {
            m.addAttribute("usuario", usuario); 
            session.setAttribute("usuario", usuario);
            
            boolean isAdmin = usuario.getRoles().stream()
                    .anyMatch(role -> "ROLE_ADMIN".equals(role.getRolename()));
            /*Usuario usesion = (Usuario)session.getAttribute("usuario");
            System.out.println("Usuario en sesion: "+usesion.getUsername());
            System.out.println("Roles del usuario:");
            for (Rol ro : usuario.getRoles()) {
                System.out.println("- " + ro.getRolename());
            }
    		System.out.println("Hola: "+ usesion.getUsername() + " con gmail " + usesion.getUsermail());*/
            if(isAdmin) {   
            	return "redirect:/user/menu-admin/list";
            }else { 	 
            	return "redirect:/tourist-places/list"; 
            }
        } else {
            // Autenticación fallida
            return "redirect:/user/login?error=true"; 
        }
	}
	
	
	
	@GetMapping("/logout")
	public String logoutuser(HttpSession session, Model m) {

		session.setAttribute("usuario", null);
		Usuario user = (Usuario)session.getAttribute("usuario");
		m.addAttribute("usuario", null);
		if(user != null) {
			System.out.println(user + "existe");
		}else {
		System.out.println("usuario nulo -");
		}
		return "redirect:/tourist-places/list";
	}
	
	
	/*ADMINISTRADOR*/
	
	@GetMapping("/log-admin")
	public String showLoginAdmin() {
		return "alogin";
	}
	
	@GetMapping("/menu-admin")
	public String showMenuAdmin() {
		return "menu";
	}
	
	@GetMapping("/menu-admin/list")
	public String listUsers(Model m, Usuario u) {
		List<Usuario> list = iuser.listarUsuarios();

		m.addAttribute("userlist", list);
		return "menu";
	}
	
	@GetMapping("/menu-admin/log-review")
	public String showLogReview() {
		return "logreview";
	}
	
	@GetMapping("/menu-admin/list/{idUser}")
	@ResponseBody
    public Usuario formVer(@PathVariable("idUser") Long id) {
        return iuser.buscarUsuarioById(id);
    }
	
	@PostMapping("/menu-admin/register")
	public String aregisterNewUser(Usuario usu, @RequestParam String rol) {
		usu.setRoles(Arrays.asList(new Rol(rol)));	
		iuser.saveAUser(usu);
		return "redirect:/user/menu-admin/list";
	}
	
	@PostMapping("/menu-admin/update")
	public String aUpdateUser(Usuario usu, @RequestParam String rol ) {
				iuser.updateAUser(usu);
		return "redirect:/user/menu-admin/list";
	}
	
	@PostMapping("/menu-admin/delete")
	public String aDeleteUser(Usuario usu, @RequestParam String rol ) {
				iuser.updateAUser(usu);
		return "redirect:/user/menu-admin/list";
	}
}
