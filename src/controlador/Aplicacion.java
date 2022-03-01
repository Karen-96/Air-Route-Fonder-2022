package controlador;

import modelo.LogicaAeropuerto;
import modelo.LogicaVuelo;
import vista.PanelCargarAeopuertos;
import vista.PanelCargarVuelos;
import vista.PanelMenu;
import vista.VentanaMenu;

public class Aplicacion {
	
	private LogicaAeropuerto logicaAeropuerto;
	private LogicaVuelo logicaVuelo;
	private VentanaMenu ventanaMenu;
	private PanelMenu panelMenu;
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
		logicaAeropuerto= new LogicaAeropuerto();
		logicaVuelo = new LogicaVuelo();
		cargarAeropuertos = new PanelCargarAeopuertos();
		cargarVuelos = new PanelCargarVuelos();
		panelMenu = new PanelMenu();
		
		/*Se establecen las relaciones entre clases*/
		ventanaMenu.setCoordinador(coordinador);
		cargarAeropuertos.setCoordinador(coordinador);
		cargarVuelos.setCoordinador(coordinador);
		panelMenu.setCoordinador(coordinador);
		logicaAeropuerto.setCoordinador(coordinador);
		logicaVuelo.setCoordinador(coordinador);
		
		
		/*Se establecen relaciones con la clase coordinador*/
		coordinador.setVentanaMenu(ventanaMenu);
		coordinador.setCargarAeropuertos(cargarAeropuertos);
		coordinador.setCargarVuelos(cargarVuelos);
		coordinador.setPanelMenu(panelMenu);
		coordinador.setLogicaAeropuerto(logicaAeropuerto);
		coordinador.setLogicaVuelo(logicaVuelo);
		
		
		
		ventanaMenu.setVisible(true);
		
		
	}
	

}
