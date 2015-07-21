package fansubs;

import java.util.List;

import enrico.Episode;
import enrico.Quality;

public abstract class Fansub {

	protected String name;
	protected String website;

	public Fansub(String name, String website) {
		setName(name);
		setWebsite(website);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String toString() {
		return getName();
	}

	public abstract Episode getLastEpisode(Quality quality);

	public abstract List<Episode> getAllEpisodes(Quality quality);

	public abstract Episode getEpisode(int number, Quality quality);

}
