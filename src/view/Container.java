package view;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;

public class Container extends ScrollableContent {

	private static final long serialVersionUID = 1L;
	private int size;

	public Container() throws IOException {

		setLayout(null);

		JPanel options = new OptionsPanel();
		options.setBounds(0, 0, 100, 200);
		add(options);

		String[] s = { "akagami-no-shirayukihime-7018.jpg", "gakusen-toshi-asterisk-7160.jpg", "berserk.jpg",
				"garo-the-animation-2-6869.jpg", "bikini-warriors-7232.jpg", "hacka-doll-the-animation-7203.jpg",
				"charlotte-6882.jpg", "haikyuu-2-6871.jpg", "comet-lucifer-7350.jpg", "heavy-object-6724.jpg",
				"concrete-revolutio-choujin-gensou-7313.jpg", "hidan-no-aria-aa-6868.jpg", "danchigai-7017.jpg",
				"high-school-star-musical-7121.jpg", "durarara-x2-ten-6725.jpg", "himouto-umaru-chan-6857.jpg",
				"fushigi-na-somera-chan-7153.jpg", "hokuto-no-ken-ichigo-aji-7269.jpg", "gakkou-gurashi-6511.jpg",
				"image-not-available_39.jpg" };

		int c = 0, d = 0, e = 0;

		for (String a : s) {
			BlockShow b = new BlockShow("images/" + a);
			b.setBounds(120 + c, 20, 200, 335);
			this.add(b);
			c = c + 220;
		}
		size = c;
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(size, 335);
	}
}
