package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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

public class DialogCTLuongCN extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTenCty, lblTitle, lblThangLuong, lblThang, lblNamLuong, lblNam, lblMaCN, lblTenCN, lblTongSP,
			lblCongDoan, lblSanPham, lblTienLuong, lblThuongVuot, lblTienPhat, lblPhuCap, lblTongLuong, MaCN, TenCN,
			TongSPHT, CongDoan, TenSP, TienLuong, TienThuongVuot, TienPhat, PhuCap, TongLuong;

	/**
	 * Create the dialog.
	 */
	public DialogCTLuongCN(String maCN, String tenCN, String congDoanSX, String tenSP, int tongSPHT, double tienLuong,
			double tienThuong, double phuCap, double tienPhat, double tongLuong, String thang, String nam) {
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogCTLuongCN.class.getResource("/image/Cash-icon.png")));
		setBounds(100, 100, 667, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblTenCty = new JLabel("CÔNG TY MAY MẶC PAYROLL");
		lblTenCty.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenCty.setForeground(new Color(64, 64, 64));
		lblTenCty.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTenCty.setBounds(180, 11, 314, 22);
		contentPanel.add(lblTenCty);

		lblTitle = new JLabel("Phiếu Thanh Toán Lương");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitle.setBounds(228, 44, 201, 22);
		contentPanel.add(lblTitle);

		lblThangLuong = new JLabel("Tháng");
		lblThangLuong.setForeground(new Color(0, 250, 154));
		lblThangLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThangLuong.setBounds(262, 77, 44, 22);
		contentPanel.add(lblThangLuong);

		lblThang = new JLabel(thang);
		lblThang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThang.setBounds(300, 79, 27, 19);
		contentPanel.add(lblThang);

		lblNamLuong = new JLabel("Năm");
		lblNamLuong.setForeground(new Color(0, 255, 255));
		lblNamLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNamLuong.setBounds(334, 77, 36, 22);
		contentPanel.add(lblNamLuong);

		lblNam = new JLabel(nam);
		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNam.setBounds(363, 79, 49, 19);
		contentPanel.add(lblNam);

		lblMaCN = new JLabel("Mã CN:");
		lblMaCN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaCN.setBounds(22, 120, 56, 22);
		contentPanel.add(lblMaCN);

		lblTenCN = new JLabel("Tên công nhân:");
		lblTenCN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTenCN.setBounds(22, 151, 107, 22);
		contentPanel.add(lblTenCN);

		lblTongSP = new JLabel("Tổng SP hoàn thành: ");
		lblTongSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTongSP.setBounds(22, 184, 134, 22);
		contentPanel.add(lblTongSP);

		lblCongDoan = new JLabel("Công đoạn: ");
		lblCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCongDoan.setBounds(403, 122, 79, 19);
		contentPanel.add(lblCongDoan);

		lblSanPham = new JLabel("Sản phẩm: ");
		lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSanPham.setBounds(403, 153, 79, 19);
		contentPanel.add(lblSanPham);

		lblTienLuong = new JLabel("Tiền lương:");
		lblTienLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTienLuong.setBounds(403, 186, 79, 19);
		contentPanel.add(lblTienLuong);

		lblThuongVuot = new JLabel("Tiền thưởng: ");
		lblThuongVuot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThuongVuot.setBounds(403, 219, 115, 19);
		contentPanel.add(lblThuongVuot);

		lblTienPhat = new JLabel("Tiền phạt:");
		lblTienPhat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTienPhat.setBounds(403, 249, 66, 22);
		contentPanel.add(lblTienPhat);

		lblPhuCap = new JLabel("Phụ cấp: ");
		lblPhuCap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhuCap.setBounds(403, 277, 79, 22);
		contentPanel.add(lblPhuCap);

		lblTongLuong = new JLabel("Tổng lương: ");
		lblTongLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTongLuong.setBounds(403, 310, 91, 26);
		contentPanel.add(lblTongLuong);

		MaCN = new JLabel(maCN);
		MaCN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		MaCN.setBounds(76, 122, 80, 19);
		contentPanel.add(MaCN);

		TenCN = new JLabel(tenCN);
		TenCN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TenCN.setBounds(130, 153, 152, 19);
		contentPanel.add(TenCN);

		TongSPHT = new JLabel(String.valueOf(tongSPHT));
		TongSPHT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TongSPHT.setBounds(159, 186, 143, 19);
		contentPanel.add(TongSPHT);

		CongDoan = new JLabel(congDoanSX);
		CongDoan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		CongDoan.setBounds(491, 122, 170, 19);
		contentPanel.add(CongDoan);

		TenSP = new JLabel(tenSP);
		TenSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TenSP.setBounds(492, 153, 171, 19);
		contentPanel.add(TenSP);

		TienLuong = new JLabel(chuyenDoiDinhDangTienTe(tienLuong));
		TienLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TienLuong.setBounds(492, 186, 169, 19);
		contentPanel.add(TienLuong);

		TienThuongVuot = new JLabel(chuyenDoiDinhDangTienTe(tienThuong));
		TienThuongVuot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TienThuongVuot.setBounds(491, 217, 133, 22);
		contentPanel.add(TienThuongVuot);

		TienPhat = new JLabel(chuyenDoiDinhDangTienTe(tienPhat));
		TienPhat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		TienPhat.setBounds(491, 249, 152, 22);
		contentPanel.add(TienPhat);

		PhuCap = new JLabel(chuyenDoiDinhDangTienTe(phuCap * 1000));
		PhuCap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		PhuCap.setBounds(492, 277, 169, 22);
		contentPanel.add(PhuCap);

		TongLuong = new JLabel(chuyenDoiDinhDangTienTe(tongLuong));
		TongLuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		TongLuong.setBounds(502, 312, 159, 26);
		contentPanel.add(TongLuong);

		JButton btnXuatPDF = new JButton("Xuất PDF");
		btnXuatPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String FILE_NAME = "T:\\luongCN\\" + tenCN + ".pdf";
					PdfWriter pdfWriter = new PdfWriter(FILE_NAME);
					PdfDocument pdfDocument = new PdfDocument(pdfWriter);
					Document document = new Document(pdfDocument);
					pdfDocument.setDefaultPageSize(PageSize.A4);

					// thiết kế các văn bản trong file
					String khoangTrang = "                             ";
					String tenCTY = "                                 CÔNG TY MAY MẶC PAYROLL";
					String tenPhieu = "                                                   Phiếu thanh toán lương";
					String thangLuong = ("                                                                    Tháng "
							+ thang + " Năm " + nam);
					String maCNvaCongDoan = "   Mã CN:  " + maCN + khoangTrang
							+ "                              Công đoạn:     " + congDoanSX;
					String tenCNvaSP = "   Tên công nhân:  " + tenCN + khoangTrang + "   Sản phẩm:     " + tenSP;
					String tongSPHTvaTL = "   Tổng SP hoàn thành:  " + tongSPHT
							+ "                                           Tiền lương:     "
							+ chuyenDoiDinhDangTienTe(tienLuong);
					String tienThuongCN =khoangTrang+ "Tiền thưởng:     "
							+ chuyenDoiDinhDangTienTe(tienThuong);
					String tienPhatCN =khoangTrang+ "Tiền phạt:     "
							+ chuyenDoiDinhDangTienTe(tienPhat);
					String phuCapCN =khoangTrang+ "Phụ cấp:     "
							+ chuyenDoiDinhDangTienTe(phuCap);
					String tongLuongCN = "Tổng lương:     "
							+ chuyenDoiDinhDangTienTe(tongLuong);
					String xuongdong = " ";
					String sign = "        Chữ ký xác nhận của cơ quan" + khoangTrang
							+ "                                            Chữ ký người nhận";

					String image = "src//image//sign.png";
					ImageData imagedata = ImageDataFactory.create(image);
					Image logo = new Image(imagedata);
					logo.setMarginLeft(62f);
					// Định dạng kiểu chữ khi xuất file pdf

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
					document.add(new Paragraph(maCNvaCongDoan).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(tenCNvaSP).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(tongSPHTvaTL).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(tienThuongCN).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));
					document.add(new Paragraph(tienPhatCN).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));

					document.add(new Paragraph(phuCapCN).setFont(font18_bold).setBold()
							.setFontColor(new ColorConstants().BLACK).setMarginLeft(62f));

					document.add(new Paragraph(tongLuongCN).setFont(font18_bold).setBold()
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
		btnXuatPDF.setIcon(new ImageIcon(DialogCTLuongCN.class.getResource("/image/PDF-icon.png")));
		btnXuatPDF.setBackground(Color.CYAN);
		btnXuatPDF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXuatPDF.setBounds(22, 304, 134, 33);
		contentPanel.add(btnXuatPDF);
	}

	private String chuyenDoiDinhDangTienTe(double soTien) {
		Locale localeVn = new Locale("vi", "VN");
		NumberFormat vn = NumberFormat.getCurrencyInstance(localeVn);
		return vn.format(soTien);
	}
}
