package impl.miw.business.vuelomanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.miw.business.VueloManagerService;
import com.miw.model.Reserva;
import com.miw.model.Vuelo;
import com.miw.model.forms.VueloSearch;
import com.miw.persistence.VueloDataService;

public class VueloManager implements VueloManagerService{
	
	@Autowired
	private VueloDataService vueloDataService;
	
	//Precios por configuracion del bean
	private Map<String,Integer> equipaje;
	private Map<String,Integer> coche;
	

	public void setVueloDataService(VueloDataService vueloDataService) {
		this.vueloDataService = vueloDataService;
	}

	public VueloDataService getVueloDataService() {
		return vueloDataService;
	}

	public void setEquipaje(Map<String, Integer> equipaje) {
		this.equipaje = equipaje;
	}

	public void setCoche(Map<String, Integer> coche) {
		this.coche = coche;
	}
	
	@Override
	public Map<String, List<String>> getAeropuertos() throws Exception {
		Map<String, List<String>> aeropuertos = 
				new HashMap<String, List<String>>();
		aeropuertos.put("origenes", vueloDataService.aeropuertosOrigen());
		aeropuertos.put("destinos", vueloDataService.aeropuertosDestino());
		
		return aeropuertos;
	}

	@Override
	public Map<String, List<Vuelo>> getVuelos(VueloSearch vueloSearch) 
			throws Exception {
		Map<String, List<Vuelo>> vuelos = new HashMap<String, List<Vuelo>>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		//Vuelo de salida
		vuelos.put("vuelosSalida", vueloDataService.getVuelosSalida(
				vueloSearch.getOrigen(), vueloSearch.getDestino(), 
				df.format(vueloSearch.getSalidaDate()), 
				vueloSearch.getPlazas()));
		
		//El usuario ha solicitado busqueda de vuelo de regreso
		if(vueloSearch.getRegresoDate() != null){
			vuelos.put("vuelosRegreso", vueloDataService.getVuelosRegreso(
					vueloSearch.getDestino(), vueloSearch.getOrigen(), 
					df.format(vueloSearch.getRegresoDate()),
					vueloSearch.getPlazas()));
		}
		return vuelos;
	}

	@Override
	public Reserva calcularPrecioVuelo(Reserva reserva, int plazas) 
					throws Exception {
		double precioVuelos = vueloDataService.precioById(
				reserva.getVueloSalidaId()) * plazas;
		
		//El usuario ha contratado vuelo de regreso
		if(reserva.getVueloRegresoId() != null)
			precioVuelos +=  vueloDataService.precioById(
					reserva.getVueloRegresoId()) * plazas;
		//Facturar equipaje
		precioVuelos += (this.equipaje.get("normal") * 
				reserva.getEquipajeNormal()) + (this.equipaje.get("grande") * 
					reserva.getEquipajeGrande());
		//Coche de alquiler
		precioVuelos += (this.coche.get("utilitario") * 
				reserva.getCocheUtilitario()) + (this.coche.get("furgoneta") * 
					reserva.getCocheFurgoneta());
		
		reserva.setPrecioTotal(precioVuelos);
		reserva.setPlazas(plazas);
		return reserva;
	}

	@Override
	public Vuelo getVueloById(Long id) throws Exception {
		if(id == null)
			return null;
		else
			return vueloDataService.getVueloById(id);
	}

	@Override
	public void updatePlazas(Long idSalida, Long idRegreso, int plazasMenos) 
			throws Exception {
	
		vueloDataService.actualizarPlazasDisponibles(idSalida, (
				vueloDataService.getVueloById(idSalida).getPlazas() + 
				plazasMenos));
		//El usuario ha contratado vuelo de regreso
		if(idRegreso != null)
			vueloDataService.actualizarPlazasDisponibles(idRegreso, (
					vueloDataService.getVueloById(idRegreso).getPlazas() + 
					plazasMenos));
	}

}
