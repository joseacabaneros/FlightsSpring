package impl.miw.presentation.reserva;

import impl.miw.presentation.util.ValidarIdentificacion;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.miw.model.Usuario;

public class ReservaValidatorUsuario implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario) target;
		
		if(usuario.getTipoIdentificacion().equals("dni") && 
				!ValidarIdentificacion.validarDNI(usuario.getIdentificacion()))
			errors.rejectValue("","error_dni");
		
		if(usuario.getTipoIdentificacion().equals("nie") && 
				!ValidarIdentificacion.validarNIE(usuario.getIdentificacion()))
			errors.rejectValue("","error_nie");
		
		if(usuario.getTipoIdentificacion().equals("pasaporte") && 
				(!ValidarIdentificacion.validarDNI(usuario.getIdentificacion()) 
						&& !ValidarIdentificacion.validarNIE(
								usuario.getIdentificacion())))
			errors.rejectValue("","error_pasaporte");
	}
	
	

}
