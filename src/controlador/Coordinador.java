package controlador;

import dato.dao.AeropuertoDao;
import dato.dao.VueloDao;
import logica.LogicaAeropuerto;
import logica.LogicaHilo;
import logica.LogicaTipoBusqueda;
import logica.LogicaVuelo;
import vista.PanelBusquedaEconomico;
import vista.PanelCargarAeropuertos;
import vista.PanelCargarVuelos;
import vista.PanelMenu;
import vista.VentanaMenu;

public class Coordinador {

	private LogicaAeropuerto logicaAeropuerto;
	private LogicaVuelo logicaVuelo;
	private LogicaTipoBusqueda logicaTipoBusqueda;
	private LogicaHilo logicaHilo;
	private VentanaMenu ventanaMenu;
	private PanelCargarAeropuertos cargarAeropuertos;
	private PanelCargarVuelos cargarVuelos;
	private PanelMenu panelMenu;
	private VueloDao vueloDao;
	private AeropuertoDao aeropuertoDao;

	public LogicaAeropuerto getLogicaAeropuerto() {
		return logicaAeropuerto;
	}

	public void setLogicaAeropuerto(LogicaAeropuerto logicaAeropuerto) {
		this.logicaAeropuerto = logicaAeropuerto;
	}

	public LogicaVuelo getLogicaVuelo() {
		return logicaVuelo;
	}

	public void setLogicaVuelo(LogicaVuelo logicaVuelo) {
		this.logicaVuelo = logicaVuelo;
	}

	public VentanaMenu getVentanaMenu() {
		return ventanaMenu;
	}

	public void setVentanaMenu(VentanaMenu ventanaMenu) {
		this.ventanaMenu = ventanaMenu;
	}

	public PanelCargarAeropuertos getCargarAeropuertos() {
		return cargarAeropuertos;
	}

	public void setCargarAeropuertos(PanelCargarAeropuertos cargarAeropuertos) {
		this.cargarAeropuertos = cargarAeropuertos;
	}

	public PanelCargarVuelos getCargarVuelos() {
		return cargarVuelos;
	}

	public void setCargarVuelos(PanelCargarVuelos cargarVuelos) {
		this.cargarVuelos = cargarVuelos;
	}

	public PanelMenu getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(PanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}

	public LogicaTipoBusqueda getLogicaTipoBusqueda() {
		return logicaTipoBusqueda;
	}

	public void setLogicaTipoBusqueda(LogicaTipoBusqueda logicaTipoBusqueda) {
		this.logicaTipoBusqueda = logicaTipoBusqueda;
	}

	public LogicaHilo getLogicaHilo() {
		return logicaHilo;
	}

	public void setLogicaHilo(LogicaHilo logicaHilo) {
		this.logicaHilo = logicaHilo;
	}

	public VueloDao getVueloDao() {
		return vueloDao;
	}

	public void setVueloDao(VueloDao vueloDao) {
		this.vueloDao = vueloDao;
	}
	

	public AeropuertoDao getAeropuertoDao() {
		return aeropuertoDao;
	}

	public void setAeropuertoDao(AeropuertoDao aeropuertoDao) {
		this.aeropuertoDao = aeropuertoDao;
	}	
	
	
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////


	public void mostrarVentanaMenu() {
		ventanaMenu.setVisible(true);
	}

}
