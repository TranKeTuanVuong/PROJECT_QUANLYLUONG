package entity;

import java.util.Date;

public class TienLuongNV {
	private String maLuongNV;
	private Date thangLuong;
	private double tienLuong;
	private double tienThuong;
	private double tienPhat;
	private double tongLuong;
	private NhanVien nhanVien;

	public TienLuongNV() {
		super();
	}
	
	
	
	public TienLuongNV(String maLuongNV, Date thangLuong, double tienLuong, double tienThuong, double tienPhat,
			double tongLuong, NhanVien nhanVien) {
		super();
		this.maLuongNV = maLuongNV;
		this.thangLuong = thangLuong;
		this.tienLuong = tienLuong;
		this.tienThuong = tienThuong;
		this.tienPhat = tienPhat;
		this.tongLuong = tongLuong;
		this.nhanVien = nhanVien;
	}

	

	public TienLuongNV(double tienLuong, double tienThuong, double tienPhat, double tongLuong,
			NhanVien nhanVien) {
		super();
		this.tienLuong = tienLuong;
		this.tienThuong = tienThuong;
		this.tienPhat = tienPhat;
		this.tongLuong = tongLuong;
		this.nhanVien = nhanVien;
	}


	public String getMaLuongNV() {
		return maLuongNV;
	}

	public void setMaLuongNV(String maLuongNV) {
		this.maLuongNV = maLuongNV;
	}

	public Date getThangLuong() {
		return thangLuong;
	}

	public void setThangLuong(Date thangLuong) {
		this.thangLuong = thangLuong;
	}

	public double getTienLuong() {
		return tienLuong;
	}

	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}

	public double getTienThuong() {
		return tienThuong;
	}

	public void setTienThuong(double tienThuong) {
		this.tienThuong = tienThuong;
	}

	public double getTienPhat() {
		return tienPhat;
	}

	public void setTienPhat(double tienPhat) {
		this.tienPhat = tienPhat;
	}

	public double getTongLuong() {
		return tongLuong;
	}

	public void setTongLuong(double tongLuong) {
		this.tongLuong = tongLuong;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public TienLuongNV(String maLuongNV) {
		super();
		this.maLuongNV = maLuongNV;
	}

	@Override
	public String toString() {
		return "TienLuongNV [maLuongNV=" + maLuongNV + ", thangLuong=" + thangLuong + ", tienLuong=" + tienLuong
				+ ", tienThuong=" + tienThuong + ", tienPhat=" + tienPhat + ", tongLuong=" + tongLuong + ", nhanVien="
				+ nhanVien + "]";
	}

	

}
