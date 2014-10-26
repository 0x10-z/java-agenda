package packManejoDB;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.security.auth.login.Configuration;
import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

public class ManejoDB {
	private static ManejoDB instance;
	private static Connection con;
	private static String host = "jdbc:mysql://localhost:3306/agenda";
	private static String hostAux = "jdbc:mysql://localhost:3306/";
	private static String user = "root";
	private static String psw = "0306iocio00506";
	private ArrayList<Cita> lista;;

	public static void main(String args[]) {
		ManejoDB mb = ManejoDB.getInstance();
		mb.probarConexion();
		mb.verCitas();
	}

	private ManejoDB() {

		leerPropiedades();
		lista = new ArrayList<Cita>();
		System.out.println("Cargando driver");
		CargarDriver();
	}

	public static ManejoDB getInstance() {
		if (null == instance) {
			instance = new ManejoDB();
		}
		return instance;
	}

	private Connection getConexion() {
		Connection con;
		con = establecerConexion();
		return con;

	}

	private void CargarDriver() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver cargado exitosamente");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Driver no encontrado");
		}
	}

	public void verCitas() {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM citas";
		con = getConexion();
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("nombre"));
			}

		} catch (SQLException e) {
			System.out.println("Error de query al ver las citas");
		}
		cerrarConexion(con);

	}

	public ArrayList<Cita> getCitasDia(int pDia, int pMes, int pAno) {
		lista = new ArrayList();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM citas WHERE dia=? and mes=? and ano=? order by(hora)asc";
		try {
			con = getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pDia);
			pstmt.setInt(2, pMes);
			pstmt.setInt(3, pAno);

			rs = pstmt.executeQuery();
			// ResultSetMetaData md = rs.getMetaData();
			while (rs.next()) {
				lista.add(new Cita(rs.getInt("dia"), rs.getInt("mes"), rs
						.getInt("ano"), rs.getString("nombre"), rs
						.getString("descripcion"), rs.getString("hora")));

			}
			return lista;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en getCitaMes()");
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<Tiempo> getMinutosDia(int pDia, int pMes, int pAno) {

		ArrayList<Tiempo> listaMinutos = new ArrayList();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM tiempo WHERE dia=? and mes=? and ano=?";
		try {
			con = getConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pDia);
			pstmt.setInt(2, pMes);
			pstmt.setInt(3, pAno);
			System.out.println("1");
			rs = pstmt.executeQuery();
			System.out.println("2");
			// ResultSetMetaData md = rs.getMetaData();
			while (rs.next()) {
				listaMinutos.add(new Tiempo(rs.getInt("dia"), rs.getInt("mes"),
						rs.getInt("ano"), rs.getString("minutos")));

			}
			return listaMinutos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en getMinutoMes()");
			e.printStackTrace();
		}
		return listaMinutos;

	}

	public boolean probarConexion() {
		try {
			con = DriverManager.getConnection(host, user, psw);
			System.out
					.println("Conexion establecida\nLa preba ha sido exitosa");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out
					.println("No se ha podido establecer conexion con la base de datos");
			return false;
		}

	}

	private void cerrarConexion(Connection con2) {
		try {
			con.close();
			System.out.println("Conexion cerrada");
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Connection establecerConexion() {
		// TODO Auto-generated method stub

		try {
			con = DriverManager.getConnection(host, user, psw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("No se ha podido establecer conexion con la base de datos");
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		System.out.println("Conexion establecida");

		return con;
	}

	/**
	 * DEPRECATED
	 * 
	 * @param pMes
	 * @return
	 */
	private String getMes(int pMes) {
		switch (pMes) {
		case 0:
			return "Enero";
		case 1:
			return "Febrero";
		case 2:
			return "Marzo";
		case 3:
			return "Abril";
		case 4:
			return "Mayo";
		case 5:
			return "Junio";
		case 6:
			return "Julio";
		case 7:
			return "Agosto";
		case 8:
			return "Septiembre";
		case 9:
			return "Octubre";
		case 10:
			return "Novimebre";
		case 11:
			return "Diciembre";
		default:
			break;
		}
		return null;

	}

	public void anadirCita(String pNombre, String pDescripcion, int pDia,
			int pMes, int pAno, String pHora) {
		// TODO Auto-generated method stub
		Connection con = getConexion();
		String sql = "Insert into citas values (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pNombre);
			ps.setString(2, pDescripcion);
			ps.setInt(3, pDia);
			ps.setInt(4, pMes);
			ps.setInt(5, pAno);
			ps.setString(6, pHora);
			ps.executeUpdate();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de query ejecutada");
		}
		cerrarConexion(con);
	}

	public void eliminarCita(String pNombre, String pHora, int pDia, int pMes,
			int pAno) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con = getConexion();
		String sql = "delete from citas where nombre =? and hora=? and dia=? and mes=? and ano=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pNombre);
			ps.setString(2, pHora);
			ps.setInt(3, pDia);
			ps.setInt(4, pMes);
			ps.setInt(5, pAno);

			ps.executeUpdate();
			// La hora no la saca bien
			sql = "delete from citas where nombre = '" + pNombre
					+ "' and hora ='" + pHora + "' and dia ='" + pDia
					+ "' and mes ='" + pMes + "' and ano='" + pAno + "';";
			System.out.println(sql);
			System.out.println("Cita borrada");

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de cita eliminada");
		}
		cerrarConexion(con);
	}

	public void leerPropiedades() {
		// Initially load properties from jar
		Properties props = new Properties();
		try {
			FileInputStream loadPropertiesURL = new FileInputStream(
					System.getProperty("user.dir") + "/agenda.prop");
			
			props.load(loadPropertiesURL);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		host = props.getProperty("db_host") + props.getProperty("db_name");
		user = props.getProperty("db_user");
		psw = props.getProperty("db_psw");
		hostAux = props.getProperty("db_host");
		System.out.println("Los datos extraidos son: " + host + " " + user
				+ " " + psw);

	}

	public void guardarPropiedades(String pDbName, String pDbUser, String pDbPsw) {
		Properties prop = new Properties();
		prop.setProperty("db_name", pDbName);
		prop.setProperty("db_user", pDbUser);
		prop.setProperty("db_psw", pDbPsw);
		try {
			FileOutputStream os = new FileOutputStream(
					System.getProperty("user.dir") + "/agenda.prop");
			prop.store(os, "Fichero de propiedades de la agenda");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public boolean eliminarBaseDatos(String pNombreDB) {
		ArrayList<String> linea = new ArrayList<String>();
		Connection conAux = null;
		try {
			conAux = DriverManager.getConnection(hostAux, user, psw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("No se ha podido eliminar la base de datos. Conexion no establecida");
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		System.out.println("Conexion establecida");

		String sql = "DROP DATABASE " + pNombreDB;

		try {
			PreparedStatement ps = conAux.prepareStatement(sql);

			ps.executeUpdate();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("No se ha podido crear la base de datos. Existe una.");
			return false;
		}
		cerrarConexion(conAux);
		return true;

	}

	public boolean crearBaseDatos() {
		ArrayList<String> linea = new ArrayList<String>();
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(System.getProperty("user.dir")
					+ "/agenda.prop"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dbName = props.getProperty("db_name");

		Connection conAux = null;
		try {
			conAux = DriverManager.getConnection(hostAux, user, psw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("No se ha podido crear la base de datos. Conexion no establecida");
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		System.out.println("Conexion establecida");

		String sql = "CREATE DATABASE " + dbName;

		try {
			PreparedStatement ps = conAux.prepareStatement(sql);

			ps.executeUpdate();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("No se ha podido crear la base de datos. Existe una.");
			return false;
		}
		cerrarConexion(conAux);
		return true;
	}

	public String sacarNombreDeCita(String pCadena) {
		String nombre = "";
		int j = 0;
		for (int i = 0; i < pCadena.length(); i++) {
			if ((pCadena.charAt(i) == '-')) {
				j++;
			} else if (j == 1) {
				nombre += pCadena.charAt(i);
			}
			if (j == 2) {
				nombre = nombre.trim();
				System.out.println("Se ha sacado el nombre |" + nombre + "|");
				return nombre;
			}

		}
		return nombre;
	}

	public String sacarHoraDeCita(String pCadena) {
		String nombre = "";
		String hora = "";
		int j = 0;
		pCadena = pCadena.replaceAll(" ", "");

		for (int i = 0; i < pCadena.length() && j < 2; i++) {
			if (pCadena.charAt(i) == '-') {
				j++;
			}
			if ((Integer.valueOf(pCadena.charAt(i)) - 48) >= 0
					&& (Integer.valueOf(pCadena.charAt(i)) - 48) <= 9
					|| pCadena.charAt(i) == ':') {
				hora += pCadena.charAt(i);
			}

		}
		System.out.println("Se ha sacado la hora |" + hora + "|");
		return hora;
	}

	public String sacarHoraDeHora(String pCadena) {
		String nombre = "";
		String hora = "";
		int j = 0;
		pCadena = pCadena.replaceAll(" ", "");

		for (int i = 0; i < pCadena.length() && j < 2; i++) {
			if (pCadena.charAt(i) == '-') {
				j++;
			}
			if ((Integer.valueOf(pCadena.charAt(i)) - 48) >= 0
					&& (Integer.valueOf(pCadena.charAt(i)) - 48) <= 9) {
				hora += pCadena.charAt(i);
			}

		}
		System.out.println("Se ha sacado la hora |" + hora + "|");
		return hora;
	}

	public String sacarMinDeHora(String pCadena) {
		String nombre = "";
		String hora = "";
		int j = 0;
		pCadena = pCadena.replaceAll(" ", "");

		for (int i = 0; i < pCadena.length() && j < 2; i++) {
			if (pCadena.charAt(i) == '-') {
				j++;
			}
			if ((Integer.valueOf(pCadena.charAt(i)) - 48) >= 0
					&& (Integer.valueOf(pCadena.charAt(i)) - 48) <= 9
					|| pCadena.charAt(i) == ':') {
				hora += pCadena.charAt(i);
			}

		}
		System.out.println("Se ha sacado la hora |" + hora + "|");
		return hora;
	}

	public void insertarMinutosTiempo(String pTmpo, int pDia, int pMes, int pAno) {

		// TODO Auto-generated method stub
		Connection con = getConexion();
		String sql = "Insert into tiempo values (?, ?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pTmpo);
			ps.setInt(2, pDia);
			ps.setInt(3, pMes);
			ps.setInt(4, pAno);
			ps.executeUpdate();

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de query ejecutada");
			JOptionPane
					.showMessageDialog(null,
							"No se ha podido introducir los minutos\nConsulte el error mas detenidamente");
		}
		cerrarConexion(con);

	}

	public void eliminarMinutosTiempo(String pMinutos, int pDia, int pMes,
			int pAno) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con = getConexion();
		String sql = "delete from tiempo where minutos=? and dia=? and mes=? and ano=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pMinutos);
			ps.setInt(2, pDia);
			ps.setInt(3, pMes);
			ps.setInt(4, pAno);

			ps.executeUpdate();

			JOptionPane.showMessageDialog(null,
					"Los minutos se han eliminado satisfactoriamente");

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error de minutos eliminados");
			JOptionPane.showMessageDialog(null,
					"No se ha podido eliminar los minutos");
		}
		cerrarConexion(con);
	}

	public String sacarMinutosDeTiempo(String pCadena) {
		String nombre = "";
		int j = 0;
		for (int i = 0; i < pCadena.length(); i++) {
			if ((pCadena.charAt(i) == '-')) {
				j++;
			} else if (j == 1) {
				nombre += pCadena.charAt(i);
			}
			if (j == 2) {

				System.out.println("Se ha sacado el tiempo |" + nombre + "|");
				return nombre;
			}

		}
		return nombre;
	}

}