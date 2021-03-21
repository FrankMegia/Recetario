package vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Receta;

public class VistaPreviaImpresion extends JFrame{

	public VistaPreviaImpresion(Receta r) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setSize(600, 800);
		setLocationRelativeTo(null);
		setTitle("Vista Previa Impresión");
		
		ImageIcon icon = null;
		
		if(r.getFoto() != null) {
			try {
				byte[] data = r.getFoto().getBytes(1, (int)r.getFoto().length());
	    		BufferedImage img = null;
	    		
	    		img = ImageIO.read(new ByteArrayInputStream(data));
	    		icon = new ImageIcon(img.getScaledInstance(195, 143, Image.SCALE_DEFAULT));
	    		
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}
		
		Impresion impresion = new Impresion(r.getNombre(), r.getCategoria(), r.getPreparacion(), r.getIngredientes(),icon);
		impresion.setVisible(true);
		add(impresion);
		
		JButton imprime = new JButton("Imprimir");
		imprime.setBounds(480, 720, 90, 23);
		imprime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(impresion);
				if(job.printDialog()) {
					try {
						job.print();
					} catch (PrinterException e2) {
						
						e2.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(getParent(), "La impresión de la receta se canceló.", "Imprimiendo...", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		add(imprime);
		
	}
}
