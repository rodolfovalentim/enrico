package enrico;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class Anime extends TVShow {

	ArrayList<Episode> episodes;
	ArrayList<Fansub> fansubs;

	public Anime(String name, String sinopse, String year, String status) {
		super(name, sinopse, year, status);
		setEpisodes(episodes);
	}
	
	public void addEpisode(String title, int episode,
			java.util.Date exibition){
		Episode e = new Episode(title, episode, exibition);
		getEpisodes().add(e);
	}

	public ArrayList<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(ArrayList<Episode> episodes) {
		this.episodes = new ArrayList<Episode>();
	}
	
	
	public static ArrayList<String> search (String searchString){
		ArrayList<String> links = new ArrayList<String>();
        WebDriver driver = new  PhantomJSDriver();
		driver.get("http://punchsub.net/#lista-de-animes/nome/todos/1");
		WebElement searchField = driver.findElement(By.id("buscaProjeto"));
		searchField.clear();
		System.out.println("ssss");
		searchField.sendKeys(searchString);
		searchField.submit();
		for (WebElement e : driver.findElements(By.className("pNome"))){
			links.add(e.findElement(By.className("ajax")).getAttribute("href"));			
		}
		driver.close();
		return links;
	}
}
