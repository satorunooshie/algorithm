package java;

import java.util.Scanner;

/**
 * The type Chain hash tester.
 * NEED: ChainHash.class
 */
class ChainHashTester {
    /**
     * The Std in.
     */
    static Scanner stdIn = new Scanner(System.in);

    /**
     * Select menu menu.
     *
     * @return the menu
     */
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

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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

    /**
     * The enum Menu.
     */
//メニュー列挙型
    enum Menu {
        /**
         * Add menu.
         */
        ADD("追加"),
        /**
         * Remove menu.
         */
        REMOVE("削除"),
        /**
         * Search menu.
         */
        SEARCH("探索"),
        /**
         * Dump menu.
         */
        DUMP("表示"),
        /**
         * Terminate menu.
         */
        TERMINATE("終了");
        private final String message;

        Menu(String string) {
            message = string;
        }

        /**
         * Menu at menu.
         *
         * @param index the index
         * @return the menu
         */
//序数がindexである列挙を返す
        static Menu MenuAt(int index) {
            for (Menu m : Menu.values())
                if (m.ordinal() == index)
                    return m;
            return null;
        }

        /**
         * Gets message.
         *
         * @return the message
         */
        String getMessage() {
            return message;
        }
    }

    /**
     * The type Data.
     */
//会員番号+氏名
    static class Data {
        /**
         * The constant NO.
         */
//番号を読み込むか
        static final int NO = 1;
        /**
         * The constant NAME.
         */
//氏名を読み込むか
        static final int NAME = 2;
        private Integer no;
        private String name;

        /**
         * Key code integer.
         *
         * @return the integer
         */
        Integer keyCode() {
            return no;
        }

        public String toString() {
            return name;
        }

        /**
         * Scan data.
         *
         * @param guide the guide
         * @param sw    the sw
         */
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
}
