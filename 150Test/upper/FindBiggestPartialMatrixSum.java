/*
 * 政府の整数を成分とするN * Nの行列がある時、合計値が最大になる部分行列を求める
 */

/*
 * ブルートフォース: O(N^6)
 * 最大化問題の最も単純な解法はブルートフォース
 * 単純に全ての部分行列について合計値をけんさんし、最大になるものを見つける
 *
 * 全ての部分行列を重複無しで調べるには行のペア、つまり行間と列のペアの組み合わせを全て回せば良い
 *
 * この開放では部分行列を調べるのにO(N^4)、各部分行列の和を計算するのにO(N^2)の計算時間がいる
 *
 * DPによる解法: O(N^4)
 * 行列の和を計算するのにO(1)にする
 *                    x1    x2
 *     -----------------------------------
 *     |              |     |            |
 *     |      A       |  C  |            |
 *     |              |     |            |
 *  y1 |--------------|-----|------------|
 *     |      B       |  D  |            |
 *  y2 |--------------|------------------|
 *     |              |                  |
 *     -----------------------------------
 * ValD = area(point(0, 0) -> point(x2, y2))
 * ValC = area(point(0, 0) -> point(x2, y1))
 * ValB = area(point(0, 0) -> point(x1, y2))
 * ValA = area(point(0, 0) -> point(x1, y1))
 *
 * Val*はそれぞれ原点からスタートし、各矩形の右下部分で終わっている
 * area(D) = ValD - area(A union C) - area(A union B) + area(A)
 * または
 * area(D) = ValD - ValB - ValC + ValA
 *
 * 同じような方法を用いて行列ないのあらゆる点に対してarea(point(0, 0) -> point(x, y))の値を効率的に計算することができる
 * Val(x, y) = Val(x - 1, y) + Val(y - 1, x) - Val(x - 1, y - 1) + Matrix(x, y)
 */
public class FindBiggestPartialMatrixSum {
    int getMaxMatrix(int[][] original) {
        // 負になりうる
        int maxArea = Integer.MIN_VALUE;
        int rowCount = original.length;
        int columnCount = original[0].length;
        int[][]matrix = precomputeMatrix(original);
        for (int row1 = 0; row1 < rowCount; row1++) {
            for (int row2 = row1; row2 < rowCount; row2++) {
                for (int col1 = 0; col1 < columnCount; col1++) {
                    for (int col2 = col1; col2 < columnCount; col2++) {
                        maxArea = Math.max(maxArea, computeSum(matrix, row1, row2, col1, col2));
                    }
                }
            }
        }
        return maxArea;
    }
    int[][] precomputeMatrix(int[][] matrix) {
        int[][] sumMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; i++) {
                if (i == 0 && j == 0) {
                    // 初めのセル
                    sumMatrix[i][j] = matrix[i][j];
                } else if (j == 0) {
                    // 最初の列のセル
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + matrix[i][j];
                } else if (i == 0) {
                    // 最初の行のセル
                    sumMatrix[i][j] = sumMatrix[i][j - 1] + matrix[i][j];
                } else {
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1] + matrix[i][j];
                }
            }
        }
        return sumMatrix;
    }
    int computeSum(int[][] sumMatrix, int i1, int i2, int j1, int j2) {
        if (i1 == 0 && j1 == 0) {
            // 0行0列が左上の頂点の場合
            return sumMatrix[i2][j2];
        }
        if (i1 == 0) {
            // 左上の頂点が0行目にある場合
            return sumMatrix[i2][j2] - sumMatrix[i2][j1 - 1];
        }
        if (j1 == 0) {
            // 左上の頂点が0列目にある場合
            return sumMatrix[i2][j2] - sumMatrix[i1 - 1][j2];
        }
        return sumMatrix[i2][j2] - sumMatrix[i2][j1 - 1] - sumMatrix[i1 - 1][j2] + sumMatrix[i1 - 1][j1 - 1];
    }
    /*
     * 最適解法: O(N^3)
     * 行数をR、列数をCとするとO(CR^2)の実行時間で解くことができる
     *
     * 整数配列が与えられてその部分配列の輪の最大値を求めるという問題(O(N))を応用する
     * 全ての部分行列は連続する行と列によって表される
     * 全ての行の連続する区間について、その行において和が最大になる列の区間を探す
     * maxSum = 0
     * foreach rowStart in rows
     *  foreach rowEnd in rows
     *      // rowStartとrowEndをそれぞれ上下の辺とするようなたくさんの部分行列の中から和が最大となるcolStartとcolEndを見つけ出す
     *      maxSum = max(runningMaxSum, maxSum)
     * return maxSum
     *
     *     rowStart
     * ----------------
     * | 9|-8| 1| 3|-2|
     * |-3| 7| 6|-2| 4|
     * | 6|-4|-4| 8|-7|
     * ----------------
     *  12 -5  3  9 -5
     *      rowEnd
     *
     * rowStartとrowEndを上下の辺とする部分行列において合計が最大になるcolStartとcolEndを見つけるには
     * 各列の要素を合計し、この問題の初めに説明したmaximumSubArrayを適用する
     * 上の部分行列では1列目から4列目までの合計が最大値になる
     * これは最大値を持つ部分行列が(rowStart, 1列目)から(rowEnd, 4列目)になるという意味
     * maxSum = 0
     * foreach rowStart in rows
     *  foreach rowEnd in rows
     *      foreach col in columns
     *          partialSum[col] = sum of matrix[rowStart, col] through matrix[rowEnd, col]
     *      runningMaxSum = maxSubArray(partialSum)
     *  maxSum = max(runningMaxSum, maxSum)
     * return maxSum
     *
     * rowStartからrowEndまでループするのでR*C回の計算をしているのでO(CR^3)
     * a[0] ... a[i]の加算を1から行なっているが、ループの一つ前でa[0] ... a[i - 1]の合計をすでに計算している(重複)
     * maxSum = 0
     * foreach rowStart in rows
     *  clear array partialSum
     *  foreach rowEnd in rows
     *      foreach col in columns
     *          partialSum[col] += matrix[rowEnd, col]
     *      runningMaxSum = maxSubArray(partialSum)
     *  maxSum = max(runningMaxSum, maxSum)
     * return maxSum
     */
    public static void clearArray(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            array[i] = 0;
        }
    }
    public static int maxSubMatrix(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        int[] partialSum = new int[colCount];
        // 暫定の最大値をからの部分行列の和を0とする
        int maxSum = 0;

        for (int rowStart = 0; rowStart < rowCount; rowStart++) {
            clearArray(partialSum);
            for (int rowEnd = rowStart; rowEnd < rowCount; rowEnd++) {
                for (int i = 0; i < colCount; i++) {
                    partialSum[i] += matrix[rowEnd][i];
                }
                int tmpMaxSum = maxSubArray(partialSum, colCount);
                // 座標が知りたい場合はここにコードを追加
                maxSum = Math.max(maxSum, tmpMaxSum);
            }
        }
        return maxSum;
    }
    public static int maxSubArray(int[] array, int N) {
        int maxSum = 0;
        int runningSum = 0;

        for (int i = 0; i < N; i++) {
            runningSum += array[i];
            maxSum = Math.max(maxSum, runningSum);
            // runningSumが0より小さければ列を伸ばす意味がないのでリセット
            if (runningSum < 0) {
                runningSum = 0;
            }
        }
        return maxSum;
    }
}
