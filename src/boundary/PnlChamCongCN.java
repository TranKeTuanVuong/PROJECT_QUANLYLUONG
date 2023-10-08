package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.DAO_ChamCongCN;
import dao.DAO_ChiTietPC;
import dao.DAO_CongNhan;
import dao.DAO_PhanCong;
import dao.DAO_SanPham;
import dao.DAO_TienLuongCN;
import entity.ChamCongCN;
import entity.ChiTietPhanCong;
import entity.CongNhan;
import entity.SanPham;
import entity.TienLuongCN;

public class PnlChamCongCN extends JPanel implements ActionListener, MouseListener {
	private JTable tableDSCC;
	private JTextField txtTimTenCN;
	private JDateChooser dateNgayChamCong;
	private JButton btnTimTheoNgay, btnTimTheoTen, btnChamCong;
	private JComboBox cboSanPham, cboCongDoan;
	private String congDoan;
	private Double donGia;
	private double tienLuong;
	private double tienPhat;
	private double tienThuong;
	private double tongTienLuong = 0;
	Calendar cal = Calendar.getInstance();
	private String thang = String.valueOf(cal.get(Calendar.MONTH) + 1);
	private String nam = String.valueOf(cal.get(Calendar.YEAR));
	private Date thangLuong = new Date();
	private long javaTime = thangLuong.getTime();
	private java.sql.Date sqlThangLuong = new java.sql.Date(javaTime);
	private java.sql.Date sqlDate = new java.sql.Date(javaTime);

	String[] headersDSCC = { "STT", "Mã CN", "Tên công nhân", "Tên sản phẩm", "Công đoạn", "Số sản phẩm PC",
			"Số SP hoàn thành", "Trạng thái" };
	private DefaultTableModel dataModelDSCC;

	private DAO_ChamCongCN dao_CCCN;
	private DAO_CongNhan dao_CN;
	private DAO_SanPham dao_SP;
	private DAO_PhanCong dao_CCD;
	private DAO_ChiTietPC dao_CTPC;
	private DAO_TienLuongCN dao_TLCN;

	/**
	 * Create the panel.
	 */
	public PnlChamCongCN() {
		dao_CN = new DAO_CongNhan();
		dao_CCCN = new DAO_ChamCongCN();
		dao_SP = new DAO_SanPham();
		dao_CCD = new DAO_PhanCong();
		dao_CTPC = new DAO_ChiTietPC();
		dao_TLCN = new DAO_TienLuongCN();

		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setBackground(Color.CYAN);
		pnTitle.setForeground(Color.WHITE);
		pnTitle.setBounds(10, 11, 908, 35);
		add(pnTitle);
		pnTitle.setLayout(null);

		JLabel lblTitle = new JLabel("Ch\u1EA5m c\u00F4ng c\u00F4ng nh\u00E2n");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblTitle.setBounds(10, 0, 239, 35);
		pnTitle.add(lblTitle);

		dateNgayChamCong = new JDateChooser(new Date());
		dateNgayChamCong.setBounds(37, 73, 118, 20);
		add(dateNgayChamCong);

		JLabel lblNgayCC = new JLabel("Ng\u00E0y ch\u1EA5m c\u00F4ng:");
		lblNgayCC.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNgayCC.setBounds(10, 47, 130, 30);
		add(lblNgayCC);

		JPanel pnDSCC = new JPanel();
		pnDSCC.setBorder(new LineBorder(Color.CYAN));
		pnDSCC.setBounds(10, 104, 908, 383);
		add(pnDSCC);
		pnDSCC.setLayout(null);

		JLabel lblDSCN = new JLabel("Danh s\u00E1ch ch\u1EA5m c\u00F4ng c\u00F4ng nh\u00E2n");
		lblDSCN.setBounds(0, 0, 908, 33);
		lblDSCN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		pnDSCC.add(lblDSCN);
		lblDSCN.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPaneDSCC = new JScrollPane();
		scrollPaneDSCC.setBounds(10, 44, 888, 305);
		pnDSCC.add(scrollPaneDSCC);

		tableDSCC = new JTable();
		dataModelDSCC = new DefaultTableModel(new Object[][] {}, headersDSCC) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					Object.class, Object.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		JTableHeader hd = tableDSCC.getTableHeader();
		hd.setBackground(Color.cyan);
		hd.setFont(new Font("Time New Roman", Font.BOLD, 12));
		scrollPaneDSCC.setViewportView(tableDSCC);

		btnChamCong = new JButton("Chấm công");
		btnChamCong.setBackground(Color.CYAN);
		btnChamCong.setBounds(760, 353, 140, 23);
		pnDSCC.add(btnChamCong);

		JLabel lblSanPham = new JLabel("S\u1EA3n ph\u1EA9m: ");
		lblSanPham.setBounds(245, 52, 73, 20);
		add(lblSanPham);

		cboSanPham = new JComboBox();
		// Lấy dữ liệu ở chia công đoạn đưa vào
//		for(PhanCong pc : dao_PC.getAllPhanCong()) {
//			cboSanPham.addItem(pc.getSanPham().getTenSP());
//		}
		for (SanPham sp : dao_SP.getAllSanPham()) {
			cboSanPham.addItem(sp.getTenSP());
		}

		cboSanPham.setBounds(267, 71, 104, 22);
		add(cboSanPham);

		cboCongDoan = new JComboBox();
		cboCongDoan.setModel(new DefaultComboBoxModel(
				new String[] { "Rập máy", "May vắt sổ", "May móc xích kép", "Là ủi", "Đóng gói" }));
		cboCongDoan.setBounds(478, 73, 136, 22);
		add(cboCongDoan);

		JLabel lblTimTheoCongDoan = new JLabel("Tìm theo công đoạn: ");
		lblTimTheoCongDoan.setBounds(445, 52, 130, 20);
		add(lblTimTheoCongDoan);

		txtTimTenCN = new JTextField();
		txtTimTenCN.setBounds(768, 73, 124, 20);
		add(txtTimTenCN);
		txtTimTenCN.setColumns(10);

		JLabel lblTimTheoTen = new JLabel("Tìm theo tên:");
		lblTimTheoTen.setBounds(728, 50, 146, 24);
		add(lblTimTheoTen);

		btnTimTheoTen = new JButton("");
		btnTimTheoTen.setSelectedIcon(new ImageIcon(PnlChamCongCN.class.getResource("/image/search-icon.png")));
		btnTimTheoTen.setIcon(new ImageIcon(PnlChamCongCN.class.getResource("/image/search-icon.png")));
		btnTimTheoTen.setBackground(Color.WHITE);
		btnTimTheoTen.setBounds(894, 73, 24, 20);
		add(btnTimTheoTen);

		btnTimTheoNgay = new JButton("");
		btnTimTheoNgay.setIcon(new ImageIcon(PnlChamCongCN.class.getResource("/image/search-icon.png")));
		btnTimTheoNgay.setBounds(155, 73, 24, 20);
		add(btnTimTheoNgay);

		btnChamCong.addActionListener(this);
		btnTimTheoNgay.addActionListener(this);
		btnTimTheoTen.addActionListener(this);
		cboSanPham.addActionListener(this);
		cboCongDoan.addActionListener(this);

		DocDuLieuTuDatabaseVaoTableCCCN();
	}

	public void DocDuLieuTuDatabaseVaoTableCCCN() {
		int stt = 1;
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateNgayChamCong.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		ArrayList<ChamCongCN> dsCC = dao_CCCN.timKiemDanhSachChamCongCNTheoNgay(sqlDate);
		ArrayList<ChiTietPhanCong> dsCTPC = dao_CTPC.getAllChiTietPC();
		if (dsCC.size() == 0) {
			for (ChiTietPhanCong chiTietPC : dsCTPC) {
				Object[] rowData = { stt, chiTietPC.getCongNhan().getMaCN(), chiTietPC.getCongNhan().getTenCN(),
						chiTietPC.getPhanCong().getSanPham().getTenSP(), chiTietPC.getPhanCong().getCongDoanSX(),
						chiTietPC.getSoLuongPC(), chiTietPC.getSoLuongPC(), false };
				dataModelDSCC.addRow(rowData);
				stt++;
			}
			tableDSCC.setEnabled(true);
		} else {
			docDuLieuKhiDaCoChamCong();
		}
		tableDSCC.setModel(dataModelDSCC);
		setColumnTable();
		tableDSCC.addMouseListener(this);
	}

	public void docDuLieuKhiDaCoChamCong() {
		int stt = 1;
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateNgayChamCong.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		java.util.Date ngayHT = new java.util.Date();
		long javaTimeHT = ngayHT.getTime();
		java.sql.Date sqlDateHT = new java.sql.Date(javaTimeHT);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(sqlDate);
		String strDateHT = formatter.format(sqlDateHT);

		ArrayList<ChamCongCN> dsCC = dao_CCCN.timKiemDanhSachChamCongCNTheoNgay(sqlDate);
		ArrayList<ChiTietPhanCong> dsCTPC = dao_CTPC.getAllChiTietPC();

		if (strDate.compareTo(strDateHT) < 0 || strDate.compareTo(strDateHT) > 0) {
			for (ChamCongCN cc : dsCC) {
				ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
				Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
						ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
						ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
				dataModelDSCC.addRow(rowData);
				stt++;
			}
			tableDSCC.setEnabled(false);
			btnChamCong.setEnabled(false);
		} else {
			if (dsCC.size() < dsCTPC.size()) {
				for (int i = 0; i < dsCC.size(); i++) {
					for (int j = 0; j < dsCTPC.size(); j++) {
						if (dsCTPC.get(j).getCongNhan().getMaCN()
								.equalsIgnoreCase(dsCC.get(i).getCongNhan().getMaCN())) {
							dsCTPC.remove(j);
						}
					}
				}
				for (int j = 0; j < dsCTPC.size(); j++) {
					Object[] rowData = { stt, dsCTPC.get(j).getCongNhan().getMaCN(),
							dsCTPC.get(j).getCongNhan().getTenCN(), dsCTPC.get(j).getPhanCong().getSanPham().getTenSP(),
							dsCTPC.get(j).getPhanCong().getCongDoanSX(), dsCTPC.get(j).getSoLuongPC(),
							dsCTPC.get(j).getSoLuongPC(), false };
					dataModelDSCC.addRow(rowData);
					stt++;
				}
				tableDSCC.setEnabled(true);
				btnChamCong.setEnabled(true);
			} else {
				for (ChamCongCN cc : dsCC) {
					ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
					Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
							ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
							ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
					dataModelDSCC.addRow(rowData);
					stt++;
				}
				tableDSCC.setEnabled(false);
				btnChamCong.setEnabled(false);
			}
		}
	}

	// Thiết lập chiều rộng của các cột
	public void setColumnTable() {
		tableDSCC.getColumnModel().getColumn(0).setPreferredWidth(48);
		tableDSCC.getColumnModel().getColumn(1).setPreferredWidth(68);
		tableDSCC.getColumnModel().getColumn(2).setPreferredWidth(131);
		tableDSCC.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableDSCC.getColumnModel().getColumn(4).setPreferredWidth(111);
		tableDSCC.getColumnModel().getColumn(5).setPreferredWidth(100);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tableDSCC.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		tableDSCC.getColumnModel().getColumn(6).setPreferredWidth(100);
		DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
		tableDSCC.getColumnModel().getColumn(6).setCellRenderer(centerRenderer1);
		tableDSCC.getColumnModel().getColumn(7).setPreferredWidth(93);
	}

	public boolean createCCCN() {
		int soLuong = dataModelDSCC.getRowCount();
		for (int i = 0; i < soLuong; i++) {
			String maCC = dao_CCCN.getAutoMaCCCN();
			String soSPHT = dataModelDSCC.getValueAt(i, 6).toString();
			boolean trangThai = false;
			int soSPPC = Integer.parseInt(dataModelDSCC.getValueAt(i, 5).toString());
			String maCN = dataModelDSCC.getValueAt(i, 1).toString();
			if (Integer.parseInt(soSPHT) < soSPPC) {
				CongNhan cn = dao_CN.timCN(maCN);
				ChamCongCN cc = new ChamCongCN(maCC, sqlDate, Integer.parseInt(soSPHT), trangThai, cn);
				dao_CCCN.themChamCong(cc);
			} else {
				trangThai = true;
				CongNhan cn = dao_CN.timCN(maCN);
				ChamCongCN cc = new ChamCongCN(maCC, sqlDate, Integer.parseInt(soSPHT), trangThai, cn);
				dao_CCCN.themChamCong(cc);
			}
		}
		return true;
	}

	public boolean kiemTraSoLuongSPHT() {
		int soLuong = dataModelDSCC.getRowCount();
		for (int i = 0; i < soLuong; i++) {
			int soSPPC = Integer.parseInt(dataModelDSCC.getValueAt(i, 5).toString());
			String soSanPhamHT = dataModelDSCC.getValueAt(i, 6).toString().trim();
			if (soSanPhamHT.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Số sản phẩm hoàn thành ko được để trống!");
				return false;
			}
			int soSPHT = 0;
			soSPHT = Integer.parseInt(soSanPhamHT);
			if (soSPHT > soSPPC) {
				JOptionPane.showMessageDialog(this,
						"Số lượng hoàn thành đã vượt quá số sản phẩm được phân công! \nVui lòng nhập lại số lượng phù hợp!");
				return false;
			}
		}
		return true;
	}

	public void timTheoNgay(java.util.Date ngayCC) {
		int stt = 1;
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		java.util.Date ngayHT = new java.util.Date();
		long javaTimeHT = ngayHT.getTime();
		java.sql.Date sqlDateHT = new java.sql.Date(javaTimeHT);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(sqlDate);
		String strDateHT = formatter.format(sqlDateHT);

		ArrayList<ChamCongCN> dsCC = dao_CCCN.timKiemDanhSachChamCongCNTheoNgay(sqlDate);
		if (dsCC.size() != 0) {
			docDuLieuKhiDaCoChamCong();
		} else {
			if (strDate.compareTo(strDateHT) > 0 || strDate.compareTo(strDateHT) < 0) {
				JOptionPane.showMessageDialog(null, "Ngày " + strDate + " chưa có chấm công!");
				dateNgayChamCong.setDate(new Date());
			} else {
				for (ChiTietPhanCong chiTietPC : dao_CTPC.getAllChiTietPC()) {
					Object[] rowData = { stt, chiTietPC.getCongNhan().getMaCN(), chiTietPC.getCongNhan().getTenCN(),
							chiTietPC.getPhanCong().getSanPham().getTenSP(), chiTietPC.getPhanCong().getCongDoanSX(),
							chiTietPC.getSoLuongPC(), chiTietPC.getSoLuongPC(), false };
					dataModelDSCC.addRow(rowData);
					stt++;
				}
				tableDSCC.setEnabled(true);
				btnChamCong.setEnabled(true);
			}
		}
		tableDSCC.addMouseListener(this);
	}

	public void timTheoSP(String tenSP) {
		int stt = 1;
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateNgayChamCong.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		java.util.Date ngayHT = new java.util.Date();
		long javaTimeHT = ngayHT.getTime();
		java.sql.Date sqlDateHT = new java.sql.Date(javaTimeHT);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(sqlDate);
		String strDateHT = formatter.format(sqlDateHT);

		ArrayList<ChamCongCN> dsCC = dao_CCCN.timKiemDanhSachChamCongCNTheoSanPham(tenSP, sqlDate);
		ArrayList<ChamCongCN> dsCCTN = dao_CCCN.timKiemDanhSachChamCongCNTheoNgay(sqlDate);
		ArrayList<ChiTietPhanCong> dsCTPC = dao_CTPC.timKiemDSCTPCTheoTenSP(tenSP);
		// Nếu ngày đó chưa được chấm công thì lọc theo ds được phân công trong chi tiết
		// phân công
		// Ngược lại đã được chấm công thì lọc theo tên sản phẩm đã được chấm công của
		// ngày đó trong chấm công CN
		if (strDate.compareTo(strDateHT) > 0 || strDate.compareTo(strDateHT) < 0) {
			if (dsCCTN.size() == 0) {
				JOptionPane.showMessageDialog(null, "Ngày " + sqlDate + " chưa có chấm công!");
				dateNgayChamCong.setDate(new Date());
			} else {
				if (dsCC.size() == 0) {
					JOptionPane.showMessageDialog(null, "Ko tìm thấy sản phẩm này!");
					for (ChamCongCN cc : dsCCTN) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				} else {
					for (ChamCongCN cc : dsCC) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				}
			}
		} else {
			if (dsCCTN.size() == 0) {
				if (dsCTPC.size() != 0) {
					for (ChiTietPhanCong chiTietPC : dsCTPC) {
						Object[] rowData = { stt, chiTietPC.getCongNhan().getMaCN(), chiTietPC.getCongNhan().getTenCN(),
								chiTietPC.getPhanCong().getSanPham().getTenSP(),
								chiTietPC.getPhanCong().getCongDoanSX(), chiTietPC.getSoLuongPC(),
								chiTietPC.getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Ko tìm thấy công nhân nào được phân công sản phẩm này!");
					for (ChiTietPhanCong chiTietPC : dao_CTPC.getAllChiTietPC()) {
						Object[] rowData = { stt, chiTietPC.getCongNhan().getMaCN(), chiTietPC.getCongNhan().getTenCN(),
								chiTietPC.getPhanCong().getSanPham().getTenSP(),
								chiTietPC.getPhanCong().getCongDoanSX(), chiTietPC.getSoLuongPC(),
								chiTietPC.getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
				}
				btnChamCong.setEnabled(true);
			} else {
				if (dsCC.size() < dsCTPC.size()) {
					for (int i = 0; i < dsCC.size(); i++) {
						for (int j = 0; j < dsCTPC.size(); j++) {
							if (dsCTPC.get(j).getCongNhan().getMaCN()
									.equalsIgnoreCase(dsCC.get(i).getCongNhan().getMaCN())) {
								dsCTPC.remove(j);
							}
						}
					}
					for (int j = 0; j < dsCTPC.size(); j++) {
						Object[] rowData = { stt, dsCTPC.get(j).getCongNhan().getMaCN(),
								dsCTPC.get(j).getCongNhan().getTenCN(),
								dsCTPC.get(j).getPhanCong().getSanPham().getTenSP(),
								dsCTPC.get(j).getPhanCong().getCongDoanSX(), dsCTPC.get(j).getSoLuongPC(),
								dsCTPC.get(j).getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
					btnChamCong.setEnabled(true);
				} else if (dsCC.size() == 0) {
					JOptionPane.showMessageDialog(null, "Ko tìm thấy sản phẩm này!");
					docDuLieuKhiDaCoChamCong();
				} else {
					for (ChamCongCN cc : dsCC) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				}
			}
		}
		setColumnTable();
		tableDSCC.addMouseListener(this);
	}

	public void timTheoCongDoan(String congDoan) {
		int stt = 1;
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateNgayChamCong.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		java.util.Date ngayHT = new java.util.Date();
		long javaTimeHT = ngayHT.getTime();
		java.sql.Date sqlDateHT = new java.sql.Date(javaTimeHT);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(sqlDate);
		String strDateHT = formatter.format(sqlDateHT);

		ArrayList<ChamCongCN> dsCC = dao_CCCN.timKiemDanhSachChamCongCNTheoCongDoan(congDoan, sqlDate);
		ArrayList<ChamCongCN> dsCCTN = dao_CCCN.timKiemDanhSachChamCongCNTheoNgay(sqlDate);
		ArrayList<ChiTietPhanCong> dsCTPC = dao_CTPC.timKiemDSCTPCTheoCDSX(congDoan);
		if (strDate.compareTo(strDateHT) > 0 || strDate.compareTo(strDateHT) < 0) {
			if (dsCCTN.size() == 0) {
				JOptionPane.showMessageDialog(null, "Ngày " + sqlDate + " chưa có chấm công!");
				dateNgayChamCong.setDate(new Date());
			} else {
				if (dsCC.size() == 0) {
					JOptionPane.showMessageDialog(null, "Ngày " + strDate + " không tìm thấy công đoạn " + congDoan);
					for (ChamCongCN cc : dsCCTN) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				} else {
					for (ChamCongCN cc : dsCC) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				}
			}
		} else {
			if (dsCCTN.size() == 0) {
				if (dsCTPC.size() != 0) {
					for (ChiTietPhanCong chiTietPC : dsCTPC) {
						Object[] rowData = { stt, chiTietPC.getCongNhan().getMaCN(), chiTietPC.getCongNhan().getTenCN(),
								chiTietPC.getPhanCong().getSanPham().getTenSP(),
								chiTietPC.getPhanCong().getCongDoanSX(), chiTietPC.getSoLuongPC(),
								chiTietPC.getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Công đoạn này chưa được phân công cho công nhân nào của ngày hôm nay!");
					for (ChiTietPhanCong chiTietPC : dao_CTPC.getAllChiTietPC()) {
						Object[] rowData = { stt, chiTietPC.getCongNhan().getMaCN(), chiTietPC.getCongNhan().getTenCN(),
								chiTietPC.getPhanCong().getSanPham().getTenSP(),
								chiTietPC.getPhanCong().getCongDoanSX(), chiTietPC.getSoLuongPC(),
								chiTietPC.getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
				}
				btnChamCong.setEnabled(true);
			} else {
				if (dsCC.size() < dsCTPC.size()) {
					for (int i = 0; i < dsCC.size(); i++) {
						for (int j = 0; j < dsCTPC.size(); j++) {
							if (dsCTPC.get(j).getCongNhan().getMaCN()
									.equalsIgnoreCase(dsCC.get(i).getCongNhan().getMaCN())) {
								dsCTPC.remove(j);
							}
						}
					}
					for (int j = 0; j < dsCTPC.size(); j++) {
						Object[] rowData = { stt, dsCTPC.get(j).getCongNhan().getMaCN(),
								dsCTPC.get(j).getCongNhan().getTenCN(),
								dsCTPC.get(j).getPhanCong().getSanPham().getTenSP(),
								dsCTPC.get(j).getPhanCong().getCongDoanSX(), dsCTPC.get(j).getSoLuongPC(),
								dsCTPC.get(j).getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
					btnChamCong.setEnabled(true);
				} else if (dsCC.size() == 0) {
					JOptionPane.showMessageDialog(null, "Ko tìm thấy công đoạn này!");
					docDuLieuKhiDaCoChamCong();
				} else {
					for (ChamCongCN cc : dsCC) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				}
			}
		}
		setColumnTable();
		tableDSCC.addMouseListener(this);
	}

	public void timTheoTen(String tenCN) {
		int stt = 1;
		java.util.Date ngayCC = new java.util.Date();
		ngayCC = dateNgayChamCong.getDate();
		long javaTime = ngayCC.getTime();
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		java.util.Date ngayHT = new java.util.Date();
		long javaTimeHT = ngayHT.getTime();
		java.sql.Date sqlDateHT = new java.sql.Date(javaTimeHT);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(sqlDate);
		String strDateHT = formatter.format(sqlDateHT);

		ArrayList<ChamCongCN> dsCC = dao_CCCN.timKiemDanhSachChamCongCNTheoTen(tenCN, sqlDate);
		ArrayList<ChamCongCN> dsCCTN = dao_CCCN.timKiemDanhSachChamCongCNTheoNgay(sqlDate);
		ArrayList<ChiTietPhanCong> dsCTPC = dao_CTPC.timKiemDSCTPCTheoTenCN(tenCN);

		if (strDate.compareTo(strDateHT) > 0 || strDate.compareTo(strDateHT) < 0) {
			if (dsCCTN.size() == 0) {
				JOptionPane.showMessageDialog(null, "Ngày " + sqlDate + " chưa có chấm công!");
				dateNgayChamCong.setDate(new Date());
			} else {
				if (dsCC.size() == 0) {
					JOptionPane.showMessageDialog(null, "Ko tìm thấy công nhân này!");
					for (ChamCongCN cc : dsCCTN) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				} else {
					for (ChamCongCN cc : dsCC) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				}
			}
		} else {
			if (dsCCTN.size() == 0) {
				if (dsCTPC.size() != 0) {
					for (ChiTietPhanCong chiTietPC : dsCTPC) {
						Object[] rowData = { stt, chiTietPC.getCongNhan().getMaCN(), chiTietPC.getCongNhan().getTenCN(),
								chiTietPC.getPhanCong().getSanPham().getTenSP(),
								chiTietPC.getPhanCong().getCongDoanSX(), chiTietPC.getSoLuongPC(),
								chiTietPC.getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Ko tìm thấy!");
					for (ChiTietPhanCong chiTietPC : dao_CTPC.getAllChiTietPC()) {
						Object[] rowData = { stt, chiTietPC.getCongNhan().getMaCN(), chiTietPC.getCongNhan().getTenCN(),
								chiTietPC.getPhanCong().getSanPham().getTenSP(),
								chiTietPC.getPhanCong().getCongDoanSX(), chiTietPC.getSoLuongPC(),
								chiTietPC.getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
				}
				btnChamCong.setEnabled(true);
			} else {
				if (dsCC.size() < dsCTPC.size()) {
					for (int i = 0; i < dsCC.size(); i++) {
						for (int j = 0; j < dsCTPC.size(); j++) {
							if (dsCTPC.get(j).getCongNhan().getMaCN()
									.equalsIgnoreCase(dsCC.get(i).getCongNhan().getMaCN())) {
								dsCTPC.remove(j);
							}
						}
					}
					for (int j = 0; j < dsCTPC.size(); j++) {
						Object[] rowData = { stt, dsCTPC.get(j).getCongNhan().getMaCN(),
								dsCTPC.get(j).getCongNhan().getTenCN(),
								dsCTPC.get(j).getPhanCong().getSanPham().getTenSP(),
								dsCTPC.get(j).getPhanCong().getCongDoanSX(), dsCTPC.get(j).getSoLuongPC(),
								dsCTPC.get(j).getSoLuongPC(), false };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(true);
					btnChamCong.setEnabled(true);
				} else if (dsCC.size() == 0) {
					JOptionPane.showMessageDialog(null, "Ko tìm thấy công nhân này!");
					docDuLieuKhiDaCoChamCong();
				} else {
					for (ChamCongCN cc : dsCC) {
						ChiTietPhanCong ctpc = dao_CTPC.timPC(cc.getCongNhan().getMaCN());
						Object[] rowData = { stt, cc.getCongNhan().getMaCN(), cc.getCongNhan().getTenCN(),
								ctpc.getPhanCong().getSanPham().getTenSP(), ctpc.getPhanCong().getCongDoanSX(),
								ctpc.getSoLuongPC(), cc.getSoSPHoanThanh(), cc.isTrangThai() };
						dataModelDSCC.addRow(rowData);
						stt++;
					}
					tableDSCC.setEnabled(false);
					btnChamCong.setEnabled(false);
				}
			}
		}
		setColumnTable();
		tableDSCC.addMouseListener(this);
	}

	public void createTienLuongCN() {
		for (ChamCongCN ccCN : dao_CCCN.timKiemDanhSachChamCongCNTheoNgay(sqlThangLuong)) {
			String maLuongCN = dao_TLCN.getAutoMaTienLuongCN();
			tienLuong = tinhTienLuongCongNhan(ccCN);
			tienPhat = tinhTienPhatCongNhan(ccCN);
			tienThuong = tinhTienThuongCongNhan(ccCN);
			tongTienLuong = 0;
			if (tienLuong != 0)
				tongTienLuong = tienLuong + tienThuong + ccCN.getCongNhan().getPhuCap() * 1000 - tienPhat;
			TienLuongCN tl = new TienLuongCN(maLuongCN, sqlThangLuong, tienLuong, tienThuong, tienPhat, tongTienLuong,
					ccCN.getCongNhan());
			dao_TLCN.themTienLuong(tl);
		}
	}

	public void updateLuong() {
		ArrayList<ChamCongCN> dsCC = dao_CCCN.timKiemDanhSachChamCongCNTheoNgay(sqlThangLuong);
		for (ChamCongCN ccCN : dsCC) {
			tienLuong = tinhTienLuongCongNhan(ccCN);
			tienPhat = tinhTienPhatCongNhan(ccCN);
			tienThuong = tinhTienThuongCongNhan(ccCN);
			tongTienLuong = 0;
			tongTienLuong = tienLuong + tienThuong + ccCN.getCongNhan().getPhuCap() * 1000 - tienPhat;
			ArrayList<TienLuongCN> ds = dao_TLCN.getDSLuongCongNhanTheoMaCongNhan(ccCN.getCongNhan().getMaCN(), thang);
			if (ds.isEmpty()) {
				TienLuongCN tlCN = new TienLuongCN(dao_TLCN.getAutoMaTienLuongCN(), sqlThangLuong, tienLuong,
						tienThuong, tienPhat, tongTienLuong, ccCN.getCongNhan());
				dao_TLCN.themTienLuong(tlCN);
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c1 = Calendar.getInstance();
				c1.roll(Calendar.DATE, -1);
				String yesTerDay = dateFormat.format(c1.getTime());
				if (dao_TLCN.getLuongCongNhanTheoMaCongNhanVaNgay(ccCN.getCongNhan().getMaCN(), yesTerDay)
						.getMaLuongCN() != null) {
					TienLuongCN tl = dao_TLCN.getLuongCongNhanTheoMaCongNhan(ccCN.getCongNhan().getMaCN(), thang);
					double luong = tl.getTienLuong() + tienLuong;
					double thuong = tl.getTienThuong() + tienThuong;
					double phat = tl.getTienPhat() + tienPhat;
					double tongLuong = luong + thuong + ccCN.getCongNhan().getPhuCap() * 1000 - phat;
					dao_TLCN.capNhatLuongCN(sqlThangLuong, luong, thuong, phat, tongLuong, ccCN.getCongNhan().getMaCN(),
							thang);
				}

			}
		}
	}

	public double tinhTienLuongCongNhan(ChamCongCN ccCN) {
		tienLuong = 0;
		int soLuong = 0;
		ChiTietPhanCong ctpc = dao_CTPC.timPC(ccCN.getCongNhan().getMaCN());
		soLuong += ccCN.getSoSPHoanThanh();
		congDoan = ctpc.getPhanCong().getCongDoanSX();
		donGia = ctpc.getPhanCong().getSanPham().getDonGia();
		if (congDoan.equalsIgnoreCase("Rập máy"))
			tienLuong = soLuong * donGia * 0.25;
		else if (congDoan.equalsIgnoreCase("May vắt sổ") || congDoan.equalsIgnoreCase("May móc xích kép")) {
			tienLuong = soLuong * donGia * 0.45;
		} else
			tienLuong = soLuong * donGia * 0.15;
		return tienLuong;
	}

	public double tinhTienThuongCongNhan(ChamCongCN ccCN) {
		tienThuong = 0;
		ChiTietPhanCong ctpc = dao_CTPC.timPC(ccCN.getCongNhan().getMaCN());
		congDoan = ctpc.getPhanCong().getCongDoanSX();
		if (ccCN.getSoSPHoanThanh() == ctpc.getSoLuongPC()) {
			if (congDoan.equalsIgnoreCase("Rập máy"))
				tienThuong = 0.1 * tinhTienLuongCongNhan(ccCN);
			else if (congDoan.equalsIgnoreCase("May vắt sổ") || congDoan.equalsIgnoreCase("May móc xích kép")) {
				tienThuong = 0.1 * tinhTienLuongCongNhan(ccCN);
			} else
				tienThuong = 0.1 * tinhTienLuongCongNhan(ccCN);
		}
		return tienThuong;
	}

	public double tinhTienPhatCongNhan(ChamCongCN ccCN) {
		tienPhat = 0;
		ChiTietPhanCong ctpc = dao_CTPC.timPC(ccCN.getCongNhan().getMaCN());
		congDoan = ctpc.getPhanCong().getCongDoanSX();
		donGia = ctpc.getPhanCong().getSanPham().getDonGia();
		if (ccCN.getSoSPHoanThanh() < 0.7 * ctpc.getSoLuongPC()) {
			double soSPPhat = 0.7 * ctpc.getSoLuongPC() - ccCN.getSoSPHoanThanh();
			if (congDoan.equalsIgnoreCase("Rập máy"))
				tienPhat = 0.15 * soSPPhat * donGia * 0.25;
			else if (congDoan.equalsIgnoreCase("May vắt sổ") || congDoan.equalsIgnoreCase("May móc xích kép")) {
				tienPhat = 0.15 * soSPPhat * donGia * 0.45;
			} else
				tienPhat = 0.15 * soSPPhat * donGia * 0.15;
		}
		return tienPhat;
	}

	public void themLuong() {
		ArrayList<TienLuongCN> dsTL = dao_TLCN.getDanhSachTienLuongTheoThang(thang, nam);
		if (dsTL.isEmpty()) {
			createTienLuongCN();
		} else {
			updateLuong();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnChamCong)) {
			if (kiemTraSoLuongSPHT()) {
				if (createCCCN()) {
					dataModelDSCC.getDataVector().removeAllElements();
					DocDuLieuTuDatabaseVaoTableCCCN();
					themLuong();
					JOptionPane.showMessageDialog(null, "Chấm công thành công");
				}
			}
		} else if (o.equals(btnTimTheoNgay)) {
			dataModelDSCC.getDataVector().removeAllElements();
			timTheoNgay(dateNgayChamCong.getDate());
		} else if (o.equals(cboSanPham)) {
			dataModelDSCC.getDataVector().removeAllElements();
			timTheoSP(cboSanPham.getSelectedItem().toString());
		} else if (o.equals(cboCongDoan)) {
			dataModelDSCC.getDataVector().removeAllElements();
			timTheoCongDoan(cboCongDoan.getSelectedItem().toString());
		} else if (o.equals(btnTimTheoTen)) {
			dataModelDSCC.getDataVector().removeAllElements();
			timTheoTen(txtTimTenCN.getText().trim());
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
