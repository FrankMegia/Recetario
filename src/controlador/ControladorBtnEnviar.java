package controlador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.*;


import modelo.Receta;
import vista.VentanaPrincipal;

public class ControladorBtnEnviar implements ActionListener{
	
	public ControladorBtnEnviar(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Receta r = ventana.getListaRecetas().getSelectedValue();
		
		if(r == null) {
			JOptionPane.showMessageDialog(ventana, "Atención: Debe seleccionar una receta para operar con ella.", "Atención...", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ventana.limpiar(false, true, true);
		
		ventana.getTf_receta().setText(r.getNombre());
		ventana.getComboCat().setSelectedItem(r.getCategoria());
		ventana.getTa_ingred().setText(r.getIngredientes());
		ventana.getTa_prepa().setText(r.getPreparacion());
		
		if(r.getFoto() == null)
		{
			ventana.getEt_foto().setIcon(null);
			ventana.getEt_foto().setText("NO IMAGEN");
			ventana.getEt_foto().setForeground(Color.WHITE);
			ventana.getEt_foto().setHorizontalAlignment(SwingConstants.CENTER);
		}
		else {
			
			try {
			byte[] data = r.getFoto().getBytes(1, (int)r.getFoto().length());
    		BufferedImage img = null;
    		
    		img = ImageIO.read(new ByteArrayInputStream(data));
    		ImageIcon icon = new ImageIcon(img.getScaledInstance(195, 143, Image.SCALE_DEFAULT));
    		ventana.getEt_foto().setIcon(icon);
    		ventana.getEt_foto().setToolTipText("Click para zoom");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		ventana.setReceta(r);
		
	}

	
	private VentanaPrincipal ventana;
}
