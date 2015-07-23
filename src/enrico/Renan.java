package enrico;

import java.util.ArrayList;
import java.util.List;

import databaseapi.Hummingbird;
import enricoDAO.AnimeDAO;

public class Renan {

	public static void mergeList(List<Anime> l1,List<Anime> l2){
		List<Anime> animes = new ArrayList<Anime>();
		double highestSimilarity;		

		for (Anime va : l2) {
			Anime similar = null;
			highestSimilarity = 0;
			for (Anime aka : l1) {
				double sim = StringSimilarity.similarity(aka.name, va.name);
				if (sim > highestSimilarity && sim > 0.68) {
					highestSimilarity = sim;
					similar = aka;
				}
			}
			if (similar != null)
				similar.mergeFansubs(va);
			else
				animes.add(va);
		}

		l1.addAll(animes);
		
	}
	
	public static void main(String[] args) {
		AnimeDAO dao = new AnimeDAO();
		for(Anime a : dao.getAll(id))
		Hummingbird db = new Hummingbird("Berserk");
		System.out.println(db.getInfoPage());
		//db.searchSoup();
	}
	

}
