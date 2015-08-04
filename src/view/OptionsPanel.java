package view;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class OptionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public OptionsPanel() {
		setLayout(null);
		
		JLabel quality = new JLabel("Quality");
		quality.setBounds(8, 15, 129, 23);
		add(quality);

		JCheckBox fullHdCheckBox = new JCheckBox("Full HD");
		fullHdCheckBox.setBounds(8, 45, 129, 23);
		add(fullHdCheckBox);

		JCheckBox hdCheckBox = new JCheckBox("HD");
		hdCheckBox.setBounds(8, 70, 129, 23);
		add(hdCheckBox);

		JCheckBox mdCheckBox = new JCheckBox("MD");
		mdCheckBox.setBounds(8, 95, 129, 23);
		add(mdCheckBox);

		JCheckBox sdCheckBox = new JCheckBox("SD");
		sdCheckBox.setBounds(8, 120, 129, 23);
		add(sdCheckBox);

	}
}
