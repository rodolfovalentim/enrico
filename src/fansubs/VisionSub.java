package fansubs;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Anime;
import enrico.Episode;
import enrico.Mirror;
import enrico.Quality;
import linkapi.PreparedLink;

public class VisionSub extends Fansub {

	private Integer pagina;
	
	public VisionSub(String id) {
		super(id);
		preparedLink = new PreparedLink("http://www.visionfansub.com.br/episodios/*/pagina/*/");
		preparedLink.set(0, id);
		pagina = 0;
		preparedLink.set(1, pagina.toString());
	}
	
	private static String renameQuality(Quality q) {
		if (q.equals(Quality.FullHD))
			return "FULLHD";
		else if(q.equals(Quality.SD))
			return "RMVB";
		else		
			return q.toString();
	}

	private static void selectQuality(WebDriver driver,Quality quality){
		for (WebElement e : driver.findElements(By.className("skin_ep_ova_dados_formatos_area_botoes"))){
			if(e.getText().equals(renameQuality(quality))){
				e.click();
			}
		}
	}
	
	private static void breakProtector(Episode e,WebDriver driver){
		for (Mirror m : e.getMirrors()){
			driver.get(m.getLink());
			m.setLink(driver.findElement(By.id("recado")).findElement(By.tagName("a")).getAttribute("href"));
		}
	}
	
	private static Episode getEpisodeInfo(WebElement elementBox){
		String title = elementBox.findElement(By.className("skin_ep_ova_titulo_numero")).getText();
		Episode ep = new Episode(title, Integer.valueOf(title.split(" ")[title.split(" ").length-1]), "");
		for (WebElement e : elementBox.findElements(By.className("skin_ep_ova_links_area_lista"))){
			String text = e.findElement(By.tagName("a")).getText();
			String href = e.findElement(By.tagName("a")).getAttribute("href");

			if (!text.isEmpty())
				ep.addMirror(text, href);
		}
		return ep;
	}
	
	private static void getAnimesFromPage(List<WebElement> elements, ArrayList<Anime> animes){
		for (WebElement e : elements){
			int id = 0;
			String[] name = e.getAttribute("href").split("/");
			if (name.length>4){
				if (name[5].length() > 1){
					Anime anime = new Anime(name[5].replace('-', ' '),"","",id,"","","");
					animes.add(anime);
				}
			}
		}
	}
	
	@Override
	public Episode getLastEpisode(Quality quality) {
		pagina = 0;
		WebDriver driver = new HtmlUnitDriver();
		
		List<WebElement> elements;
		
		do{
			preparedLink.set(1, pagina.toString());
			driver.get(preparedLink.toString());
			selectQuality(driver, quality);
			elements = driver.findElements(By.className("skin_ep_ova_skin"));
			pagina++;
		}while(elements.size() > 0);
		
		pagina-=2;
		preparedLink.set(1, pagina.toString());
		driver.get(preparedLink.toString());
		selectQuality(driver, quality);
		elements = driver.findElements(By.className("skin_ep_ova_skin"));
		
		Episode episode = getEpisodeInfo(elements.get(elements.size()-1));
		
		breakProtector(episode, driver);
		
		driver.close();
		return episode;
	}

	@Override
	public List<Episode> getAllEpisodes(Quality quality) {
		pagina = 0;
		WebDriver driver = new HtmlUnitDriver();
		
		List<WebElement> elements;
		List<Episode> episodes = new ArrayList<Episode>();
		
		do{
			preparedLink.set(1, pagina.toString());
			driver.get(preparedLink.toString());
			selectQuality(driver, quality);
			elements = driver.findElements(By.className("skin_ep_ova_skin"));
			for (WebElement e : elements)
				episodes.add(getEpisodeInfo(e));
			pagina++;
		}while(elements.size() > 0);
		
		for (Episode e: episodes)
			breakProtector(e, driver);
		
		driver.close();
		return episodes;
	}

	@Override
	public Episode getEpisode(int number, Quality quality) {
		pagina = 0;
		WebDriver driver = new HtmlUnitDriver();
		
		List<WebElement> elements;
		Episode episode = null;
		
		do{
			preparedLink.set(1, pagina.toString());
			driver.get(preparedLink.toString());
			selectQuality(driver, quality);
			elements = driver.findElements(By.className("skin_ep_ova_skin"));
			for (WebElement e : elements){
				Episode ep = getEpisodeInfo(e);
				if (ep.getEpisode()==number){
					episode = ep;
					break;
				}
			}
			pagina++;
		}while(elements.size() > 0);
		
		if(episode!=null) 
			breakProtector(episode, driver);
		
		driver.close();
		return episode;
	}

	public static List<Anime> getAllAnimes (){
		ArrayList<Anime> animeList = new ArrayList<Anime>();
		
		WebDriver driver = new PhantomJSDriver();
		int page = 0;
		List<WebElement> elements = null;
		do {
			try{
				driver.get("http://www.visionfansub.com.br/episodios/pagina/"+(page++)+"/");
				elements = driver.findElement(By.id("area_conteudo_site")).findElements(By.tagName("a"));
				getAnimesFromPage(elements,animeList);
			}catch(Exception e){
				page --;
			}
		}while(elements.size() > 52);
		driver.close();
		
		return animeList;
	}
}
