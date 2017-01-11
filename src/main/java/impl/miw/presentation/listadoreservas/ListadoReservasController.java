package impl.miw.presentation.listadoreservas;

import java.util.Locale;

import impl.miw.presentation.util.ContadorVisitasInternacionaUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miw.business.ReservaManagerService;

@Controller
public class ListadoReservasController extends ContadorVisitasInternacionaUtil{
	
	@Autowired
	private ReservaManagerService reservaManagerService;
	
	public void setReservaManagerService(ReservaManagerService 
			reservaManagerService) {
		this.reservaManagerService = reservaManagerService;
	}
	
	@RequestMapping("listadoReservas/{identificacion}")
	public String showReservas(@PathVariable(value="identificacion") String 
			ident, Model model) throws Exception {
		
		/**Anadir al modelo los detalles del usuario y las reservas realizadas
		 *  por el mismo**/
		addUsuarioReservasModel(model, ident);

		return "listadoReservas";
	}
	
	@RequestMapping("listadoReservas/{identificacion}/{codReserva}")
	public String showReservasVuelo(@PathVariable(value="identificacion") String 
			ident, @PathVariable(value="codReserva") String codReserva, 
			Model model) throws Exception {
		
		/**Anadir al modelo los detalles del usuario, las reservas realizadas
		 *  por el mismo y los detalles de los vuelos de la reserva
		 *  seleccionada**/
		addUsuarioReservasModel(model, ident);
		model.addAttribute("vuelos", reservaManagerService
				.getVuelosByCodigoReserva(codReserva));
		model.addAttribute("codigoReserva", codReserva);

		return "listadoReservas";
	}
	
	@RequestMapping("listadoReservas/cancelar/{identificacion}/{codReserva}")
	public String cancerlarReserva(@PathVariable(value="identificacion") String 
			ident, @PathVariable(value="codReserva") String codReserva, 
			Model model, Locale locale) throws Exception {
		
		//Cancelar reserva seleccionada
		reservaManagerService.cancerlarReserva(codReserva, 
				reservaManagerService.getUsuarioByIdentificacion(ident)
				.getEmail());
		
		/**Anadir al modelo los detalles del usuario y las reservas realizadas
		 *  por el mismo**/
		addUsuarioReservasModel(model, ident);
		
		String[] args = {codReserva};
		model.addAttribute("message", getMessageSource().getMessage(
				"exito_reserva_cancelada", args, locale));

		return "listadoReservas";
	}
	
	private void addUsuarioReservasModel(Model model, String ident) 
			throws Exception{
		model.addAttribute("usuario", reservaManagerService
				.getUsuarioByIdentificacion(ident));
		model.addAttribute("reservas", reservaManagerService.
				getReservasByidentificacion(ident));
		model.addAttribute("visitas", getVisitas());
	}

}
