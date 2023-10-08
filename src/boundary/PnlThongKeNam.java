package boundary;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.DAO_ThongKe;

public class PnlThongKeNam extends JPanel {
	JPanel pnChart;
	JPanel pnJfreeChart;
	DAO_ThongKe dao_ThongKe = new DAO_ThongKe();

	String pattern = "#.###";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	String tienTe = "";
	double tien;
	double tienBaThang;
	String tongSPString = "";
	double tongSP;
	double tongSPBaThang;

	/**
	 * Create the panel.
	 * 
	 * @throws InterruptedException
	 */
	public PnlThongKeNam(String year) {
		setLayout(null);

		pnChart = new JPanel();
		add(pnChart);
		pnChart.setBounds(53, 10, 756, 434);

		CategoryDataset dataset = createDataset(year);

		// Create chart
		JFreeChart chart = ChartFactory.createBarChart("Biểu đồ thống kê lương", // Chart Title
				"Tháng", // Category axis
				"Tổng số tính theo triệu", // Value axis
				dataset, PlotOrientation.VERTICAL, true, true, false);
		pnChart.setLayout(new BorderLayout(0, 0));

		ChartPanel chartPanel = new ChartPanel(chart);
		pnChart.add(chartPanel);

	}

	private CategoryDataset createDataset(String Year) {
		tienBaThang = 0.0;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		tienBaThang = dao_ThongKe.getTongLuongNam(1 + "", Year) + dao_ThongKe.getTongLuongNam(2 + "", Year)
				+ dao_ThongKe.getTongLuongNam(3 + "", Year);
		tienTe = decimalFormat.format(tienBaThang / 1000000);
		tien = Double.parseDouble(tienTe);

		tongSPBaThang = dao_ThongKe.getTongSPNam(1 + "", Year) + dao_ThongKe.getTongSPNam(2 + "", Year)
				+ dao_ThongKe.getTongSPNam(3 + "", Year);
		tongSPString = decimalFormat.format(tongSPBaThang / 1000000);
		tongSP = Double.parseDouble(tongSPString);

		dataset.addValue(tien, "Tổng Lương", "Tháng 1 - 3");
		dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng 1 - 3");

		tienBaThang = dao_ThongKe.getTongLuongNam(4 + "", Year) + dao_ThongKe.getTongLuongNam(5 + "", Year)
				+ dao_ThongKe.getTongLuongNam(6 + "", Year);
		tienTe = decimalFormat.format(tienBaThang / 1000000);
		tien = Double.parseDouble(tienTe);

		tongSPBaThang = dao_ThongKe.getTongSPNam(4 + "", Year) + dao_ThongKe.getTongSPNam(5 + "", Year)
				+ dao_ThongKe.getTongSPNam(6 + "", Year);
		tongSPString = decimalFormat.format(tongSPBaThang / 1000000);
		tongSP = Double.parseDouble(tongSPString);
		// Population in Thang 10
		dataset.addValue(tien, "Tổng Lương", "Tháng 4 - 6");
		dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng 4 - 6");

		// Population in Thang 11
		tienBaThang = dao_ThongKe.getTongLuongNam(7 + "", Year) + dao_ThongKe.getTongLuongNam(8 + "", Year)
				+ dao_ThongKe.getTongLuongNam(9 + "", Year);
		tienTe = decimalFormat.format(tienBaThang / 1000000);
		tien = Double.parseDouble(tienTe);

		tongSPBaThang = dao_ThongKe.getTongSPNam(7 + "", Year) + dao_ThongKe.getTongSPNam(8 + "", Year)
				+ dao_ThongKe.getTongSPNam(9 + "", Year);
		tongSPString = decimalFormat.format(tongSPBaThang / 1000000);
		tongSP = Double.parseDouble(tongSPString);

		dataset.addValue(tien, "Tổng Lương", "Tháng 7 - 9");
		dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng 7 - 9");

		// Population in Thang 11
		tienBaThang = dao_ThongKe.getTongLuongNam(10 + "", Year) + dao_ThongKe.getTongLuongNam(11 + "", Year)
				+ dao_ThongKe.getTongLuongNam(12 + "", Year);
		tienTe = decimalFormat.format(tienBaThang / 1000000);
		tien = Double.parseDouble(tienTe);

		tongSPBaThang = dao_ThongKe.getTongSPNam(10 + "", Year) + dao_ThongKe.getTongSPNam(11 + "", Year)
				+ dao_ThongKe.getTongSPNam(12 + "", Year);
		tongSPString = decimalFormat.format(tongSPBaThang / 1000000);
		tongSP = Double.parseDouble(tongSPString);

		dataset.addValue(tien, "Tổng Lương", "Tháng 10 - 12");
		dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng 10 - 12");

		return dataset;
	}
}
