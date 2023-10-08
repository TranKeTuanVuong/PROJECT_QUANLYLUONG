package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.DAO_CongNhan;
import dao.DAO_PhanCong;
import dao.DAO_SanPham;
import entity.PhanCong;
import entity.SanPham;

public class PnlChiaCongDoan extends JPanel implements ActionListener, MouseListener {
	private JTable tableDSSP;
	private JTextField txtTimKiemSP;
	private JTextField txtTenSP;
	private JTextField txtSoLuong;
	private JTable tableDSPC;
	private JTable tableDSCD;

	private JButton btnXacNhan, btnSua, btnXoa, btnReload, btnTimKiemSP, btnThem, btnTimTheoNgay;
	private JDateChooser datePC, dateChoosertTim;

	private JComboBox comboBoxCongDoan;

	private DefaultTableModel dataModelDSSP;
	String[] headersDSSP = { "STT", "Tên sản phẩm" };
	private DefaultTableModel dataModelDSCD;
	String[] headersDSCD = { "STT", "Mã công đoạn", "Tên sản phẩm", "Ngày chia công đoạn", "Công đoạn",
			"Số lượng/ 1 Ngày" };

	private DAO_SanPham dao_SP;
	private DAO_CongNhan dao_CN;
	private DAO_PhanCong dao_PC;
	private JTextField txtMaCD;

	/**
	 * Create the panel.
	 */
	public PnlChiaCongDoan() {

		dao_SP = new DAO_SanPham();
		dao_CN = new DAO_CongNhan();
		dao_PC = new DAO_PhanCong();

		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("Chia công đoạn"));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.CYAN));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 24, 304, 193);
		add(panel);
		panel.setLayout(null);

		JLabel lblTitle = new JLabel("Danh sách sản phẩm");
		lblTitle.setBounds(1, 1, 335, 17);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTitle);

		JScrollPane scrollPaneDSSP = new JScrollPane();
		scrollPaneDSSP.setBounds(11, 46, 281, 136);
		panel.add(scrollPaneDSSP);

		// Táº¡o báº£ng vÃ  thÃªm cÃ´ng nhÃ¢n vÃ o báº£ng
		dataModelDSSP = new DefaultTableModel(headersDSSP, 0);
		tableDSSP = new JTable(dataModelDSSP);
		scrollPaneDSSP.setViewportView(tableDSSP);

		tableDSSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String maPC = null;
					int row = tableDSSP.getSelectedRow();
					if (row >= 0) {
						txtTenSP.setText(dataModelDSSP.getValueAt(row, 1).toString());
						moKhoaTextfields(true);
						for (SanPham sp : dao_SP.getAllSanPham()) {
							if (sp.getTenSP().equalsIgnoreCase((String) dataModelDSSP.getValueAt(row, 1)))
								maPC = dao_PC.getAutoMaPC() + sp.getMaSP();
						}
						txtMaCD.setText(maPC);
						moKhoaControls(false);
						btnThem.setEnabled(true);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Ko còn sản phẩm để phân công!");
				}
			}
		});

		txtTimKiemSP = new JTextField();
		txtTimKiemSP.setToolTipText("Tìm theo tên nhân viên");
		txtTimKiemSP.setBounds(116, 22, 149, 20);
		panel.add(txtTimKiemSP);
		txtTimKiemSP.setColumns(10);

		btnTimKiemSP = new JButton("");
		btnTimKiemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiemSP.setBounds(268, 22, 24, 20);
		panel.add(btnTimKiemSP);
		btnTimKiemSP.setBackground(Color.WHITE);
		btnTimKiemSP.setIcon(new ImageIcon(PnlChiaCongDoan.class.getResource("/image/search-icon.png")));

		JLabel lblIconMuiTen = new JLabel("");
		lblIconMuiTen.setIcon(new ImageIcon(PnlChiaCongDoan.class.getResource("/image/Icon_MuiTen.png")));
		lblIconMuiTen.setBounds(324, 104, 32, 32);
		add(lblIconMuiTen);

		JPanel pnlChiTietPC = new JPanel();
		pnlChiTietPC.setBorder(new LineBorder(Color.CYAN));
		pnlChiTietPC.setBackground(Color.WHITE);
		pnlChiTietPC.setBounds(366, 24, 549, 193);
		add(pnlChiTietPC);
		pnlChiTietPC.setLayout(null);

		JLabel lblTenSP = new JLabel("Sản phẩm:");
		lblTenSP.setBounds(10, 59, 63, 17);
		pnlChiTietPC.add(lblTenSP);

		txtTenSP = new JTextField();
		txtTenSP.setEditable(false);
		txtTenSP.setBounds(83, 57, 142, 20);
		pnlChiTietPC.add(txtTenSP);
		txtTenSP.setColumns(10);

		JLabel lblNgayPC = new JLabel("Ngày chia CĐ: ");
		lblNgayPC.setBounds(277, 11, 94, 16);
		pnlChiTietPC.add(lblNgayPC);

		datePC = new JDateChooser();
		datePC.setDate(new Date());
		datePC.setBounds(374, 11, 134, 20);
		datePC.setEnabled(false);
		pnlChiTietPC.add(datePC);

		JLabel lblCongDoan = new JLabel("C\u00F4ng \u0111o\u1EA1n:");
		lblCongDoan.setBounds(293, 59, 71, 17);
		pnlChiTietPC.add(lblCongDoan);

		comboBoxCongDoan = new JComboBox();
		comboBoxCongDoan.setModel(new DefaultComboBoxModel(new String[] { "R\u1EADp m\u00E1y", "May v\u1EAFt s\u1ED5",
				"May m\u00F3c x\u00EDch k\u00E9p", "L\u00E0 \u1EE7i", "\u0110\u00F3ng g\u00F3i" }));
		comboBoxCongDoan.setBounds(374, 56, 165, 22);
		pnlChiTietPC.add(comboBoxCongDoan);

		JLabel lblSoLuongSP = new JLabel("S\u1ED1 l\u01B0\u1EE3ng: ");
		lblSoLuongSP.setBounds(304, 106, 67, 17);
		pnlChiTietPC.add(lblSoLuongSP);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(374, 104, 121, 20);
		pnlChiTietPC.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		btnXacNhan = new JButton("X\u00E1c nh\u1EADn");
		btnXacNhan.setIcon(new ImageIcon(PnlChiaCongDoan.class.getResource("/image/paste-icon.png")));
		btnXacNhan.setBackground(Color.CYAN);
		btnXacNhan.setBorderPainted(false);
		btnXacNhan.setBounds(415, 159, 103, 23);
		pnlChiTietPC.add(btnXacNhan);

		btnSua = new JButton("S\u1EEDa");
		btnSua.setIcon(new ImageIcon(PnlChiaCongDoan.class.getResource("/image/settings.png")));
		btnSua.setBackground(Color.CYAN);
		btnSua.setBorderPainted(false);
		btnSua.setBounds(285, 159, 109, 23);
		pnlChiTietPC.add(btnSua);

		btnXoa = new JButton("X\u00F3a");
		btnXoa.setBorderPainted(false);
		btnXoa.setIcon(new ImageIcon(PnlChiaCongDoan.class.getResource("/image/trash.png")));
		btnXoa.setBackground(Color.CYAN);
		btnXoa.setBounds(155, 159, 109, 23);
		pnlChiTietPC.add(btnXoa);

		JLabel lblMaPC = new JLabel("Mã CD:");
		lblMaPC.setBounds(23, 11, 65, 17);
		pnlChiTietPC.add(lblMaPC);

		txtMaCD = new JTextField();
		txtMaCD.setEnabled(false);
		txtMaCD.setEditable(false);
		txtMaCD.setBounds(83, 9, 121, 20);
		pnlChiTietPC.add(txtMaCD);
		txtMaCD.setColumns(10);

		JPanel panelDanhSachPC = new JPanel();
		panelDanhSachPC.setBorder(new LineBorder(Color.CYAN));
		panelDanhSachPC.setBackground(Color.WHITE);
		panelDanhSachPC.setBounds(10, 238, 908, 260);
		add(panelDanhSachPC);
		panelDanhSachPC.setLayout(null);

		JLabel lblDSPC = new JLabel("Danh sách công đoạn theo sản phẩm");
		lblDSPC.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSPC.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblDSPC.setBounds(10, 0, 888, 21);
		panelDanhSachPC.add(lblDSPC);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 888, 200);
		panelDanhSachPC.add(scrollPane);

		dataModelDSCD = new DefaultTableModel(headersDSCD, 0);
		tableDSCD = new JTable(dataModelDSCD);
		tableDSCD.getColumnModel().getColumn(0).setPreferredWidth(29);
		tableDSCD.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableDSCD.getColumnModel().getColumn(2).setPreferredWidth(191);
		tableDSCD.getColumnModel().getColumn(3).setPreferredWidth(136);
		tableDSCD.getColumnModel().getColumn(4).setPreferredWidth(140);
		scrollPane.setViewportView(tableDSCD);

		tableDSCD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row1 = tableDSCD.getSelectedRow();
				if (row1 >= 0) {
					txtMaCD.setText(dataModelDSCD.getValueAt(row1, 1).toString());
					txtTenSP.setText(dataModelDSCD.getValueAt(row1, 2).toString());
					String id = (String) dataModelDSCD.getValueAt(row1, 1);
					PhanCong pc = dao_PC.timPC(id);
					datePC.setDate(pc.getNgayPC());
					comboBoxCongDoan.setSelectedItem(dataModelDSCD.getValueAt(row1, 4).toString());
					txtSoLuong.setText(dataModelDSCD.getValueAt(row1, 5).toString());
					moKhoaControls(true);
					btnThem.setEnabled(false);
					btnXacNhan.setEnabled(false);
				}
			}
		});

		dateChoosertTim = new JDateChooser(new Date());
		dateChoosertTim.setBounds(10, 23, 150, 20);
		panelDanhSachPC.add(dateChoosertTim);

		btnReload = new JButton("Reload");
		btnReload.setBackground(Color.CYAN);
		btnReload.setBorderPainted(false);
		btnReload.setIcon(new ImageIcon(PnlChiaCongDoan.class.getResource("/image/return.png")));
		btnReload.setBounds(772, 20, 107, 23);
		panelDanhSachPC.add(btnReload);

		btnTimTheoNgay = new JButton("");
		btnTimTheoNgay.setIcon(new ImageIcon(PnlChiaCongDoan.class.getResource("/image/search-icon.png")));
		btnTimTheoNgay.setBounds(158, 23, 23, 20);
		panelDanhSachPC.add(btnTimTheoNgay);

		btnThem = new JButton("Thêm");
		btnThem.setBackground(Color.CYAN);
		btnThem.setBorderPainted(false);
		btnThem.setIcon(new ImageIcon(PnlChiaCongDoan.class.getResource("/image/add.png")));
		btnThem.setBounds(31, 159, 103, 23);
		pnlChiTietPC.add(btnThem);

		JTableHeader hd = tableDSCD.getTableHeader();
		hd.setBackground(Color.cyan);
		hd.setFont(new Font("Time New Roman", Font.BOLD, 10));
//		JTableHeader hd1=tableDSPC.getTableHeader();
//		hd1.setBackground(Color.cyan);
//		hd1.setFont(new Font("Time New Roman", Font.BOLD,10));
		JTableHeader hd2 = tableDSSP.getTableHeader();
		hd2.setBackground(Color.cyan);
		hd2.setFont(new Font("Time New Roman", Font.BOLD, 10));

		moKhoaTextfields(false);
		btnXacNhan.setEnabled(false);
		btnThem.setEnabled(false);

		// Thực thi
		btnXacNhan.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnReload.addActionListener(this);
		btnThem.addActionListener(this);
		btnTimKiemSP.addActionListener(this);
		btnTimTheoNgay.addActionListener(this);

		DocDuLieuTuDatabaseVaoTableDSPC();
		DocDuLieuTuDatabaseVaoTableDSSP();
	}

	public void DocDuLieuTuDatabaseVaoTableDSSP() {
		int stt = 1;
		ArrayList<SanPham> dssp = new ArrayList<SanPham>();
		dssp = dao_SP.getAllSanPham();
		for (SanPham sanPham : dssp) {
			Object[] rowData = { stt, sanPham.getTenSP() };
			dataModelDSSP.addRow(rowData);
			stt++;
		}
		tableDSSP.setModel(dataModelDSSP);
		tableDSSP.getColumnModel().getColumn(0).setPreferredWidth(66);
		tableDSSP.getColumnModel().getColumn(1).setPreferredWidth(281);
		tableDSSP.addMouseListener(this);
	}

	public void DocDuLieuTuDatabaseVaoTableDSPC() {
		int stt = 1;
		for (PhanCong pc : dao_PC.getAllPhanCong()) {
			Object[] rowData = { stt, pc.getMaPC(), pc.getSanPham().getTenSP(), pc.getNgayPC(), pc.getCongDoanSX(),
					pc.getSoLuong() };
			dataModelDSCD.addRow(rowData);
			stt++;
		}
		tableDSCD.setModel(dataModelDSCD);
		tableDSCD.addMouseListener(this);
	}

	public void xoaRong() {
		txtMaCD.setText("");
		txtTenSP.setText("");
		comboBoxCongDoan.setSelectedIndex(0);
		txtSoLuong.setText("");
	}

	private void moKhoaControls(boolean b) {
		btnXacNhan.setEnabled(b);
		btnSua.setEnabled(b);
		btnXoa.setEnabled(b);
		btnThem.setEnabled(b);
	}

	private void moKhoaTextfields(boolean b) {
		comboBoxCongDoan.setEnabled(b);
		txtSoLuong.setEditable(b);
	}

	public PhanCong createPC() {
		String maPC = txtMaCD.getText().trim();
		Date ngayPC = new Date();
		ngayPC = datePC.getDate();
		long javaTime = ngayPC.getTime();
		java.sql.Date sqlNgayPC = new java.sql.Date(javaTime);
		int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
		String congDoan = comboBoxCongDoan.getSelectedItem().toString();
		String maSP = "";
		for (SanPham sanPham : dao_SP.getAllSanPham()) {
			if (txtTenSP.getText().equalsIgnoreCase(sanPham.getTenSP())) {
				maSP = sanPham.getMaSP();
			}
		}
		SanPham sp = new SanPham(maSP);
		PhanCong pc = new PhanCong(maPC, sqlNgayPC, congDoan, soLuong, sp);
		return pc;
	}

	private void suaPC() {
		int row = tableDSCD.getSelectedRow();
		if (row >= 0) {
			PhanCong pc = createPC();
			if (dao_PC.update(pc)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(datePC.getDate());
				tableDSCD.setValueAt(date, row, 3);
				tableDSCD.setValueAt(comboBoxCongDoan.getSelectedItem(), row, 4);
				tableDSCD.setValueAt(txtSoLuong.getText(), row, 5);
			}
		}
	}

	private void xoaPC() {
		int row = tableDSCD.getSelectedRow();
		String maPC = dataModelDSCD.getValueAt(row, 1).toString();
		if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa công đoạn này ko?", "Cảnh báo!!!",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (row >= 0) {
				if (dao_PC.delete(maPC)) {
					dataModelDSCD.removeRow(row);
					xoaRong();
					dataModelDSCD.getDataVector().removeAllElements();
					DocDuLieuTuDatabaseVaoTableDSPC();
				}
			}
		} else
			xoaRong();
	}

	private boolean valid() {
		String soLuongCuThe = txtSoLuong.getText();
		if (soLuongCuThe.isEmpty() || soLuongCuThe.equalsIgnoreCase("0")) {
			JOptionPane.showMessageDialog(this, "Số lượng tổng không được bỏ trống và phải khác 0!");
			return false;
		} else if (soLuongCuThe.matches("[\\D]*")) {
			JOptionPane.showMessageDialog(this, "Chỉ được nhập số nguyên!");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnSua)) {
			if (txtTenSP.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công nhân cần sửa");
			else if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				moKhoaTextfields(true);
				moKhoaControls(false);
				tableDSSP.setEnabled(false);
				btnXacNhan.setEnabled(true);
				btnSua.setEnabled(true);
				btnSua.setText("Hủy");
				btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/Icon_Huy.png")));
			} else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				btnXacNhan.setEnabled(false);
				btnThem.setEnabled(false);
				btnSua.setText("Sửa");
				tableDSSP.setEnabled(true);
				xoaRong();
				btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/settings.png")));
				//
			}
		}
		if (o.equals(btnXacNhan)) {
			if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				if (valid()) {
					suaPC();
					moKhoaTextfields(false);
					moKhoaControls(true);
					btnXacNhan.setEnabled(false);
					btnThem.setEnabled(false);
					btnSua.setText("Sửa");
					btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/settings.png")));
					JOptionPane.showMessageDialog(null, "Thông tin đã được cập nhật!");
					xoaRong();
				}
			}
		}
		if (o.equals(btnThem)) {
			if (valid()) {
				PhanCong pc = createPC();
				if (dao_PC.themPhanCong(pc)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String ngayPC = sdf.format(datePC.getDate());
					int stt = 1;
					Object[] rowData = { stt, txtMaCD.getText(), txtTenSP.getText(), ngayPC,
							comboBoxCongDoan.getSelectedItem(), txtSoLuong.getText() };
					stt++;
					dataModelDSCD.addRow(rowData);
					xoaRong();
					moKhoaTextfields(false);
					JOptionPane.showMessageDialog(null, "Công đoạn theo sản phẩm mới đã được thêm vào!");
					moKhoaControls(true);
					btnThem.setEnabled(false);
					btnXacNhan.setEnabled(false);
				}
				dataModelDSCD.getDataVector().removeAllElements();
				DocDuLieuTuDatabaseVaoTableDSPC();
			}
		}
		if (o.equals(btnXoa)) {
			if (txtTenSP.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công đoạn cần xóa");
			else
				xoaPC();
		}
		if (o.equals(btnReload)) {
			dataModelDSCD.getDataVector().removeAllElements();
			DocDuLieuTuDatabaseVaoTableDSPC();

		}
		if (o.equals(btnTimKiemSP)) {
			ArrayList<SanPham> ds = dao_SP.getSanPhamTheoTen(txtTimKiemSP.getText().toString().trim());
			int stt = 1;
			if (ds.size() == 0)
				JOptionPane.showMessageDialog(this, "Ko có sản phẩm này trong danh sách");
			else {
				dataModelDSSP.getDataVector().removeAllElements();
				for (SanPham sp : ds) {
					Object[] rowData = { stt, sp.getTenSP() };
					dataModelDSSP.addRow(rowData);
					stt++;
				}
				tableDSSP.setModel(dataModelDSSP);
			}
		}
		if (o.equals(btnTimTheoNgay))
			timKiemTheoNgay();
	}

	private void timKiemTheoNgay() {
		java.util.Date ngayPC = new java.util.Date();
		ngayPC = dateChoosertTim.getDate();
		long javaTime = ngayPC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<PhanCong> dsPC = dao_PC.timKiemDSPCTheoNgay(sqlDate);
		if (dsPC.size() == 0) {
			JOptionPane.showMessageDialog(null, "Ngày này chưa được chia công đoạn!");
			dateChoosertTim.setDate(new Date());
		} else {
			dataModelDSCD.getDataVector().removeAllElements();
			int stt = 1;
			for (PhanCong dto_PC : dsPC) {
				Object[] rowData = { stt, dto_PC.getMaPC(), dto_PC.getSanPham().getTenSP(), dto_PC.getNgayPC(),
						dto_PC.getCongDoanSX(), dto_PC.getSoLuong() };
				dataModelDSCD.addRow(rowData);
				stt++;
			}
		}
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
}
