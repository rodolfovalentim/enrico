package enrico;

import java.io.IOException;
import java.util.ArrayList;

import fansubs.PunchSub;

public class Rodolfo {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		PunchSub p = new PunchSub();
		ArrayList<Anime> animes = p.getAllAnimes();

		for (Anime a : animes)
			System.out.println(a.toString());
	}
}