package animakai;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Episode;
import linkapi.PreparedLink;

public class AnimaKaiEpisodeListPage {
	private String preparedLink = "http://www.animakai.tv/anime/*/";
	
	private ArrayList<Episode> episodesHD = new ArrayList<Episode>();
	private ArrayList<Episode> episodesFullHD = new ArrayList<Episode>();
	private ArrayList<Episode> episodesSD = new ArrayList<Episode>();
	private ArrayList<Episode> episodesMP4 = new ArrayList<Episode>();
	
	private void findEpisodes(){
		WebDriver driver = new  PhantomJSDriver();
		driver.get(preparedLink);
		
		//System.out.println(driver.getPageSource());
		
		for (WebElement e : driver.findElements(By.className("epBt1"))){
			System.out.println(e.findElement(By.tagName("a")).getText()+" - "+e.findElement(By.tagName("a")).getAttribute("href"));
			e.findElement(By.tagName("a")).click();
		}
		for (WebElement e : driver.findElements(By.className("tc_content_item"))){
			WebElement elem = e.findElement(By.tagName("a"));
			if(elem.getText().equals("MP4"))
				elem.click();
		}
		for (WebElement e : driver.findElements(By.className("tc_content_item"))){
			System.out.println(e.findElement(By.tagName("a")).getText());
		}
		
		driver.close();
	}
	
	public AnimaKaiEpisodeListPage (int id, String quality){
		PreparedLink url = new PreparedLink(preparedLink);
		url.set(0, Integer.toString(id));
		preparedLink = url.toString();
		findEpisodes();
	}
	
	public ArrayList<Episode> getEpisodes (String quality){
		switch (quality){
			case "HD": 
				return episodesHD;
			case "FullHD": 
				return episodesFullHD;
			case "SD": 
				return episodesSD;
			case "MP4": 
				return episodesMP4;
			default:
				return null;
		}
	}
}
