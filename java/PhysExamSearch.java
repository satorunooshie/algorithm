import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

/**
 * PhyscExamSort.class
 * PhysicalExamination.class
 */
class PhysExamSearch {
    static class PhyscData {
        private String name;
        private int height;
        private double vision;

        public PhyscData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }

        public String toString() {
            return name + " " + height + " " + vision;
        }

        public static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();

        /**
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
        private static class HeightOrderComparator implements Comparator<PhyscData> {
            public int compare(PhyscData d1, PhyscData d2) {
                return (d1.height > d2.height) ? 1 :
                        (d1.height < d2.height) ? -1 : 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
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
        System.out.print("何cmの人を探しますか:");
        int height = stdIn.nextInt();
        int index = Arrays.binarySearch(
                x,
                new PhyscData("", height, 0.0),
                PhyscData.HEIGHT_ORDER
        );
        if (index < 0)
            System.out.println("その値は存在しません");
        else {
            System.out.println("その値はx[" + index + "]にあります");
            System.out.println("データ:" + x[index]);
        }
    }
}