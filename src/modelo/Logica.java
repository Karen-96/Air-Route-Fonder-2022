package modelo;

import javax.swing.JOptionPane;

import controlador.Coordinador;
import modelo.dao.AeropuertoDao;
import modelo.vo.AeropuertoVo;

public class Logica {
	private Coordinador coordinador;
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
		
	}

	public void validarRegistroAeropuerto(AeropuertoVo aeropuerto) {
		AeropuertoDao aeropuertoDao;
		if(aeropuerto.getAbreviacion().length() <= 3) {
			aeropuertoDao = new AeropuertoDao();
			aeropuertoDao.registrarAeropuerto(aeropuerto);
		}else {
			JOptionPane.showMessageDialog(null,"La Abreviación tiene que ser 3 palabras","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
		
	}

	

}
