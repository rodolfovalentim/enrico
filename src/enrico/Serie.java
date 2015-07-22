package enrico;

import java.util.ArrayList;

public class Serie extends TVShow {
	ArrayList<Season> seasons;

	public Serie(String name, String sinopse, String year, String status) {
		super(name, sinopse, year, status);
		setSeasons();
	}
	
	public Serie(String name, String sinopse, String year, String status,
			ArrayList<Season> seasons) {
		super(name, sinopse, year, status);
		setSeasons(seasons);
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons() {
		this.seasons = new ArrayList<Season>();
	}
	
	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}
}
