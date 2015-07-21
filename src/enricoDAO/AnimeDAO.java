package enricoDAO;

import enrico.Anime;
import java.sql.*;

public class AnimeDAO {

	public static void createTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS ANIMES ("
					+ " ID 			   INTEGER 	    PRIMARY KEY  NOT NULL,"
					+ " NAME           TEXT		                 NOT NULL,"
					+ " SINOPSE        TEXT," + " YEAR           TEXT,"
					+ " FANSUBS        TEXT," + " TABLEEPISODES  TEXT,"
					+ " STATUS         TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void insert(Anime anime) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();

			String s = "ID, " + "NAME, " + "SINOPSE, " + "YEAR, " + "FANSUBS, "
					+ "STATUS";

			String a = "'" + anime.getName() + "', '" + anime.getSinopse()
					+ "', '" + anime.getYear() + "', '"
					+ anime.getFansubtoString() + "', '" + anime.getStatus()
					+ "'";

			String sql = "INSERT INTO ANIMES (" + s + ") VALUES (" + a + ");";

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public Anime getByName(String name) {
		Anime a = null;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "SELECT * FROM ANIMES WHERE NAME = '" + name + "';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				a = new Anime(rs.getString("NAME"), rs.getString("SINOPSE"),
						rs.getString("YEAR"), rs.getInt("ID"),
						rs.getString("FANSUBS"), rs.getString("TABLEEPISODES"),
						rs.getString("STATUS"));
			}

			stmt.close();
			c.commit();
			c.close();
			return a;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return a;
	}

	public Anime getById(String id) {
		Anime a = null;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "SELECT * FROM ANIMES WHERE ID = '" + id.toString()
					+ "';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				a = new Anime(rs.getString("NAME"), rs.getString("SINOPSE"),
						rs.getString("YEAR"), rs.getInt("ID"),
						rs.getString("FANSUBS"), rs.getString("TABLEEPISODES"),
						rs.getString("STATUS"));
			}

			stmt.close();
			c.commit();
			c.close();
			return a;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return a;
	}
	
	public Anime getAll(String id) {
		Anime a = null;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "SELECT * FROM ANIMES WHERE ID = '" + id.toString()
					+ "';";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				a = new Anime(rs.getString("NAME"), rs.getString("SINOPSE"),
						rs.getString("YEAR"), rs.getInt("ID"),
						rs.getString("FANSUBS"), rs.getString("TABLEEPISODES"),
						rs.getString("STATUS"));
			}

			stmt.close();
			c.commit();
			c.close();
			return a;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return a;
	}
}
