package entity;

import java.util.Date;

public class ChamCongCN {
	private String maChamCong;
	private java.util.Date ngayCN;
	private int soSPHoanThanh;
	private boolean trangThai;
	private CongNhan congNhan;

	public String getMaChamCong() {
		return maChamCong;
	}

	public void setMaChamCong(String maChamCong) {
		this.maChamCong = maChamCong;
	}

	public java.util.Date getNgayCN() {
		return ngayCN;
	}

	public void setNgayCN(java.util.Date ngayCN) {
		this.ngayCN = ngayCN;
	}

	public int getSoSPHoanThanh() {
		return soSPHoanThanh;
	}

	public void setSoSPhoanThanh(int soSPHoanThanh) {
		this.soSPHoanThanh = soSPHoanThanh;
	}


	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public ChamCongCN() {
		super();
	}

	public ChamCongCN(String maChamCong) {
		super();
		this.maChamCong = maChamCong;
	}

	public ChamCongCN(String maChamCong, Date ngayCN, int soSPHoanThanh,  boolean trangThai,
			CongNhan congNhan) {
		super();
		this.maChamCong = maChamCong;
		this.ngayCN = ngayCN;
		this.soSPHoanThanh = soSPHoanThanh;
		this.trangThai = trangThai;
		this.congNhan = congNhan;
	}

	@Override
	public String toString() {
		return "ChamCongCN [maChamCong=" + maChamCong + ", ngayCN=" + ngayCN + ", soSPHoanThanh=" + soSPHoanThanh
				+ ", trangThai=" + trangThai + ", congNhan=" + congNhan + "]";
	}

	
}
