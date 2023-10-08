package entity;

public class TaiKhoan {
	private String maTK;
	private String tenTK;
	private String matKhau;
	private NhanVien nhanVien;
	private String email;
	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String tenTK) {
		super();
		this.tenTK = tenTK;
	}

	public TaiKhoan(String maTK, String tenTK, String matKhau, NhanVien nhanVien, String email) {
		super();
		this.maTK = maTK;
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
		this.email = email;
	}
	public TaiKhoan(String maTK, String tenTK, String matKhau, NhanVien nhanVien) {
		super();
		this.maTK = maTK;
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}
	public TaiKhoan(String tenTK, String matKhau, String email) {
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.email = email;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTK=" + maTK + ", tenTK=" + tenTK + ", matKhau=" + matKhau + ", nhanVien=" + nhanVien + "]";
	}

}
