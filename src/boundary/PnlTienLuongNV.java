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
import javax.swing.border.Border;
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
import dao.DAO_ChamCongNV;
import dao.DAO_ChiTietPC;
import dao.DAO_CongNhan;
import dao.DAO_NhanVien;
import dao.DAO_TienLuongCN;
import dao.DAO_TienLuongNV;
import entity.NhanVien;
import entity.TienLuongNV;

public class PnlTienLuongNV extends JPanel implements ActionListener, MouseListener {
	private JTable tableLuong;
	String[] headers = { "STT", "Mã","Họ tên", "Phụ cấp", "Tiền lương", "Tiền thưởng", "Tiền phạt", "Tổng tiền lương" };
	private DefaultTableModel model;
	private DAO_NhanVien dao_nv;
	private DAO_ChamCongNV dao_ccnv;
	private DAO_ChamCongCN dao_CCCN;
	private DAO_CongNhan dao_CN;
	private DAO_ChiTietPC dao_CTPC;
	private DAO_TienLuongCN dao_TienLuongCN;
	private DAO_TienLuongNV dao_TL;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private JButton btnTim;
	private JButton btnExcel;

	/**
	 * Create the panel.
	 */
	public PnlTienLuongNV() {
		dao_CN = new DAO_CongNhan();
		dao_CCCN = new DAO_ChamCongCN();
		dao_nv = new DAO_NhanVien();
		dao_ccnv = new DAO_ChamCongNV();
		dao_CTPC = new DAO_ChiTietPC();
		dao_TienLuongCN = new DAO_TienLuongCN();
		dao_TL=new DAO_TienLuongNV();

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
		tableLuong.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableLuong.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableLuong.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableLuong.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableLuong.getColumnModel().getColumn(4).setPreferredWidth(50);
		tableLuong.getColumnModel().getColumn(5).setPreferredWidth(50);
		tableLuong.getColumnModel().getColumn(6).setPreferredWidth(50);
		JTableHeader hd=tableLuong.getTableHeader();
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
		btnExcel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		btnExcel.setBounds(776, 37, 136, 36);
		btnExcel.setBorderPainted(false);
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
		
		JLabel lblDanhSchTin = new JLabel("Danh sách tiền lương nhân viên");
		lblDanhSchTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSchTin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblDanhSchTin.setBounds(45, 86, 821, 27);
		add(lblDanhSchTin);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(15, 81, 897, 36);
		add(panel);
		// load DS lương NV
		model.getDataVector().removeAllElements();
		loadDanhSachNhanVienTheoThang(monthChooser.getMonth(),yearChooser.getYear());
	}

	public JMonthChooser getMonthChooser() {
		return monthChooser;
	}

	public JYearChooser getYearChooser() {
		return yearChooser;
	}

	

	
	//load dsNV
	private void loadDanhSachNhanVienTheoThang(int month,int year) {
		int stt=1;
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
		String thang = monthFormatter.format(Month.of(month + 1));
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
		String nam = yearFormatter.format(Year.of(year));
		ArrayList<TienLuongNV> dsTL=dao_TL.getDanhSachTienLuongTheoThang(thang, nam);
		if(dsTL.isEmpty())
			JOptionPane.showMessageDialog(null, "Không tìm thấy");
		else {
			for(TienLuongNV tl:dsTL) {
				NhanVien nv=dao_nv.getNhanVienTheoMaNV(tl.getNhanVien().getMaNhanVien());
				model.addRow(new Object[] {stt,tl.getNhanVien().getMaNhanVien(),nv.getTenNhanVien(),
						chuyenDoiDinhDangTienTe(nv.getPhuCap()*1000), 
						chuyenDoiDinhDangTienTe(tl.getTienLuong()),
						chuyenDoiDinhDangTienTe(tl.getTienThuong()),
						chuyenDoiDinhDangTienTe(tl.getTienPhat()),
						chuyenDoiDinhDangTienTe(tl.getTongLuong())});
				stt++;
			}
		}		
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
				String maNV=model.getValueAt(row, 1).toString();
				String tenNV=model.getValueAt(row, 2).toString();
				NhanVien nv=new DAO_NhanVien().getNhanVienTheoMaNV(maNV);
				DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
				String thang = monthFormatter.format(Month.of(monthChooser.getMonth()+1));
				DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
				String nam = yearFormatter.format(Year.of(yearChooser.getYear()));
				TienLuongNV tl=new DAO_TienLuongNV().getTienLuongNVTheoThangNam(maNV, thang, nam);
				int soNgayDiLam=new DAO_ChamCongNV().getSoNgayDiLam(maNV, thang, nam);
				int soGioTangCa=new DAO_ChamCongNV().getSoGioTangCa(maNV, thang, nam);
				int soNgayNghiCoPhep=new DAO_ChamCongNV().getSoNgayNghiCoPhep(maNV, thang, nam);
				int soNgayNghiKhongPhep=new DAO_ChamCongNV().getSoNgayNghiKhongPhep(maNV, thang, nam);
				new FrameChiTietTienLuongNV(tl, nv, soNgayDiLam, soGioTangCa, soNgayNghiCoPhep, soNgayNghiKhongPhep,thang,nam).setVisible(true);
			}
		}
	}
	
	private void xuatFileExcel() {
		try {
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Tiền lương nhân viên");
		
		XSSFRow row=null;
		Cell cell=null;
		
		row=sheet.createRow((short) 2);
		row.setHeight((short) 500);
		cell=row.createCell(0,CellType.STRING);
		cell.setCellValue("DANH SÁCH TIỀN LƯƠNG NHÂN VIÊN");
		
		row=sheet.createRow((short) 3);
		row.setHeight((short)500);
		
		cell=row.createCell(0, CellType.NUMERIC);
		cell.setCellValue("STT");
		
		cell=row.createCell(1, CellType.STRING);
		cell.setCellValue("Mã NV");
		
		cell=row.createCell(2, CellType.STRING);
		cell.setCellValue("Họ tên");
		
		cell=row.createCell(3, CellType.STRING);
		cell.setCellValue("Phụ cấp");
		
		cell=row.createCell(4, CellType.STRING);
		cell.setCellValue("Tiền lương");
		
		cell=row.createCell(5, CellType.STRING);
		cell.setCellValue("Tiền thưởng");
		
		cell=row.createCell(6, CellType.STRING);
		cell.setCellValue("Tiền phạt");
		
		cell=row.createCell(7, CellType.STRING);
		cell.setCellValue("Tổng tiền lương");
		
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
		String thang = monthFormatter.format(Month.of(monthChooser.getMonth() + 1));
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
		String nam = yearFormatter.format(Year.of(yearChooser.getYear()));
		ArrayList<TienLuongNV> dsTL=dao_TL.getDanhSachTienLuongTheoThang(thang,nam);
		if(dsTL!=null) {
			int s=dsTL.size();
			for(int i=0;i<s;i++) {
				TienLuongNV tl=dsTL.get(i);
				NhanVien nv=dao_nv.getNhanVienTheoMaNV(tl.getNhanVien().getMaNhanVien());
				double phuCap=nv.getPhuCap()*1000;
				row=sheet.createRow((short)4+i);
				row.setHeight((short)400);
				row.createCell(0).setCellValue(i+1);
				row.createCell(1).setCellValue(tl.getNhanVien().getMaNhanVien());
				row.createCell(2).setCellValue(nv.getTenNhanVien());
				row.createCell(3).setCellValue(phuCap);
				row.createCell(4).setCellValue(tl.getTienLuong());
				row.createCell(5).setCellValue(tl.getTienThuong());
				row.createCell(6).setCellValue(tl.getTienPhat());
				row.createCell(7).setCellValue(tl.getTongLuong());	
			}
			File f=new File("T:\\DanhSachTienLuongNV.xlsx");
			FileOutputStream fis=new FileOutputStream(f);
			workbook.write(fis);
			fis.close();
			JOptionPane.showMessageDialog(null, "Xuất file thành công!");
		}
		}catch(Exception e){
			e.printStackTrace();
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
		Object o = e.getSource();
		if (o.equals(btnTim)) {
			DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
			String thang = monthFormatter.format(Month.of(monthChooser.getMonth() + 1));
			DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
			String nam = yearFormatter.format(Year.of(yearChooser.getYear()));
			ArrayList<TienLuongNV> dsTL = dao_TL.getDanhSachTienLuongTheoThang(thang, nam);
			if (dsTL.size() == 0) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy!");
			} else {
				model.getDataVector().removeAllElements();
				loadDanhSachNhanVienTheoThang(monthChooser.getMonth(), yearChooser.getYear());
			}
		}else if(o.equals(btnExcel)) {
			xuatFileExcel();
		}
	}
}
