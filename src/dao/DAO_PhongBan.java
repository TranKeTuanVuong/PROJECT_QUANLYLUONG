package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.PhongBan;
public class DAO_PhongBan {
	public ArrayList<PhongBan> getAllPhongBan(){
		ArrayList<PhongBan> list=new ArrayList<PhongBan>();
		
		try {
			String sql="Select * from PhongBan";
			Connection con=ConnectDB.getConnection();
			Statement statement=con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				String maPB=rs.getString(1);
				String tenPB=rs.getString(2);
				PhongBan pb=new PhongBan(maPB,tenPB);
				list.add(pb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<String> getTenPB(){
		ArrayList<String> list=new ArrayList<String>();
		
		try {
			String sql="Select tenPB from PhongBan";
			Connection con=ConnectDB.getConnection();
			Statement statement=con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				String tenPB=rs.getString("tenPB");
				list.add(tenPB);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public PhongBan timPhongBan(String maPB) {
		ArrayList<PhongBan> list=new DAO_PhongBan().getAllPhongBan();
		for (PhongBan dto_PB : list) {
			if(dto_PB.getMaPB().equals(maPB)){
				return dto_PB;
			}
		}
		return null;
	}
}
