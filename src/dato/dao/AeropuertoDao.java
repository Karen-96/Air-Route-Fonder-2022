package dato.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.Coordinador;
import dato.conexion.Conexion;
import dato.vo.AeropuertoVo;

/**
 * Clase que permite el acceso a la base de datos
 * 
 * @author Apablaza Karen
 *
 */

public class AeropuertoDao {
	Coordinador coordinador;

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;

	}

	public void registrarAeropuerto(AeropuertoVo aeropuerto) {
		Conexion conexion = new Conexion();
		PreparedStatement ps;
		String sql;
		Connection con = null;
		try {
			Statement estatuto = conexion.getConnection().createStatement();

			estatuto.executeUpdate("INSERT INTO aeropuerto(abreviacion,nombre) VALUES ('"
					+ aeropuerto.getAbreviacion().toUpperCase() + "', '" + aeropuerto.getNombre() + "')");
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se Registro los datos");
		}

	}
	
	

	public TreeMap<String, AeropuertoVo> obtenerAeropuertosAll() {
		TreeMap<String, AeropuertoVo> listaAeropuerto = new TreeMap<>();
		AeropuertoVo aeropuerto = null;
		Conexion conex = new Conexion();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM aeropuerto ");
			ResultSet rs = consulta.executeQuery();
			while (rs.next()) {
				aeropuerto = new AeropuertoVo();
				aeropuerto.setIdAeropuerto(Integer.parseInt(rs.getString("idaeropuerto")));
				aeropuerto.setAbreviacion(rs.getString("abreviacion"));
				aeropuerto.setNombre(rs.getString("nombre"));
				listaAeropuerto.put(aeropuerto.getAbreviacion(), aeropuerto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
		return listaAeropuerto;

	}
	
	
	

	public void eliminarAeropuerto(String abreviacion) {
		Conexion conex = new Conexion();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("DELETE FROM aeropuerto WHERE abreviacion='" + abreviacion + "'");
			JOptionPane.showMessageDialog(null, " Se ha Eliminado Correctamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se Elimino el Aeropuerto");
		}

	}

	public void modificarAeropuerto(AeropuertoVo aeropuerto, String abreviacion, String nombre) {
		Conexion conex = new Conexion();
		try {

			String consulta = "UPDATE aeropuerto SET  abreviacion= ?, nombre = ?  WHERE abreviacion= ? and nombre= ? ";
			PreparedStatement estatuto = conex.getConnection().prepareStatement(consulta);

			estatuto.setString(1, aeropuerto.getAbreviacion());
			estatuto.setString(2, aeropuerto.getNombre());
			estatuto.setString(3, abreviacion);
			estatuto.setString(4, nombre);
			estatuto.executeUpdate();
			JOptionPane.showMessageDialog(null, " Se ha Modificado Correctamente el Aeropuerto ", "Confirmación",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al Modificar el Aeropuerto", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public DefaultTableModel buscarAeropuerto(String buscar) {
		String [] nombresColumnas = {"Abreviacion" ,"Nombre"};
		String [] registros = new String[2];
		DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
		Conexion conex = new Conexion();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM aeropuerto WHERE abreviacion LIKE '"+buscar+"%' OR nombre LIKE '"+buscar+"%' ");
			ResultSet rs = consulta.executeQuery();
			while (rs.next()) {
				registros[0]= rs.getString("abreviacion");
				registros[1]=rs.getString("nombre");
				modelo.addRow(registros);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
		return modelo;	
		
	}
	
	public AeropuertoVo buscarAeropuertoAbreviacion(String abreviacion) {
		AeropuertoVo aeropuerto = null;
		Conexion conex = new Conexion();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM aeropuerto WHERE abreviacion='"+abreviacion+"'");
			ResultSet rs = consulta.executeQuery();
			while (rs.next()) {
				aeropuerto = new AeropuertoVo();
				aeropuerto.setIdAeropuerto(Integer.parseInt(rs.getString("idaeropuerto")));
				aeropuerto.setAbreviacion(rs.getString("abreviacion"));
				aeropuerto.setNombre(rs.getString("nombre"));			

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
		}
		return aeropuerto;
				
	}
	
	
	

}
