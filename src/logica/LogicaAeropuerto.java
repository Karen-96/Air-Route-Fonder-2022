package logica;

import java.util.List;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import controlador.Coordinador;
import dato.dao.AeropuertoDao;
import dato.vo.AeropuertoVo;

public class LogicaAeropuerto {
	private String mensaje = "";
	private Coordinador coordinador;

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

	public String validarRegistroAeropuerto(AeropuertoVo aeropuerto) {
		
		if (aeropuerto.getAbreviacion().equals("") || aeropuerto.getNombre().equals("")) {
			mensaje = "Por favor ingrese abreviacion y nombre";
		} else if (aeropuerto.getAbreviacion().length() == 3) {
			
		} else {
			mensaje = "La Abreviación tiene que ser 3 palabras";
		}
		AeropuertoVo ae = coordinador.getAeropuertoDao().buscarAeropuertoAbreviacion(aeropuerto.getAbreviacion());
		if(ae != null) {
			mensaje = "El aeropuerto ya existe";
		}
			
		
		return mensaje;
	}

	public TreeMap<String, AeropuertoVo> validarConsultaAeropuerto() {
		return coordinador.getAeropuertoDao().obtenerAeropuertosAll();
	}

	public void validarEliminacion(String abreviacion) {
		coordinador.getAeropuertoDao().eliminarAeropuerto(abreviacion);
	}

	public String validarModificacion(AeropuertoVo aeropuerto, String abreviacion, String nombre) {
		if (aeropuerto.getAbreviacion().equals("") || aeropuerto.getNombre().equals("")) {
			mensaje = "Por favor ingrese abreviacion y nombre";
		} else if (aeropuerto.getAbreviacion().length() == 3) {
			coordinador.getAeropuertoDao().modificarAeropuerto(aeropuerto, abreviacion, nombre);

		} else {
			mensaje = "La Abreviación tiene que ser 3 palabras";
		}
		return mensaje;
	}
}
