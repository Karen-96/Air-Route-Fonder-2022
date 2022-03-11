package dato.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;
import dato.conexion.Conexion;
import dato.vo.AeropuertoVo;
import dato.vo.VueloVo;

/**
 * Clase que permite el acceso a la base de datos
 * 
 * @author Apablaza Karen
 *
 */

public class VueloDao {
	TreeMap<String,AeropuertoVo> listaAeropuertos;
	Coordinador coordinador;
		

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
	
	public void registrarVuelo(VueloVo vuelo) {
		Conexion conexion = new Conexion();
		PreparedStatement ps;
		String sql;
		Connection con = null;
		con = Conexion.getConnection();
		try {
			Statement estatuto = conexion.getConnection().createStatement();

			estatuto.executeUpdate(
					"INSERT INTO vuelo(numero_vuelo,fecha,precio,aeropuerto_origen,aeropuerto_destino,tiempo_vuelo,demora) VALUES ('"
							+ vuelo.getNumero_vuelo() + "', '" + vuelo.getFecha() + "', '" + vuelo.getPrecio() + "', '"
							+ vuelo.getAeropuerto_origen() + "', '" + vuelo.getAeropuerto_destino() + "', '"
							+ vuelo.getTiempo_vuelo() + "', '" + vuelo.getDemora() + "')");
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();

		} catch (SQLException e) {
		}

	}

	public List<VueloVo> obtenerVuelosAll() {
	    listaAeropuertos = coordinador.getAeropuertoDao().obtenerAeropuertosAll();
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
				vuelo.setAeropuerto_origen(listaAeropuertos.get(rs.getString("aeropuerto_origen")));
				vuelo.setAeropuerto_destino(listaAeropuertos.get(rs.getString("aeropuerto_destino")));
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
		Conexion conex = new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("DELETE FROM vuelo WHERE numero_vuelo='" + numero_vuelo + "'");
			JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se Elimino el Aeropuerto");
		}

	}

	public void modificarVuelo(VueloVo vuelo, String numero_vuelo) {
		Conexion conex = new Conexion();
		try {

			String consulta = "UPDATE vuelo SET  numero_vuelo= ?, fecha = ?, precio = ?, aeropuerto_origen = ?, aeropuerto_destino = ?, tiempo_vuelo = ?, demora = ?  "
					+ "WHERE numero_vuelo= ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);
			estatuto.setString(1, vuelo.getNumero_vuelo());
			estatuto.setTimestamp(2, vuelo.getFecha());
			estatuto.setInt(3, vuelo.getPrecio());
			estatuto.setString(4,vuelo.getAeropuerto_origen().getAbreviacion());
			estatuto.setString(5,vuelo.getAeropuerto_destino().getAbreviacion());
			estatuto.setString(6,vuelo.getTiempo_vuelo());
			estatuto.setString(7, vuelo.getDemora());
			estatuto.setString(8, numero_vuelo);
			
			estatuto.executeUpdate();

			JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente el Aeropuerto ", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al Modificar el Aeropuerto", "Error", JOptionPane.ERROR_MESSAGE);
			e.getStackTrace();
		}

	}

	//Metodo donde busca aeropuertos dependiendo de la fecha que eligio el usuario
	public List<VueloVo> obtenerVuelosAllFecha(String fecha) {
		listaAeropuertos = coordinador.getAeropuertoDao().obtenerAeropuertosAll();
		ArrayList<VueloVo> listaVuelos = new ArrayList<>();
		VueloVo vuelo = null;
		Conexion conex = new Conexion();

		try {
			
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM vuelo where fecha LIKE '"+fecha+"%'");
			
			System.out.println(fecha);
			ResultSet rs = consulta.executeQuery();
			while (rs.next()) {
				vuelo = new VueloVo();
				vuelo.setNumero_vuelo(rs.getString("numero_vuelo"));
				vuelo.setFecha(Timestamp.valueOf(rs.getString("fecha")));
				vuelo.setAeropuerto_origen(listaAeropuertos.get(rs.getString("aeropuerto_origen")));
				vuelo.setAeropuerto_destino(listaAeropuertos.get(rs.getString("aeropuerto_destino")));
				vuelo.setPrecio(Integer.parseInt(rs.getString("precio")));
				vuelo.setTiempo_vuelo(rs.getString("tiempo_vuelo"));
				vuelo.setDemora(rs.getString("demora"));
				listaVuelos.add(vuelo);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
		return listaVuelos;
	}

	public DefaultTableModel buscarVuelo(String buscar) {
		String [] nombresColumnas = {"N° Vuelo" ,"Fecha y Hora Salida", "Aeropuerto Origen" , "Aeropuerto Destino", "Precio", "Tiempo Vuelo", "Demora"};
		String [] registros = new String[7];
		DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
		Conexion conex = new Conexion();
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM vuelo WHERE numero_vuelo LIKE '"+buscar+"%' OR aeropuerto_origen LIKE '"+buscar+"%' OR fecha LIKE '"+buscar+"%'"
					+ "OR aeropuerto_destino LIKE '"+buscar+"' ");
			ResultSet rs = consulta.executeQuery();
			while (rs.next()) {
				registros[0]= rs.getString("numero_vuelo");
				registros[1]=(rs.getString("fecha"));
				registros[2]=rs.getString("aeropuerto_origen");
				registros[3]=rs.getString("aeropuerto_destino");
				registros[4]=rs.getString("precio");
				registros[5]=rs.getString("tiempo_vuelo");
				registros[6]=rs.getString("demora");
				modelo.addRow(registros);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
		return modelo;	
	}
	
	public VueloVo buscarAeropuertoNumeroVuelo(String numero_vuelo) {
		VueloVo vuelo = null;
		Conexion conex = new Conexion();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM vuelo WHERE numero_vuelo='"+numero_vuelo+"'");
			ResultSet rs = consulta.executeQuery();
			while (rs.next()) {
				vuelo = new VueloVo();
				vuelo.setNumero_vuelo(rs.getString("numero_vuelo"));
				vuelo.setFecha(Timestamp.valueOf(rs.getString("fecha")));
				vuelo.setAeropuerto_origen(listaAeropuertos.get(rs.getString("aeropuerto_origen")));
				vuelo.setAeropuerto_destino(listaAeropuertos.get(rs.getString("aeropuerto_destino")));
				vuelo.setPrecio(Integer.parseInt(rs.getString("precio")));
				vuelo.setTiempo_vuelo(rs.getString("tiempo_vuelo"));
				vuelo.setDemora(rs.getString("demora"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
		return vuelo;
				
	}
	
}
