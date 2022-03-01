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

public class PanelMenu extends JPanel implements ActionListener {
	private Coordinador coordinador; //objeto miCoordinador que permite la relacion entre esta clase y la clase coordinador
	private JLabel lblTituloAplicacion;
	private JLabel fondoVentanaMenu;
	private JSeparator separator;
	private JTabbedPane pesta�as;
	private JButton btnCargarDatos;
	
	//Ver esto
		private PanelCargarAeopuertos cargarAeropuertos;
		private PanelCargarVuelos cargarVuelos;

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
		add(btnCargarDatos);
		
		//Linea de subrayado para el titulo
		separator = new JSeparator();
		separator.setBounds(379, 146, 481, 2);
		add(separator);
		
		//Titulo de la aplicacion		
		lblTituloAplicacion = new JLabel("Air Route Finder");
		lblTituloAplicacion.setFont(new Font("Algerian", Font.PLAIN, 50));
		lblTituloAplicacion.setBounds(403, 95, 457, 59);
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
			pesta�as = new JTabbedPane();
			pesta�as.setBounds(0,0,1255,678);
//			cargarAeropuertos = new PanelCargarAeopuertos();
			cargarVuelos = coordinador.getCargarVuelos();
			cargarVuelos.mostrarAeropuertosComboBox();;
			cargarVuelos.completarTablaVuelos();
			
			cargarAeropuertos = coordinador.getCargarAeropuertos();
			cargarAeropuertos.completarTablaAeropuerto();
			
			
			pesta�as.add("Aeropuertos",cargarAeropuertos);
			pesta�as.setFont(new Font("Arial", Font.PLAIN, 20));
			pesta�as.add("Vuelos",cargarVuelos);
			coordinador.getVentanaMenu().getPanelMenu().removeAll();
			coordinador.getVentanaMenu().getPanelMenu().add(pesta�as);
			coordinador.getVentanaMenu().getPanelMenu().revalidate();
			coordinador.getVentanaMenu().getPanelMenu().repaint();
			
		}
		
	}

}
