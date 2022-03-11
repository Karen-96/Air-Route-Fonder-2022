package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;
import dato.vo.AeropuertoVo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelCargarAeropuertos extends JPanel implements ActionListener {

	private static Coordinador coordinador = new Coordinador(); // objeto miCoordinador que permite la relacion entre
	private JPanel panel;
	private JTextField textAbreviacion;
	private JTextField textNombre;
	private JTable tableAeropuertos;
	private JLabel lblAbreviacion;
	private JLabel lblNombre;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane JScrollPaneAeropuertos;
	DefaultTableModel model;
	private JLabel lblBuscar;
	private JButton btnEliminarAbreviacion;
	private JButton btnEliminarNombre;
	private JTextField textBuscar;
	private TreeMap<String, AeropuertoVo> listaAeropuertos;
	AeropuertoVo aeropuertoVo;
	private JButton btnVolver;
	
	//Constructor
	public PanelCargarAeropuertos() {
		model = new DefaultTableModel();
		listaAeropuertos = new TreeMap<>();
		aeropuertoVo = new AeropuertoVo();
		
		iniciarComponentes();

	}
	
	//Inicia todos los componentes de la aplicacion	
	public void iniciarComponentes() {
		setBounds(0, 0, 1145, 678);
		setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 1255, 678);
		add(panel);
		panel.setLayout(null);

		lblAbreviacion = new JLabel("Abreviaci\u00F3n:");
		lblAbreviacion.setFont(new Font("Roboto Slab Medium", Font.PLAIN, 20));
		lblAbreviacion.setBounds(24, 218, 135, 30);
		panel.add(lblAbreviacion);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Roboto Slab Medium", Font.PLAIN, 20));
		lblNombre.setBounds(24, 276, 135, 30);
		panel.add(lblNombre);

		textAbreviacion = new JTextField();
		textAbreviacion.setToolTipText("abreviacion del aeropuerto");
		textAbreviacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAbreviacion.setBounds(155, 220, 385, 29);
		panel.add(textAbreviacion);
		textAbreviacion.setColumns(10);

		textNombre = new JTextField();
		textNombre.setToolTipText("Nombre del aeropuerto");
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textNombre.setColumns(10);
		textNombre.setBounds(155, 278, 385, 29);
		panel.add(textNombre);

		JScrollPaneAeropuertos = new JScrollPane();
		JScrollPaneAeropuertos.setBounds(606, 101, 621, 515);
		panel.add(JScrollPaneAeropuertos);

		tableAeropuertos = new JTable();
		tableAeropuertos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tableAeropuertos.getSelectedRow();
				textAbreviacion.setText(model.getValueAt(fila, 0).toString());
				textNombre.setText(model.getValueAt(fila, 1).toString());
			}
		});
		
		tableAeropuertos.setFont(new Font("Arial", Font.PLAIN, 15));
		tableAeropuertos.setBackground(new Color(240, 248, 255));
		tableAeropuertos.setRowHeight(25);
		

		
		Object[] column = { "Abreviacion", "Nombre del Aeropuerto" };
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		tableAeropuertos.setModel(model);
		JScrollPaneAeropuertos.setViewportView(tableAeropuertos);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setToolTipText("Agrega el Aeropuerto");
		btnAgregar.setIcon(new ImageIcon(PanelCargarAeropuertos.class.getResource("/recursos/agregar.png")));
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAgregar.setBounds(40, 423, 151, 30);
		btnAgregar.addActionListener(this);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusPainted(false);
		//btnAgregar.setBorderPainted(false);
		btnAgregar.setContentAreaFilled(false);
		panel.add(btnAgregar);

		btnModificar = new JButton("Modificar");
		btnModificar.setToolTipText("Modifica el aeropuerto");
		btnModificar.setIcon(new ImageIcon(PanelCargarAeropuertos.class.getResource("/recursos/Actualizar.png")));
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModificar.setBounds(210, 423, 174, 30);
		btnModificar.addActionListener(this);
		btnModificar.setForeground(Color.black);
		btnModificar.setFocusPainted(false);
		//btnActualizar.setBorderPainted(false);
		btnModificar.setContentAreaFilled(false);
		panel.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setToolTipText("Elimina el aeropuerto");
		btnEliminar.setIcon(new ImageIcon(PanelCargarAeropuertos.class.getResource("/recursos/eliminarFila.png")));
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEliminar.setBounds(413, 423, 151, 30);
		btnEliminar.addActionListener(this);
		btnEliminar.setForeground(Color.red);
		btnEliminar.setFocusPainted(false);
		//btnEliminar.setBorderPainted(false);
		btnEliminar.setContentAreaFilled(false);
		panel.add(btnEliminar);
		
		lblBuscar = new JLabel("Buscar :");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBuscar.setBounds(694, 41, 72, 25);
		panel.add(lblBuscar);
		
		textBuscar = new JTextField();
		textBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarAeropuerto(textBuscar.getText());
			}
		});
		textBuscar.setBounds(770, 41, 326, 29);
		panel.add(textBuscar);
		textBuscar.setColumns(10);
		
		btnEliminarAbreviacion = new JButton("");
		btnEliminarAbreviacion.setToolTipText("Elimina todo el campo");
		btnEliminarAbreviacion.setIcon(new ImageIcon(PanelCargarAeropuertos.class.getResource("/recursos/eliminar.png")));
		btnEliminarAbreviacion.setFocusPainted(false);
		btnEliminarAbreviacion.setBorderPainted(false);
		btnEliminarAbreviacion.setContentAreaFilled(false);
		btnEliminarAbreviacion.setBounds(547, 220, 33, 31);
		btnEliminarAbreviacion.addActionListener(this);
		panel.add(btnEliminarAbreviacion);
		
		btnEliminarNombre = new JButton("");
		btnEliminarNombre.setToolTipText("Elimina todo el campo");
		btnEliminarNombre.setIcon(new ImageIcon(PanelCargarAeropuertos.class.getResource("/recursos/eliminar.png")));
		btnEliminarNombre.setFocusPainted(false);
		btnEliminarNombre.setContentAreaFilled(false);
		btnEliminarNombre.setBorderPainted(false);
		btnEliminarNombre.setBounds(547, 276, 33, 31);
		btnEliminarNombre.addActionListener(this);
		panel.add(btnEliminarNombre);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelCargarAeropuertos.class.getResource("/recursos/buscar.png")));
		lblNewLabel.setBounds(659, 30, 33, 40);
		panel.add(lblNewLabel);
		
		btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon(PanelCargarAeropuertos.class.getResource("/recursos/iconovolver.png")));
		btnVolver.setToolTipText("Volver al men\u00FA");
		btnVolver.setBounds(24, 26, 46, 23);
		btnVolver.addActionListener(this);
		panel.add(btnVolver);
	}

	// Metodo para completar la tabla de los aeropuertos
	public void completarTablaAeropuerto() {
		// remuevo todo lo que contengo en la tabla
		DefaultTableModel modelo = (DefaultTableModel) tableAeropuertos.getModel();
		int filas = tableAeropuertos.getRowCount();
		for (int i = 0; filas > i; i++) {
			modelo.removeRow(0);
		}
		listaAeropuertos = coordinador.getLogicaAeropuerto().validarConsultaAeropuerto();
		Object[] fila = new Object[tableAeropuertos.getModel().getColumnCount()]; // filas con 2 columnas
		
		for (Entry<String, AeropuertoVo> aeropuertoVo : listaAeropuertos.entrySet()) {
			fila[0] = aeropuertoVo.getValue().getAbreviacion();
			fila[1] = aeropuertoVo.getValue().getNombre();
			((DefaultTableModel) tableAeropuertos.getModel()).addRow(fila);
		}

	}
	
	public void buscarAeropuerto(String buscar) {		
		DefaultTableModel modelo = coordinador.getAeropuertoDao().buscarAeropuerto(buscar);		
		tableAeropuertos.setModel(modelo);		
	}

	private void limpiar() {
		textAbreviacion.setText("");
		textNombre.setText("");
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			
			aeropuertoVo.setAbreviacion(textAbreviacion.getText());
			aeropuertoVo.setNombre(textNombre.getText());
			String verificacion = coordinador.getLogicaAeropuerto().validarRegistroAeropuerto(aeropuertoVo);
			if (verificacion.isEmpty()) {
				// Si se registro se refresca la tabla
				coordinador.getAeropuertoDao().registrarAeropuerto(aeropuertoVo);
				completarTablaAeropuerto();
			}else {
				JOptionPane.showMessageDialog(null,verificacion,"Advertencia",JOptionPane.WARNING_MESSAGE);
			}
			limpiar();

		}
		if (e.getSource() == btnEliminar) {

			int fila = tableAeropuertos.getSelectedRow();
			System.out.println(fila);
			if (fila>=0) {
				String abreviacion = (String) tableAeropuertos.getValueAt(tableAeropuertos.getSelectedRow(), 0);
				if (textAbreviacion.getText().equals("")) {
					int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar el Aeropuerto?",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_NO_OPTION) {
						coordinador.getLogicaAeropuerto().validarEliminacion(abreviacion);
						completarTablaAeropuerto();						
					}					
				}else {
					int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro de eliminar el Aeropuerto?",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_NO_OPTION) {
						coordinador.getLogicaAeropuerto().validarEliminacion(abreviacion);
						completarTablaAeropuerto();	
						limpiar();
					}	
					
				}		

			} else {
				JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
			}

		}
		
		if(e.getSource()==btnEliminarAbreviacion) {
			textAbreviacion.setText("");			
		}
		if(e.getSource()==btnEliminarNombre) {
			textNombre.setText("");			
		}
		if(e.getSource()==btnModificar) {
			int fila = tableAeropuertos.getSelectedRow();
			if (fila>=0) {
				String abreviacion = (String) tableAeropuertos.getValueAt(tableAeropuertos.getSelectedRow(), 0);
				String nombre = (String) tableAeropuertos.getValueAt(tableAeropuertos.getSelectedRow(), 1);
				aeropuertoVo.setAbreviacion(textAbreviacion.getText());
				aeropuertoVo.setNombre(textNombre.getText());
				String verificacion = coordinador.getLogicaAeropuerto().validarModificacion(aeropuertoVo, abreviacion, nombre);
				if (verificacion.isEmpty()) {
					// Si se registro se refresca la tabla
					completarTablaAeropuerto();
				}else {
					JOptionPane.showMessageDialog(null,verificacion,"Advertencia",JOptionPane.WARNING_MESSAGE);					
				}
				limpiar();
				
			} else {
				JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
			}		
			
		}
		if(e.getSource()==btnVolver) {
			coordinador.getVentanaMenu().getPanelMenu().removeAll(); 
			coordinador.getVentanaMenu().getPanelMenu().add(coordinador.getPanelMenu());
			coordinador.getVentanaMenu().getPanelMenu().revalidate();
			coordinador.getVentanaMenu().getPanelMenu().repaint();		
		}

	}
}
