package entity;

import java.util.Date;

public class TienLuongCN {
	private String maLuongCN;
	private Date thangLuong;
	private double tienLuong;
	private double tienThuong;
	private double tienPhat;
	private double tongLuong;
	private CongNhan congNhan;

	public String getMaLuongCN() {
		return maLuongCN;
	}

	public void setMaLuongCN(String maLuongCN) {
		this.maLuongCN = maLuongCN;
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

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public TienLuongCN() {
		super();
	}

	public TienLuongCN(String maLuongCN) {
		super();
		this.maLuongCN = maLuongCN;
	}

	public TienLuongCN(String maLuongCN, Date thangLuong, double tienLuong, double tienThuong, double tienPhat,
			double tongLuong, CongNhan congNhan) {
		super();
		this.maLuongCN = maLuongCN;
		this.thangLuong = thangLuong;
		this.tienLuong = tienLuong;
		this.tienThuong = tienThuong;
		this.tienPhat = tienPhat;
		this.tongLuong = tongLuong;
		this.congNhan = congNhan;
	}

	@Override
	public String toString() {
		return "TienLuongCN [maLuongCN=" + maLuongCN + ", thangLuong=" + thangLuong + ", tienLuong=" + tienLuong
				+ ", tienThuong=" + tienThuong + ", tienPhat=" + tienPhat + ", tongLuong=" + tongLuong + ", congNhan="
				+ congNhan + "]";
	}

}
