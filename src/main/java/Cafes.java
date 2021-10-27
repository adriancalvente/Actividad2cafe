import java.io.IOException;
import java.sql.*;


/**
 * @descrition
 * @author Carlos
 * @date 23/10/2021
 * @version 1.0
 * @license GPLv3
 */

public class Cafes {

	// Consultas a realizar en BD
	private static final String SELECT_CAFES_QUERY = "select CAF_NOMBRE, PROV_ID, PRECIO, VENTAS, TOTAL from CAFES";

	/**
	 * Metodo que muestra por pantalla los datos de la tabla cafes
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public void verTabla() {
		/* Conexi�n a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		// Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		// ResultSet rs = null;
		try {
			con = new Utilidades().getConnection();
			// Creaci�n de la sentencia

			try (Statement stmt = con.createStatement()) {
				// Ejecuci�n de la consulta y obtenci�n de resultados en un ResultSet
				try (ResultSet rs = stmt.executeQuery(SELECT_CAFES_QUERY)) {

					// Recuperaci�n de los datos del ResultSet
					while (rs.next()) {
						String coffeeName = rs.getString("CAF_NOMBRE");
						int supplierID = rs.getInt("PROV_ID");
						float PRECIO = rs.getFloat("PRECIO");
						int VENTAS = rs.getInt("VENTAS");
						int total = rs.getInt("TOTAL");
						System.out
								.println(coffeeName + ", " + supplierID + ", " + PRECIO + ", " + VENTAS + ", " + total);
					}
				}
			} catch (SQLException sqle) {
				// En una aplicaci�n real, escribo en el log y delego
				System.err.println(sqle.getMessage());
			}

		} catch (SQLException sqle) {
			// En una aplicaci�n real, escribo en el log y delego
			System.err.println(sqle.getMessage());

		} catch (IOException e) {
			// Error al leer propiedades
			// En una aplicaci�n real, escribo en el log y delego
			System.err.println(e.getMessage());

		}

		finally {

			// Liberamos todos los recursos pase lo que pase
			/*
			 * if (rs != null) { rs.close(); } if (stmt != null) { stmt.close(); }
			 */
			if (con != null) {
				Utilidades.closeConnection(con);
			}

		}

	}

	/**
	 * M�todo que busca un cafe por nombre y muestra sus datos
	 *
	 * @param nombre
	 */
	public void buscar(String nombre) {
		try {
			Utilidades u1=new Utilidades();
			Connection conexion=u1.getConnection();
			Statement st=conexion.createStatement();
		ResultSet rs=st.executeQuery(SELECT_CAFES_QUERY+" where CAF_NOMBRE = '"+nombre+"'");

			while (rs.next()) {
				System.out.println("nombre= "+rs.getString(1));
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * M�todo para insertar una fila
	 * 
	 * @param nombre
	 * @param provid
	 * @param precio
	 * @param ventas
	 * @param total
	 * @return
	 */
	public void insertar(String nombre, int provid, float precio, int ventas, int total) {
		try {
			Utilidades u1=new Utilidades();
			Connection conexion=u1.getConnection();
			PreparedStatement ps=conexion.prepareStatement("Insert into cafes values (?,?,?,?,?)");
			ps.setString(1,nombre);
			ps.setInt(2,provid);
			ps.setFloat(3,precio);
			ps.setInt(4,ventas);
			ps.setInt(5,total);
			ps.execute();

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo para borrar una fila dado un nombre de caf�
	 * 
	 * @param nombre
	 * @return
	 */
	public void borrar(String nombre) {
		try {
			Utilidades u1=new Utilidades();
			Connection conexion=u1.getConnection();
			PreparedStatement ps=conexion.prepareStatement("Delete from cafes where CAF_NOMBRE = ? ");
			ps.setString(1,nombre);
			ps.execute();

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}
