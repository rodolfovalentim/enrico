package enrico;

import java.util.ArrayList;
import java.util.List;

import fansubs.AnimaKai;
import fansubs.VisionSub;

public class Renan {

	public static void main(String[] args) {
		List<Anime> animaKaiAnimes = AnimaKai.getAllAnimes();
		List<Anime> visionSubAnimes = VisionSub.getAllAnimes();

		int similares;
		double highestSimilarity;
		String similarest = "";

		System.out.println(animaKaiAnimes.size() + " : "
				+ visionSubAnimes.size());

		for (Anime va : visionSubAnimes) {
			similares = 0;
			highestSimilarity = 0;
			System.out.print(va.name + " : ");
			for (Anime aka : animaKaiAnimes) {
				double sim = StringSimilarity.similarity(aka.name, va.name);
				if (sim > 0.85) {
					similares++;
				}
				if (sim > highestSimilarity) {
					highestSimilarity = sim;
					similarest = aka.name;
				}
			}
			System.out.println(similarest + " (" + similares + " : "
					+ highestSimilarity + ")");
		}

	}

}
