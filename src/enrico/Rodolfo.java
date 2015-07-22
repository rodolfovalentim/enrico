package enrico;

import java.io.IOException;

import databaseapi.AnimePlanet;

public class Rodolfo {

	public static void main(String[] args) throws InterruptedException, IOException {

		long startTime;
		long stopTime;
		AnimePlanet imdb = new AnimePlanet();

		imdb.setTitle("Yuru Yuri 2");
		startTime = System.currentTimeMillis();
		imdb.search();
		stopTime = System.currentTimeMillis();
		System.out.println("Execution Time 1: " + (stopTime - startTime));

	}
}