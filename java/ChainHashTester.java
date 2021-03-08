import java.util.Scanner;

class ChainHashTester {
    static Scanner stdIn = new Scanner(System.in);
    //会員番号+氏名
    static class Data {
        //番号を読み込むか
        static final int NO = 1;
        //氏名を読み込むか
        static final int NAME = 2;
        private Integer no;
        private String name;

        Integer KeyCode() {
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

    //メニュー列挙型
    enum Menu {
        ADD("追加"),
        REMOVE("削除"),
        SEARCH("探索"),
        DUMP("表示"),
        TERMINATE("終了");
        private final String message;
        //序数がindexである列挙を返す
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

    //メニュー選択
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
        //追加用データ参照
        Data data;
        //読み込み用データ
        Data temp = new Data();

        ChainHash<Integer, Data> hash = new ChainHash<Integer, Data>(13);
        do {
            switch (menu = SelectMenu()) {
                case ADD:
                    data = new Data();
                    data.scanData("追加", Data.NO | Data.NAME);
                    hash.add(data.keyCode(), data);
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
                    break;
                case DUMP:
                    hash.dump();
                    break;
            }
        } while (menu != Menu.TERMINATE);
    }
}
