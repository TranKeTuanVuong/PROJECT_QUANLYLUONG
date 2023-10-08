package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.DAO_PhanCong;
import dao.DAO_CongNhan;
import dao.DAO_Kho;
import dao.DAO_NhanVien;
import entity.CongNhan;
import entity.NhanVien;

public class PnlDanhMucCongNhan extends JPanel implements ActionListener, MouseListener {
	private JTextField txtMaCN;
	private JTextField txtTenCN;
	private JTextField txtDiaChi;
	private JTextField txtCMND;
	private JTextField txtTimKiem;
	private JTextField txtMaNV;
	private JLabel lblAvt;
	private JComboBox cbPhuCap, cbTenNV, cbTimTheoNVQL;
	private JRadioButton radNam, radNu;
	private JTable tableCN;
	private JButton btnThem, btnSua, btnXoa, btnLuu, btnThemAvt, btnTimKiem;
	private JDateChooser dateChooser;
	private DefaultTableModel dataModel;
	private String linkImage = "/image/rsz_ngankute.png";

	private DAO_NhanVien dao_NV;
	private DAO_CongNhan dao_CN;
	private DAO_Kho dao_Kho;
	private DAO_PhanCong dao_PC;

	String[] headers = { "STT", "Mã CN", "Tên công nhân", "Giới tính", "Ngày sinh", "CMND/CCCD", "Địa chỉ", "Phụ cấp" };
	private JButton btnReload;

	/**
	 * Create the panel.
	 */
	public PnlDanhMucCongNhan() {
		setBackground(Color.WHITE);
		dao_NV = new DAO_NhanVien();
		dao_CN = new DAO_CongNhan();
		dao_Kho = new DAO_Kho();
		dao_PC = new DAO_PhanCong();

		setLayout(null);

		JPanel pnAnh = new JPanel();
		pnAnh.setBackground(SystemColor.control);
		pnAnh.setBorder(new LineBorder(Color.CYAN));
		pnAnh.setBounds(10, 46, 128, 152);
		add(pnAnh);
		pnAnh.setLayout(null);

		btnThemAvt = new JButton("Tải ảnh lên");
		btnThemAvt.setBounds(20, 122, 87, 19);
		btnThemAvt.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnThemAvt.setBorderPainted(false);
		btnThemAvt.setBackground(Color.cyan);
		pnAnh.add(btnThemAvt);

		lblAvt = new JLabel("");
		lblAvt.setBackground(Color.WHITE);
		lblAvt.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/rsz_ngankute.png")));
		lblAvt.setBounds(10, 11, 108, 109);
		pnAnh.add(lblAvt);

		JPanel pnTitle = new JPanel();
		pnTitle.setBorder(new LineBorder(Color.CYAN));
		pnTitle.setBackground(Color.CYAN);
		pnTitle.setForeground(Color.WHITE);
		pnTitle.setBounds(10, 5, 920, 35);
		add(pnTitle);
		pnTitle.setLayout(null);

		JLabel lblTitle = new JLabel("Công nhân");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblTitle.setBounds(10, 0, 115, 35);
		pnTitle.add(lblTitle);

		JPanel pnChucNang = new JPanel();
		pnChucNang.setBackground(Color.WHITE);
		pnChucNang.setBorder(new LineBorder(Color.CYAN));
		pnChucNang.setBounds(11, 203, 919, 54);
		add(pnChucNang);
		pnChucNang.setLayout(null);

		JPanel pnThongTin = new JPanel();
		pnThongTin.setBackground(Color.WHITE);
		pnThongTin.setBorder(new LineBorder(Color.CYAN));
		pnThongTin.setBounds(148, 46, 782, 152);
		add(pnThongTin);
		pnThongTin.setLayout(null);

		JLabel lblMaCN = new JLabel("Mã công nhân:");
		lblMaCN.setBounds(26, 8, 87, 19);
		pnThongTin.add(lblMaCN);

		txtMaCN = new JTextField();
		txtMaCN.setBackground(SystemColor.control);
		txtMaCN.setEnabled(false);
		txtMaCN.setBounds(123, 7, 110, 19);
		pnThongTin.add(txtMaCN);
		txtMaCN.setColumns(10);

		JLabel lblTenCN = new JLabel("Tên công nhân:");
		lblTenCN.setBounds(280, 7, 99, 19);
		pnThongTin.add(lblTenCN);

		txtTenCN = new JTextField();
		txtTenCN.setBounds(401, 7, 148, 21);
		pnThongTin.add(txtTenCN);
		txtTenCN.setColumns(10);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setBounds(598, 7, 60, 19);
		pnThongTin.add(lblGioiTinh);

		radNam = new JRadioButton("Nam");
		radNam.setBackground(Color.WHITE);
		radNam.setBounds(645, 32, 60, 21);
		pnThongTin.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setBackground(Color.WHITE);
		radNu.setBounds(707, 32, 46, 21);
		pnThongTin.add(radNu);

		ButtonGroup bg = new ButtonGroup();
		bg.add(radNam);
		bg.add(radNu);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setBounds(280, 70, 75, 15);
		pnThongTin.add(lblNgaySinh);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(280, 37, 45, 18);
		pnThongTin.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(401, 36, 148, 21);
		pnThongTin.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		JLabel lblCMND = new JLabel("CMND/CCCD:");
		lblCMND.setBounds(26, 42, 87, 13);
		pnThongTin.add(lblCMND);

		txtCMND = new JTextField();
		txtCMND.setBounds(123, 34, 110, 21);
		pnThongTin.add(txtCMND);
		txtCMND.setColumns(10);

		JLabel lblPhuCap = new JLabel("Phụ cấp:");
		lblPhuCap.setBounds(26, 71, 55, 13);
		pnThongTin.add(lblPhuCap);

		cbPhuCap = new JComboBox();
		cbPhuCap.setModel(new DefaultComboBoxModel(new String[] { "500", "700", "1000" }));
		cbPhuCap.setBounds(123, 63, 110, 23);
		pnThongTin.add(cbPhuCap);

		JPanel pnQuanLy = new JPanel();
		pnQuanLy.setBackground(Color.WHITE);
		pnQuanLy.setBorder(
				new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						"Qu\u1EA3n l\u00FD b\u1EDFi nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0)));
		pnQuanLy.setBounds(109, 95, 549, 46);
		pnThongTin.add(pnQuanLy);
		pnQuanLy.setLayout(null);

		txtMaNV = new JTextField();
		txtMaNV.setBackground(SystemColor.control);
		txtMaNV.setEnabled(false);
		txtMaNV.setBounds(115, 19, 108, 19);
		pnQuanLy.add(txtMaNV);
		txtMaNV.setColumns(10);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setBounds(20, 22, 85, 13);
		pnQuanLy.add(lblMaNV);

		JLabel lblTenNV = new JLabel("Họ tên nhân viên:");
		lblTenNV.setBounds(269, 23, 103, 13);
		pnQuanLy.add(lblTenNV);

		cbTenNV = new JComboBox();
		ArrayList<NhanVien> dsNV = dao_NV.getAllNhanVien();
		for (NhanVien nv : dsNV) {
			if (nv.getChucVu().equalsIgnoreCase("Trưởng phòng nhân sự"))
				cbTenNV.addItem(nv.getTenNhanVien());
		}
		cbTenNV.setBounds(391, 19, 148, 21);
		pnQuanLy.add(cbTenNV);
		cbTenNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (NhanVien nhanVien : dsNV) {
					if (cbTenNV.getSelectedItem().toString().trim().equalsIgnoreCase(nhanVien.getTenNhanVien()))
						txtMaNV.setText(nhanVien.getMaNhanVien());
				}
			}
		});

		dateChooser = new JDateChooser();
		dateChooser.setBounds(401, 63, 148, 22);
		pnThongTin.add(dateChooser);

		btnReload = new JButton("Reload");
		btnReload.setBackground(Color.CYAN);
		btnReload.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/return.png")));
		btnReload.setBounds(668, 110, 104, 31);
		btnReload.setBorderPainted(false);
		pnThongTin.add(btnReload);

		JLabel lblTimTen = new JLabel("Tìm kiếm theo tên:");
		lblTimTen.setBounds(10, 6, 118, 19);
		pnChucNang.add(lblTimTen);

		JLabel lblTimTheoNVQL = new JLabel("Tìm kiếm theo nhân viên quản lí: ");
		lblTimTheoNVQL.setBounds(220, 5, 219, 21);
		pnChucNang.add(lblTimTheoNVQL);

		btnLuu = new JButton("Lưu");
		btnLuu.setBackground(Color.CYAN);
		btnLuu.setBorderPainted(false);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLuu.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/paste-icon.png")));
		btnLuu.setBounds(429, 12, 107, 35);
		pnChucNang.add(btnLuu);

		btnThem = new JButton("Thêm");
		btnThem.setBackground(Color.CYAN);
		btnThem.setBorderPainted(false);
		btnThem.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/add.png")));
		btnThem.setBounds(558, 12, 100, 35);
		pnChucNang.add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBackground(Color.CYAN);
		btnXoa.setBorderPainted(false);
		btnXoa.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/trash.png")));
		btnXoa.setBounds(684, 12, 100, 35);
		pnChucNang.add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.setBackground(Color.CYAN);
		btnSua.setBorderPainted(false);
		btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/settings.png")));
		btnSua.setBounds(809, 12, 100, 35);
		pnChucNang.add(btnSua);

		btnTimKiem = new JButton("");
		btnTimKiem.setSelectedIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/search-icon.png")));

		btnTimKiem.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/search-icon.png")));
		btnTimKiem.setBounds(144, 26, 23, 21);
		pnChucNang.add(btnTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(10, 26, 134, 21);
		pnChucNang.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		cbTimTheoNVQL = new JComboBox();
		cbTimTheoNVQL.setBounds(228, 26, 145, 21);
		ArrayList<NhanVien> ds = dao_NV.getAllNhanVien();
		for (NhanVien nv : dsNV) {
			if (nv.getChucVu().equals("Trưởng phòng nhân sự"))
				cbTimTheoNVQL.addItem(nv.getTenNhanVien());
		}
		pnChucNang.add(cbTimTheoNVQL);

		JPanel pnDanhSach = new JPanel();
		pnDanhSach.setBackground(Color.WHITE);
		pnDanhSach.setBorder(new LineBorder(Color.CYAN));
		pnDanhSach.setBounds(10, 268, 920, 236);
		add(pnDanhSach);
		pnDanhSach.setLayout(null);

		JScrollPane scrollPaneDSCN = new JScrollPane();
		scrollPaneDSCN.setBounds(10, 11, 900, 214);
		pnDanhSach.add(scrollPaneDSCN);

		tableCN = new JTable();
		dataModel = new DefaultTableModel(headers, 0);
		tableCN = new JTable(dataModel);

		tableCN.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableCN.getColumnModel().getColumn(1).setPreferredWidth(15);
		tableCN.getColumnModel().getColumn(2).setPreferredWidth(90);
		tableCN.getColumnModel().getColumn(3).setPreferredWidth(15);
		tableCN.getColumnModel().getColumn(4).setPreferredWidth(15);
		tableCN.getColumnModel().getColumn(5).setPreferredWidth(15);
		tableCN.getColumnModel().getColumn(6).setPreferredWidth(120);
		tableCN.getColumnModel().getColumn(7).setPreferredWidth(25);
		
		JTableHeader hd=tableCN.getTableHeader();
		hd.setBackground(Color.cyan);
		hd.setFont(new Font("Time New Roman",Font.BOLD,10));
		scrollPaneDSCN.setViewportView(tableCN);
		DocDuLieuTuDatabaseVaoTable();

		// Phân chia chức năng
		moKhoaTextfields(false);
		moKhoaControls(true);
		btnLuu.setEnabled(false);
		btnThemAvt.setEnabled(false);
		// Thực thi sự kiện
		tableCN.addMouseListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThemAvt.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnReload.addActionListener(this);
		cbTimTheoNVQL.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				xoaRong();
				moKhoaTextfields(true);
				moKhoaControls(false);
				btnThemAvt.setEnabled(true);
				btnLuu.setEnabled(true);
				btnThem.setEnabled(true);
				btnThem.setText("Hủy");
				btnThem.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/Icon_Huy.png")));
				lblAvt.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource(linkImage)));
				loadMaCN();
			
			} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				btnLuu.setEnabled(false);
				btnThemAvt.setEnabled(false);
				btnThem.setText("Thêm");
				btnThem.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/add.png")));
				xoaRong();
				//
			}
		}
		if (o.equals(btnSua)) {
			if (txtTenCN.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công nhân cần sửa");
			else if (btnSua.getText().equalsIgnoreCase("Sửa")) {
				moKhoaTextfields(true);
				txtMaCN.setEditable(false);
				txtMaNV.setEditable(false);
				moKhoaControls(false);
				btnLuu.setEnabled(true);
				btnSua.setEnabled(true);
				btnThemAvt.setEnabled(true);
				btnSua.setText("Hủy");
				btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/Icon_Huy.png")));
				int row = tableCN.getSelectedRow();
				String id = (String) dataModel.getValueAt(row, 1);
				CongNhan cn = dao_CN.getCongNhanTheoMaCN(id);
				if (cn != null) {
					dateChooser.setDate(cn.getNgaySinh());
				}
			} else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				moKhoaTextfields(false);
				moKhoaControls(true);
				btnLuu.setEnabled(false);
				btnThemAvt.setEnabled(false);
				btnSua.setText("Sửa");
				btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/settings.png")));
				xoaRong();
				//
			}
		}
		if (o.equals(btnLuu)) {
			if (btnSua.getText().equalsIgnoreCase("Hủy")) {
				if (valid()) {
					suaCN();
					moKhoaTextfields(false);
					moKhoaControls(true);
					btnThemAvt.setEnabled(false);
					btnLuu.setEnabled(false);
					btnSua.setText("Sửa");
					btnSua.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/settings.png")));
					JOptionPane.showMessageDialog(null, "Thông tin đã được cập nhật!");
					xoaRong();
				}
			}

			if (btnThem.getText().equalsIgnoreCase("Hủy")) {
				if (valid()) {
					CongNhan cn = createCongNhan();
					if (dao_CN.themCongNhan(cn)) {
						String gioiTinh = "";
						Boolean p = radNam.isSelected();
						if (p)
							gioiTinh = "Nam";
						else
							gioiTinh = "Nữ";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String ngaySinh = sdf.format(dateChooser.getDate());
						int stt = dao_CN.getAllCongNhan().size();
						Object[] rowData = { stt, txtMaCN.getText(), txtTenCN.getText(), gioiTinh, ngaySinh,
								txtCMND.getText(), txtDiaChi.getText(), cbPhuCap.getSelectedItem() };
						stt++;
						dataModel.addRow(rowData);
					}
					moKhoaTextfields(false);
					moKhoaControls(true);
					btnLuu.setEnabled(false);
					btnThemAvt.setEnabled(false);
					btnThem.setText("Thêm");
					xoaRong();
					btnThem.setIcon(new ImageIcon(PnlDanhMucCongNhan.class.getResource("/image/add.png")));
					JOptionPane.showMessageDialog(null, "Thêm thành công!");
				} else
					JOptionPane.showMessageDialog(null, "Thêm ko thành công!");
			}
		}
		if (o.equals(btnXoa))

		{
			if (txtTenCN.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công nhân cần xóa");
			else {
				xoaCongNhan();
				xoaRong();
			}
		}
		if (o.equals(btnThemAvt))
			addAvt();
		if (o.equals(btnTimKiem)) {
			ArrayList<CongNhan> ds = dao_CN.getCongNhanTheoTen(txtTimKiem.getText());
			int stt = 1;
			if (ds.size() == 0)
				JOptionPane.showMessageDialog(this, "Ko tìm thấy");
			else {
				dataModel.getDataVector().removeAllElements();
				String gioiTinh = "";
				for (CongNhan cn : ds) {
					if (!cn.isGioiTinh())
						gioiTinh = "Nam";
					else
						gioiTinh = "Nữ";
					Object[] rowData = { stt, cn.getMaCN(), cn.getTenCN(), gioiTinh, cn.getNgaySinh(), cn.getCMND(),
							cn.getDiaChi(), cn.getPhuCap() };
					dataModel.addRow(rowData);
					stt++;
				}
				tableCN.setModel(dataModel);
			}
		}
		if (o.equals(cbTimTheoNVQL)) {
			ArrayList<CongNhan> ds = dao_CN.getCongNhanTheoNVQL(cbTimTheoNVQL.getSelectedItem().toString().trim());
			int stt = 1;
			if (ds.size() == 0)
				JOptionPane.showMessageDialog(this, "Ko tìm thấy");
			else {
				dataModel.getDataVector().removeAllElements();
				String gioiTinh = "";
				for (CongNhan cn : ds) {
					if (!cn.isGioiTinh())
						gioiTinh = "Nam";
					else
						gioiTinh = "Nữ";
					Object[] rowData = { stt, cn.getMaCN(), cn.getTenCN(), gioiTinh, cn.getNgaySinh(), cn.getCMND(),
							cn.getDiaChi(), cn.getPhuCap() };
					stt++;
					dataModel.addRow(rowData);
				}
				tableCN.setModel(dataModel);
			}
		}
		if (o.equals(btnReload)) {
			dataModel.getDataVector().removeAllElements();
			DocDuLieuTuDatabaseVaoTable();

		}

	}

	public CongNhan createCongNhan() {
		String maCN = txtMaCN.getText().trim();
		String tenCN = txtTenCN.getText().trim();
		String CMND = txtCMND.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		boolean gioiTinh;
		if (radNam.isSelected())
			gioiTinh = true;
		else
			gioiTinh = false;
		double phuCap = Double.parseDouble(cbPhuCap.getSelectedItem().toString());
		Date ngaySinh = new Date();
		ngaySinh = dateChooser.getDate();
		long javaTime = ngaySinh.getTime();
		java.sql.Date sqlNgaySinh = new java.sql.Date(javaTime);

		String tenNV = cbTenNV.getSelectedItem().toString();
		String anh = linkImage;
		String maNV = "";
		for (NhanVien nv : dao_NV.getAllNhanVien()) {
			if (tenNV.equalsIgnoreCase(nv.getTenNhanVien())) {
				maNV = nv.getMaNhanVien();
			}
		}
		NhanVien nv = new NhanVien(maNV);
		CongNhan cn = new CongNhan(maCN, tenCN, gioiTinh, sqlNgaySinh, CMND, diaChi, phuCap, anh, nv);
		return cn;
	}

	private void xoaCongNhan() {
		int row = tableCN.getSelectedRow();
		String maCN = dataModel.getValueAt(row, 1).toString();
		if (JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa công nhân này ko?", "Cảnh báo!!!",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (row >= 0) {
				if (dao_CN.xoaCongNhan(maCN)) {
					dataModel.removeRow(row);
					xoaRong();
					dataModel.getDataVector().removeAllElements();
					DocDuLieuTuDatabaseVaoTable();
				}
			}
		}
	}

	public void suaCN() {
		int row = tableCN.getSelectedRow();

		if (row >= 0) {
			CongNhan cn = createCongNhan();
			if (dao_CN.capNhatThongTinNhanVien(cn)) {
				tableCN.setValueAt(txtTenCN.getText(), row, 2);
				String gioiTinh = "";
				if (radNam.isSelected())
					gioiTinh = "Nam";
				else
					gioiTinh = "Nữ";
				tableCN.setValueAt(gioiTinh, row, 3);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(dateChooser.getDate());
				tableCN.setValueAt(date, row, 4);
				tableCN.setValueAt(txtCMND.getText(), row, 5);
				tableCN.setValueAt(txtDiaChi.getText(), row, 6);
				tableCN.setValueAt(cbPhuCap.getSelectedItem(), row, 7);

			}
		}
	}

	// Ràng buộc
	private boolean valid() {
		String ten = txtTenCN.getText();
		if (ten.isEmpty() || ten.matches("[\\d]*")) {
			showMessage(txtMaNV, "Họ tên ko hợp lệ!");
			return false;
		}
		String cmnd = txtCMND.getText();
		if (cmnd.isEmpty() || !cmnd.matches("[\\d]{9}||[\\d]{12}")) {
			showMessage(txtMaNV, "CMND ko hợp lệ!");
			return false;
		}
		if (txtDiaChi.getText().isEmpty()) {
			showMessage(txtMaNV, "Địa chỉ ko được để trống!");
			return false;
		}
		if (cbTenNV.getSelectedItem().equals("")) {
			showMessage(txtMaNV, "Bạn cần phải chọn nhân viên quản lí!");
			return false;
		}

		LocalDate localDate = LocalDate.now();
		LocalDate ngayNhap = new java.sql.Date(dateChooser.getDate().getTime()).toLocalDate();
		if (Period.between(ngayNhap, localDate).getYears() < 18) {
			JOptionPane.showMessageDialog(null, "Chưa đủ tuổi!");
			return false;
		}
		return true;
	}

	private void showMessage(JTextField tf, String mes) {
		JOptionPane.showMessageDialog(tf, mes);
		tf.selectAll();
		tf.requestFocus();
	}

	public void DocDuLieuTuDatabaseVaoTable() {
		String gioiTinh = "";
		int stt = 1;
		for (CongNhan cn : dao_CN.getAllCongNhan()) {
			if (!cn.isGioiTinh())
				gioiTinh = "Nam";
			else
				gioiTinh = "Nữ";
			Object[] rowData = { stt, cn.getMaCN(), cn.getTenCN(), gioiTinh, cn.getNgaySinh(), cn.getCMND(),
					cn.getDiaChi(), cn.getPhuCap() };
			dataModel.addRow(rowData);
			stt++;
		}
	}

	public void xoaRong() {
		txtMaCN.setText("");
		txtTenCN.setText("");
		txtCMND.setText("");
		txtDiaChi.setText("");
		dateChooser.setDate(null);
		cbPhuCap.setSelectedIndex(0);
		radNam.setSelected(true);
		txtMaNV.setText("");
		cbTenNV.setSelectedIndex(0);
		txtMaCN.requestFocus();
	}

	private void moKhoaControls(boolean b) {
		btnThemAvt.setEnabled(b);
		btnThem.setEnabled(b);
		btnLuu.setEnabled(b);
		btnSua.setEnabled(b);
		btnXoa.setEnabled(b);

	}

	private void moKhoaTextfields(boolean b) {
		txtMaCN.setEditable(b);
		txtTenCN.setEditable(b);
		txtCMND.setEditable(b);
		txtDiaChi.setEditable(b);
		dateChooser.setEnabled(b);
		;
		radNam.setEnabled(b);
		radNu.setEnabled(b);
		cbPhuCap.setEnabled(b);
		cbTenNV.setEnabled(b);
		txtMaNV.setEditable(b);
	}

	private void loadMaCN() {
		String maCN = null;
		maCN = dao_CN.getAutoMaCN();
		txtMaCN.setText(maCN);
	}

	public ImageIcon ResizeImage(String linkImage) {
		ImageIcon myImage = new ImageIcon(linkImage);
		Image img = myImage.getImage();
		Image newImage = img.getScaledInstance(lblAvt.getWidth(), lblAvt.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImage);
		return image;
	}

	private void addAvt() {
		try {
			JFileChooser f = new JFileChooser("T:\\Anh");
			f.setDialogTitle("Open File");
			f.showOpenDialog(null);
			File fNameImage = f.getSelectedFile();
			linkImage = fNameImage.getAbsolutePath();
			if (linkImage != null) {
				lblAvt.setIcon(ResizeImage(String.valueOf(linkImage)));
				System.out.println(linkImage);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Chưa có ảnh được chọn!");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableCN.getSelectedRow();
		if (row >= 0) {
			String id = (String) dataModel.getValueAt(row, 1);
			CongNhan cn = dao_CN.getCongNhanTheoMaCN(id);
			if (cn != null) {
				txtMaCN.setText(cn.getMaCN());
				txtMaNV.setText(cn.getNhanVien().getMaNhanVien());
				String maNV = cn.getNhanVien().getMaNhanVien();
				NhanVien nv = dao_NV.getNhanVienTheoMaNV(maNV);
				cbTenNV.setSelectedItem(nv.getTenNhanVien());
				lblAvt.setIcon(new ImageIcon(cn.getAnh()));
				dateChooser.setDate(cn.getNgaySinh());
				linkImage = cn.getAnh();
				try {
					if (cn.getAnh() != null)
						lblAvt.setIcon(ResizeImage(String.valueOf(linkImage)));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			txtTenCN.setText(dataModel.getValueAt(row, 2).toString());
			radNam.setSelected(dataModel.getValueAt(row, 3).toString() == "Nam" ? true : false);
			radNu.setSelected(dataModel.getValueAt(row, 3).toString() == "Nữ" ? true : false);
			txtCMND.setText(dataModel.getValueAt(row, 5).toString());
			txtDiaChi.setText(dataModel.getValueAt(row, 6).toString());
			cbPhuCap.setSelectedItem(dataModel.getValueAt(row, 7).toString());
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
}
