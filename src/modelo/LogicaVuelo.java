package modelo;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import controlador.Coordinador;
import modelo.dao.VueloDao;
import modelo.vo.VueloVo;

public class LogicaVuelo {
	Coordinador coordinador;
	VueloDao vueloDao;
	Date date;
	
	public LogicaVuelo() {
		//Borrar y reemplazar por coordinador
		vueloDao = new VueloDao();
		date  = new Date();		
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;		
	}
	
	//Metodo donde valido antes de registrar el vuelo
	public boolean validarRegistroVuelo(VueloVo vuelo) {
		//Fecha actual
		boolean verificacion = true;
		System.out.println(vuelo.getAeropuerto_origen().toString()+" "+vuelo.getAeropuerto_destino());
		if(vuelo.getAeropuerto_origen().equals("") || vuelo.getAeropuerto_destino().equals("") || vuelo.getFecha().equals(null) || vuelo.getTiempo_vuelo().equals("") || vuelo.getDemora().equals("") || vuelo.getNumero_vuelo().equals("")) {
			JOptionPane.showMessageDialog(null,"Por favor ingrese los datos","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;
			
		}else if(vuelo.getFecha().before(date)) {
			JOptionPane.showMessageDialog(null,"La fecha seleccionada debe ser igual o mayor a la fecha actual","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;
		}else if(vuelo.getAeropuerto_origen().equals(vuelo.getAeropuerto_destino())) {
			JOptionPane.showMessageDialog(null,"El Aeropuerto Origen debe ser distinto al Aeropuerto Destino","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;			
		}else if(vuelo.getPrecio()<=0){
			JOptionPane.showMessageDialog(null,"El precio debe ser mayor a 0","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;	
			
		}else {
			vueloDao.registrarVuelo(vuelo);
		}
		return verificacion;
	}

	//Devuelve todos los vuelos de la base de datos
	public List<VueloVo> validarConsultaVuelos() {
		return vueloDao.obtenerVuelosAll();
	}

	//metodo donde valida la eliminacion del vuelo
	public void validarEliminacionTablaVuelo(String numero_vuelo) {
		vueloDao.eliminarVuelo(numero_vuelo);
		
	}
	
	//Metodo donde valida antes de modificar un vuelo
	public boolean validarModificacion(VueloVo vuelo,String numero_viejo) {
		boolean verificacion = true;
		if(vuelo.getAeropuerto_origen().equals("") || vuelo.getAeropuerto_destino().equals("") || vuelo.getFecha().equals(null) || vuelo.getTiempo_vuelo().equals("") || vuelo.getDemora().equals("") || vuelo.getNumero_vuelo().equals("")) {
			JOptionPane.showMessageDialog(null,"Por favor ingrese los datos","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;
		}else if(vuelo.getPrecio()<=0){
			JOptionPane.showMessageDialog(null,"El precio debe ser mayor a 0","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;	
			
		}else if(vuelo.getFecha().before(date)) {
			JOptionPane.showMessageDialog(null,"La fecha seleccionada debe ser igual o mayor a la fecha actual","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;
		}else if(vuelo.getAeropuerto_origen().equals(vuelo.getAeropuerto_destino())) {
			JOptionPane.showMessageDialog(null,"El Aeropuerto Origen debe ser distinto al Aeropuerto Destino","Advertencia",JOptionPane.WARNING_MESSAGE);
			verificacion = false;			
		}else {
			vueloDao.modificarVuelo(vuelo,numero_viejo);
		}
		
		return verificacion;
	}

	public List<VueloVo> validarConsultaVuelosFecha(String fecha) {
		return vueloDao.obtenerVuelosAllFecha(fecha);		
	}
	

}
