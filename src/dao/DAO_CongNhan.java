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
import entity.NhanVien;

public class DAO_CongNhan {
	public ArrayList<CongNhan> getAllCongNhan() {
		ArrayList<CongNhan> list = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM CongNhan";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCN = rs.getString("maCN");
				String tenCN = rs.getString("tenCN");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String CMND = rs.getString("CMND");
				String diaChi = rs.getString("diaChi");
				Double phuCap = rs.getDouble("phuCap");
				String anh = rs.getString("anh");
				String maNV = rs.getNString("nhanVien");
				NhanVien nv = new DAO_NhanVien().timNV(maNV);
				CongNhan cN = new CongNhan(maCN, tenCN, gioiTinh, ngaySinh, CMND, diaChi, phuCap, anh, nv);
				list.add(cN);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean themCongNhan(CongNhan cn) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into CongNhan\r\n" + "values(?,?,?,?,?,?,?,?,?)";

			statement = con.prepareStatement(sql);
			statement.setString(1, cn.getMaCN());
			statement.setString(2, cn.getTenCN());
			statement.setBoolean(3, cn.isGioiTinh());
			statement.setDate(4, (Date) cn.getNgaySinh());
			statement.setString(5, cn.getDiaChi());
			statement.setString(6, cn.getCMND());
			statement.setDouble(7, cn.getPhuCap());
			statement.setString(8, cn.getAnh());
			statement.setString(9, cn.getNhanVien().getMaNhanVien());
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

	public boolean xoaCongNhan(String maCN) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "delete from CongNhan where maCN=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maCN);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	

	public boolean capNhatThongTinNhanVien(CongNhan cn) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			//
			String sql = "update CongNhan set tenCN=?,gioiTinh=?,ngaySinh=?,diaChi=?,CMND=?,phuCap=?,anh=?,nhanVien=? where maCN=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, cn.getTenCN());
			statement.setBoolean(2, cn.isGioiTinh());
			statement.setDate(3, (Date) cn.getNgaySinh());
			statement.setString(4, cn.getDiaChi());
			statement.setString(5, cn.getCMND());
			statement.setDouble(6, cn.getPhuCap());
			statement.setString(7, cn.getAnh());
			statement.setString(8, cn.getNhanVien().getMaNhanVien());
			statement.setString(9, cn.getMaCN());
			n = statement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return n > 0;
	}

	public CongNhan getCongNhanTheoMaCN(String ma) {
		CongNhan cN = new CongNhan();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select *from CongNhan where maCN='" + ma + "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				cN.setMaCN(rs.getString("maCN"));
				cN.setTenCN(rs.getString("tenCN"));
				cN.setGioiTinh(rs.getBoolean("gioiTinh"));
				cN.setNgaySinh(rs.getDate("ngaySinh"));
				cN.setCMND(rs.getString("CMND"));
				cN.setDiaChi(rs.getString("diaChi"));
				cN.setPhuCap(rs.getDouble("phuCap"));
				cN.setAnh(rs.getString("anh"));
				NhanVien nv = new NhanVien();
				nv.setMaNhanVien(rs.getString("nhanVien"));
				cN.setNhanVien(nv);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return cN;
	}

	public ArrayList<CongNhan> getCongNhanTheoTen(String tenCN) {
		ArrayList<CongNhan> dsCN = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select *from CongNhan where tenCN like N'%" + tenCN + "%'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maCN = rs.getString("maCN");
				String ten = rs.getString("tenCN");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String CMND = rs.getString("CMND");
				String diaChi = rs.getString("diaChi");
				Double phuCap = rs.getDouble("phuCap");
				String anh = rs.getString("anh");
				String maNV = rs.getNString("nhanVien");
				NhanVien nv = new DAO_NhanVien().timNV(maNV);
				CongNhan cN = new CongNhan(maCN, ten, gioiTinh, ngaySinh, CMND, diaChi, phuCap, anh, nv);
				dsCN.add(cN);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCN;
	}

	public ArrayList<CongNhan> getCongNhanTheoSanPham(String sanPham) {
		ArrayList<CongNhan> dsCN = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select *\r\n"
					+ "from CongNhan CN JOIN PhanCong PC ON PC.maPC= CN.phanCong JOIN SanPham SP ON PC.sanPham =SP.maSP "
					+ "where SP.tenSP like N'%" + sanPham + "%'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maCN = rs.getString("maCN");
				String ten = rs.getString("tenCN");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String CMND = rs.getString("CMND");
				String diaChi = rs.getString("diaChi");
				Double phuCap = rs.getDouble("phuCap");
				String anh = rs.getString("anh");
				String maNV = rs.getNString("nhanVien");
				NhanVien nv = new DAO_NhanVien().timNV(maNV);
				CongNhan cN = new CongNhan(maCN, ten, gioiTinh, ngaySinh, CMND, diaChi, phuCap, anh, nv);
				dsCN.add(cN);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCN;
	}

	public ArrayList<CongNhan> getCongNhanTheoNVQL(String tenNV) {
		ArrayList<CongNhan> dsCN = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select * from CongNhan cn Join NhanVien nv on cn.nhanVien= nv.maNV\r\n"
					+ "where tenNV like N'" + tenNV + "%'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maCN = rs.getString("maCN");
				String tenCN = rs.getString("tenCN");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String CMND = rs.getString("CMND");
				String diaChi = rs.getString("diaChi");
				Double phuCap = rs.getDouble("phuCap");
				String anh = rs.getString("anh");
				String maNV = rs.getNString("nhanVien");
				NhanVien nv = new DAO_NhanVien().timNV(maNV);
				CongNhan cN = new CongNhan(maCN, tenCN, gioiTinh, ngaySinh, CMND, diaChi, phuCap, anh, nv);
				dsCN.add(cN);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCN;
	}

	public ArrayList<CongNhan> getCongNhanTheoCongDoan(String congDoan) {
		ArrayList<CongNhan> dsCN = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {

			String sql = "select *from CongNhan where congDoan=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, congDoan);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maCN = rs.getString("maCN");
				String tenCN = rs.getString("tenCN");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String CMND = rs.getString("CMND");
				String diaChi = rs.getString("diaChi");
				Double phuCap = rs.getDouble("phuCap");
				String anh = rs.getString("anh");
				String maNV = rs.getNString("nhanVien");
				NhanVien nv = new DAO_NhanVien().timNV(maNV);
				CongNhan cN = new CongNhan(maCN, tenCN, gioiTinh, ngaySinh, CMND, diaChi, phuCap, anh, nv);
				dsCN.add(cN);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCN;
	}

	public String getAutoMaCN() {
		int soLuong = 0;
		String so = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT count(1) FROM CongNhan";
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
			so = "CN0" + soLuong;
		} else {
			so = "CN" + soLuong;
		}
		List<CongNhan> listCN = new ArrayList<CongNhan>();
		listCN = getAllCongNhan();
		for (int i = listCN.size() - 1; i >= 0; i--) {
			if (so.equalsIgnoreCase(listCN.get(i).getMaCN())) {
				soLuong--;
				if (soLuong < 10) {
					so = "CN0" + soLuong;
				} else {
					so = "CN" + soLuong;
				}
			}
		}
		return so;
	}


	public CongNhan timCN(String maCN) {
		ArrayList<CongNhan> list = new DAO_CongNhan().getAllCongNhan();
		for (CongNhan dto_PC : list) {
			if (dto_PC.getMaCN().equalsIgnoreCase(maCN)) {
				return dto_PC;
			}
		}
		return null;
	}
}
