package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image icon;

	public ImagePanel() throws IOException {
		setLayout(new BorderLayout(0, 0));
		icon = ImageIO.read(new File("images/image-not-available_39.jpg"));
	}

	public ImagePanel(String teste) throws IOException {
		setLayout(new BorderLayout(0, 0));
		icon = ImageIO.read(new File(teste));
	}


	@Override
	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		G.drawImage(icon, 0, 0, 200, 286, null);
	}
}
