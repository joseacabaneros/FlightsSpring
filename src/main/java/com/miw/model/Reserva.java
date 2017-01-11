package com.miw.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Reserva {
	
	private String codigoReserva;
	@NotNull
	private Long vueloSalidaId;
	private Long vueloRegresoId;
	private String usuarioIdentificacion;
	@NotNull
	@Min(0) @Max(5)
	private int equipajeNormal;
	@NotNull
	@Min(0) @Max(5)
	private int equipajeGrande;
	@NotNull
	@Min(0) @Max(4)
	private int cocheUtilitario;
	@NotNull
	@Min(0) @Max(2)
	private int cocheFurgoneta;
	private int plazas;
	private double precioTotal;
	
	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}
	
	public String getCodigoReserva() {
		return codigoReserva;
	}
	
	public void setVueloSalidaId(Long vueloSalidaId) {
		this.vueloSalidaId = vueloSalidaId;
	}
	
	public Long getVueloSalidaId() {
		return vueloSalidaId;
	}
	
	public void setVueloRegresoId(Long vueloRegresoId) {
		this.vueloRegresoId = vueloRegresoId;
	}
	
	public Long getVueloRegresoId() {
		return vueloRegresoId;
	}
	
	public void setUsuarioIdentificacion(String usuarioIdentificacion) {
		this.usuarioIdentificacion = usuarioIdentificacion;
	}
	
	public String getUsuarioIdentificacion() {
		return usuarioIdentificacion;
	}
	
	public void setEquipajeNormal(int equipajeNormal) {
		this.equipajeNormal = equipajeNormal;
	}
	
	public int getEquipajeNormal() {
		return equipajeNormal;
	}

	public void setEquipajeGrande(int equipajeGrande) {
		this.equipajeGrande = equipajeGrande;
	}

	public int getEquipajeGrande() {
		return equipajeGrande;
	}

	public void setCocheUtilitario(int cocheUtilitario) {
		this.cocheUtilitario = cocheUtilitario;
	}

	public int getCocheUtilitario() {
		return cocheUtilitario;
	}

	public void setCocheFurgoneta(int cocheFurgoneta) {
		this.cocheFurgoneta = cocheFurgoneta;
	}

	public int getCocheFurgoneta() {
		return cocheFurgoneta;
	}
	
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
	public int getPlazas() {
		return plazas;
	}
	
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = Math.floor(precioTotal * 100) / 100;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}

}
