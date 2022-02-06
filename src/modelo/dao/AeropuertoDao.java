package modelo.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import modelo.conexion.Conexion;
import modelo.vo.AeropuertoVo;

/**
 * Clase que permite el acceso a la base de datos
 * @author Apablaza Karen
 *
 */

public class AeropuertoDao {
	
	public void registrarAeropuerto(AeropuertoVo aeropuerto) {
		Conexion conexion = new Conexion();
		PreparedStatement ps;
		String sql;
		Connection con = null;
		try {
			Statement estatuto = conexion.getConnection().createStatement();
			
			sql="insert into aeropuerto(abreviacion,nombre) values(?,?)";
			ps= con.prepareStatement(sql);
			ps.setString(1,aeropuerto.getAbreviacion());
			ps.setString(2,aeropuerto.getNombre());
			ps.executeUpdate();			
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conexion.desconectar();
			
		} catch (SQLException e) {
			 System.out.println(e.getMessage());
			 JOptionPane.showMessageDialog(null, "No se Registro");
		}
		
	}
	

}
