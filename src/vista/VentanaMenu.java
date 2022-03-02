package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Coordinador;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

public class VentanaMenu extends JFrame{
	private Coordinador coordinador; //objeto miCoordinador que permite la relacion entre esta clase y la clase coordinador
	private JPanel panelMenu;
	
	//Ver esto
	private PanelCargarAeropuertos cargarAeropuertos;
	private PanelCargarVuelos cargarVuelos;

	
	/**
	 * Establece la informacion que se presentara como introduccion del sistema
	 */
	public String textoIntroduccion = "";
	
	
	public VentanaMenu() {
		
		
	}
	
	public void iniciarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1270, 717);
		setTitle("Air Route Finder");
		panelMenu = new JPanel();
		panelMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMenu.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false); //No se puede maximinizar
		setContentPane(panelMenu);
		
		panelMenu.removeAll();
		panelMenu.add(coordinador.getPanelMenu());
		panelMenu.revalidate();
		panelMenu.repaint();
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
	



	public JPanel getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(JPanel panelMenu) {
		this.panelMenu = panelMenu;
	}
	
}
