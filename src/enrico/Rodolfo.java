package enrico;

import java.io.IOException;
import databaseapi.IMDB;

public class Rodolfo {

	public static void main(String[] args) throws InterruptedException, IOException {

		long startTime;
		long stopTime;
		IMDB imdb = new IMDB();

		imdb.setTitle("Berserk");
		imdb.enableExactSearch(true);
		startTime = System.currentTimeMillis();
		imdb.search();
		stopTime = System.currentTimeMillis();
		System.out.println("Execution Time 1: " + (stopTime - startTime));

	}
}