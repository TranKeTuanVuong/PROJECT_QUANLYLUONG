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
import entity.CongNhan;
import entity.PhanCong;

public class DAO_ChamCongCN {
	public ArrayList<ChamCongCN> getAllDanhSachChamCong() {
		ArrayList<ChamCongCN> dsChamCongCN = new ArrayList<ChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM ChamCongCN ";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCC = rs.getString("maChamCongCN");
				Date ngayCN = rs.getDate("ngayCN");
				int soSPHT = rs.getInt("soSPHoanThanh");
				Boolean trangThai = rs.getBoolean("trangThai");
				String maCN = rs.getNString("congNhan");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				ChamCongCN chamCongCN = new ChamCongCN(maCC, ngayCN, soSPHT, trangThai, cn);
				dsChamCongCN.add(chamCongCN);
			}
			return dsChamCongCN;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongCN> getChamCongTheoThang(String ma, String thang) {
		ArrayList<ChamCongCN> dsChamCongCN = new ArrayList<ChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT *" + "FROM ChamCongCN WHERE congNhan = '" + ma + "' AND MONTH(ngayCN)= '" + thang
					+ "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maCC = rs.getString("maChamCongCN");
				Date ngayCN = rs.getDate("ngayCN");
				int soSPHT = rs.getInt("soSPHoanThanh");
				Boolean trangThai = rs.getBoolean("trangThai");
				String maCN = rs.getNString("congNhan");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				ChamCongCN chamCongCN = new ChamCongCN(maCC, ngayCN, soSPHT, trangThai, cn);
				dsChamCongCN.add(chamCongCN);
			}
			return dsChamCongCN;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongCN> timKiemDanhSachChamCongTheoTen(String tenCN) {
		ArrayList<ChamCongCN> dsChamCongCN = new ArrayList<ChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CN.tenCN, P.congDoan, CC.soSPhoanthanh, CC.soGioTangCa, "
					+ "CC.trangThai FROM ChamCongCN CC JOIN CongNhan CN ON CC.congNhan =CN.maCN "
					+ "JOIN PhanCong P ON P.maPC= CN.phanCong WHERE tenCN LIKE %" + tenCN + "%";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ChamCongCN chamCongCN = new ChamCongCN();
				CongNhan cN = new CongNhan();
				PhanCong phanCong = new PhanCong();
				cN.setTenCN(rs.getString("tenCN"));
				phanCong.setCongDoanSX(rs.getString("congDoan"));
				chamCongCN.setSoSPhoanThanh(rs.getInt("soSPhoanthanh"));
				chamCongCN.setTrangThai(rs.getBoolean("trangThai"));
				if (!dsChamCongCN.contains(chamCongCN)) {
					dsChamCongCN.add(chamCongCN);
				}
			}
			return dsChamCongCN;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongCN> timKiemDanhSachChamCongTheoCongDoan(String congDoan) {
		ArrayList<ChamCongCN> dsChamCongCN = new ArrayList<ChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CN.tenCN, P.congDoan, CC.soSPhoanthanh, CC.soGioTangCa, "
					+ "CC.trangThai FROM ChamCongCN CC JOIN CongNhan CN ON CC.congNhan =CN.maCN "
					+ "JOIN PhanCong P ON P.maPC= CN.phanCong WHERE congDoan LIKE ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, congDoan);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ChamCongCN chamCongCN = new ChamCongCN();
				CongNhan cN = new CongNhan();
				PhanCong phanCong = new PhanCong();
				cN.setTenCN(rs.getString("tenCN"));
				phanCong.setCongDoanSX(rs.getString("congDoan"));
				chamCongCN.setSoSPhoanThanh(rs.getInt("soSPhoanthanh"));
				chamCongCN.setTrangThai(rs.getBoolean("trangThai"));
				if (!dsChamCongCN.contains(chamCongCN)) {
					dsChamCongCN.add(chamCongCN);
				}
			}
			return dsChamCongCN;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public boolean themChamCong(ChamCongCN cc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement;
		try {
			String sql = "insert into ChamCongCN values(?,?,?,?,?)";
			statement = con.prepareStatement(sql);
			statement.setString(1, cc.getMaChamCong());
			statement.setDate(2, (Date) cc.getNgayCN());
			statement.setInt(3, cc.getSoSPHoanThanh());
			statement.setBoolean(4, cc.isTrangThai());
			statement.setString(5, cc.getCongNhan().getMaCN());
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

	public String getAutoMaCCCN() {
		int soLuong = 0;
		String so = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm;
			String sql = "SELECT count(1) FROM ChamCongCN";
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
			so = "CCCN0" + soLuong;
		} else {
			so = "CCCN" + soLuong;
		}
		ArrayList<ChamCongCN> listCCCN = new ArrayList<ChamCongCN>();
		listCCCN = getAllDanhSachChamCong();
		for (int i = listCCCN.size() - 1; i >= 0; i--) {
			if (so.equalsIgnoreCase(listCCCN.get(i).getMaChamCong())) {
				soLuong--;
				if (soLuong < 10) {
					so = "CCCN0" + soLuong;
				} else {
					so = "CCCN" + soLuong;
				}
			}
		}
		return so;
	}

	public ArrayList<ChamCongCN> timKiemDanhSachChamCongCNTheoNgay(Date ngayCC) {
		ArrayList<ChamCongCN> dsChamCongCN = new ArrayList<ChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CC.maChamCongCN,CN.maCN,CN.tenCN, CC.ngayCN, CC.soSPHoanThanh, CC.trangThai FROM ChamCongCN CC JOIN CongNhan CN ON CC.congNhan =CN.maCN WHERE CC.ngayCN= '"
					+ ngayCC + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString("maChamCongCN");
				Date ngayCCCN = rs.getDate("ngayCN");
				int soSPHoanThanh = rs.getInt("soSPHoanThanh");
				boolean trangThai = rs.getBoolean("trangThai");
				String maCN = rs.getString("maCN");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				ChamCongCN ccCN = new ChamCongCN(maChamCong, ngayCCCN, soSPHoanThanh, trangThai, cn);
				dsChamCongCN.add(ccCN);
			}
			return dsChamCongCN;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongCN> timKiemDanhSachChamCongCNTheoSanPham(String tenSP, java.util.Date ngayCC) {
		ArrayList<ChamCongCN> dsChamCongCN = new ArrayList<ChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CC.maChamCongCN,CN.maCN,CN.tenCN, SP.tenSP,CC.ngayCN, CC.soSPHoanThanh, CC.trangThai FROM ChamCongCN CC JOIN CongNhan CN ON CC.congNhan =CN.maCN JOIN ChiTietPhanCong CTPC ON CN.maCN = CTPC.congNhan JOIN PhanCong PC ON PC.maPC = CTPC.phanCong JOIN SanPham SP ON SP.maSP = PC.sanPham WHERE CC.ngayCN= '"
					+ ngayCC + "' AND SP.tenSP LIKE N'" + tenSP + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString("maChamCongCN");
				Date ngayCCCN = rs.getDate("ngayCN");
				int soSPHoanThanh = rs.getInt("soSPHoanThanh");
				boolean trangThai = rs.getBoolean("trangThai");
				String maCN = rs.getString("maCN");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				ChamCongCN ccCN = new ChamCongCN(maChamCong, ngayCCCN, soSPHoanThanh, trangThai, cn);
				dsChamCongCN.add(ccCN);
			}
			return dsChamCongCN;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongCN> timKiemDanhSachChamCongCNTheoCongDoan(String congDoan, java.util.Date ngayCC) {
		ArrayList<ChamCongCN> dsChamCongCN = new ArrayList<ChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CC.maChamCongCN,CN.maCN,CN.tenCN, CC.ngayCN, CC.soSPHoanThanh, CC.trangThai FROM ChamCongCN CC JOIN CongNhan CN ON CC.congNhan =CN.maCN JOIN ChiTietPhanCong CTPC ON CN.maCN = CTPC.congNhan JOIN PhanCong PC ON PC.maPC = CTPC.phanCong "
					+ "WHERE CC.ngayCN= '" + ngayCC + "' AND PC.congDoan LIKE N'" + congDoan + "'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString("maChamCongCN");
				Date ngayCCCN = rs.getDate("ngayCN");
				int soSPHoanThanh = rs.getInt("soSPHoanThanh");
				boolean trangThai = rs.getBoolean("trangThai");
				String maCN = rs.getString("maCN");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				ChamCongCN ccCN = new ChamCongCN(maChamCong, ngayCCCN, soSPHoanThanh, trangThai, cn);
				dsChamCongCN.add(ccCN);
			}
			return dsChamCongCN;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ChamCongCN> timKiemDanhSachChamCongCNTheoTen(String tenCN, java.util.Date ngayCC) {
		ArrayList<ChamCongCN> dsChamCongCN = new ArrayList<ChamCongCN>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "SELECT CC.maChamCongCN,CN.maCN,CN.tenCN, CC.ngayCN, CC.soSPHoanThanh, CC.trangThai\r\n"
					+ "FROM ChamCongCN CC JOIN CongNhan CN ON CC.congNhan =CN.maCN \r\n" + "WHERE CC.ngayCN= '" + ngayCC
					+ "' AND CN.tenCN LIKE N'%" + tenCN + "%'";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maChamCong = rs.getString("maChamCongCN");
				Date ngayCCCN = rs.getDate("ngayCN");
				int soSPHoanThanh = rs.getInt("soSPHoanThanh");
				boolean trangThai = rs.getBoolean("trangThai");
				String maCN = rs.getString("maCN");
				CongNhan cn = new DAO_CongNhan().timCN(maCN);
				ChamCongCN ccCN = new ChamCongCN(maChamCong, ngayCCCN, soSPHoanThanh, trangThai, cn);
				dsChamCongCN.add(ccCN);
			}
			return dsChamCongCN;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ChamCongCN timCCCN(String maCCCN) {
		ArrayList<ChamCongCN> list = new DAO_ChamCongCN().getAllDanhSachChamCong();
		for (ChamCongCN dto_CCCN : list) {
			if (dto_CCCN.getMaChamCong().equalsIgnoreCase(maCCCN)) {
				return dto_CCCN;
			}
		}
		return null;
	}

}
