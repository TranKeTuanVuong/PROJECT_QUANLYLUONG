package boundary;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Blue;

import connect.ConnectDB;
import entity.TaiKhoan;
import java.awt.Color;

public class FrameChinh extends JFrame {

	private JPanel contentPane;
	private JPanel pnTemp;
	private JPanel pnChinh;
	private JMenu mnTrangChu;
	private JMenu mnDanhMuc;
	private JMenu mnXuLi;
	private JMenuItem mntmNhanVien;
	private JMenuItem mntmCongNhan;
	private JMenuItem mntmSanPham;
	private JMenu mnChamCong;
	private JMenuItem mntmChamCongCN;
	private JMenuItem mntmChamCongNV;
	private JMenuItem mntmTLCN;
	private JMenu mnTienLuong;
	private JMenuItem mntmTLNV;
	private JMenuItem mntmPhanCong;
	private JMenuItem mntmChiaCongDoan;
	private JMenu mnThongKe;
	private JMenuItem mntmThongKeLuong;
	private JMenu mnCaNhan;
	private JMenuItem mntmThongTin;
	private JMenuItem mntmDoiMK;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FrameChinh(TaiKhoan taiKhoan) {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		setFont(new Font("Times New Roman", Font.BOLD, 28));
		setTitle("PAYROLL");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameChinh.class.getResource("/image/Cash-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 955, 587);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		mnTrangChu = new JMenu("Trang ch\u1EE7");
		mnTrangChu.setForeground(Color.CYAN);
		mnTrangChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logicBackground1(1, 0, 0);
				showPnl(new PnlTrangChu());
			}
		});

		mnTrangChu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mnTrangChu.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/Home-icon.png")));
		menuBar.add(mnTrangChu);

		mnDanhMuc = new JMenu("Danh m\u1EE5c");
		mnDanhMuc.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/DanhMuc_icon.png")));
		mnDanhMuc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		menuBar.add(mnDanhMuc);

		mntmNhanVien = new JMenuItem("Nh\u00E2n Vi\u00EAn");
		mntmNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(2, 1, 0);
				showPnl(new PnlDanhMucNhanVien());
			}
		});
		mntmNhanVien.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/NhanVien-icon.png")));
		mnDanhMuc.add(mntmNhanVien);

		mntmCongNhan = new JMenuItem("C\u00F4ng nh\u00E2n");
		mntmCongNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(2, 2, 0);
				showPnl(new PnlDanhMucCongNhan());
			}
		});
		mntmCongNhan.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/CongNhan-icon.png")));
		mntmCongNhan.setHorizontalAlignment(SwingConstants.TRAILING);
		mnDanhMuc.add(mntmCongNhan);

		mntmSanPham = new JMenuItem("S\u1EA3n ph\u1EA9m");
		mntmSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(2, 3, 0);
				showPnl(new PnlDanhMucSanPham());
			}
		});
		mntmSanPham.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/icon_SanPham.png")));
		mnDanhMuc.add(mntmSanPham);

		mnXuLi = new JMenu("X\u1EED l\u00ED");
		mnXuLi.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/XuLy-icon.png")));
		mnXuLi.setFont(new Font("Times New Roman", Font.BOLD, 14));
		menuBar.add(mnXuLi);

		mnChamCong = new JMenu("Ch\u1EA5m c\u00F4ng");
		mnChamCong.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/ChamCong-icon.png")));
		mnXuLi.add(mnChamCong);

		mntmChamCongCN = new JMenuItem("Ch\u1EA5m c\u00F4ng c\u00F4ng nh\u00E2n");
		mntmChamCongCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(3, 1, 1);
				showPnl(new PnlChamCongCN());
			}
		});
		mnChamCong.add(mntmChamCongCN);

		mntmChamCongNV = new JMenuItem("Ch\u1EA5m c\u00F4ng nh\u00E2n vi\u00EAn");
		mntmChamCongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(3, 1, 2);
				showPnl(new PnlChamCongNV());
			}
		});
		mnChamCong.add(mntmChamCongNV);

		mnTienLuong = new JMenu("Tiền lương");
		mnTienLuong.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/TienLuong_icon.png")));
		mnXuLi.add(mnTienLuong);

		mntmTLCN = new JMenuItem("Tiền lương công nhân");
		mntmTLCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(3, 2, 1);
				showPnl(new PnlTienLuongCN());
			}
		});
		mnTienLuong.add(mntmTLCN);

		mntmTLNV = new JMenuItem("Tiền lương nhân viên");
		mntmTLNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(3, 2, 2);

				showPnl(new PnlTienLuongNV());
			}
		});
		mnTienLuong.add(mntmTLNV);

		mntmPhanCong = new JMenuItem("Ph\u00E2n c\u00F4ng");
		mntmPhanCong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(3, 3, 0);

				showPnl(new PnlPhanCong());
			}
		});
		mntmPhanCong.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/PhanCong-icon.png")));
		mnXuLi.add(mntmPhanCong);

		mntmChiaCongDoan = new JMenuItem("Chia công đoạn");
		mntmChiaCongDoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(3, 4, 0);

				showPnl(new PnlChiaCongDoan());
			}
		});
		mntmChiaCongDoan.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/icon_ChiaCongDoan.jpg")));
		mnXuLi.add(mntmChiaCongDoan);

		mnThongKe = new JMenu("Th\u1ED1ng k\u00EA");
		mnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 14));
		mnThongKe.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/ThongKe-icon.png")));
		menuBar.add(mnThongKe);



		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pnChinh = new JPanel();
		contentPane.add(pnChinh, BorderLayout.CENTER);
		pnChinh.setLayout(new BorderLayout(0, 0));
		showPnl(new PnlTrangChu());
		mnCaNhan = new JMenu(taiKhoan.getTenTK());
		mnCaNhan.setIcon(new ImageIcon(FrameChinh.class.getResource("/image/mk.jpg")));
		menuBar.add(mnCaNhan);
		mntmThongTin = new JMenuItem("Thông tin");
		mntmThongTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicBackground1(5, 1, 0);
				showPnl(new PnlThongTin(taiKhoan));

			}
		});
		mnCaNhan.add(mntmThongTin);

		mntmDoiMK = new JMenuItem("Đổi mật khẩu");
		mntmDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.ConnectDB.getInstance().disconnect();
				logicBackground1(5, 2, 0);
				showPnl(new NewPass(taiKhoan));
			}
		});

		JMenuItem mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_Gui login = new Login_Gui();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				setVisible(false);
			}
		});
		mnCaNhan.add(mntmDangXuat);
		mnCaNhan.add(mntmDoiMK);
		if (taiKhoan.getNhanVien().getChucVu().equalsIgnoreCase("Trưởng phòng nhân sự")) {
			mntmSanPham.setEnabled(false);
			mnThongKe.setEnabled(false);	
			mnTienLuong.setEnabled(false);
			mntmPhanCong.setEnabled(true);
			mnChamCong.setEnabled(true);
			mntmChiaCongDoan.setEnabled(true);
		} else if (taiKhoan.getNhanVien().getChucVu().equalsIgnoreCase("Quản lí kho")) {
			mntmCongNhan.setEnabled(false);
			mntmNhanVien.setEnabled(false);
			mnXuLi.setEnabled(false);
			mnThongKe.setEnabled(false);
		} else {
			mnDanhMuc.setEnabled(false);
			mnChamCong.setEnabled(false);
			mntmPhanCong.setEnabled(false);
			mntmChiaCongDoan.setEnabled(false);
			mnThongKe.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					logicBackground1(4, 0, 0);
					showPnl(new PnlThongKeLuong());
				}
			});
		}

		// pnChinh.add(new PnlChamCong(), BorderLayout.CENTER);
	}

	private void showPnl(JPanel panel) {
		// pnTemp = panel;
		pnChinh.removeAll();
		pnChinh.add(panel, BorderLayout.CENTER);
		pnChinh.validate();
	}

	private void setBackGround() {
//		menu
		mnTrangChu.setForeground(Color.BLACK);
		mnDanhMuc.setForeground(Color.BLACK);
		mnXuLi.setForeground(Color.BLACK);
		mnThongKe.setForeground(Color.BLACK);
		mnCaNhan.setForeground(Color.BLACK);
//		menuItem
		mntmNhanVien.setBackground(Color.WHITE);
		mntmCongNhan.setBackground(Color.WHITE);
		mntmSanPham.setBackground(Color.WHITE);

		mntmChamCongCN.setBackground(Color.WHITE);
		mntmChamCongNV.setBackground(Color.WHITE);
		mntmTLCN.setBackground(Color.WHITE);
		mntmTLNV.setBackground(Color.WHITE);
		mntmPhanCong.setBackground(Color.WHITE);
		mntmChiaCongDoan.setBackground(Color.WHITE);


		mntmThongTin.setBackground(Color.WHITE);
		mntmDoiMK.setBackground(Color.WHITE);

	}

	private void logicBackground1(int menuBackGround, int menuItemBackGround, int menuItemChildBackGround) {
		if (menuBackGround == 1) {
			setBackGround();
			mnTrangChu.setForeground(Color.CYAN);
		} else if (menuBackGround == 2) {

			if (menuItemBackGround == 1) {
				setBackGround();
				mntmNhanVien.setBackground(Color.CYAN);
			} else if (menuItemBackGround == 2) {
				setBackGround();
				mntmCongNhan.setBackground(Color.CYAN);
			} else {
				setBackGround();
				mntmSanPham.setBackground(Color.CYAN);
			}
			mnDanhMuc.setForeground(Color.CYAN);
		} else if (menuBackGround == 3) {

			if (menuItemBackGround == 1) {
				if (menuItemChildBackGround == 1) {
					setBackGround();
					mntmChamCongCN.setBackground(Color.CYAN);
				} else {
					setBackGround();
					mntmChamCongNV.setBackground(Color.CYAN);
				}
			} else if (menuItemBackGround == 2) {
				if (menuItemChildBackGround == 1) {
					setBackGround();
					mntmTLCN.setBackground(Color.CYAN);
				} else {
					setBackGround();
					mntmTLNV.setBackground(Color.CYAN);
				}
			} else if (menuItemBackGround == 3) {
				setBackGround();
				mntmPhanCong.setBackground(Color.CYAN);
			} else {
				setBackGround();
				mntmChiaCongDoan.setBackground(Color.CYAN);
			}
			mnXuLi.setForeground(Color.CYAN);
		} else if (menuBackGround == 4) {
			setBackGround();
			mnThongKe.setForeground(Color.CYAN);
		} else {

			if (menuItemBackGround == 1) {
				setBackGround();
				mntmThongTin.setBackground(Color.CYAN);
			} else {
				setBackGround();
				mntmDoiMK.setBackground(Color.CYAN);
			}
			mnCaNhan.setForeground(Color.CYAN);
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
