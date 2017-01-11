package com.miw.persistence;

import java.util.List;

import com.miw.model.Usuario;

public interface UsuarioDataService {

	public Usuario newUsuario(Usuario usuario) throws Exception;
	public Usuario getUsuarioByIdentificacion(String ident) throws Exception;
	public List<String> getIdentificacionesByEmail(String email) 
			throws Exception;
	
}
