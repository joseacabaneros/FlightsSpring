package com.miw.model.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioSearch {
	
	@NotEmpty
	private String identificacion;
	private String tipoIdentificacion;
	
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}
	
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

}
