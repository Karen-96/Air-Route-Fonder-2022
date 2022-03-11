package dato.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;


/**
 * Clase que permite conectar con la base de datos
 * @author Apablaza Karen
 *
 */

public class Conexion {
	private static Connection con = null;

	public static Connection getConnection() {
		try {
			if (con == null) {
				// con esto determinamos cuando finalize el programa
				Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
				ResourceBundle rb = ResourceBundle.getBundle("dato.conexion.configuracion");
				String driver = rb.getString("DRIVER");
				String url = rb.getString("jdbc");
				String pwd = rb.getString("CLAVE");
				String usr = rb.getString("USUARIO");
				Class.forName(driver);
				con = DriverManager.getConnection(url, usr, pwd);				
			}
			return con;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error al crear la conexion", ex);
		}
	}

	static class MiShDwnHook extends Thread {
		// justo antes de finalizar el programa la JVM invocara
		// a este metodo donde podemos cerrar la conexion
		public void run() {
			try {
				Connection con = Conexion.getConnection();
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}           	
}
