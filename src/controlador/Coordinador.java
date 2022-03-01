package controlador;

import modelo.LogicaAeropuerto;
import modelo.LogicaVuelo;
import vista.PanelCargarAeopuertos;
import vista.PanelCargarVuelos;
import vista.PanelMenu;
import vista.VentanaMenu;

public class Coordinador {
	
	private LogicaAeropuerto logicaAeropuerto;
	private LogicaVuelo logicaVuelo;
	private VentanaMenu ventanaMenu;
	private PanelCargarAeopuertos cargarAeropuertos;
	private PanelCargarVuelos cargarVuelos;
	private PanelMenu panelMenu;
	
	
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
	
	public PanelCargarAeopuertos getCargarAeropuertos() {
		return cargarAeropuertos;
	}
	public void setCargarAeropuertos(PanelCargarAeopuertos cargarAeropuertos) {
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
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public void mostrarVentanaMenu() {
		ventanaMenu.setVisible(true);
	}
	/*
	public boolean registrarAeropuerto(AeropuertoVo aeropuertoVo) {
		return logica.validarRegistroAeropuerto(aeropuertoVo);
		
	}
	
	public List<AeropuertoVo> obtenerAeropuertosAll() {				
		return logica.validarConsultaAeropuerto();
	}

	public boolean actualizarAeropuerto(AeropuertoVo aeropuertoVo, String abreviacion, String nombre) {
		return logica.validarModificacion(aeropuertoVo, abreviacion, nombre);
	}
	
	public boolean registrarVuelo(VueloVo vuelovo) {
		return logica.validarRegistroVuelo(vuelovo);
	}
	public List<VueloVo> obtenerVuelosAll() {
		return logica.validarConsultaVuelos();
	}
	*/
}
