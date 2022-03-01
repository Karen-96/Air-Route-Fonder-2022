package modelo;

import java.util.List;

import javax.swing.JOptionPane;

import controlador.Coordinador;
import modelo.dao.AeropuertoDao;
import modelo.vo.AeropuertoVo;

public class LogicaAeropuerto {
	Coordinador coordinador;
	AeropuertoDao aeropuertoDao;
	
	public LogicaAeropuerto() {
		//Borrar y reemplazar por coordinador
		aeropuertoDao = new AeropuertoDao();
	}
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;		
	}
	
	public boolean validarRegistroAeropuerto(AeropuertoVo aeropuerto) {		
		boolean verificacion = true;	
		if(aeropuerto.getAbreviacion().equals("") || aeropuerto.getNombre().equals("")) {
			JOptionPane.showMessageDialog(null,"Por favor ingrese abreviacion y nombre","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;
			
		}else if(aeropuerto.getAbreviacion().length() == 3){
			aeropuertoDao.registrarAeropuerto(aeropuerto);
			
		}else {
			JOptionPane.showMessageDialog(null,"La Abreviación tiene que ser 3 palabras","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;			
		}		
		return verificacion;		
	}
	
	public List<AeropuertoVo> validarConsultaAeropuerto() {
		return aeropuertoDao.obtenerAeropuertosAll();		
	}

	public void validarEliminacion(String abreviacion) {
		aeropuertoDao.eliminarAeropuerto(abreviacion);
	}

	public boolean validarModificacion(AeropuertoVo aeropuerto, String abreviacion, String nombre) {
		boolean verificacion = true;
		if(aeropuerto.getAbreviacion().equals("") || aeropuerto.getNombre().equals("")) {
			JOptionPane.showMessageDialog(null,"Por favor ingrese abreviacion y nombre","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;
			
		}else if(aeropuerto.getAbreviacion().length() == 3){
			aeropuertoDao.modificarAeropuerto(aeropuerto,abreviacion,nombre);
			
		}else {
			JOptionPane.showMessageDialog(null,"La Abreviación tiene que ser 3 palabras","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;			
		}		
		return verificacion;	
	}


}
