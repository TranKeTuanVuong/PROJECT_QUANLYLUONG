package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connect.ConnectDB;
import entity.Kho;

public class DAO_Kho {
	public ArrayList<Kho> getAllKho(){
		ArrayList<Kho> list=new ArrayList<Kho>();
		
		try {
			String sql="Select * from Kho";
			Connection con=ConnectDB.getConnection();
			Statement statement=con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				String maKho=rs.getString(1);
				String diaChi=rs.getString(2);
				Kho kho=new Kho(maKho,diaChi);
				list.add(kho);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<String> getTenKho(){
		ArrayList<String> list=new ArrayList<String>();
		
		try {
			String sql="Select diaChi from Kho";
			Connection con=ConnectDB.getConnection();
			Statement statement=con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while(rs.next()) {
				String diaChi=rs.getNString("diaChi");
				list.add(diaChi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Kho timKho(String maKho) {
		ArrayList<Kho> list=new DAO_Kho().getAllKho();
		for (Kho dto_Kho : list) {
			if(dto_Kho.getMaKho().equals(maKho)){
				return dto_Kho;
			}
		}
		return null;
	}
}
