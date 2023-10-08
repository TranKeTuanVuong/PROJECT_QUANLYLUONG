package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.ChiTietPhanCong;
import entity.CongNhan;
import entity.PhanCong;

public class DAO_ChiTietPC {
	public ArrayList<ChiTietPhanCong> getAllChiTietPC() {
		ArrayList<ChiTietPhanCong> list = new ArrayList<ChiTietPhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM ChiTietPhanCong";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int soLuongCT = rs.getInt("soLuongCT");
				Date ngayCTPC = rs.getDate("ngayCTPC");
				String maCN = rs.getNString("congNhan");
				String maPC = rs.getNString("phanCong");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				PhanCong pc = new DAO_PhanCong().timPC(maPC);
				ChiTietPhanCong ct = new ChiTietPhanCong(cn, pc, soLuongCT, ngayCTPC);
				list.add(ct);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean themChiTietPhanCong(ChiTietPhanCong ctpc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into ChiTietPhanCong values(?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, ctpc.getPhanCong().getMaPC());
			statement.setString(2, ctpc.getCongNhan().getMaCN());
			statement.setDate(3, (Date) ctpc.getNgayCTPC());
			statement.setInt(4, ctpc.getSoLuongPC());
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

	public boolean update(ChiTietPhanCong chiTietPhanCong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pre = null;
		try {
			String sql = "UPDATE ChiTietPhanCong SET phanCong = ?, soLuongCT=? WHERE congNhan= ?";
			pre = con.prepareStatement(sql);
			pre.setString(1, chiTietPhanCong.getPhanCong().getMaPC());
			pre.setInt(2, chiTietPhanCong.getSoLuongPC());
			pre.setString(3, chiTietPhanCong.getCongNhan().getMaCN());
			pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateSoLuongBang0(String maCN) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pre = null;
		try {
			String sql = "UPDATE ChiTietPhanCong SET soLuongCT='0' WHERE congNhan= '"+maCN+"'";
			pre = con.prepareStatement(sql);
			pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(String idCTPC, String idPhanCong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pre = null;
		int n = 0;
		try {
			pre = con.prepareStatement(
					"delete from ChiTietPhanCong where congNhan=? and phanCong= ?");
			pre.setString(1, idCTPC);
			pre.setString(2, idPhanCong);
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public ChiTietPhanCong timPC(String maCN) {
		ArrayList<ChiTietPhanCong> list = new DAO_ChiTietPC().getAllChiTietPC();
		for (ChiTietPhanCong dto_PC : list) {
			if (dto_PC.getCongNhan().getMaCN().equalsIgnoreCase(maCN)) {
				return dto_PC;
			}
		}
		return null;
	}

	public ArrayList<ChiTietPhanCong> timKiemDSCTPCTheoNgay(java.util.Date ngayCTPC) {
		ArrayList<ChiTietPhanCong> dsCTPC = new ArrayList<ChiTietPhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT * FROM ChiTietPhanCong  WHERE ngayCTPC = '" + ngayCTPC + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCN = rs.getNString("congNhan");
				String maPC = rs.getNString("phanCong");
				Date ngayPhanCong = rs.getDate("ngayCTPC");
				int soLuong = rs.getInt("soLuongCT");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				PhanCong pc = new DAO_PhanCong().timPC(maPC);
				ChiTietPhanCong ctpc = new ChiTietPhanCong(cn, pc, soLuong, ngayPhanCong);
				dsCTPC.add(ctpc);
			}
			return dsCTPC;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChiTietPhanCong> timKiemDSCTPCTheoCongDoan(String congDoanSX) {
		ArrayList<ChiTietPhanCong> dsCTPC = new ArrayList<ChiTietPhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CTPC.congNhan, CTPC.phanCong,CTPC.ngayCTPC,CTPC.soLuongCT FROM ChiTietPhanCong CTPC JOIN PhanCong PC ON CTPC.phanCong= PC.maPC WHERE PC.congDoan LIKE N'"
					+ congDoanSX + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCN = rs.getNString("congNhan");
				String maPC = rs.getNString("phanCong");
				Date ngayPhanCong = rs.getDate("ngayCTPC");
				int soLuong = rs.getInt("soLuongCT");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				PhanCong pc = new DAO_PhanCong().timPC(maPC);
				ChiTietPhanCong ctpc = new ChiTietPhanCong(cn, pc, soLuong, ngayPhanCong);
				dsCTPC.add(ctpc);
			}
			return dsCTPC;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChiTietPhanCong> timKiemDSCTPCTheoTenSP(String tenSP) {
		ArrayList<ChiTietPhanCong> dsCTPC = new ArrayList<ChiTietPhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CTPC.congNhan, CTPC.phanCong,CTPC.ngayCTPC,CTPC.soLuongCT FROM ChiTietPhanCong CTPC JOIN PhanCong PC ON CTPC.phanCong= PC.maPC JOIN SanPham SP ON SP.maSP = PC.sanPham WHERE SP.tenSP LIKE N'"
					+ tenSP + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCN = rs.getNString("congNhan");
				String maPC = rs.getNString("phanCong");
				Date ngayPhanCong = rs.getDate("ngayCTPC");
				int soLuong = rs.getInt("soLuongCT");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				PhanCong pc = new DAO_PhanCong().timPC(maPC);
				ChiTietPhanCong ctpc = new ChiTietPhanCong(cn, pc, soLuong, ngayPhanCong);
				dsCTPC.add(ctpc);
			}
			return dsCTPC;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChiTietPhanCong> timKiemDSCTPCTheoCDSX(String congDoanSX) {
		ArrayList<ChiTietPhanCong> dsCTPC = new ArrayList<ChiTietPhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CTPC.congNhan, CTPC.phanCong,CTPC.ngayCTPC,CTPC.soLuongCT FROM ChiTietPhanCong CTPC JOIN PhanCong PC ON CTPC.phanCong= PC.maPC WHERE PC.congDoan LIKE N'"
					+ congDoanSX + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCN = rs.getNString("congNhan");
				String maPC = rs.getNString("phanCong");
				Date ngayPhanCong = rs.getDate("ngayCTPC");
				int soLuong = rs.getInt("soLuongCT");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				PhanCong pc = new DAO_PhanCong().timPC(maPC);
				ChiTietPhanCong ctpc = new ChiTietPhanCong(cn, pc, soLuong, ngayPhanCong);
				dsCTPC.add(ctpc);
			}
			return dsCTPC;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChiTietPhanCong> timKiemDSCTPCTheoTenCN(String tenCN) {
		ArrayList<ChiTietPhanCong> dsCTPC = new ArrayList<ChiTietPhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CTPC.congNhan, CTPC.phanCong,CTPC.ngayCTPC,CTPC.soLuongCT FROM ChiTietPhanCong CTPC JOIN CongNhan CN ON CN.maCN= CTPC.congNhan WHERE CN.tenCN LIKE N'%"
					+ tenCN + "%'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCN = rs.getNString("congNhan");
				String maPC = rs.getNString("phanCong");
				Date ngayPhanCong = rs.getDate("ngayCTPC");
				int soLuong = rs.getInt("soLuongCT");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				PhanCong pc = new DAO_PhanCong().timPC(maPC);
				ChiTietPhanCong ctpc = new ChiTietPhanCong(cn, pc, soLuong, ngayPhanCong);
				dsCTPC.add(ctpc);
			}
			return dsCTPC;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
