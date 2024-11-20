package modelo.negocio;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCoche;

public class GestorCoche {
	public boolean guardarCoche(Coche coche) {
		boolean respuesta = false;
		
		if(!coche.getMarca().isBlank() && !coche.getModelo().isBlank() && !coche.getTipoMotor().toString().isBlank()) {
			DaoCoche daoCoche = new DaoCoche();
			daoCoche.persistirCoche(coche);
			respuesta = true;
		}
		
		return respuesta;
	}
}
