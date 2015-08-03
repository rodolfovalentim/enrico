package view;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Principal extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.setVisible(true);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 900;

		JPanel content = new ScrollableContent();
		JPanel animes = new HomeAnimes();
		JPanel series = new HomeSeries();
		JPanel movies = new HomeMovies();

		JScrollPane pictureScrollPane1 = new JScrollPane(animes);
		pictureScrollPane1.setBounds(100, 0, 800, 306);

		JScrollPane pictureScrollPane2 = new JScrollPane(series);
		pictureScrollPane2.setBounds(100, 309, 800, 309);

		JScrollPane pictureScrollPane3 = new JScrollPane(movies);
		pictureScrollPane3.setBounds(100, 618, 800, 309);

		content.add(pictureScrollPane1);
		content.add(pictureScrollPane2);
		content.add(pictureScrollPane3);

		JScrollPane pictureScrollPane4 = new JScrollPane(content);
		pictureScrollPane4.setBounds(0, 0, 800, 1200);

		setContentPane(pictureScrollPane4);
	}
}
