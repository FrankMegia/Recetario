package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import modelo.EjecutarConsultas;
import modelo.Receta;
import vista.VentanaPrincipal;

public class ControladorBtnBuscar implements ActionListener{
	
	public ControladorBtnBuscar(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String receta = "";
		String categ = "";
		boolean vacio = true;
		rs = null;
		
		receta = ventana.getTf_receta().getText();
		categ = ventana.getTf_categ().getText();
		
		if(receta.equals("") && categ.equals("")) {
			JOptionPane.showMessageDialog(ventana, "Error: No hay criterios de búsqueda!", "Error: Buscar", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		EjecutarConsultas obj = new EjecutarConsultas();
		
		if(!receta.equals("")) rs = obj.buscarBBDD(buscarReceta, receta);
		else rs = obj.buscarBBDD(buscarCateg, categ);
		
		ArrayList<Receta> lista = new ArrayList<Receta>();
		
		if(rs != null) {
			
			try {
				
				while(rs.next()) {
					vacio = false;
					Receta rec = new Receta(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getBlob(6));
					lista.add(rec);
					
				}
				
				if(vacio) {
					JOptionPane.showMessageDialog(ventana, "No se han encontrado recetas con esos criterios de búsqueda", 
							"Aviso: Buscando...", JOptionPane.WARNING_MESSAGE);
					ventana.limpiar(true, false, false);
					
					return;
				}
				
				ventana.getListaRecetas().setModel(new AbstractListModel<Receta>() {

					@Override
					public Receta getElementAt(int index) {
						
						return lista.get(index);
					}

					@Override
					public int getSize() {
						
						return lista.size();
					}
				});
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			ventana.limpiar(false, true, false);
		}
		
		
	}
	
	private VentanaPrincipal ventana;
	private final String buscarReceta = "SELECT * FROM RECETAS WHERE RECETA LIKE ?";
	private final String buscarCateg = "SELECT * FROM RECETAS WHERE CATEGORIA LIKE ?";
	private ResultSet rs;
}
