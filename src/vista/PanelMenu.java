package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import modelo.vo.AeropuertoVo;
import modelo.vo.VueloVo;

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
	private VueloVo vueloVo;
	
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
		private JComboBox comboBoxTipoBusqueda;
		private JLabel lblTipoBusqueda;
		private List<AeropuertoVo> listaAeropuertos;

	/**
	 * Create the panel.
	 */
	public PanelMenu() {
		listaAeropuertos = new ArrayList<>();
		vueloVo = new VueloVo();
		iniciarComponentes();

	}
	
	public void iniciarComponentes() {
		setBounds(0, 0, 1270, 717);		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		//Boton para cargar aeropuertos
		btnCargarDatos = new JButton("Cargar Datos\r\n");
		btnCargarDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCargarDatos.setBounds(1087, 641, 139, 23);
		btnCargarDatos.addActionListener(this);
		
		comboBoxTipoBusqueda = new JComboBox();
		comboBoxTipoBusqueda.setModel(new DefaultComboBoxModel(new String[] { "Economico", "Menos horas", "Menos escalas" }));
		comboBoxTipoBusqueda.setSelectedItem(null);
		comboBoxTipoBusqueda.setBounds(483, 339, 304, 22);
		add(comboBoxTipoBusqueda);
		
		lblTipoBusqueda = new JLabel("Tipo B\u00FAsqueda");
		lblTipoBusqueda.setForeground(Color.WHITE);
		lblTipoBusqueda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTipoBusqueda.setBounds(576, 315, 117, 23);
		add(lblTipoBusqueda);
		
		btnBuscarVuelo = new JButton("Buscar Vuelo");
		btnBuscarVuelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBuscarVuelo.setBounds(520, 430, 230, 37);
		btnBuscarVuelo.addActionListener(this);
		add(btnBuscarVuelo);
		
		
		dateChooserFechaHora = new JDateChooser();
		dateChooserFechaHora.setBounds(941, 271, 181, 23);
		dateChooserFechaHora.getJCalendar().setMinSelectableDate(new Date());
		add(dateChooserFechaHora);
		
		lblFechaVuelo = new JLabel("Fecha Partida");
		lblFechaVuelo.setForeground(Color.WHITE);
		lblFechaVuelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaVuelo.setBounds(941, 246, 109, 23);
		add(lblFechaVuelo);
		
		comboBoxAeropuertoDestino = new JComboBox();
		comboBoxAeropuertoDestino.addItem("Seleccione");
		comboBoxAeropuertoDestino.setBounds(556, 270, 329, 24);
		add(comboBoxAeropuertoDestino);
		
		lblAeropuertoDestino = new JLabel("Aeropuerto Destino");
		lblAeropuertoDestino.setForeground(Color.WHITE);
		lblAeropuertoDestino.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAeropuertoDestino.setBounds(556, 245, 147, 23);
		add(lblAeropuertoDestino);
		
		comboBoxAeropuertoOrigen = new JComboBox();
		comboBoxAeropuertoOrigen.addItem("Seleccione");
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
	public void mostrarAeropuertosComboBox() {
		listaAeropuertos = coordinador.getLogicaAeropuerto().validarConsultaAeropuerto();
		for (int i = 0; i<listaAeropuertos.size(); i++) {
			comboBoxAeropuertoOrigen.addItem(listaAeropuertos.get(i).getAbreviacion()+" - "+listaAeropuertos.get(i).getNombre());
			comboBoxAeropuertoDestino.addItem(listaAeropuertos.get(i).getAbreviacion()+" - "+listaAeropuertos.get(i).getNombre());			
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnCargarDatos) {
			pestañas = new JTabbedPane();
			pestañas.setBounds(0, 0, 1270, 717);
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
		if (e.getSource() == btnBuscarVuelo) {
			//Obtengo el nuevo aeropuerto origen seleccionado del comboBox
			String origen = (String) comboBoxAeropuertoOrigen.getSelectedItem();
			String[] parts = origen.split("-");
			String origen_abreviacion_seleccionado = parts[0]; // abreviacion Origen
			
			//Obtengo el nuevo aeropuerto destino seleccionado del comboBox
			String destino = (String) comboBoxAeropuertoDestino.getSelectedItem();
			String[] parts2 = destino.split("-");
			String destino_abreviacion_seleccionado = parts2[0]; // abreviacion Destino	
			
			if(dateChooserFechaHora.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Por favor seleccione una fecha");
			}
				
			//-------------------Transformo la fecha Date a un Timestamp--------------------------------------
			Date date = dateChooserFechaHora.getDate();
			long d = date.getTime();
			java.sql.Timestamp fecha = new java.sql.Timestamp(d);
			//------------------------------------------------------------------------------------------------
			
           
			
			vueloVo.setAeropuerto_origen(origen_abreviacion_seleccionado);
			vueloVo.setAeropuerto_destino(destino_abreviacion_seleccionado);
			vueloVo.setFecha(fecha);
			int itemTipoBusqueda= comboBoxTipoBusqueda.getSelectedIndex();
			coordinador.getLogicaTipoBusqueda().validarTipoDeBusqueda(vueloVo, itemTipoBusqueda);
			
			
			
			
		}
		
	}
}
