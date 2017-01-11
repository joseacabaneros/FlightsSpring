package impl.miw.business.vuelomanager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.miw.business.VueloManagerService;
import com.miw.model.Reserva;
import com.miw.model.Vuelo;
import com.miw.model.forms.VueloSearch;

public class VueloManagerEJBService implements VueloManagerService{
	
	@Autowired
	private VueloManager vueloManager;

	public void setVueloManager(VueloManager vueloManager) {
		this.vueloManager = vueloManager;
	}

	@Override
	public Map<String, List<String>> getAeropuertos() throws Exception {
		System.out.println("Obteniendo aeropuertos de origen y destino");
		return vueloManager.getAeropuertos();
	}

	@Override
	public Map<String, List<Vuelo>> getVuelos(VueloSearch vueloSearch) 
			throws Exception {
		System.out.println("Obteniendo vuelos de la busqueda");
		return vueloManager.getVuelos(vueloSearch);
	}

	@Override
	public Reserva calcularPrecioVuelo(Reserva reserva, int plazas) 
			throws Exception {
		System.out.println("Calculado precio total de la reserva");
		return vueloManager.calcularPrecioVuelo(reserva, plazas);
	}

	@Override
	public Vuelo getVueloById(Long id) throws Exception {
		System.out.println("Obteniendo vuelo por su identificacion");
		return vueloManager.getVueloById(id);
	}

	@Override
	public void updatePlazas(Long idSalida, Long idRegreso, int plazasMenos) 
			throws Exception {
		System.out.println("Actualizando las plazas disponibles de los vuelos"
				+ "reservados");
		vueloManager.updatePlazas(idSalida, idRegreso, plazasMenos);
	}

}
