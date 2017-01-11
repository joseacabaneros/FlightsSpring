package impl.miw.presentation.reserva;

import java.util.Locale;

import impl.miw.presentation.util.ContadorVisitasInternacionaUtil;
import impl.miw.presentation.util.ValidatorUsuarioSearch;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.miw.business.ReservaManagerService;
import com.miw.business.VueloManagerService;
import com.miw.model.Reserva;
import com.miw.model.Usuario;
import com.miw.model.forms.UsuarioSearch;

@Controller
@RequestMapping("reservaVuelos")
public class ReservaVueloController extends ContadorVisitasInternacionaUtil{

	@Autowired
	private VueloManagerService vueloManagerService;
	@Autowired
	private ReservaManagerService reservaManagerService;
	private Reserva reserva;
	
	
	public void setVueloManagerService(VueloManagerService 
			vueloManagerService) {
		this.vueloManagerService = vueloManagerService;
	}
	
	public void setReservaManagerService(ReservaManagerService 
			reservaManagerService) {
		this.reservaManagerService = reservaManagerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getResumen(@ModelAttribute Reserva reserva, Model model) 
			throws Exception {

		this.reserva = reserva;
		addSummaryModel(model);
		return "reservaVuelo";
	}
	
	@RequestMapping(params = "pagar", method = RequestMethod.POST)
	public String confirmarReserva(@Valid @ModelAttribute Usuario 
			usuario, BindingResult result, Model model) throws Exception {
		
		if (result.hasErrors()){
			/**Volver a anadir el resumen de la reserva en el caso de que exita 
			 * algun error al introducir los datos personales del usuario**/
			addSummaryModel(model);
			//Datos de resumen de la reserva
			model.addAttribute("reserva", reserva);
			return "reservaVuelo";
		}
		
		ReservaValidatorUsuario reservaVal = new ReservaValidatorUsuario();
		reservaVal.validate(usuario, result);
		if (result.hasErrors()){
			/**Volver a anadir el resumen de la reserva en el caso de que exita 
			 * algun error al introducir los datos personales del usuario**/
			addSummaryModel(model);
			//Datos de resumen de la reserva
			model.addAttribute("reserva", reserva);
			return "reservaVuelo";
		}
		
		//Almacenar reserva y usuario
		reserva = reservaManagerService.addReserva(reserva, usuario);
		
		return "redirect:resumenReserva/" + reserva.getCodigoReserva();
	}
	
	@RequestMapping(params = "autocompletar", method = RequestMethod.POST)
	public String autocompletarUsuario(@Valid @ModelAttribute UsuarioSearch
			usuarioSearch, BindingResult result, Model model, Locale locale) 
					throws Exception {
		
		/**Volver a anadir el resumen de la reserva en el caso de que:
		 * - Exita algun error al introducir el documento de identificacion del 
		 * usuario para autocompletar datos de mismo
		 * - No se haya introducido un documento de identificacion valido
		 * - No exista ningun usuario con el documento de identificacion 
		 * introducido
		 * - Todo ok
		 * **/
		addSummaryModel(model);
		//Datos de resumen de la reserva
		model.addAttribute("reserva", reserva);
		
		if (result.hasErrors())
			return "reservaVuelo";
	
		ValidatorUsuarioSearch searchValUs = new ValidatorUsuarioSearch();
		searchValUs.validate(usuarioSearch, result);
		if (result.hasErrors())
			return "reservaVuelo";
		
		Usuario usuario = reservaManagerService
				.getUsuarioByIdentificacion(usuarioSearch.getIdentificacion());
		
		//No existe usuario con el documento de identificacion introducido
		if(usuario == null){
			String[] args = {usuarioSearch.getIdentificacion()};
			model.addAttribute("message", getMessageSource().getMessage(
					"error_no_usuario", args, locale));
		//Existe usuario con el documento de identificacion introducido
		}else{
			//Todo ok
			model.addAttribute("usuario", usuario);
		}
		
		return "reservaVuelo";
	}
	
	@ModelAttribute
	Usuario getUsuario(){
		return new Usuario();
	}
	
	@ModelAttribute
	UsuarioSearch getUsuarioSearch(){
		return new UsuarioSearch();
	}
	
	private void addSummaryModel(Model model) throws Exception{
		model.addAttribute("vueloSalida", vueloManagerService.getVueloById(
				reserva.getVueloSalidaId()));
		//El usuario a contratado vuelo de regreso
		model.addAttribute("vueloRegreso", vueloManagerService.getVueloById(
				reserva.getVueloRegresoId()));
		model.addAttribute("visitas", getVisitas());
	}
	
}
