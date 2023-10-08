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
import entity.PhanCong;
import entity.SanPham;

public class DAO_PhanCong {
	public ArrayList<PhanCong> getAllPhanCong() {
		ArrayList<PhanCong> list = new ArrayList<PhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM PhanCong";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {

				String maPC = rs.getString("maPC");
				Date ngayPC = rs.getDate("ngayPC");
				int soLuong = rs.getInt("soLuongTong");
				String congDoanSX = rs.getNString("congDoan");
				String maSP = rs.getNString("sanPham");
				SanPham sp = new DAO_SanPham().timSanPham(maSP);
				PhanCong pc = new PhanCong(maPC, ngayPC, congDoanSX, soLuong, sp);
				list.add(pc);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean themPhanCong(PhanCong pc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into PhanCong values(?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, pc.getMaPC());
			statement.setDate(2, (Date) pc.getNgayPC());
			statement.setInt(3, pc.getSoLuong());
			statement.setString(4, pc.getCongDoanSX());
			statement.setString(5, pc.getSanPham().getMaSP());
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

	public boolean update(PhanCong phanCong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pre = null;
		try {
			String sql = "UPDATE PhanCong SET ngayPC = ?, soLuongTong=? , congDoan=?, sanPham=? WHERE maPC= ?";
			pre = con.prepareStatement(sql);
			pre.setDate(1, (Date) phanCong.getNgayPC());
			pre.setInt(2, phanCong.getSoLuong());
			pre.setString(3, phanCong.getCongDoanSX());
			pre.setString(4, phanCong.getSanPham().getMaSP());
			pre.setString(5, phanCong.getMaPC());
			pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(String idPC) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pre = null;
		int n = 0;
		try {
			pre = con.prepareStatement("delete from PhanCong where maPC=?");
			pre.setString(1, idPC);
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public PhanCong timPC(String maPC) {
		ArrayList<PhanCong> list = new DAO_PhanCong().getAllPhanCong();
		for (PhanCong dto_PC : list) {
			if (dto_PC.getMaPC().equalsIgnoreCase(maPC)) {
				return dto_PC;
			}
		}
		return null;
	}

	public ArrayList<PhanCong> timKiemDSPCTheoNgay(java.util.Date ngayPC) {
		ArrayList<PhanCong> dsPC = new ArrayList<PhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT PC.maPC,PC.ngayPC, PC.congDoan,SP.maSP,SP.tenSP,PC.soLuongTong FROM  PhanCong PC JOIN SanPham SP ON PC.sanPham= SP.maSP WHERE PC.ngayPC = '"
					+ ngayPC + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maPC = rs.getString("maPC");
				String tenSP = rs.getNString("tenSP");
				Date ngayPhanCong = rs.getDate("ngayPC");
				String congDoan = rs.getNString("congDoan");
				String maSP = rs.getNString("maSP");
				int soLuong = rs.getInt("soLuongTong");
				SanPham sp = new SanPham(maSP, tenSP);
				PhanCong pc = new PhanCong(maPC, ngayPhanCong, congDoan, soLuong, sp);
				dsPC.add(pc);
			}
			return dsPC;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<PhanCong> timKiemDSPCTheoCongDoan(String congDoanSX) {
		ArrayList<PhanCong> dsPC = new ArrayList<PhanCong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT PC.maPC,PC.ngayPC, PC.congDoan,SP.maSP,SP.tenSP,PC.soLuongTong FROM  PhanCong PC JOIN SanPham SP ON PC.sanPham= SP.maSP WHERE PC.congDoan = N'"
					+ congDoanSX + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maPC = rs.getString("maPC");
				String tenSP = rs.getNString("tenSP");
				Date ngayPhanCong = rs.getDate("ngayPC");
				String congDoan = rs.getNString("congDoan");
				String maSP = rs.getNString("maSP");
				int soLuong = rs.getInt("soLuongTong");
				SanPham sp = new SanPham(maSP, tenSP);
				PhanCong pc = new PhanCong(maPC, ngayPhanCong, congDoan, soLuong, sp);
				dsPC.add(pc);
			}
			return dsPC;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAutoMaPC() {
		int soLuong = 0;
		String so = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT count(1) FROM PhanCong";
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
			so = "CD0" + soLuong;
		} else {
			so = "CD" + soLuong;
		}
		List<PhanCong> listCD = new ArrayList<PhanCong>();
		listCD = getAllPhanCong();
		for (int i = listCD.size() - 1; i >= 0; i--) {
			if (so.equalsIgnoreCase(listCD.get(i).getMaPC())) {
				soLuong--;
				if (soLuong < 10) {
					so = "CD0" + soLuong;
				} else {
					so = "CD" + soLuong;
				}
			}
		}
		return so;
	}
}
