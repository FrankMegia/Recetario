package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class EjecutarConsultas {

	public EjecutarConsultas()
	{
		c = new Conexion();
		conexion = c.getConexion();
	}
	
	public ResultSet buscarBBDD(String consulta, String criterio) {
		
		ResultSet rs = null;
		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setString(1, "%"+criterio+"%");
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al consultar a BBDD", "Error: Buscar BBDD", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public boolean agregarBBDD(String nombre, String cat, String ingred, String prepa, String ruta) {
		
		boolean correcto = false;
		
		try {
			PreparedStatement ps = conexion.prepareStatement(agregar);
			ps.setString(1, nombre);
			ps.setString(2, cat);
			ps.setString(3, ingred);
			ps.setString(4, prepa);
			
			if(ruta == null || ruta.equals(""))
			{
				ps.setBinaryStream(5, null);
			}
			else {
				try {
					File file = new File(ruta);
					FileInputStream archivo = new FileInputStream(file);
					ps.setBinaryStream(5, archivo);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Error al leer la foto!", "Error foto", JOptionPane.ERROR_MESSAGE);
					ps.setBinaryStream(5, null);
					e.printStackTrace();
				}
			}
			
			int i = ps.executeUpdate();
			
			if(i!=0) correcto = true;
			else correcto = false;
			
			
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "Error al insertar receta!->"+e.getMessage(), "Error agregando...", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			correcto = false;
		}
		
		c.cerrarConexion();
		return correcto;
	}
	
	public boolean modificarBBDD(int id, String nombreReceta, String categoria, String ingre, String prepa, String ruta) {
		
		boolean resultado= false;
		
		c = new Conexion();
		conexion = c.getConexion();

		try {
			PreparedStatement st = conexion.prepareStatement(modif);
			
			st.setString(1, nombreReceta);
			st.setString(2, categoria);
			st.setString(3, ingre);
			st.setString(4, prepa);
			st.setInt(6, id);
			
			if(ruta == null || ruta.equals("")) st.setBinaryStream(5, null);
			else {
				File file = new File(ruta);
				FileInputStream archivo = new FileInputStream(file);
				st.setBinaryStream(5, archivo);
			}
			
			int r = st.executeUpdate();
			
			if(r!=0) resultado = true;
			else resultado = false;
			
		} catch (SQLException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Modificando Receta...", JOptionPane.ERROR_MESSAGE);
			resultado = false;
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public boolean modificarBBDD(int id, String nombreReceta, String categoria, String ingre, String prepa, Receta r) {
		
		boolean resultado= false;
		
		c = new Conexion();
		conexion = c.getConexion();
		
		try {
			PreparedStatement st = conexion.prepareStatement(modif);
			
			st.setString(1, nombreReceta);
			st.setString(2, categoria);
			st.setString(3, ingre);
			st.setString(4, prepa);
			st.setInt(6, id);
			
			if(r.getFoto() == null) st.setNull(5, Types.NULL);
			else st.setBlob(5, r.getFoto());
			
			int result = st.executeUpdate();
			
			if(result != 0) resultado = true;
			else resultado = false;
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error Modificando Receta...", JOptionPane.ERROR_MESSAGE);
			resultado = false;
			e.printStackTrace();
		}
		
		c.cerrarConexion();
		return resultado;
	}
	
	public boolean borrarBBDD(int id) {
		boolean resultado = false;
		
		c = new Conexion();
		conexion = c.getConexion();
		
		PreparedStatement st;
		try {
			st = conexion.prepareStatement(borrar);
			st.setInt(1, id);
		
			int r = st.executeUpdate();
			
			if(r!=0) resultado = true;
			else resultado = false;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Borrando...", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			resultado = false;
		}
		
		
		return resultado;
	}
	
	private Conexion c;
	private Connection conexion;
	private final String agregar = "INSERT INTO RECETAS(RECETA, CATEGORIA, INGREDIENTES, PREPARACION, FOTO) VALUES (?,?,?,?,?)";
	private final String modif = "UPDATE RECETAS SET RECETA=?, CATEGORIA=?, INGREDIENTES=?, PREPARACION=?, FOTO=? WHERE ID=?";
	private final String borrar = "DELETE FROM RECETAS WHERE ID=?";
	
}
