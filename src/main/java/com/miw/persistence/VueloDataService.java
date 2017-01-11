package com.miw.persistence;

import java.util.ArrayList;
import java.util.List;

import com.miw.model.Vuelo;

public interface VueloDataService {
	
	public List<String> aeropuertosOrigen() throws Exception;
	public List<String> aeropuertosDestino() throws Exception;
	public ArrayList<Vuelo> getVuelosSalida(String origen, String destino, 
			String salida, int plazas) throws Exception;
	public ArrayList<Vuelo> getVuelosRegreso(String origen, String destino, 
			String regreso, int plazas) throws Exception;
	public double precioById(Long id) throws Exception;
	public Vuelo getVueloById(Long id) throws Exception;
	public void actualizarPlazasDisponibles(Long id, int plazas) 
			throws Exception;
	
}
