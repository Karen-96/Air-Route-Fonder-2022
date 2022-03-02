package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import java.awt.Color;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class PanelMenu extends JPanel implements ActionListener {
	private Coordinador coordinador  = new Coordinador(); //objeto miCoordinador que permite la relacion entre esta clase y la clase coordinador
	private JLabel lblTituloAplicacion;
	private JLabel fondoVentanaMenu;
	private JSeparator separator;
	private JTabbedPane pestañas;
	private JButton btnCargarDatos;
	
	//Ver esto
		private PanelCargarAeropuertos cargarAeropuertos;
		private PanelCargarVuelos cargarVuelos;
		private JLabel lblAeropuertoDestino;
		private JLabel lblAeropuertoOrigen;
		private JComboBox comboBoxAeropuertoOrigen;
		private JComboBox comboBoxAeropuertoDestino;
		private JLabel lblFechaVuelo;
		private JDateChooser dateChooserFechaHora;
		private JButton btnBuscarVuelo;

	/**
	 * Create the panel.
	 */
	public PanelMenu() {
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1270, 717);
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		//Boton para cargar aeropuertos
		btnCargarDatos = new JButton("Cargar Datos\r\n");
		btnCargarDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCargarDatos.setBounds(1087, 641, 139, 23);
		btnCargarDatos.addActionListener(this);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(483, 339, 304, 22);
		add(comboBox);
		
		JLabel lblTipoBusqueda = new JLabel("Tipo B\u00FAsqueda");
		lblTipoBusqueda.setForeground(Color.WHITE);
		lblTipoBusqueda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTipoBusqueda.setBounds(576, 315, 117, 23);
		add(lblTipoBusqueda);
		
		btnBuscarVuelo = new JButton("Buscar Vuelo");
		btnBuscarVuelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBuscarVuelo.setBounds(520, 430, 230, 37);
		add(btnBuscarVuelo);
		
		
		dateChooserFechaHora = new JDateChooser();
		dateChooserFechaHora.setBounds(941, 271, 181, 23);
		add(dateChooserFechaHora);
		
		lblFechaVuelo = new JLabel("Fecha Partida");
		lblFechaVuelo.setForeground(Color.WHITE);
		lblFechaVuelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaVuelo.setBounds(941, 246, 109, 23);
		add(lblFechaVuelo);
		
		comboBoxAeropuertoDestino = new JComboBox();
		comboBoxAeropuertoDestino.setBounds(556, 270, 329, 24);
		add(comboBoxAeropuertoDestino);
		
		lblAeropuertoDestino = new JLabel("Aeropuerto Destino");
		lblAeropuertoDestino.setForeground(Color.WHITE);
		lblAeropuertoDestino.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAeropuertoDestino.setBounds(556, 245, 147, 23);
		add(lblAeropuertoDestino);
		
		comboBoxAeropuertoOrigen = new JComboBox();
		comboBoxAeropuertoOrigen.setBounds(177, 270, 329, 24);
		add(comboBoxAeropuertoOrigen);
		
		lblAeropuertoOrigen = new JLabel("Aeropuerto Origen");
		lblAeropuertoOrigen.setForeground(Color.WHITE);
		lblAeropuertoOrigen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAeropuertoOrigen.setBounds(177, 245, 147, 23);
		add(lblAeropuertoOrigen);
		add(btnCargarDatos);
		
		//Linea de subrayado para el titulo
		separator = new JSeparator();
		separator.setBounds(379, 146, 481, 2);
		add(separator);
		
		//Titulo de la aplicacion		
		lblTituloAplicacion = new JLabel("Air Route Finder");
		lblTituloAplicacion.setFont(new Font("Algerian", Font.PLAIN, 50));
		lblTituloAplicacion.setBounds(406, 95, 457, 59);
		add(lblTituloAplicacion);			
		
		//Imagen de Fondo
		fondoVentanaMenu = new JLabel("");
		fondoVentanaMenu.setIcon(new ImageIcon(VentanaMenu.class.getResource("/recursos/ImagenFondo1.jpg")));
		fondoVentanaMenu.setBounds(0, 0, 1264, 688);
		add(fondoVentanaMenu);

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
			coordinador.getVentanaMenu().getPanelMenu().removeAll();
			coordinador.getVentanaMenu().getPanelMenu().add(pestañas);
			coordinador.getVentanaMenu().getPanelMenu().revalidate();
			coordinador.getVentanaMenu().getPanelMenu().repaint();
			
		}
		
	}
}
