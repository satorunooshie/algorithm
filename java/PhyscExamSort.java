package java;

import java.util.Arrays;
import java.util.Comparator;

/**
 * NEED:
 * PhysicalExamination.class
 * PhysExamSearch.class
 */
class PhyscExamSort {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        PhyscData[] x = {
                new PhyscData("加藤忠正", 162, 0.3),
                new PhyscData("赤坂忠雄", 172, 0.7),
                new PhyscData("前田淳二", 192, 1.8),
                new PhyscData("長田長信", 152, 1.5),
                new PhyscData("佐藤圭一", 168, 1.3),
                new PhyscData("西田一郎", 173, 0.8),
                new PhyscData("多田治", 159, 0.2),
                new PhyscData("後藤凛", 178, 0.4),
                new PhyscData("久保田力", 182, 2.0),
        };
        Arrays.sort(x, PhyscData.HEIGHT_ORDER);
        System.out.println("**身体検査一覧表**");
        System.out.println("氏名    身長 視力");
        System.out.println("---------------");
        for (PhyscData physcData : x)
            System.out.printf("%-8s%3d%5.1f\n", physcData.name, physcData.height, physcData.vision);
        Arrays.sort(x, PhyscData.VISION_ORDER);
        System.out.println("**身体検査一覧表**");
        System.out.println("氏名    身長 視力");
        System.out.println("---------------");
        for (PhyscData physcData : x)
            System.out.printf("%-8s%3d%5.1f\n", physcData.name, physcData.height, physcData.vision);
    }

    /**
     * The type Physc data.
     */
    static class PhyscData {
        /*
         * ジェネリクスは処理対象となる型に依存しない汎用的なクラスやメソッドを型安全な手法で実現する機能
         * クラス名やインターフェース名の直後に<Type>といった形式の型変数を置いて宣言し、型変数は複数指定することができる
         * このように定義されたクラスやインターフェースは引数として型を受け取ることになり、処理の対象となるオブジェクトの型に依存しない
         * 標準的な命名規則は以下の通り
         * ・できるだけ一文字の大文字を使用して小文字は使わない
         * ・コレクション内の要素型はelementの頭文字Eとする
         * ・マップ内のキー型と値型を表す場合にはkeyとvalueの頭文字K,Vとする
         * ・一般的な方はTで表す
         * なお方変数にはワイルドカードを指定することもできる
         * <? extends T>
         * <? super T>
         */
        // not public
        /**
         * The Height order.
         */
        static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();
        /**
         * The Vision order.
         */
        static final Comparator<PhyscData> VISION_ORDER = new VisionOrderComparator();
        /**
         * The Name.
         */
        String name;
        /**
         * The Height.
         */
        int height;
        /**
         * The Vision.
         */
        double vision;

        /**
         * Instantiates a new Physc data.
         *
         * @param name   the name
         * @param height the height
         * @param vision the vision
         */
        PhyscData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }

        public String toString() {
            return name + " " + height + " " + vision;
        }

        private static class HeightOrderComparator implements Comparator<PhyscData> {
            public int compare(PhyscData d1, PhyscData d2) {
                return Integer.compare(d1.height, d2.height);
            }
        }

        private static class VisionOrderComparator implements Comparator<PhyscData> {
            public int compare(PhyscData d1, PhyscData d2) {
                return Double.compare(d1.vision, d2.vision);
            }
        }
    }
}
