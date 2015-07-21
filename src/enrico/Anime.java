package enrico;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enricoDAO.EpisodeDAO;
import fansubs.Fansub;

public class Anime extends TVShow {

	int id;
	ArrayList<Episode> episodes;
	ArrayList<Fansub> fansubs;


	public Anime(String name, String sinopse, String year, int id,
			String fansubs, String tableEpisodes, String status) {
		super(name, sinopse, year, status);
		setId(id);
		setEpisodes(tableEpisodes);
		setFansubs(fansubs);
	}

	public void addEpisode(String title, int episode, java.util.Date exibition,
			String quality) {
		Episode e = new Episode(title, episode, exibition, quality);
		getEpisodes().add(e);
	}

	public static ArrayList<String> search(String searchString) {
		ArrayList<String> links = new ArrayList<String>();
		WebDriver driver = new PhantomJSDriver();
		driver.get("http://punchsub.net/#lista-de-animes/nome/todos/1");
		WebElement searchField = driver.findElement(By.id("buscaProjeto"));
		searchField.clear();
		System.out.println("ssss");
		searchField.sendKeys(searchString);
		searchField.submit();
		for (WebElement e : driver.findElements(By.className("pNome"))) {
			links.add(e.findElement(By.className("ajax")).getAttribute("href"));
		}
		driver.close();
		return links;
	}

	public String getFansubtoString() {
		String s = "";
		ArrayList<Fansub> fansubs = getFansubs();

		if (fansubs != null) {
			for (Fansub f : fansubs) {
				s = s + f.getName() + ",";
			}
		}
		return s;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId(String name) {
		WebDriver driver = new PhantomJSDriver();
		name.replaceAll("\\s", "+");
		driver.get("http://anidb.net/perl-bin/animedb.pl?type=2&show=animelist&do.search=Search&adb.search="
				+ name);

		int out = -1;

		try {
			out = Integer.valueOf(driver.getCurrentUrl().split("=")[2]);
		} catch (java.lang.NumberFormatException e) {
			driver.get("http://anidb.net/perl-bin/animedb.pl?type=2&show=animelist&do.search=Search&adb.search="
					+ "\"" + name + "\"");
			try {
				out = Integer.valueOf(driver.getCurrentUrl().split("=")[2]);
			} catch (java.lang.NumberFormatException e2) {

			}
		}
		driver.close();

		this.id = out;
	}

	public ArrayList<Fansub> getFansubs() {
		return fansubs;
	}

	public void setFansubs() {
		this.fansubs = new ArrayList<Fansub>();
	}

	public void setFansubs(ArrayList<Fansub> fansubs) {
		this.fansubs = fansubs;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
	        return false;
		Anime anime = (Anime) obj;
		return (this.id == anime.id);
	}

	public void setFansubs(String fansubs) {
		ArrayList<Fansub> f = new ArrayList<Fansub>();
		String[] tokies = fansubs.split(",");

		for (String s : tokies) {
			// f.add(new Fansub("", s));
		}
	}

	public ArrayList<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(String tableEpisodes) {
		EpisodeDAO e = new EpisodeDAO();
		setEpisodes(e.getAll(tableEpisodes));
	}

	public void setEpisodes() {
		this.episodes = new ArrayList<Episode>();
	}

	public void setEpisodes(ArrayList<Episode> episodes) {
		this.episodes = episodes;
	}

}
