package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

import modelo.entidad.Coche;

public class DaoCoche {
	public boolean persistirCoche(Coche coche){
		// Datos de la base de datos
		String url = "jdbc:mysql://127.0.0.1:3306/bbdd";
		String user = "root";
		String pass = "";
		
		boolean persistido = false;
		
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			
			String query = "INSERT INTO coches (marca, modelo, tipoMotor, km) VALUES (?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setString(3, coche.getTipoMotor().toString());
			ps.setDouble(4, coche.getKm());
			
			int filas = ps.executeUpdate();
			
			if (filas != 0) 
				persistido = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return persistido;
	}
	
	public boolean eliminarByID(int id) {
		boolean eliminado = false;
		
		String url = "jdbc:mysql://127.0.0.1:3306/bbdd";
		String user = "root";
		String pass = "";
		
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			String query = "DELETE FROM coches WHERE id=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
			int rows = ps.executeUpdate();
			if (rows == 1) eliminado = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eliminado;
	}
	
	public boolean modificarByID(int id, Coche coche) {
		boolean modificado = false;
		
		String url = "jdbc:mysql://127.00.0.1:3306/bbdd";
		String user = "root";
		String pass = "";
		
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			String query = "UPDATE coches SET marca=?, modelo=?, tipoMotor=?, km=? WHERE id=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, coche.getMarca());
			ps.setString(2, coche.getModelo());
			ps.setString(3, coche.getTipoMotor());
			ps.setDouble(4, coche.getKm());
			ps.setInt(5, id);
			
			int rows = ps.executeUpdate();
			if (rows == 1) modificado = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return modificado;
	}
	
	public ArrayList<Coche> buscarByMarca(String marca) {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		String url = "jdbc:mysql://127.0.0.1:3306/bbdd";
		String user = "root";
		String pass = "";
		
		
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			String query = "SELECT * FROM coches WHERE marca=?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, marca);
			
			ResultSet filas = ps.executeQuery();
			while(filas.next()) {
				Coche coche = new Coche();
				coche.setMarca(filas.getString(2));
				coche.setModelo(filas.getString(3));
				coche.setTipoMotor(filas.getString(4));
				coche.setKm(filas.getDouble(5));
				listaCoches.add(coche);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return listaCoches;
	}
	
	public ArrayList<Coche> buscarTodos() {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		String url = "jdbc:mysql://127.0.0.1:3306/bbdd";
		String user = "root";
		String pass = "";
		
		
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			String query = "SELECT * FROM coches";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet filas = ps.executeQuery();
			while(filas.next()) {
				Coche coche = new Coche();
				coche.setMarca(filas.getString(2));
				coche.setModelo(filas.getString(3));
				coche.setTipoMotor(filas.getString(4));
				coche.setKm(filas.getDouble(5));
				listaCoches.add(coche);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return listaCoches;
	}
	
	public Coche buscarByID(int id) {
		String url = "jdbc:mysql://127.0.0.1:3306/bbdd";
		String user = "root";
		String pass = "";
		
		Coche coche = new Coche();
		
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			String query = "SELECT * FROM coches WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet filas = ps.executeQuery();
			if(filas.next()) {
				coche.setMarca(filas.getString(2));
				coche.setModelo(filas.getString(3));
				coche.setTipoMotor(filas.getString(4));
				coche.setKm(filas.getDouble(5));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return coche;
	}
}
