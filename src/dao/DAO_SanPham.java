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
import entity.Kho;
import entity.NhanVien;
import entity.PhanCong;
import entity.PhongBan;
import entity.SanPham;

public class DAO_SanPham {
	public ArrayList<SanPham> getAllSanPham() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();

		try {
			String sql = "Select * from SanPham";
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getNString(2);
				double donGia = rs.getDouble(3);
				String anh = rs.getString(4);
				String kho = rs.getString(5);
				Kho k = new DAO_Kho().timKho(kho);
				SanPham sp = new SanPham(maSP, tenSP, donGia, anh, k);
				list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean themSanPham(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into SanPham\r\n" + "values(?,?,?,?,?)";

			statement = con.prepareStatement(sql);
			statement.setString(1, sp.getMaSP());
			statement.setString(2, sp.getTenSP());
			statement.setDouble(3, sp.getDonGia());
			statement.setString(4, sp.getAnh());
			statement.setString(5, sp.getKho().getMaKho());

			int kq = statement.executeUpdate();
			statement.close();
			if (kq == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	public boolean xoaSanPham(String maSP) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "delete from SanPham where maSP=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			n = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return n > 0;
	}

	public boolean capNhatSanPham(SanPham sanPham) {
		int n = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "update SanPham set tenSP=?,donGia=?,anh=?,kho=? where maSP=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, sanPham.getTenSP());
			statement.setDouble(2, sanPham.getDonGia());
			statement.setString(3, sanPham.getAnh());
			statement.setString(4, sanPham.getKho().getMaKho());
			statement.setString(5, sanPham.getMaSP());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return n > 0;
	}

	public SanPham timSanPham(String maSP) {
		ArrayList<SanPham> list = new DAO_SanPham().getAllSanPham();
		for (SanPham dto_SP : list) {
			if (dto_SP.getMaSP().equals(maSP)) {
				return dto_SP;
			}
		}
		return null;
	}

	public ArrayList<SanPham> getSanPhamTheoTen(String tenSP) {
		ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select *from SanPham where tenSP Like N'%" + tenSP + "%'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String ten = rs.getNString(2);
				double donGia = rs.getDouble(3);
				String anh = rs.getString(4);
				String kho = rs.getString(5);
				Kho k = new DAO_Kho().timKho(kho);
				SanPham sp = new SanPham(maSP, ten, donGia, anh, k);
				dsSP.add(sp);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsSP;
	}

	public ArrayList<Kho> getKho() {
		ArrayList<Kho> khoSP = new ArrayList<Kho>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT * FROM KHO";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maKho = rs.getString(1);
				String diaChi = rs.getString(2);
				Kho kho = new Kho(maKho, diaChi);
				khoSP.add(kho);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return khoSP;
	}

	public String getMaSP() {
		int soLuong = 0;
		String so = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT count(1) FROM SanPham";
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
			so = "SP0" + soLuong;
		} else {
			so = "SP" + soLuong;
		}
		List<SanPham> listSP = new ArrayList<SanPham>();
		listSP = getAllSanPham();
		for (int i = listSP.size() - 1; i >= 0; i--) {
			if (so.equalsIgnoreCase(listSP.get(i).getMaSP())) {
				soLuong--;
				if (soLuong < 10) {
					so = "SP0" + soLuong;
				} else {
					so = "SP" + soLuong;
				}
			}
		}
		return so;
	}

	public ArrayList<SanPham> filterByMaKho(String maKho) {
		ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
		PreparedStatement stm = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "select * from sanpham as sp join Kho as K on sp.kho = maKho where diaChi = ?";
			stm = con.prepareStatement(sql);

			stm.setString(1, maKho);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				double donGia = rs.getInt("donGia");
				String anh = rs.getString("anh");
				String maKho1 = rs.getString("maKho");
				String diaChi = rs.getString("diaChi");
				Kho kho = new Kho(maKho1, diaChi);
				dsSanPham.add(new SanPham(maSP, tenSP, donGia, anh, kho));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsSanPham;
	}

}
