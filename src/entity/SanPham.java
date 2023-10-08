package entity;

import java.util.Objects;

public class SanPham {
	private String maSP;
	private String tenSP;
	private double donGia;
	private String anh;
	private Kho kho;

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public Kho getKho() {
		return kho;
	}

	public void setKho(Kho kho) {
		this.kho = kho;
	}

	public SanPham() {
		super();
	}

	public SanPham(String maSP, String tenSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
	}

	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}

	public SanPham(String maSP, String tenSP, double donGia, String anh, Kho kho) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donGia = donGia;
		this.anh = anh;
		this.kho = kho;
	}

	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", donGia=" + donGia + ", anh=" + anh + ", kho=" + kho
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSP, other.maSP);
	}

}
