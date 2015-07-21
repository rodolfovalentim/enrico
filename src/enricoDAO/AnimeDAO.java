package enricoDAO;

import enrico.Anime;
import java.sql.*;

public class AnimeDAO {

	public void createTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS ANIMES ("
					+ " ID  INTEGER   PRIMARY KEY   AUTOINCREMENT,"
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
		PreparedStatement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			stmt = c.prepareStatement("INSERT INTO ANIMES (NAME, SINOPSE, YEAR, FANSUBS, STATUS, TABLEEPISODES) "
					+ "VALUES (?,?,?,?,?,?);");

			stmt.setString(1, anime.getName());
			stmt.setString(2, anime.getSinopse());
			stmt.setString(3, anime.getYear());
			stmt.setString(4, anime.getFansubtoString());
			stmt.setString(5, anime.getStatus());
			stmt.setString(6, anime.getName().replace(" ", "").toUpperCase());

			stmt.executeUpdate();
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
						rs.getString("YEAR"), rs.getString("FANSUBS"),
						rs.getString("TABLEEPISODES"), rs.getString("STATUS"),
						rs.getInt("ID"));
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
						rs.getString("YEAR"), rs.getString("FANSUBS"),
						rs.getString("TABLEEPISODES"), rs.getString("STATUS"),
						rs.getInt("ID"));
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
						rs.getString("YEAR"), rs.getString("FANSUBS"),
						rs.getString("TABLEEPISODES"), rs.getString("STATUS"),
						rs.getInt("ID"));
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