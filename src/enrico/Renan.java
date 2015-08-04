package enrico;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import download.File4Go;
import enricoDAO.AnimeDAO;
import fansubs.AnimaKai;
import fansubs.Fansub;


public class Renan {

	public static void mergeList(List<Anime> l1,List<Anime> l2){
		List<Anime> animes = new ArrayList<Anime>();
		double highestSimilarity;		

		for (Anime va : l2) {
			Anime similar = null;
			highestSimilarity = 0;
			for (Anime aka : l1) {
				double sim = StringSimilarity.similarity(aka.name, va.name);
				if (sim > highestSimilarity && sim > 0.68) {
					highestSimilarity = sim;
					similar = aka;
				}
			}
			if (similar != null)
				similar.mergeFansubs(va);
			else
				animes.add(va);
		}

		l1.addAll(animes);
		
	}
	
	public static List<Episode> getEpisodes(String animeName, Quality quality){
		AnimeDAO dao = new AnimeDAO();
		List<Episode> episodes = new ArrayList<Episode>();
		Anime toDownload = dao.getByName(animeName);
		for (Fansub f : toDownload.getFansubs()){
			for(Episode e : f.getAllEpisodes(quality)){
				if (episodes.contains(e)){
					episodes.get(episodes.indexOf(e)).mergeMirrors(e);
				}else{
					episodes.add(e);
				}
			}
		}
		return episodes;
	}
	
	public static void main(String[] args) {
		
		/*
		DesiredCapabilities c = DesiredCapabilities.firefox();

		c.setJavascriptEnabled(true);

		WebDriver driver = new HtmlUnitDriver(c);
		
		DriverManager.newDriver(driver);
		DriverManager.free(driver);
		
		AnimeDAO dao = new AnimeDAO();
		for(Anime a : dao.getAll()){
			
			while(true){
				try{
					Hummingbird db = new Hummingbird(a.getName());
					System.out.println(a.getName()+" -- "+db.getTitle());
					break;
				}catch(Exception e){
					System.out.println("ERRO::"+e);
					DriverManager.free(driver);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			
	
		}
		
		driver = (HtmlUnitDriver)DriverManager.getDriver(DriverType.HTML_UNIT);
		driver.close();
		DriverManager.removeAllDrivers();
		*/
		
		/*
		WebDriver driver = new FixedPhantomJSDriver();	
		DriverManager.newDriver(driver);
		DriverManager.free(driver);
		driver = new FirefoxDriver();
		DriverManager.newDriver(driver);
		DriverManager.free(driver);
		*/
		
		/*driver = new FixedPhantomJSDriver();
		DriverManager.newDriver(driver);
		DriverManager.free(driver);
		driver = new HtmlUnitDriver();
		DriverManager.newDriver(driver);
		DriverManager.free(driver);
		driver = new FirefoxDriver();
		DriverManager.newDriver(driver);
		DriverManager.free(driver);

		AnimeDAO dao = new AnimeDAO();
		List<Episode> episodes = new ArrayList<Episode>();
		Anime toDownload = dao.getByName("Berserk");
		
		toDownload.getAllEpisodes(Quality.MP4);
		episodes = toDownload.getEpisodes();
		
		for (Episode e : episodes){
			for (Mirror m : e.getMirrors()){
				if (m.download != null){
					try{
						System.out.println(m.link);
						m.download.download("C:\\Users\\renan_000\\Videos\\Animes\\Test\\");
						break;
					}catch (Exception exc){
						System.out.println(exc);
					}
				}
			}
		}
		
		driver = DriverManager.getDriver();
		driver.close();
		driver = DriverManager.getDriver();
		driver.close();
		driver = DriverManager.getDriver();
		driver.close();
		driver = DriverManager.getDriver();
		driver.close();
	
	*/
		
	/*
		AnimaKai ank = new AnimaKai("1177");
		
		System.out.println(ank.getAllEpisodes(Quality.MP4));
		
		driver = DriverManager.getDriver();
		driver.close();
		driver = DriverManager.getDriver();
		driver.close()
	*/
		File f;
		try {
			f = new File(Renan.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			System.out.println(f.getName());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
