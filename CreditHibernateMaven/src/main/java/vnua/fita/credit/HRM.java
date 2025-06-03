package vnua.fita.credit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HRM {
	private List<Human> hrList;

	public HRM() {
		hrList = new ArrayList<Human>();
	}

	public void addHr(Human human) {
		hrList.add(human);
	}

	public void addHr(Scanner sc) {
		int loai;
		System.out.println("Chon loai nhan su (sv: 0, gv: 1): ");
		loai = sc.nextInt();
		sc.nextLine();

		Human human = null;
		if (loai == 0) {
			human = new Student();
		} else if (loai == 1) {
			human = new Lecturer();
		}

		human.enterInfo(sc);

		addHr(human);
	}

	public void printHRList() {
		for (Human human : hrList) {
			System.out.println(human.toString());
		}
	}

	public void printStudenList() {
		for (Human human : hrList) {
			if (human instanceof Student) {
				Student std = (Student) human;
				System.out.println(std.getClass_());
			}
		}
	}

	public void initDemoData() {
		Human h1 = new Student("78689", "Nguyen van luuyen", "K78574");
		Human h2 = new Student("686933", "Le hai ha", "K85633");

		addHr(h1);
		addHr(h2);
	}

	public void initDemoData(Scanner sc) {
		String chon;
		do {
			// Nhap thong tin nhan su
			addHr(sc);

			// Hoi muon nhap tiep khong
			System.out.println("Ban muon nhap tiep khong(c/k): ");
			chon = sc.nextLine();
		} while ("c".equalsIgnoreCase(chon));
	}

	public List<Human> searchHuman(String code) {
		List<Human> humanList = new ArrayList<Human>();
		for (Human human : hrList) {
			if (human.code.contains(code)) {
				humanList.add(human);
			}
		}

		return humanList;
	}

	public static void main(String[] args) {
		HRM hrm = new HRM();
		hrm.initDemoData();
		Scanner sc = new Scanner(System.in);
		hrm.initDemoData(sc);
		hrm.printHRList();
		hrm.searchHuman("66");
	}
}