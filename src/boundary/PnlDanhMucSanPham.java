package boundary;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.DAO_SanPham;
import entity.Kho;
import entity.SanPham;

public class PnlDanhMucSanPham extends JPanel implements MouseListener, ActionListener {
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtDonGia;
	private JTextField txtTimKiem;
	private JTable tableSP;
	private DefaultTableModel modelTableModel;
	private JLabel lblAvt;
	private JButton btnThem;
	private JButton btnLamMoi;
	private JButton btnXoa;
	private String linkImage = "";
	private int save = 0;
	JComboBox cbKho;
	private JButton btnSua;
	private JButton btnThemAvt;
	private DAO_SanPham dao_SanPham;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */

	public PnlDanhMucSanPham() {

		try {
			connect.ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		dao_SanPham = new DAO_SanPham();

		setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setBorder(new LineBorder(Color.CYAN));
		pnTitle.setBackground(Color.CYAN);
		pnTitle.setForeground(Color.WHITE);
		pnTitle.setBounds(10, 0, 920, 35);
		add(pnTitle);
		pnTitle.setLayout(null);

		JLabel lblTitle = new JLabel("Sản phẩm");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblTitle.setBounds(10, 0, 115, 35);
		pnTitle.add(lblTitle);

		JPanel pnAnh = new JPanel();
		pnAnh.setBorder(new LineBorder(Color.CYAN));
		pnAnh.setBounds(10, 46, 128, 152);
		add(pnAnh);
		pnAnh.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblAvt = new JLabel("");
		lblAvt.setIcon(new ImageIcon(PnlDanhMucSanPham.class.getResource("/image/rsz_ngankute.png")));
		pnAnh.add(lblAvt);

		btnThemAvt = new JButton("Tải ảnh");
		btnThemAvt.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnThemAvt.setBorderPainted(false);
		btnThemAvt.setBackground(Color.cyan);
		pnAnh.add(btnThemAvt);
		btnThemAvt.setEnabled(false);
		btnThemAvt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAvt();
			}
		});

		JPanel pnThongTin = new JPanel();
		pnThongTin.setBorder(new LineBorder(Color.CYAN));
		pnThongTin.setBounds(144, 46, 786, 152);
		add(pnThongTin);
		pnThongTin.setLayout(null);

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setBounds(10, 22, 92, 13);
		pnThongTin.add(lblMaSP);

		txtMaSP = new JTextField();
		txtMaSP.setEnabled(false);
		txtMaSP.setBounds(100, 19, 96, 19);
		pnThongTin.add(txtMaSP);
		txtMaSP.setColumns(10);

		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setBounds(228, 12, 120, 32);
		pnThongTin.add(lblTenSP);

		txtTenSP = new JTextField();
		txtTenSP.setBounds(328, 18, 111, 19);
		pnThongTin.add(txtTenSP);
		txtTenSP.setColumns(10);

		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setBounds(10, 115, 58, 19);
		pnThongTin.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(100, 113, 120, 21);
		pnThongTin.add(txtDonGia);
		txtDonGia.setColumns(10);

		JLabel lblKho = new JLabel("Kho:");
		lblKho.setBounds(10, 67, 45, 13);
		pnThongTin.add(lblKho);

		cbKho = new JComboBox();
		cbKho.setBounds(100, 63, 120, 21);
		pnThongTin.add(cbKho);

		JPanel pnChucNang = new JPanel();
		pnChucNang.setBounds(449, 0, 337, 152);
		pnThongTin.add(pnChucNang);
		pnChucNang.setBorder(new LineBorder(Color.CYAN));
		pnChucNang.setLayout(null);

		btnLamMoi = new JButton("Lưu");
		btnLamMoi.setBackground(Color.CYAN);
		btnLamMoi.setBorderPainted(false);
		btnLamMoi.setEnabled(false);
		btnLamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicSave();
			}
		});
		btnLamMoi.setIcon(new ImageIcon(PnlDanhMucSanPham.class.getResource("/image/return.png")));
		btnLamMoi.setBounds(40, 20, 110, 35);
		pnChucNang.add(btnLamMoi);

		btnXoa = new JButton("Xóa");
		btnXoa.setBorderPainted(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}

		});
		btnXoa.setBackground(Color.CYAN);
		btnXoa.setIcon(new ImageIcon(PnlDanhMucSanPham.class.getResource("/image/trash.png")));
		btnXoa.setBounds(212, 20, 100, 35);
		pnChucNang.add(btnXoa);

		btnThem = new JButton("Thêm");
		btnThem.setBackground(Color.CYAN);
		btnThem.setBorderPainted(false);
		btnThem.setIcon(new ImageIcon(PnlDanhMucSanPham.class.getResource("/image/add.png")));
		btnThem.setBounds(40, 65, 110, 35);
		pnChucNang.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicAdd();
			}
		});
		btnSua = new JButton("Sửa");
		btnSua.setBorderPainted(false);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logicUpdate();
			}
		});
		btnSua.setBackground(Color.CYAN);
		btnSua.setIcon(new ImageIcon(PnlDanhMucSanPham.class.getResource("/image/settings.png")));
		btnSua.setBounds(212, 66, 100, 35);
		pnChucNang.add(btnSua);

		JLabel lblTimTen = new JLabel("Tìm kiếm theo tên:");
		lblTimTen.setBounds(20, 115, 110, 27);
		pnChucNang.add(lblTimTen);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(143, 118, 128, 19);
		pnChucNang.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		JButton btnTimKiem = new JButton("");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setBounds(272, 116, 24, 21);
		pnChucNang.add(btnTimKiem);
		btnTimKiem.setIcon(new ImageIcon(PnlDanhMucSanPham.class.getResource("/image/search-icon.png")));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchName();
			}
		});

		JPanel pnDanhSach = new JPanel();
		pnDanhSach.setBorder(new LineBorder(Color.CYAN));
		pnDanhSach.setBounds(10, 209, 920, 299);
		add(pnDanhSach);
		pnDanhSach.setLayout(null);
		JScrollPane scrollPaneDSSP = new JScrollPane();
		scrollPaneDSSP.setBounds(10, 11, 900, 276);
		pnDanhSach.add(scrollPaneDSSP);
		modelTableModel = new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }

		}, new String[] { "STT", "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "\u0110\u01A1n gi\u00E1",
				"Kho", "\u0110\u1ECBa ch\u1EC9 kho" });
		tableSP = new JTable();
		tableSP.setModel(modelTableModel);
		tableSP.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableSP.getColumnModel().getColumn(5).setPreferredWidth(110);
		JTableHeader hd=tableSP.getTableHeader();
		hd.setBackground(Color.cyan);
		hd.setFont(new Font("Time New Roman",Font.BOLD,11));
		scrollPaneDSSP.setViewportView(tableSP);
		txtMaSP.setEditable(false);
		txtTenSP.setText("");
		txtDonGia.setText("");
		txtTenSP.setEditable(false);
		txtDonGia.setEditable(false);
		btnThemAvt.setEnabled(false);
		try {
			loadComboBox();
			getAllSanPham();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		tableSP.addMouseListener(this);
		cbKho.addActionListener(this);
	}

	private void loadComboBox() throws SQLException {
		while (cbKho.getItemCount() != 0) {
			cbKho.removeAllItems();
		}
		ArrayList<Kho> listKho = dao_SanPham.getKho();
		for (Kho kho : listKho) {
			cbKho.addItem(kho.getDiaChi());
		}
	}

	public SanPham logicThem() {
		SanPham sanPham = null;
		try {
			float donGia = 0;
			if (!txtTenSP.getText().equalsIgnoreCase("") && !txtDonGia.getText().equalsIgnoreCase("")) {
				String maSP = txtMaSP.getText();
				String tenSP = txtTenSP.getText();
				donGia = Float.parseFloat(txtDonGia.getText());
				String linkImg = "";
				if (linkImage.equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Vui Long Chon Anh");
				} else {
					linkImg = linkImage;
				}

				String khuVucKho = cbKho.getSelectedItem().toString();
				String maKho = "";
				for (Kho kho : dao_SanPham.getKho()) {
					if (khuVucKho.equalsIgnoreCase(kho.getDiaChi())) {
						maKho = kho.getMaKho();
					}
				}
				Kho kho = new Kho(maKho, khuVucKho);
				if (!linkImage.equalsIgnoreCase("")) {
					sanPham = new SanPham(maSP, tenSP, donGia, linkImg, kho);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui Long Nhap Day Du Thong Tin");
				return null;
			}

		} catch (Exception e) {
//			e.printStackTrace();
		}
		return sanPham;
	}

	private void getAllSanPham() {
		int stt = 1;
		while (tableSP.getRowCount() != 0) {
			modelTableModel.removeRow(0);
		}
		ArrayList<SanPham> listSanPham = dao_SanPham.getAllSanPham();
		for (SanPham sanPham : listSanPham) {
			String data[] = { stt + "", sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonGia() + "",
					sanPham.getKho().getMaKho(), sanPham.getKho().getDiaChi() };
			stt++;
			modelTableModel.addRow(data);
		}
	}

	private void loadMaSP() {
		String maSP = null;
		maSP = dao_SanPham.getMaSP();
		txtMaSP.setText(maSP);
	}

	public void xoaTrangTxt() {
		txtTenSP.setText("");
		txtDonGia.setText("");
		txtMaSP.setText("");
	}

	public ImageIcon ResizeImage(String linkImage) {

		ImageIcon myImage = new ImageIcon(linkImage);
		Image img = myImage.getImage();
		Image newImage = img.getScaledInstance(lblAvt.getWidth(), lblAvt.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImage);
		return image;
	}

	void setLogicWhenAdd(boolean b) {
		txtTenSP.setEditable(b);
		txtDonGia.setEditable(b);
		btnLamMoi.setEnabled(b);
	}

	private void logicAdd() {
		if (btnThem.getText().equalsIgnoreCase("Thêm")) {
			btnThem.setText("Hủy");
			setLogicWhenAdd(true);
			xoaTrangTxt();
			loadMaSP();
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
			btnThemAvt.setEnabled(true);
			save = 1;
			cbKho.removeActionListener(this);
		} else {
			btnThem.setText("Thêm");
			setLogicWhenAdd(false);
			xoaTrangTxt();
			save = 0;
			btnSua.setEnabled(true);
			btnThemAvt.setEnabled(false);
			btnXoa.setEnabled(true);
			cbKho.addActionListener(this);
			btnThemAvt.setEnabled(false);
		}
	}

	private void logicUpdate() {
		if (txtTenSP.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Sản Phẩm Cần Sửa");
		} else {
			if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				btnSua.setText("Hủy");
				setLogicWhenAdd(true);
				save = 2;
				btnThem.setEnabled(false);
				btnXoa.setEnabled(false);
				btnThemAvt.setEnabled(true);
				cbKho.removeActionListener(this);
			} else {
				btnSua.setText("Sửa");
				setLogicWhenAdd(false);
				xoaTrangTxt();
				save = 0;
				btnThemAvt.setEnabled(false);
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				cbKho.addActionListener(this);
				btnThemAvt.setEnabled(false);
			}
		}
	}

	private void logicSave() {
		try {
			if (save == 1) {
				add();
			}
			if (save == 2) {
				update();
			}
			btnThemAvt.setEnabled(false);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	private void delete() {
		int row = tableSP.getSelectedRow();
		if (row != -1) {
			int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không");
			if (n == 0) {
				boolean isDel = dao_SanPham.xoaSanPham(tableSP.getValueAt(row, 1).toString());
				if (isDel) {
					JOptionPane.showMessageDialog(null, "Delete Succes!");
					getAllSanPham();
					xoaTrangTxt();
				} else {
					JOptionPane.showMessageDialog(null, "Delete Failse!");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "bạn phải chọn vào dòng cần xóa");
		}
	}

	private void add() {
		SanPham sanPham = null;
		sanPham = logicThem();
		if (sanPham != null) {
			if (dao_SanPham.themSanPham(sanPham) == true) {
				JOptionPane.showMessageDialog(null, "Add Succes");
				setLogicWhenAdd(false);
				xoaTrangTxt();
				save = 0;
				btnThem.setText("Thêm");
				getAllSanPham();
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Add Fails");
			}
		}
	}

	private void update() {
		SanPham sanPham = null;
		sanPham = logicThem();
		if (sanPham != null) {
			if (dao_SanPham.capNhatSanPham(sanPham) == true) {
				JOptionPane.showMessageDialog(null, "Update Succes");
				setLogicWhenAdd(false);
				xoaTrangTxt();
				save = 0;
				btnSua.setText("Sửa");
				getAllSanPham();
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Update Fails");
			}
		}
	}

	private void addAvt() {
		try {
			JFileChooser f = new JFileChooser("T:\\image");
			f.setDialogTitle("Open File");
			f.showOpenDialog(null);
			File fNameImage = f.getSelectedFile();
			linkImage = fNameImage.getAbsolutePath();
			if (linkImage != null) {
				lblAvt.setIcon(ResizeImage(String.valueOf(linkImage)));

			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Vui Lòng Chọn Sản Phẩm Cần Sửa");
		}
	}

	private void searchName() {
		String tenSP = txtTimKiem.getText().toString();
		ArrayList<SanPham> listSP = new ArrayList<SanPham>();
		listSP = dao_SanPham.getSanPhamTheoTen(tenSP);
		if (tenSP.equalsIgnoreCase("")) {
			getAllSanPham();
		} else {
			if (listSP != null) {
				int stt = 1;
				while (tableSP.getRowCount() != 0) {
					modelTableModel.removeRow(0);
				}
				for (SanPham sanPham : listSP) {
					String data[] = { stt + "", sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonGia() + "",
							sanPham.getKho().getMaKho(), sanPham.getKho().getDiaChi() };
					stt++;
					modelTableModel.addRow(data);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Không có dữ liệu cần tìm");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(tableSP)) {
			int row = tableSP.getSelectedRow();
			cbKho.removeActionListener(this);
			if (row != -1) {
				txtMaSP.setText(tableSP.getValueAt(row, 1).toString());
				txtTenSP.setText(tableSP.getValueAt(row, 2).toString());
				txtDonGia.setText(tableSP.getValueAt(row, 3).toString());
				cbKho.setSelectedItem(tableSP.getValueAt(row, 5).toString());
				for (SanPham sanPham : dao_SanPham.getAllSanPham()) {
					if (txtMaSP.getText().equalsIgnoreCase(sanPham.getMaSP())) {
						linkImage = sanPham.getAnh();
						try {
							if (sanPham.getAnh() != null) {
								lblAvt.setIcon(ResizeImage(String.valueOf(linkImage)));
							} else {
								JOptionPane.showMessageDialog(null, "Link Anh San Pham Null");
							}
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if (object.equals(cbKho)) {
			int stt = 1;
			String maSP = cbKho.getSelectedItem().toString();
			ArrayList<SanPham> listSP = dao_SanPham.filterByMaKho(maSP);
			while (tableSP.getRowCount() != 0) {
				modelTableModel.removeRow(0);
			}
			for (SanPham sanPham : listSP) {
				String data[] = { stt + "", sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonGia() + "",
						sanPham.getKho().getMaKho(), sanPham.getKho().getDiaChi() };
				modelTableModel.addRow(data);
				stt++;
			}
		}
	}

}
