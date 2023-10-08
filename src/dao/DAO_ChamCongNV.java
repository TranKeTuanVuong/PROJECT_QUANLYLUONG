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
import entity.ChamCongNV;
import entity.Kho;
import entity.NhanVien;
import entity.PhongBan;

public class DAO_ChamCongNV {
	public ArrayList<ChamCongNV> getAllDanhSachChamCong() {
		ArrayList<ChamCongNV> dsChamCongNV = new ArrayList<ChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT NV.tenNV, CC.coMat, CC.trangThai, CC.phep,CC.soGioTangCa ,CC.ghiChu "
					+ "FROM ChamCongNV CC JOIN NhanVien NV ON CC.nhanVien =NV.MaNV ";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ChamCongNV chamCongNV = new ChamCongNV();
				NhanVien nV = new NhanVien();
				nV.setTenNhanVien(rs.getString("tenNV"));
				chamCongNV.setNhanVien(nV);
				chamCongNV.setCoMat(rs.getBoolean("coMat"));
				chamCongNV.setTrangThai(rs.getBoolean("trangThai"));
				chamCongNV.setPhep(rs.getBoolean("phep"));
				chamCongNV.setSoGioTangCa(rs.getInt("soGioTangCa"));
				chamCongNV.setGhiChu(rs.getString("ghiChu"));
				if (!dsChamCongNV.contains(chamCongNV)) {
					dsChamCongNV.add(chamCongNV);
				}
			}
			return dsChamCongNV;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(ChamCongNV nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pre = null;
		int n = 0;
		try {
			pre = con.prepareStatement(
					"update ChamCongNV set coMat = ?, trangThai=? , phep=?, soGioTangCa= ? , ghiChu= ? where maChamCongNV= ?");
			pre.setBoolean(1, nv.isCoMat());
			pre.setBoolean(2, nv.isTrangThai());
			pre.setBoolean(3, nv.isPhep());
			pre.setInt(4, nv.getSoGioTangCa());
			pre.setString(5, nv.getMaChamCong());
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<ChamCongNV> timKiemDanhSachChamCongNVTheoTen(String tenNV, java.util.Date ngayCC) {
		ArrayList<ChamCongNV> dsChamCongNV = new ArrayList<ChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CC.maChamCongNV,CC.ngayCN, NV.maNV,NV.tenNV, CC.coMat, CC.trangThai, CC.phep,CC.soGioTangCa ,CC.ghiChu "
					+ "FROM ChamCongNV CC JOIN NhanVien NV ON CC.nhanVien =NV.MaNV " + "WHERE tenNV LIKE N'%" + tenNV
					+ "%' and CC.ngayCN='" + ngayCC + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString("maChamCongNV");
				Date ngayCCNV = rs.getDate("ngayCN");
				boolean coMat = rs.getBoolean("coMat");
				boolean trangThai = rs.getBoolean("trangThai");
				boolean phep = rs.getBoolean("phep");
				int soGioTangCa = rs.getInt("soGioTangCa");
				String ghiChu = rs.getNString("ghiChu");
				String tennv = rs.getNString("tenNV");
				String maNV = rs.getNString("maNV");
				NhanVien nv = new NhanVien(maNV, tennv);
				ChamCongNV ccnv = new ChamCongNV(maChamCong, ngayCCNV, phep, soGioTangCa, coMat, trangThai, ghiChu, nv);
				dsChamCongNV.add(ccnv);
			}
			return dsChamCongNV;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongNV> timKiemDanhSachChamCongNVTheoThang(String ma, String thang) {
		ArrayList<ChamCongNV> dsChamCongNV = new ArrayList<ChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CC.maChamCongNV,CC.ngayCN, NV.maNV,NV.tenNV, CC.coMat, CC.trangThai, CC.phep,CC.soGioTangCa ,CC.ghiChu "
					+ "FROM ChamCongNV CC JOIN NhanVien NV ON CC.nhanVien =NV.MaNV " + "WHERE maNV ='" + ma
					+ "' and MONTH(CC.ngayCN)= '" + thang + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString("maChamCongNV");
				Date ngayCCNV = rs.getDate("ngayCN");
				boolean coMat = rs.getBoolean("coMat");
				boolean trangThai = rs.getBoolean("trangThai");
				boolean phep = rs.getBoolean("phep");
				int soGioTangCa = rs.getInt("soGioTangCa");
				String ghiChu = rs.getNString("ghiChu");
				String tennv = rs.getNString("tenNV");
				String maNV = rs.getNString("maNV");
				NhanVien nv = new NhanVien(maNV, tennv);
				ChamCongNV ccnv = new ChamCongNV(maChamCong, ngayCCNV, phep, soGioTangCa, coMat, trangThai, ghiChu, nv);
				dsChamCongNV.add(ccnv);
			}
			return dsChamCongNV;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongNV> timKiemDanhSachChamCongNVTheoPB(java.util.Date ngayCC, String tenpb) {
		ArrayList<ChamCongNV> dsChamCongNV = new ArrayList<ChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT cc.maChamCongNV, cc.ngayCN, cc.phep, cc.coMat, cc.soGioTangCa, cc.trangThai, cc.ghiChu, nv.maNV, nv.tenNV, pb.maPB, pb.tenPB "
					+ "FROM     ChamCongNV cc  JOIN " + " NhanVien nv ON cc.nhanVien = nv.maNV  JOIN "
					+ " PhongBan pb ON nv.phongBan = pb.maPB" + " where cc.ngayCN='" + ngayCC + "' and pb.tenPB like N'"
					+ tenpb + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCC = rs.getNString("maChamCongNV");
				Date ngayCCNV = rs.getDate("ngayCN");
				boolean coMat = rs.getBoolean("coMat");
				boolean trangThai = rs.getBoolean("trangThai");
				boolean phep = rs.getBoolean("phep");
				int soGioTangCa = rs.getInt("soGioTangCa");
				String ghiChu = rs.getNString("ghiChu");
				String tennv = rs.getNString("tenNV");
				String maNV = rs.getNString("maNV");
				String maPB = rs.getNString("maPB");
				String tenPB = rs.getNString("tenPB");
				PhongBan pb = new PhongBan(maPB, tenPB);
				NhanVien nv = new NhanVien(maNV, tennv, pb);
				ChamCongNV ccnv = new ChamCongNV(maCC, ngayCCNV, phep, soGioTangCa, coMat, trangThai, ghiChu, nv);
				dsChamCongNV.add(ccnv);
			}
			return dsChamCongNV;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongNV> timKiemDanhSachChamCongNVTheoKho(java.util.Date ngayCC, String tenKho) {
		ArrayList<ChamCongNV> dsChamCongNV = new ArrayList<ChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT cc.maChamCongNV, cc.ngayCN, cc.phep, cc.coMat, cc.soGioTangCa, cc.trangThai, cc.ghiChu, nv.maNV, nv.tenNV, k.maKho, k.diaChi "
					+ "FROM     ChamCongNV cc  JOIN " + " NhanVien nv ON cc.nhanVien = nv.maNV  JOIN "
					+ " Kho k ON nv.kho = k.maKho" + " where cc.ngayCN='" + ngayCC + "' and k.diaChi like N'" + tenKho
					+ "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCC = rs.getNString("maChamCongNV");
				Date ngayCCNV = rs.getDate("ngayCN");
				boolean coMat = rs.getBoolean("coMat");
				boolean trangThai = rs.getBoolean("trangThai");
				boolean phep = rs.getBoolean("phep");
				int soGioTangCa = rs.getInt("soGioTangCa");
				String ghiChu = rs.getNString("ghiChu");
				String tennv = rs.getNString("tenNV");
				String maNV = rs.getNString("maNV");
				String maKho = rs.getNString("maKho");
				String diaChi = rs.getNString("diaChi");
				Kho kho = new Kho(maKho, diaChi);
				NhanVien nv = new NhanVien(maNV, tennv, kho);
				ChamCongNV ccnv = new ChamCongNV(maCC, ngayCCNV, phep, soGioTangCa, coMat, trangThai, ghiChu, nv);
				dsChamCongNV.add(ccnv);
			}
			return dsChamCongNV;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongNV> timKiemDanhSachChamCongNVTheoNgay(java.util.Date ngayCC) {
		ArrayList<ChamCongNV> dsChamCongNV = new ArrayList<ChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CC.maChamCongNV,CC.ngayCN, NV.maNV,NV.tenNV, CC.coMat, CC.trangThai, CC.phep,CC.soGioTangCa ,CC.ghiChu "
					+ "FROM ChamCongNV CC JOIN NhanVien NV ON CC.nhanVien =NV.MaNV " + "WHERE CC.ngayCN='" + ngayCC
					+ "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString("maChamCongNV");
				Date ngayCCNV = rs.getDate("ngayCN");
				boolean coMat = rs.getBoolean("coMat");
				boolean trangThai = rs.getBoolean("trangThai");
				boolean phep = rs.getBoolean("phep");
				int soGioTangCa = rs.getInt("soGioTangCa");
				String ghiChu = rs.getNString("ghiChu");
				String tennv = rs.getNString("tenNV");
				String maNV = rs.getNString("maNV");
				NhanVien nv = new NhanVien(maNV, tennv);
				ChamCongNV ccnv = new ChamCongNV(maChamCong, ngayCCNV, phep, soGioTangCa, coMat, trangThai, ghiChu, nv);
				dsChamCongNV.add(ccnv);
			}
			return dsChamCongNV;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongNV> getDanhSachChamCongTheoMaNV(String maNV) {
		ArrayList<ChamCongNV> dsChamCongNV = new ArrayList<ChamCongNV>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CC.maChamCongNV,CC.ngayCN, NV.maNV,NV.tenNV, CC.coMat, CC.trangThai, CC.phep,CC.soGioTangCa ,CC.ghiChu "
					+ "FROM ChamCongNV CC JOIN NhanVien NV ON CC.nhanVien =NV.MaNV " + "WHERE maNV ='" + maNV + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString("maChamCongNV");
				Date ngayCCNV = rs.getDate("ngayCN");
				boolean coMat = rs.getBoolean("coMat");
				boolean trangThai = rs.getBoolean("trangThai");
				boolean phep = rs.getBoolean("phep");
				int soGioTangCa = rs.getInt("soGioTangCa");
				String ghiChu = rs.getNString("ghiChu");
				String tennv = rs.getNString("tenNV");
				String manv = rs.getNString("maNV");
				NhanVien nv = new NhanVien(maNV, tennv);
				ChamCongNV ccnv = new ChamCongNV(maChamCong, ngayCCNV, phep, soGioTangCa, coMat, trangThai, ghiChu, nv);
				dsChamCongNV.add(ccnv);
			}
			return dsChamCongNV;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ChamCongNV timCCNV(String maCC) {
		ArrayList<ChamCongNV> list = new DAO_ChamCongNV().getAllDanhSachChamCong();
		for (ChamCongNV dto_CC : list) {
			if (dto_CC.getMaChamCong().equals(maCC)) {
				return dto_CC;
			}
		}
		return null;
	}

	public int getSoNgayDiLam(String maNV, String thang, String nam) {
		int soLuong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select count(coMat) from ChamCongNV where nhanVien='" + maNV
					+ "' and coMat='True' and MONTH(ngayCN) = '" + thang + "' and YEAR(ngayCN)='" + nam + "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soLuong = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soLuong;
	}

	public int getSoNgayNghiCoPhep(String maNV, String thang, String nam) {
		int soLuong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select count(phep) from ChamCongNV where nhanVien='" + maNV
					+ "' and phep='True' and MONTH(ngayCN) = '" + thang + "' and YEAR(ngayCN)='" + nam + "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soLuong = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soLuong;
	}

	public int getSoNgayNghiKhongPhep(String maNV, String thang, String nam) {
		int soLuong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select count(phep) from ChamCongNV where nhanVien='" + maNV
					+ "' and phep='False' and MONTH(ngayCN) = '" + thang + "' and YEAR(ngayCN)='" + nam + "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soLuong = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soLuong;
	}

	public int getSoGioTangCa(String maNV, String thang, String nam) {
		int soLuong = 0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "select sum(soGioTangCa) from ChamCongNV where nhanVien='" + maNV + "' and MONTH(ngayCN) = '"
					+ thang + "' and YEAR(ngayCN)='" + nam + "'";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				soLuong = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return soLuong;
	}

	public String getMaCCNV() {
		int soLuong = 0;
		String so = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT count(1) FROM ChamCongNV";
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
			so = "CCNV0" + soLuong;
		} else {
			so = "CCNV" + soLuong;
		}
		List<ChamCongNV> listCCNV = new ArrayList<ChamCongNV>();
		listCCNV = getAllDanhSachChamCong();
		for (int i = listCCNV.size() - 1; i >= 0; i--) {
			if (so.equalsIgnoreCase(listCCNV.get(i).getMaChamCong())) {
				soLuong--;
				if (soLuong < 10) {
					so = "CCNV0" + soLuong;
				} else {
					so = "CCNV" + soLuong;
				}
			}
		}
		return so;
	}

	public boolean themChamCongNVCoMat(ChamCongNV ccnv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into ChamCongNV(maChamCongNV,ngayCN,coMat,soGioTangCa,trangThai,ghiChu,nhanVien)\r\n"
					+ "values(?,?,?,?,?,?,?)";

			statement = con.prepareStatement(sql);
			statement.setString(1, ccnv.getMaChamCong());
			statement.setDate(2, (java.sql.Date) ccnv.getNgayCN());
			statement.setBoolean(3, ccnv.isCoMat());
			statement.setInt(4, ccnv.getSoGioTangCa());
			statement.setBoolean(5, ccnv.isTrangThai());
			statement.setString(6, ccnv.getGhiChu());
			statement.setString(7, ccnv.getNhanVien().getMaNhanVien());
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

	public boolean themChamCongNVVang(ChamCongNV ccnv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into ChamCongNV(maChamCongNV,ngayCN,phep,coMat,ghiChu,nhanVien)\r\n"
					+ "values(?,?,?,?,?,?)";

			statement = con.prepareStatement(sql);
			statement.setString(1, ccnv.getMaChamCong());
			statement.setDate(2, (java.sql.Date) ccnv.getNgayCN());
			statement.setBoolean(3, ccnv.isPhep());
			statement.setBoolean(4, ccnv.isCoMat());
			statement.setString(5, ccnv.getGhiChu());
			statement.setString(6, ccnv.getNhanVien().getMaNhanVien());
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
}
