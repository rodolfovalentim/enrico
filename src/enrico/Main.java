package enrico;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import animakai.AnimaKaiEpisodeListPage;
import fansubs.FansubAnimePage;

public class Main {

	void file4GoDownload(WebDriver driver, String link) {

	}

	public static void main(String[] args) throws InterruptedException,
			IOException {

		FansubAnimePage rokkaAnik = new AnimaKaiEpisodeListPage(8);
		System.out.println(rokkaAnik.getAllEpisodes(Quality.FullHD));
		// PunchSubEpisodeListPage teste = new PunchSubEpisodeListPage(567);

		/*
		 * WebDriver driver = new PhantomJSDriver();
		 * driver.get("http://punchsub.com/#listar/658/episodios/hd/1");
		 * Document doc = Jsoup.parse(driver.getPageSource());
		 * 
		 * Elements paging = doc.select("a.eplist"); int numberOfPages =
		 * paging.size();
		 * 
		 * for (int i = 1; i <= numberOfPages; i++) { Element load =
		 * doc.getElementById("load"); Elements links =
		 * load.select("[href^=download/]");
		 * 
		 * for (Element l : links) { System.out.println("punchsub.com/" +
		 * l.attr("href")); }
		 * 
		 * driver.get("http://punchsub.com/#listar/658/episodios/hd/" + i + 1);
		 * driver.navigate().refresh(); doc =
		 * Jsoup.parse(driver.getPageSource()); }
		 * 
		 * driver.close();
		 * 
		 * System.out.println("Closed");
		 */
	}
}