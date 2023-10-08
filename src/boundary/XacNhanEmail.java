package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.DAO_TaiKhoan;
import entity.TaiKhoan;

public class XacNhanEmail extends JFrame {

	private JPanel contentPane;
	private JTextField txtXacNhan;
	private JButton btnXacNhan;
	private JLabel lblNewLabel_1;
	private DAO_TaiKhoan dao_TaiKhoan;
	private JLabel lblError;
	private JLabel lblNewLabel_2;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					XacNhanEmail frame = new XacNhanEmail();
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
	public XacNhanEmail(TaiKhoan taiKhoan,String key) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 255);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Xác Nhận");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(54, 72, 148, 30);
		contentPane.add(lblNewLabel);
		
		txtXacNhan = new JTextField() {
			  
			   @Override protected void paintComponent(Graphics g) {
			     if (!isOpaque()) {
			       int w = getWidth() - 1;
			       int h = getHeight() - 1;
			       Graphics2D g2 = (Graphics2D) g.create();
			       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			       g2.setPaint(UIManager.getColor("TextField.background"));
			       g2.fillRoundRect(0, 0, w, h, h, h);
			       g2.setPaint(Color.GRAY);
			       g2.drawRoundRect(0, 0, w, h, h, h);
			       g2.dispose();
			     }
			     super.paintComponent(g);
			   }

			   @Override public void updateUI() {
			     super.updateUI();
			     setOpaque(false);
			     setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			   }
			 };
		txtXacNhan.setBounds(225, 72, 314, 30);
		contentPane.add(txtXacNhan);
		txtXacNhan.setColumns(10);
		
		btnXacNhan = new JButton("Xác Nhận") {
			  
			   @Override protected void paintComponent(Graphics g) {
			     if (!isOpaque()) {
			       int w = getWidth() - 1;
			       int h = getHeight() - 1;
			       Graphics2D g2 = (Graphics2D) g.create();
			       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			       g2.setPaint(UIManager.getColor("TextField.background"));
			       g2.fillRoundRect(0, 0, w, h, h, h);
			       g2.setPaint(Color.GRAY);
			       g2.drawRoundRect(0, 0, w, h, h, h);
			       g2.dispose();
			     }
			     super.paintComponent(g);
			   }

			   @Override public void updateUI() {
			     super.updateUI();
			     setOpaque(false);
			     setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
			   }
			 };
		btnXacNhan.setForeground(new Color(0, 0, 128));
		btnXacNhan.setBackground(new Color(0, 255, 255));
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyInput = txtXacNhan.getText();
				if(key.equalsIgnoreCase(keyInput)) {
					dispose();
					new CapNhatMK(taiKhoan).setVisible(true);
				}else {
					lblError.setText("Mã Xác Nhận Không Chính Xác");
					txtXacNhan.setText("");
				}
			}
		});
		btnXacNhan.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnXacNhan.setBounds(462, 156, 125, 30);
		contentPane.add(btnXacNhan);
		
		lblNewLabel_1 = new JLabel("Vui Lòng Nhập Vào Mã Đã Gửi");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(176, 10, 431, 38);
		contentPane.add(lblNewLabel_1);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblError.setBounds(235, 112, 304, 20);
		contentPane.add(lblError);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(XacNhanEmail.class.getResource("/image/test1..jpg")));
		lblNewLabel_2.setBounds(0, 0, 623, 235);
		contentPane.add(lblNewLabel_2);
	}
}
