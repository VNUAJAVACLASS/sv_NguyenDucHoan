package vnua.fita.tkb;

public class demo {	
	public static void main(String[] args) throws Exception {
		CtrinhChinh ctchinh = new CtrinhChinh();
		
		ctchinh.loadHTML("C:/Users/LOQ/eclipse-workspace/TKB_Vnua/src/main/java/vnua/fita/tkb/tkb_nguyenduchoan.html");
		
		ctchinh.xemThoiKhoaBieuHomNay();
		
	}
}
