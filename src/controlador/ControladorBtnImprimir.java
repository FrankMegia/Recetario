package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import modelo.Receta;
import vista.Impresion;
import vista.VentanaPrincipal;
import vista.VistaPreviaImpresion;

public class ControladorBtnImprimir implements ActionListener{

	public ControladorBtnImprimir(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Receta r = ventana.getReceta();
		
		VistaPreviaImpresion vpi = new VistaPreviaImpresion(r);
		vpi.setVisible(true);
	}

	private VentanaPrincipal ventana;

}
