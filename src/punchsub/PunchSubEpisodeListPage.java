package punchsub;

import java.util.ArrayList;
import linkapi.PreparedLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Episode;

public class PunchSubEpisodeListPage {

	private static String preparedLink = "http://punchsub.com/#listar/*/episodios/*/*";
	private static String[] quality = { "fullhd", "hd", "sd", "mp4" };

	public PunchSubEpisodeListPage(int id) {

		ArrayList<Episode> episodes = new ArrayList<Episode>();

		WebDriver driver = new PhantomJSDriver();
		WebDriver iteratorDriver = new PhantomJSDriver();

		PreparedLink url = new PreparedLink(preparedLink);
		url.set(0, Integer.toString(id));
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
					.className("listagemLinks"));

			int i = 1;

			for (WebElement divBox : episodeBox) {
				java.util.List<WebElement> linksDownload = divBox
						.findElements(By.tagName("a"));

				Episode e = new Episode("", i++);
				String name = "";
				String link = "";

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
			System.out.println("Closed");
		}
	}

	private static void getAllPage(String id, String page) {

	}

	public static void getAll(String id) {

	}

	public static void getLatestIfThereIs(String id, String latest) {

	}

	public static void search(String anime) {

	}

	public String getPreparedLink() {
		return preparedLink;
	}

	public void setPreparedLink(String preparedLink) {
		this.preparedLink = preparedLink;
	}

	public String[] getQuality() {
		return quality;
	}

	public void setQuality(String[] quality) {
		this.quality = quality;
	}

	public PunchSubEpisodeListPage(int id, String quality) {

	}
}
