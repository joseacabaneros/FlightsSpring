package com.miw.persistence;

import java.util.List;

import com.miw.model.Reserva;

public interface ReservaDataService {
	
	public Reserva newReserva(Reserva reserva) throws Exception;
	public Reserva getReservaByCode(String code) throws Exception;
	public int deleteReserva(String codigoReserva) throws Exception;
	public List<Long> getIdsVuelosByNumeroReservas() throws Exception;
	public List<Reserva> getReservasByIdentificacion(String identificacion) 
			throws Exception;
}
