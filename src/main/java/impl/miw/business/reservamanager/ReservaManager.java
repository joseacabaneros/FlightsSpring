package impl.miw.business.reservamanager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.miw.business.ReservaManagerService;
import com.miw.business.VueloManagerService;
import com.miw.model.Reserva;
import com.miw.model.Usuario;
import com.miw.model.Vuelo;
import com.miw.persistence.ReservaDataService;
import com.miw.persistence.UsuarioDataService;

public class ReservaManager implements ReservaManagerService{
	
	public static final int ID_LENGTH = 10;
	
	@Autowired
	private ReservaDataService reservaDataService;
	@Autowired
	private UsuarioDataService usuarioDataService;
	@Autowired
	private VueloManagerService vueloManagerService;
	
	
	public void setReservaDataService(ReservaDataService reservaDataService) {
		this.reservaDataService = reservaDataService;
	}
	
	public ReservaDataService getReservaDataService() {
		return reservaDataService;
	}
	
	public void setUsuarioDataService(UsuarioDataService usuarioDataService) {
		this.usuarioDataService = usuarioDataService;
	}
	
	public UsuarioDataService getUsuarioDataService() {
		return usuarioDataService;
	}
	
	public void setVueloManagerService(VueloManagerService 
			vueloManagerService) {
		this.vueloManagerService = vueloManagerService;
	}

	@Override
	public Reserva addReserva(Reserva reserva, Usuario usuario)
			throws Exception {
		/**Si no se encuentra el usuario(null), nunca ha realizado ninguna 
		 * reserva y es necesario guardar el usuario en la bbdd **/
		if(usuarioDataService.getUsuarioByIdentificacion(
				usuario.getIdentificacion()) == null)
			usuarioDataService.newUsuario(usuario);
			
		//Codigo de reserva e identificacion del usuario de la reserva
		reserva.setCodigoReserva(RandomStringUtils.randomAlphanumeric(
				ID_LENGTH));
		reserva.setUsuarioIdentificacion(usuario.getIdentificacion());
		
		//Actualizar el numero de plazas de los vuelos reservados (restar 
		//plazas)
		vueloManagerService.updatePlazas(reserva.getVueloSalidaId(),
				reserva.getVueloRegresoId(), (reserva.getPlazas()*-1));
		
		return reservaDataService.newReserva(reserva);
	}

	@Override
	public Reserva getReservaByCode(String code) throws Exception {
		return reservaDataService.getReservaByCode(code);
	}

	@Override
	public Usuario getUsuarioByIdentificacion(String ident) throws Exception {
		return usuarioDataService.getUsuarioByIdentificacion(ident);
	}

	@Override
	public int cancerlarReserva(String codigoReserva, String email)
			throws Exception {
		List<String> identificadores = usuarioDataService.
				getIdentificacionesByEmail(email);
		Reserva res = reservaDataService.getReservaByCode(codigoReserva);
		
		if(res != null)
			for(String ident:identificadores)
				if(res.getUsuarioIdentificacion().equals(ident)){
					Long idRegreso = null;
					if(res.getVueloRegresoId() != 0)
						idRegreso = res.getVueloRegresoId();
					
					//Actualizar el numero de plazas de los vuelos reservados
					//(sumar plazas)
					vueloManagerService.updatePlazas(res.getVueloSalidaId(),
							idRegreso, res.getPlazas());
					return reservaDataService.deleteReserva(codigoReserva);
				}
		
		return 0;
	}

	@Override
	public List<String> getDestinoPopulares() throws Exception{
		List<String> destinos = new ArrayList<String>();
		List<Long> idsPopulares = reservaDataService
				.getIdsVuelosByNumeroReservas();
		
		int i = 0;
		while(destinos.size() < 5 && idsPopulares.size() > i){
			String destino = vueloManagerService.getVueloById(idsPopulares
					.get(i)).getDestino();
			if(!destinos.contains(destino))
				destinos.add(destino);
			i++;
		}
		
		return destinos;
		
	}

	@Override
	public List<Reserva> getReservasByidentificacion(String ident)
			throws Exception {
		return reservaDataService.getReservasByIdentificacion(ident);
	}

	@Override
	public List<Vuelo> getVuelosByCodigoReserva(String codReserva)
			throws Exception {
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		Reserva reserva = reservaDataService.getReservaByCode(codReserva);
		
		Vuelo vueloSalida = vueloManagerService.getVueloById(reserva
				.getVueloSalidaId());
		Vuelo vueloRegreso = vueloManagerService.getVueloById(reserva
				.getVueloRegresoId());
		
		vuelos.add(vueloSalida);
		if(vueloRegreso != null)
			vuelos.add(vueloRegreso);
		
		return vuelos;
	}

}
