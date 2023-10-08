package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import connect.ConnectDB;

public class DoiMK {

	private JFrame frame;
	private JPasswordField textField;
	private JPasswordField textField_1;
	private JPasswordField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMK window = new DoiMK();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DoiMK() {
		initialize();
//		setLocationRelativeTo(null);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 417, 410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 415, 372);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u1EADp m\u1EADt kh\u1EA9u ");
		lblNewLabel_1.setBounds(17, 147, 100, 23);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel_1);
		
		 textField = new JPasswordField() {
			   // Unleash Your Creativity with Swing and the Java 2D API!
			   // http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
			   // http://web.archive.org/web/20091205092230/http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
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
		textField.setBounds(127, 142, 252, 36);
		textField.setBackground(Color.GRAY);
		panel.add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Nh\u1EADp m\u1EADt kh\u1EA9u m\u1EDBi\r\n");
		lblNewLabel_2.setBounds(17, 198, 114, 23);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel_2);
		
		
		textField_1 = new JPasswordField() {
			   // Unleash Your Creativity with Swing and the Java 2D API!
			   // http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
			   // http://web.archive.org/web/20091205092230/http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
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
		textField_1.setBounds(127, 193, 252, 36);
		textField_1.setBackground(Color.GRAY);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nh\u1EADp L\u1EA1i M\u1EADt Kh\u1EA9u\r\n");
		lblNewLabel_3.setBounds(17, 251, 114, 23);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblNewLabel_3);
		
		textField_2 = new JPasswordField() {
			   // Unleash Your Creativity with Swing and the Java 2D API!
			   // http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
			   // http://web.archive.org/web/20091205092230/http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
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
		textField_2.setBounds(127, 246, 252, 36);
		textField_2.setBackground(Color.GRAY);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("T\u00E0i Kho\u1EA3n\r\n");
		
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(17, 98, 100, 23);
		panel.add(lblNewLabel_5);
		
		textField_3 = new JTextField() {
			   // Unleash Your Creativity with Swing and the Java 2D API!
			   // http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
			   // http://web.archive.org/web/20091205092230/http://java.sun.com/products/jfc/tsc/articles/swing2d/index.html
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
		textField_3.setBackground(Color.GRAY);
		textField_3.setBounds(127, 93, 252, 36);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("G\u1EEDi X\u00E1c Nh\u1EADn\r\n") {
			   @Override protected void paintComponent(Graphics g) {
			     if (!isOpaque()) {
			       int w = getWidth() - 1;
			       int h = getHeight() - 1;
			       Graphics2D g2 = (Graphics2D) g.create();
			       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			       g2.setPaint(UIManager.getColor("TextField.background"));
			       g2.fillRoundRect(0, 0, w, h, h, h);
			       g2.setPaint(new Color(135, 206, 250));
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
		btnNewButton.setBounds(159, 311, 179, 36);
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.setForeground(SystemColor.activeCaption);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String tenTK, pass, newpasss, cnlpass;
					tenTK = textField_3.getText().toString();
					pass = textField.getText();
					newpasss = textField_1.getText();
					cnlpass = textField_2.getText();
					String sql ="select * from TaiKhoan	where tenTK= %"+tenTK+"%";
					String sql1 = "update TaiKhoan set matKhau ='"+newpasss+"'where tenTK='"+tenTK+"'";
					ConnectDB.getInstance();
					Connection con = ConnectDB.getConnection();
					PreparedStatement statement = null;
					statement = con.prepareStatement(sql);
					ResultSet rs = statement.executeQuery();
					if (rs.next()) {
						
						 if (rs.getString("matKhau").equals(pass)) {
							lblNewLabel_1.setText(""); // nhập mật khẩu
							if (newpasss.equals(pass)) {
							lblNewLabel_3.setText(""); // nhập lại mật khẩu 
							statement.executeUpdate(sql1);
							JOptionPane.showMessageDialog(btnNewButton, "Password ");
							}else {
							lblNewLabel_3.setText("mật khẩu không khớp");
							textField_2.requestFocus(); // ô nhập lại mật khẩu
							}
						} else {
						lblNewLabel_1.setText("mật khẩu không hợp lệ"); // nhập mật khẩu mới
						textField_1.requestFocus(); // ô nhập mật khẩu mới
					}
						 lblNewLabel_5.setText("");
					}else {
						lblNewLabel_5.setText("Tài Khoản Không hợp lệ"); // tên tài khoản
						textField_3.requestFocus(); // ô nhập tài khoản
					}		
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(176, 8, 100, 49);
		panel_1.setBackground(new Color(248, 248, 255));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\u0110\u1ED5i M\u1EADt Kh\u1EA9u");
		lblNewLabel.setBackground(SystemColor.activeCaption);
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
	
	}
}
