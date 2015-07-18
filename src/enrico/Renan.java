package enrico;

import fansubs.Fansub;
import fansubs.VisionSub;

public class Renan {

	public static void main(String[] args) {
		Fansub vision = new VisionSub("kuroko-no-basket-3");
		System.out.println(vision.getLastEpisode(Quality.HD));

	}

}
