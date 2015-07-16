package animakai;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Episode;
import enrico.Quality;
import fansubs.FansubAnimePage;
import linkapi.PreparedLink;

public class AnimaKaiEpisodeListPage extends FansubAnimePage {
	
	private static String renameQuality (Quality q){
		if(q.equals(Quality.FullHD))
			return "1080p";
		else
			return q.toString();
	}
	
	private static void getByIndividualEpisode (List<Episode> episodes,Quality quality, WebDriver driver){
		
		driver.findElement(By.id("serie_pg_tabs_item_2")).click();
		
		for (WebElement e : driver.findElements(By.className("epBt1"))){
			e.findElement(By.tagName("a")).click();
		}
		
		for (WebElement e : driver.findElements(By.className("tc_content_item"))){
			WebElement elem = e.findElement(By.tagName("a"));
			if(elem.getText().equals(renameQuality(quality)))
				elem.click();
		}
		
		for (WebElement e : driver.findElements(By.cssSelector(".tc_content_item,.tc_title"))){

			try {
				String href = e.findElement(By.tagName("a")).getAttribute("href");
				String text = e.findElement(By.tagName("a")).getText();
				if (!text.isEmpty() && href.split("/").length > 1 && episodes.size()>0)
					episodes.get(episodes.size()-1).addMirror(text,href);
				}catch(org.openqa.selenium.NoSuchElementException exc){
				String str = e.getText().replaceAll("[^0123456789]", "");
				if (!str.isEmpty())
					 episodes.add(new Episode("",Integer.valueOf(str)));
			}
		}
	}
	
	public AnimaKaiEpisodeListPage (int id){
		preparedLink = "http://www.animakai.tv/anime/*/";
		PreparedLink url = new PreparedLink(preparedLink);
		url.set(0, Integer.toString(id));
		preparedLink = url.toString();
	}

	@Override
	public Episode getLastEpisode(Quality quality) {
		return null;
	}

	@Override
	public List<Episode> getAllEpisodes(Quality quality) {
		List<Episode> episodes = new ArrayList<Episode>();
		WebDriver driver = new  PhantomJSDriver();
		driver.get(preparedLink);	
		getByIndividualEpisode(episodes, quality, driver);
		
		driver.close();
		return episodes;
	}

	@Override
	public Episode getEpisode(int number, Quality quality) {
		// TODO Auto-generated method stub
		return null;
	}

}
