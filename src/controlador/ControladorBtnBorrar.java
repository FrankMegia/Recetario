package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.EjecutarConsultas;
import modelo.Receta;
import vista.VentanaPrincipal;

public class ControladorBtnBorrar implements ActionListener{

	public ControladorBtnBorrar(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		boolean correcto = false;
		Receta rec = ventana.getReceta();
		
		EjecutarConsultas obj = new EjecutarConsultas();
		correcto = obj.borrarBBDD(rec.getId());
		
		if(correcto) 
			JOptionPane.showMessageDialog(ventana, "La receta: " + rec.getNombre() + "ha sido eliminada.", "Borrando...", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(ventana, "La receta: " + rec.getNombre() + "no ha sido eliminada.!", "Borrando...", JOptionPane.ERROR_MESSAGE);
	
		ventana.limpiar(true, false, false);
		
	}
	
	private VentanaPrincipal ventana;

}
