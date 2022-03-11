package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controlador.Coordinador;
import dato.vo.AeropuertoVo;
import dato.vo.VueloVo;
import net.datastructures.Graph;
import net.datastructures.PositionalList;
import net.datastructures.Vertex;

public class PanelMenu extends JPanel implements ActionListener {
	private Coordinador coordinador = new Coordinador(); // objeto miCoordinador que permite la relacion entre esta
															// clase y la clase coordinador
	private JLabel lblTituloAplicacion;
	private JLabel fondoVentanaMenu;
	private JSeparator separator;
	private JTabbedPane pestañas;
	private JTabbedPane pestañasBusqueda;
	private JButton btnCargarDatos;
	private VueloVo vueloVo;

	// Ver esto
	private PanelCargarAeropuertos cargarAeropuertos;
	private PanelCargarVuelos cargarVuelos;

	// Paneles de la Info de busqueda----------------------
	private PanelBusquedaEconomico busquedaEconomico;
	private PanelBusquedaMenosEscala busquedaMenosEscala;
	private PanelBusquedaMenosHoras busquedaMenosHoras;
	// ----------------------------------------------------

	private JLabel lblAeropuertoDestino;
	private JLabel lblAeropuertoOrigen;
	private JComboBox comboBoxAeropuertoOrigen;
	private JComboBox comboBoxAeropuertoDestino;
	private JLabel lblFechaVuelo;
	private JDateChooser dateChooserFechaHora;
	private JButton btnBuscarVuelo;
	private JComboBox comboBoxTipoBusqueda;
	private JLabel lblTipoBusqueda;
	private TreeMap<String, AeropuertoVo> listaAeropuertos;
	private List<VueloVo> listaVuelos;
	private String origen_abreviacion_seleccionado;
	private String destino_abreviacion_seleccionado;

	// Grafo
	Graph<String, VueloVo> grafo;
	private JLabel lblMensaje;

	/**
	 * Create the panel.
	 */
	public PanelMenu() {
		listaAeropuertos = new TreeMap<>();
		listaVuelos = new ArrayList();
		vueloVo = new VueloVo();
		iniciarComponentes();

	}

	public void iniciarComponentes() {
		setBounds(0, 0, 1270, 717);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		// Boton para cargar aeropuertos
		btnCargarDatos = new JButton("Cargar Datos\r\n");
		btnCargarDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCargarDatos.setBounds(1087, 641, 139, 23);
		btnCargarDatos.addActionListener(this);

		comboBoxTipoBusqueda = new JComboBox();
		comboBoxTipoBusqueda
				.setModel(new DefaultComboBoxModel(new String[] { "Económico", "Menos horas", "Menos escalas" }));
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

		// Linea de subrayado para el titulo
		separator = new JSeparator();
		separator.setBounds(379, 146, 481, 2);
		add(separator);

		// Titulo de la aplicacion
		lblTituloAplicacion = new JLabel("Air Route Finder");
		lblTituloAplicacion.setFont(new Font("Algerian", Font.PLAIN, 50));
		lblTituloAplicacion.setBounds(406, 95, 457, 59);
		add(lblTituloAplicacion);

		// Imagen de Fondo
		fondoVentanaMenu = new JLabel("");
		fondoVentanaMenu.setIcon(new ImageIcon(VentanaMenu.class.getResource("/recursos/ImagenFondo1.jpg")));
		fondoVentanaMenu.setBounds(0, 0, 1264, 688);
		add(fondoVentanaMenu);

		lblMensaje = new JLabel("");
		lblMensaje.setBounds(56, 360, 304, 89);
		add(lblMensaje);

	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;

	}

	public void mostrarAeropuertosComboBox() {
		listaAeropuertos = coordinador.getLogicaAeropuerto().validarConsultaAeropuerto();		
		for (Entry<String, AeropuertoVo> aeropuertoVo : listaAeropuertos.entrySet()) {
			comboBoxAeropuertoOrigen
					.addItem(aeropuertoVo.getValue().getAbreviacion() + " - " + aeropuertoVo.getValue().getNombre());
			comboBoxAeropuertoDestino
					.addItem(aeropuertoVo.getValue().getAbreviacion() + " - " + aeropuertoVo.getValue().getNombre());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Si presiona el boton Cargar Datos muestra los paneles de aeropuerto y vuelo
		if (e.getSource() == btnCargarDatos) {
			pestañas = new JTabbedPane();
			pestañas.setBounds(0, 0, 1270, 717);
			cargarVuelos = coordinador.getCargarVuelos();
			cargarVuelos.mostrarAeropuertosComboBox();
			;
			cargarVuelos.completarTablaVuelos();

			cargarAeropuertos = coordinador.getCargarAeropuertos();
			cargarAeropuertos.completarTablaAeropuerto();

			pestañas.add("Aeropuertos", cargarAeropuertos);
			pestañas.setFont(new Font("Arial", Font.PLAIN, 20));
			pestañas.add("Vuelos", cargarVuelos);
			coordinador.getVentanaMenu().getPanelMenu().removeAll();
			coordinador.getVentanaMenu().getPanelMenu().add(pestañas);
			coordinador.getVentanaMenu().getPanelMenu().revalidate();
			coordinador.getVentanaMenu().getPanelMenu().repaint();

		}
		//Si presiona el boton de buscar vuelo 
		if (e.getSource() == btnBuscarVuelo) {
			// Obtengo el nuevo aeropuerto origen seleccionado del comboBox
			String origen = (String) comboBoxAeropuertoOrigen.getSelectedItem();
			String[] parts = origen.split("-");
			origen_abreviacion_seleccionado = parts[0].trim(); // abreviacion Origen

			// Obtengo el nuevo aeropuerto destino seleccionado del comboBox
			String destino = (String) comboBoxAeropuertoDestino.getSelectedItem();
			String[] parts2 = destino.split("-");
			destino_abreviacion_seleccionado = parts2[0].trim(); // abreviacion Destino

			if (dateChooserFechaHora.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Por favor seleccione una fecha");
			}

			// Transformo la fecha Date a un Timestamp-------------------
			Date date = dateChooserFechaHora.getDate();
			long d = date.getTime();
			java.sql.Timestamp fecha = new java.sql.Timestamp(d);
			// ----------------------------------------------------------
			
			vueloVo.setAeropuerto_origen(listaAeropuertos.get(origen_abreviacion_seleccionado));
			vueloVo.setAeropuerto_destino(listaAeropuertos.get(destino_abreviacion_seleccionado));
			vueloVo.setFecha(fecha);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaSeleccionada = dateChooserFechaHora.getDate();

			// Trae el numero que seleccino el usuario para el tipo de busqueda
			int itemTipoBusqueda = comboBoxTipoBusqueda.getSelectedIndex();

			// Valida que los campos esten correctamente los datos para buscar el vuelo
			String verificacion = coordinador.getLogicaTipoBusqueda().validarTipoDeBusqueda(vueloVo, itemTipoBusqueda);
			System.out.println(verificacion);
			if (verificacion.isEmpty()) {
				// Busca los vuelos dependiendo la fecha que seleccione el usuario
				List<VueloVo> listaVuelos = coordinador.getLogicaVuelo()
						.validarConsultaVuelosFecha(dateFormat.format(fechaSeleccionada));

				if (listaVuelos.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No hay Vuelos para esa fecha, seleccione otra fecha",
							"Advertencia", JOptionPane.WARNING_MESSAGE);

				} else {
					// Crea el grafo con los vuelos que se busque dependiendo de la fecha que
					// ingrese el usuario
					grafo = coordinador.getLogicaTipoBusqueda().crearGrafo(listaVuelos);

					for (Vertex<String> vueloVo : grafo.vertices()) {
						System.out.println(vueloVo.getElement());
					}
					// Se busca el camino mas corto usando el algoritmo de disktra
					List<PositionalList<Vertex<String>>> caminito = coordinador.getLogicaTipoBusqueda().caminoMasCorto(
							grafo, origen_abreviacion_seleccionado, destino_abreviacion_seleccionado,
							comboBoxTipoBusqueda.getSelectedIndex());
					boolean verf = true;
					// verifico que las listas no esten vacias
					for (PositionalList<Vertex<String>> list : caminito) {
						if (list == null) {
							verf = false;
						}
					}

					if (verf) {
						// Se obtiene la informacion a mostrar en pantalla
						// porq de objeto?
						ArrayList<Object[]> caminoMasCortoEconomico = coordinador.getLogicaTipoBusqueda()
								.obtenerBusqueda(grafo, caminito, 0);
						ArrayList<Object[]> caminoMasCortoMenosHoras = coordinador.getLogicaTipoBusqueda()
								.obtenerBusqueda(grafo, caminito, 1);
						ArrayList<Object[]> caminoMasCortoMenosEscalas = coordinador.getLogicaTipoBusqueda()
								.obtenerBusqueda(grafo, caminito, 2);

						pestañasBusqueda = new JTabbedPane();
						pestañasBusqueda.setBounds(0, 0, 1270, 717);
						busquedaEconomico = new PanelBusquedaEconomico(caminoMasCortoEconomico);
						busquedaEconomico.setCoordinador(coordinador);
						busquedaMenosHoras = new PanelBusquedaMenosHoras(caminoMasCortoMenosHoras);
						busquedaMenosEscala = new PanelBusquedaMenosEscala(caminoMasCortoMenosEscalas);

						/* Muestro las pestañas dependiendo la seleccion del Usuario pero con los 3
						 * metodos de busquedas ya realizados por hilos
						 * 
						 */
						if (comboBoxTipoBusqueda.getSelectedIndex() == 0) {
							pestañasBusqueda.add("Económico", busquedaEconomico);
							pestañasBusqueda.setFont(new Font("Arial", Font.PLAIN, 20));
							pestañasBusqueda.add("Menos Horas", busquedaMenosHoras);
							pestañasBusqueda.add("Menos Escalas", busquedaMenosEscala);
							coordinador.getVentanaMenu().getPanelMenu().removeAll();
							coordinador.getVentanaMenu().getPanelMenu().add(pestañasBusqueda);
							coordinador.getVentanaMenu().getPanelMenu().revalidate();
							coordinador.getVentanaMenu().getPanelMenu().repaint();
						} else if (comboBoxTipoBusqueda.getSelectedIndex() == 1) {
							pestañasBusqueda.add("Menos Horas", busquedaMenosHoras);
							pestañasBusqueda.add("Menos Escalas", busquedaMenosEscala);
							pestañasBusqueda.add("Económico", busquedaEconomico);
							pestañasBusqueda.setFont(new Font("Arial", Font.PLAIN, 20));
							coordinador.getVentanaMenu().getPanelMenu().removeAll();
							coordinador.getVentanaMenu().getPanelMenu().add(pestañasBusqueda);
							coordinador.getVentanaMenu().getPanelMenu().revalidate();
							coordinador.getVentanaMenu().getPanelMenu().repaint();

						} else {
							pestañasBusqueda.add("Menos Escalas", busquedaMenosEscala);
							pestañasBusqueda.add("Económico", busquedaEconomico);
							pestañasBusqueda.add("Menos Horas", busquedaMenosHoras);
							pestañasBusqueda.setFont(new Font("Arial", Font.PLAIN, 20));
							coordinador.getVentanaMenu().getPanelMenu().removeAll();
							coordinador.getVentanaMenu().getPanelMenu().add(pestañasBusqueda);
							coordinador.getVentanaMenu().getPanelMenu().revalidate();
							coordinador.getVentanaMenu().getPanelMenu().repaint();

						}

					} else {
						JOptionPane.showMessageDialog(null, "No hay Vuelos programados, Ingrese otro vuelo", "Advertencia", JOptionPane.ERROR_MESSAGE);

					}

				}

			}else {
				JOptionPane.showMessageDialog(null,verificacion,"Advertencia", JOptionPane.WARNING_MESSAGE);
			}

		}

	}
}
