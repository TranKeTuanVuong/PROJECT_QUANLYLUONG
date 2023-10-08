package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.DAO_ChiTietPC;
import dao.DAO_PhanCong;
import dao.DAO_CongNhan;
import entity.ChiTietPhanCong;
import entity.CongNhan;
import entity.PhanCong;

public class PnlPhanCong extends JPanel implements ActionListener, MouseListener {
	private JTable tableDSCN;
	private JTextField txtTimTenCN;
	private JTable tableDSCD;
	private JTextField txtSoLuong;
	private JTextField txtTenCN;
	private JTextField txtCongDoan;
	private JTextField txtTenSP;
	private JButton btnThem, btnSua, btnXoa, btnXacNhan, btnTimCN, btnTimTheoNgay;
	private JComboBox cboTimCDPC, cboCongDoan;
	private JDateChooser dateChooser;

	private DefaultTableModel dataModelDSCN;
	String[] headersDSCN = { "Mã NV", "Tên công nhân" };
	private DefaultTableModel dataModelDSCD;
	String[] headersDSCD = { "Mã CĐ", "Tên công đoạn" };
	private DefaultTableModel dataModelDSPC;
	String[] headersDSPC = { "STT", "Mã CN", "Tên công nhân", "Ngày phân công", "Công đoạn", "Sản phẩm", "Số lượng" };

	private JTable tableDSPC;
	private DAO_CongNhan dao_CN;
	private DAO_PhanCong dao_CCD;
	private DAO_ChiTietPC dao_CTPC;
	private JTextField txtMaCN;
	private JLabel lblMaCN;

	/**
	 * Create the panel.
	 */
	public PnlPhanCong() {

		dao_CN = new DAO_CongNhan();
		dao_CCD = new DAO_PhanCong();
		dao_CTPC = new DAO_ChiTietPC();

		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Phân công"));

		JPanel pnlDSCN = new JPanel();
		pnlDSCN.setBounds(10, 24, 281, 221);
		pnlDSCN.setBorder(new LineBorder(Color.CYAN));
		pnlDSCN.setBackground(Color.WHITE);
		pnlDSCN.setBorder(BorderFactory.createTitledBorder("Danh sách công nhân"));
		add(pnlDSCN);
		pnlDSCN.setLayout(null);

		JScrollPane scrollPaneDSCN = new JScrollPane();
		scrollPaneDSCN.setBounds(10, 41, 260, 169);
		pnlDSCN.add(scrollPaneDSCN);

		dataModelDSCN = new DefaultTableModel(headersDSCN, 0);
		tableDSCN = new JTable(dataModelDSCN);
		tableDSCN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tableDSCN.getSelectedRow();
					if (row >= 0) {
						txtMaCN.setText(dataModelDSCN.getValueAt(row, 0).toString());
						txtTenCN.setText(dataModelDSCN.getValueAt(row, 1).toString());
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Ko còn công nhân để phân công!");
				}
			}
		});
		JTableHeader hd = tableDSCN.getTableHeader();
		hd.setBackground(Color.cyan);
		hd.setFont(new Font("Time New Roman", Font.BOLD, 10));
		scrollPaneDSCN.setViewportView(tableDSCN);

		txtTimTenCN = new JTextField();
		txtTimTenCN.setBounds(106, 15, 131, 23);
		pnlDSCN.add(txtTimTenCN);
		txtTimTenCN.setColumns(10);

		btnTimCN = new JButton("");
		btnTimCN.setBackground(SystemColor.control);
		btnTimCN.setBounds(244, 15, 24, 23);
		pnlDSCN.add(btnTimCN);
		btnTimCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimCN.setIcon(new ImageIcon(PnlPhanCong.class.getResource("/image/search-icon.png")));

		JLabel lblIconMuiTen1 = new JLabel("");
		lblIconMuiTen1.setIcon(new ImageIcon(PnlPhanCong.class.getResource("/image/Icon_MuiTen.png")));
		lblIconMuiTen1.setBounds(290, 105, 32, 30);
		add(lblIconMuiTen1);

		JPanel pnlCongDoan = new JPanel();
		pnlCongDoan.setBounds(321, 24, 281, 221);
		pnlCongDoan.setBackground(Color.WHITE);
		pnlCongDoan.setBorder(BorderFactory.createTitledBorder("Danh sách công đoạn"));
		add(pnlCongDoan);
		pnlCongDoan.setLayout(null);

		cboCongDoan = new JComboBox();
		cboCongDoan
				.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "R\u1EADp m\u00E1y", "May v\u1EAFt s\u1ED5",
						"May m\u00F3c x\u00EDch k\u00E9p", "L\u00E0 \u1EE7i", "\u0110\u00F3ng g\u00F3i" }));
		cboCongDoan.setBounds(118, 17, 153, 22);
		pnlCongDoan.add(cboCongDoan);

		JScrollPane scrollPaneDSCD = new JScrollPane();
		scrollPaneDSCD.setBounds(10, 44, 261, 166);
		pnlCongDoan.add(scrollPaneDSCD);

		dataModelDSCD = new DefaultTableModel(headersDSCD, 0);
		tableDSCD = new JTable(dataModelDSCD);
		tableDSCD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tableDSCD.getSelectedRow();
					if (row >= 0) {
						PhanCong pc = dao_CCD.timPC(dataModelDSCD.getValueAt(row, 0).toString());
						if (pc != null) {
							txtTenSP.setText(pc.getSanPham().getTenSP());
							txtCongDoan.setText(pc.getCongDoanSX());
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Ko còn công đoạn để phân công!");
				}
			}
		});
		JTableHeader hd1 = tableDSCD.getTableHeader();
		hd1.setBackground(Color.cyan);
		hd1.setFont(new Font("Time New Roman", Font.BOLD, 10));
		scrollPaneDSCD.setViewportView(tableDSCD);

		JPanel pnlDSPC = new JPanel();
		pnlDSPC.setBounds(10, 256, 910, 243);
		pnlDSPC.setBackground(Color.WHITE);
		pnlDSPC.setBorder(BorderFactory.createTitledBorder("Danh sách phân công"));
		add(pnlDSPC);
		pnlDSPC.setLayout(null);

		dateChooser = new JDateChooser(new Date());
		dateChooser.setBounds(455, 11, 118, 22);
		pnlDSPC.add(dateChooser);

		cboTimCDPC = new JComboBox();
		cboTimCDPC
				.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "R\u1EADp m\u00E1y", "May v\u1EAFt s\u1ED5",
						"May m\u00F3c x\u00EDch k\u00E9p", "L\u00E0 \u1EE7i", "\u0110\u00F3ng g\u00F3i" }));
		cboTimCDPC.setBounds(763, 11, 137, 22);
		pnlDSPC.add(cboTimCDPC);

		JScrollPane scrollPaneDSPC = new JScrollPane();
		scrollPaneDSPC.setBounds(10, 38, 890, 194);
		pnlDSPC.add(scrollPaneDSPC);
		scrollPaneDSPC.setViewportView(tableDSPC);

		dataModelDSPC = new DefaultTableModel(headersDSPC, 0);
		tableDSPC = new JTable(dataModelDSPC);
		tableDSPC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tableDSPC.getSelectedRow();
					if (row >= 0) {
						txtMaCN.setText(dataModelDSPC.getValueAt(row, 1).toString());
						txtTenCN.setText(dataModelDSPC.getValueAt(row, 2).toString());
						txtTenSP.setText(dataModelDSPC.getValueAt(row, 5).toString());
						txtCongDoan.setText(dataModelDSPC.getValueAt(row, 4).toString());
						txtSoLuong.setText(dataModelDSPC.getValueAt(row, 6).toString());
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Ko còn công đoạn để phân công!");
				}
			}
		});
		JTableHeader hd2 = tableDSPC.getTableHeader();
		hd2.setBackground(Color.cyan);
		hd2.setFont(new Font("Time New Roman", Font.BOLD, 10));
		// Chỉnh kích cỡ cột cho bảng chi tiết phân công
		tableDSPC.getColumnModel().getColumn(0).setPreferredWidth(29);
		tableDSPC.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableDSPC.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableDSPC.getColumnModel().getColumn(3).setPreferredWidth(90);
		tableDSPC.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableDSPC.getColumnModel().getColumn(5).setPreferredWidth(160);
		tableDSPC.getColumnModel().getColumn(6).setPreferredWidth(80);
		scrollPaneDSPC.setViewportView(tableDSPC);

		btnTimTheoNgay = new JButton("");
		btnTimTheoNgay.setBackground(SystemColor.control);
		btnTimTheoNgay.setIcon(new ImageIcon(PnlPhanCong.class.getResource("/image/search-icon.png")));
		btnTimTheoNgay.setBounds(575, 10, 24, 23);
		pnlDSPC.add(btnTimTheoNgay);

		JPanel pnlChiTiet = new JPanel();
		pnlChiTiet.setBorder(new LineBorder(Color.CYAN));
		pnlChiTiet.setBackground(Color.WHITE);
		pnlChiTiet.setBounds(639, 34, 281, 213);
		add(pnlChiTiet);
		pnlChiTiet.setLayout(null);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(115, 118, 117, 20);
		pnlChiTiet.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		JLabel lblSoLuong = new JLabel("Số lượng cụ thể:");
		lblSoLuong.setBounds(10, 118, 106, 20);
		pnlChiTiet.add(lblSoLuong);

		JLabel lblTenCN = new JLabel("Tên công nhân:");
		lblTenCN.setBounds(10, 10, 106, 17);
		pnlChiTiet.add(lblTenCN);

		txtTenCN = new JTextField();
		txtTenCN.setBounds(115, 8, 156, 20);
		pnlChiTiet.add(txtTenCN);
		txtTenCN.setColumns(10);

		txtCongDoan = new JTextField();
		txtCongDoan.setBounds(116, 62, 148, 20);
		pnlChiTiet.add(txtCongDoan);
		txtCongDoan.setColumns(10);

		JLabel lblCongDoan = new JLabel("Công đoạn:");
		lblCongDoan.setBounds(30, 62, 96, 20);
		pnlChiTiet.add(lblCongDoan);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon(PnlPhanCong.class.getResource("/image/add.png")));
		btnThem.setBackground(Color.CYAN);
		btnThem.setBorderPainted(false);
		btnThem.setBounds(10, 149, 106, 23);
		pnlChiTiet.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(PnlPhanCong.class.getResource("/image/settings.png")));
		btnSua.setBackground(Color.CYAN);
		btnSua.setBorderPainted(false);
		btnSua.setBounds(165, 149, 106, 23);
		pnlChiTiet.add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.setBorderPainted(false);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXoa.setIcon(new ImageIcon(PnlPhanCong.class.getResource("/image/trash.png")));
		btnXoa.setBackground(Color.CYAN);
		btnXoa.setBounds(10, 183, 106, 23);
		pnlChiTiet.add(btnXoa);

		JLabel lblSanPham = new JLabel("Sản phẩm:");
		lblSanPham.setBounds(33, 93, 73, 17);
		pnlChiTiet.add(lblSanPham);

		txtTenSP = new JTextField();
		txtTenSP.setBounds(116, 90, 133, 20);
		pnlChiTiet.add(txtTenSP);
		txtTenSP.setColumns(10);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setBackground(Color.CYAN);
		btnXacNhan.setBorderPainted(false);
		btnXacNhan.setIcon(new ImageIcon(PnlPhanCong.class.getResource("/image/paste-icon.png")));
		btnXacNhan.setBounds(165, 183, 106, 23);
		pnlChiTiet.add(btnXacNhan);

		JLabel lblIconMuiTen2 = new JLabel("");
		lblIconMuiTen2.setIcon(new ImageIcon(PnlPhanCong.class.getResource("/image/Icon_MuiTen.png")));
		lblIconMuiTen2.setBounds(602, 108, 32, 27);
		add(lblIconMuiTen2);

		txtMaCN = new JTextField();
		txtMaCN.setBounds(116, 35, 117, 20);
		pnlChiTiet.add(txtMaCN);
		txtMaCN.setColumns(10);

		lblMaCN = new JLabel("Mã CN:");
		lblMaCN.setBounds(47, 38, 49, 17);
		pnlChiTiet.add(lblMaCN);

		// Khóa chức năng
		moKhoaControls(false);
		moKhoaTextfields(false);
		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);

		// Thực thi chức năng
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnTimCN.addActionListener(this);
		btnTimTheoNgay.addActionListener(this);
		cboTimCDPC.addActionListener(this);
		cboCongDoan.addActionListener(this);

		//
		moKhoaTextfields(false);
		moKhoaControls(true);
		btnXacNhan.setEnabled(false);

		// Khóa table DSCN và DSCD
		tableDSCN.setEnabled(false);
		tableDSCD.setEnabled(false);

		DocDuLieuTuDatabaseVaoTableDSCN();
		DocDuLieuTuDatabaseVaoTableDSCD();
		DocDuLieuTuDatabaseVaoTableDSPC();
	}

	public void DocDuLieuTuDatabaseVaoTableDSCN() {
		ArrayList<CongNhan> dscn = new ArrayList<CongNhan>();
		dscn = dao_CN.getAllCongNhan();
		for (CongNhan congNhan : dscn) {
			Object[] rowData = { congNhan.getMaCN(), congNhan.getTenCN() };
			dataModelDSCN.addRow(rowData);
		}
		tableDSCN.setModel(dataModelDSCN);
		tableDSCN.getColumnModel().getColumn(0).setPreferredWidth(66);
		tableDSCN.getColumnModel().getColumn(1).setPreferredWidth(281);
		tableDSCN.addMouseListener(this);
	}

	public void DocDuLieuTuDatabaseVaoTableDSCD() {
		ArrayList<PhanCong> dspc = new ArrayList<PhanCong>();
		dspc = dao_CCD.getAllPhanCong();
		for (PhanCong congDoan : dspc) {
			Object[] rowData = { congDoan.getMaPC(),
					congDoan.getCongDoanSX() + " (" + congDoan.getSanPham().getTenSP() + ")" };
			dataModelDSCD.addRow(rowData);
		}
		tableDSCD.setModel(dataModelDSCD);
		tableDSCD.getColumnModel().getColumn(0).setPreferredWidth(95);
		tableDSCD.getColumnModel().getColumn(1).setPreferredWidth(246);
		tableDSCD.addMouseListener(this);
	}

	public void DocDuLieuTuDatabaseVaoTableDSPC() {
		ArrayList<ChiTietPhanCong> ctpc = new ArrayList<ChiTietPhanCong>();
		ctpc = dao_CTPC.getAllChiTietPC();
		int stt = 1;
		for (ChiTietPhanCong chiTietPhanCong : ctpc) {
			Object[] rowData = { stt, chiTietPhanCong.getCongNhan().getMaCN(), chiTietPhanCong.getCongNhan().getTenCN(),
					chiTietPhanCong.getNgayCTPC(), chiTietPhanCong.getPhanCong().getCongDoanSX(),
					chiTietPhanCong.getPhanCong().getSanPham().getTenSP(), chiTietPhanCong.getSoLuongPC() };
			stt++;
			dataModelDSPC.addRow(rowData);
		}
		tableDSPC.setModel(dataModelDSPC);
		tableDSPC.getColumnModel().getColumn(0).setPreferredWidth(29);
		tableDSPC.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableDSPC.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableDSPC.getColumnModel().getColumn(3).setPreferredWidth(90);
		tableDSPC.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableDSPC.getColumnModel().getColumn(5).setPreferredWidth(160);
		tableDSPC.getColumnModel().getColumn(6).setPreferredWidth(80);
		tableDSPC.addMouseListener(this);
	}

	private void xoaRong() {
		txtMaCN.setText("");
		txtTenCN.setText("");
		txtCongDoan.setText("");
		txtTenSP.setText("");
		txtSoLuong.setText("");
	}

	private void moKhoaControls(boolean b) {
		btnSua.setEnabled(b);
		btnXoa.setEnabled(b);
		btnThem.setEnabled(b);
		btnXacNhan.setEnabled(b);
	}

	private void moKhoaTextfields(boolean b) {
		txtTenCN.setEditable(b);
		txtCongDoan.setEditable(b);
		txtTenSP.setEditable(b);
		txtSoLuong.setEditable(b);
		txtMaCN.setEditable(b);
	}

	// Tạo 1 chi tiết phân công mới
	public ChiTietPhanCong createCTPC() {
		Date ngayPC = new Date();
		ngayPC = dateChooser.getDate();
		long javaTime = ngayPC.getTime();
		java.sql.Date sqlNgayPC = new java.sql.Date(javaTime);
		int soLuongCT = Integer.parseInt(txtSoLuong.getText().trim());
		CongNhan cn = new CongNhan(txtMaCN.getText().trim());
		String maPC = "";
		for (PhanCong phanCong : dao_CCD.getAllPhanCong()) {
			if (txtCongDoan.getText().equalsIgnoreCase(phanCong.getCongDoanSX())
					&& txtTenSP.getText().equalsIgnoreCase(phanCong.getSanPham().getTenSP())) {
				maPC = phanCong.getMaPC();
			}
		}
		PhanCong pc = new PhanCong(maPC);
		ChiTietPhanCong ctPC = new ChiTietPhanCong(cn, pc, soLuongCT, sqlNgayPC);
		return ctPC;
	}

	// Sửa chi tiết phân công
	private void suaPC() {
		int row = tableDSPC.getSelectedRow();
		if (row >= 0) {
			ChiTietPhanCong ctpc = createCTPC();
			java.util.Date ngayCTPC = new java.util.Date();
			long javaTime = ngayCTPC.getTime();
			java.sql.Date sqlDate = new java.sql.Date(javaTime);
			if (dao_CTPC.update(ctpc)) {
				tableDSPC.setValueAt(sqlDate, row, 3);
				tableDSPC.setValueAt(txtCongDoan.getText(), row, 4);
				tableDSPC.setValueAt(txtTenSP.getText(), row, 5);
				tableDSPC.setValueAt(txtSoLuong.getText(), row, 6);
			}
		}
	}

	private void xoaPC() {
		int row = tableDSPC.getSelectedRow();
		String maCN = dataModelDSPC.getValueAt(row, 1).toString();
		String maPC = dao_CTPC.timPC(maCN).getPhanCong().getMaPC();
		if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phân công này ko?", "Cảnh báo!!!",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (row >= 0) {
				if (dao_CTPC.delete(maCN, maPC)) {
					dataModelDSPC.removeRow(row);
					xoaRong();
					dataModelDSPC.getDataVector().removeAllElements();
					DocDuLieuTuDatabaseVaoTableDSPC();
					dataModelDSCN.getDataVector().removeAllElements();
					DocDuLieuTuDatabaseVaoTableDSCN();
					JOptionPane.showMessageDialog(null, "Xóa thành công!");
				}
			}
		} else {
			xoaRong();
		}
	}

	private void timKiemTheoNgay() {
		java.util.Date ngayCTPC = new java.util.Date();
		ngayCTPC = dateChooser.getDate();
		long javaTime = ngayCTPC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<ChiTietPhanCong> dsCTPC = dao_CTPC.timKiemDSCTPCTheoNgay(sqlDate);
		if (dsCTPC.size() == 0)
			JOptionPane.showMessageDialog(null, "Ngày này chưa có phân công!");
		else {
			dataModelDSPC.getDataVector().removeAllElements();
			int stt = 1;
			for (ChiTietPhanCong chiTietPhanCong : dsCTPC) {
				Object[] rowData = { stt, chiTietPhanCong.getCongNhan().getMaCN(),
						chiTietPhanCong.getCongNhan().getTenCN(), chiTietPhanCong.getNgayCTPC(),
						chiTietPhanCong.getPhanCong().getCongDoanSX(),
						chiTietPhanCong.getPhanCong().getSanPham().getTenSP(), chiTietPhanCong.getSoLuongPC() };
				stt++;
				dataModelDSPC.addRow(rowData);
				stt++;
			}
		}
	}

	private void showMessage(JTextField tf, String mes) {
		JOptionPane.showMessageDialog(tf, mes);
		tf.selectAll();
		tf.requestFocus();
	}

	private int tong(String congDoan, String tenSP) {
		int tongSoLuong = 0;
		java.util.Date ngayCTPC = new java.util.Date();
		long javaTime = ngayCTPC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<ChiTietPhanCong> dsCTPC = dao_CTPC.timKiemDSCTPCTheoNgay(sqlDate);
		for (ChiTietPhanCong ct : dsCTPC) {
			if (ct.getPhanCong().getCongDoanSX().equalsIgnoreCase(congDoan)
					&& ct.getPhanCong().getSanPham().getTenSP().equalsIgnoreCase(tenSP))
				tongSoLuong += ct.getSoLuongPC();
		}
		return tongSoLuong;
	}

	// Ràng buộc
	private boolean valid() {
		String soLuongCuThe = txtSoLuong.getText();
		if (soLuongCuThe.isEmpty() || soLuongCuThe.equalsIgnoreCase("0")) {
			showMessage(txtSoLuong, "Vui lòng nhập số lượng cụ thể!");
			return false;
		} else if (soLuongCuThe.matches("[\\D]*")) {
			showMessage(txtSoLuong, "Vui lòng nhập số!");
			return false;
		}

		if (dao_CTPC.timPC(txtMaCN.getText().trim()) != null) {
			JOptionPane.showMessageDialog(this, "Công nhân này đã được phân công!\nVui lòng chọn công nhân khác!");
			return false;
		}
		String maPC = "";
		for (PhanCong phanCong : dao_CCD.getAllPhanCong()) {
			if (txtCongDoan.getText().equalsIgnoreCase(phanCong.getCongDoanSX())
					&& txtTenSP.getText().equalsIgnoreCase(phanCong.getSanPham().getTenSP())) {
				maPC = phanCong.getMaPC();
			}
		}
		PhanCong pc = dao_CCD.timPC(maPC);
		if (Integer.parseInt(soLuongCuThe) > (pc.getSoLuong() - tong(txtCongDoan.getText(), txtTenSP.getText()))) {
			txtSoLuong.setText(String.valueOf(pc.getSoLuong() - tong(txtCongDoan.getText(), txtTenSP.getText())));
			showMessage(txtSoLuong,
					"Số lượng cho phép là: " + (pc.getSoLuong() - tong(txtCongDoan.getText(), txtTenSP.getText())));	
			return false;
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		Object o = e.getSource();
		if (o.equals(btnSua)) {
			if (txtSoLuong.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phân công cần sửa");
			else if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				txtSoLuong.requestFocus();
				moKhoaTextfields(false);
				txtSoLuong.setEditable(true);
				moKhoaControls(false);
				btnXacNhan.setEnabled(true);
				btnSua.setEnabled(true);
				btnSua.setText("Hủy");
				btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/Icon_Huy.png")));
				tableDSPC.setEnabled(false);
			} else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				xoaRong();
				btnXacNhan.setEnabled(false);
				btnSua.setText("Sửa");
				btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/settings.png")));
				tableDSPC.setEnabled(true);
			}
		}
		if (o.equals(btnXacNhan)) {
			if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				dao_CTPC.updateSoLuongBang0(txtMaCN.getText().trim());
				if (valid()) {
					suaPC();
					moKhoaTextfields(false);
					moKhoaControls(true);
					btnXacNhan.setEnabled(false);
					btnSua.setText("Sửa");
					btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/settings.png")));
					xoaRong();
					JOptionPane.showMessageDialog(null, "Thông tin đã được cập nhật!");
					tableDSPC.setEnabled(true);
				}
			}
			if (btnThem.getText().equalsIgnoreCase("Hủy")) {
				if (txtMaCN.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Vui lòng chọn công nhân!");
				else if (txtCongDoan.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Vui lòng chọn công đoạn theo sản phẩm!");
				if (valid()) {
					ChiTietPhanCong ctpc = createCTPC();
					if (dao_CTPC.themChiTietPhanCong(ctpc)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String ngayCTPC = sdf.format(dateChooser.getDate());
						int stt = 1;
						Object[] rowData = { stt, ctpc.getCongNhan().getMaCN(), txtTenCN.getText(), ngayCTPC,
								txtCongDoan.getText(), txtTenSP.getText(), txtSoLuong.getText() };
						stt++;
						dataModelDSPC.addRow(rowData);
						JOptionPane.showMessageDialog(null, "Phân công mới đã được thêm vào!");
						dataModelDSCN.getDataVector().removeAllElements();
						DocDuLieuTuDatabaseVaoTableDSCN();
						moKhoaTextfields(false);
						moKhoaControls(true);
						xoaRong();
						btnXacNhan.setEnabled(false);
						btnThem.setText("Thêm");
						btnThem.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/add.png")));
						tableDSCN.setEnabled(false);
						tableDSCD.setEnabled(false);
						tableDSCN.setRowSelectionAllowed(isEnabled() == false);
						tableDSCD.setRowSelectionAllowed(isEnabled() == false);
					}
				}
				dataModelDSPC.getDataVector().removeAllElements();
				DocDuLieuTuDatabaseVaoTableDSPC();
			}
		}
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				txtSoLuong.requestFocus();
				moKhoaTextfields(false);
				txtSoLuong.setEditable(true);
				moKhoaControls(false);
				btnXacNhan.setEnabled(true);
				btnThem.setEnabled(true);
				btnThem.setText("Hủy");
				btnThem.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/Icon_Huy.png")));
				tableDSCN.setEnabled(true);
				tableDSCD.setEnabled(true);
			} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				xoaRong();
				btnXacNhan.setEnabled(false);
				btnThem.setText("Thêm");
				btnThem.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/add.png")));
				tableDSCN.setEnabled(false);
				tableDSCD.setEnabled(false);
				//
				tableDSCN.setRowSelectionAllowed(isEnabled() == false);
				tableDSCD.setRowSelectionAllowed(isEnabled() == false);
			}
		}
		if (o.equals(btnXoa)) {
			if (txtSoLuong.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phân công cần xóa!");
			else {
				xoaPC();
			}
		}
		if (o.equals(btnTimCN)) {
			ArrayList<CongNhan> ds = dao_CN.getCongNhanTheoTen(txtTimTenCN.getText().toString().trim());
			if (ds.size() == 0)
				JOptionPane.showMessageDialog(this, "Ko có công nhân này trong danh sách");
			else {
				dataModelDSCN.getDataVector().removeAllElements();
				for (CongNhan cn : ds) {
					Object[] rowData = { cn.getMaCN(), cn.getTenCN() };
					dataModelDSCN.addRow(rowData);
				}
				tableDSCN.setModel(dataModelDSCN);
			}
		}
		if (o.equals(cboCongDoan)) {
			ArrayList<PhanCong> ds = dao_CCD.timKiemDSPCTheoCongDoan(cboCongDoan.getSelectedItem().toString().trim());
			if (cboCongDoan.getSelectedItem().toString().trim() == "Tất cả") {
				dataModelDSCD.getDataVector().removeAllElements();
				DocDuLieuTuDatabaseVaoTableDSCD();
			} else if (ds.size() == 0)
				JOptionPane.showMessageDialog(this, "Ko có công đoạn này trong danh sách");
			else {
				dataModelDSCD.getDataVector().removeAllElements();
				for (PhanCong pc : ds) {
					Object[] rowData = { pc.getMaPC(), pc.getCongDoanSX() + " (" + pc.getSanPham().getTenSP() + ")" };
					dataModelDSCD.addRow(rowData);
				}
				tableDSCD.setModel(dataModelDSCD);
			}
		}
		if (o.equals(cboTimCDPC)) {
			ArrayList<ChiTietPhanCong> ds = dao_CTPC
					.timKiemDSCTPCTheoCongDoan(cboTimCDPC.getSelectedItem().toString().trim());
			if (cboTimCDPC.getSelectedItem().toString().trim() == "Tất cả") {
				dataModelDSPC.getDataVector().removeAllElements();
				DocDuLieuTuDatabaseVaoTableDSPC();
			} else if (ds.size() == 0)
				JOptionPane.showMessageDialog(this, "Ko tìm thấy công đoạn này trong danh sách phân công");
			else {
				dataModelDSPC.getDataVector().removeAllElements();
				int stt = 1;
				for (ChiTietPhanCong chiTietPhanCong : ds) {
					Object[] rowData = { stt, chiTietPhanCong.getCongNhan().getMaCN(),
							chiTietPhanCong.getCongNhan().getTenCN(), chiTietPhanCong.getNgayCTPC(),
							chiTietPhanCong.getPhanCong().getCongDoanSX(),
							chiTietPhanCong.getPhanCong().getSanPham().getTenSP(), chiTietPhanCong.getSoLuongPC() };
					stt++;
					dataModelDSPC.addRow(rowData);
				}
				tableDSPC.setModel(dataModelDSPC);
			}
		}
		if (o.equals(btnTimTheoNgay))
			timKiemTheoNgay();
	}
}
