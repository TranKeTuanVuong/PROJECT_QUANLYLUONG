package entity;

import java.util.Date;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String tenNhanVien;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String diaChi;
	private String CMND;
	private String chucVu;
	private String trinhDoHocVan;
	private String kinhNghiem;
	private String anh;
	private PhongBan phongBan;
	private Kho kho;
	private double heSoLuong;
	private double phuCap;

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public java.sql.Date getNgaySinh() {
		return (java.sql.Date) ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
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

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getTrinhDoHocVan() {
		return trinhDoHocVan;
	}

	public void setTrinhDoHocVan(String trinhDoHocVan) {
		this.trinhDoHocVan = trinhDoHocVan;
	}

	public String getKinhNghiem() {
		return kinhNghiem;
	}

	public void setKinhNghiem(String kinhNghiem) {
		this.kinhNghiem = kinhNghiem;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	public Kho getKho() {
		return kho;
	}

	public void setKho(Kho kho) {
		this.kho = kho;
	}

	public double getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(double heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public double getPhuCap() {
		return phuCap;
	}

	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public NhanVien() {
		super();
	}

	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

	public NhanVien(String maNhanVien, String tenNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
	}

	public NhanVien(String maNhanVien, String tenNhanVien, PhongBan phongBan) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.phongBan = phongBan;
	}
	
	

	public NhanVien(String maNhanVien, String tenNhanVien, Kho kho) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.kho = kho;
	}

	public NhanVien(String maNhanVien, String tenNhanVien, String anh, String chucVu,String cMND ) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		CMND = cMND;
		this.chucVu = chucVu;
		this.anh = anh;
	}

	public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, Date ngaySinh, String diaChi, String cMND,
			String chucVu, String trinhDoHocVan, String kinhNghiem, double heSoLuong, double phuCap, String anh,
			PhongBan phongBan, Kho kho) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		CMND = cMND;
		this.chucVu = chucVu;
		this.trinhDoHocVan = trinhDoHocVan;
		this.kinhNghiem = kinhNghiem;
		this.anh = anh;
		this.heSoLuong = heSoLuong;
		this.phuCap = phuCap;
		this.phongBan = phongBan;
		this.kho = kho;

	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", CMND=" + CMND + ", chucVu=" + chucVu
				+ ", trinhDoHocVan=" + trinhDoHocVan + ", kinhNghiem=" + kinhNghiem + ", anh=" + anh + ", phongBan="
				+ phongBan + ", kho=" + kho + ", heSoLuong=" + heSoLuong + ", phuCap=" + phuCap + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

}
