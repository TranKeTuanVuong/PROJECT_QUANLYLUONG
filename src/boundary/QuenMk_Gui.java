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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;

import dao.DAO_TaiKhoan;
import entity.TaiKhoan;

public class QuenMk_Gui extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private DAO_TaiKhoan dao_TaiKhoan;
	TaiKhoan taiKhoan = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuenMk_Gui mk = new QuenMk_Gui();
					mk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuenMk_Gui(){
		
		try {
			connect.ConnectDB.getInstance().connect();
			dao_TaiKhoan = new DAO_TaiKhoan();
		} catch (Exception e) {
			// TODO: handle exception
			connect.ConnectDB.getInstance().disconnect();
			e.printStackTrace();
			
		}
		
		setBackground(new Color(135, 206, 235));
		setBounds(100, 100, 571, 244);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBorder(new CompoundBorder());
		panel.setBounds(0, 0, 557, 218);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nh\u1EADp t\u00EAn \u0111\u0103ng nh\u1EADp");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(301, 43, 130, 29);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		JTextField txtTenTK = new JTextField(20) {
			   @Override protected void paintComponent(Graphics g) {
			     if (!isOpaque()) {
			       int w = getWidth() - 1;
			       int h = getHeight() - 1;
			       Graphics2D g2 = (Graphics2D) g.create();
			       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			       g2.setPaint(UIManager.getColor("TextField.background"));
			       g2.fillRoundRect(0, 0, w, h, h, h);
			       g2.setPaint(Color.gray);
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
		txtTenTK.setBackground(Color.GRAY);
		txtTenTK.setBounds(301, 93, 239, 35);
		panel.add(txtTenTK);
		txtTenTK.setColumns(10);
		
		textField_1 = new JTextField();
		
//		JButton btnNewButton = new JButton("G\u1EEDi X\u00E1c Nh\u1EADn\r\n");
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taiKhoan = new TaiKhoan();
				CheckMail main = new CheckMail();
				String tenTK = txtTenTK.getText();
				taiKhoan = dao_TaiKhoan.getTaiKhoan(tenTK);

				if(dao_TaiKhoan.getTaiKhoan(tenTK) != null) {
					connect.ConnectDB.getInstance().disconnect();
					
					
					 new JavaMail(taiKhoan);
					 dispose();
					
				}else {
					JOptionPane.showMessageDialog(null,"Tai Khoan Khong Ton Tai");
				}
			}
		});
		btnNewButton.setForeground(SystemColor.activeCaption);
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.setBounds(356, 160, 130, 35);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(QuenMk_Gui.class.getResource("/image/16-icon.jpg")));
		lblNewLabel_3.setBounds(356, 160, 130, 35);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(QuenMk_Gui.class.getResource("/image/laptop.jpg")));
		lblNewLabel_1.setBounds(0, 0, 277, 223);
		panel.add(lblNewLabel_1);
	}
	


}

