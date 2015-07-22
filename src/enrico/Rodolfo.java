package enrico;

import java.io.IOException;
import java.util.ArrayList;

import fansubs.VisionSub;

public class Rodolfo {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		//PunchSub p = new PunchSub();
		// ArrayList<Anime> animes = p.getAllAnimes();
		// ArrayList<Anime> animes1 = (ArrayList<Anime>)
		// AnimaKai.getAllAnimes();
		ArrayList<Anime> animes2 = (ArrayList<Anime>) VisionSub.getAllAnimes();

		for (Anime a : animes2)
			System.out.println(a.toString());
	}
}