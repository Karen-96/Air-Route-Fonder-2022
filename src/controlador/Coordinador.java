package controlador;

import modelo.LogicaAeropuerto;
import modelo.LogicaHilo;
import modelo.LogicaTipoBusqueda;
import modelo.LogicaVuelo;
import vista.PanelCargarAeropuertos;
import vista.PanelCargarVuelos;
import vista.PanelMenu;
import vista.PanelSeleccionTipoBusqueda;
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
	private PanelSeleccionTipoBusqueda infoBusqueda;
	
	
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
	

	public PanelSeleccionTipoBusqueda getInfoBusqueda() {
		return infoBusqueda;
	}
	public void setInfoBusqueda(PanelSeleccionTipoBusqueda infoBusqueda) {
		this.infoBusqueda = infoBusqueda;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
		
	
	
	public void mostrarVentanaMenu() {
		ventanaMenu.setVisible(true);
	}
	
}
