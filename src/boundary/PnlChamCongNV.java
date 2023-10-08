 package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultCellEditor;
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

import dao.DAO_ChamCongNV;
import dao.DAO_NhanVien;
import dao.DAO_TienLuongNV;
import entity.ChamCongNV;
import entity.NhanVien;
import entity.TienLuongNV;

public class PnlChamCongNV extends JPanel implements ActionListener, MouseListener {
	private JTable tableDSCCNV;
	private JTextField txtTimTheoTen;
	private DAO_ChamCongNV dao_ccnv;
	private DAO_NhanVien dao_nv;
	private DAO_TienLuongNV dao_tl;
	private Object[] headers = { "STT", "Mã nhân viên", "Họ tên", "Vắng", "Trạng thái", "Không phép",
			"Số giờ tăng ca", "Ghi chú" };
	private DefaultTableModel model;
	private JDateChooser dateChooser;
	private JComboBox comboBoxTimKiem;
	private JComboBox comSoGioTangCa;
	private JComboBox comTrangThai;
	private JButton btnTimTheoTen;
	private JButton btnChamCong;
	private JButton btnTimTheoNgay;
	private JComboBox comboBoxTimKho;

	/**
	 * Create the panel.
	 */
	public PnlChamCongNV() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		dao_nv = new DAO_NhanVien();
		dao_ccnv = new DAO_ChamCongNV();
		dao_tl = new DAO_TienLuongNV();

		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(Color.CYAN);
		pnTitle.setBounds(10, 11, 915, 36);
		add(pnTitle);
		pnTitle.setLayout(null);

		JLabel lblTitle = new JLabel("Ch\u1EA5m c\u00F4ng nh\u00E2n vi\u00EAn");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblTitle.setBounds(10, 0, 256, 36);
		pnTitle.add(lblTitle);

		JLabel lblNgayChamCong = new JLabel("Ng\u00E0y ch\u1EA5m c\u00F4ng: ");
		lblNgayChamCong.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNgayChamCong.setBounds(10, 65, 119, 22);
		add(lblNgayChamCong);

		dateChooser = new JDateChooser(new Date());
		dateChooser.setBounds(104, 67, 129, 20);
		add(dateChooser);

		comboBoxTimKiem = new JComboBox();
		comboBoxTimKiem.setModel(new DefaultComboBoxModel(new String[] { "Tất cả","Kế toán", "Nhân sự", "Thiết kế" }));
		comboBoxTimKiem.setBounds(825, 65, 100, 22);
		add(comboBoxTimKiem);


		JPanel pnlDSCCNV = new JPanel();
		pnlDSCCNV.setBorder(new LineBorder(Color.CYAN));
		pnlDSCCNV.setBounds(10, 98, 915, 397);
		add(pnlDSCCNV);
		pnlDSCCNV.setLayout(null);

		JLabel lblDSCCNV = new JLabel("Danh s\u00E1ch ch\u1EA5m c\u00F4ng nh\u00E2n vi\u00EAn");
		lblDSCCNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSCCNV.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblDSCCNV.setBounds(0, 0, 925, 34);
		pnlDSCCNV.add(lblDSCCNV);

		JScrollPane scrollPaneDSCCNV = new JScrollPane();
		scrollPaneDSCCNV.setBounds(10, 33, 895, 319);
		pnlDSCCNV.add(scrollPaneDSCCNV);

		tableDSCCNV = new JTable();
		model = new DefaultTableModel(new Object[][] {}, headers) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class, Boolean.class, String.class,
					Boolean.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableDSCCNV.setModel(model);
		// Thêm JcomboBox trạng thái vào table
		comTrangThai = new JComboBox();
		comTrangThai.addItem("Nguyên ngày");
		comTrangThai.addItem("Nửa buổi");
		comTrangThai.setSelectedIndex(0);
		DefaultCellEditor deTrangThai = new DefaultCellEditor(comTrangThai);
		
		// Thêm JcomboBox số giờ tăng ca vào table
		comSoGioTangCa = new JComboBox();
		comSoGioTangCa.addItem("0");
		comSoGioTangCa.addItem("1");
		comSoGioTangCa.addItem("2");
		comSoGioTangCa.addItem("3");
		comSoGioTangCa.addItem("4");
		comSoGioTangCa.setSelectedIndex(0);
		DefaultCellEditor deSoGioTangCa = new DefaultCellEditor(comSoGioTangCa);

		tableDSCCNV.getColumnModel().getColumn(0).setPreferredWidth(27);
		tableDSCCNV.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableDSCCNV.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableDSCCNV.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableDSCCNV.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableDSCCNV.getColumnModel().getColumn(4).setCellEditor(deTrangThai);
		tableDSCCNV.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableDSCCNV.getColumnModel().getColumn(6).setCellEditor(deSoGioTangCa);
		tableDSCCNV.getColumnModel().getColumn(6).setPreferredWidth(70);
		tableDSCCNV.getColumnModel().getColumn(7).setPreferredWidth(100);
		
		JTableHeader hd=tableDSCCNV.getTableHeader();
		hd.setBackground(Color.cyan);
		hd.setFont(new Font("Time New Roman", Font.BOLD, 12));
		scrollPaneDSCCNV.setViewportView(tableDSCCNV);

		btnChamCong = new JButton("Chấm Công");
		btnChamCong.setFont(btnChamCong.getFont().deriveFont(btnChamCong.getFont().getStyle() | Font.BOLD | Font.ITALIC,
				btnChamCong.getFont().getSize() + 5f));
		btnChamCong.setBackground(Color.CYAN);
		btnChamCong.setBounds(760, 355, 132, 37);
		btnChamCong.setBorderPainted(false);
		pnlDSCCNV.add(btnChamCong);

		txtTimTheoTen = new JTextField();
		txtTimTheoTen.setToolTipText("\r\n");
		txtTimTheoTen.setBounds(352, 67, 119, 20);
		add(txtTimTheoTen);
		txtTimTheoTen.setColumns(10);

		JLabel lblTimTheoTen = new JLabel("Tìm theo tên:");
		lblTimTheoTen.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTimTheoTen.setBounds(280, 68, 96, 17);
		add(lblTimTheoTen);

		JLabel lblTimTheoPB = new JLabel("Tìm theo phòng ban:");
		lblTimTheoPB.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTimTheoPB.setBounds(717, 68, 136, 17);
		add(lblTimTheoPB);

		btnTimTheoTen = new JButton("");
		btnTimTheoTen.setBackground(Color.WHITE);
		btnTimTheoTen.setSelectedIcon(new ImageIcon(PnlChamCongNV.class.getResource("/image/search-icon.png")));
		btnTimTheoTen.setIcon(new ImageIcon(PnlChamCongNV.class.getResource("/image/search-icon.png")));
		btnTimTheoTen.setBounds(471, 65, 24, 23);
		btnTimTheoTen.setBorder(new LineBorder(Color.DARK_GRAY));
		add(btnTimTheoTen);

		btnTimTheoNgay = new JButton("");
		btnTimTheoNgay.setIcon(new ImageIcon(PnlChamCongNV.class.getResource("/image/search-icon.png")));
		btnTimTheoNgay.setBackground(Color.WHITE);
		btnTimTheoNgay.setBounds(233, 66, 24, 22);
		btnTimTheoNgay.setBorder(new LineBorder(Color.DARK_GRAY));
		add(btnTimTheoNgay);
		
		JLabel lblTimKho = new JLabel("Tìm theo kho:");
		lblTimKho.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTimKho.setBounds(520, 68, 136, 17);
		add(lblTimKho);
		
		comboBoxTimKho = new JComboBox();
		comboBoxTimKho.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Gò Vấp", "Bình Thạnh"}));
		comboBoxTimKho.setBounds(594, 65, 100, 22);
		add(comboBoxTimKho);

		doDuLieuNhanVienTuCollection();

		btnChamCong.addActionListener(this);
		tableDSCCNV.addMouseListener(this);
		btnTimTheoTen.addActionListener(this);
		btnTimTheoNgay.addActionListener(this);
		comboBoxTimKiem.addActionListener(this);
		comboBoxTimKho.addActionListener(this);
		

	}

	private void doDuLieuNhanVienTuCollection() {
		int stt = 1;
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateChooser.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<ChamCongNV> dsCC = dao_ccnv.timKiemDanhSachChamCongNVTheoNgay(sqlDate);
		if (dsCC.isEmpty()) {
			for (NhanVien dto_nv : dao_nv.getAllNhanVien()) {
				model.addRow(new Object[] { stt, dto_nv.getMaNhanVien(), dto_nv.getTenNhanVien(),null,"Nguyên ngày",null,"0",null });
				stt++;
			}
			tableDSCCNV.setModel(model);
		} else {
			for (ChamCongNV dto_ccnv : dsCC) {
				if (dto_ccnv.isCoMat()) {
					Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
							dto_ccnv.getNhanVien().getTenNhanVien(), false,
							dto_ccnv.isTrangThai() == true ? "Nguyên ngày" : "Nửa buổi", false,
							dto_ccnv.getSoGioTangCa(), dto_ccnv.getGhiChu() };
					model.addRow(rowData);
				} else {
					Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
							dto_ccnv.getNhanVien().getTenNhanVien(), true, null, dto_ccnv.isPhep()==true?false:true, null,
							dto_ccnv.getGhiChu() };
					model.addRow(rowData);
				}
				stt++;
			}
			tableDSCCNV.setModel(model);
			tableDSCCNV.setEnabled(false);
			btnChamCong.setEnabled(false);
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnChamCong)) {
			if (luuDanhSachChamCong()) {
				JOptionPane.showMessageDialog(null, "Chấm công thành công");	
				int ngay=dateChooser.getDate().getDate();
				if(ngay==1)
					themLuongThangMoi();
				else
					capNhatLuongTrongThang();
			}
			;
		} else if (o.equals(btnTimTheoTen)) {
			timKiemTheoTen();
		} else if (o.equals(comboBoxTimKiem)) {
			if(comboBoxTimKiem.getSelectedItem().toString().equals("Tất cả")) {
				timKiemTheoNgay();
			}
			else
				timKiemTheoPB();
		}else if (o.equals(comboBoxTimKho)) {
			if(comboBoxTimKho.getSelectedItem().toString().equals("Tất cả")) {
				timKiemTheoNgay();
			}
			else
				timKiemTheoKho();
		} else if (o.equals(btnTimTheoNgay)) {
			timKiemTheoNgay();
		}

	}
	

	

	private void timKiemTheoKho() {
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateChooser.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<ChamCongNV> dsCC = dao_ccnv.timKiemDanhSachChamCongNVTheoKho(sqlDate,
				comboBoxTimKho.getSelectedItem().toString());
		if(sqlDate.toLocalDate().compareTo(LocalDate.now())!=0) {
			if(dsCC.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
			}else {
				xoaHetDuLieuTrenModel();
				int stt = 1;
				for (ChamCongNV dto_ccnv : dsCC) {
					if (dto_ccnv.isCoMat()) {
						Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
								dto_ccnv.getNhanVien().getTenNhanVien(), false,
								dto_ccnv.isTrangThai() == true ? "Nguyên ngày" : "Nửa buổi", false,
								dto_ccnv.getSoGioTangCa(), dto_ccnv.getGhiChu() };
						model.addRow(rowData);
					} else {
						Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
								dto_ccnv.getNhanVien().getTenNhanVien(), true, null, dto_ccnv.isPhep()==true?false:true, null,
								dto_ccnv.getGhiChu() };
						model.addRow(rowData);
					}
					stt++;
					tableDSCCNV.setModel(model);
				}
				btnChamCong.setEnabled(false);
				tableDSCCNV.setEnabled(false);
			}
		}else {
			if (dsCC.isEmpty()) {
				ArrayList<NhanVien> dsNV=dao_nv.getNhanVienTheoKho(comboBoxTimKho.getSelectedItem().toString());
				if(dsNV.isEmpty())
					JOptionPane.showMessageDialog(null, "Không tìm thấy");
				else {
					xoaHetDuLieuTrenModel();
					int stt=1;
					for(NhanVien dto_nv:dsNV)  {
						model.addRow(new Object[] { stt, dto_nv.getMaNhanVien(), dto_nv.getTenNhanVien(),null,"Nguyên ngày",null,"0",null });
						stt++;
					}
					tableDSCCNV.setModel(model);
					btnChamCong.setEnabled(true);
					tableDSCCNV.setEnabled(true);
				}
			} else {
				xoaHetDuLieuTrenModel();
				int stt = 1;
				for (ChamCongNV dto_ccnv : dsCC) {
					if (dto_ccnv.isCoMat()) {
						Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
								dto_ccnv.getNhanVien().getTenNhanVien(), false,
								dto_ccnv.isTrangThai() == true ? "Nguyên ngày" : "Nửa buổi", false,
								dto_ccnv.getSoGioTangCa(), dto_ccnv.getGhiChu() };
						model.addRow(rowData);
					} else {
						Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
								dto_ccnv.getNhanVien().getTenNhanVien(),true, null, dto_ccnv.isPhep()==true?false:true, null,
								dto_ccnv.getGhiChu() };
						model.addRow(rowData);
					}
					stt++;
					tableDSCCNV.setModel(model);
				}
				btnChamCong.setEnabled(false);
				tableDSCCNV.setEnabled(false);
			}
		}
		
	}

	private void timKiemTheoPB() {
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateChooser.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<ChamCongNV> dsCC = dao_ccnv.timKiemDanhSachChamCongNVTheoPB(sqlDate,
				comboBoxTimKiem.getSelectedItem().toString());
		if(sqlDate.toLocalDate().compareTo(LocalDate.now())!=0) {
			if(dsCC.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
			}else {
				xoaHetDuLieuTrenModel();
				int stt = 1;
				for (ChamCongNV dto_ccnv : dsCC) {
					if (dto_ccnv.isCoMat()) {
						Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
								dto_ccnv.getNhanVien().getTenNhanVien(), false,
								dto_ccnv.isTrangThai() == true ? "Nguyên ngày" : "Nửa buổi", false,
								dto_ccnv.getSoGioTangCa(), dto_ccnv.getGhiChu() };
						model.addRow(rowData);
					} else {
						Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
								dto_ccnv.getNhanVien().getTenNhanVien(), true, null, dto_ccnv.isPhep()==true?false:true, null,
								dto_ccnv.getGhiChu() };
						model.addRow(rowData);
					}
					stt++;
					tableDSCCNV.setModel(model);
				}
				btnChamCong.setEnabled(false);
				tableDSCCNV.setEnabled(false);
			}
		}else {
			if (dsCC.isEmpty()) {
				ArrayList<NhanVien> dsNV=dao_nv.getNhanVienTheoPhongBan(comboBoxTimKiem.getSelectedItem().toString());
				if(dsNV.isEmpty())
					JOptionPane.showMessageDialog(null, "Không tìm thấy");
				else {
					xoaHetDuLieuTrenModel();
					int stt=1;
					for(NhanVien dto_nv:dsNV)  {
						model.addRow(new Object[] { stt, dto_nv.getMaNhanVien(), dto_nv.getTenNhanVien(),null,"Nguyên ngày",null,"0",null });
						stt++;
					}
					tableDSCCNV.setModel(model);
					btnChamCong.setEnabled(true);
					tableDSCCNV.setEnabled(true);
				}
			} else {
				xoaHetDuLieuTrenModel();
				int stt = 1;
				for (ChamCongNV dto_ccnv : dsCC) {
					if (dto_ccnv.isCoMat()) {
						Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
								dto_ccnv.getNhanVien().getTenNhanVien(), false,
								dto_ccnv.isTrangThai() == true ? "Nguyên ngày" : "Nửa buổi", false,
								dto_ccnv.getSoGioTangCa(), dto_ccnv.getGhiChu() };
						model.addRow(rowData);
					} else {
						Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
								dto_ccnv.getNhanVien().getTenNhanVien(),true, null, dto_ccnv.isPhep()==true?false:true, null,
								dto_ccnv.getGhiChu() };
						model.addRow(rowData);
					}
					stt++;
					tableDSCCNV.setModel(model);
				}
				btnChamCong.setEnabled(false);
				tableDSCCNV.setEnabled(false);
			}
		}
	}

	private void xoaHetDuLieuTrenModel() {
		DefaultTableModel dm = (DefaultTableModel) tableDSCCNV.getModel();
		dm.getDataVector().removeAllElements();
	}

	private void timKiemTheoTen() {
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateChooser.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<ChamCongNV> dsCC = dao_ccnv.timKiemDanhSachChamCongNVTheoTen(txtTimTheoTen.getText(), sqlDate);
		if (dsCC.isEmpty())
			JOptionPane.showMessageDialog(null, "Không tìm thấy");
		else {
			xoaHetDuLieuTrenModel();
			int stt = 1;
			for (ChamCongNV dto_ccnv : dsCC) {
				if (dto_ccnv.isCoMat()) {
					Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
							dto_ccnv.getNhanVien().getTenNhanVien(), false,
							dto_ccnv.isTrangThai() == true ? "Nguyên ngày" : "Nửa buổi", false,
							dto_ccnv.getSoGioTangCa(), dto_ccnv.getGhiChu() };
					model.addRow(rowData);
				} else {
					Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
							dto_ccnv.getNhanVien().getTenNhanVien(), true, null, dto_ccnv.isPhep()==true?false:true, null,
							dto_ccnv.getGhiChu() };
					model.addRow(rowData);
				}
				stt++;
			}
			tableDSCCNV.setModel(model);
		}

	}


	private boolean luuDanhSachChamCong() {
		int soLuong = model.getRowCount();
		for (int i = 0; i < soLuong; i++) {
			String ghiChu;
			int soGioTangCa = 0;
			boolean kPhep = false, trangthai = true, vang = true;
			String maNV = model.getValueAt(i, 1).toString();
			String tenNV = model.getValueAt(i, 2).toString();
			String maCC = dao_ccnv.getMaCCNV();
			java.util.Date ngayCC = new java.util.Date();
			ngayCC = dateChooser.getDate();
			long javaTime = ngayCC.getTime();
			java.sql.Date sqlDate = new java.sql.Date(javaTime);
			if (model.getValueAt(i, 3) == null) {
				vang = false;
			}
			if(model.getValueAt(i, 4).equals("Nửa buổi"))
				trangthai=false;
			if (model.getValueAt(i, 7) == null) {
				ghiChu = "";
			} else
				ghiChu = model.getValueAt(i, 7).toString();
			if (model.getValueAt(i, 5) != null)
				kPhep = true;
			if (model.getValueAt(i, 6) != null)
				soGioTangCa = Integer.parseInt((String) model.getValueAt(i, 6));
			NhanVien nv = new NhanVien(maNV);
			if (!vang) {
				ChamCongNV ccnv = new ChamCongNV(maCC, sqlDate, soGioTangCa, vang=true, trangthai, ghiChu, nv);
				dao_ccnv.themChamCongNVCoMat(ccnv);
			} else {
				ChamCongNV ccnv = new ChamCongNV(maCC, sqlDate, kPhep==true?false:true, vang=false, ghiChu, nv);
				dao_ccnv.themChamCongNVVang(ccnv);
			}

		}
		tableDSCCNV.setEnabled(false);
		btnChamCong.setEnabled(false);
		return true;
	}

	private void timKiemTheoNgay() {
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateChooser.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(sqlDate);
		ArrayList<ChamCongNV> dsCC = dao_ccnv.timKiemDanhSachChamCongNVTheoNgay(sqlDate);
		if (dsCC.isEmpty()) {
			java.util.Date ngayHT = new java.util.Date();
			dateChooser.setDate(new Date());
			if (ngayCC.compareTo(ngayHT) > 0) {
				model.getDataVector().removeAllElements();
				doDuLieuNhanVienTuCollection();
				tableDSCCNV.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Ngày " + strDate + " chưa được chấm công!");
			}else if(sqlDate.toLocalDate().compareTo(LocalDate.now())==0){
				xoaHetDuLieuTrenModel();
				doDuLieuNhanVienTuCollection();
				tableDSCCNV.setEnabled(true);
				btnChamCong.setEnabled(true);
			}else
				JOptionPane.showMessageDialog(null, "Không tìm thấy");
		}
		else {
			xoaHetDuLieuTrenModel();
			int stt = 1;
			for (ChamCongNV dto_ccnv : dsCC) {
				if (dto_ccnv.isCoMat()) {
					Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
							dto_ccnv.getNhanVien().getTenNhanVien(), false,
							dto_ccnv.isTrangThai() == true ? "Nguyên ngày" : "Nửa buổi", false,
							dto_ccnv.getSoGioTangCa(), dto_ccnv.getGhiChu() };
					model.addRow(rowData);
				} else {
					Object[] rowData = { stt, dto_ccnv.getNhanVien().getMaNhanVien(),
							dto_ccnv.getNhanVien().getTenNhanVien(), true, null, dto_ccnv.isPhep()==true?false:true, null,
							dto_ccnv.getGhiChu() };
					model.addRow(rowData);
				}
				stt++;
			}
			tableDSCCNV.setModel(model);
			tableDSCCNV.setEnabled(false);
			btnChamCong.setEnabled(false);
		}
	}
	
	private void themLuongThangMoi() {
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateChooser.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<ChamCongNV> dsCC=new DAO_ChamCongNV().timKiemDanhSachChamCongNVTheoNgay(sqlDate);
		for(ChamCongNV ccnv: dsCC) {
			NhanVien nv=new DAO_NhanVien().timNV(ccnv.getNhanVien().getMaNhanVien());
			double tienLuong=tinhTienLuongNhanVien(nv);
			double tienThuong=tinhTienThuongNhanVien(nv);
			double tienPhat=tinhTienPhatNhanVien(nv);
			double tongLuong=tienLuong+tienThuong-tienPhat;
			TienLuongNV tl=new TienLuongNV(dao_tl.getAutoMaTienLuong(), sqlDate, tienLuong, tienThuong, tienPhat, tongLuong, nv);
			dao_tl.themTienLuongNV(tl);
		}
	}
	
	private void capNhatLuongTrongThang() {
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateChooser.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
		String thang = monthFormatter.format(Month.of(dateChooser.getDate().getMonth() + 1));
		ArrayList<ChamCongNV> dsCC=new DAO_ChamCongNV().timKiemDanhSachChamCongNVTheoNgay(sqlDate);
		for(ChamCongNV ccnv: dsCC) {
			NhanVien nv=new DAO_NhanVien().timNV(ccnv.getNhanVien().getMaNhanVien());
			double tienLuong=tinhTienLuongNhanVien(nv);
			double tienThuong=tinhTienThuongNhanVien(nv);
			double tienPhat=tinhTienPhatNhanVien(nv);
			double tongLuong=tienLuong+tienThuong-tienPhat;
			ArrayList<TienLuongNV>dstl=new DAO_TienLuongNV().getDSNV(ccnv.getNhanVien().getMaNhanVien(),thang);
			if(!dstl.isEmpty()) {
				dao_tl.capNhatTienLuong(sqlDate,thang, tienLuong, tienThuong, tienPhat, tongLuong, ccnv.getNhanVien().getMaNhanVien());	
			}else {
				TienLuongNV tl=new TienLuongNV(dao_tl.getAutoMaTienLuong(), sqlDate, tienLuong, tienThuong, tienPhat, tongLuong, nv);
				dao_tl.themTienLuongNV(tl);
			}	
		}
	}

	private double tinhTienLuongNhanVien(NhanVien nv) {
		double luongCoBan = 6000000;
		double heSoLuong = nv.getHeSoLuong();
		double phuCap = nv.getPhuCap() * 1000;
		double tienLuong;
		double soNgayThucTe = 0;
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
		String thang = monthFormatter.format(Month.of(dateChooser.getDate().getMonth() + 1));
		ArrayList<ChamCongNV> dsCCNV = dao_ccnv.timKiemDanhSachChamCongNVTheoThang(nv.getMaNhanVien(), thang);
		for (ChamCongNV dto_ccnv : dsCCNV) {
			if (dto_ccnv.isCoMat()) {
				if (dto_ccnv.isTrangThai())
					soNgayThucTe++;
				else
					soNgayThucTe += 0.5;
			}
		}
		tienLuong = (((luongCoBan * heSoLuong) + phuCap) / 26) * soNgayThucTe;
		return tienLuong;
	
	}
	
	private double tinhTienThuongNhanVien(NhanVien nv) {
		double tienThuong = 0;
		int soGioTangCa = 0;
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
		String thang = monthFormatter.format(Month.of(dateChooser.getDate().getMonth() + 1));
		ArrayList<ChamCongNV> dsCCNV = dao_ccnv.timKiemDanhSachChamCongNVTheoThang(nv.getMaNhanVien(), thang);
		for (ChamCongNV dto_ccnv : dsCCNV) {
			if (dto_ccnv.isCoMat()) {
				if(dto_ccnv.getNgayCN().getDay()==0) //thưởng ngày chủ nhật +100k
					tienThuong+=100000;
				soGioTangCa = dto_ccnv.getSoGioTangCa();  //thưởng theo số giờ tăng ca
				tienThuong += soGioTangCa * 40000;
			}
		}
		return tienThuong;
	}
	
	private double tinhTienPhatNhanVien(NhanVien nv) {
		double luongCoBan = 5000000;
		double heSoLuong = nv.getHeSoLuong();
		double phuCap = nv.getPhuCap() * 1000;
		int coPhep = 0, khongPhep = 0;
		double tienPhat = 0;
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
		String thang = monthFormatter.format(Month.of(dateChooser.getDate().getMonth() + 1));
		ArrayList<ChamCongNV> dsCCNV = dao_ccnv.timKiemDanhSachChamCongNVTheoThang(nv.getMaNhanVien(), thang);
		for (ChamCongNV dto_ccnv : dsCCNV) {
			if(!dto_ccnv.isCoMat()) {
				if (dto_ccnv.isPhep())
					coPhep++;
				else
					khongPhep++;
			}
		}
		if (coPhep > 4)		//tiền phạt nghỉ có phép hơn 4 ngày
			tienPhat = (((luongCoBan * heSoLuong) + phuCap) / 26) * coPhep
					+ (((luongCoBan * heSoLuong) + phuCap) / 26) * 3 * khongPhep;
		else		//tiền phạt nghỉ không phép
			tienPhat = (((luongCoBan * heSoLuong) + phuCap) / 26) * 3 * khongPhep;
		return tienPhat;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

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