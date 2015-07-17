package fansubs;

import java.util.List;

import enrico.Episode;
import enrico.Quality;

public abstract class Fansub {

	protected String id;
	protected String preparedLink;
	
	public abstract Episode getLastEpisode (Quality quality);
	
	public abstract List<Episode> getAllEpisodes (Quality quality);
	
	public abstract Episode getEpisode (int number,Quality quality);
}
