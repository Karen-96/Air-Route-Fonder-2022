package logica;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import controlador.Coordinador;
import dato.dao.VueloDao;
import dato.vo.AeropuertoVo;
import dato.vo.VueloVo;

public class LogicaVuelo {
	private String mensaje = "";
	private Coordinador coordinador;
	private Date date;
	
	public LogicaVuelo() {
		date  = new Date();		
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;		
	}
	
	//Metodo donde valido antes de registrar el vuelo
	public String validarRegistroVuelo(VueloVo vuelo) {
		if(vuelo.getAeropuerto_origen().getAbreviacion().equals("") || vuelo.getAeropuerto_destino().getAbreviacion().equals("") || vuelo.getFecha().equals(null) || vuelo.getTiempo_vuelo().equals("") || vuelo.getDemora().equals("") || vuelo.getNumero_vuelo().equals("")) {
			mensaje = "Por favor ingrese los datos";
		}else if(vuelo.getFecha().before(date)) {
			mensaje = "La fecha seleccionada debe ser igual o mayor a la fecha actual";
		}else if(vuelo.getAeropuerto_origen().equals(vuelo.getAeropuerto_destino())) {
			mensaje = "El Aeropuerto Origen debe ser distinto al Aeropuerto Destino";		
		}else if(vuelo.getPrecio()<=0){
			mensaje = "El precio debe ser mayor a 0";		
		}
		
		VueloVo vue = coordinador.getVueloDao().buscarAeropuertoNumeroVuelo(vuelo.getNumero_vuelo());
		if(vue != null) {
			mensaje = "El N° de Vuelo ya existe";
		}
			
		return mensaje;
	}

	//Devuelve todos los vuelos de la base de datos
	public List<VueloVo> validarConsultaVuelos() {
		return coordinador.getVueloDao().obtenerVuelosAll();
	}

	//Metodo donde valida la eliminacion del vuelo
	public void validarEliminacionTablaVuelo(String numero_vuelo) {
		coordinador.getVueloDao().eliminarVuelo(numero_vuelo);
		
	}
	
	//Metodo donde valida antes de modificar un vuelo
	public String validarModificacion(VueloVo vuelo,String numero_viejo) {
		if(vuelo.getAeropuerto_origen().equals("") || vuelo.getAeropuerto_destino().equals("") || vuelo.getFecha().equals(null) || vuelo.getTiempo_vuelo().equals("") || vuelo.getDemora().equals("") || vuelo.getNumero_vuelo().equals("")) {
			mensaje = "Por favor ingrese los datos";
		}else if(vuelo.getPrecio()<=0){
			mensaje = "El precio debe ser mayor a 0";			
		}else if(vuelo.getFecha().before(date)) {
			mensaje = "La fecha seleccionada debe ser igual o mayor a la fecha actual";
		}else if(vuelo.getAeropuerto_origen().equals(vuelo.getAeropuerto_destino())) {
			mensaje = "El Aeropuerto Origen debe ser distinto al Aeropuerto Destino";			
		}else {
			coordinador.getVueloDao().modificarVuelo(vuelo,numero_viejo);
		}		
		return mensaje;
	}

	public List<VueloVo> validarConsultaVuelosFecha(String fecha) {
		return coordinador.getVueloDao().obtenerVuelosAllFecha(fecha);		
	}
	

}
