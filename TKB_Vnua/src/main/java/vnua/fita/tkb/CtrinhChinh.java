package vnua.fita.tkb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CtrinhChinh {
	private Map<Integer, Tuan> danhSachTuan = new HashMap<>();

	public void loadHTML(String filePath) throws Exception {
		Document doc = Jsoup.parse(new File(filePath), "UTF-8");

		Elements rows = doc.select("tr");
		for (Element row : rows) {
			Elements cols = row.select("td");
			if (cols.size() < 13)
				continue;

			String maMon = cols.get(0).text().trim();
			String tenMon = cols.get(1).text().trim();
			String nhom = cols.get(2).text().trim();
			int tietBatDau = Integer.parseInt(cols.get(9).text().trim());
			int soTiet = Integer.parseInt(cols.get(10).text().trim());
			String phong = cols.get(11).text().trim();
			String giangVien = cols.get(12).text().trim();
			String thu = cols.get(8).text().trim();
			String thoigianHoc = cols.get(13).text().trim();

			LichHoc lichHoc = new LichHoc(maMon, tenMon, nhom, tietBatDau, soTiet, phong, giangVien);

			int thuchuyen = 0;
			if (!thu.equals("CN")) {
				thuchuyen = Integer.parseInt(thu);
			} else {
				thuchuyen = 8;
			}

		for (int i = 0; i < thoigianHoc.length(); i++) {
				char kyTu = thoigianHoc.charAt(i);
				if (kyTu != '-' && kyTu != ' ') {
					danhSachTuan.putIfAbsent(1, new Tuan());
					danhSachTuan.get(1).getThu(thuchuyen).themLichHoc(lichHoc);
				}
			}
		}
	}

	public void xemThoiKhoaBieuHomNay() {
		LocalDate today = LocalDate.now();
		int thu = today.getDayOfWeek().getValue() + 1;
		int tuan = 2;
		Tuan t = danhSachTuan.get(tuan);
		if (t != null) {
			Thu th = t.getThu(thu);
			if (th != null) {
				List<LichHoc> ds = th.getDanhSachLichHoc();
				if (ds.isEmpty()) {
					System.out.println("Không có môn học nào hôm nay.");
				}
				for (LichHoc lich : ds) {
					System.out.println(lich.toString());

				}
			}
		} else {
			System.out.println("Không có lịch học hôm nay");
		}
	}

}