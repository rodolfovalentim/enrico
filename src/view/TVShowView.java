package view;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TVShowView extends ScrollableContent {

	private static final long serialVersionUID = 1L;
	private int size;
	
	public TVShowView() throws IOException {
		setLayout(null);
		JPanel image = new ImagePanel();
		image.setBounds(0, 0, 195, 266);
		add(image);

		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setBounds(214, 23, 279, 15);
		add(lblNewLabel);
		
		JLabel lbl2NewLabel = new JLabel("Rating");
		lbl2NewLabel.setBounds(214, 50, 279, 15);
		add(lbl2NewLabel);


		JLabel lblNewLabel_1 = new JLabel( "<html><p>This is a long paragraph and I want it to break on its own.  " + 
			    "This is a long paragraph and I want it to break on its own.  " +
			    "This is a long paragraph and I want it to break on its own.  " +
			    "This is a long paragraph and I want it to break on its own.</p></html>");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(214, 77, 279, 189);
		add(lblNewLabel_1);
		
		JLabel lblDownload = new JLabel("Download");
		lblDownload.setBounds(214, 279, 86, 15);
		add(lblDownload);
		
		JLabel label = new JLabel("Link 1");
		label.setBounds(214, 299, 279, 15);
		add(label);
		
		JLabel label1 = new JLabel("Link 2");
		label1.setBounds(214, 314, 279, 15);
		add(label1);
		
		JLabel lblAssistir = new JLabel("Watch");
		lblAssistir.setBounds(10, 278, 86, 15);
		add(lblAssistir);
		
		size = 1000;
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1200, size);
	}
}
