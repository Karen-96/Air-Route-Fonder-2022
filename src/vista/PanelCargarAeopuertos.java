package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.Coordinador;
import modelo.vo.AeropuertoVo;

public class PanelCargarAeopuertos extends JPanel implements ActionListener {

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
	private JLabel lblNewLabel_2;
	private JTextField textBuscar;
	private JButton btnBuscar;

	/**
	 * Create the panel.
	 */
	public PanelCargarAeopuertos() {
		setBounds(0, 0, 1145, 678);
		setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(0, 0, 1255, 678);
		add(panel);
		panel.setLayout(null);

		lblAbreviacion = new JLabel("Abreviacion:");
		lblAbreviacion.setFont(new Font("Roboto Slab Medium", Font.PLAIN, 20));
		lblAbreviacion.setBounds(24, 218, 135, 30);
		panel.add(lblAbreviacion);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Roboto Slab Medium", Font.PLAIN, 20));
		lblNombre.setBounds(24, 276, 135, 30);
		panel.add(lblNombre);

		textAbreviacion = new JTextField();
		textAbreviacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAbreviacion.setBounds(155, 220, 151, 29);
		panel.add(textAbreviacion);
		textAbreviacion.setColumns(10);

		textNombre = new JTextField();
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
		

		model = new DefaultTableModel();
		Object[] column = { "Abreviacion", "Nombre del Aeropuerto" };
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		tableAeropuertos.setModel(model);
		JScrollPaneAeropuertos.setViewportView(tableAeropuertos);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(PanelCargarAeopuertos.class.getResource("/recursos/agregar.png")));
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAgregar.setBounds(40, 423, 151, 30);
		btnAgregar.addActionListener(this);
		btnAgregar.setForeground(Color.black);
		btnAgregar.setFocusPainted(false);
		//btnAgregar.setBorderPainted(false);
		btnAgregar.setContentAreaFilled(false);
		panel.add(btnAgregar);

		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelCargarAeopuertos.class.getResource("/recursos/Actualizar.png")));
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModificar.setBounds(229, 423, 174, 30);
		btnModificar.addActionListener(this);
		btnModificar.setForeground(Color.black);
		btnModificar.setFocusPainted(false);
		//btnActualizar.setBorderPainted(false);
		btnModificar.setContentAreaFilled(false);
		panel.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelCargarAeopuertos.class.getResource("/recursos/eliminarFila.png")));
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
		textBuscar.setBounds(770, 41, 326, 29);
		panel.add(textBuscar);
		textBuscar.setColumns(10);
		
		btnEliminarAbreviacion = new JButton("");
		btnEliminarAbreviacion.setToolTipText("Elimina todo el campo");
		btnEliminarAbreviacion.setIcon(new ImageIcon(PanelCargarAeopuertos.class.getResource("/recursos/eliminar.png")));
		btnEliminarAbreviacion.setFocusPainted(false);
		btnEliminarAbreviacion.setBorderPainted(false);
		btnEliminarAbreviacion.setContentAreaFilled(false);
		btnEliminarAbreviacion.setBounds(310, 217, 33, 31);
		btnEliminarAbreviacion.addActionListener(this);
		panel.add(btnEliminarAbreviacion);
		
		btnEliminarNombre = new JButton("");
		btnEliminarNombre.setToolTipText("Elimina todo el campo");
		btnEliminarNombre.setIcon(new ImageIcon(PanelCargarAeopuertos.class.getResource("/recursos/eliminar.png")));
		btnEliminarNombre.setFocusPainted(false);
		btnEliminarNombre.setContentAreaFilled(false);
		btnEliminarNombre.setBorderPainted(false);
		btnEliminarNombre.setBounds(547, 276, 33, 31);
		btnEliminarNombre.addActionListener(this);
		panel.add(btnEliminarNombre);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(PanelCargarAeopuertos.class.getResource("/recursos/aeropuerto1.png")));
		lblNewLabel_2.setBounds(191, 26, 231, 129);
		panel.add(lblNewLabel_2);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(PanelCargarAeopuertos.class.getResource("/recursos/buscar.png")));
		btnBuscar.setBounds(1100, 41, 45, 29);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.addActionListener(this);
		//btnBuscar.setBorderPainted(false);
		panel.add(btnBuscar);

	}

	// Metodo para completar la tabla de los aeropuertos
	public void completarTablaAeropuerto() {
		// remuevo todo lo que contengo en la tabla
		DefaultTableModel modelo = (DefaultTableModel) tableAeropuertos.getModel();
		int filas = tableAeropuertos.getRowCount();
		for (int i = 0; filas > i; i++) {
			modelo.removeRow(0);
		}
		List<AeropuertoVo> listaAeropuertos = coordinador.getLogicaAeropuerto().validarConsultaAeropuerto();
		Object[] fila = new Object[tableAeropuertos.getModel().getColumnCount()]; // filas con 2 columnas
		for (int i = 0; i < listaAeropuertos.size(); i++) {
			fila[0] = listaAeropuertos.get(i).getAbreviacion();
			fila[1] = listaAeropuertos.get(i).getNombre();

			((DefaultTableModel) tableAeropuertos.getModel()).addRow(fila);
		}

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
			AeropuertoVo aeropuertoVo = new AeropuertoVo();
			aeropuertoVo.setAbreviacion(textAbreviacion.getText());
			aeropuertoVo.setNombre(textNombre.getText());
			boolean verificacion = coordinador.getLogicaAeropuerto().validarRegistroAeropuerto(aeropuertoVo);
			if (verificacion) {
				// Si se registro se refresca la tabla
				completarTablaAeropuerto();
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
				AeropuertoVo aeropuertoVo = new AeropuertoVo();
				aeropuertoVo.setAbreviacion(textAbreviacion.getText());
				aeropuertoVo.setNombre(textNombre.getText());
				boolean verificacion = coordinador.getLogicaAeropuerto().validarModificacion(aeropuertoVo, abreviacion, nombre);
				if (verificacion) {
					// Si se registro se refresca la tabla
					completarTablaAeropuerto();
				}
				limpiar();
				
				//System.out.println(textAbreviacion.getText()+" "+textNombre.getText());
				
			} else {
				JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
			}		
			
		}
		if(e.getSource()==btnBuscar) {
					
			
		}

	}
}
