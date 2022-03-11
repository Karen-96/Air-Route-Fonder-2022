package vista;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;

import controlador.Coordinador;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PanelBusquedaEconomico extends JPanel implements ActionListener {
	private Coordinador coordinador; //objeto miCoordinador que permite la relacion entre esta clase y la clase coordinador
	private String[] fecha;
	private String[] camino;
	private String[] edge;
	private Object[] total;
	private String mensaje;
	private JPanel panel;
	private JLabel lblFondo;
	private JLabel lblVueloOrigen;
	private JLabel lblRespuestaVueloOrigen;
	private JLabel lblVueloDestino;
	private JLabel lblRespuestaVueloDestino;
	private JLabel lblFechaVuelo;
	private JLabel lblRespuestaFecha;
	private JLabel lblDatosVuelos;
	private JLabel lblTituloInformacion;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private JLabel lblPrecioTotal;
	private JLabel lblHorasVueloTotal;
	private JLabel lblTotalEscala;
	private JLabel lblRespuestaPrecioTotal;
	private JLabel lblRespuestaTotalViaje;
	private JLabel lblRespuestaTotalEscala;
	private JLabel lblTitulo;
	private JLabel label;
	private JLabel lblFondoPrecio;
	private JLabel iconoVueloOrigen;
	private JLabel iconoVueloDestino;
	private JLabel iconoTipoBusqueda;
	private JButton btnVolver;
	private JLabel iconoHoraTotal;
	private JLabel iconoTotalEscala;
	private JLabel iconoTotalPrecio;
	private JLabel lblFondoPantalla;
														
	

	/**
	 * Constructor
	 * @param caminoMasCortoEconomico 
	 */
	public PanelBusquedaEconomico(ArrayList<Object[]> infoBusqueda) {
		
		fecha = (String[]) infoBusqueda.get(0);
		camino = (String[]) infoBusqueda.get(1);// La primera posicion trae los nombre de los aeropuertos que componen el camino
		// mas corto
		edge = (String[]) infoBusqueda.get(2); // La segunda posicion trae la informacion que hay de cada aeropuerto
		total = (Object[]) infoBusqueda.get(3); // La tercera posicion trae los totales (precio, hora)		
		iniciarComponentes();

	}
	
	public void iniciarComponentes() {
		/*
		 * El mensaje se muestra en un JLabel y al no poder escribir hacia abajo, se
		 * hace en codigo HTML
		 */
		mensaje = "<html><body>"; // inicia

		// Muestro el nombre de los aeropuertos con su respectiva informacion
		for (int i = 0; i < edge.length; i++) {
			mensaje += camino[i] + "<br>" + edge[i] + "<br>";

		}		
		
		mensaje += "</body></html>";// termina
		
		setBounds(0, 0, 1145, 678);
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 1255, 678);
		add(panel);
		panel.setLayout(null);		
		
		btnVolver = new JButton("");
		btnVolver.setToolTipText("Volver al men\u00FA");
		btnVolver.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/iconovolver.png")));
		btnVolver.setBounds(21, 30, 46, 23);
		btnVolver.addActionListener(this);
		
		iconoTotalPrecio = new JLabel("");
		iconoTotalPrecio.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/precioVuelo.png")));
		iconoTotalPrecio.setBounds(638, 349, 30, 30);
		panel.add(iconoTotalPrecio);
		
		iconoTotalEscala = new JLabel("");
		iconoTotalEscala.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/escalaVuelo.png")));
		iconoTotalEscala.setBounds(637, 524, 30, 30);
		panel.add(iconoTotalEscala);
		
		iconoHoraTotal = new JLabel("");
		iconoHoraTotal.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/horaVuelo.png")));
		iconoHoraTotal.setBounds(637, 433, 30, 30);
		panel.add(iconoHoraTotal);
		panel.add(btnVolver);
		
		iconoTipoBusqueda = new JLabel("");
		iconoTipoBusqueda.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/tipoBusqueda.png")));
		iconoTipoBusqueda.setBounds(487, 21, 30, 30);
		panel.add(iconoTipoBusqueda);
		
		iconoVueloDestino = new JLabel("");
		iconoVueloDestino.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/VueloDestinoBlanco.png")));
		iconoVueloDestino.setBounds(626, 125, 30, 30);
		panel.add(iconoVueloDestino);
		
		iconoVueloOrigen = new JLabel("");
		iconoVueloOrigen.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/VueloOrigenBlanco.png")));
		iconoVueloOrigen.setBounds(32, 125, 30, 30);
		panel.add(iconoVueloOrigen);
		
		lblRespuestaFecha = new JLabel(fecha[0]);
		lblRespuestaFecha.setForeground(Color.WHITE);
		lblRespuestaFecha.setVerticalAlignment(SwingConstants.TOP);
		lblRespuestaFecha.setHorizontalTextPosition(SwingConstants.LEADING);
		lblRespuestaFecha.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaFecha.setBounds(597, 91, 287, 23);
		panel.add(lblRespuestaFecha);
		
		lblFechaVuelo = new JLabel("Fecha y hora del Vuelo :");
		lblFechaVuelo.setForeground(Color.WHITE);
		lblFechaVuelo.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblFechaVuelo.setBounds(337, 82, 259, 29);
		panel.add(lblFechaVuelo);
		
		lblRespuestaVueloDestino = new JLabel(camino[camino.length - 1]);
		lblRespuestaVueloDestino.setForeground(Color.WHITE);
		lblRespuestaVueloDestino.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaVueloDestino.setBounds(828, 131, 464, 33);
		panel.add(lblRespuestaVueloDestino);
		
		lblVueloDestino = new JLabel("Vuelo Destino :");
		lblVueloDestino.setForeground(Color.WHITE);
		lblVueloDestino.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblVueloDestino.setBounds(666, 125, 163, 29);
		panel.add(lblVueloDestino);
		
		lblRespuestaVueloOrigen = new JLabel(camino[0]);
		lblRespuestaVueloOrigen.setForeground(Color.WHITE);
		lblRespuestaVueloOrigen.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaVueloOrigen.setBounds(231, 131, 399, 33);
		panel.add(lblRespuestaVueloOrigen);
		
		lblVueloOrigen = new JLabel("Vuelo Origen :");
		lblVueloOrigen.setForeground(Color.WHITE);
		lblVueloOrigen.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblVueloOrigen.setBounds(72, 125, 163, 29);
		panel.add(lblVueloOrigen);
		
		lblFondo = new JLabel("");
		lblFondo.setBackground(new Color(0, 0, 0, 70));
		lblFondo.setBounds(21, 70, 1213, 108);
		lblFondo.setOpaque(true);
		lblFondo.setForeground(Color.ORANGE);
		panel.add(lblFondo);	
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.inactiveCaption);
		scrollPane.setBounds(21, 238, 570, 392);
		panel.add(scrollPane);
		
		lblDatosVuelos = new JLabel(mensaje);
		lblDatosVuelos.setForeground(SystemColor.infoText);
		lblDatosVuelos.setBackground(SystemColor.inactiveCaption);
		lblDatosVuelos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosVuelos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		scrollPane.setViewportView(lblDatosVuelos);
		lblDatosVuelos.setOpaque(true);
		lblDatosVuelos.setBounds(34, 36, 685, 430);
		
		lblTituloInformacion = new JLabel("Informaci\u00F3n de los  vuelos");
		lblTituloInformacion.setForeground(Color.WHITE);
		lblTituloInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloInformacion.setBounds(32, 204, 532, 23);
		lblTituloInformacion.setFont(new Font("Elephant", Font.PLAIN, 20));
		panel.add(lblTituloInformacion);
		
		separator = new JSeparator();
		separator.setBounds(158, 225, 279, 2);
		panel.add(separator);
		
		lblPrecioTotal = new JLabel("Precio Total :");
		lblPrecioTotal.setForeground(Color.WHITE);
		lblPrecioTotal.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 25));
		lblPrecioTotal.setBounds(667, 340, 163, 38);
		panel.add(lblPrecioTotal);
		
		lblHorasVueloTotal = new JLabel("Horas total de viaje :");
		lblHorasVueloTotal.setForeground(SystemColor.desktop);
		lblHorasVueloTotal.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 25));
		lblHorasVueloTotal.setBounds(666, 433, 264, 29);
		panel.add(lblHorasVueloTotal);
		
		lblTotalEscala = new JLabel("Total Escalas :");
		lblTotalEscala.setForeground(SystemColor.desktop);
		lblTotalEscala.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 25));
		lblTotalEscala.setBounds(666, 521, 232, 33);
		panel.add(lblTotalEscala);
		
		lblRespuestaPrecioTotal = new JLabel("$ " + total[0]);
		lblRespuestaPrecioTotal.setForeground(Color.WHITE);
		lblRespuestaPrecioTotal.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		lblRespuestaPrecioTotal.setBounds(828, 343, 423, 48);
		panel.add(lblRespuestaPrecioTotal);
		String horas =(String) total[1];
		lblRespuestaTotalViaje = new JLabel(horas);
		lblRespuestaTotalViaje.setForeground(SystemColor.windowText);
		lblRespuestaTotalViaje.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		lblRespuestaTotalViaje.setBounds(914, 433, 399, 45);
		panel.add(lblRespuestaTotalViaje);
		
		lblRespuestaTotalEscala = new JLabel(" " + (camino.length - 1));
		lblRespuestaTotalEscala.setForeground(SystemColor.windowText);
		lblRespuestaTotalEscala.setFont(new Font("Palatino Linotype", Font.BOLD, 28));
		lblRespuestaTotalEscala.setBounds(835, 523, 399, 45);
		panel.add(lblRespuestaTotalEscala);
		
		lblTitulo = new JLabel("Económico");
		lblTitulo.setForeground(SystemColor.desktop);
		lblTitulo.setBounds(464, 21, 326, 38);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Elephant", Font.PLAIN, 35));
		panel.add(lblTitulo);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/flecha derecha.png")));
		label.setBounds(598, 340, 32, 29);
		panel.add(label);
		
		lblFondoPrecio = new JLabel("");
		lblFondoPrecio.setBackground(new Color(0, 0, 0, 70));
		lblFondoPrecio.setBounds(637, 340, 598, 38);
		lblFondoPrecio.setOpaque(true);
		lblFondoPrecio.setForeground(Color.ORANGE);
		panel.add(lblFondoPrecio);
		
		lblFondoPantalla = new JLabel("");
		lblFondoPantalla.setIcon(new ImageIcon(PanelBusquedaEconomico.class.getResource("/recursos/ventana2.jpg")));
		lblFondoPantalla.setBounds(-33, 0, 1284, 699);
		panel.add(lblFondoPantalla);
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnVolver){			
			coordinador.getVentanaMenu().getPanelMenu().removeAll();
			coordinador.getVentanaMenu().getPanelMenu().add(coordinador.getPanelMenu());
			coordinador.getVentanaMenu().getPanelMenu().revalidate();
			coordinador.getVentanaMenu().getPanelMenu().repaint();
		}
		
	}	
}
