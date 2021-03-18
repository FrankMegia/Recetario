package controlador;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import modelo.Receta;
import vista.VentanaPrincipal;

public class ControladorFotoZoom extends MouseAdapter{
	
	public ControladorFotoZoom(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	public void mouseClicked(MouseEvent e) {
		
		Receta r = ventana.getReceta();
		
		if(r != null && r.getFoto() != null) {
			try {
				byte[] data = r.getFoto().getBytes(1, (int)r.getFoto().length());
	    		BufferedImage img = null;
	    		
	    		img = ImageIO.read(new ByteArrayInputStream(data));
	    		int alto = img.getHeight();
	    		int ancho = img.getWidth();
	    		ImageIcon icon = new ImageIcon(img);
	    		
	    		JLabel zoom = new JLabel(icon);
	    		zoom.setSize(ancho, alto);
	    		zoom.setIcon(icon);
	    		
	    		JOptionPane.showMessageDialog(ventana, zoom, r.getNombre() + " - " + r.getCategoria(), JOptionPane.PLAIN_MESSAGE);
	    		
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else {
			try {
				File f = new File(ventana.getRuta().getText());
				BufferedImage img = ImageIO.read(f);
				int alto = img.getHeight();
				int ancho = img.getWidth();
				ImageIcon icon = new ImageIcon(img.getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
				
				JLabel zoom = new JLabel(icon);
	    			    		
	    		JOptionPane.showMessageDialog(ventana, zoom, ventana.getRuta().getText(), JOptionPane.PLAIN_MESSAGE);
				
			} catch (IOException e2) {
				
				e2.printStackTrace();
			}
		}
	}
	
	private VentanaPrincipal ventana;
}
