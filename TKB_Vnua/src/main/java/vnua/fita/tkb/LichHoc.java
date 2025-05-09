package vnua.fita.tkb;

public class LichHoc {
	private String maMon;
	private String tenMon;
	private String nhom;
	private int tietBatD;
	private int soTiet;
	private String phong;
	private String giangVien;
	
	public LichHoc() {}
	
	public LichHoc(String maMon, String tenMon, String nhom, int tietBatD, int soTiet, String phong, String giangVien) {
		// TODO Auto-generated constructor stub
		this.maMon = maMon;
		this.tenMon = tenMon;
		this.nhom = nhom;
		this.tietBatD = tietBatD;
		this.soTiet = soTiet;
		this.phong = phong;
		this.giangVien = giangVien;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return maMon+"--"+tenMon+"--"+nhom+"--"+tietBatD+"--"+soTiet+"--"+phong+"--"+giangVien;
	}
}
