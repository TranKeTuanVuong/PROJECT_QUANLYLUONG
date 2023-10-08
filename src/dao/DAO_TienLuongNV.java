package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Stack;

import connect.ConnectDB;
import entity.ChamCongCN;
import entity.ChamCongNV;
import entity.CongNhan;
import entity.NhanVien;
import entity.TienLuongNV;

public class DAO_TienLuongNV {
	public ArrayList<TienLuongNV> getAllTienLuongNV() {
		ArrayList<TienLuongNV> dsTienLuong = new ArrayList<TienLuongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM TienLuongNV ";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuong = rs.getString("maLuongNV");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongLuong = rs.getDouble("tongLuong");
				String maNV = rs.getString("nhanVien");
				NhanVien nv = new DAO_NhanVien().timNV(maNV);
				TienLuongNV tl = new TienLuongNV(maLuong, thangLuong, tienLuong, tienThuong, tienPhat, tongLuong, nv);
				dsTienLuong.add(tl);
			}
			return dsTienLuong;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<TienLuongNV> getDanhSachTienLuongTheoThang(String thang, String nam) {
		ArrayList<TienLuongNV> dsTL = new ArrayList<TienLuongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *from TienLuongNV " + "WHERE month(thangLuong)='" + thang + "' and year(thangLuong)='"
					+ nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuong = rs.getString("maLuongNV");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongTien = rs.getDouble("tongLuong");
				String maNV = rs.getNString("nhanVien");
				NhanVien nv = new NhanVien(maNV);
				TienLuongNV tl = new TienLuongNV(maLuong, thangLuong, tienLuong, tienThuong, tienPhat, tongTien, nv);
				dsTL.add(tl);
			}
			return dsTL;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public TienLuongNV getTienLuongNVTheoThangNam(String maNV, String thang, String nam) {
		TienLuongNV tl = new TienLuongNV();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *from TienLuongNV " + "WHERE month(thangLuong)='" + thang + "' and year(thangLuong)='"
					+ nam + "' and nhanVien='" + maNV + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuong = rs.getString("maLuongNV");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongTien = rs.getDouble("tongLuong");
				String manv = rs.getNString("nhanVien");
				NhanVien nv = new NhanVien(manv);
				tl = new TienLuongNV(maLuong, thangLuong, tienLuong, tienThuong, tienPhat, tongTien, nv);
			}
			return tl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean themTienLuongNV(TienLuongNV tlnv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into TienLuongNV\r\n" + "values(?,?,?,?,?,?,?)";

			statement = con.prepareStatement(sql);
			statement.setString(1, tlnv.getMaLuongNV());
			statement.setDate(2, (Date) tlnv.getThangLuong());
			statement.setDouble(3, tlnv.getTienLuong());
			statement.setDouble(4, tlnv.getTienThuong());
			statement.setDouble(5, tlnv.getTienPhat());
			statement.setDouble(6, tlnv.getTongLuong());
			statement.setString(7, tlnv.getNhanVien().getMaNhanVien());
//			statement.setString(8, tlnv.getChamCongNV().getMaChamCong());
			int kq = statement.executeUpdate();
			statement.close();
			if (kq == 1) {
				return true;
			} else {
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	public boolean capNhatTienLuong(Date ngayThang, String thangLuong, double tienLuong, double tienThuong,
			double tienPhat, double tongTien, String nhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "update TienLuongNV set tienLuong=?, tienThuong=?, tienPhat=?, tongLuong=?, thangLuong=? where nhanVien='"
					+ nhanVien + "' and month(thangLuong)='" + thangLuong + "'";
			statement = con.prepareStatement(sql);
			statement.setDouble(1, tienLuong);
			statement.setDouble(2, tienThuong);
			statement.setDouble(3, tienPhat);
			statement.setDouble(4, tongTien);
			statement.setDate(5, ngayThang);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public TienLuongNV timTLNV(String maTL) {
		ArrayList<TienLuongNV> list = new DAO_TienLuongNV().getAllTienLuongNV();
		for (TienLuongNV dto_TL : list) {
			if (dto_TL.getMaLuongNV().equals(maTL)) {
				return dto_TL;
			}
		}
		return null;
	}

	public ArrayList<TienLuongNV> getDSNV(String maNV,String thang) {
		ArrayList<TienLuongNV> dsTienLuongNV = new ArrayList<TienLuongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM TienLuongNV where nhanVien='" + maNV + "' and month(thangLuong)='"+thang+"'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuong = rs.getString("maLuongNV");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongLuong = rs.getDouble("tongLuong");
				String manv = rs.getString("nhanVien");
				NhanVien nv = new DAO_NhanVien().timNV(manv);
				TienLuongNV tl = new TienLuongNV(maLuong, thangLuong, tienLuong, tienThuong, tienPhat, tongLuong, nv);
				dsTienLuongNV.add(tl);
			}
			return dsTienLuongNV;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public String getAutoMaTienLuong() {
		int soLuong = 0;
		String so = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT count(1) FROM TienLuongNV";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				so = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		soLuong = Integer.parseInt(so);
		soLuong++;
		if (soLuong < 10) {
			so = "TLNV0" + soLuong;
		} else {
			so = "TLNV" + soLuong;
		}
		return so;
	}
}
