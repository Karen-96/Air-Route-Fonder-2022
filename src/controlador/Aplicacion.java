package controlador;

import modelo.Logica;
import vista.PanelCargarAeopuertos;
import vista.PanelCargarVuelos;
import vista.VentanaMenu;

public class Aplicacion {
	
	private Logica logica;
	private VentanaMenu ventanaMenu;
	private PanelCargarAeopuertos cargarAeropuertos;
	private PanelCargarVuelos cargarVuelos;	
	private Coordinador coordinador;
	
	public static void main(String[] args) {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.iniciar();
	}
	
	/**
	 * Permite instanciar todas las clases con las que trabaja
	 * el sistema
	 */
	private void iniciar() {
		/*Se instancian las clases*/
		coordinador = new Coordinador();
		ventanaMenu = new VentanaMenu();
		logica= new Logica();
		cargarAeropuertos = new PanelCargarAeopuertos();
		cargarVuelos = new PanelCargarVuelos();
		
		/*Se establecen las relaciones entre clases*/
		ventanaMenu.setCoordinador(coordinador);
		cargarAeropuertos.setCoordinador(coordinador);
		cargarVuelos.setCoordinador(coordinador);
		logica.setCoordinador(coordinador);
		
		
		/*Se establecen relaciones con la clase coordinador*/
		coordinador.setVentanaMenu(ventanaMenu);
		coordinador.setLogica(logica);
		coordinador.setCargarAeropuertos(cargarAeropuertos);
		coordinador.setCargarVuelos(cargarVuelos);
		coordinador.setLogica(logica);
		
		
		
		ventanaMenu.setVisible(true);
		
		
	}
	

}
