package controlador;

import modelo.LogicaAeropuerto;
import modelo.LogicaHilo;
import modelo.LogicaTipoBusqueda;
import modelo.LogicaVuelo;
import vista.PanelBusquedaEconomico;
import vista.PanelBusquedaMenosEscala;
import vista.PanelBusquedaMenosHoras;
import vista.PanelCargarAeropuertos;
import vista.PanelCargarVuelos;
import vista.PanelMenu;
import vista.VentanaMenu;

public class Aplicacion {

	private LogicaAeropuerto logicaAeropuerto;
	private LogicaVuelo logicaVuelo;
	private LogicaTipoBusqueda logicaTipoBusqueda;
	private VentanaMenu ventanaMenu;
	private PanelMenu panelMenu;
	private PanelCargarAeropuertos cargarAeropuertos;
	private PanelCargarVuelos cargarVuelos;
	private Coordinador coordinador;

	public static void main(String[] args) {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.iniciar();
	}

	/**
	 * Permite instanciar todas las clases con las que trabaja el sistema
	 */
	private void iniciar() {
		/* Se instancian las clases */
		coordinador = new Coordinador();
		ventanaMenu = new VentanaMenu();
		logicaAeropuerto = new LogicaAeropuerto();
		logicaVuelo = new LogicaVuelo();
		logicaTipoBusqueda = new LogicaTipoBusqueda();
		cargarAeropuertos = new PanelCargarAeropuertos();
		cargarVuelos = new PanelCargarVuelos();
		panelMenu = new PanelMenu();
	

		/* Se establecen las relaciones entre clases */
		ventanaMenu.setCoordinador(coordinador);
		cargarAeropuertos.setCoordinador(coordinador);
		cargarVuelos.setCoordinador(coordinador);
		panelMenu.setCoordinador(coordinador);
		logicaAeropuerto.setCoordinador(coordinador);
		logicaVuelo.setCoordinador(coordinador);
		logicaTipoBusqueda.setCoordinador(coordinador);

		/* Se establecen relaciones con la clase coordinador */
		coordinador.setVentanaMenu(ventanaMenu);
		coordinador.setCargarAeropuertos(cargarAeropuertos);
		coordinador.setCargarVuelos(cargarVuelos);
		coordinador.setPanelMenu(panelMenu);
		coordinador.setLogicaAeropuerto(logicaAeropuerto);
		coordinador.setLogicaVuelo(logicaVuelo);
		coordinador.setLogicaTipoBusqueda(logicaTipoBusqueda);

		// Muestra la aplicaion en pantalla
		ventanaMenu.iniciarComponentes();
		ventanaMenu.setVisible(true);

	}

}
