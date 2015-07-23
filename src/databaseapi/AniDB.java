package databaseapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import linkapi.PreparedLink;

public class AniDB {

	private String title;
	private PreparedLink searchQuery;
	private String infoPage;
	
	private static List<java.util.Date> acessList = new ArrayList<java.util.Date>();
	
	public AniDB(){
		searchQuery = new PreparedLink("http://anidb.net/perl-bin/animedb.pl?type=2&show=animelist&do.search=Search&adb.search=*");
		this.setTitle("");
	}
	
	public AniDB(String title){
		searchQuery = new PreparedLink("http://anidb.net/perl-bin/animedb.pl?type=2&show=animelist&do.search=Search&adb.search=*");
		this.setTitle(title);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		searchQuery.set(0, title.replaceAll("\\s", "+"));
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
		WebDriver driver = new PhantomJSDriver();
		driver.get(searchQuery.toString());
		if (!driver.getCurrentUrl().equals(searchQuery.toString())){
			infoPage = driver.getCurrentUrl();
			driver.close();
			return true;
		}else{
			searchQuery.set(0, "\""+title.replaceAll("\\s", "+")+"\"");
			driver.get(searchQuery.toString());
			if (!driver.getCurrentUrl().equals(searchQuery.toString())){
				infoPage = driver.getCurrentUrl();
				driver.close();
				return true;
			}else{
				infoPage = "Not Found";
				driver.close();
				return false;
			}
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
