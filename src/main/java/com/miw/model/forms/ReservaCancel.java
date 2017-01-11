package com.miw.model.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ReservaCancel {
	
	@NotEmpty
	@Size(min=10, max=10)
	private String codigoReserva;
	@NotEmpty 
	@Email
	private String email;
	
	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}
	
	public String getCodigoReserva() {
		return codigoReserva;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

}
