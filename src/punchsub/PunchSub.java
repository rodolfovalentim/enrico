package punchsub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fansubs.*;
import linkapi.PreparedLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Episode;
import enrico.Quality;

public class PunchSub extends Fansub{

	private static String preparedLink = "http://punchsub.com/#listar/*/episodios/*/*";
	private static String[] quality = { "fullhd", "hd", "sd", "mp4" };

	public static ArrayList<Episode> getAllEpisodes(String id) {

		ArrayList<Episode> episodes = new ArrayList<Episode>();

		WebDriver driver = new PhantomJSDriver();
		WebDriver iteratorDriver = new PhantomJSDriver();

		PreparedLink url = new PreparedLink(preparedLink);
		url.set(0, id);
		url.set(1, quality[1]);
		url.set(2, "1");

		driver.get(url.toString());

		java.util.List<WebElement> paging = driver.findElements(By
				.className("epList"));

		int numberOfPages = paging.size();

		for (int j = 1; j <= numberOfPages; j++) {

			url.set(2, String.valueOf(j));
			driver.get(url.toString());
			driver.navigate().refresh();

			java.util.List<WebElement> episodeBox = driver.findElements(By
					.cssSelector(".listagemLinks,.listagemEp"));

			String title = "";
			String name = "";
			String link = "";
			String[] arr = null;

			for (Iterator<WebElement> tuplas = episodeBox.iterator(); tuplas
					.hasNext();) {
				title = tuplas.next().getText();
				arr = title.split(" ", 2);
				Episode e = new Episode(title, Integer.valueOf(arr[1]));

				java.util.List<WebElement> linksDownload = tuplas.next()
						.findElements(By.tagName("a"));

				for (WebElement l : linksDownload) {
					name = l.getText();
					link = l.getAttribute("href");
					if (!name.startsWith("Link")) {
						e.addMirror(name, PunchSub.getWithoutWait(
								iteratorDriver, link));
					}
				}
				episodes.add(e);
			}
			driver.close();
		}
		return episodes;
	}

	public static Episode getLastEpisode(String id, String latest) {
		WebDriver driver = new PhantomJSDriver();
		WebDriver iteratorDriver = new PhantomJSDriver();

		PreparedLink url = new PreparedLink(preparedLink);
		url.set(0, id);
		url.set(1, quality[1]);
		url.set(2, "1");

		driver.get(url.toString());

		java.util.List<WebElement> paging = driver.findElements(By
				.className("epList"));
		int numberOfPages = paging.size();
		url.set(2, String.valueOf(numberOfPages));

		driver.get(url.toString());
		driver.navigate().refresh();

		java.util.List<WebElement> episodeBox = driver.findElements(By
				.cssSelector(".listagemLinks,.listagemEp"));

		String title = episodeBox.get(episodeBox.size() - 2).getText();
		String[] arr = null;
		String name = "";
		String link = "";

		// Hard to do, hard to understand
		if (!title.equals(latest)) {
			arr = title.split(" ", 2);
			Episode e = new Episode(title, Integer.valueOf(arr[1]));

			java.util.List<WebElement> linksDownload = episodeBox.get(
					episodeBox.size() - 1).findElements(By.tagName("a"));

			for (WebElement l : linksDownload) {
				name = l.getText();
				link = l.getAttribute("href");
				if (!name.startsWith("Link")) {
					e.addMirror(name, PunchSub.getWithoutWait(
							iteratorDriver, link));
				}
			}
			return e;
		} else {
			System.out.println("Anime up-to-date");
			return null;
		}
	}

	public String getPreparedLink() {
		return preparedLink;
	}

	public String[] getQuality() {
		return quality;
	}

	public PunchSub(int id, String quality) {

	}
	
	public static String getWithoutWait(WebDriver driver, String relativeUrl) {

		driver.get(relativeUrl);
		WebElement pulaBottom = driver.findElement(By
				.className("download-pular"));
		pulaBottom.click();
		WebElement divBottom = driver.findElement(By.id("download-botao"));
		return divBottom.getAttribute("href");
	}

	@Override
	public Episode getLastEpisode(Quality quality) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Episode> getAllEpisodes(Quality quality) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Episode getEpisode(int number, Quality quality) {
		// TODO Auto-generated method stub
		return null;
	}
}
