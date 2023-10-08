package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
public class DAO_TaiKhoan {
	
	public TaiKhoan taiKhoan(String tenTK){
		TaiKhoan taiKhoan = null;
		PreparedStatement stm = null;
		try {
			String sql="select maTK , tenTK , matKhau, tenNV , anh, chucvu , CMND from taikhoan as tk join nhanvien as nv on tk.nhanVien = nv.maNV where tk.tenTK = ?";
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			stm = con.prepareStatement(sql);
			
			stm.setString(1, tenTK);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String maTK=rs.getNString("maTK");
				String tenTaiKhoan=rs.getNString("tenTK");
				String matKhau=rs.getNString("matKhau");
				String maNV = rs.getString(4);
				String tenNV = rs.getNString("tenNV");
				String anh = rs.getNString("anh");
				String chucVu = rs.getString("chucvu");
				String CMND = rs.getString("CMND");
				NhanVien nhanVien = new NhanVien(maNV, tenNV,anh,chucVu, CMND);
				taiKhoan = new TaiKhoan(maTK, tenTaiKhoan, matKhau, nhanVien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taiKhoan;
	}
	public TaiKhoan getTaiKhoan(String tenTK){
		TaiKhoan taiKhoan = null;
		PreparedStatement stm = null;
		try {
			String sql="SELECT * FROM TAIKHOAN where tenTK = ?";
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			stm = con.prepareStatement(sql);
			
			stm.setString(1, tenTK);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				
				String tenTaiKhoan=rs.getNString("tenTK");
				String matKhau=rs.getNString("matKhau");
				String email = rs.getString("gmail");
				taiKhoan = new TaiKhoan(tenTaiKhoan, matKhau, email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taiKhoan;
	}
	public boolean updateAccount(String taiKhoan , String matKhau) {
		PreparedStatement stm = null;
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "update TaiKhoan set matKhau = ? where tenTK = ?";
			stm = con.prepareStatement(sql);
			
			stm.setString(1, matKhau);
			stm.setString(2, taiKhoan);
			n = stm.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return n > 0;
		
	}
}
