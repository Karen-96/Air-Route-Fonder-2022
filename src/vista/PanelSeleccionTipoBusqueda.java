package vista;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PanelSeleccionTipoBusqueda extends JPanel {
	private Coordinador coordinador = new Coordinador(); // objeto miCoordinador que permite la relacion entre esta
	private JLabel lblFondo;
														// clase y la clase coordinador

	/**
	 * Create the panel.
	 */
	public PanelSeleccionTipoBusqueda() {
		iniciarComponentes();
	}
	
	public void iniciarComponentes() {
		setBounds(0, 0, 1270, 717);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(PanelSeleccionTipoBusqueda.class.getResource("/recursos/fondo2.jpg")));
		lblFondo.setBounds(0, 0, 1270, 717);
		add(lblFondo);
	}
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
}
