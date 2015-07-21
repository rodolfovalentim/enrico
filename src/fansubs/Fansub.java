package fansubs;

import java.util.List;
import linkapi.PreparedLink;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Episode;
import enrico.Quality;

public abstract class Fansub {

	protected String id;
	protected String name;
	protected String website;
	protected PreparedLink preparedLink;

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

	public void setPreparedLink(PreparedLink preparedLink) {
		this.preparedLink = preparedLink;
	}

	public abstract Episode getLastEpisode(Quality quality);

	public abstract List<Episode> getAllEpisodes(Quality quality);

	public abstract Episode getEpisode(int number, Quality quality);

	public Fansub(String id) {
		setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PreparedLink getPreparedLink() {
		return preparedLink;
	}

	public void setPreparedLink(String preparedLink) {
		this.preparedLink = new PreparedLink(preparedLink);
	}

}

