import java.util.Scanner;

/**
 * ハッシュ表探索法
 * 1回の処理で目的のデータが見つかるので理想的にはO(1)
 * 1.データのハッシュ値を求める
 * 2.hashTable[ハッシュ値]を読み出す
 */
public class HashTableSearch {
    //10で割った余は必ず9個になる
    private static int[] hashTable = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    private static Scanner stdIn = new Scanner(System.in);
    private static int data, hashValue;
    private static int notFoundIndex = -1;
    private static boolean retryFlg;

    public static int hash(int data) {
        return data % 10;
    }

    public static void insert(int data) {
        insert(data, false);
    }

    //for trace
    public static void insert(int data, boolean getTrace) {
        hashValue = hash(data);
        if (getTrace) System.out.printf("ハッシュ値 = %d %% 10 = %d\n", data, hashValue);
        hashTable[hashValue] = data;
        if (getTrace) System.out.printf("hashTable[%d]に%dを格納\n", hashValue, data);
    }

    public static int search(int data) {
            hashValue = hash(data);
            if (hashTable[hashValue] == data) return hashValue;
            return notFoundIndex;
    }

    public static void main(String[] args) {
        System.out.println("シノニム未対応のハッシュ表探索法");
        do {
            System.out.print("格納するデータ:");
            data = stdIn.nextInt();
            if (data < 0) break;
            insert(data);
            //insert(data, true);
            System.out.println();
            System.out.print("続けますか{Yes(1)No(0)}:");
            retryFlg = (stdIn.nextInt() > 0) ? true : false;
        } while (retryFlg);
        System.out.println();
        do {
            System.out.print("検索するデータ:");
            data = stdIn.nextInt();
            if (data < 0) break;
            if (search(data) == notFoundIndex)
                System.out.println("そのデータは存在しません");
            else
                System.out.printf("%dで見つかりました\n", hashValue);
            System.out.print("続けますか{Yes(1)No(0)}:");
            retryFlg = (stdIn.nextInt() > 0) ? true : false;
        } while (retryFlg);

        //--------------------------------------------------------------------
        System.out.println();
        System.out.println("シノニムに対応したハッシュ表探索法");
        do {
            System.out.print("格納するデータ:");
            data = stdIn.nextInt();
            if (data < 0) break;
            insertAgstSyn(data);
            //insertAgsSyn(data, true);
            System.out.println();
            System.out.print("続けますか{Yes(1)No(0)}:");
            retryFlg = (stdIn.nextInt() > 0) ? true : false;
        } while (retryFlg);
        System.out.println();
        do {
            System.out.print("検索するデータ:");
            data = stdIn.nextInt();
            if (data < 0) break;
            pos = searchAgstSyn(data);
            //pos = searchAgstSyn(data, true);
            System.out.println(pos);
            if (pos == notFoundIndex)
                System.out.println("そのデータは存在しません");
            else
                System.out.printf("%dで見つかりました\n", pos);
            System.out.print("続けますか{Yes(1)No(0)}:");
            retryFlg = (stdIn.nextInt() > 0) ? true : false;
        } while (retryFlg);
    }

    /**
     * Synonym対策
     * --格納--
     *  1.ハッシュ値を求める
     *  2.hashTable[ハッシュ値]が-1でない場合は1つ先の要素に格納する
     *  3.末尾を超えた場合は先頭の要素に戻る
     *  4.ハッシュ値の位置まで戻った場合はハッシュ表がいっぱいですと表示する
     * --検索--
     *  1.サーチするデータのハッシュ値を求める
     *  2.ハッシュ値の位置にあるデータが-1でなくかつ目的のデータと異なる場合は一つ先の要素を検索し、末尾の要素を超えた場合は先頭の要素に戻る
     *  3.検索したデータが見つかった場合は見つかった位置を表示する
     *  4.-1を見つけるか、または、検索を開始した位置まで戻った場合は見つかりませんと表示する
     */
    //格納位置、探索位置
    private static int pos;

    public static void insertAgstSyn(int data) {
        insertAgstSyn(data, false);
    }

    public static void insertAgstSyn(int data, boolean getTrace) {
        hashValue = hash(data);
        if (getTrace) System.out.printf("ハッシュ値 = %d %% 10 = %d\n", data, hashValue);
        pos = hashValue;
        if (getTrace) System.out.printf("格納位置pos = %d\n", pos);
        while (hashTable[pos] != notFoundIndex) {
            pos++;
            if (pos >= hashTable.length) pos = 0;
            if (getTrace) System.out.printf("hashTable[%d]に%dを格納\n", pos, data);
            //ハッシュ値の位置まで戻ったらハッシュ表がいっぱい
            if (pos == hashValue) break;
        }
        if (hashTable[pos] == notFoundIndex)
            hashTable[pos] = data;
        else
            System.out.println("ハッシュテーブルがいっぱいです");
        for (int v : hashTable) System.out.println(v);
    }

    public static int searchAgstSyn(int data) {
        return searchAgstSyn(data, false);
    }

    public static int searchAgstSyn(int data, boolean getTrace) {
        hashValue = hash(data);
        if (getTrace) System.out.printf("ハッシュ値 = %d %% 10 = %d\n", data, hashValue);
        pos = hashValue;
        if (getTrace) System.out.printf("探索位置pos = %d\n", pos);
        while (hashTable[pos] != notFoundIndex && hashTable[pos] != data) {
            pos++;
            if (pos >= hashTable.length) pos = 0;
            if (getTrace) System.out.printf("探索位置pos = %d\n", pos);
            //-1を見つけるか、ハッシュ値の位置まで戻ったらデータが存在しないことが確定
            if (hashTable[pos] == notFoundIndex || pos == hashValue) return notFoundIndex;
        }
        return pos;
    }
}