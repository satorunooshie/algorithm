import java.util.Scanner;

/**
 * ナップサック問題
 * 耐重量が示された一つのナップサックと
 * 重量と価値が示された複数の品物がある
 * 耐重量を超えないように品物を選んで、ナップサックに詰める
 * 価値の合計が最大となる品物の組み合わせを求めよ
 *
 * 例)
 *  耐重量6kg
 *  品物5種類
 *      A.1kg:100yen
 *      B.2kg:300yen
 *      C.3kg:350yen
 *      D.4kg:500yen
 *      E.5kg:650yen
 *  但し、同じ品物を複数選ぶことはできないとする
 *  もしも全ての組み合わせをチェックするとそれぞれ2通りあるので、2^5=32通りある
 *  品物の種類をnとすればO(2^n)なので指数時間アルゴリズムになってしまう
 *
 *  動的計画法を使う
 *  問題を分割して、耐重量0~6kgの7つのナップサックに1~5種類の品物を選んで詰めたときの最大値を得るという部分問題に分割して解く
 */
public class Knapsack {
    private static final int KNAP_MAX = 6;
    private static final int ITEM_NUM = 5;
    private static char[] name = { 'A', 'B', 'C', 'D', 'E' };
    private static int[] weight = { 1, 2, 3, 4, 5 };
    private static int[] value = { 100, 300, 350, 500, 650 };
    private static int[][] maxValue = new int[ITEM_NUM][KNAP_MAX + 1];
    private static int[] lastItem = new int[KNAP_MAX + 1];
    public static void show(int item) {
        //0~6kgのナップ
        int knap;
        //吟味した品物の情報を表示
        System.out.printf("<%c, %dkg, %d円を吟味した結果>\n", name[item], weight[item], value[item]);
        //ナップサックの耐重量を表示
        for (knap = 0; knap <= KNAP_MAX; knap++)
            System.out.printf("%dkg\t", knap);
        System.out.println();
        //ナップサックに詰められた品物の価値の合計
        for (knap = 0; knap <= KNAP_MAX; knap++)
            System.out.printf("%d円\t", maxValue[item][knap]);
        System.out.println();
        //ナップサックに最後に入れた品物
        for (knap = 0; knap <= KNAP_MAX; knap++) {
            if (lastItem[knap] != -1)
                System.out.printf("%c", name[lastItem[knap]]);
            else
                System.out.print("なし");
            System.out.print("\t");
        }
        System.out.println("\n");
    }
    public static void main(String[] args) {
        //品物の番号、0~6のナップサック、価値の合計、重量の合計
        int item, knap, totalValue, totalWeight;
        //0番目の品物を吟味
        item = 0;
        for (knap = 0; knap <= KNAP_MAX; knap++)
            //耐重量以下なら選ぶ
            if (weight[item] <= knap) {
                maxValue[item][knap] = value[item];
                lastItem[knap] = item;
            } else {
                maxValue[0][knap] = 0;
                lastItem[knap] = -1;
            }
        show(item);
        //1番目からITEM_NUM-1番目の品物を吟味
        for (item = 1; item < ITEM_NUM; item++) {
            for (knap = 0; knap <= KNAP_MAX; knap++) {
                //耐重量以下
                if (weight[item] <= knap) {
                    //選んだ場合の価値
                    totalValue = maxValue[item - 1][knap - weight[item]] + value[item];
                    //価値が大きくなるなら選ぶ
                    if (totalValue > maxValue[item - 1][knap]) {
                        maxValue[item][knap] = totalValue;
                        lastItem[knap] = item;
                    } else {
                        maxValue[item][knap] = maxValue[item - 1][knap];
                    }
                } else
                    maxValue[item][knap] = maxValue[item - 1][knap];
            }
            show(item);
        }
        System.out.println("<ナップサックに入っている品物を調べる");
        totalWeight = 0;
        for (knap = KNAP_MAX; knap > 0; knap -= weight[item]) {
            item = lastItem[knap];
            System.out.printf("%dkgのナップサックに入れた最後の品物は%c\n", knap, name[item]);
            totalWeight += weight[item];
            System.out.printf(" %c, %dkg, %d円\n", name[item], weight[item], value[item]);
            System.out.printf(" %dkg - %dkg = %dkg\n", knap, weight[item], knap - weight[item]);
        }
        System.out.println("\n<解を表示>");
        System.out.printf("重量の合計 = %dkg\n", totalWeight);
        System.out.printf("価値の最大値 = %d円\n", maxValue[ITEM_NUM - 1][KNAP_MAX]);

        System.out.println("遺伝的アルゴリズム");
        GA.main(args);
    }
}
class GA {
    //ナップサックの耐重量
    private static final int KNAP_MAX = 6;
    //品物の種類
    private static final int ITEM_NUM = 5;
    //個体の数
    private static final int IND_NUM = 8;
    //突然変異する確率
    private static final double MUTATE_RATE = 0.1;
    private static final char[] name = { 'A', 'B', 'C', 'D', 'E' };
    private static final int[] weight = { 1, 2, 3, 4, 5 };
    private static final int[] value = { 100, 300, 350, 500, 650 };
    //個体の世代
    private static int indGeneration;
    //個体の遺伝子
    private static final int[][] indGene = new int[IND_NUM][ITEM_NUM];
    //個体の重量
    private static final int[] indWeight = new int[IND_NUM];
    //個体の価値
    private static final int[] indValue = new int[IND_NUM];
    //個体の適応度
    private static final int[] indFitness = new int[IND_NUM];

    static void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
    //個体をランダムに作成する
    public static void createIndividual() {
        //0か1をランダムに格納する
        for (int ind = 0; ind < IND_NUM; ind++)
            for (int item = 0; item < ITEM_NUM; item++)
                indGene[ind][item] = Math.random() > 0.5 ? 0 : 1;
    }
    //個体の重量、価値、適応度を計算するメソッド
    public static void calcIndividual() {
        for (int ind = 0; ind < IND_NUM; ind++) {
            indWeight[ind] = indValue[ind] =  0;
            for (int item = 0; item < ITEM_NUM; item++)
                if (indGene[ind][item] == 1) {
                    indWeight[ind] += indWeight[item];
                    indValue[ind] += indValue[item];
                }
            //適応度を計算する
            if (indWeight[ind] <= KNAP_MAX)
                //耐重量以下なら価値をそのまま適応度とする
                indFitness[ind] = indValue[ind];
            else
                //耐重量を超えているなら適応度を0とする
                indFitness[ind] = 0;
        }
    }
    //個体の情報を表示するメソッド
    public static void showIndividual() {
        System.out.printf("\n<第%d世代>\n", indGeneration);
        System.out.print("遺伝子\t\t重量\t\t価値\t\t適応度\n");
        for (int ind = 0; ind < IND_NUM; ind++) {
            for (int item = 0; item < ITEM_NUM; item++) {
                System.out.printf("[%d]", indGene[ind][item]);
            }
            System.out.printf("\t\t%2dkg\t\t%4d円\t\t%4d\n", indWeight[ind], indValue[ind], indFitness[ind]);
        }
        System.out.println();
    }
    //個体を適応度の大きい順にソートする
    public static void sortIndividual() {
        //挿入する要素、挿入する位置
        int pos, ins;
        for (pos = 1; pos < IND_NUM; pos++) {
            ins = pos;
            while (ins >= 1 && indFitness[ins - 1] < indFitness[ins]) {
                for (int item = 0; item < ITEM_NUM; item++) {
                    int tmp = indGene[ins - 1][item];
                    indGene[ins - 1][item] = indGene[ins][item];
                    indGene[ins][item] = tmp;
                }
                swap(indWeight, ins - 1, ins);
                swap(indValue, ins - 1, ins);
                swap(indFitness, ins - 1, ins);
                ins--;
            }
        }
    }
    //淘汰するメソッド
    public static void selectIndividual() {
        //適応度の上位50%を下位50%にコピーする(下位50%を淘汰する)
        for (int ind = 0; ind < IND_NUM / 2; ind++)
            /*
            for (int item = 0; item < ITEM_NUM; item++)
                indGene[ind + IND_NUM / 2][item] = indGene[ind][item];
             */
            System.arraycopy(indGene[ind], 0, indGene[ind + IND_NUM / 2], 0, ITEM_NUM);
        System.out.println("下位50%を淘汰しました");
    }

    public static void crossoverIndividual() {
        //交叉する位置
        int crossoverPoint, tmp;
        //下位50%にコピーした個体を対象とする
        for (int ind = IND_NUM / 2; ind < (IND_NUM - 1); ind += 2) {
            crossoverPoint = (int)(Math.random() * 10000) % (ITEM_NUM - 1) + 1;
            for (int item = crossoverPoint; item < ITEM_NUM; item++) {
                //隣の個体と交叉する
                tmp = indGene[ind][item];
                indGene[ind][item] = indGene[ind + 1][item];
                indGene[ind + 1][item] = tmp;
            }
            System.out.printf("個体%dと個体%dを%dの位置で交叉しました\n", ind, ind + 1, crossoverPoint);
        }
    }

    public static void mutateIndividual() {
        //下位50%にコピーした個体を対象とする
        for (int ind = IND_NUM / 2; ind < IND_NUM; ind++)
            for (int item = 0; item < ITEM_NUM; item++)
                if (Math.random() <= MUTATE_RATE) {
                    //反転する
                    indGene[ind][item] ^= 1;
                    System.out.printf("個体%dの%dの位置で突然変異しました\n", ind, item);
                }
    }
    public static void main(String[] args) {
        int genMax;
        Scanner stdIn = new Scanner(System.in);
        System.out.print("最大の世代:");
        genMax = stdIn.nextInt();

        //第一世代の個体を生成する
        indGeneration = 1;
        createIndividual();
        //適応度を計算する
        calcIndividual();
        //適応度が大きい順にソートする
        sortIndividual();
        showIndividual();
        indGeneration++;
        while (indGeneration <= genMax) {
            sortIndividual();
            selectIndividual();
            crossoverIndividual();
            mutateIndividual();
            calcIndividual();
            sortIndividual();
            showIndividual();
            indGeneration++;
        }
        //最も適応度の高い個体を解として表示する
        System.out.println("<ナップサックに入っている品物を表示する");
        for (int item = 0; item < ITEM_NUM; item++)
            if (indGene[0][item] == 1)
                System.out.printf("%c, %dkg, %d円\n", name[item], weight[item], value[item]);
        System.out.println("\n<解を表示する>");
        System.out.printf("重量の合計値 = %dkg\n", indWeight[0]);
        System.out.printf("価値の最大値 = %d円\n", indValue[0]);
    }
}