package impl.miw.presentation.search;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miw.business.ReservaManagerService;
import com.miw.business.VueloManagerService;
import com.miw.model.forms.ReservaCancel;
import com.miw.model.forms.UsuarioSearch;
import com.miw.model.forms.VueloSearch;

@Controller
@RequestMapping("searchVuelos")
public class SearchVuelosController extends ContadorVisitasInternacionaUtil{
	
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
	
	@RequestMapping(method = RequestMethod.GET)
	public void get(Model model) throws Exception {
		//Anadir los vuelos a los selects del formulario
		addFlightsModel(model);
	}
	
	@RequestMapping(params = "search", method = RequestMethod.POST)
	public String searchVuelos(@Valid @ModelAttribute VueloSearch vueloSearch, 
			BindingResult result, Model model, RedirectAttributes 
			redirectAttributes) throws Exception {
		
		if (result.hasErrors()){
			/**Volver a anadir los vuelos a los select en el caso de que exita 
			 * algun error al rellenar el formulario por el usuario**/
			addFlightsModel(model);
			return "searchVuelos";
		}
		
		SearchValidatorVuelo searchValVuel = new SearchValidatorVuelo();
		searchValVuel.validate(vueloSearch, result);
		if (result.hasErrors()){
			/**Volver a anadir los vuelos a los select en el caso de que exita 
			 * algun error al rellenar el formulario por el usuario**/
			addFlightsModel(model);
			return "searchVuelos";
		}
		
		/**Enviamos al controlador 'listadoVuelos' el modelo 'vueloSearch' que 
		 * contiene los datos del formulario de busqueda rellenados por el 
		 * usuario**/
		redirectAttributes.addFlashAttribute("vueloSearch", vueloSearch);
		return "redirect:listadoVuelos";
	}
	
	@RequestMapping(params = "cancelar", method = RequestMethod.POST)
	public String cancelarReserva(@Valid @ModelAttribute ReservaCancel 
			reservaCancel, BindingResult result, Model model, Locale locale) 
					throws Exception {
		
		/**Volver a anadir los vuelos a los select (necesario al tratarse en 
		 * cualquier caso de la misma vista)**/
		addFlightsModel(model);
		
		if (result.hasErrors())
			return "searchVuelos";
		
		int resultado = reservaManagerService.cancerlarReserva(reservaCancel
				.getCodigoReserva(),reservaCancel.getEmail());
		if(resultado == 1){
			String[] args = {reservaCancel.getCodigoReserva()};
			model.addAttribute("message_exito", getMessageSource().getMessage(
					"exito_reserva_cancelada", args, locale));
		}
		else{
			String[] args = {reservaCancel.getCodigoReserva(), 
					reservaCancel.getEmail()};
			model.addAttribute("message_error", getMessageSource().getMessage(
					"error_reserva_cancelar", args, locale));
		}
		
		return "searchVuelos";
	}
	
	@RequestMapping(params = "reservas", method = RequestMethod.POST)
	public String buscarReservas(@Valid @ModelAttribute UsuarioSearch 
			usuarioSearch, BindingResult result, Model model, Locale locale) 
					throws Exception {
		
		if (result.hasErrors()){
			/**Volver a anadir los vuelos a los select en el caso de que no 
			 * se haya introducido el documento de identificacion y se haya
			 * enviado dicho formulario**/
			addFlightsModel(model);
			return "searchVuelos";
		}
		
		ValidatorUsuarioSearch searchValUs = new ValidatorUsuarioSearch();
		searchValUs.validate(usuarioSearch, result);
		if (result.hasErrors()){
			/**Volver a anadir los vuelos a los select en el caso de que no 
			 * se haya introducido un documento de identificacion valido**/
			addFlightsModel(model);
			return "searchVuelos";
		}
		
		if(reservaManagerService.getUsuarioByIdentificacion(
				usuarioSearch.getIdentificacion()) == null){
			/**Volver a anadir los vuelos a los select en el caso de que no 
			 * existe ningun usuario con ese documento de identificacion**/
			addFlightsModel(model);
			
			String[] args = {usuarioSearch.getIdentificacion()};
			model.addAttribute("message_error", getMessageSource().getMessage(
					"error_listado_identificacion", args, locale));
			
			return "searchVuelos";
		}
		
		//Documento de identificacion existe asociado a un usuario
		return "redirect:listadoReservas/" + usuarioSearch.getIdentificacion();
	}
	
	/**Modelo para formulario de busqueda**/
	@ModelAttribute
	VueloSearch getVueloSearch(){
		return new VueloSearch();
	}
	
	/**Modelo para formulario de cancelacion de reserva**/
	@ModelAttribute
	ReservaCancel getReservaCancel(){
		return new ReservaCancel();
	}
	
	/**Modelo para formulario de listado de reservas por identificacion del
	 * usuario**/
	@ModelAttribute
	UsuarioSearch getUsuarioSearch(){
		return new UsuarioSearch();
	}

	private void addFlightsModel(Model model) throws Exception{
		model.addAttribute("origenes",vueloManagerService.
				getAeropuertos().get("origenes"));
		model.addAttribute("destinos",vueloManagerService.
				getAeropuertos().get("destinos"));
		model.addAttribute("populares",reservaManagerService
				.getDestinoPopulares());
		model.addAttribute("visitas", getVisitas());
	}

}
