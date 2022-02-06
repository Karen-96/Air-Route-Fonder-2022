package vista;

import javax.swing.JPanel;

import controlador.Coordinador;
import modelo.vo.AeropuertoVo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelCargarAeopuertos extends JPanel implements ActionListener {
	
	 Coordinador coordinador; //objeto miCoordinador que permite la relacion entre esta clase y la clase coordinador
	private JLabel lblFondoCargarAeropuerto;
	private JLabel lblNombre;
	private JLabel lblAbreviacion;
	private JTextField textAbreviacion;
	private JTextField textNombre;
	private JButton btnCancelar;
	private JButton btnGuardar;

	/**
	 * Create the panel.
	 */
	public PanelCargarAeopuertos() {
		setBounds(0, 0, 1145, 678);
		setLayout(null);
		
		this.coordinador = coordinador;
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGuardar.setBounds(334, 230, 89, 23);
		//add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(191, 230, 89, 23);
		//add(btnCancelar);
		
		textNombre = new JTextField();
		textNombre.setBounds(185, 154, 334, 20);
		add(textNombre);
		textNombre.setColumns(10);
		
		textAbreviacion = new JTextField();
		textAbreviacion.setBounds(185, 91, 334, 20);
		add(textAbreviacion);
		textAbreviacion.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombre.setBounds(61, 148, 100, 25);
		add(lblNombre);
		
		lblAbreviacion = new JLabel("Abreviacion:\r\n");
		lblAbreviacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAbreviacion.setBounds(61, 85, 114, 25);
		add(lblAbreviacion);
		
		//Fondo de imagen de Cargar Aeropuerto
		lblFondoCargarAeropuerto = new JLabel("");
		lblFondoCargarAeropuerto.setIcon(new ImageIcon(PanelCargarAeopuertos.class.getResource("/recursos/ImagenFondo1.jpg")));
		lblFondoCargarAeropuerto.setBounds(0, 0, 1255, 678);
		add(lblFondoCargarAeropuerto);
		
		btnGuardar.addActionListener(this);
		btnCancelar.addActionListener(this);
		add(btnGuardar);
		add(btnCancelar);
		limpiar();
		

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
		if(e.getSource()==btnGuardar) {
			try {
				AeropuertoVo aeropuertoVo = new AeropuertoVo();
				aeropuertoVo.setAbreviacion(textAbreviacion.getText());
				aeropuertoVo.setNombre(textNombre.getText());	
				System.out.println(aeropuertoVo);
				coordinador.registrarAeropuerto(aeropuertoVo);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,"Error en el Ingreso de Datos","Error",JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
		}
		if(e.getSource()==btnCancelar) {
			//this.dispose();
		}
		
		
	}
	
	
}
