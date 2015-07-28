package fansubs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import linkapi.PreparedLink;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import enrico.Anime;
import enrico.DriverManager;
import enrico.DriverManager.DriverType;
import enrico.Episode;
import enrico.Quality;
import enricoDAO.AnimeDAO;

public class PunchSub extends Fansub {

	private String id;
	private PreparedLink animePage;
	private PreparedLink listPage;

	@Override
	public List<Episode> getAllEpisodes(Quality quality) {

		ArrayList<Episode> episodes = new ArrayList<Episode>();

		WebDriver driver = DriverManager.getDriver(DriverType.PHANTOMJS);
		WebDriver iteratorDriver = DriverManager.getDriver(DriverType.PHANTOMJS);

		setAnimeQuality(renameQuality(quality));
		setAnimePageIndex("1");

		driver.get(getAnimePage().toString());

		java.util.List<WebElement> paging = driver.findElements(By
				.className("epList"));

		int numberOfPages = paging.size();

		if (numberOfPages > 0) {
			for (int j = 1; j <= numberOfPages; j++) {

				setAnimePageIndex(String.valueOf(j));
				driver.get(getAnimePage().toString());
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
					Episode e = new Episode(title, Integer.valueOf(arr[1]),
							quality.toString());

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
			}
			DriverManager.free(driver);
			DriverManager.free(iteratorDriver);
			return episodes;
		} else {
			System.out.println("Quality not available");
			DriverManager.free(driver);
			DriverManager.free(iteratorDriver);
			return null;
		}
	}

	@Override
	public Episode getLastEpisode(Quality quality) {
		WebDriver driver = new PhantomJSDriver();
		WebDriver iteratorDriver = new PhantomJSDriver();

		setAnimeQuality(renameQuality(quality));
		setAnimePageIndex("1");

		driver.get(getAnimePage().toString());

		java.util.List<WebElement> paging = driver.findElements(By
				.className("epList"));

		int numberOfPages = paging.size();

		if (numberOfPages > 0) {
			setAnimePageIndex(String.valueOf(numberOfPages));
			driver.get(getAnimePage().toString());
			driver.navigate().refresh();

			java.util.List<WebElement> episodeBox = driver.findElements(By
					.cssSelector(".listagemLinks,.listagemEp"));

			String title = episodeBox.get(episodeBox.size() - 2).getText();
			String[] arr = null;
			String name = "";
			String link = "";

			// Hard to do, hard to understand
			arr = title.split(" ", 2);
			Episode e = new Episode(title, Integer.valueOf(arr[1]),
					quality.toString());

			java.util.List<WebElement> linksDownload = episodeBox.get(
					episodeBox.size() - 1).findElements(By.tagName("a"));

			for (WebElement l : linksDownload) {
				name = l.getText();
				link = l.getAttribute("href");
				if (!name.startsWith("Link")) {
					e.addMirror(name,
							PunchSub.getWithoutWait(iteratorDriver, link));
				}
			}
			return e;
		} else {
			System.out.println("Quality not available");
			return null;
		}
	}

	public static String getWithoutWait(WebDriver driver, String relativeUrl) {

		driver.get(relativeUrl);
		WebElement pulaBottom = driver.findElement(By
				.className("download-pular"));
		pulaBottom.click();
		WebElement divBottom = driver.findElement(By.id("download-botao"));
		return divBottom.getAttribute("href");
	}

	public ArrayList<Anime> getAllAnimes() {

		ArrayList<Anime> animes = new ArrayList<Anime>();
		Anime anime = null;
		PunchSub pfs = null;
		AnimeDAO animeDao = new AnimeDAO();
		animeDao.createTable();

		WebDriver driver = DriverManager.getDriver();
		
		setListPageIndex("1");
		driver.get(getListPage().toString());

		WebElement paging = driver.findElement(By.className("paginacao"));
		java.util.List<WebElement> pagings = paging.findElements(By
				.className("projList"));

		int numberOfPages = pagings.size();
		String id = "0";
		WebElement a = null;
		java.util.List<WebElement> boxNames = null;

		if (numberOfPages > 0) {
			for (int j = 1; j <= numberOfPages; j++) {

				setListPageIndex(String.valueOf(j));
				driver.get(getListPage().toString());
				driver.navigate().refresh();

				boxNames = driver.findElements(By.className("pNome"));

				for (WebElement e : boxNames) {
					a = e.findElement(By.tagName("a"));
					if (a != null) {
						anime = new Anime(a.getText());
						id = parserURL(a.getAttribute("href"));
						pfs = new PunchSub(id);
						anime.addFansub(pfs);
						animes.add(anime);
					}
					a = null;
				}
			}
			DriverManager.free(driver);
			return animes;
		} else {
			System.out.println("Quality not available");
			DriverManager.free(driver);
			return null;
		}
	}

	@Override
	public Episode getEpisode(int number, Quality quality) {
		// TODO Auto-generated method stub
		return null;
	}

	public PunchSub() {
		super("PunchSub", "http://punchsub.com");
		setAnimePage("http://punchsub.com/#listar/*/episodios/*/*");
		setListPage("http://punchsub.com/#lista-de-animes/nome/todos/*");
	}

	public PunchSub(String id) {
		super("PunchSub", "http://punchsub.com");
		setAnimePage("http://punchsub.com/#listar/*/episodios/*/*");
		setListPage("http://punchsub.com/#lista-de-animes/nome/todos/*");
		setAnimeId(id);
	}

	public void setAnimeId(String id) {
		this.id = id;
	}

	public String getAnimeId() {
		return this.id;
	}

	public void setAnimeQuality(String quality) {
		getAnimePage().set(1, quality);
	}

	public void setAnimePageIndex(String page) {
		getAnimePage().set(2, page);
	}

	public void setListPageIndex(String page) {
		getListPage().set(0, page);
	}

	public PreparedLink getAnimePage() {
		animePage.set(0, getAnimeId());
		return animePage;
	}

	public void setAnimePage(String animePage) {
		this.animePage = new PreparedLink(animePage);
	}

	public PreparedLink getListPage() {
		return listPage;
	}

	public void setListPage(String listPage) {
		this.listPage = new PreparedLink(listPage);
	}

	public String parserURL(String url) {
		String[] tmp = url.split("/");
		return tmp[4];
	}

	private String renameQuality(Quality quality) {
		return quality.toString().toLowerCase();
	}

	public String toString() {
		return getName() + ":" + getAnimeId();
	}
}
