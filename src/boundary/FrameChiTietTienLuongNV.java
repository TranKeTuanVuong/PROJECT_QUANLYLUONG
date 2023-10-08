package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.BaseFont;

import entity.NhanVien;
import entity.TienLuongNV;

public class FrameChiTietTienLuongNV extends JFrame {

	public FrameChiTietTienLuongNV(TienLuongNV tl, NhanVien nv, int soNgayDiLam, int soGioTangCa, int soNgayNghiCoPhep,
			int soNgayNghiKhongPhep, String thang, String nam) {
		setSize(750, 413);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setBounds(401, 108, 85, 23);
		getContentPane().add(lblTenNV);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setBounds(50, 109, 85, 21);
		getContentPane().add(lblMaNV);

		JLabel lblSoNgayLam = new JLabel("Số ngày đi làm:");
		lblSoNgayLam.setBounds(50, 161, 102, 23);
		getContentPane().add(lblSoNgayLam);

		JLabel lblTangCa = new JLabel("Số giờ tăng ca:");
		lblTangCa.setBounds(401, 163, 96, 18);
		getContentPane().add(lblTangCa);

		JLabel lblCoPhep = new JLabel("Số ngày nghỉ có phép:");
		lblCoPhep.setBounds(50, 216, 134, 23);
		getContentPane().add(lblCoPhep);

		JLabel lblKhongPhep = new JLabel("Số ngày nghỉ không phép:");
		lblKhongPhep.setBounds(401, 213, 154, 29);
		getContentPane().add(lblKhongPhep);

		JLabel lblTienLuong = new JLabel("Tiền lương:");
		lblTienLuong.setBounds(50, 271, 85, 23);
		getContentPane().add(lblTienLuong);

		JLabel lblTienThuong = new JLabel("Tiền thưởng:");
		lblTienThuong.setBounds(401, 273, 85, 18);
		getContentPane().add(lblTienThuong);

		JLabel lblTienPhat = new JLabel("Tiền phạt:");
		lblTienPhat.setBounds(50, 324, 85, 21);
		getContentPane().add(lblTienPhat);

		JLabel lblTongTien = new JLabel("Tổng tiền lương:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTongTien.setBounds(401, 311, 129, 42);
		getContentPane().add(lblTongTien);

		JLabel txtMaNV = new JLabel("New label");
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMaNV.setBounds(194, 108, 109, 21);
		txtMaNV.setText(nv.getMaNhanVien());
		getContentPane().add(txtMaNV);

		JLabel txtTenNV = new JLabel("New label");
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTenNV.setBounds(577, 107, 154, 23);
		txtTenNV.setText(nv.getTenNhanVien());
		getContentPane().add(txtTenNV);

		JLabel txtNgayDiLam = new JLabel("New label");
		txtNgayDiLam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNgayDiLam.setBounds(194, 161, 85, 21);
		txtNgayDiLam.setText(String.valueOf(soNgayDiLam));
		getContentPane().add(txtNgayDiLam);

		JLabel txtCoPhep = new JLabel("New label");
		txtCoPhep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCoPhep.setBounds(194, 216, 85, 21);
		txtCoPhep.setText(String.valueOf(soNgayNghiCoPhep));
		getContentPane().add(txtCoPhep);

		JLabel txtTienLuong = new JLabel("New label");
		txtTienLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTienLuong.setBounds(194, 271, 85, 21);
		String tienLuong = chuyenDoiDinhDangTienTe(tl.getTienLuong());
		txtTienLuong.setText(tienLuong);
		getContentPane().add(txtTienLuong);

		JLabel txtPhat = new JLabel("New label");
		txtPhat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPhat.setBounds(194, 323, 85, 20);
		String tienPhat = chuyenDoiDinhDangTienTe(tl.getTienPhat());
		txtPhat.setText(tienPhat);
		getContentPane().add(txtPhat);

		JLabel txtTangCa = new JLabel("New label");
		txtTangCa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTangCa.setBounds(577, 161, 109, 21);
		txtTangCa.setText(String.valueOf(soGioTangCa));
		getContentPane().add(txtTangCa);

		JLabel txtKhongPhep = new JLabel("New label");
		txtKhongPhep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtKhongPhep.setBounds(577, 216, 85, 21);
		txtKhongPhep.setText(String.valueOf(soNgayNghiKhongPhep));
		getContentPane().add(txtKhongPhep);

		JLabel txtTienThuong = new JLabel("New label");
		txtTienThuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTienThuong.setBounds(577, 271, 85, 21);
		String tienThuong = chuyenDoiDinhDangTienTe(tl.getTienThuong());
		txtTienThuong.setText(tienThuong);
		getContentPane().add(txtTienThuong);

		JLabel txtTongTien = new JLabel("New label");
		txtTongTien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		txtTongTien.setBounds(577, 313, 154, 38);
		String tongTien = chuyenDoiDinhDangTienTe(tl.getTongLuong());
		txtTongTien.setText(tongTien);
		getContentPane().add(txtTongTien);

		JLabel lblTenCty = new JLabel("CÔNG TY MAY MẶC PAYROLL");
		lblTenCty.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenCty.setForeground(Color.DARK_GRAY);
		lblTenCty.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenCty.setBounds(201, 12, 314, 22);
		getContentPane().add(lblTenCty);

		JLabel lblTitle = new JLabel("Phiếu Thanh Toán Lương");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitle.setBounds(269, 44, 201, 22);
		getContentPane().add(lblTitle);

		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setForeground(Color.GRAY);
		lblThang.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblThang.setBounds(300, 68, 44, 22);
		getContentPane().add(lblThang);

		JLabel txtThang = new JLabel("<dynamic>");
		txtThang.setForeground(Color.GRAY);
		txtThang.setFont(new Font("Tahoma", Font.ITALIC, 10));
		txtThang.setBounds(339, 69, 123, 21);
		getContentPane().add(txtThang);
		txtThang.setText(thang);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setForeground(Color.GRAY);
		lblNam.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNam.setBounds(371, 68, 44, 22);
		getContentPane().add(lblNam);

		JLabel txtNam = new JLabel("<dynamic>");
		txtNam.setForeground(Color.GRAY);
		txtNam.setFont(new Font("Tahoma", Font.ITALIC, 10));
		txtNam.setBounds(401, 68, 129, 23);
		getContentPane().add(txtNam);
		txtNam.setText(nam);

		JButton btnXuatPDF = new JButton("Xuất PDF");
		btnXuatPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String FILE_NAME = "T:\\luongCN\\" + nv.getMaNhanVien() + nv.getTenNhanVien() + ".pdf";
					PdfWriter pdfWriter = new PdfWriter(FILE_NAME);
					PdfDocument pdfDocument = new PdfDocument(pdfWriter);
					Document document = new Document(pdfDocument);
					pdfDocument.setDefaultPageSize(PageSize.A4);


					String khoangTrang = "                             ";
					String tenCTY = "                                 CÔNG TY MAY MẶC PAYROLL";
					String tenPhieu = "                                                   Phiếu thanh toán lương";
					String thangLuong = ("                                                                    Tháng "
							+ thang + " Năm " + nam);
					String maNVvaTenNV = "        Mã Nhân Viên:  " + nv.getMaNhanVien() + khoangTrang
							+ "               Tên Nhân Viên:  " + nv.getTenNhanVien();
					String diLamVaTangCa = "        Số ngày đi làm:  " + soNgayDiLam + khoangTrang
							+ "                       Số giờ tăng ca:  " + soGioTangCa;
					String coPhepKhPhep = "        Số ngày nghỉ có phép:  " + soNgayNghiCoPhep
							+ "                                         Số ngày nghỉ không phép:  "
							+ soNgayNghiKhongPhep;
					String tienLuongTienThuong = "        Tiền lương:  " + chuyenDoiDinhDangTienTe(tl.getTienLuong())
							+ khoangTrang + "            Tiền thưởng:  " + chuyenDoiDinhDangTienTe(tl.getTienThuong());
					String tienPhat = "        Tiền phạt:  " + chuyenDoiDinhDangTienTe(tl.getTienPhat());
					String tongLuong = "                                                                        Tổng tiền lương:  "
							+ chuyenDoiDinhDangTienTe(tl.getTongLuong());
					String xuongdong = " ";
					String sign = "        Chữ ký xác nhận của cơ quan" + khoangTrang
							+ "                                            Chữ ký người nhận";

					String image = "src//image//sign.png";
					ImageData imagedata = ImageDataFactory.create(image);
					Image logo = new Image(imagedata);
					logo.setMarginLeft(62f);

					PdfFont font18_bold = PdfFontFactory.createFont("c:\\windows\\fonts\\times.ttf",
							BaseFont.IDENTITY_H, true);

					document.add(new Paragraph(tenCTY).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setTextAlignment(TextAlignment.CENTER)
							.setFontSize(20));
					document.add(new Paragraph(tenPhieu).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setTextAlignment(TextAlignment.CENTER)
							.setFontSize(18));
					document.add(new Paragraph(thangLuong).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setTextAlignment(TextAlignment.CENTER));
					document.add(new Paragraph(xuongdong).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK));
					document.add(new Paragraph(xuongdong).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK));
					document.add(new Paragraph(maNVvaTenNV).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(diLamVaTangCa).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(coPhepKhPhep).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(tienLuongTienThuong).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(tienPhat).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(tongLuong).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setTextAlignment(TextAlignment.RIGHT)
							.setFontSize(18));
					document.add(new Paragraph(sign).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setFontSize(10).setMarginLeft(62f).setItalic());
					document.add(new Paragraph(xuongdong).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK));
					document.add(logo);

					// close
					document.close();
					JOptionPane.showMessageDialog(null, "Đã Lưu");

				} catch (FileNotFoundException /* | DocumentException */ e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnXuatPDF.setIcon(new ImageIcon(FrameChiTietTienLuongNV.class.getResource("/image/PDF-icon.png")));
		btnXuatPDF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXuatPDF.setBackground(Color.CYAN);
		btnXuatPDF.setBounds(578, 40, 134, 33);
		btnXuatPDF.setBorderPainted(false);
		getContentPane().add(btnXuatPDF);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameChiTietTienLuongNV.class.getResource("/image/Cash-icon.png")));
		lblNewLabel.setBounds(499, 5, 37, 34);
		getContentPane().add(lblNewLabel);

	}

	private String chuyenDoiDinhDangTienTe(double soTien) {
		Locale localeVn = new Locale("vi", "VN");
		NumberFormat vn = NumberFormat.getCurrencyInstance(localeVn);
		return vn.format(soTien);
	}
}
