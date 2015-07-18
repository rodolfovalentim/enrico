package enrico;

import java.io.IOException;

import enricoDAO.AnimeDAO;

import punchsub.PunchSub;

public class Main {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		//PunchSub p = new PunchSub("567",
		//		"http://punchsub.com/#listar/*/episodios/*/*");
		//System.out.println(p.getLastEpisode(Quality.HD).toString());
		
		Anime a = new Anime(1, "Berserk", "Fudião matador", "1900", "Finalizado");
		AnimeDAO.createTable();
		AnimeDAO.insert(a);
		
	}
}