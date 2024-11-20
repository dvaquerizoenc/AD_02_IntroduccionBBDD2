package interfaz;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;

public class InterfazCoche {
	public void interfaz() {
		Scanner sc = new Scanner(System.in);
		Coche coche = new Coche();
		GestorCoche gc = new GestorCoche();
		boolean guardado=false;
		
		System.out.println("#########################");
		System.out.println("## Inicio del programa ##");
		System.out.println("#########################");

		System.out.println("--> Introduce una marca: ");
		coche.setMarca(sc.nextLine());
		
		System.out.println("--> Introduce el modelo: ");
		coche.setModelo(sc.nextLine());
		
		System.out.println("--> Introduce su tipo de motor [Gasolina, Diesel, Electrico]: ");
		coche.setTipoMotor(sc.nextLine());
		
		System.out.println("--> Introduce los KM recorridos por el coche: ");
		coche.setKm(Double.parseDouble(sc.nextLine()));
		
		guardado = gc.guardarCoche(coche);
		
		if (guardado)
			System.out.println("---> Vehiculo guardado");
		
			System.out.println("--> Desea guardar otro vehiculo?");
			if(sc.nextLine().equals("si")) {
				System.out.println("--> Introduce una marca: ");
				coche.setMarca(sc.nextLine());
				
				System.out.println("--> Introduce el modelo: ");
				coche.setModelo(sc.nextLine());
				
				System.out.println("--> Introduce su tipo de motor [Gasolina, Diesel, Electrico]: ");
				coche.setTipoMotor(sc.nextLine());
				
				System.out.println("--> Introduce los KM recorridos por el coche: ");
				coche.setKm(Double.parseDouble(sc.nextLine()));
				
				guardado = gc.guardarCoche(coche);
				
				System.out.println("---> Vehiculo guardado");
			}
		else 
			System.out.println("---> Error al guardar el vehiculo");
	}
	
	void menu() {
		System.out.println("1- Dar de alta coche");
		System.out.println("2- Dar de baja coche por id");
		System.out.println("3- Modificar coche por id");
		System.out.println("4- Buscar coche por id");
		System.out.println("5- Buscar coches por marca");
		System.out.println("6- Listar todos los coches");
		System.out.println("0- Salir de la aplicación");
	}
}
