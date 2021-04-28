package vista;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JTextArea;
import java.awt.Color;

public class Impresion extends JPanel implements Printable{


	/**
	 * Create the panel.
	 */
	public Impresion(String nombre, String cat, String prepa, String ingred, ImageIcon icon) {
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(600,700);
		
		et_foto = new JLabel("");
		et_foto.setBounds(410, 99, 150, 150);
		et_foto.setIcon(icon);
		add(et_foto);
		
		JLabel et_ingred = new JLabel("Ingredientes");
		et_ingred.setFont(new Font("Segoe Script", Font.BOLD, 14));
		et_ingred.setBounds(410, 260, 111, 24);
		add(et_ingred);
		
		JLabel et_prepa = new JLabel("Preparaci\u00F3n");
		et_prepa.setFont(new Font("Segoe Script", Font.BOLD, 14));
		et_prepa.setBounds(10, 93, 111, 24);
		add(et_prepa);
		
		ta_ingred = new JTextArea();
		ta_ingred.setWrapStyleWord(true);
		ta_ingred.setLineWrap(true);
		ta_ingred.setText(ingred);
		ta_ingred.setBounds(368, 295, 211, 358);
		ta_ingred.setEditable(false);
		add(ta_ingred);
		
		ta_prepa = new JTextArea();
		ta_prepa.setText(prepa);
		ta_prepa.setLineWrap(true);
		ta_prepa.setWrapStyleWord(true);
		ta_prepa.setEditable(false);
		ta_prepa.setBounds(10, 128, 350, 525);
		add(ta_prepa);
		
		et_categ = new JLabel(cat);
		et_categ.setFont(new Font("Segoe Script", Font.BOLD, 14));
		et_categ.setBounds(467, 11, 186, 24);
		add(et_categ);
		
		ta_receta = new JTextArea();
		ta_receta.setText(nombre);
		ta_receta.setFont(new Font("Segoe Script", Font.BOLD, 24));
		ta_receta.setWrapStyleWord(true);
		ta_receta.setEditable(false);
		ta_receta.setLineWrap(true);
		ta_receta.setBounds(10, 36, 654, 52);
		add(ta_receta);

		setVisible(true);
	}

	

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if(pageIndex == 0) {
			Graphics2D g2d = (Graphics2D)graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			g2d.scale(pageFormat.getImageableWidth()/this.getWidth(), pageFormat.getImageableHeight() / this.getHeight());
			this.printAll(graphics);
			

			return PAGE_EXISTS;
		}else {
			return NO_SUCH_PAGE;
		}
	}
	private JLabel et_foto;
	private JTextArea ta_ingred;
	private JTextArea ta_prepa;
	private JLabel et_categ;
	private JTextArea ta_receta;
}
