package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class VentanaMenu extends JFrame implements ActionListener{
	private Coordinador coordinador; //objeto miCoordinador que permite la relacion entre esta clase y la clase coordinador
	private JPanel panelMenu;
	private JLabel lblTituloAplicacion;
	private JLabel fondoVentanaMenu;
	private JSeparator separator;
	private JTabbedPane pestañas;
	
	//Ver esto
	private PanelCargarAeopuertos cargarAeropuertos;
	private PanelCargarVuelos cargarVuelos;

	
	/**
	 * Establece la informacion que se presentara como introduccion del sistema
	 */
	public String textoIntroduccion = "";
	private JButton btnCargarDatos;
	

	
	
	
	public VentanaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1270, 717);
		setTitle("Air Route Finder");
		panelMenu = new JPanel();
		panelMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMenu.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false); //No se puede maximinizar
		setContentPane(panelMenu);
		
		//Boton para cargar aeropuertos
		btnCargarDatos = new JButton("Cargar Datos\r\n");
		btnCargarDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCargarDatos.setBounds(1087, 641, 139, 23);
		btnCargarDatos.addActionListener(this);
		panelMenu.add(btnCargarDatos);
		
		//Linea de subrayado para el titulo
		separator = new JSeparator();
		separator.setBounds(379, 146, 481, 2);
		panelMenu.add(separator);
		
		//Titulo de la aplicacion		
		lblTituloAplicacion = new JLabel("Air Route Finder");
		lblTituloAplicacion.setFont(new Font("Algerian", Font.PLAIN, 50));
		lblTituloAplicacion.setBounds(403, 95, 457, 59);
		panelMenu.add(lblTituloAplicacion);			
		
		//Imagen de Fondo
		fondoVentanaMenu = new JLabel("");
		fondoVentanaMenu.setIcon(new ImageIcon(VentanaMenu.class.getResource("/recursos/ImagenFondo1.jpg")));
		fondoVentanaMenu.setBounds(0, 0, 1264, 688);
		panelMenu.add(fondoVentanaMenu);
		
		
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnCargarDatos) {
			pestañas = new JTabbedPane();
			pestañas.setBounds(0,0,1255,678);
//			cargarAeropuertos = new PanelCargarAeopuertos();
			cargarVuelos = coordinador.getCargarVuelos();
			cargarVuelos.mostrarAeropuertosComboBox();;
			cargarVuelos.completarTablaVuelos();
			
			cargarAeropuertos = coordinador.getCargarAeropuertos();
			cargarAeropuertos.completarTablaAeropuerto();
			
			
			pestañas.add("Aeropuertos",cargarAeropuertos);
			pestañas.setFont(new Font("Arial", Font.PLAIN, 20));
			pestañas.add("Vuelos",cargarVuelos);
			panelMenu.removeAll();
			panelMenu.add(pestañas);
			panelMenu.revalidate();
			panelMenu.repaint();
			
		}
		
	}
}
