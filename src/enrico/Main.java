package enrico;

import java.io.IOException;
import punchsub.PunchSubEpisodeListPage;

public class Main {

	public static void main(String[] args) throws InterruptedException,
			IOException {

		PunchSubEpisodeListPage.getLastEpisode("567", "Episódio 11");
	}
}