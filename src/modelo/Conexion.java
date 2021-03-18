package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {

	public Conexion() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3308/recetario", "root", "");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		
	}
	
	public Connection getConexion() {
		return conexion;
	}
	
	public void cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	private Connection conexion;
	
}
