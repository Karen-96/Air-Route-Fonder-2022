package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controlador.Coordinador;
import javax.swing.ImageIcon;

public class PanelBusquedaMenosHoras extends JPanel {
	private Coordinador coordinador = new Coordinador(); // objeto miCoordinador que permite la relacion entre esta
														// clase y la clase coordinador
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
	private JLabel lblFondoPrecio;
	private JLabel label;

	/**
	 * Create the panel.
	 * @param caminoMasCortoMenosHoras 
	 */
	public PanelBusquedaMenosHoras(ArrayList<Object[]> infoBusquedaMenosHoras) {
		fecha = (String[]) infoBusquedaMenosHoras.get(0);
		camino = (String[]) infoBusquedaMenosHoras.get(1);// La primera posicion trae los nombre de los aeropuertos que componen el camino
		// mas corto
		edge = (String[]) infoBusquedaMenosHoras.get(2); // La segunda posicion trae la informacion que hay de cada aeropuerto
		total = (Object[]) infoBusquedaMenosHoras.get(3); // La tercera posicion trae los totales (precio, hora)		
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
		
		lblRespuestaFecha = new JLabel(fecha[0]);
		lblRespuestaFecha.setVerticalAlignment(SwingConstants.TOP);
		lblRespuestaFecha.setHorizontalTextPosition(SwingConstants.LEADING);
		lblRespuestaFecha.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaFecha.setBounds(566, 91, 287, 23);
		panel.add(lblRespuestaFecha);
		
		lblFechaVuelo = new JLabel("FechaVuelo :");
		lblFechaVuelo.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblFechaVuelo.setBounds(428, 82, 163, 29);
		panel.add(lblFechaVuelo);
		
		lblRespuestaVueloDestino = new JLabel(camino[camino.length - 1]);
		lblRespuestaVueloDestino.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaVueloDestino.setBounds(828, 139, 399, 33);
		panel.add(lblRespuestaVueloDestino);
		
		lblVueloDestino = new JLabel("Vuelo Destino :");
		lblVueloDestino.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblVueloDestino.setBounds(666, 133, 163, 29);
		panel.add(lblVueloDestino);
		
		lblRespuestaVueloOrigen = new JLabel(camino[0]);
		lblRespuestaVueloOrigen.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaVueloOrigen.setBounds(238, 139, 399, 33);
		panel.add(lblRespuestaVueloOrigen);
		
		lblVueloOrigen = new JLabel("Vuelo Origen :");
		lblVueloOrigen.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblVueloOrigen.setBounds(79, 133, 163, 29);
		panel.add(lblVueloOrigen);
		
		lblFondo = new JLabel("");
		lblFondo.setBackground(SystemColor.inactiveCaption);
		lblFondo.setBounds(59, 70, 1123, 108);
		lblFondo.setOpaque(true);
		panel.add(lblFondo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.inactiveCaption);
		scrollPane.setBounds(59, 238, 532, 392);
		panel.add(scrollPane);
		
		lblDatosVuelos = new JLabel(mensaje);
		lblDatosVuelos.setBackground(SystemColor.inactiveCaption);
		lblDatosVuelos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosVuelos.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane.setViewportView(lblDatosVuelos);
		lblDatosVuelos.setOpaque(true);
		lblDatosVuelos.setBounds(34, 36, 685, 430);
		
		lblTituloInformacion = new JLabel("Informaci\u00F3n del vuelo");
		lblTituloInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloInformacion.setBounds(59, 204, 532, 23);
		lblTituloInformacion.setFont(new Font("Elephant", Font.PLAIN, 20));
		panel.add(lblTituloInformacion);
		
		separator = new JSeparator();
		separator.setBounds(196, 225, 253, 2);
		panel.add(separator);
		
		lblPrecioTotal = new JLabel("Precio Total :");
		lblPrecioTotal.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblPrecioTotal.setBounds(666, 347, 163, 29);
		panel.add(lblPrecioTotal);
		
		lblHorasVueloTotal = new JLabel("Horas total de viaje :");
		lblHorasVueloTotal.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblHorasVueloTotal.setBounds(666, 277, 232, 29);
		panel.add(lblHorasVueloTotal);
		
		lblTotalEscala = new JLabel("Total Escalas :");
		lblTotalEscala.setFont(new Font("Ebrima", Font.BOLD | Font.ITALIC, 22));
		lblTotalEscala.setBounds(666, 416, 232, 29);
		panel.add(lblTotalEscala);
		
		lblRespuestaPrecioTotal = new JLabel("$ " + total[0]);
		lblRespuestaPrecioTotal.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaPrecioTotal.setBounds(811, 353, 399, 33);
		panel.add(lblRespuestaPrecioTotal);
		
		String horas =(String) total[1];
		lblRespuestaTotalViaje = new JLabel(horas);
		lblRespuestaTotalViaje.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaTotalViaje.setBounds(893, 284, 399, 33);
		panel.add(lblRespuestaTotalViaje);
		
		lblRespuestaTotalEscala = new JLabel(" " + (camino.length - 1));
		lblRespuestaTotalEscala.setFont(new Font("Palatino Linotype", Font.PLAIN, 19));
		lblRespuestaTotalEscala.setBounds(828, 422, 399, 33);
		panel.add(lblRespuestaTotalEscala);
		
		lblTitulo = new JLabel("Menos Horas");
		lblTitulo.setBounds(464, 21, 326, 38);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Elephant", Font.PLAIN, 35));
		panel.add(lblTitulo);
		
		lblFondoPrecio = new JLabel("");
		lblFondoPrecio.setOpaque(true);
		lblFondoPrecio.setBackground(SystemColor.inactiveCaption);
		lblFondoPrecio.setBounds(664, 275, 518, 31);
		panel.add(lblFondoPrecio);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(PanelBusquedaMenosHoras.class.getResource("/recursos/flecha derecha.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(637, 277, 32, 29);
		panel.add(label);
		
	}
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;

	}

}
