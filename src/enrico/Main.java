package enrico;

import java.io.IOException;
import punchsub.PunchSub;

public class Main {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		System.out.println(PunchSub.getLastEpisode("567", "Episódio 12")
				.toString());
	}
}