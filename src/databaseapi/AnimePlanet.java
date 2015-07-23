package databaseapi;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import enrico.TVShow;
import linkapi.PreparedLink;

public class AnimePlanet {
	private String title;
	private PreparedLink searchQuery;
	private String infoPage;
	private TVShow show;

	public AnimePlanet() {
		setSearchQuery("http://www.anime-planet.com/anime/all?name=*&include_types=6");
		setTitle("");
		setInfoPage("");
	}

	public AnimePlanet(String title) {
		setSearchQuery("http://www.anime-planet.com/anime/all?name=*&include_types=6");
		setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSearchQuery() {
		searchQuery.set(0, getTitle());
		return searchQuery.toString();
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = new PreparedLink(searchQuery);
	}

	public String getInfoPage() {
		return infoPage;
	}

	public void setInfoPage(String infoPage) {
		this.infoPage = infoPage;
	}

	public TVShow getShow() {
		return show;
	}

	public void setShow() {
		String regex = "\\((\\d+–\\d+)\\)";
		Pattern p = Pattern.compile(regex);
		String status = "Unknown";
		Document doc;

		try {
			doc = Jsoup.connect(getInfoPage()).get();
			Element title = doc.select("div[itemprop = name]").first();
			Element year = doc.getElementsByClass("iconYear").first();
			Element description = doc.select("div[itemprop = description]").first();
			Matcher m = p.matcher(year.text());

			if (m.matches())
				status = "Finished";
			else
				status = "On going";

			this.show = new TVShow(title.text(), description.text(), year.text(), status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setShow(TVShow show) {
		this.show = show;
	}

	public boolean search() {
		Document doc;
		Element name = null;
		Element description = null;
		Element year = null;

		try {
			doc = Jsoup.connect(getSearchQuery())
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0").get();

			if (doc.title().contains("Browse All Anime")) {
				if (doc.getElementsByClass("error").size() > 0){
					System.out.println(getTitle() + ": No results found");
					return false;
				}
				
				Element result = doc.getElementsByClass("card").first().child(0);
				Document alt = Jsoup.parse(result.attr("title"));
				name = alt.getElementsByTag("h5").first();
				description = alt.getElementsByTag("p").first();
				year = alt.getElementsByClass("iconYear").first();				

			} else {
				name = doc.select("h1[itemprop = name").first();
				description = doc.select("div[itemprop = description").first();
				year = doc.select("span[class = iconYear]").first();

			}
			
			if(name != null)
				System.out.println("Procurando: " + getTitle() + " Achou :" + name.text());
			if(description != null)
				System.out.println(description.text());
			if(year != null)
				System.out.println(year.text());
			// setInfoPage(result.child(0).attr("abs:href"));
			// setShow();
			return true;
		} catch (IOException e) {
			search();
			return false;
		}
	}
}
