package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import modelo.EjecutarConsultas;
import vista.VentanaPrincipal;

public class ControladorBtnAgregar implements ActionListener{

	public ControladorBtnAgregar(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String nombre = ventana.getTf_receta().getText();
		String categ = ventana.getComboCat().getSelectedItem().toString();
		String ingred = ventana.getTa_ingred().getText();
		String prepa = ventana.getTa_prepa().getText();
		String ruta = ventana.getRuta().getText();
		boolean correcto= false;
		
		if(nombre.equals("") || categ.equals("")) {
			JOptionPane.showMessageDialog(ventana, "Error: Receta o categoría en blanco.", "Error Añadiendo...", JOptionPane.ERROR_MESSAGE);
			ventana.limpiar(false, false, false);
			return;
		}
		
		obj = new EjecutarConsultas();
		correcto = obj.agregarBBDD(nombre, categ, ingred, prepa, ruta);
		
		if(correcto) JOptionPane.showMessageDialog(ventana, "Receta añadida correctamente.", "Añadiendo...", JOptionPane.INFORMATION_MESSAGE);
		else JOptionPane.showMessageDialog(ventana, "Algo ha ido mal al añadir receta.", "Añadiendo...", JOptionPane.ERROR_MESSAGE);
		
		ventana.limpiar(true, false, false);
		
	}

	private VentanaPrincipal ventana;
	private EjecutarConsultas obj;
	
}
