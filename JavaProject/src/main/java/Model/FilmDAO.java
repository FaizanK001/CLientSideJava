package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FilmDAO implements FilmInfo {
	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;

	/*
	 * String user = "khalilf"; String password = "upaschOl4"; // Note none default
	 * port used, 6306 not 3306 String url =
	 * "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;
	 */
	private static FilmDAO singletonCam;
	// Connection with the googlecloud database
	String Ip_Instance = "35.242.168.86";
	String databaseName = "filmsDB";
	String instanceConnectionName = "webassignment-298401:europe-west2:films";
	String gcpUrl = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s", Ip_Instance, databaseName,
			instanceConnectionName);
	String user = "root";
	String password = "12345";

	public FilmDAO() {

		super();
	}

	public static synchronized FilmDAO getSingletonCam() {

		if (singletonCam == null) {
			singletonCam = new FilmDAO();

		}

		return singletonCam;
	}

	private Connection openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(gcpUrl, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return conn;
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

	public ArrayList<Film> getAllFilms() {

		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from films";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				allFilms.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return allFilms;
	}

	public Film getFilmByID(int id) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where id=" + id;
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneFilm;
	}

	public Film getFilmByNAME(String name) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where title=" + "'" + name + "'";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		return oneFilm;

	}

	public void addFilm(int id, String title, int year, String director, String stars, String review) {

		openConnection();
//	oneFilm=null;
		// Create select statement and execute it
		try {
			Connection conection = openConnection();
			String selectSQL = "INSERT INTO films (id, title, year, director, stars , review) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement post = conection.prepareStatement(selectSQL);

			post.setInt(1, id);
			post.setString(2, title);
			post.setInt(3, year);
			post.setString(4, director);
			post.setString(5, stars);
			post.setString(6, review);

			post.execute();
			// ResultSet rs1 = stmt.executeQuery(selectSQL);

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

	}

	public void deleteById(int id) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			Connection conection = openConnection();

			String selectSQL = "delete from films where id =" + id;
			// ResultSet rs1 = stmt.executeQuery(selectSQL);
			PreparedStatement post = conection.prepareStatement(selectSQL);

			post.execute();
			// Retrieve the results
			/*
			 * while(rs1.next()){ oneFilm = getNextFilm(rs1); }
			 */

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

	}

	public void updateFilm(int id, String title, int year, String director, String stars, String review) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			Connection conection = openConnection();
			String selectSQL = "UPDATE films SET title=?, year=?, director=?, stars=?,review=? WHERE id=" + id;
			PreparedStatement post = conection.prepareStatement(selectSQL);

			// post.setInt(1,id);

			post.setString(1, title);
			post.setInt(2, year);
			post.setString(3, director);
			post.setString(4, stars);
			post.setString(5, review);

			post.execute();
			// ResultSet rs1 = stmt.executeQuery(selectSQL);

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

	}

}
