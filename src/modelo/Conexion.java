package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {

	public Conexion() {
		
		String datos[] = null;
		String ruta ="jdbc:mysql://localhost:";
		String pass = "";
		
		try {
			datos = getRutaConexion();
			
			ruta = ruta + datos[1].trim() + "/" + datos[0].trim();
			String user = datos[2].trim();
			
			if(datos[3] != null) pass = datos[3].trim();
			
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(ruta,user, pass);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		
	}
	
	private String[] getRutaConexion() {
		
		String datos[] = new String[4];
		
		File f = new File("");
		
		File config = new File(f.getAbsolutePath(), "Recetario.cfg");
		
		try {
			FileReader entrada = new FileReader(config);
			BufferedReader buffer = new BufferedReader(entrada);
			
			String linea = buffer.readLine();
			
			
			while(linea != null) {
				if(!linea.startsWith("#")) {
					if(linea.startsWith("Nombre_base_de_datos=")) datos[0] = linea.replace("Nombre_base_de_datos=","");
					if(linea.startsWith("Puerto=")) datos[1] = linea.replace("Puerto=","");
					if(linea.startsWith("User=")) datos[2] = linea.replace("User=","");
					if(linea.startsWith("Password=")) datos[3] = linea.replace("Password=","");
				}
				
				linea = buffer.readLine();
			}
			
			entrada.close();
		} catch (IOException  e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} 
		
		
		return datos;
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
