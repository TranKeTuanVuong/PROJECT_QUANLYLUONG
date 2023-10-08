package boundary;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import dao.DAO_TaiKhoan;
import entity.TaiKhoan;

public class Login_Gui extends JFrame{

	
	private JTextField textField;
	private JPasswordField passwordField;
	private DAO_TaiKhoan dao_TaiKhoan;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Login_Gui() {
		try {
			connect.ConnectDB.getInstance().connect();
			
			dao_TaiKhoan = new DAO_TaiKhoan();
			new JFrame();
			getContentPane().setForeground(Color.LIGHT_GRAY);
			getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
			setBackground(Color.WHITE);
			getContentPane().setBackground(Color.WHITE);
			setBounds(100, 100, 873, 384);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			
			JLabel label = new JLabel("");
			
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
				}
			});
			label.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent arg0) {
					
				}
			});
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(47, 79, 79));
			panel.setBounds(483, 0, 387, 353);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("\r\\n");
			lblNewLabel_1.setBounds(294, 89, 30, 29);
			panel.add(lblNewLabel_1);
			lblNewLabel_1.setIcon(new ImageIcon(Login_Gui.class.getResource("/image/mk.jpg")));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			JLabel lblNewLabel = new JLabel("\r\n");
			lblNewLabel.setIcon(new ImageIcon(Login_Gui.class.getResource("/image/tk.jpg")));
			lblNewLabel.setBounds(294, 156, 25, 41);
			panel.add(lblNewLabel);
			
		

			JButton btnQuenMK = new JButton("Quên mật khẩu") {
				   @Override protected void paintComponent(Graphics g) {
				     if (!isOpaque()) {
				       int w = getWidth() - 1;
				       int h = getHeight() - 1;
				       Graphics2D g2 = (Graphics2D) g.create();
				       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				       g2.setPaint(UIManager.getColor("TextField.background"));
				       g2.fillRoundRect(0, 0, w, h, h, h);
				       g2.setPaint(new Color(124, 252, 0));
				       g2.drawRoundRect(0, 0, w, h, h, h);
				       g2.dispose();
				     }
				     super.paintComponent(g);
				   }

				   @Override public void updateUI() {
				     super.updateUI();
				     setOpaque(false);
				     // setBackground(new Color(0x0, true));
				     setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
				   }
				 };
			btnQuenMK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						connect.ConnectDB.getInstance().disconnect();
						dispose();
						new QuenMk_Gui().setVisible(true);
						
				}
			});
			 btnQuenMK.setBackground(new Color(124, 252, 0));
			 btnQuenMK.setForeground(new Color(124, 252, 0));
			 btnQuenMK.setBounds(212, 296, 141, 29);
			panel.add(btnQuenMK);
			
			textField = new JTextField();
			JTextField txtTaiKhoan = new JTextField(20) {
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
				     // setBackground(new Color(0x0, true));
				     setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
				   }
				 };
				 txtTaiKhoan.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							if (txtTaiKhoan.getText().equals("Tên Đăng Nhập")) {
								txtTaiKhoan.setText("");
								txtTaiKhoan.setForeground(new Color(153,153,153));
							}
						}
						@Override
						public void focusLost(FocusEvent e) {
							if (txtTaiKhoan.getText().equals("")) {
								txtTaiKhoan.setText("Tên Đăng Nhập");
								txtTaiKhoan.setForeground(new Color(153,153,153));
							}
						}
					});
					
			txtTaiKhoan.setBackground(Color.GRAY);
			txtTaiKhoan.setBackground(Color.GRAY);
			txtTaiKhoan.setBounds(36, 84, 248, 41);
			txtTaiKhoan.setText("nguyenvanloc");
			panel.add(txtTaiKhoan);
			txtTaiKhoan.setColumns(10);
			
			passwordField = new JPasswordField();
			JPasswordField txtPassWord = new JPasswordField(20) {
				  
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
				 txtPassWord.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							
								if (txtPassWord.getText().equals("Mat Khau")) {
									txtPassWord.setText("");
									txtPassWord.setForeground(new Color(153,153,153));
								}
							}
							@Override
							public void focusLost(FocusEvent e) {
								if (txtPassWord.getText().equals("")) {
									txtPassWord.setText("Mat Khau");
									txtPassWord.setForeground(new Color(153,153,153));
								}
							}
						});
			txtPassWord.setText("12345");
			txtPassWord.setBackground(Color.GRAY);
			txtPassWord.setBounds(36, 156, 248, 41);
			
			panel.add(txtPassWord);
			//		
			//		JButton JButton = new JButton("Login");
					JButton btnDangNhap = new JButton("Đăng nhập\r\n") {
						   @Override protected void paintComponent(Graphics g) {
						     if (!isOpaque()) {
						       int w = getWidth() - 1;
						       int h = getHeight() - 1;
						       Graphics2D g2 = (Graphics2D) g.create();
						       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						       g2.setPaint(UIManager.getColor("TextField.background"));
						       g2.fillRoundRect(0, 0, w, h, h, h);
						       g2.setPaint(new Color(124, 252, 0));
						       g2.drawRoundRect(0, 0, w, h, h, h);
						       g2.dispose();
						     }
						     super.paintComponent(g);
						   }
			
						   @Override public void updateUI() {
						     super.updateUI();
						     setOpaque(false);
						     // setBackground(new Color(0x0, true));
						     setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
						   }
						 };
					btnDangNhap.setForeground(new Color(124, 252, 0));
					btnDangNhap.setBounds(36, 224, 248, 41);
					panel.add(btnDangNhap);
					btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
					btnDangNhap.setBackground(new Color(0, 255, 0));
					
					
					JLabel lblNewLabel_3 = new JLabel("App_PayRoll\r\n");
					lblNewLabel_3.setForeground(new Color(124, 252, 0));
					lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
					lblNewLabel_3.setBounds(94, 29, 112, 29);
					panel.add(lblNewLabel_3);
					
					JLabel lblNewLabel_4 = new JLabel("");
					lblNewLabel_4.setIcon(new ImageIcon(Login_Gui.class.getResource("/image/Cash-icon.png")));
					lblNewLabel_4.setBounds(200, 29, 54, 29);
					panel.add(lblNewLabel_4);
					
					JPanel panel_1 = new JPanel();
					panel_1.setBackground(Color.WHITE);
					panel_1.setBounds(0, 0, 463, 353);
					getContentPane().add(panel_1);
					panel_1.setLayout(null);
					
					JLabel lblNewLabel_2 = new JLabel("");
					lblNewLabel_2.setIcon(new ImageIcon(Login_Gui.class.getResource("/image/login.jpg")));
					lblNewLabel_2.setBounds(0, 10, 453, 337);
					panel_1.add(lblNewLabel_2);
					
					JLabel lbError = new JLabel("");
					lbError.setForeground(Color.RED);
					lbError.setBounds(268, 202, 265, 13);
					panel.add(lbError);
					ArrayList<TaiKhoan> accountList = null;
					
					
			btnDangNhap.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String taiKhoan = txtTaiKhoan.getText();
						String passWord = txtPassWord.getText();
						TaiKhoan taiKhoanObj = dao_TaiKhoan.taiKhoan(taiKhoan.toUpperCase());
						if(taiKhoanObj != null && passWord.equalsIgnoreCase(taiKhoanObj.getMatKhau())) {
							
							connect.ConnectDB.getInstance().disconnect();
							dispose();
							FrameChinh frame = new  FrameChinh(taiKhoanObj);
							frame.setVisible(true);
							frame.setLocationRelativeTo(null);
						}else {
							lbError.setText("Tài khoản hoặc mật khẩu ko chính xác");
							txtTaiKhoan.setText("");
							txtPassWord.setText("");
							
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
				
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connect.ConnectDB.getInstance().disconnect();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
}
