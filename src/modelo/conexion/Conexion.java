package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite conectar con la base de datos
 * @author Apablaza Karen
 *
 */

public class Conexion {
	static final String bd = "aerolinea_argentina";
	static final String login = "root";
	static final String password = "123456789";
	static final String url = "jdbc:mysql://localhost:3305/aerolinea_argentina";
	Connection conn = null;
	
	/** Constructor de DbConnection */
	public Conexion() {
	      try{
	         //obtenemos el driver de para mysql
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         //obtenemos la conexión
	         conn = (Connection) DriverManager.getConnection(url,login,password);

	         if (conn!=null){
	            System.out.println("Conección a base de datos "+bd+" OK");
	         }
	      }
	      catch(SQLException e){
	         System.out.println(e);
	      }catch(ClassNotFoundException e){
	         System.out.println(e);
	      }catch(Exception e){
	         System.out.println(e);
	      }
	   }
	
	 /**Permite retornar la conexión*/
	 public Connection getConnection(){
	      return conn;
	 }

	 public void desconectar(){
	      conn = null;
	 }
	
}
