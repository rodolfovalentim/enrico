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
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS ANIMES ("
					+ " ID 			   INTEGER 	    PRIMARY KEY  NOT NULL,"
					+ " NAME           TEXT		                 NOT NULL,"
					+ " SINOPSE        TEXT,"
					+ " YEAR           TEXT,"
					+ " FANSUBS        TEXT,"
					+ " TABLEEPISODES  TEXT,"
					+ " STATUS         TEXT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public static Anime get() {
		Anime a = new Anime(1, null, null, null, null);
		return a;
	}

	public static void insert(Anime anime) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:enrico.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			
			String s = "ID, " +
					   "NAME, " +
					   "SINOPSE, " +
					   "YEAR, " +
					   "FANSUBS, " +
					   "STATUS";
			
			String a = "'" + anime.getName() + "', '" 
						+ anime.getSinopse() + "', '" 
						+ anime.getYear() + "', '"
						+ anime.getFansubtoString() + "', '"
						+ anime.getStatus() + "'";
			
			String sql = "INSERT INTO ANIMES (" + s + ") VALUES (" + a + ");";
			
			System.out.println(a);
			
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}
}
