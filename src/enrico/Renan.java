package enrico;

import fansubs.AnimaKai;
import fansubs.Fansub;

public class Renan {

	public static void main(String[] args) {
		Fansub vision = new AnimaKai("1885");
		for (Mirror m : vision.getEpisode(2,Quality.HD).getMirrors())
			System.out.println(m.download);
	}

}
