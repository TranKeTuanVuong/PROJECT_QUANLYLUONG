package entity;

public class Kho {
	private String maKho;
	private String diaChi;

	public String getMaKho() {
		return maKho;
	}

	public void setMaKho(String maKho) {
		this.maKho = maKho;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Kho(String maKho, String diaChi) {
		super();
		this.maKho = maKho;
		this.diaChi = diaChi;
	}

	public Kho() {
		super();
	}

	public Kho(String maKho) {
		super();
		this.maKho = maKho;
	}

	@Override
	public String toString() {
		return "Kho [maKho=" + maKho + ", diaChi=" + diaChi + "]";
	}

}
