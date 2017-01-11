package impl.miw.persistence;

import impl.miw.persistence.conf.Conf;
import impl.miw.persistence.conf.util.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.miw.model.Reserva;
import com.miw.persistence.ReservaDataService;

public class ReservaDAO implements ReservaDataService{

	@Override
	public Reserva newReserva(Reserva reserva) throws Exception {
		PreparedStatement ps = null;
		Connection con = null;
		
		try {
			con = Jdbc.getConnection();
			
			ps = con.prepareStatement(Conf.get("SQL_RESERVA_NUEVA"));
			ps.setString(1, reserva.getCodigoReserva());
			ps.setLong(2, reserva.getVueloSalidaId());
			if(reserva.getVueloRegresoId() == null)
				ps.setNull(3, java.sql.Types.BIGINT);
			else
				ps.setLong(3, reserva.getVueloRegresoId());
			ps.setString(4, reserva.getUsuarioIdentificacion());
			ps.setInt(5, reserva.getEquipajeNormal());
			ps.setInt(6, reserva.getEquipajeGrande());
			ps.setInt(7, reserva.getCocheUtilitario());
			ps.setInt(8, reserva.getCocheFurgoneta());
			ps.setInt(9, reserva.getPlazas());
			ps.setDouble(10, reserva.getPrecioTotal());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(ps, con);
		}

		return reserva;
	}

	@Override
	public Reserva getReservaByCode(String code) throws Exception {
		Reserva reserva = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();
			
			ps = con.prepareStatement(Conf.get("SQL_RESERVA_GETBYCODE"));
			ps.setString(1, code);
			rs = ps.executeQuery();

			while (rs.next()){
				reserva = new Reserva();
				reserva.setCodigoReserva(rs.getString("codigo_reserva"));
				reserva.setVueloSalidaId(rs.getLong("vuelo_salida_id"));
				reserva.setVueloRegresoId(rs.getLong("vuelo_regreso_id"));
				reserva.setUsuarioIdentificacion(rs.getString(
						"usuario_identificacion"));
				reserva.setEquipajeNormal(rs.getInt("equipaje_normal"));
				reserva.setEquipajeGrande(rs.getInt("equipaje_grande"));
				reserva.setCocheUtilitario(rs.getInt("coche_utilitario"));
				reserva.setCocheFurgoneta(rs.getInt("coche_furgoneta"));
				reserva.setPlazas(rs.getInt("plazas"));
				reserva.setPrecioTotal(rs.getDouble("precio_total"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return reserva;
	}

	@Override
	public int deleteReserva(String codigoReserva) throws Exception {
		int retorno = 0;

		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();
			
			ps = con.prepareStatement(Conf.get("SQL_RESERVA_DELETE"));
			ps.setString(1, codigoReserva);
			retorno = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(ps,con);
		}
		
		return retorno;
	}

	@Override
	public List<Long> getIdsVuelosByNumeroReservas() throws Exception {
		List<Long> idsPopulares = new ArrayList<Long>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();
			
			ps = con.prepareStatement(Conf.get("SQL_RESERA_BYRESERVA"));
			rs = ps.executeQuery();

			while (rs.next())
				idsPopulares.add(rs.getLong("vuelo_salida_id"));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return idsPopulares;
	}

	@Override
	public List<Reserva> getReservasByIdentificacion(String identificacion)
			throws Exception {
		List<Reserva> reservas = new ArrayList<Reserva>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = Jdbc.getConnection();
			
			ps = con.prepareStatement(Conf.get("SQL_RESERVA_BYUSUARIO"));
			ps.setString(1, identificacion);
			rs = ps.executeQuery();

			while (rs.next()){
				Reserva reserva = new Reserva();
				reserva.setCodigoReserva(rs.getString("codigo_reserva"));
				reserva.setVueloSalidaId(rs.getLong("vuelo_salida_id"));
				reserva.setVueloRegresoId(rs.getLong("vuelo_regreso_id"));
				reserva.setUsuarioIdentificacion(rs.getString(
						"usuario_identificacion"));
				reserva.setEquipajeNormal(rs.getInt("equipaje_normal"));
				reserva.setEquipajeGrande(rs.getInt("equipaje_grande"));
				reserva.setCocheUtilitario(rs.getInt("coche_utilitario"));
				reserva.setCocheFurgoneta(rs.getInt("coche_furgoneta"));
				reserva.setPlazas(rs.getInt("plazas"));
				reserva.setPrecioTotal(rs.getDouble("precio_total"));
				reservas.add(reserva);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs, ps, con);
		}
		
		return reservas;
	}

}
