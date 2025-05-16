package vnua.fita.vn;

import java.util.ArrayList;
import java.util.List;

public class Thu {
    private List<LichHoc> DSLichHoc = new ArrayList<>();

    public void themLichHoc(LichHoc lichHoc) {
        DSLichHoc.add(lichHoc);
    }

    public List<LichHoc> getDanhSachLichHoc() {
        return DSLichHoc;
    }
}
