package vnua.fita.tkb;

import java.util.HashMap;
import java.util.Map;

public class Tuan {
    private Map<Integer, Thu> danhSachThu = new HashMap<>();
    private final int soThu = 7;
   
    
    public Tuan() {
        for (int i = 0; i < soThu; i++) {
            danhSachThu.put(i, new Thu());
        }
    }

    public Thu getThu(int thu) {
        if (thu < 2 || thu > soThu + 1 ) return null;
        return danhSachThu.get(thu-2);
    }
}
