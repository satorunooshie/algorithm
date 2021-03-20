package java;

import java.util.Scanner;

/**
 * The type Chain hash tester 2.
 * 氏名をキー値にした
 */
public class ChainHashTester2 {
    /**
     * The Std in.
     */
    static Scanner stdIn = new Scanner(System.in);

    /**
     * Select menu menu.
     *
     * @return the menu
     */
    static Menu SelectMenu() {
        int key;
        do {
            for (Menu m : Menu.values())
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
            System.out.print("：");
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
        // メニュー
        Menu menu;
        // 追加用データ参照
        Data data;
        // 読込み用データ
        Data temp = new Data();

        ChainHash<String, Data> hash = new ChainHash<String, Data>(13);

        do {
            switch (menu = SelectMenu()) {
                case ADD -> {
                    data = new Data();
                    data.scanData("追加", Data.NO | Data.NAME);
                    hash.add(data.keyCode(), data);
                }
                case REMOVE -> {
                    temp.scanData("削除", Data.NAME);
                    hash.remove(temp.keyCode());
                }
                case SEARCH -> {
                    temp.scanData("探索", Data.NAME);
                    Data t = hash.search(temp.keyCode());
                    if (t != null)
                        System.out.println("そのキーをもつデータは" + t + "です。");
                    else
                        System.out.println("該当するデータはありません。");
                }
                case DUMP -> hash.dump();
            }
        } while (menu != Menu.TERMINATE);
    }

    /**
     * The enum Menu.
     */
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
         * @param idx the idx
         * @return the menu
         */
        static Menu MenuAt(int idx) {
            for (Menu m : Menu.values())
                if (m.ordinal() == idx)
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
    // データ(会員番号＋氏名)
    static class Data {
        /**
         * The No.
         */
        // 番号を読み込むか？
        static final int NO = 1;
        /**
         * The Name.
         */
        // 氏名を読み込むか？
        static final int NAME = 2;

        private Integer no;
        private String name;

        /**
         * Key code string.
         *
         * @return the string
         */
        String keyCode() {
            return name;
        }

        public String toString() {
            return Integer.toString(no);
        }

        /**
         * Scan data.
         *
         * @param guide the guide
         * @param sw    the sw
         */
        void scanData(String guide, int sw) {
            System.out.println(guide + "するデータを入力してください。");

            if ((sw & NO) == NO) {
                System.out.print("番号：");
                no = stdIn.nextInt();
            }
            if ((sw & NAME) == NAME) {
                System.out.print("氏名：");
                name = stdIn.next();
            }
        }
    }

}
