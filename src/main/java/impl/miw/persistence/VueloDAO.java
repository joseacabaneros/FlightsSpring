package impl.miw.persistence;

import impl.miw.persistence.conf.Conf;
import impl.miw.persistence.conf.util.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.miw.model.Vuelo;
import com.miw.persistence.VueloDataService;

public class VueloDAO implements VueloDataService{

	@Override
	public List<String> aeropuertosOrigen() throws Exception {
		List<String> origenes = new ArrayList<String>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_VUELO_ORIGEN"));
			rs = ps.executeQuery();

			while (rs.next()) 
				origenes.add(rs.getString("origen"));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return origenes;
	}

	@Override
	public List<String> aeropuertosDestino() throws Exception {
		List<String> destinos = new ArrayList<String>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_VUELO_DESTINO"));
			rs = ps.executeQuery();

			while (rs.next()) 
				destinos.add(rs.getString("destino"));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return destinos;
	}

	@Override
	public ArrayList<Vuelo> getVuelosSalida(String origen, String destino,
			String salida, int plazas) throws Exception {
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_VUELO_VUELOS"));
			ps.setString(1, origen);
			ps.setString(2, destino);
			ps.setString(3, salida+"%");
			ps.setInt(4, plazas);
			rs = ps.executeQuery();

			while (rs.next()){
				Vuelo vuelo = new Vuelo();
				vuelo.setId(rs.getLong("id"));
				vuelo.setOrigen(rs.getString("origen"));
				vuelo.setDestino(rs.getString("destino"));
				vuelo.setFechaSalida(rs.getTimestamp("fecha_salida"));
				vuelo.setDuracion(rs.getTime("duracion"));
				vuelo.setPlazas(rs.getInt("plazas"));
				vuelo.setPrecio(rs.getDouble("precio"));
				vuelo.setImagenUrl(rs.getString("imagen_url"));
				vuelos.add(vuelo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return vuelos;
	}

	@Override
	public ArrayList<Vuelo> getVuelosRegreso(String origen, String destino,
			String regreso, int plazas) throws Exception {
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_VUELO_VUELOS"));
			ps.setString(1, origen);
			ps.setString(2, destino);
			ps.setString(3, regreso+"%");
			ps.setInt(4, plazas);
			rs = ps.executeQuery();

			while (rs.next()){
				Vuelo vuelo = new Vuelo();
				vuelo.setId(rs.getLong("id"));
				vuelo.setOrigen(rs.getString("origen"));
				vuelo.setDestino(rs.getString("destino"));
				vuelo.setFechaSalida(rs.getTimestamp("fecha_salida"));
				vuelo.setDuracion(rs.getTime("duracion"));
				vuelo.setPlazas(rs.getInt("plazas"));
				vuelo.setPrecio(rs.getDouble("precio"));
				vuelo.setImagenUrl(rs.getString("imagen_url"));
				vuelos.add(vuelo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return vuelos;
	}

	@Override
	public double precioById(Long id) throws Exception {
		double precio = 0;

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_VUELO_PRECIOBYID"));
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()) 
				precio = rs.getDouble("precio");

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return precio;
	}

	@Override
	public Vuelo getVueloById(Long id) throws Exception {
		Vuelo vuelo = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_VUELO_BYID"));
			ps.setLong(1, id);
			rs = ps.executeQuery();

			while (rs.next()){
				vuelo = new Vuelo();
				vuelo.setId(rs.getLong("id"));
				vuelo.setOrigen(rs.getString("origen"));
				vuelo.setDestino(rs.getString("destino"));
				vuelo.setFechaSalida(rs.getTimestamp("fecha_salida"));
				vuelo.setDuracion(rs.getTime("duracion"));
				vuelo.setPlazas(rs.getInt("plazas"));
				vuelo.setPrecio(rs.getDouble("precio"));
				vuelo.setImagenUrl(rs.getString("imagen_url"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(rs,ps,con);
		}
		
		return vuelo;
	}
	
	@Override
	public void actualizarPlazasDisponibles(Long id, int plazas) 
			throws Exception {
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = Jdbc.getConnection();

			ps = con.prepareStatement(Conf.get("SQL_VUELO_UPDATE"));
			ps.setInt(1, plazas);
			ps.setLong(2, id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			Jdbc.close(ps,con);
		}
	}

}
