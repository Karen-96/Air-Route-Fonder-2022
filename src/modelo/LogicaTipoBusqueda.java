package modelo;

import javax.swing.JOptionPane;

import controlador.Coordinador;
import modelo.vo.VueloVo;

public class LogicaTipoBusqueda {
	Coordinador coordinador;
	
	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;		
	}
	
	//valida que los campos no esten vacios	
	public void validarTipoDeBusqueda(VueloVo vueloVo, int itemTipoBusqueda) {
		if(vueloVo.getAeropuerto_origen() == "Seleccione" || vueloVo.getAeropuerto_destino() == "Seleccione" || itemTipoBusqueda == -1) {
			JOptionPane.showMessageDialog(null,"Por favor Complete los datos","Advertencia",JOptionPane.WARNING_MESSAGE);
			
		}
		else if(vueloVo.getAeropuerto_origen().equals(vueloVo.getAeropuerto_destino())) {
			JOptionPane.showMessageDialog(null,"El Aeropuerto Origen debe ser distinto al Aeropuerto Destino","Advertencia",JOptionPane.WARNING_MESSAGE);
			
		}else if(itemTipoBusqueda == -1) {
			JOptionPane.showMessageDialog(null,"Seleccione un tipo de busqueda","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	
	/**
	 * Este método calcula el camino mas corto segun la opcion que eligio el usuario.
	 * @param g		recibe el grafo principal.
	 * @param orig		recibe el aeropuerto origen que el usuario eligio.
	 * @param dest		recibe el aeropuerto destino  que el usuario eligio.
	 * @param busqueda		recibe el tipo de busqueda que el usuario eligio.
	 * @return PositionalList<Vertex<String>>		contiene los nodos que hacen el
	 * camino mas corto.
	 */
	
	

}
