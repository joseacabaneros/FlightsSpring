package impl.miw.presentation.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.miw.model.forms.UsuarioSearch;

public class ValidatorUsuarioSearch implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioSearch.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UsuarioSearch usuarioSearch = (UsuarioSearch) target;
		
		if(usuarioSearch.getTipoIdentificacion().equals("dni") && 
				!ValidarIdentificacion.validarDNI(usuarioSearch
						.getIdentificacion()))
			errors.rejectValue("","error_dni");
		
		if(usuarioSearch.getTipoIdentificacion().equals("nie") && 
				!ValidarIdentificacion.validarNIE(usuarioSearch
						.getIdentificacion()))
			errors.rejectValue("","error_nie");
		
		if(usuarioSearch.getTipoIdentificacion().equals("pasaporte") && 
				(!ValidarIdentificacion.validarDNI(usuarioSearch
						.getIdentificacion()) 
						&& !ValidarIdentificacion.validarNIE(
								usuarioSearch.getIdentificacion())))
			errors.rejectValue("","error_pasaporte");
	}

}
