package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VentanaPrincipal;

public class ControladorBtnOtraAcc implements ActionListener{

	public ControladorBtnOtraAcc(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		ventana.limpiar(true, false, false);
	}

	private VentanaPrincipal ventana;
}
