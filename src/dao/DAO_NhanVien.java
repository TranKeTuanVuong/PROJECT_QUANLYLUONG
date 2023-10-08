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
import entity.PhongBan;

public class DAO_NhanVien {
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> list = new ArrayList<NhanVien>();

		try {
			String sql = "Select * from NhanVien";
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String tenNV = rs.getNString("tenNV");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String diaChi = rs.getNString("diaChi");
				String CMND = rs.getNString("CMND");
				String chucVu = rs.getNString("chucVu");
				String trinhDoHocVan = rs.getNString("trinhDoHocVan");
				String kinhNghiem = rs.getNString("kinhNghiem");
				Double heSoLuong = rs.getDouble("heSoLuong");
				Double phuCap = rs.getDouble("phuCap");
				String anh = rs.getNString("anh");
				String phongBan = rs.getNString("phongBan");
				String maKho = rs.getNString("kho");
				PhongBan pb = new DAO_PhongBan().timPhongBan(phongBan);
				Kho kho = new DAO_Kho().timKho(maKho);
				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, diaChi, CMND, chucVu, trinhDoHocVan,
						kinhNghiem, heSoLuong, phuCap, anh, pb, kho);
				list.add(nv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean themNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into NhanVien\r\n" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			statement = con.prepareStatement(sql);
			statement.setString(1, nv.getMaNhanVien());
			statement.setString(2, nv.getTenNhanVien());
			statement.setBoolean(3, nv.isGioiTinh());
			statement.setDate(4, nv.getNgaySinh());
			statement.setString(5, nv.getDiaChi());
			statement.setString(6, nv.getCMND());
			statement.setString(7, nv.getChucVu());
			statement.setString(8, nv.getTrinhDoHocVan());
			statement.setString(9, nv.getKinhNghiem());
			statement.setDouble(10, nv.getHeSoLuong());
			statement.setDouble(11, nv.getPhuCap());
			statement.setString(12, nv.getAnh());
			if (nv.getPhongBan() == null) {
				statement.setString(13, null);
			} else
				statement.setString(13, nv.getPhongBan().getMaPB());
			if (nv.getKho() == null)
				statement.setString(14, null);
			else
				statement.setString(14, nv.getKho().getMaKho());
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

	public boolean xoaNhanVien(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "delete from TienLuongNV where nhanVien=?\r\n" + "delete from ChamCongNV where nhanVien=?\r\n"
					+ "delete from NhanVien where maNV=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maNV);
			statement.setString(2, maNV);
			statement.setString(3, maNV);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean capNhatThongTinNhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, Date ngaySinh,
			String diaChi, String CMND, String chucVu, String trinhDoHocVan, String kinhNghiem, String anh,
			double heSoLuong, double phuCap, String phongBan, String kho) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "update NhanVien set tenNV=?,gioiTinh=?,ngaySinh=?,diaChi=?,CMND=?,chucVu=?,trinhDoHocVan=?,kinhNghiem=?,heSoLuong=?,phuCap=?,anh=?,phongBan=?,kho=? where maNV='"
					+ maNhanVien + "'";
			statement = con.prepareStatement(sql);
			statement.setString(1, tenNhanVien);
			statement.setBoolean(2, gioiTinh);
			statement.setDate(3, ngaySinh);
			statement.setString(4, diaChi);
			statement.setString(5, CMND);
			statement.setString(6, chucVu);
			statement.setString(7, trinhDoHocVan);
			statement.setString(8, kinhNghiem);
			statement.setDouble(9, heSoLuong);
			statement.setDouble(10, phuCap);
			statement.setString(11, anh);
			statement.setString(12, phongBan);
			statement.setString(13, kho);

			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;

		}
	}

	public NhanVien timNV(String maNV) {
		ArrayList<NhanVien> list = new DAO_NhanVien().getAllNhanVien();
		for (NhanVien dto_NV : list) {
			if (dto_NV.getMaNhanVien().equals(maNV)) {
				return dto_NV;
			}
		}
		return null;
	}

	public NhanVien getNhanVienTheoMaNV(String ma) {
		NhanVien nv = new NhanVien();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select *from NhanVien where maNV='" + ma + "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getNString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getNString(5);
				String CMND = rs.getNString(6);
				String chucVu = rs.getNString(7);
				String trinhDoHocVan = rs.getNString(8);
				String kinhNghiem = rs.getNString(9);
				Double heSoLuong = rs.getDouble(10);
				Double phuCap = rs.getDouble(11);
				String anh = rs.getNString(12);
				String phongBan = rs.getNString(13);
				String maKho = rs.getNString(14);
				PhongBan pb = new DAO_PhongBan().timPhongBan(phongBan);
				Kho kho = new DAO_Kho().timKho(maKho);
				nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, diaChi, CMND, chucVu, trinhDoHocVan, kinhNghiem,
						heSoLuong, phuCap, anh, pb, kho);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}

	public ArrayList<NhanVien> getNhanVienTheoPhongBan(String tenPB) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select *from NhanVien nv join PhongBan pb on nv.phongBan=pb.maPB where tenPB=N'" + tenPB
					+ "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getNString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getNString(5);
				String CMND = rs.getNString(6);
				String chucVu = rs.getNString(7);
				String trinhDoHocVan = rs.getNString(8);
				String kinhNghiem = rs.getNString(9);
				Double heSoLuong = rs.getDouble(10);
				Double phuCap = rs.getDouble(11);
				String anh = rs.getNString(12);
				String phongBan = rs.getNString(13);
				String maKho = rs.getNString(14);
				PhongBan pb = new DAO_PhongBan().timPhongBan(phongBan);
				Kho kho = new DAO_Kho().timKho(maKho);
				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, diaChi, CMND, chucVu, trinhDoHocVan,
						kinhNghiem, heSoLuong, phuCap, anh, pb, kho);
				dsNhanVien.add(nv);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNhanVien;
	}

	public ArrayList<NhanVien> getNhanVienTheoKho(String tenKho) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select *from NhanVien nv join Kho k on nv.kho=k.maKho where k.diaChi=N'" + tenKho + "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getNString(2);
				boolean gioiTinh = rs.getBoolean(3);
				Date ngaySinh = rs.getDate(4);
				String diaChi = rs.getNString(5);
				String CMND = rs.getNString(6);
				String chucVu = rs.getNString(7);
				String trinhDoHocVan = rs.getNString(8);
				String kinhNghiem = rs.getNString(9);
				Double heSoLuong = rs.getDouble(10);
				Double phuCap = rs.getDouble(11);
				String anh = rs.getNString(12);
				String phongBan = rs.getNString(13);
				String maKho = rs.getNString(14);
				PhongBan pb = new DAO_PhongBan().timPhongBan(phongBan);
				Kho kho = new DAO_Kho().timKho(maKho);
				NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, ngaySinh, diaChi, CMND, chucVu, trinhDoHocVan,
						kinhNghiem, heSoLuong, phuCap, anh, pb, kho);
				dsNhanVien.add(nv);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNhanVien;
	}

	public ArrayList<NhanVien> getNhanVienTheoTen(String tenNV) {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select *from NhanVien where tenNV like N'%" + tenNV + "%'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String ten = rs.getNString("tenNV");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String diaChi = rs.getNString("diaChi");
				String CMND = rs.getNString("CMND");
				String chucVu = rs.getNString("chucVu");
				String trinhDoHocVan = rs.getNString("trinhDoHocVan");
				String kinhNghiem = rs.getNString("kinhNghiem");
				Double heSoLuong = rs.getDouble("heSoLuong");
				Double phuCap = rs.getDouble("phuCap");
				String anh = rs.getNString("anh");
				String phongBan = rs.getNString("phongBan");
				PhongBan pb = null;
				if (phongBan != null) {
					pb = new DAO_PhongBan().timPhongBan(phongBan);
				}
				String maKho = rs.getNString("kho");
				Kho kho = null;
				if (maKho != null) {
					kho = new DAO_Kho().timKho(maKho);
				}
				NhanVien nv = new NhanVien(maNV, ten, gioiTinh, ngaySinh, diaChi, CMND, chucVu, trinhDoHocVan,
						kinhNghiem, heSoLuong, phuCap, anh, pb, kho);
				dsNhanVien.add(nv);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNhanVien;
	}

	public NhanVien getNhanVienTheoMa(String id) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		NhanVien nv = null;
		try {
			Statement stm;
			String sql = "select *from NhanVien where maNV ='" + id + "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString("maNV");
				String ten = rs.getNString("tenNV");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String diaChi = rs.getNString("diaChi");
				String CMND = rs.getNString("CMND");
				String chucVu = rs.getNString("chucVu");
				String trinhDoHocVan = rs.getNString("trinhDoHocVan");
				String kinhNghiem = rs.getNString("kinhNghiem");
				Double heSoLuong = rs.getDouble("heSoLuong");
				Double phuCap = rs.getDouble("phuCap");
				String anh = rs.getNString("anh");
				String phongBan = rs.getNString("phongBan");
				String maKho = rs.getNString("kho");
				PhongBan pb = new DAO_PhongBan().timPhongBan(phongBan);
				Kho kho = new DAO_Kho().timKho(maKho);
				nv = new NhanVien(maNV, ten, gioiTinh, ngaySinh, diaChi, CMND, chucVu, trinhDoHocVan, kinhNghiem,
						heSoLuong, phuCap, anh, pb, kho);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nv;
	}

	public String getMaNV() {
		int soLuong = 0;
		String so = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT count(1) FROM NhanVien";
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
			so = "NV0" + soLuong;
		} else {
			so = "NV" + soLuong;
		}
		List<NhanVien> listNV = new ArrayList<NhanVien>();
		listNV = getAllNhanVien();
		for (int i = listNV.size() - 1; i >= 0; i--) {
			if (so.equalsIgnoreCase(listNV.get(i).getMaNhanVien())) {
				soLuong--;
				if (soLuong < 10) {
					so = "NV0" + soLuong;
				} else {
					so = "NV" + soLuong;
				}
			}
		}
		return so;
	}
}
