package enricoDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import enrico.Episode;

public class EpisodeDAO {

	public void createTable(String name) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS" + name + "("
					+ " NUMBER		   INTEGER 	    PRIMARY KEY  NOT NULL,"
					+ " TITLE          TEXT," + " QUALITY        TEXT,"
					+ " DATE           TEXT," + " MIRRORS        TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public void insert(String tableName, Episode e) {
		Connection c = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.prepareStatement("INSERT INTO ? (ID, TITLE, QUALITY, MIRRORS) VALUES (?,?,?,?)");

			stmt.setString(1, tableName);
			stmt.setInt(2, e.getEpisode());
			stmt.setString(3, e.getTitle());
			stmt.setString(4, e.getQuality());
			stmt.setString(5, e.getMirrorstoString());

			stmt.executeUpdate();
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception a) {
			System.err.println(a.getClass().getName() + ": " + a.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public Episode getById(String tableName, int id) {

		Episode ep = null;
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "SELECT * FROM ANIMES " + tableName + "WHERE ID = "
					+ String.valueOf(id) + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ep = new Episode(rs.getString("TITLE"), rs.getInt("ID"),
						rs.getString("QUALITY"), rs.getString("MIRRORS"));
			}

			stmt.close();
			c.commit();
			c.close();
			return ep;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return ep;
	}

	public ArrayList<Episode> getAll(String tableName) {

		ArrayList<Episode> episodes = new ArrayList<Episode>();
		Episode ep = null;
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "SELECT * FROM ANIMES " + tableName + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ep = new Episode(rs.getString("TITLE"), rs.getInt("ID"),
						rs.getString("QUALITY"), rs.getString("MIRRORS"));
				episodes.add(ep);
			}

			stmt.close();
			c.commit();
			c.close();
			return episodes;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return episodes;
	}
}
