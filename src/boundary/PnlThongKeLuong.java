package boundary;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JYearChooser;

import dao.DAO_ThongKe;

public class PnlThongKeLuong extends JPanel implements ActionListener {
	JPanel pnChart;
	JComboBox comboBox;
	JPanel pnlCombobox;
	JPanel pnJfreeChart;
	JPanel pnlYear;
	JYearChooser yearChooser;
	DAO_ThongKe dao_ThongKe = new DAO_ThongKe();
	int btnChiTietIndex = 0;
	 
	String pattern = "#.###";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	 String tienTe = "";
	 String tongSPString = "";
	 double tongSP;
	   double tien;
	   int monthString;
	
	/**
	 * Create the panel.
	 * @throws InterruptedException 
	 */
	public PnlThongKeLuong() {
		
		setLayout(null);
		
		try {
			connect.ConnectDB.getInstance().disconnect();
			connect.ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		pnJfreeChart = new JPanel();
		pnJfreeChart.setBounds(0, 41, 819, 454);
		add(pnJfreeChart);
		pnJfreeChart.setLayout(null);
		
		pnChart = new JPanel();
		pnChart.setBounds(53, 10, 756, 434);
		pnJfreeChart.add(pnChart);
		
		 CategoryDataset dataset = createDataset();  
	      
		    //Create chart  
		    JFreeChart chart=ChartFactory.createBarChart(  
		        "Biểu đồ thống kê lương", //Chart Title  
		        "Tháng", // Category axis  
		        "Tổng số tính tỉ lệ tiền tỉ", // Value axis  
		        dataset,  
		        PlotOrientation.VERTICAL,  
		        true,true,true  
		       );
		    pnChart.setLayout(new BorderLayout(0, 0));
		    
		    ChartPanel chartPanel = new ChartPanel(chart);
		    pnChart.add(chartPanel);;
		   
		   pnlCombobox = new JPanel();
		   pnlCombobox.setBounds(153, 0, 520, 31);
		   add(pnlCombobox);
		   
		   comboBox = new JComboBox();
		   comboBox.setModel(new DefaultComboBoxModel(new String[] {"Thống kê 3 tháng gần nhất", "Thống kê theo năm"}));
		   pnlCombobox.add(comboBox);
		    yearChooser = new JYearChooser();
		   comboBox.addActionListener(this);
		   JPanel pnBtnChart = new JPanel();
		   pnBtnChart.setBounds(820, 452, 110, 31);
		   add(pnBtnChart);
		   btnChiTietIndex = 1;
		   JButton btnChiTiet1 = new JButton("Xem chi tiết");
		   pnBtnChart.add(btnChiTiet1);
		   btnChiTiet1.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent e) {
		   		DiaLogThongKe log = new DiaLogThongKe(btnChiTietIndex);
		   		log.setVisible(true);
		   	}
		   });
		   btnChiTiet1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		   pnlYear = new JPanel();
		   pnlYear.setBounds(683, 0, 103, 30);
		   add(pnlYear);
		   pnlYear.add(yearChooser);
		   yearChooser.setBounds(259, 61, 67, 22);
	}
	
	
	private CategoryDataset createDataset() {  
		Date date = new Date();
		monthString = date.getMonth();
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
	  
	    // Population in Thang 9  
	    tienTe = decimalFormat.format(dao_ThongKe.getTongLuong((monthString - 2) + "")/ 1000000);
	    tien = Double.parseDouble(tienTe);
	    tongSPString = decimalFormat.format(dao_ThongKe.getTongsp((monthString - 2) + "")/ 1000000);
	    tongSP = Double.parseDouble(tongSPString);
	    dataset.addValue(tien, "Tổng Lương", "Tháng " + (monthString - 2) + "");  
	    dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng " + (monthString - 2) + "");  
	    
	    tienTe = decimalFormat.format(dao_ThongKe.getTongLuong((monthString - 1) + "")/ 1000000);
	    tien = Double.parseDouble(tienTe);
	    tongSPString = decimalFormat.format(dao_ThongKe.getTongsp((monthString - 1) + "")/ 1000000);
	    tongSP = Double.parseDouble(tongSPString);
	  
	    // Population in Thang 10
	    dataset.addValue(tien, "Tổng Lương", "Tháng " +  (monthString - 1) + "");  
	    dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng " +  (monthString - 1) + "");  
	    
	  
	    // Population in Thang 11
	     tienTe = decimalFormat.format(dao_ThongKe.getTongLuong((monthString) + "")/ 1000000);
	     tien = Double.parseDouble(tienTe);
	     tongSPString = decimalFormat.format(dao_ThongKe.getTongsp((monthString) + "")/ 1000000);
		 tongSP = Double.parseDouble(tongSPString);
		 
	    dataset.addValue(tien, "Tổng Lương", "Tháng " +  (monthString) + "");  
	    dataset.addValue(tongSP, "Tổng Số Sản Phẩm Hoàn Thành", "Tháng " +  (monthString) + "");  
	   
	  
	    return dataset;  
	  }  
	
	
	private void showPnl(JPanel panel) {
		//pnTemp = panel;
		panel.setBounds(0, 0, 820, 500);
		pnJfreeChart.removeAll();
		pnJfreeChart.add(panel);
		pnJfreeChart.validate();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if(object.equals(comboBox)) {
			int i = 1;
			if(comboBox.getSelectedIndex() == 0) {
				showPnl(new pnlThongKeLuongThang());
				btnChiTietIndex = 1;
			} else if(comboBox.getSelectedIndex() == 1) {
				
//				System.out.print(comboBox.getSelectedItem().toString());
				showPnl(new PnlThongKeNam(yearChooser.getYear()+""));
				btnChiTietIndex = 2;
				
				
				
			}
		}
	}
}
