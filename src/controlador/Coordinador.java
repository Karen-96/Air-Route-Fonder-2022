package controlador;

import modelo.Logica;
import modelo.vo.AeropuertoVo;
import vista.PanelCargarAeopuertos;
import vista.PanelCargarVuelos;
import vista.VentanaMenu;

public class Coordinador {
	
	private Logica logica;
	private VentanaMenu ventanaMenu;
	private PanelCargarAeopuertos cargarAeropuertos;
	private PanelCargarVuelos cargarVuelos;
	
	
	public Logica getLogica() {
		return logica;
	}
	public void setLogica(Logica logica) {
		this.logica = logica;
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
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void mostrarPanelCargaDatos() {
		
		
		
	}
	public void registrarAeropuerto(AeropuertoVo aeropuertoVo) {
		System.out.println(aeropuertoVo);
		//logica.validarRegistroAeropuerto(aeropuertoVo);
		
	}
	

}
