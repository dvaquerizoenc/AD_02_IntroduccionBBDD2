package interfaz;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;
import modelo.persistencia.DaoCoche;

public class InterfazCoche {
	public void interfaz() {
		Scanner sc = new Scanner(System.in);
		GestorCoche gc = new GestorCoche();
		
		
		System.out.println("##########");
		System.out.println("## MENÚ ##");
		System.out.println("##########");

		
		int eleccion = menu();
		
		switch(eleccion) {
			case 1: if(gc.altaCoche(ingresarCoche())) System.out.println("Coche añadido correctamente");
					else System.out.println("Ha habido un problema al subir el coche");
				break;
			
			case 2: System.out.println("Ingresa la ID del coche que desea borrar");
					if(gc.eliminarCocheID(Integer.parseInt(sc.nextLine()))) System.out.println("Se ha dado de baja el coche");
					else System.out.println("Se ha producido un error al dar de alta el coche");
				break;
				
			case 3: modificarCoche();
				break;
				
			case 4: listarCoche(gc.buscarCocheID(Integer.parseInt(sc.nextLine())));;
				break;
				
			case 5: listarCocheMarca();
				break;
				
			case 6: listarCoches();
				break;
		}
		
		
	}
	
	int menu() {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		
		System.out.println("1- Dar de alta coche");
		System.out.println("2- Dar de baja coche por id");
		System.out.println("3- Modificar coche por id");
		System.out.println("4- Buscar coche por id");
		System.out.println("5- Buscar coches por marca");
		System.out.println("6- Listar todos los coches");
		System.out.println("0- Salir de la aplicación");
		
		do {
			try {
				num = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("ERROR -> Solo puedes introducir números");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(num<0 || num>6);
		
		return num;
	}
	
	Coche ingresarCoche() {
		Coche coche = new Coche();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--> Introduce una marca: ");
		coche.setMarca(sc.nextLine());
		
		System.out.println("--> Introduce el modelo: ");
		coche.setModelo(sc.nextLine());
		
		System.out.println("--> Introduce su tipo de motor [Gasolina, Diesel, Electrico]: ");
		coche.setTipoMotor(sc.nextLine());
		
		System.out.println("--> Introduce los KM recorridos por el coche: ");
		coche.setKm(Double.parseDouble(sc.nextLine()));
		
		return coche;
	}
	
	void modificarCoche() {
		Scanner sc = new Scanner(System.in);
		Coche coche = new Coche();
		boolean seguir = false;
		
		System.out.println("-> ¿Que id tiene el coche que desea modificar?");
		int id = Integer.parseInt(sc.nextLine());
		
		do {
			System.out.println("-> ¿Qué desea modificar del coche con id: "+id+"?");
			System.out.println("1. Marca");
			System.out.println("2. Modelo");
			System.out.println("3. Tipo motor");
			System.out.println("4. KMs");
			System.out.println("0. Salir");
			int respuesta = Integer.parseInt(sc.nextLine());
			
			switch(respuesta) {
				case 0: interfaz();
				break;
				case 1: System.out.println("--> Introduce la nueva marca");
					coche.setMarca(sc.nextLine());
					break;
				case 2: System.out.println("--> Introduce el nuevo modelo");
					coche.setModelo(sc.nextLine());
					break;
				case 3: System.out.println("--> Introduce el nuevo motor");
					coche.setTipoMotor(sc.nextLine());
					break;
				case 4: System.out.println("--> Introduce la nueva cantidad de KMs");
					coche.setKm(Double.parseDouble(sc.nextLine()));
					break;
			}
			
			System.out.println("-> ¿Quieres modificar algo mas? [Si, No]");
			seguir = sc.nextLine().equalsIgnoreCase("si")?true:false;
		} while(seguir);
		GestorCoche gc = new GestorCoche();
		boolean modificado = gc.modificarCocheID(id, coche);
		if (modificado) System.out.println("Se ha modificado correctamente");
		else System.out.println("Se ha producido un error a al modificar el coche");
	}
	
	void listarCocheMarca() {
		Scanner sc = new Scanner(System.in);
		String marca = "";
		GestorCoche gc = new GestorCoche();
		ArrayList<Coche> lista = new ArrayList<Coche>();
		
		System.out.println("--> ¿Que marca quieres listar?");
		marca = sc.nextLine();
		lista= gc.buscarCocheMarca(marca);
		for(Coche c:lista) {
			listarCoche(c);
			System.out.println("--------------------------------------");
		}
	}
	
	void listarCoches() {
		GestorCoche gc = new GestorCoche();
		ArrayList<Coche> lista = new ArrayList<Coche>();

		lista = gc.buscarCoches();
		for(Coche c:lista) {
			listarCoche(c);
			System.out.println("--------------------------------------");
		}
	}
	
	void listarCoche(Coche coche) {
		System.out.println("Marca: "+coche.getMarca());
		System.out.println("Modelo: "+coche.getModelo());
		System.out.println("Tipo motor: "+coche.getTipoMotor());
		System.out.println("Kms: "+coche.getKm());
	}
	
}
