package entity;

import java.util.Date;
import java.util.Scanner;

public class ChamCongNV {
	private String maChamCong;
	private Date ngayCN;
	private boolean phep;
	private int soGioTangCa;
	private boolean coMat;
	private boolean trangThai;
	private String ghiChu;
	private NhanVien nhanVien;

	public ChamCongNV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChamCongNV(String maChamCong) {
		super();
		this.maChamCong = maChamCong;
	}

	public ChamCongNV(String maChamCong, Date ngayCN, boolean phep, int soGioTangCa, boolean coMat, boolean trangThai,
			String ghiChu, NhanVien nhanVien) {
		super();
		this.maChamCong = maChamCong;
		this.ngayCN = ngayCN;
		this.phep = phep;
		this.soGioTangCa = soGioTangCa;
		this.coMat = coMat;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.nhanVien = nhanVien;
	}
	

	public ChamCongNV(String maChamCong, Date ngayCCNV, boolean phep, boolean coMat, String ghiChu, NhanVien nhanVien) {
		super();
		this.maChamCong = maChamCong;
		this.ngayCN = ngayCCNV;
		this.phep = phep;
		this.coMat = coMat;
		this.ghiChu = ghiChu;
		this.nhanVien = nhanVien;
	}
	
	public ChamCongNV(String maChamCong, Date ngayCN, int soGioTangCa, boolean coMat, boolean trangThai, String ghiChu,
			NhanVien nhanVien) {
		super();
		this.maChamCong = maChamCong;
		this.ngayCN = ngayCN;
		this.soGioTangCa = soGioTangCa;
		this.coMat = coMat;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.nhanVien = nhanVien;
	}

	public String getMaChamCong() {
		return maChamCong;
	}

	public void setMaChamCong(String maChamCong) {
		this.maChamCong = maChamCong;
	}

	public Date getNgayCN() {
		return ngayCN;
	}

	public void setNgayCN(Date ngayCN) {
		this.ngayCN = ngayCN;
	}

	public boolean isPhep() {
		return phep;
	}

	public void setPhep(boolean phep) {
		this.phep = phep;
	}

	public int getSoGioTangCa() {
		return soGioTangCa;
	}

	public void setSoGioTangCa(int soGioTangCa) {
		this.soGioTangCa = soGioTangCa;
	}

	public boolean isCoMat() {
		return coMat;
	}

	public void setCoMat(boolean coMat) {
		this.coMat = coMat;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "ChamCongNV [maChamCong=" + maChamCong + ", ngayCN=" + ngayCN + ", phep=" + phep + ", soGioTangCa="
				+ soGioTangCa + ", coMat=" + coMat + ", trangThai=" + trangThai + ", ghiChu=" + ghiChu + "]";
	}

}
