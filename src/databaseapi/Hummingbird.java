package databaseapi;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enrico.Anime;
import enrico.DriverManager;
import enrico.TVShow;
import linkapi.PreparedLink;

public class Hummingbird {

	private String title;
	private PreparedLink searchQuery;
	private String infoPage;
	private TVShow tvShow;
	
	public Hummingbird(){
		searchQuery = new PreparedLink("https://hummingbird.me/search?query=*&scope=anime");
		title = "";
		infoPage = "";
		tvShow = null;
	}
	
	public Hummingbird(String title){
		searchQuery = new PreparedLink("https://hummingbird.me/search?query=*&scope=anime");
		this.setTitle(title);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		searchQuery.set(0,title);
		search();
	}

	public PreparedLink getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(PreparedLink searchQuery) {
		this.searchQuery = searchQuery;
	}

	public String getInfoPage() {
		return infoPage;
	}

	public void setInfoPage(String infoPage) {
		this.infoPage = infoPage;
	}
	
	public boolean search() {
		WebDriver driver = DriverManager.getDriver();	
		driver.get(searchQuery.toString());
		WebElement element = (new WebDriverWait(driver, 5000)) //added this line
			    .until(ExpectedConditions.presenceOfElementLocated(By.className("search-container-results")));
		if (element == null){
			infoPage = "";
			tvShow = null;
			DriverManager.free(driver);
			return false;
		}
		WebElement elem=(new WebDriverWait(driver, 5000)) //added this line
			    .until(ExpectedConditions.presenceOfElementLocated(By.className("search-result-image")));
		List <WebElement>elements = element.findElements(By.className("ember-view"));
		if(elements.size()==0){
			infoPage = "";
			tvShow = null;
			DriverManager.free(driver);
			return false;
		}else{
			infoPage = elements.get(0).getAttribute("href");
			String sinopse = elements.get(0).findElement(By.className("search-result-details")).getText();
			String[] details = sinopse.split("\n");
			title = details[0];
			tvShow = new Anime(details[0], details[1], "unknown", "unknown");
			DriverManager.free(driver);
			return true;
		}
	}
		
	public boolean searchSoup() {
		Document doc = null;
		try {
			doc = Jsoup.connect(searchQuery.toString()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		for (Element e :doc.getElementsByClass("value")){
			System.out.println(e.text());
		}
		System.out.println("search soup test");
		return false;
	}
}
