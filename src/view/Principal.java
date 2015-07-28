package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JScrollBar;

public class Principal {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() throws IOException {
		initialize();

	}

	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenuItem mntmAnimes = new JMenuItem("Animes");
		menuBar.add(mntmAnimes);

		JMenuItem mntmSeries = new JMenuItem("Series");
		menuBar.add(mntmSeries);

		JMenuItem mntmMovies = new JMenuItem("Movies");
		menuBar.add(mntmMovies);
		frame.getContentPane().setLayout(null);

		JPanel container = new JPanel();
		container.setLayout(null);

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
			b.setBounds(20 + c, 20 + d, 200, 335);
			container.add(b);
			c = c + 220;
			e++;

			if (e == 5) {
				c = 0;
				d = d + 300;
				e = 0;
			}
		}

		JScrollPane scrollPane = new JScrollPane(container);
		scrollPane.setBounds(0, 0, 1200, 800);
		scrollPane.setVisible(true);
		scrollPane.setAutoscrolls(true);
		scrollPane.setPreferredSize(new Dimension(1200, 800));
		frame.getContentPane().add(scrollPane);

	}
}
