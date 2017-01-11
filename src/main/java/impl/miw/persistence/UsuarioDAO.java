package impl.miw.persistence;

import impl.miw.persistence.conf.Conf;
import impl.miw.persistence.conf.util.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.miw.model.Usuario;
import com.miw.persistence.UsuarioDataService;

public class UsuarioDAO implements UsuarioDataService{

	@Override
	public Usuario newUsuario(Usuario usuario) throws Exception {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_USUARIO_NUEVO"));
			ps.setString(1, usuario.getIdentificacion());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellidos());
			ps.setString(4, usuario.getEmail());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(ps, con);
		}

		return usuario;
	}

	@Override
	public Usuario getUsuarioByIdentificacion(String ident) throws Exception {
		Usuario usuario = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_USUARIO_BYID"));
			ps.setString(1, ident);
			rs = ps.executeQuery();

			while (rs.next()){
				usuario = new Usuario();
				usuario.setIdentificacion(rs.getString("identificacion"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return usuario;
	}

	@Override
	public List<String> getIdentificacionesByEmail(String email)
			throws Exception {
		List<String> identificaciones = new ArrayList<String>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_USUARIO_IDBYEMAIL"));
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next())
				identificaciones.add(rs.getString("identificacion"));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return identificaciones;
	}

}
