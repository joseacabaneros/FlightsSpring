package impl.miw.presentation.listadovuelos;

import impl.miw.presentation.util.ContadorVisitasInternacionaUtil;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miw.business.VueloManagerService;
import com.miw.model.Reserva;
import com.miw.model.forms.VueloSearch;

@Controller
@RequestMapping("listadoVuelos")
public class ListadoVuelosController extends ContadorVisitasInternacionaUtil{
	
	@Autowired
	private VueloManagerService vueloManagerService;
	private VueloSearch vueloSearch;
	
	public void setVueloManagerService(VueloManagerService 
			vueloManagerService) {
		this.vueloManagerService = vueloManagerService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String listarVuelos(@ModelAttribute VueloSearch vueloSearch, 
			Model model) throws Exception {
		/**Si se acceder por url al listado de vuelos, redirigir al usuario a 
		 * la pantalla principal**/
		if(vueloSearch.getSalida() == null)
			return "redirect:searchVuelos";
			
		this.vueloSearch = vueloSearch;
		addVuelosModel(model);
		
		return "listadoVuelos";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String vuelosSeleccionados(@Valid @ModelAttribute Reserva 
			reserva, BindingResult result, Model model, RedirectAttributes 
			redirectAttributes) throws Exception {
		
		if (result.hasErrors()){
			/**Volver a anadir los vuelos de la busqueda y los datos de busqueda
			 * en el caso de que exita algun error al seleccionar los vuelos**/
			addVuelosModel(model);
			//Datos de la busqueda
			model.addAttribute("vueloSearch", vueloSearch);
			return "listadoVuelos";
		}
		//Calcular precio total
		reserva = vueloManagerService.calcularPrecioVuelo(reserva, 
				vueloSearch.getPlazas());
		
		/**Enviamos al controlador 'reservaVuelo' el modelo 'reserva' que 
		 * contiene los datos de los vuelos, las opciones de equipaje, 
		 * coche de alquiler y plazas que seran reservadas para ser completado 
		 * en siguiente controlador con la informacion personal**/
		redirectAttributes.addFlashAttribute("reserva", reserva);
		return "redirect:reservaVuelos";
	}
	
	@ModelAttribute
	Reserva getReserva(){
		return new Reserva();
	}
	
	private void addVuelosModel(Model model) throws Exception{
		model.addAttribute("vuelosSalida",vueloManagerService
				.getVuelos(vueloSearch).get("vuelosSalida"));
		model.addAttribute("vuelosRegreso",vueloManagerService
				.getVuelos(vueloSearch).get("vuelosRegreso"));
		model.addAttribute("visitas", getVisitas());
	}

}
