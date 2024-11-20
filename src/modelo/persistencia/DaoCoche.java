package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
}
