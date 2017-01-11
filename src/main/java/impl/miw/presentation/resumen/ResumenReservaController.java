package impl.miw.presentation.resumen;

import impl.miw.presentation.util.ContadorVisitasInternacionaUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miw.business.ReservaManagerService;
import com.miw.business.VueloManagerService;
import com.miw.model.Reserva;

@Controller
public class ResumenReservaController extends ContadorVisitasInternacionaUtil{
	
	@Autowired
	private VueloManagerService vueloManagerService;
	@Autowired
	private ReservaManagerService reservaManagerService;

	
	public void setVueloManagerService(VueloManagerService 
			vueloManagerService) {
		this.vueloManagerService = vueloManagerService;
	}
	
	public void setReservaManagerService(ReservaManagerService 
			reservaManagerService) {
		this.reservaManagerService = reservaManagerService;
	}
	
	@RequestMapping("resumenReserva/{codigoReserva}")
	public String showSummaryReserva(@PathVariable(
			value="codigoReserva") String code, Model model) throws Exception {

		/**Anadir los modelos de reserva y usuario recientemente creados y los
		 * modelos de los vuelos reservados para pasarlos a la vista 
		 * 'resumenReserva' y mostrar al usuario un resumen de la reserva 
		 * realizada**/
		Reserva resumen = reservaManagerService.getReservaByCode(code);
		model.addAttribute("reserva", resumen);
		model.addAttribute("usuario", reservaManagerService.
				getUsuarioByIdentificacion(resumen.getUsuarioIdentificacion()));
		model.addAttribute("vueloSalida", vueloManagerService.getVueloById(
				resumen.getVueloSalidaId()));
		model.addAttribute("vueloRegreso", vueloManagerService.getVueloById(
				resumen.getVueloRegresoId()));
		model.addAttribute("visitas", getVisitas());
		
		return "resumenReserva";
		
	}

}
