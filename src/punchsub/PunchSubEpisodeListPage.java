package punchsub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import linkapi.PreparedLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Episode;

public class PunchSubEpisodeListPage {

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
						e.addMirror(name, PunchSubProtectorPage.getWithoutWait(
								iteratorDriver, link));
					}
				}
				episodes.add(e);
			}
			driver.close();
		}
		return episodes;
	}

	public static void getLastEpisode(String id, String latest) {
		WebDriver driver = new PhantomJSDriver();

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

		// Hard to do, hard to understand
		if (!episodeBox.get(episodeBox.size() - 2).getText()
				.equals(latest)) {			
			System.out.println("Downloading last episode: " + episodeBox.get(episodeBox.size() - 2).getText());
			System.out.println("Downloading last episode: " + episodeBox.get(episodeBox.size() - 1).getText());
		} else {
			System.out.println("Anime up-to-date");
		}
	}

	public String getPreparedLink() {
		return preparedLink;
	}

	public String[] getQuality() {
		return quality;
	}

	public PunchSubEpisodeListPage(int id, String quality) {

	}
}
