package vnua.fita.tkb;

import java.util.ArrayList;
import java.util.List;

public class KyHoc {
    private List<Tuan> dsTuan = new ArrayList<>();
    private final int soTuan = 22;

    public KyHoc() {
        for (int i = 0; i < soTuan; i++) {
            dsTuan.add(new Tuan());
        }
    }

    public Tuan getTuan(int tuanThu) {
        if (tuanThu < 1 || tuanThu > soTuan) return null;
        return dsTuan.get(tuanThu - 1);
    }

    public int getSoTuan() {
        return soTuan;
    }

}
