package org.review.com.pe.model;

import java.util.Collection;
import java.util.Date;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "T_User")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(unique = true)
	private String username;
	private String fullname;
	@Column(unique = true)
	private String usermail;
	private String userpassword;
	private String userimage;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "Id"),
	inverseJoinColumns = @JoinColumn(name="rol_id", referencedColumnName = "id"))
	private Collection<Rol> roles;
	
	@Temporal(TemporalType.DATE)
	private Date fechacreacion;
	@Temporal(TemporalType.TIME)
	private Date horacreacion;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsermail() {
		return usermail;
	}

	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	
	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Date getHoracreacion() {
		return horacreacion;
	}

	public void setHoracreacion(Date horacreacion) {
		this.horacreacion = horacreacion;
	}


	public Usuario(Long id, String username, String fullname, String usermail, String userpassword, String userimage,
			Collection<Rol> roles, Date fechacreacion, Date horacreacion) {
		super();
		Id = id;
		this.username = username;
		this.fullname = fullname;
		this.usermail = usermail;
		this.userpassword = userpassword;
		this.userimage = userimage;
		this.roles = roles;
		this.fechacreacion = fechacreacion;
		this.horacreacion = horacreacion;
	}

	public Usuario(String username, String fullname, String usermail, String userpassword, Collection<Rol> roles, String userimage) {
		
		this.username = username;
		this.fullname = fullname;
		this.usermail = usermail;
		this.userpassword = userpassword;
		this.roles = roles;
		this.userimage = userimage;
	}
	
	public Usuario() {
		 
	 }

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUserimage() {
		return userimage;
	}

	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}

}
