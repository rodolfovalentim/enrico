package enrico;

import java.util.ArrayList;

public class Episode {

	String title;
	int episode;
	String quality;
	ArrayList<Mirror> mirrors;

	public Episode(String title, int episode, String quality) {
		setTitle(title);
		setQuality(quality);
		setEpisode(episode);
		setMirrors();
	}

	public Episode(String title, int episode, String quality, String mirrors) {
		setTitle(title);
		setQuality(quality);
		setEpisode(episode);
		setMirrors(mirrors);
	}

	public void addMirror(String name, String link) {
		Mirror m = new Mirror(name, link);
		getMirrors().add(m);
	}

	public String toString() {
		String s = getTitle() + " " + getEpisode() + "\n";

		ArrayList<Mirror> mirrors = getMirrors();

		for (Mirror m : mirrors)
			s = s + m.toString() + "\n";

		return s;

	}

	public void mergeMirrors(Episode ep) {
		if (this.equals(ep)) {
			this.mirrors.addAll(ep.getMirrors());
		}
	}

	public String getMirrorstoString() {
		String s = "";
		ArrayList<Mirror> mirrors = getMirrors();

		for (Mirror m : mirrors)
			s = s + m.toString() + ",";

		return s;
	}

	// Getters and Setters
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

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public ArrayList<Mirror> getMirrors() {
		return mirrors;
	}

	public void setMirrors() {
		this.mirrors = new ArrayList<Mirror>();
	}

	public void setMirrors(String mirrors) {
		this.mirrors = new ArrayList<Mirror>();
	}

	// Overrided Methods
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episode ep = (Episode) obj;
		return (this.episode == ep.episode);
	}

}
