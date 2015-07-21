package enrico;

import java.util.List;

import fansubs.AnimaKai;
import fansubs.VisionSub;

public class Renan {

	public static void main(String[] args) {
		List<Anime> animaKaiAnimes = AnimaKai.getAllAnimes();
		List<Anime> visionSubAnimes = VisionSub.getAllAnimes();

		double highestSimilarity;
		Anime similar = null;

		for (Anime va : visionSubAnimes) {
			highestSimilarity = 0;
			System.out.print(va.name + " : ");
			for (Anime aka : animaKaiAnimes) {
				double sim = StringSimilarity.similarity(aka.name, va.name);
				if (sim > highestSimilarity && sim > 0.78) {
					highestSimilarity = sim;
					similar = aka;
				}
			}
			if (similar!=null)
				similar.mergeFansubs(va);
		}
		System.out.println(animaKaiAnimes);
	}

}
