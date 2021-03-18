package modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ListaRenderer extends DefaultListCellRenderer{
	
	final JPanel p = new JPanel(new BorderLayout());
	final JTextArea ta = new JTextArea();
	final JLabel img = new JLabel();
	
	public ListaRenderer() {
		
		p.setBorder(BorderFactory.createEtchedBorder());
		p.add(ta, BorderLayout.CENTER);
		
		img.setSize(80, 80);
		img.setBorder(BorderFactory.createEtchedBorder());
		p.add(img, BorderLayout.WEST);

		
	}

	@Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean hasFocus)
    {
		Receta tmp = (Receta) value;
		img.setIcon(null);
		img.setText("");
		ta.setText("");
		ta.append("\nReceta: " + tmp.getNombre());
		ta.append("\nCategoría: " + tmp.getCategoria() + "\n");
		
		if(tmp.getFoto() == null) {
			img.setVerticalAlignment(CENTER);
			img.setText("NO IMAGEN");
		}else {
    		try {
    			byte[] data = tmp.getFoto().getBytes(1, (int)tmp.getFoto().length());
    			BufferedImage bufImg = null;
				bufImg = ImageIO.read(new ByteArrayInputStream(data));
				ImageIcon icon = new ImageIcon(bufImg.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				img.setIcon(icon);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    		
		}
		
		if(isSelected) ta.setBackground(Color.LIGHT_GRAY);
		else ta.setBackground(Color.WHITE);
		
		
		return p;
		
    }
}
