import java.util.Scanner;

public class OpenHashTester {
    static Scanner stdIn = new Scanner(System.in);
    static class Data {
        static final int NO = 1;
        static final int NAME = 2;
        private Integer no;
        private String name;
        Integer keyCode() {
            return no;
        }
        public String toString() {
            return name;
        }
        void scanData(String guide, int sw) {
            System.out.println(guide + "するデータを入力してください");
            if ((sw & NO) == NO) {
                System.out.print("番号:");
                no = stdIn.nextInt();
            }
            if ((sw & NAME) == NAME) {
                System.out.print("氏名:");
                name = stdIn.next();
            }
        }
    }
    enum Menu {
        ADD("追加"),
        REMOVE("削除"),
        SEARCH("探索"),
        DUMP("表示"),
        TERMINATE("終了");
        private final String message;
        static Menu MenuAt(int index) {
            for (Menu m : Menu.values())
                if (m.ordinal() == index)
                    return m;
            return null;
        }
        Menu(String string) {
            message = string;
        }
        String getMessage() {
            return message;
        }
    }
    static Menu SelectMenu() {
        int key;
        do {
            for (Menu m : Menu.values())
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
            System.out.print(" : ");
            key = stdIn.nextInt();
        } while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());
        return Menu.MenuAt(key);
    }
    public static void main(String[] args) {
        Menu menu;
        Data data;
        Data temp = new Data();
        OpenHash<Integer, Data> hash = new OpenHash<Integer, Data>(13);
        do {
            switch (menu = SelectMenu()) {
                case ADD:
                    data = new Data();
                    data.scanData("追加", Data.NO | Data.NAME);
                    int k = hash.add(data.keyCode(), data);
                    switch (k) {
                        case 1:
                            System.out.println("そのキー値は登録済みです");
                            break;
                        case 2:
                            System.out.println("ハッシュ表が満杯です");
                            break;
                    }
                    break;
                case REMOVE:
                    temp.scanData("削除", Data.NO);
                    hash.remove(temp.keyCode());
                    break;
                case SEARCH:
                    temp.scanData("探索", Data.NO);
                    Data t = hash.search(temp.keyCode());
                    if (t != null)
                        System.out.println("そのキーを持つデータは" + t);
                    else
                        System.out.println("該当するデータはありません");
                case DUMP:
                    hash.dump();
                    break;
            }
        } while (menu != Menu.TERMINATE);
    }
}