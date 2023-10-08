package boundary;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PnlTrangChu extends JPanel {

	private JLabel lblBackGround;
	/**
	 * Create the panel.
	 */
	public PnlTrangChu() {
		setLayout(null);
		lblBackGround = new JLabel("");
		lblBackGround.setBounds(0, 0, 931, 508);
		lblBackGround.setIcon(new ImageIcon(PnlTrangChu.class.getResource("/image/background.jpg")));	
		add(lblBackGround);

	}
}
