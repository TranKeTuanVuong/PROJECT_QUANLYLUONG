package boundary;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.DAO_ThongKe;

public class pnlThongKeLuongThang extends JPanel {
	JPanel pnChart;
	JPanel pnJfreeChart;
	DAO_ThongKe dao_ThongKe = new DAO_ThongKe();
	String tongSPString = "";
	double tongSP;
	String pattern = "#.###";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	String tienTe = "";
	double tien;

	/**
	 * Create the panel.
	 * 
	 * @throws InterruptedException
	 */
	public pnlThongKeLuongThang() {
		setLayout(null);

		pnChart = new JPanel();
		add(pnChart);
		pnChart.setBounds(53, 10, 756, 434);

		CategoryDataset dataset = createDataset();

		// Create chart
		JFreeChart chart = ChartFactory.createBarChart("Biểu đồ thống kê lương", // Chart Title
				"Tháng", // Category axis
				"Tổng số tính theo triệu", // Value axis
				dataset, PlotOrientation.VERTICAL, true, true, false);
		pnChart.setLayout(new BorderLayout(0, 0));

		ChartPanel chartPanel = new ChartPanel(chart);
		pnChart.add(chartPanel);

	}

	private CategoryDataset createDataset() {
		Date date = new Date();
		int monthString = date.getMonth();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// Population in Thang 9
		tienTe = decimalFormat.format(dao_ThongKe.getTongLuong((monthString - 2) + "") / 1000000);
		tien = Double.parseDouble(tienTe);
		tongSPString = decimalFormat.format(dao_ThongKe.getTongsp((monthString - 2) + "") / 1000000);
		tongSP = Double.parseDouble(tongSPString);

		dataset.addValue(tien, "Tổng Lương", "Tháng " + (monthString - 2) + "");
		dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng " + (monthString - 2) + "");

		tienTe = decimalFormat.format(dao_ThongKe.getTongLuong((monthString - 1) + "") / 1000000);
		tien = Double.parseDouble(tienTe);
		tongSPString = decimalFormat.format(dao_ThongKe.getTongsp((monthString - 1) + "") / 1000000);
		tongSP = Double.parseDouble(tongSPString);

		// Population in Thang 10
		dataset.addValue(tien, "Tổng Lương", "Tháng " + (monthString - 1) + "");
		dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng " + (monthString - 1) + "");

		// Population in Thang 11
		tienTe = decimalFormat.format(dao_ThongKe.getTongLuong((monthString) + "") / 1000000);
		tien = Double.parseDouble(tienTe);
		tongSPString = decimalFormat.format(dao_ThongKe.getTongsp((monthString) + "") / 1000000);
		tongSP = Double.parseDouble(tongSPString);

		dataset.addValue(tien, "Tổng Lương", "Tháng " + (monthString) + "");
		dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng " + (monthString) + "");

		return dataset;
	}
}
