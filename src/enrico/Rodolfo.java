package enrico;

import java.io.IOException;
import java.util.ArrayList;

import databaseapi.AnimePlanet;
import enricoDAO.AnimeDAO;

public class Rodolfo {

	public static void main(String[] args) throws InterruptedException, IOException {

		String password = LinuxCommand.getPasswdForRoot();
		LinuxCommand.runFromRoot("echo 'teest' >> blabl.sh", password);
		LinuxCommand.runFromRoot("mv blabl.sh /etc/init.d", password);
		
		/*
		 * AnimePlanet imdb = new AnimePlanet(); long startTime; long stopTime;
		 * 
		 * long startTime; long stopTime; AnimePlanet imdb = new AnimePlanet();
		 * 
		 * imdb.setTitle("Yuru Yuri 2"); startTime = System.currentTimeMillis();
		 * imdb.search(); stopTime = System.currentTimeMillis();
		 * System.out.println("Execution Time 1: " + (stopTime - startTime));
		 * 
		 * 
		 * AnimeDAO a = new AnimeDAO(); ArrayList<Anime> anime = a.getAll();
		 * 
		 * startTime = System.currentTimeMillis();
		 * 
		 * for (int i = 0; i < 1662; i++) {
		 * imdb.setTitle(anime.get(i).getName()); imdb.search(); }
		 * 
		 * stopTime = System.currentTimeMillis(); System.out.println(
		 * "Execution Time 1: " + (stopTime - startTime));
		 */
	}
}