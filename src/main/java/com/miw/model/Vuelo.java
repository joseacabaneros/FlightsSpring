package com.miw.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Vuelo {

	private Long id;
	private String origen;
	private String destino;
	private Timestamp fechaSalida;
	private Time duracion;
	private int plazas;
	private double precio;
	private String imagenUrl;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
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
	
	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public Timestamp getFechaSalida() {
		return fechaSalida;
	}
	
	public String getFechaSalidaDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return df.format(new Date(fechaSalida.getTime()));
	}
	
	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}
	
	public Time getDuracion() {
		return duracion;
	}
	
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
	public int getPlazas() {
		return plazas;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
	
	public String getImagenUrl() {
		return imagenUrl;
	}
	
}
