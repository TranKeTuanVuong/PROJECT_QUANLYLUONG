package boundary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import dao.DAO_ThongKe;

public class DiaLogThongKe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	String pattern = "###,###,###.###";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	 String tienTe = "";
	 String tongSPString = "";
	 double tongSP;
	   double tien;
	   int monthString;
	   DAO_ThongKe dao_ThongKe = new DAO_ThongKe();
		 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DiaLogThongKe dialog = new DiaLogThongKe();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public DiaLogThongKe(int btnChiTietIndex) {
		
		try {
			connect.ConnectDB.getInstance().disconnect();
			connect.ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		setBounds(100, 100, 714, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		Date date = new Date();
		monthString = date.getMonth();
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
	  
	    // Population in Thang 9  
	    tien = dao_ThongKe.getTongLuong((monthString - 2) + "");
	   
	    tongSP = dao_ThongKe.getTongsp((monthString - 2) + "");
	    
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
				if(btnChiTietIndex == 1) {
					table.setModel(new DefaultTableModel(
							new Object[][] {
								{monthString - 2 + "",decimalFormat.format(dao_ThongKe.getTongLuong((monthString - 2) + "")) ,  decimalFormat.format(dao_ThongKe.getTongsp((monthString - 2) + ""))},
								{monthString - 1 + "",decimalFormat.format(dao_ThongKe.getTongLuong((monthString - 1) + "")) ,  decimalFormat.format(dao_ThongKe.getTongsp((monthString - 1) + ""))},
								{monthString + "",decimalFormat.format(dao_ThongKe.getTongLuong((monthString) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((monthString) + ""))},
							},
							new String[] {
								"Th\u00E1ng", "T\u1ED5ng L\u01B0\u01A1ng", "T\u1ED5ng S\u1ED1 S\u1EA3n Ph\u1EA9m Ho\u00E0n Th\u00E0nh"
							}
						));
				}else {
					table.setModel(new DefaultTableModel(
							new Object[][] {
								{1 + "",decimalFormat.format(dao_ThongKe.getTongLuong((1) + "")) ,  decimalFormat.format(dao_ThongKe.getTongsp((1) + ""))},
								{2 + "",decimalFormat.format(dao_ThongKe.getTongLuong((2) + "")) ,  decimalFormat.format(dao_ThongKe.getTongsp((2) + ""))},
								{3 + "",decimalFormat.format(dao_ThongKe.getTongLuong(3 + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((3) + ""))},
								{4 + "",decimalFormat.format(dao_ThongKe.getTongLuong((4) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((4) + ""))},
								{5 + "",decimalFormat.format(dao_ThongKe.getTongLuong((5) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((5) + ""))},
								{6 + "",decimalFormat.format(dao_ThongKe.getTongLuong((6) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((6) + ""))},
								{7 + "",decimalFormat.format(dao_ThongKe.getTongLuong((7) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((7) + ""))},
								{8 + "",decimalFormat.format(dao_ThongKe.getTongLuong((8) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((8) + ""))},
								{9 + "",decimalFormat.format(dao_ThongKe.getTongLuong((9) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((9) + ""))},
								{10 + "",decimalFormat.format(dao_ThongKe.getTongLuong((10) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((10) + ""))},
								{11 + "",decimalFormat.format(dao_ThongKe.getTongLuong((11) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((11) + ""))},
								{12 + "",decimalFormat.format(dao_ThongKe.getTongLuong((12) + "")) ,  decimalFormat.format( dao_ThongKe.getTongsp((12) + ""))},
								
							},
							new String[] {
								"Th\u00E1ng", "T\u1ED5ng L\u01B0\u01A1ng", "T\u1ED5ng S\u1ED1 S\u1EA3n Ph\u1EA9m Ho\u00E0n Th\u00E0nh"
							}
						));
				}
				table.getColumnModel().getColumn(1).setPreferredWidth(158);
				table.getColumnModel().getColumn(2).setPreferredWidth(259);
			}
		}
		{
			
		}
	}

}
