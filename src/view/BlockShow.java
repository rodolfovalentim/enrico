package view;

import javax.swing.JPanel;
import java.io.IOException;
import javax.swing.JLabel;

public class BlockShow extends JLabel {

	private static final long serialVersionUID = 1L;

	public BlockShow() throws IOException {
		setLayout(null);
		JPanel imagePanel = new ImagePanel();
		imagePanel.setBounds(0, 0, 200, 286);
		add(imagePanel);
	}

	public BlockShow(String teste) throws IOException {
		setLayout(null);
		JPanel imagePanel = new ImagePanel(teste);
		imagePanel.setBounds(0, 0, 200, 286);
		add(imagePanel);
	}
}
