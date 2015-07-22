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
		setSearchQuery("http://www.anime-planet.com/anime/all?name=*&include_types=5");
		setTitle("");
		setInfoPage("");
	}

	public AnimePlanet(String title) {
		setSearchQuery("http://www.anime-planet.com/anime/all?name=*&include_types=5");
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
			Element title = doc.select("span[itemprop = name]").first();
			Element year = doc.getElementsByClass("nobr").first();
			Element description = doc.select("p[itemprop = description]").first();
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
		try {
			doc = Jsoup.connect(getSearchQuery()).get();
			Element result = doc.getElementsByClass("result_text").first();
			setInfoPage(result.child(0).attr("abs:href"));
			setShow();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
