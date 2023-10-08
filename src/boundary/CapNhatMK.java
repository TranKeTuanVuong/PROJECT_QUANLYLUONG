package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.DAO_TaiKhoan;
import entity.TaiKhoan;

public class CapNhatMK extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPass;
	private JPasswordField txtRequirePass;
	private DAO_TaiKhoan dao_TaiKhoan;
	private JButton btnNewButton;
	private JLabel lblError;
	private JLabel lbErrorPass1;
	private JLabel lblErrorPass2;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CapNhatMK frame = new CapNhatMK();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CapNhatMK(TaiKhoan taiKhoan) {

		try {
			connect.ConnectDB.getInstance().connect();
			dao_TaiKhoan = new DAO_TaiKhoan();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connect.ConnectDB.getInstance().disconnect();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 334);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mật khẩu mới: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(91, 81, 119, 27);
		contentPane.add(lblNewLabel);

		JLabel lblXcNhnMt = new JLabel("Xác nhận mật khẩu: ");
		lblXcNhnMt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblXcNhnMt.setBounds(91, 161, 129, 27);
		contentPane.add(lblXcNhnMt);

		txtPass = new JPasswordField() {
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque()) {
					int w = getWidth() - 1;
					int h = getHeight() - 1;
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w, h, h, h);
					g2.setPaint(new Color(176, 224, 230));
					g2.drawRoundRect(0, 0, w, h, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				// setBackground(new Color(0x0, true));
				setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			}
		};
		txtPass.setBounds(241, 82, 363, 27);
		contentPane.add(txtPass);
		txtPass.setColumns(10);

		txtRequirePass = new JPasswordField() {
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque()) {
					int w = getWidth() - 1;
					int h = getHeight() - 1;
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w, h, h, h);
					g2.setPaint(new Color(176, 224, 230));
					g2.drawRoundRect(0, 0, w, h, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				// setBackground(new Color(0x0, true));
				setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			}
		};
		txtRequirePass.setColumns(10);
		txtRequirePass.setBounds(241, 162, 363, 27);
		contentPane.add(txtRequirePass);

		btnNewButton = new JButton("Đổi mật khẩu") {
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque()) {
					int w = getWidth() - 1;
					int h = getHeight() - 1;
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setPaint(UIManager.getColor("TextField.background"));
					g2.fillRoundRect(0, 0, w, h, h, h);
					g2.setPaint(new Color(176, 224, 230));
					g2.drawRoundRect(0, 0, w, h, h, h);
					g2.dispose();
				}
				super.paintComponent(g);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				// setBackground(new Color(0x0, true));
				setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			}
		};
		btnNewButton.setForeground(new Color(0, 0, 139));
		btnNewButton.setBackground(new Color(175, 238, 238));

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check() == true) {
					if (txtPass.getText().equals(txtRequirePass.getText())) {
						if (dao_TaiKhoan.updateAccount(taiKhoan.getTenTK(), txtPass.getText()) == true) {
							JOptionPane.showMessageDialog(null, "Cập nhật thành công");
							connect.ConnectDB.getInstance().disconnect();
							dispose();
							new Login_Gui().setVisible(true);
						}
					} else {
						lblError.setText("Mật khẩu không giống nhau");
						lbErrorPass1.setText("*");
						lblErrorPass2.setText("*");
						txtPass.setText("");
						txtRequirePass.setText("");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(538, 252, 119, 27);
		contentPane.add(btnNewButton);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblError.setBounds(294, 198, 363, 27);
		contentPane.add(lblError);

		lbErrorPass1 = new JLabel("");
		lbErrorPass1.setForeground(Color.RED);
		lbErrorPass1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lbErrorPass1.setBounds(667, 89, 45, 13);
		contentPane.add(lbErrorPass1);

		lblErrorPass2 = new JLabel("");
		lblErrorPass2.setForeground(Color.RED);
		lblErrorPass2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblErrorPass2.setBounds(667, 169, 45, 13);
		contentPane.add(lblErrorPass2);

		lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setIcon(new ImageIcon(CapNhatMK.class.getResource("/image/test1..jpg")));
		lblNewLabel_1.setBounds(0, 0, 694, 297);
		contentPane.add(lblNewLabel_1);
	}

	public boolean check() {
		boolean check = true;
		if (txtPass.getText().equalsIgnoreCase("") || txtRequirePass.getText().equalsIgnoreCase("")) {
			lblError.setText("");
			lbErrorPass1.setText("");
			lblErrorPass2.setText("");
			txtPass.setText("");
			txtRequirePass.setText("");
			JOptionPane.showMessageDialog(null, "Ko được để trống");
			check = false;
		}
		return check;
	}

}
