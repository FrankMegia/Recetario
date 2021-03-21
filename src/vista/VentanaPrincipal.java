package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controlador.*;
import modelo.ListaRenderer;
import modelo.Receta;

public class VentanaPrincipal extends JFrame{

	public VentanaPrincipal() {
		
		receta = null;
		
		setTitle("Recetario de Gema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 533);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel et_receta = new JLabel("Receta:");
		et_receta.setForeground(Color.WHITE);
		et_receta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		et_receta.setBounds(42, 49, 52, 14);
		contentPane.add(et_receta);
		
		tf_receta = new JTextField();
		tf_receta.setBounds(98, 48, 272, 20);
		contentPane.add(tf_receta);
		tf_receta.setColumns(10);
		
		JLabel et_categ = new JLabel("Categor\u00EDa:");
		et_categ.setForeground(Color.WHITE);
		et_categ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		et_categ.setBounds(42, 102, 78, 19);
		contentPane.add(et_categ);
		
		tf_categ = new JTextField();
		tf_categ.setBounds(121, 101, 249, 20);
		contentPane.add(tf_categ);
		tf_categ.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(42, 174, 328, 218);
		contentPane.add(tabbedPane);
		
		ta_ingred = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(ta_ingred);
		tabbedPane.addTab("Ingredientes", null, scrollPane, null);
		
		ta_prepa = new JTextArea();
		JScrollPane scrollPane1 = new JScrollPane(ta_prepa);
		ta_prepa.setLineWrap(true);
		ta_prepa.setWrapStyleWord(true);
		tabbedPane.addTab("Preparación", null, scrollPane1, null);
		
		btnOtraAcc = new JButton("Otra Acción");
		btnOtraAcc.setBounds(42,426, 140,23);
		btnOtraAcc.setVisible(false);
		btnOtraAcc.addActionListener(new ControladorBtnOtraAcc(this));
		contentPane.add(btnOtraAcc);
		
		btnAdd = new JButton("A\u00F1adir");
		btnAdd.setBounds(42, 426, 69, 23);
		btnAdd.setMargin(new Insets(0,-4,0,-4));
		btnAdd.addActionListener(new ControladorBtnAgregar(this));
		contentPane.add(btnAdd);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(121, 426, 69, 23);
		btnBuscar.setMargin(new Insets(0,-4,0,-4));
		btnBuscar.addActionListener(new ControladorBtnBuscar(this));
		contentPane.add(btnBuscar);
		
		btnModif = new JButton("Modificar");
		btnModif.setBounds(196, 426, 78, 23);
		btnModif.setMargin(new Insets(0,-4,0,-4));
		btnModif.setEnabled(false);
		btnModif.addActionListener(new ControladorBtnModificar(this));
		contentPane.add(btnModif);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(281, 426, 89, 23);
		btnBorrar.setEnabled(false);
		btnBorrar.addActionListener(new ControladorBtnBorrar(this));
		contentPane.add(btnBorrar);
		
		et_foto = new JLabel("");
		et_foto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		et_foto.setBounds(400, 11, 195, 143);
		et_foto.addMouseListener(new ControladorFotoZoom(this));
		contentPane.add(et_foto);
		
		JButton btnFoto = new JButton("Foto");
		btnFoto.setBounds(634, 76, 89, 23);
		btnFoto.addActionListener(new ControladorBtnFoto(this));
		contentPane.add(btnFoto);
		
		listaRecetas = new JList<Receta>();
		listaRecetas.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		listaRecetas.setCellRenderer(new ListaRenderer());
		listaRecetas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane spLista = new JScrollPane(listaRecetas);
		spLista.setBounds(400, 195, 311, 195);
		spLista.setBorder(null);
		contentPane.add(spLista);
		
		btnEnviar = new JButton("<<");
		btnEnviar.setBounds(513, 426, 89, 23);
		btnEnviar.setEnabled(false);
		btnEnviar.addActionListener(new ControladorBtnEnviar(this));
		contentPane.add(btnEnviar);
		
		btn_Imprimir = new JButton("Vista Previa");
		btn_Imprimir.setBounds(660, 460, 109, 23);
		btn_Imprimir.setEnabled(false);
		btn_Imprimir.addActionListener(new ControladorBtnImprimir(this));
		contentPane.add(btn_Imprimir);
		
		JLabel et_recetario = new JLabel("Recetario:");
		et_recetario.setForeground(Color.WHITE);
		et_recetario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		et_recetario.setBounds(400, 165, 89, 20);
		contentPane.add(et_recetario);
		
		ruta = new JLabel();
		
		JLabel et_fondo = new JLabel();
		et_fondo.setBounds(0, 0, 784, 496);
		
		BufferedImage icon;
		try {
			icon = ImageIO.read(VentanaPrincipal.class.getResource("/images/fogones.jpeg"));
			et_fondo.setIcon(new ImageIcon(icon));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		contentPane.add(et_fondo);
	}
	
	public JTextField getTf_receta() {
		return tf_receta;
	}
	public void setTf_receta(JTextField tf_receta) {
		this.tf_receta = tf_receta;
	}
	public JTextField getTf_categ() {
		return tf_categ;
	}
	public void setTf_categ(JTextField tf_categ) {
		this.tf_categ = tf_categ;
	}

	public JLabel getRuta() {
		return ruta;
	}

	public void setRuta(JLabel ruta) {
		this.ruta = ruta;
	}

	public JTextArea getTa_ingred() {
		return ta_ingred;
	}

	public void setTa_ingred(JTextArea ta_ingred) {
		this.ta_ingred = ta_ingred;
	}

	public JTextArea getTa_prepa() {
		return ta_prepa;
	}

	public void setTa_prepa(JTextArea ta_prepa) {
		this.ta_prepa = ta_prepa;
	}
	
	public JList<Receta> getListaRecetas() {
		return listaRecetas;
	}

	public JLabel getEt_foto() {
		return et_foto;
	}
	
	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	/**
	 * Limpiará las cajas de texto y modificará los botones según parámetros.
	 * @param limpiarLista
	 * @param mostrarOtraAcc
	 * @param habilitarBotones
	 */
	public void limpiar(boolean limpiarLista, boolean mostrarOtraAcc, boolean habilitarBotones) {
			
		tf_receta.setText("");
		tf_categ.setText("");
		ta_ingred.setText("");
		ta_prepa.setText("");
		et_foto.setIcon(null);
		et_foto.setText("");
		et_foto.setToolTipText("");
		ruta.setText("");
		
		if(limpiarLista) {
			DefaultListModel dlm = new DefaultListModel();
			listaRecetas.setModel(dlm);
		}
		
		
		if(mostrarOtraAcc){
		 
			btnAdd.setVisible(false);
			btnBuscar.setVisible(false);
			btnOtraAcc.setVisible(true);
			btnEnviar.setEnabled(true);
		}
		else {
			btnOtraAcc.setVisible(false);
			btnAdd.setVisible(true);
			btnBuscar.setVisible(true);
			btnEnviar.setEnabled(false);
		}
		 
		if(habilitarBotones) {
			btnModif.setEnabled(true);
			btnBorrar.setEnabled(true);
			btn_Imprimir.setEnabled(true);
		}else {
			btnModif.setEnabled(false);
			btnBorrar.setEnabled(false);
			btn_Imprimir.setEnabled(false);
		}
		
		
	}
	
	

	private JPanel contentPane;
	private JTextField tf_receta;
	private JTextField tf_categ;
	private JTextArea ta_ingred;
	private JTextArea ta_prepa;
	private JLabel et_foto;
	private JLabel ruta;
	private JList<Receta> listaRecetas;
	private JButton btnAdd, btnBorrar, btnModif, btnBuscar, btnEnviar, btnOtraAcc, btn_Imprimir;
	private Receta receta;

}
