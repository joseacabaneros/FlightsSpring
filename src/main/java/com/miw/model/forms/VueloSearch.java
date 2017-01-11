package com.miw.model.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class VueloSearch {
	
	private String origen;
	private String destino;
	@NotEmpty
	private String salida;
	private String regreso;
	@NotNull
	@Min(1)
	private Integer plazas;
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getOrigen() {
		return origen;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setSalida(String salida) {
		this.salida = salida;
	}
	
	public String getSalida() {
		return salida;
	}
	
	public Date getSalidaDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(salida);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public void setRegreso(String regreso) {
		this.regreso = regreso;
	}
	
	public String getRegreso() {
		return regreso;
	}
	
	public Date getRegresoDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			if(!regreso.equals(""))
				date = formatter.parse(regreso);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}

	public Integer getPlazas() {
		return plazas;
	}

}
