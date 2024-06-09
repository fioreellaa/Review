package org.review.com.pe.DTO;

public class UserRegisterDTO {
	private Long Id;
	private String user_name;
	private String user_mail;
	private String user_password;

	public UserRegisterDTO(Long id, String user_name, String user_mail, String user_password) {
		super();
		Id = id;
		this.user_name = user_name;
		this.user_mail = user_mail;
		this.user_password = user_password;
	}

	public UserRegisterDTO(String user_name, String user_mail, String user_password) {
		super();
		this.user_name = user_name;
		this.user_mail = user_mail;
		this.user_password = user_password;
	}

	public UserRegisterDTO(String user_mail) {
		this.user_mail = user_mail;
	}

	public UserRegisterDTO() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

}
