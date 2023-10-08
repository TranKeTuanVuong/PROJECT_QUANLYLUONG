package boundary;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.TaiKhoan;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

public class PnlThongTin extends JPanel {

	private JLabel lblAVT;
	/**
	 * Create the panel.
	 */
	public PnlThongTin(TaiKhoan taiKhoan) {
		setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.CYAN);
		panel_1_1.setBounds(10, 10, 909, 44);
		add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblThngTinC_1 = new JLabel("Thông Tin Tài Khoản Đăng Nhập");
		lblThngTinC_1.setBounds(277, 0, 394, 44);
		lblThngTinC_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		panel_1_1.add(lblThngTinC_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.LIGHT_GRAY);
		panel_2_1.setBorder(new LineBorder(Color.CYAN, 1, true));
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(10, 67, 909, 238);
		add(panel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Tên Nhân Viên: ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(340, 45, 104, 25);
		panel_2_1.add(lblNewLabel_1);
		
		JLabel lblSinThoi_1 = new JLabel("Chứng Minh Nhân Dân:");
		lblSinThoi_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSinThoi_1.setBounds(340, 164, 157, 25);
		panel_2_1.add(lblSinThoi_1);
		
		JLabel lblChcV_1 = new JLabel("Chức Vụ: ");
		lblChcV_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblChcV_1.setBounds(340, 103, 104, 25);
		panel_2_1.add(lblChcV_1);
		
		JLabel lblTenNV_1 = new JLabel(taiKhoan.getNhanVien().getTenNhanVien());
		lblTenNV_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTenNV_1.setBounds(551, 45, 398, 25);
		panel_2_1.add(lblTenNV_1);
		
		JLabel lblChucVu_1 = new JLabel(taiKhoan.getNhanVien().getChucVu());
		lblChucVu_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblChucVu_1.setBounds(551, 100, 360, 30);
		panel_2_1.add(lblChucVu_1);
		
		JLabel lblSDT_1 = new JLabel(taiKhoan.getNhanVien().getCMND());
		lblSDT_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSDT_1.setBounds(551, 164, 300, 25);
		panel_2_1.add(lblSDT_1);
		String linkImage=taiKhoan.getNhanVien().getAnh();
		
		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), null));
		panel.setBounds(55, 40, 143, 159);
		panel_2_1.add(panel);
		panel.setLayout(null);
		
		lblAVT = new JLabel("                Ảnh 3x4");
		lblAVT.setBounds(1, 1, 141, 157);
		panel.add(lblAVT);
		lblAVT.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblAVT.setIcon(new ImageIcon(taiKhoan.getNhanVien().getAnh()));
		lblAVT.setIcon(ResizeImage(String.valueOf(linkImage)));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PnlThongTin.class.getResource("/image/anhNenThongTin.jpg")));
		lblNewLabel.setBounds(10, 256, 909, 288);
		add(lblNewLabel);

	}
	public ImageIcon ResizeImage(String linkImage) {
		  ImageIcon myImage = new ImageIcon(linkImage);
		  Image img = myImage.getImage();
		  Image newImage = img.getScaledInstance(lblAVT.getWidth(), lblAVT.getHeight(), Image.SCALE_SMOOTH);
		  ImageIcon image = new ImageIcon(newImage);
		  return image;
	  }
}
