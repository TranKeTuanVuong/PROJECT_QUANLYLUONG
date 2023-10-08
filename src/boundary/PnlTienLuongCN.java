package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import dao.DAO_ChamCongCN;
import dao.DAO_ChiTietPC;
import dao.DAO_CongNhan;
import dao.DAO_TienLuongCN;
import entity.CongNhan;
import entity.TienLuongCN;

public class PnlTienLuongCN extends JPanel implements ActionListener, MouseListener {
	private JTable tableLuong;
	String[] headers = { "STT", "Mã", "Họ tên", "Phụ cấp", "Tiền lương", "Tiền thưởng", "Tiền phạt",
			"Tổng tiền lương" };
	private DefaultTableModel model;
	private DAO_ChamCongCN dao_CCCN;
	private DAO_CongNhan dao_CN;
	private DAO_ChiTietPC dao_CTPC;
	private DAO_TienLuongCN dao_TienLuongCN;

	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private JButton btnTim, btnExcel;

	/**
	 * Create the panel.
	 */
	public PnlTienLuongCN() {
		dao_CN = new DAO_CongNhan();
		dao_CCCN = new DAO_ChamCongCN();
		dao_CTPC = new DAO_ChiTietPC();
		dao_TienLuongCN = new DAO_TienLuongCN();

		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBorder(BorderFactory.createTitledBorder("Tiền lương nhân viên"));
		setLayout(null);

		JPanel pnlBangLuong = new JPanel();
		pnlBangLuong.setBorder(new LineBorder(Color.CYAN));
		pnlBangLuong.setBackground(Color.WHITE);
		pnlBangLuong.setBounds(15, 127, 897, 367);
		add(pnlBangLuong);
		pnlBangLuong.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPanelLuong = new JScrollPane();
		pnlBangLuong.add(scrollPanelLuong, BorderLayout.CENTER);

		tableLuong = new JTable();
		model = new DefaultTableModel(headers, 0);
		tableLuong.setModel(model);
		JTableHeader hd = tableLuong.getTableHeader();
		hd.setBackground(Color.cyan);
		hd.setFont(new Font("Time New Roman", Font.BOLD, 12));
		scrollPanelLuong.setViewportView(tableLuong);

		JLabel lblThang = new JLabel("Tháng: ");
		lblThang.setBounds(20, 45, 57, 22);
		add(lblThang);

		monthChooser = new JMonthChooser();
		monthChooser.setBounds(68, 45, 119, 23);
		add(monthChooser);

		JLabel lblNam = new JLabel("Năm: ");
		lblNam.setBounds(226, 45, 57, 22);
		add(lblNam);

		yearChooser = new JYearChooser();
		yearChooser.setBounds(269, 45, 67, 22);
		add(yearChooser);

		btnExcel = new JButton("Xuất file excel");
		btnExcel.setBackground(Color.CYAN);
		btnExcel.setBorderPainted(false);
		btnExcel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnExcel.setBounds(776, 37, 136, 36);
		add(btnExcel);
		tableLuong.addMouseListener(this);
		btnExcel.addActionListener(this);

		btnTim = new JButton("");
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(Color.WHITE);
		btnTim.setIcon(new ImageIcon(PnlTienLuongNV.class.getResource("/image/search-icon.png")));
		btnTim.setBounds(349, 45, 22, 23);
		add(btnTim);
		btnTim.addActionListener(this);

		JLabel lblDanhSchTin = new JLabel("Danh sách tiền lương công nhân");
		lblDanhSchTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSchTin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblDanhSchTin.setBounds(45, 86, 821, 27);
		add(lblDanhSchTin);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(15, 81, 897, 36);
		add(panel);

		btnExcel.addActionListener(this);
		btnTim.addActionListener(this);
		// load DS lương CN
		model.getDataVector().removeAllElements();
		loadDSLuongTheoThang();
	}

	public JMonthChooser getMonthChooser() {
		return monthChooser;
	}

	public JYearChooser getYearChooser() {
		return yearChooser;
	}

	public void loadDSLuongTheoThang() {
		int stt = 1;
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
		String thang = monthFormatter.format(Month.of(monthChooser.getMonth() + 1));
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
		String nam = yearFormatter.format(Year.of(yearChooser.getYear()));
		for (TienLuongCN tl : dao_TienLuongCN.getDanhSachTienLuongTheoThang(thang, nam)) {
			model.addRow(new Object[] { stt, tl.getCongNhan().getMaCN(), tl.getCongNhan().getTenCN(),
					chuyenDoiDinhDangTienTe(tl.getCongNhan().getPhuCap() * 1000),
					chuyenDoiDinhDangTienTe(tl.getTienLuong()), chuyenDoiDinhDangTienTe(tl.getTienThuong()),
					chuyenDoiDinhDangTienTe(tl.getTienPhat()), chuyenDoiDinhDangTienTe(tl.getTongLuong()) });
			stt++;
		}
		tableLuong.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableLuong.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableLuong.getColumnModel().getColumn(2).setPreferredWidth(140);
	}

	private String chuyenDoiDinhDangTienTe(double soTien) {
		Locale localeVn = new Locale("vi", "VN");
		NumberFormat vn = NumberFormat.getCurrencyInstance(localeVn);
		return vn.format(soTien);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tableLuong)) {
			int row = tableLuong.getSelectedRow();
			if (row >= 0) {
				String maCN = model.getValueAt(row, 1).toString();
				String tenCN = model.getValueAt(row, 2).toString();
				DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
				String thang = monthFormatter.format(Month.of(monthChooser.getMonth() + 1));
				DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
				String nam = yearFormatter.format(Year.of(yearChooser.getYear()));
				String tenSP = dao_TienLuongCN.getSanPhamTheoMaCNvaTheoThangLuong(maCN, thang, nam);
				String congDoan = dao_TienLuongCN.getCongDoanTheoMaCNvaTheoThangLuong(maCN, thang, nam);
				int tongSoSPHT = dao_TienLuongCN.getTongSoLuongSPHTTheoMaCNvaTheoThangLuong(maCN, thang, nam);
				double tienLuong = dao_TienLuongCN.getTienLuong(maCN, thang, nam);
				double tienThuong = dao_TienLuongCN.getTienThuong(maCN, thang, nam);
				double tienPhat = dao_TienLuongCN.getTienPhat(maCN, thang, nam);
				double tongLuong = dao_TienLuongCN.getTongLuong(maCN, thang, nam);
				CongNhan cn = dao_CN.getCongNhanTheoMaCN(maCN);
				double phuCap = cn.getPhuCap();
				DialogCTLuongCN CTLuongCN = new DialogCTLuongCN(maCN, tenCN, congDoan, tenSP, tongSoSPHT, tienLuong,
						tienThuong, phuCap, tienPhat, tongLuong, thang, nam);
				CTLuongCN.setLocationRelativeTo(null);
				CTLuongCN.setVisible(true);
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

	private void xuatFileExcel() {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("TL");

			XSSFRow row = null;
			Cell cell = null;

			row = sheet.createRow((short) 2);
			row.setHeight((short) 500);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("DANH SÁCH TIỀN LƯƠNG CÔNG NHÂN");

			row = sheet.createRow((short) 3);
			row.setHeight((short) 500);

			cell = row.createCell(0, CellType.NUMERIC);
			cell.setCellValue("STT");

			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Mã CN");

			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Họ tên");

			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Phụ cấp");

			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Tiền lương");

			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Tiền thưởng");

			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Tiền phạt");

			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("Tổng tiền lương");

			DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
			String thang = monthFormatter.format(Month.of(monthChooser.getMonth() + 1));
			DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
			String nam = yearFormatter.format(Year.of(yearChooser.getYear()));
			ArrayList<TienLuongCN> dsTL = dao_TienLuongCN.getDanhSachTienLuongTheoThang(thang, nam);
			if (dsTL != null) {
				int s = dsTL.size();
				for (int i = 0; i < s; i++) {
					TienLuongCN tl = dsTL.get(i);
					CongNhan cn = dao_CN.getCongNhanTheoMaCN(tl.getCongNhan().getMaCN());
					double phuCap = cn.getPhuCap() * 1000;
					row = sheet.createRow((short) 4 + i);
					row.setHeight((short) 400);
					row.createCell(0).setCellValue(i + 1);
					row.createCell(1).setCellValue(tl.getCongNhan().getMaCN());
					row.createCell(2).setCellValue(cn.getTenCN());
					row.createCell(3).setCellValue(phuCap);
					row.createCell(4).setCellValue(tl.getTienLuong());
					row.createCell(5).setCellValue(tl.getTienThuong());
					row.createCell(6).setCellValue(tl.getTienPhat());
					row.createCell(7).setCellValue(tl.getTongLuong());
				}
				File f = new File("T:\\DanhSachTienLuongCN.xlsx");
				FileOutputStream fis = new FileOutputStream(f);
				workbook.write(fis);
				fis.close();
				JOptionPane.showMessageDialog(null, "Xuất file thành công!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// lethikimngan
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
			String thang = monthFormatter.format(Month.of(monthChooser.getMonth() + 1));
			DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
			String nam = yearFormatter.format(Year.of(yearChooser.getYear()));
			ArrayList<TienLuongCN> dsTL = dao_TienLuongCN.getDanhSachTienLuongTheoThang(thang, nam);
			if (dsTL.size() == 0) {
				JOptionPane.showMessageDialog(null, "Ko tìm thấy!");
			} else {
				model.getDataVector().removeAllElements();
				loadDSLuongTheoThang();
			}
		} else if (o.equals(btnExcel)) {
			xuatFileExcel();
		}
	}
}
