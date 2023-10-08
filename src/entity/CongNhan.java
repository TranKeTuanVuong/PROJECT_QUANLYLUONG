package entity;

import java.util.Date;
import java.util.Objects;

public class CongNhan {
	private String maCN, tenCN, diaChi, CMND;
	private boolean gioiTinh;
	private Double phuCap;
	private java.util.Date ngaySinh;
	private String anh;
	private NhanVien nhanVien;

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getMaCN() {
		return maCN;
	}

	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}

	public String getTenCN() {
		return tenCN;
	}

	public void setTenCN(String tenCN) {
		this.tenCN = tenCN;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public double getPhuCap() {
		return phuCap;
	}

	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}

	public java.util.Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(java.sql.Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public CongNhan() {
		super();
	}

	public CongNhan(String maCN) {
		super();
		this.maCN = maCN;
	}


	public CongNhan(String maCN, String tenCN, boolean gioiTinh, Date ngaySinh, String cMND, String diaChi,
			Double phuCap, String anh, NhanVien nhanVien) {
		super();
		this.maCN = maCN;
		this.tenCN = tenCN;
		this.diaChi = diaChi;
		CMND = cMND;
		this.gioiTinh = gioiTinh;
		this.phuCap = phuCap;
		this.ngaySinh = ngaySinh;
		this.anh = anh;
		this.nhanVien = nhanVien;

	}

	@Override
	public String toString() {
		return "CongNhan [maCN=" + maCN + ", tenCN=" + tenCN + ", diaChi=" + diaChi + ", CMND=" + CMND + ", gioiTinh="
				+ gioiTinh + ", phuCap=" + phuCap + ", ngaySinh=" + ngaySinh + ", anh=" + anh + ", nhanVien=" + nhanVien
				+ ", phanCong=" + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCN);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongNhan other = (CongNhan) obj;
		return Objects.equals(maCN, other.maCN);
	}

}
