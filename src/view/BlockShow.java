package view;

import javax.swing.JPanel;

import java.io.IOException;

import javax.swing.JLabel;
import java.awt.Font;

public class BlockShow extends JPanel {

	private static final long serialVersionUID = 1L;

	public BlockShow() throws IOException {
		setLayout(null);

		JPanel imagePanel = new ImagePanel();
		imagePanel.setBounds(0, 0, 200, 286);
		add(imagePanel);
		JLabel title = new JLabel("Naruto Shippuden");
		title.setFont(new Font("Verdana", Font.BOLD, 18));
		title.setBounds(5, 291, 100, 20);
		add(title);

		JLabel rating = new JLabel("8.0/10.0");
		rating.setFont(new Font("Verdana", Font.BOLD, 12));
		rating.setBounds(5, 311, 100, 20);
		add(rating);
	}

	public BlockShow(String teste) throws IOException {
		setLayout(null);
		JPanel imagePanel = new ImagePanel(teste);
		imagePanel.setBounds(0, 0, 200, 286);
		add(imagePanel);
		JLabel title = new JLabel("Title: ");
		title.setFont(new Font("Verdana", Font.BOLD, 18));
		title.setBounds(5, 291, 100, 20);
		add(title);
	}
}
