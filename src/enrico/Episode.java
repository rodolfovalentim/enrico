package enrico;

import java.util.ArrayList;

public class Episode {

	String title;
	int episode;
	String quality;
	java.util.Date exibitionDate;
	ArrayList<Mirror> mirrors;

	public Episode(String title, int episode) {
		setTitle(title);
		setExibitionDate(new java.util.Date());
		setEpisode(episode);
		setMirrors();
	}

	public Episode(String title, int episode, java.util.Date exibition) {
		setTitle(title);
		setExibitionDate(exibition);
		setEpisode(episode);
		setMirrors();
	}

	public void addMirror(String name, String link) {
		Mirror m = new Mirror(name, link);
		getMirrors().add(m);
	}

	public String toString() {
		String s = getTitle() + " " + getEpisode() + "\n" + exibitionDate
				+ "\n";

		ArrayList<Mirror> mirrors = getMirrors();

		for (Mirror m : mirrors)
			s = s + m.toString() + "\n";

		return s;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public java.util.Date getExibitionDate() {
		return exibitionDate;
	}

	public void setExibitionDate(java.util.Date exibitionDate) {
		this.exibitionDate = exibitionDate;
	}

	public ArrayList<Mirror> getMirrors() {
		return mirrors;
	}

	public void setMirrors() {
		this.mirrors = new ArrayList<Mirror>();
	}

}
