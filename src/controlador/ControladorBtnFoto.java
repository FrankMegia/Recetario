package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import vista.VentanaPrincipal;

public class ControladorBtnFoto implements ActionListener{

	public ControladorBtnFoto(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		File ruta = null;
		
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagenes JPG y PNG", "jpg","png");
		fc.setFileFilter(filtro);
		
		int v = fc.showOpenDialog(ventana);
		
		if(v == JFileChooser.APPROVE_OPTION) {
			ruta = fc.getSelectedFile();
			ventana.getRuta().setText(ruta.getPath());
		}else
			return;
		
		try {
			BufferedImage img = ImageIO.read(ruta);
			ImageIcon icon = new ImageIcon(img.getScaledInstance(195, 143, Image.SCALE_DEFAULT));
			ventana.getEt_foto().setIcon(icon);
			ventana.getEt_foto().setToolTipText("Click para hacer zoom");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private VentanaPrincipal ventana;

}
