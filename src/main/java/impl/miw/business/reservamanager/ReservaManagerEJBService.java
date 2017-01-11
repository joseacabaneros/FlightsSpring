package impl.miw.business.reservamanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.miw.business.ReservaManagerService;
import com.miw.model.Reserva;
import com.miw.model.Usuario;
import com.miw.model.Vuelo;

public class ReservaManagerEJBService implements ReservaManagerService{
	
	@Autowired
	private ReservaManager reservaManager;

	public void setReservaManager(ReservaManager reservaManager) {
		this.reservaManager = reservaManager;
	}

	@Override
	public Reserva addReserva(Reserva reserva, Usuario usuario) 
			throws Exception {
		System.out.println("Confirmando y anadiendo reserva (y usuario)");
		return reservaManager.addReserva(reserva, usuario);
	}

	@Override
	public Reserva getReservaByCode(String code) throws Exception {
		System.out.println("Obteniendo reserva por su codigo");
		return reservaManager.getReservaByCode(code);
	}

	@Override
	public Usuario getUsuarioByIdentificacion(String ident) throws Exception {
		System.out.println("Obteniendo usuario por su identificacion");
		return reservaManager.getUsuarioByIdentificacion(ident);
	}

	@Override
	public int cancerlarReserva(String codigoReserva, String email)
			throws Exception {
		System.out.println("Intentando eliminar reserva");
		return reservaManager.cancerlarReserva(codigoReserva, email);
	}

	@Override
	public List<String> getDestinoPopulares() throws Exception {
		System.out.println("Obteniendo destinos populares");
		return reservaManager.getDestinoPopulares();
	}

	@Override
	public List<Reserva> getReservasByidentificacion(String ident)
			throws Exception {
		System.out.println("Obteniendo listado de reservas");
		return reservaManager.getReservasByidentificacion(ident);
	}

	@Override
	public List<Vuelo> getVuelosByCodigoReserva(String codReserva)
			throws Exception {
		System.out.println("Obteniendo vuelos de reserva");
		return reservaManager.getVuelosByCodigoReserva(codReserva);
	}
	
}
