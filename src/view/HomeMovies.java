package view;

import java.awt.Dimension;
import java.io.IOException;

public class HomeMovies extends ScrollableContent {

	private static final long serialVersionUID = -1694096556963281718L;
	private int size;

	public HomeMovies() throws IOException {
		setLayout(null);

		String[] s = { "8.jpg", "expendables-2-movie-poster-comic-con-high-quality.jpg", "gamer-movie-poster.jpg",
				"grey-movie-poster.jpg", "movie-poster-20.jpg", "movie-posters-movies-11136777-810-1200.jpg",
				"Real-Steel-movie-posters-26233237-1079-1600.jpg",
				"Robert-Downey-Jr-SH2-Movie-Posters-robert-downey-jr-26552470-453-660.jpg",
				"Thor_Official_Poster.jpg" };

		int c = 0;

		for (String a : s) {
			BlockShow b = new BlockShow("images/movies/" + a);
			b.setBounds(c, 0, 200, 286);
			this.add(b);
			c = c + 220;
		}
		size = c;

	}
	
	public Dimension getPreferredSize() {
		return new Dimension(size, 286);
	}
	
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}
}
