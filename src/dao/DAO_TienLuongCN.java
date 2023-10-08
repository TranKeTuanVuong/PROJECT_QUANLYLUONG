package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDB;
import entity.CongNhan;
import entity.TienLuongCN;

public class DAO_TienLuongCN {
	public ArrayList<TienLuongCN> getAllTienLuongCN() {
		ArrayList<TienLuongCN> dsTienLuong = new ArrayList<TienLuongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM TienLuongCN ";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuongCN = rs.getString("maLuongCN");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongLuong = rs.getDouble("tongLuong");
				String maCN = rs.getNString("congNhan");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				TienLuongCN tl = new TienLuongCN(maLuongCN, thangLuong, tienLuong, tienThuong, tienPhat, tongLuong, cn);
				dsTienLuong.add(tl);
			}
			return dsTienLuong;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public TienLuongCN getLuongCongNhanTheoMaCongNhan(String ma, String thang) {
		TienLuongCN tl = new TienLuongCN();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM TienLuongCN where congNhan= '" + ma + "'and MONTH(thangLuong) ='"+thang+"'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuongCN = rs.getString("maLuongCN");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongLuong = rs.getDouble("tongLuong");
				String maCN = rs.getNString("congNhan");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				tl = new TienLuongCN(maLuongCN, thangLuong, tienLuong, tienThuong, tienPhat, tongLuong, cn);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tl;
	}
	
	public TienLuongCN getLuongCongNhanTheoMaCongNhanVaNgay(String ma, String ngayLuong) {
		TienLuongCN tl = new TienLuongCN();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM TienLuongCN where congNhan= '" + ma + "'and thangLuong ='"+ngayLuong+"'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuongCN = rs.getString("maLuongCN");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongLuong = rs.getDouble("tongLuong");
				String maCN = rs.getNString("congNhan");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				tl = new TienLuongCN(maLuongCN, thangLuong, tienLuong, tienThuong, tienPhat, tongLuong, cn);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tl;
	}
	
	public TienLuongCN getLuongCongNhanTheoMaCongNhanVaThang(String ma, String thang) {
		TienLuongCN tl = new TienLuongCN();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM TienLuongCN where congNhan= '" + ma + "'and MONTH(thangLuong) ='"+thang+"'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuongCN = rs.getString("maLuongCN");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongLuong = rs.getDouble("tongLuong");
				String maCN = rs.getNString("congNhan");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				tl = new TienLuongCN(maLuongCN, thangLuong, tienLuong, tienThuong, tienPhat, tongLuong, cn);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tl;
	}
	
	public ArrayList<TienLuongCN> getDSLuongCongNhanTheoMaCongNhan(String ma, String thang) {
		ArrayList<TienLuongCN> dsTL = new ArrayList<TienLuongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM TienLuongCN where congNhan= '" + ma + "'AND MONTH(thangLuong) = '"+ thang+"'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuongCN = rs.getString("maLuongCN");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongLuong = rs.getDouble("tongLuong");
				String maCN = rs.getNString("congNhan");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				TienLuongCN tl = new TienLuongCN(maLuongCN, thangLuong, tienLuong, tienThuong, tienPhat, tongLuong, cn);
				dsTL.add(tl);
			}
			return dsTL;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public boolean themTienLuong(TienLuongCN tienLuongCN) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into TienLuongCN values(?,?,?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, tienLuongCN.getMaLuongCN());
			statement.setDate(2, (Date) tienLuongCN.getThangLuong());
			statement.setDouble(3, tienLuongCN.getTienLuong());
			statement.setDouble(4, tienLuongCN.getTienThuong());
			statement.setDouble(5, tienLuongCN.getTienPhat());
			statement.setDouble(6, tienLuongCN.getTongLuong());
			statement.setString(7, tienLuongCN.getCongNhan().getMaCN());
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

	public boolean capNhatLuongCN(Date thangLuong,double tienLuong, double tienThuong, double tienPhat, double tongLuong,
			String congNhan, String thang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			//
			String sql = "update TienLuongCN set thangLuong=?, tienLuong=?, tienThuong=?,tienPhat=?,tongLuong=? where congNhan='"
					+ congNhan + "' and MONTH(thangLuong)= ?";
			statement = con.prepareStatement(sql);
			statement.setDate(1, thangLuong);
			statement.setDouble(2, tienLuong);
			statement.setDouble(3, tienThuong);
			statement.setDouble(4, tienPhat);
			statement.setDouble(5, tongLuong);
			statement.setString(6, thang);
			n = statement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return n > 0;
	}

	public ArrayList<TienLuongCN> getDanhSachTienLuongTheoThang(String thang, String nam) {
		ArrayList<TienLuongCN> dsTL = new ArrayList<TienLuongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *from TienLuongCN " + "WHERE month(thangLuong)='" + thang + "' and year(thangLuong)='"
					+ nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLuong = rs.getString("maLuongCN");
				Date thangLuong = rs.getDate("thangLuong");
				double tienLuong = rs.getDouble("tienLuong");
				double tienThuong = rs.getDouble("tienThuong");
				double tienPhat = rs.getDouble("tienPhat");
				double tongTien = rs.getDouble("tongLuong");
				String maCN = rs.getNString("congNhan");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				TienLuongCN tl = new TienLuongCN(maLuong, thangLuong, tienLuong, tienThuong, tienPhat, tongTien, cn);
				dsTL.add(tl);
			}
			return dsTL;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getTongSoLuongSPHTTheoMaCNvaTheoThangLuong(String maCN, String thang, String nam) {
		int tongSP = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT SUM(soSPHoanThanh) FROM ChamCongCN CCCN JOIN CongNhan CN ON CCCN.congNhan = CN.maCN JOIN TienLuongCN TLCN ON CN.maCN = TLCN.congNhan WHERE TLCN.congNhan = '"
					+ maCN + "' AND MONTH(TLCN.thangLuong) ='" + thang + "' AND YEAR(TLCN.thangLuong)='" + nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tongSP = rs.getInt(1);
			}
			return tongSP;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tongSP;
	}

	public String getCongDoanTheoMaCNvaTheoThangLuong(String maNV, String thang, String nam) {
		String congDoan = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT PC.congDoan FROM  ChiTietPhanCong CTPC JOIN PhanCong PC ON PC.maPC = CTPC.phanCong WHERE CTPC.congNhan = '"
					+ maNV + "' AND MONTH(CTPC.ngayCTPC) ='" + thang + "' AND YEAR(CTPC.ngayCTPC)='" + nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				congDoan = rs.getString(1);
			}
			return congDoan;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return congDoan;
	}

	public String getSanPhamTheoMaCNvaTheoThangLuong(String maCN, String thang, String nam) {
		String sanPham = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT SP.tenSP FROM  ChiTietPhanCong CTPC JOIN PhanCong PC ON PC.maPC = CTPC.phanCong JOIN SanPham SP ON SP.maSP=PC.sanPham WHERE CTPC.congNhan = '"
					+ maCN + "'AND MONTH(CTPC.ngayCTPC) ='" + thang + "' AND YEAR(CTPC.ngayCTPC)='" + nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				sanPham = rs.getString(1);
			}
			return sanPham;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sanPham;
	}

	public double getTienLuong(String maCN, String thang, String nam) {
		double tienLuong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT tienLuong FROM  TienLuongCN WHERE congNhan = '" + maCN + "' AND MONTH(thangLuong) ='"
					+ thang + "' AND YEAR(thangLuong)='" + nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tienLuong = rs.getDouble(1);
			}
			return tienLuong;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tienLuong;
	}

	public double getTienThuong(String maCN, String thang, String nam) {
		double tienThuong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT tienThuong FROM  TienLuongCN WHERE congNhan = '" + maCN + "' AND MONTH(thangLuong) ='"
					+ thang + "' AND YEAR(thangLuong)='" + nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tienThuong = rs.getDouble(1);
			}
			return tienThuong;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tienThuong;
	}

	public double getTienPhat(String maCN, String thang, String nam) {
		double tienPhat = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT tienPhat FROM  TienLuongCN WHERE congNhan = '" + maCN + "' AND MONTH(thangLuong) ='"
					+ thang + "' AND YEAR(thangLuong)='" + nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tienPhat = rs.getDouble(1);
			}
			return tienPhat;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tienPhat;
	}

	public double getTongLuong(String maCN, String thang, String nam) {
		double tongLuong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT tongLuong FROM  TienLuongCN WHERE congNhan = '" + maCN + "' AND MONTH(thangLuong) ='"
					+ thang + "' AND YEAR(thangLuong)='" + nam + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tongLuong = rs.getDouble(1);
			}
			return tongLuong;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tongLuong;
	}

	public String getAutoMaTienLuongCN() {
		int soLuong = 0;
		String so = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT count(1) FROM TienLuongCN";
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
			so = "TLCN0" + soLuong;
		} else {
			so = "TLCN" + soLuong;
		}
		List<TienLuongCN> listTLNC = new ArrayList<TienLuongCN>();
		listTLNC = getAllTienLuongCN();
		for (int i = listTLNC.size() - 1; i >= 0; i--) {
			if (so.equalsIgnoreCase(listTLNC.get(i).getMaLuongCN())) {
				soLuong--;
				if (soLuong < 10) {
					so = "TLCN0" + soLuong;
				} else {
					so = "TLCN" + soLuong;
				}
			}
		}
		return so;
	}
}
