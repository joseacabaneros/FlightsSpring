package impl.miw.presentation.search;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.miw.model.forms.VueloSearch;

public class SearchValidatorVuelo implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return VueloSearch.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		VueloSearch search = (VueloSearch) target;
		
		if(search.getOrigen().equals(search.getDestino()))
			errors.rejectValue("","error_mismo_aeropuertos");
		
		if(search.getRegresoDate() != null)
			if(search.getRegresoDate().before(search.getSalidaDate()))
				errors.rejectValue("","error_fecha_salida_regreso");
	}


}
