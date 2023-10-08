package entity;

public class PhongBan {
	private String maPB;
	private String tenPB;

	public String getMaPB() {
		return maPB;
	}

	public void setMaPB(String maPB) {
		this.maPB = maPB;
	}

	public String getTenPB() {
		return tenPB;
	}

	public void setTenPB(String tenPB) {
		this.tenPB = tenPB;
	}

	public PhongBan(String maPB, String tenPB) {
		super();
		this.maPB = maPB;
		this.tenPB = tenPB;
	}

	public PhongBan() {
		super();
	}

	public PhongBan(String maPB) {
		super();
		this.maPB = maPB;
	}

	@Override
	public String toString() {
		return "PhongBan [maPB=" + maPB + ", tenPB=" + tenPB + "]";
	}

}
