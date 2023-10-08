package boundary;

import entity.TaiKhoan;

public class JavaMail {
	public JavaMail(TaiKhoan taiKhoan) {
		try {
			run(taiKhoan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void run(TaiKhoan taiKhoan) throws Exception {
		int random=(int) Math.floor(((Math.random()*899999)+100000));
		String key = random+ "";
		CheckMail.sendMail(taiKhoan.getEmail(), key);
		new XacNhanEmail(taiKhoan , key).setVisible(true);
	}
}
