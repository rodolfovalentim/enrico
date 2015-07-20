package fansubs;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enrico.Anime;
import enrico.Episode;
import enrico.Mirror;
import enrico.Quality;
import linkapi.PreparedLink;

public class AnimaKai extends Fansub {

	//Private methods
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
					episodes.add(new Episode("", Integer.valueOf(str)));
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
					episode = new Episode("", Integer.valueOf(str));
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
						episode = new Episode("", Integer.valueOf(str));
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
// Public methods
	public AnimaKai(String id) {
		super(id);
		preparedLink = new PreparedLink("http://www.animakai.tv/anime/*/");
		preparedLink.set(0, id);
	}

	@Override
	public Episode getLastEpisode(Quality quality) {
		WebDriver driver = new PhantomJSDriver();
		driver.get(preparedLink.toString());
		Episode ep = getLastEpisode(driver, quality);
		removeProtectionLinks(ep, driver);
		driver.close();
		return ep;
	}

	@Override
	public List<Episode> getAllEpisodes(Quality quality) {
		List<Episode> episodes = new ArrayList<Episode>();
		WebDriver driver = new PhantomJSDriver();
		driver.get(preparedLink.toString());
		getByIndividualEpisode(episodes, quality, driver);
		for (Episode e : episodes)
			removeProtectionLinks(e, driver);
		driver.close();
		return episodes;
	}

	@Override
	public Episode getEpisode(int number, Quality quality) {
		WebDriver driver = new PhantomJSDriver();
		driver.get(preparedLink.toString());
		Episode ep = getSpecificEpisode(driver, quality, number);
		removeProtectionLinks(ep, driver);
		driver.close();
		return ep;
	}

	public static void getAllAnimes(List<Anime> animeList){
		WebDriver driver = new PhantomJSDriver();
		
	}
	
}
