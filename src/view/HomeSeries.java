package view;

import java.awt.Dimension;
import java.io.IOException;

public class HomeSeries extends ScrollableContent {

	private static final long serialVersionUID = -1694096556963281718L;
	private int size;

	public HomeSeries() throws IOException {
		setLayout(null);

		String[] s = { "159933.jpg", "Arrow-Kneeling-drop.jpg", "Fringe-finalStand-drop.jpg",
				"game-of-thrones-tv-show-poster-01.jpg", "girls-s1-poster-1.jpg", "images2.jpeg", "images.jpeg",
				"LucyLiu-Watson-drop.jpg", "Picture-5.png",
				"poster-version-of-the-Catch-VD-the-vampire-diaries-tv-show-18762918-1080-1609.jpg",
				"Rogue-DirecTV-2013-season-1-poster.jpg",
				"tvd-new-poster-the-vampire-diaries-tv-show-26070568-1580-2048.jpg" };

		int c = 0;

		for (String a : s) {
			BlockShow b = new BlockShow("images/tvshows/" + a);
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
