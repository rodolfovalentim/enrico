package fansubs;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enrico.Anime;
import enrico.DriverManager;
import enrico.DriverManager.DriverType;
import enrico.Episode;
import enrico.Mirror;
import enrico.Quality;
import linkapi.PreparedLink;

public class AnimaKai extends Fansub {

	private String id;
	private PreparedLink animePage;
	private PreparedLink listPage;

	// Private methods
	private static String renameQuality(Quality q) {
		if (q.equals(Quality.FullHD))
			return "1080p";
		else
			return q.toString();
	}

	private static void clickToShowLinks(WebDriver driver, Quality quality) {
		driver.findElement(By.id("serie_pg_tabs_item_2")).click();

		for (WebElement e : driver.findElements(By.className("epBt1"))) {
			e.findElement(By.tagName("a")).click();
		}

		for (WebElement e : driver
				.findElements(By.className("tc_content_item"))) {
			WebElement elem = e.findElement(By.tagName("a"));
			if (elem.getText().equals(renameQuality(quality)))
				elem.click();
		}
	}

	private static void getByIndividualEpisode(List<Episode> episodes,
			Quality quality, WebDriver driver) {
		
		clickToShowLinks(driver, quality);
		
		for (WebElement e : driver.findElements(By
				.cssSelector(".tc_content_item,.tc_title"))) {
			try {
				String href = e.findElement(By.tagName("a")).getAttribute(
						"href");
				String text = e.findElement(By.tagName("a")).getText();
				if (!text.isEmpty() && href.split("/").length > 1
						&& episodes.size() > 0)
					episodes.get(episodes.size() - 1).addMirror(text, href);
			} catch (org.openqa.selenium.NoSuchElementException exc) {
				String str = e.getText().replaceAll("[^0123456789]", "");
				if (!str.isEmpty())
					episodes.add(new Episode("", Integer.valueOf(str), quality
							.toString()));
			}
		}
	}
	
	private void getByServer(List<Episode> episodes, Quality quality, WebDriver driver) {
		if (driver.findElements(By.id("serie_pg_tabs_item_1")).size()<=0)
			return;
		
		driver.findElement(By.id("serie_pg_tabs_item_1")).click();
		if (quality.equals(Quality.MP4)&&(driver.findElements(By.id("spge_quality_tabs_mq")).size()>0)){
			driver.findElement(By.id("spge_quality_tabs_mq")).click();
		}else if(quality.equals(Quality.HD)&&(driver.findElements(By.id("spge_quality_tabs_bd")).size()>0)){
			driver.findElement(By.id("spge_quality_tabs_bd")).click();
		}else{
			return;
		}
		
		while(driver.findElement(By.id("serie_epby_link_loader")).isDisplayed());
		
		for (WebElement e : driver.findElements(By.className("serie_epby_link_content"))){
			if (!e.isDisplayed())
				continue;
			for (WebElement elem : e.findElements(By.className("serie_epby_link_item"))){
				elem = elem.findElement(By.tagName("a"));
				Episode ep = new Episode("",Integer.valueOf(elem.getText().split(" ")[1]),quality.toString());
				ep.addMirror("Unknown", elem.getAttribute("href"));
				if (episodes.contains(ep)){
					episodes.get(episodes.indexOf(ep)).mergeMirrors(ep);
				}else{
					episodes.add(ep);
				}
			}
		}
		
	}

	private static Episode getLastEpisode(WebDriver driver, Quality quality) {
		clickToShowLinks(driver, quality);
		Episode episode = null;
		for (WebElement e : driver.findElements(By
				.cssSelector(".tc_content_item,.tc_title"))) {

			try {
				String href = e.findElement(By.tagName("a")).getAttribute(
						"href");
				String text = e.findElement(By.tagName("a")).getText();
				if (!text.isEmpty() && href.split("/").length > 1
						&& episode != null)
					episode.addMirror(text, href);
			} catch (org.openqa.selenium.NoSuchElementException exc) {
				String str = e.getText().replaceAll("[^0123456789]", "");
				if (!str.isEmpty())
					episode = new Episode("", Integer.valueOf(str),
							quality.toString());
			}
		}
		return episode;
	}

	private static Episode getSpecificEpisode(WebDriver driver,
			Quality quality, int number) {
		clickToShowLinks(driver, quality);
		Episode episode = null;
		for (WebElement e : driver.findElements(By
				.cssSelector(".tc_content_item,.tc_title"))) {

			try {
				String href = e.findElement(By.tagName("a")).getAttribute(
						"href");
				String text = e.findElement(By.tagName("a")).getText();
				if (!text.isEmpty() && href.split("/").length > 1
						&& episode != null)
					episode.addMirror(text, href);
			} catch (org.openqa.selenium.NoSuchElementException exc) {
				String str = e.getText().replaceAll("[^0123456789]", "");
				if (!str.isEmpty()) {
					if (episode != null)
						break;
					if (Integer.valueOf(str).equals(number))
						episode = new Episode("", Integer.valueOf(str),
								quality.toString());
				}
			}
		}
		return episode;
	}

	private static String protectedBy(String link, WebDriver driver) {
		driver.get(link);

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("link")));

		return (driver.findElement(By.id("link"))
				.findElement(By.tagName("div")).getText());
	}

	private static void removeProtectionLinks(Episode episode, WebDriver driver) {
		for (Mirror m : episode.getMirrors()) {
			if (m.getLink().split("/")[2].equals("www.animakai.tv")
					|| m.getLink().split("/")[2].equals("www.otakai.com.br"))
				m.setLink(protectedBy(m.getLink(), driver));
		}
	}

	private static void getAnimesFromPage(List<WebElement> elements,
			ArrayList<Anime> animes) {
		AnimaKai f = null;
		for (WebElement e : elements) {
			e = e.findElement(By.className("sl_title")).findElement(
					By.tagName("a"));
			String id = AnimaKai.parserURL(e.getAttribute("href"));
			Anime anime = new Anime(e.getText());
			f = new AnimaKai(id);
			anime.addFansub(f);
			animes.add(anime);
		}
	}

	// Public methods
	public AnimaKai() {
		super("AnimaKai", "http://www.animakai.tv/");
		setAnimePage("http://www.animakai.tv/anime/*/");
		setListPage("http://www.animakai.tv/animes/*/");
		setId("");
	}

	public AnimaKai(String id) {
		super("AnimaKai", "http://www.animakai.tv/");
		setAnimePage("http://www.animakai.tv/anime/*/");
		setListPage("http://www.animakai.tv/animes/*/");
		setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PreparedLink getAnimePage() {
		this.animePage.set(0, getId());
		return animePage;
	}

	public void setAnimePage(String animePage) {
		this.animePage = new PreparedLink(animePage);
	}

	public void setAnimePage(PreparedLink animePage) {
		this.animePage = animePage;
	}

	public void setListPage(String listPage) {
		this.listPage = new PreparedLink(listPage);
	}

	public void setListPage(PreparedLink listPage) {
		this.listPage = listPage;
	}

	public PreparedLink getListPage() {
		return this.listPage;
	}

	public void setListPageIndex(String index) {
		getListPage().set(0, index);
	}

	private static String parserURL(String url) {
		String[] tmp = url.split("/");
		return tmp[4];
	}

	@Override
	public Episode getLastEpisode(Quality quality) {
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		driver.get(getAnimePage().toString());
		Episode ep = getLastEpisode(driver, quality);
		removeProtectionLinks(ep, driver);
		DriverManager.free(driver);
		return ep;
	}

	@Override
	public List<Episode> getAllEpisodes(Quality quality) {
		List<Episode> episodes = new ArrayList<Episode>();
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		driver.get(getAnimePage().toString());
		getByIndividualEpisode(episodes, quality, driver);
		getByServer(episodes,quality,driver);
		for (Episode e : episodes)
			removeProtectionLinks(e, driver);
		DriverManager.free(driver);
		return episodes;
	}

	@Override
	public Episode getEpisode(int number, Quality quality) {
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		driver.get(getAnimePage().toString());
		Episode ep = getSpecificEpisode(driver, quality, number);
		removeProtectionLinks(ep, driver);
		DriverManager.free(driver);
		return ep;
	}

	public static List<Anime> getAllAnimes() {
		ArrayList<Anime> animeList = new ArrayList<Anime>();
		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		int page = 1;
		List<WebElement> elements = null;
		do {
			try {
				System.out.println("http://www.animakai.tv/animes/" + (page) + "/");
				driver.get("http://www.animakai.tv/animes/" + (page++) + "/");
				elements = driver.findElements(By.className("sl_details "));
				getAnimesFromPage(elements, animeList);
			} catch (Exception e) {
				page--;
			}
		} while (elements.size() > 0);
		DriverManager.free(driver);
		return animeList;
	}

	public String toString() {
		return getName() + ":" + getId();
	}
}
