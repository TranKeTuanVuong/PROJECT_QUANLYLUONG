package entity;

import java.util.Date;

public class ChiTietPhanCong {
	private CongNhan congNhan;
	private PhanCong phanCong;
	private int soLuongPC;
	private Date ngayCTPC;

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public PhanCong getPhanCong() {
		return phanCong;
	}

	public void setPhanCong(PhanCong phanCong) {
		this.phanCong = phanCong;
	}

	public int getSoLuongPC() {
		return soLuongPC;
	}

	public void setSoLuongPC(int soLuongPC) {
		this.soLuongPC = soLuongPC;
	}

	public Date getNgayCTPC() {
		return ngayCTPC;
	}

	public void setNgayCTPC(Date ngayCTPC) {
		this.ngayCTPC = ngayCTPC;
	}

	public ChiTietPhanCong() {
		super();
	}

	public ChiTietPhanCong(CongNhan congNhan, PhanCong phanCong, int soLuongPC, Date ngayCTPC) {
		super();
		this.congNhan = congNhan;
		this.phanCong = phanCong;
		this.soLuongPC = soLuongPC;
		this.ngayCTPC = ngayCTPC;
	}

	@Override
	public String toString() {
		return "ChiTietPhanCong [congNhan=" + congNhan + ", phanCong=" + phanCong + ", soLuongPC=" + soLuongPC
				+ ", ngayCTPC=" + ngayCTPC + "]";
	}

}
