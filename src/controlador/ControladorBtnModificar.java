package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.EjecutarConsultas;
import modelo.Receta;
import vista.VentanaPrincipal;

public class ControladorBtnModificar implements ActionListener{

	public ControladorBtnModificar(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nombreRec = ventana.getTf_receta().getText();
		String categoria = ventana.getTf_categ().getText();
		String ingredientes = ventana.getTa_ingred().getText();
		String prepa = ventana.getTa_prepa().getText();
		String ruta = ventana.getRuta().getText();
		Receta receta = ventana.getReceta();
		int ID = receta.getId();
		boolean correcto;
		
		EjecutarConsultas obj = new EjecutarConsultas();
		
		if(!ruta.equals("")) correcto = obj.modificarBBDD(ID, nombreRec, categoria, ingredientes, prepa, ruta);
		else correcto = obj.modificarBBDD(ID, nombreRec, categoria, ingredientes, prepa, receta);
		
		ventana.limpiar(true, false, false);
		
		if(correcto) JOptionPane.showMessageDialog(ventana, "Info: Receta modificada con éxito.", "Modificando...", JOptionPane.INFORMATION_MESSAGE);
		else JOptionPane.showMessageDialog(ventana, "Error!: La receta no ha sido modificada.", "Modificando...", JOptionPane.ERROR_MESSAGE);
		
	}

	
	private VentanaPrincipal ventana;
}
