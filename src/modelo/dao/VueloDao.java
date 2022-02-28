package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.conexion.Conexion;
import modelo.vo.AeropuertoVo;
import modelo.vo.VueloVo;

/**
 * Clase que permite el acceso a la base de datos
 * 
 * @author Apablaza Karen
 *
 */

public class VueloDao {	

	public void registrarVuelo(VueloVo vuelo) {
		System.out.println(vuelo.getPrecio());
				
		//fecha que selecciona el usuario
		////Timestamp fecha = (Timestamp) vuelo.getFecha();
		
		Conexion conexion = new Conexion();
		PreparedStatement ps;
		String sql;
		Connection con = null;
		try {
			Statement estatuto = conexion.getConnection().createStatement();

			estatuto.executeUpdate("INSERT INTO vuelo(numero_vuelo,fecha,precio,aeropuerto_origen,aeropuerto_destino,tiempo_vuelo,demora) VALUES ('"
					+ vuelo.getNumero_vuelo() + "', '"	+ vuelo.getFecha() + "', '" + vuelo.getPrecio() + "', '"  + vuelo.getAeropuerto_origen()+ "', '" + vuelo.getAeropuerto_destino()+"', '" + vuelo.getTiempo_vuelo()+"', '" + vuelo.getDemora()+ "')");
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro los datos");
		}
		
	}

	public List<VueloVo> obtenerVuelosAll() {
		ArrayList<VueloVo> listaVuelos = new ArrayList<>();
		VueloVo vuelo = null;
		Conexion conex = new Conexion();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM vuelo ");
			ResultSet rs = consulta.executeQuery();
			while (rs.next()) {
				vuelo = new VueloVo();
				vuelo.setNumero_vuelo(rs.getString("numero_vuelo"));
				vuelo.setFecha(Timestamp.valueOf(rs.getString("fecha")));
				vuelo.setAeropuerto_origen(rs.getString("aeropuerto_origen"));
				vuelo.setAeropuerto_destino(rs.getString("aeropuerto_destino"));
				vuelo.setPrecio(Integer.parseInt(rs.getString("precio")));
				vuelo.setTiempo_vuelo(rs.getString("tiempo_vuelo"));
				vuelo.setDemora(rs.getString("demora"));
				listaVuelos.add(vuelo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
		}
		return listaVuelos;		
	}

	public void eliminarVuelo(String numero_vuelo) {
		Conexion conex= new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("DELETE FROM vuelo WHERE numero_vuelo='"+numero_vuelo+"'");
			  JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
				estatuto.close();
				conex.desconectar();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Elimino el Aeropuerto");
		}
		
		
	}
}
