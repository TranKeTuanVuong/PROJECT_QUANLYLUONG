package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import connect.ConnectDB;
import entity.PhongBan;

public class DAO_ThongKe {
	
	public Double getTongLuong(String month) {
		Double tongLuong = null;
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		try {
			String sql="select TongLuong = SUM(TLCN.tongLuong + TLNV.tongLuong) from TienLuongCN as TLCN join TienLuongNV as TLNV on MONTH(TLCN.thangLuong) = MONTH(TLNV.THANGLUONG)\r\n"
					+ "where month(TLCN.thangLuong) = ?";
			PreparedStatement stm;
			stm=con.prepareStatement(sql);
			
			stm.setString(1, month);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				tongLuong = rs.getDouble("TongLuong");
				
			}
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tongLuong;
		
	}public Double getTongsp(String month) {
		Double tongLuong = null;
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		try {
			String sql="select TongSOSP = SUM(SOSPHOANTHANH) from ChamCongCN\r\n"
					+ "WHERE MONTH(NGAYCN) = ?";
			PreparedStatement stm;
			stm=con.prepareStatement(sql);
			
			stm.setString(1, month);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				tongLuong = rs.getDouble("TongSOSP");
				
			}
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tongLuong;
		
	}
	public Double getTongLuongNam(String month , String year) {
		Double tongLuong = null;
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		try {
			String sql="select TongLuong = SUM(TLCN.tongLuong + TLNV.tongLuong) from TienLuongCN as TLCN join TienLuongNV as TLNV on MONTH(TLCN.thangLuong) = MONTH(TLNV.THANGLUONG)\r\n"
					+ "where month(TLCN.thangLuong) = ? and year(TLCN.thangLuong) = ?";
			PreparedStatement stm;
			stm=con.prepareStatement(sql);
			
			stm.setString(1, month);
			stm.setString(2, year);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				tongLuong = rs.getDouble("TongLuong");
				
			}
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tongLuong;
		
	}
	public Double getTongSPNam(String month , String year) {
		Double tongLuong = null;
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		try {
			String sql="select TongSOSP = SUM(SOSPHOANTHANH) from ChamCongCN\r\n"
					+ "WHERE MONTH(NGAYCN) = ? And Year(ngaycn) = ?";
			PreparedStatement stm;
			stm=con.prepareStatement(sql);
			
			stm.setString(1, month);
			stm.setString(2, year);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				tongLuong = rs.getDouble("TongSOSP");
				
			}
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tongLuong;
		
	}
	
	public Double getThongKe(String month , String year) {
		Double tongLuong = null;
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		try {
			String sql="select TongLuong = SUM(TLCN.tongLuong + TLNV.tongLuong) , TONGSP = SUM(SOSPHOANTHANH)  from TienLuongCN as TLCN join TienLuongNV as TLNV on MONTH(TLCN.thangLuong) = MONTH(TLNV.THANGLUONG) join ChamCongCN as CCCN  ON MONTH(TLCN.THANGLUONG) = MONTH(CCCN.NGAYCN)\r\n"
					+ "					where month(TLCN.thangLuong) = ? and year(TLCN.thangLuong) = ?\r\n"
					+ "";
			PreparedStatement stm;
			stm=con.prepareStatement(sql);
			
			stm.setString(1, month);
			stm.setString(2, year);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				tongLuong = rs.getDouble("TongSOSP");
				
			}
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tongLuong;
		
	}
	
}
