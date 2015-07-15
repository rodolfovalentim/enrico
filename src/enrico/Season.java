package enrico;

import java.util.ArrayList;

public class Season {
	int number;
	int year;
	ArrayList<Episode> episodes;

	public Season(int number, int year, ArrayList<Episode> episodes) {
		super();
		this.number = number;
		this.year = year;
		this.episodes = episodes;
	}
}
