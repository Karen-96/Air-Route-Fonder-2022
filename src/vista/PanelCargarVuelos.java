package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import controlador.Coordinador;
import modelo.vo.AeropuertoVo;
import modelo.vo.VueloVo;
import javax.swing.JFormattedTextField;

public class PanelCargarVuelos extends JPanel implements ActionListener{
	 
	private static Coordinador coordinador = new Coordinador(); // objeto miCoordinador que permite la relacion entre esta clase y la clase coordinador
	private String hora = "^(?:0?[1-9]|1[0-2]):[0-5][0-9]\\s?(?:[aApP](\\.?)[mM]\\1)?"; //expresion regular para los campos de hora
	private JPanel panel;
	private JComboBox comboBoxAeropuertosOrigen;
	private JComboBox comboBoxAeropuertosDestino;
	private JLabel lblAeropuertoDestino;
	private JLabel lblAeropuertoOrigen;
	private JLabel IconoAeropuertoOrigen;
	private JLabel iconoAeropuertoDestino;
	private JLabel iconoFechaHoraSalida;
	private JLabel iconoPrecio;
	private JLabel lblDemora;
	private JTable tableVuelos;
	private JLabel lblHoraSalida;
	private JLabel lblPrecio;
	private JScrollPane scrollPaneVuelo;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JDateChooser dateFechaHora;

	private Pattern pattern; // Patron para expresion regular
	private Border wrongBorder;
	private Border validateBorder;
	private Border defaultBorder;
	private JSpinner intPrecio;
	private JLabel lblTiempoVuelo;
	private JLabel iconoTiempoVuelo;
	DefaultTableModel model;
	private JFormattedTextField horaTiempoVuelo;
	private MaskFormatter mascara;
	private JFormattedTextField horaDemora;
	private JLabel lblNumeroVuelo;
	private JTextField textNumeroVuelo;
	
	private SimpleDateFormat dateFormat;
	private List<AeropuertoVo> listaAeropuertos;
	private JButton btnVolver;
	private VueloVo vuelovo;
	
	//Constructor
	public PanelCargarVuelos() {
		vuelovo = new VueloVo();
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		listaAeropuertos = new ArrayList<>();
		wrongBorder = BorderFactory.createLineBorder(Color.RED);
		validateBorder = BorderFactory.createLineBorder(Color.GREEN);
		model = new DefaultTableModel();
		iniciarComponentes();
	}
	
	//Inicia todos los componentes de la aplicacion	
	public void iniciarComponentes() {
		setBounds(0, 0, 1145, 678);
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 1265, 678);
		add(panel);
		panel.setLayout(null);
		
		//Etiqueta del nombre del Aeropuerto Origen
		lblAeropuertoOrigen = new JLabel("Aeropuerto Origen :");
		lblAeropuertoOrigen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAeropuertoOrigen.setBounds(81, 100, 156, 32);
		panel.add(lblAeropuertoOrigen);
		
		//Listado de Aeropuertos Origen para que seleccione el Administrador
		comboBoxAeropuertosOrigen = new JComboBox();
		comboBoxAeropuertosOrigen.setBounds(242, 108, 292, 22);
		comboBoxAeropuertosOrigen.addItem("Seleccione");
		panel.add(comboBoxAeropuertosOrigen);
		
		//Etiqueta del nombre del Aeropuerto Destino
		lblAeropuertoDestino = new JLabel("Aeropuerto Destino :");
		lblAeropuertoDestino.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAeropuertoDestino.setBounds(580, 100, 156, 32);
		panel.add(lblAeropuertoDestino);
		
		//Listado de Aeropuertos Destino para que seleccione el Administrador
		comboBoxAeropuertosDestino = new JComboBox();
		comboBoxAeropuertosDestino.setBounds(746, 108, 292, 22);
		comboBoxAeropuertosDestino.addItem("Seleccione");
		panel.add(comboBoxAeropuertosDestino);
		
		//Etiqueta de la Fecha y Hora de salida del vuelo
		lblHoraSalida = new JLabel("Fecha y Hora Salida :");
		lblHoraSalida.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHoraSalida.setBounds(83, 143, 162, 32);
		panel.add(lblHoraSalida);
		
		//Etiqueta del Precio del vuelo
		lblPrecio = new JLabel("Precio : $");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPrecio.setBounds(456, 143, 90, 32);
		panel.add(lblPrecio);
		
		//Icono para aeropuerto Origen
		IconoAeropuertoOrigen = new JLabel("");
		IconoAeropuertoOrigen.setIcon(new ImageIcon(PanelCargarVuelos.class.getResource("/recursos/aeropuerto origen.png")));
		IconoAeropuertoOrigen.setBounds(44, 100, 36, 32);
		panel.add(IconoAeropuertoOrigen);
		
		//Icono para aeropuerto Destino
		iconoAeropuertoDestino = new JLabel("");
		iconoAeropuertoDestino.setIcon(new ImageIcon(PanelCargarVuelos.class.getResource("/recursos/aero_destino.png")));
		iconoAeropuertoDestino.setBounds(544, 100, 36, 32);
		panel.add(iconoAeropuertoDestino);
		
		//Icono para fecha y hora
		iconoFechaHoraSalida = new JLabel("");
		iconoFechaHoraSalida.setIcon(new ImageIcon(PanelCargarVuelos.class.getResource("/recursos/fecha hora salida.png")));
		iconoFechaHoraSalida.setBounds(46, 143, 36, 32);
		panel.add(iconoFechaHoraSalida);
		
		//Icono para el precio
		iconoPrecio = new JLabel("");
		iconoPrecio.setIcon(new ImageIcon(PanelCargarVuelos.class.getResource("/recursos/precio.png")));
		iconoPrecio.setBounds(422, 143, 36, 32);
		panel.add(iconoPrecio);
		
		//Icono para la demora del vuelo
		lblDemora = new JLabel("Demora : ");
		lblDemora.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDemora.setBounds(880, 141, 75, 32);
		panel.add(lblDemora);
		this.pattern = Pattern.compile(hora);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(1066, 44, 2, 168);
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator);
		
		//ScrollPane para la table del los vuelos
		scrollPaneVuelo = new JScrollPane();
		scrollPaneVuelo.setBounds(33, 244, 1181, 374);
		panel.add(scrollPaneVuelo);
		
		//Tabla de todos los vuelos
		tableVuelos = new JTable();
		//Metodo cuando selecciona una fila de la tabla
		tableVuelos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tableVuelos.getSelectedRow();
				//Muestra los datos en los campos de cada vuelo
				textNumeroVuelo.setText(model.getValueAt(fila, 0).toString());
				try {
					dateFechaHora.setDate((Date) dateFormat.parse((String) model.getValueAt(fila, 1)));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				//Busca la abreviacion en la lista de los aeropuertos y guarda el nombre para mostrar en el comboBox	
				AeropuertoVo aeropuertoOrgn = null;
				AeropuertoVo aeropuertoDest = null;
				String aeropuertoOrigen = (String) model.getValueAt(fila, 2); 
				String aeropuertoDestino = (String) model.getValueAt(fila, 3); 
				for (AeropuertoVo aeropuertoVo : listaAeropuertos) {					
					if (aeropuertoVo.getAbreviacion().equals(aeropuertoOrigen.trim())) {
						aeropuertoOrgn = aeropuertoVo;
					}					
					if (aeropuertoVo.getAbreviacion().equals(aeropuertoDestino.trim())) {
						aeropuertoDest = aeropuertoVo;
					}									
				}				
			    comboBoxAeropuertosOrigen.setSelectedItem(aeropuertoOrgn.getAbreviacion()+" - "+aeropuertoOrgn.getNombre());
				comboBoxAeropuertosDestino.setSelectedItem(aeropuertoDest.getAbreviacion()+" - "+aeropuertoDest.getNombre());
				intPrecio.setValue(model.getValueAt(fila, 4));
				horaTiempoVuelo.setValue(model.getValueAt(fila, 5));
				horaDemora.setValue(model.getValueAt(fila, 6));
			}
		});
		
		tableVuelos.setFont(new Font("Arial", Font.PLAIN, 15));
		tableVuelos.setBackground(new Color(240, 248, 255));
		tableVuelos.setRowHeight(25);
		Object[] column = { "N° Vuelo","Fecha y Hora Salida", "Aeropuerto Origen", "Aeropuerto Destino", "Precio", "Tiempo Vuelo", "Demora" };
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		tableVuelos.setModel(model);
		
		scrollPaneVuelo.setViewportView(tableVuelos);
		
		//Boton para agregar un vuelo
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(1112, 57, 89, 23);
		btnAgregar.addActionListener(this);
		panel.add(btnAgregar);
		
		//Boton para modificar un vuelo
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(1112, 115, 89, 23);
		btnModificar.addActionListener(this);
		panel.add(btnModificar);
		
		//Boton para eliminar un vuelo
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(1112, 166, 89, 23);
		btnEliminar.addActionListener(this);
		panel.add(btnEliminar);
		
		//Fechas y hora para seleccionar un vuelo
		dateFechaHora = new JDateChooser("yyyy-MM-dd HH:mm", "####-##-##", '_');		
		dateFechaHora.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 17));
		dateFechaHora.getJCalendar().setMinSelectableDate(new Date());
		dateFechaHora.setBounds(244, 153, 172, 22);
		panel.add(dateFechaHora);
		
		//Campo donde ingresa el precio
		intPrecio = new JSpinner();
		intPrecio.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		intPrecio.setBounds(532, 151, 90, 22);
		panel.add(intPrecio);
		
		//Etiqueta para el Tiempo de Vuelo
		lblTiempoVuelo = new JLabel("Tiempo Vuelo :");
		lblTiempoVuelo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTiempoVuelo.setBounds(657, 148, 117, 22);
		panel.add(lblTiempoVuelo);
		
		//Icono para el tiempo de vuelo
		iconoTiempoVuelo = new JLabel("");
		iconoTiempoVuelo.setIcon(new ImageIcon(PanelCargarVuelos.class.getResource("/recursos/tiempo vuelo.png")));
		iconoTiempoVuelo.setBounds(624, 143, 36, 32);
		panel.add(iconoTiempoVuelo);
		
		//Formato para el tiempo de vuelo (hora)
		try {
			mascara = new MaskFormatter("##:##");
			mascara.setPlaceholderCharacter('0'); // Linea opcional

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Campo donde ingresa la hora del tiempo de un vuelo
		horaTiempoVuelo = new JFormattedTextField(mascara);
		horaTiempoVuelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String[] time = horaTiempoVuelo.getText().split(":"); // Separa las horas y los minutos
				int hora = Integer.parseInt(time[0]);
				int minutos = Integer.parseInt(time[1]);
				if (minutos > 59) {
					horaTiempoVuelo.setValue(horaTiempoVuelo.getValue());
				}else if(hora>24 || minutos >59) {
					JOptionPane.showMessageDialog(null,"Se acepta solo el formato de 24hs","Advertencia",JOptionPane.WARNING_MESSAGE);
					
				}

			}
		});
		horaTiempoVuelo.setBounds(773, 150, 97, 22);
		horaTiempoVuelo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(horaTiempoVuelo);
		
		//campo Demora donde se ingresa la hora de la demora de un vuelo
		horaDemora = new JFormattedTextField(mascara);
		horaDemora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String[] time = horaDemora.getText().split(":"); // Separa las horas y los minutos
				int hora = Integer.parseInt(time[0]);
				int minutos = Integer.parseInt(time[1]);
				if (minutos > 59) {
					horaDemora.setValue(horaDemora.getValue());
				}else if(hora>24 || minutos >59) {
					JOptionPane.showMessageDialog(null,"Se acepta solo el formato de 24hs","Advertencia",JOptionPane.WARNING_MESSAGE);
					
				}

			}
		});
		
		horaDemora.setBounds(955, 150, 89, 20);
		horaDemora.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(horaDemora);
		
		//Etiqueta Linea Aerea de un vuelo
		lblNumeroVuelo = new JLabel("N\u00B0 Vuelo :");
		lblNumeroVuelo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumeroVuelo.setBounds(44, 57, 82, 32);
		panel.add(lblNumeroVuelo);
		
		//Campo donde se ingresa el numero de vuelo
		textNumeroVuelo = new JTextField();
		textNumeroVuelo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNumeroVuelo.setBounds(126, 66, 127, 20);
		panel.add(textNumeroVuelo);
		textNumeroVuelo.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(44, 57, 1024, 4);
		panel.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(44, 196, 1024, 4);
		panel.add(separator_1_1);
		
		//Boton para volver al menu
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolver.setBounds(44, 23, 97, 23);
		btnVolver.addActionListener(this);
		panel.add(btnVolver);
	}
	
	
	
	//Metodo para completar la tabla con todos los vuelos
	public void completarTablaVuelos() {
		// remuevo todo lo que contengo en la tabla
		DefaultTableModel modelo = (DefaultTableModel) tableVuelos.getModel();
		int filas = tableVuelos.getRowCount();
		for (int i = 0; filas > i; i++) {
			modelo.removeRow(0);
		}
		
		List<VueloVo> listaVuelos = coordinador.getLogicaVuelo().validarConsultaVuelos();
		Object[] fila = new Object[tableVuelos.getModel().getColumnCount()]; // filas con 2 columnas
		
		for (int i = 0; i < listaVuelos.size(); i++) {				
			fila[0] = listaVuelos.get(i).getNumero_vuelo();
			fila[1] = dateFormat.format(listaVuelos.get(i).getFecha());
			fila[2] = listaVuelos.get(i).getAeropuerto_origen();
			fila[3] = listaVuelos.get(i).getAeropuerto_destino();
			fila[4]= listaVuelos.get(i).getPrecio();
			fila[5]=listaVuelos.get(i).getTiempo_vuelo();
			fila[6]=listaVuelos.get(i).getDemora();
			((DefaultTableModel) tableVuelos.getModel()).addRow(fila);
		}
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;		
	}
	
	//Metodo para llenar los comboBox con los Aeropuertos(abreviacion,nombre)
	public void mostrarAeropuertosComboBox() {
		listaAeropuertos = coordinador.getLogicaAeropuerto().validarConsultaAeropuerto();
		for (int i = 0; i<listaAeropuertos.size(); i++) {
			comboBoxAeropuertosOrigen.addItem(listaAeropuertos.get(i).getAbreviacion()+" - "+listaAeropuertos.get(i).getNombre());
			comboBoxAeropuertosDestino.addItem(listaAeropuertos.get(i).getAbreviacion()+" - "+listaAeropuertos.get(i).getNombre());			
		}	
	}
	
	//Metodo para setear los campos y poder llenar otro vuelo
	private void limpiar() {
		textNumeroVuelo.setText("");
		comboBoxAeropuertosOrigen.removeAllItems();
		comboBoxAeropuertosDestino.removeAllItems();
		comboBoxAeropuertosOrigen.addItem("Seleccione");
		comboBoxAeropuertosDestino.addItem("Seleccione");
		mostrarAeropuertosComboBox();
		dateFechaHora.setDate(null);
		intPrecio.setValue(0);
		horaTiempoVuelo.setText("00:00");
		horaDemora.setText("00:00");		
	}
	
	
	//Metodos para los botones "AGREGAR" , "MODIFICAR", "ELIMINAR", "VOLVER"
	@Override
	public void actionPerformed(ActionEvent e) {
		//Si se presiona el boton agregar, agrega un vuelo
		if(e.getSource()==btnAgregar) {
			System.out.println("hora tiempo vuelo"+horaTiempoVuelo.getText());
			//Recorto solo la abreviacion del aeropuerto origen
			String origen = (String) comboBoxAeropuertosOrigen.getSelectedItem();
			String[] parts = origen.split("-");
			String origen_abreviacion = parts[0].trim(); // abreviacion Origen
			
			//Recorto solo la abreviacion del aeropuerto Destino
			String destino = (String) comboBoxAeropuertosDestino.getSelectedItem();
			String[] parts2 = destino.split("-");
			String destino_abreviacion = parts2[0].trim(); // abreviacion Destino	
			
			
			
			//-------------------Transforma la fecha a un string----------------------------------------------
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//			Date fecha =dateFechaHora.getDate();
//			System.out.println(dateFormat.format(fecha)); //2013/10/15 16:16:39
			//------------------------------------------------------------------------------------------------
			
			//-------------------Transforma la fecha Date a un Timestamp--------------------------------------
			Date date = dateFechaHora.getDate();
			long d = date.getTime();
			java.sql.Timestamp fecha = new java.sql.Timestamp(d);
			//------------------------------------------------------------------------------------------------	
			
			vuelovo.setAeropuerto_origen(origen_abreviacion);
			vuelovo.setAeropuerto_destino(destino_abreviacion);
			vuelovo.setNumero_vuelo(textNumeroVuelo.getText());
			vuelovo.setFecha(fecha);
			vuelovo.setPrecio((int) intPrecio.getValue());
			vuelovo.setDemora(horaDemora.getText());
			vuelovo.setTiempo_vuelo(horaTiempoVuelo.getText());
			
			boolean verificacion = coordinador.getLogicaVuelo().validarRegistroVuelo(vuelovo);
			if (verificacion) {
				// Si se registro se refresca la tabla
				completarTablaVuelos();
			}
			limpiar();			
		}
		//Si se presiona el boton eliminar, elimina un vuelo
		if(e.getSource()==btnEliminar) {
			int fila = tableVuelos.getSelectedRow();
			if (fila>=0) {
				String numero_vuelo = (String) tableVuelos.getValueAt(tableVuelos.getSelectedRow(), 0);
				if (textNumeroVuelo.getText().equals("")) {
					int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar el Vuelo?",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_NO_OPTION) {
						coordinador.getLogicaVuelo().validarEliminacionTablaVuelo(numero_vuelo);
						completarTablaVuelos();;						
					}
					
				}else {
					int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar el Vuelo?",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_NO_OPTION) {
						coordinador.getLogicaVuelo().validarEliminacionTablaVuelo(numero_vuelo);
						completarTablaVuelos();	
						limpiar();
					}	
					
				}	
				
			}else {
				JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
			}			
		}
		//si se presiona el boton modificar, se modifica un vuelo
		if(e.getSource()==btnModificar) {
			int fila = tableVuelos.getSelectedRow();
			if (fila>=0) {
				String numero_vuelo_viejo = (String) tableVuelos.getValueAt(tableVuelos.getSelectedRow(), 0);
				//Obtengo el nuevo aeropuerto origen seleccionado del comboBox
				String origen = (String) comboBoxAeropuertosOrigen.getSelectedItem();
				String[] parts = origen.split("-");
				String origen_abreviacion_seleccionado = parts[0].trim(); // abreviacion Origen
				
				//Obtengo el nuevo aeropuerto destino seleccionado del comboBox
				String destino = (String) comboBoxAeropuertosDestino.getSelectedItem();
				String[] parts2 = destino.split("-");
				String destino_abreviacion_seleccionado = parts2[0].trim(); // abreviacion Destino			
			
				
				//-------------------Transformo la fecha Date a un Timestamp--------------------------------------
				Date date = dateFechaHora.getDate();
				long d = date.getTime();
				java.sql.Timestamp fecha = new java.sql.Timestamp(d);
				//------------------------------------------------------------------------------------------------
				
				vuelovo.setNumero_vuelo(textNumeroVuelo.getText());
				vuelovo.setFecha(fecha);
				vuelovo.setAeropuerto_origen(origen_abreviacion_seleccionado);
				vuelovo.setAeropuerto_destino(destino_abreviacion_seleccionado);
				vuelovo.setPrecio((int) intPrecio.getValue());
				vuelovo.setTiempo_vuelo(horaTiempoVuelo.getText());
				vuelovo.setDemora(horaDemora.getText());
				
				boolean verificacion = coordinador.getLogicaVuelo().validarModificacion(vuelovo,numero_vuelo_viejo);
				if (verificacion) {
					// Si se registro se refresca la tabla
					completarTablaVuelos();
				}
				limpiar(); 
				
			} else {
				JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
			}				
		}
		//Si se presiono el boton volver, vuelve a la pantalla Menu
		if(e.getSource()==btnVolver) {
			coordinador.getVentanaMenu().getPanelMenu().removeAll(); 
			coordinador.getVentanaMenu().getPanelMenu().add(coordinador.getPanelMenu());
			coordinador.getVentanaMenu().getPanelMenu().revalidate();
			coordinador.getVentanaMenu().getPanelMenu().repaint();			
		}	
	}
}
