package entity;


import java.util.Date;
import java.util.Objects;

public class PhanCong {
	private String maPC;
	private Date ngayPC;
	private String congDoanSX;
	private int soLuong;
	private SanPham sanPham;

	public String getMaPC() {
		return maPC;
	}

	public void setMaPC(String maPC) {
		this.maPC = maPC;
	}

	public Date getNgayPC() {
		return ngayPC;
	}

	public void setNgayPC(Date ngayPC) {
		this.ngayPC = ngayPC;
	}

	public String getCongDoanSX() {
		return congDoanSX;
	}

	public void setCongDoanSX(String congDoanSX) {
		this.congDoanSX = congDoanSX;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public PhanCong() {
		super();
	}

	public PhanCong(String maPC) {
		super();
		this.maPC = maPC;
	}

	public PhanCong(String maPC, Date ngayPC, String congDoanSX, int soLuong, SanPham sanPham) {
		super();
		this.maPC = maPC;
		this.ngayPC = ngayPC;
		this.congDoanSX = congDoanSX;
		this.soLuong = soLuong;
		this.sanPham = sanPham;
	}

	@Override
	public String toString() {
		return "PhanCong [maPC=" + maPC + ", ngayPC=" + ngayPC + ", congDoanSX=" + congDoanSX + ", soLuong=" + soLuong
				+ ", sanPham=" + sanPham + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPC);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhanCong other = (PhanCong) obj;
		return Objects.equals(maPC, other.maPC);
	}

}
