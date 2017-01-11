package com.miw.business;

import java.util.List;

import com.miw.model.Reserva;
import com.miw.model.Usuario;
import com.miw.model.Vuelo;

public interface ReservaManagerService {
	
	public Reserva addReserva(Reserva reserva, Usuario usuario) 
			throws Exception;
	public Reserva getReservaByCode(String code) throws Exception;
	public Usuario getUsuarioByIdentificacion(String ident) throws Exception;
	public int cancerlarReserva(String codigoReserva, String email) 
			throws Exception;
	public List<String> getDestinoPopulares() throws Exception;
	public List<Reserva> getReservasByidentificacion(String ident) 
			throws Exception;
	public List<Vuelo> getVuelosByCodigoReserva(String codReserva) 
			throws Exception;
	
}
