package enrico;

import java.util.ArrayList;

public class Series extends TVShow {
	ArrayList<Season> seasons;

	public Series(String name, String sinopse, String year, String status,
			ArrayList<Season> seasons) {
		super(name, sinopse, year, status);
		setSeasons(seasons);
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}
}
