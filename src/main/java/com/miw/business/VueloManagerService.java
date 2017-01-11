package com.miw.business;

import java.util.List;
import java.util.Map;

import com.miw.model.Reserva;
import com.miw.model.Vuelo;
import com.miw.model.forms.VueloSearch;

public interface VueloManagerService {
	
	public Map<String, List<String>> getAeropuertos() throws Exception;
	public Map<String, List<Vuelo>> getVuelos(VueloSearch vueloSearch) 
			throws Exception;
	public Reserva calcularPrecioVuelo(Reserva reserva, int plazas) 
			throws Exception;
	public Vuelo getVueloById(Long id) throws Exception;
	public void updatePlazas(Long idSalida, Long idRegreso, int plazasMenos) 
			throws Exception;
	
}
