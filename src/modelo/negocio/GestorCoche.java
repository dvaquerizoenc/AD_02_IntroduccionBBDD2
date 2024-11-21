package modelo.negocio;

import java.util.ArrayList;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCoche;

public class GestorCoche {

	DaoCoche daoCoche = new DaoCoche();
	
	public boolean altaCoche(Coche coche) {
		boolean subido = false;
		
		if(!coche.getMarca().isBlank() && !coche.getModelo().isBlank() && !coche.getTipoMotor().toString().isBlank()) {
			daoCoche.persistirCoche(coche);
			subido = true;
		}
		
		return subido;
	}
	
	public boolean eliminarCocheID(int id) {
		return daoCoche.eliminarByID(id);
	}
	
	public boolean modificarCocheID(int id, Coche coche) {
		Coche cocheAntiguo = buscarCocheID(id);
		System.out.println(coche.getModelo());
		if(!coche.getMarca().isBlank()) {
			cocheAntiguo.setMarca(coche.getMarca());
		}
		if(!coche.getModelo().isBlank()) {
			cocheAntiguo.setModelo(coche.getModelo());
		}
		if(!coche.getTipoMotor().isBlank()) {
			cocheAntiguo.setTipoMotor(coche.getTipoMotor());
		}
		if(coche.getKm()!=0) {
			cocheAntiguo.setKm(coche.getKm());
		}
		
		return daoCoche.modificarByID(id, cocheAntiguo);
	}
	
	public ArrayList<Coche> buscarCocheMarca(String marca){
		return daoCoche.buscarByMarca(marca);
	}
	
	public ArrayList<Coche> buscarCoches(){
		return daoCoche.buscarTodos();
	}
	
	public Coche buscarCocheID(int id) {
		return daoCoche.buscarByID(id);
	}
	
}
