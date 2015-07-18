package enrico;

import java.io.IOException;
import enricoDAO.AnimeDAO;
import fansubs.AnimaKai;

public class Rodolfo {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		//PunchSub p = new PunchSub("567",
		//		"http://punchsub.com/#listar/*/episodios/*/*");
		//System.out.println(p.getLastEpisode(Quality.HD).toString());
		
		Anime a = new Anime(1, "Berserk", "Fudião matador", "1900", "Finalizado");
		AnimeDAO.createTable();
		AnimeDAO.insert(a);
		
		AnimaKai ank = new AnimaKai("1888");	
		Episode ep1 = ank.getEpisode(3, Quality.MP4);
		ank = new AnimaKai("1912");
		Episode ep2 = ank.getEpisode(3, Quality.MP4);
		
		System.out.println(ep1);
		System.out.println(ep2);
		
		ep1.mergeMirrors(ep2);
		System.out.println(ep1);
	}
}