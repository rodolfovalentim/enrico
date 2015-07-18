package fansubs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Episode;
import enrico.Quality;

public abstract class Fansub {

	protected String id;
	protected String preparedLink;
	
	public abstract Episode getLastEpisode (Quality quality);
	
	public abstract List<Episode> getAllEpisodes (Quality quality);
	
	public abstract Episode getEpisode (int number,Quality quality);
	
	public static int getAnimeID (String name){
		WebDriver driver = new PhantomJSDriver();
		name.replaceAll("\\s", "+");
		driver.get("http://anidb.net/perl-bin/animedb.pl?type=2&show=animelist&do.search=Search&adb.search="+name);
		
		int out = -1;
		
		try{
			out = Integer.valueOf(driver.getCurrentUrl().split("=")[2]);
		}catch (java.lang.NumberFormatException e){
			driver.get("http://anidb.net/perl-bin/animedb.pl?type=2&show=animelist&do.search=Search&adb.search="+"\""+name+"\"");
			try{
				out = Integer.valueOf(driver.getCurrentUrl().split("=")[2]);
			}catch(java.lang.NumberFormatException e2){
				
			}
		}
		driver.close();
		
		return out;
	}
}
