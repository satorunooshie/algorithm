/*
 * 隠せるが白か黒である正方行列がある時、
 * 周囲が全て黒で囲まれている最大の正方形を見つけるアルゴリズム
 */

/*
 * シンプルな解法: O(N^4)
 *
 * 正方行列をN * Nとする
 * いっぺんがNの正方形が可能な最大サイズであり、それはただ一つしかない
 * この正方形が条件を満たしているかは簡単に判定できる
 *
 * N*Nの正方形が条件を満たしてitないならば次に大きい(N - 1) * (N - 1)の正方形を探す
 * このサイズの正方形を全てチェックし最初に見つかったものを返すようにする
 */
public class FindBiggestSquare{}
class Subsquare {
    Subsquare findSquare(int[][] matrix) {
        for (int i = matrix.length; i >= 1; i--) {
            Subsquare square = findSquareWithSize(matrix, i);
            if (square != null) return square;
        }
        return null;
    }
    Subsquare findSquareWithSize(int[][] matrix, int squareSize) {
        /*
         * 長さNの辺上には
         * (N - squareSize + 1)このsquareSizeの大きさの正方形がある
         */
        int count = matrix.length - squareSize + 1;
        /* 辺の長さがsquareSizeの正方形全てを回す */
        for (int row = 0; row < count; row++) {
            for (int col = 0; col < count; col++) {
                if (isSquare(matrix, row, col, squareSize)) {
                    return new Subsquare(row, col, squareSize);
                }
            }
        }
        return null;
    }

    boolean isSquare(int[][] matrix, int row, int col, int size) {
        // 上下の境界をチェックする
        for (int j = 0; j < size; j++) {
            if (matrix[row][col + j] == 1) {
                return false;
            }
            if (matrix[row + size - 1][col + j] == 1) {
                return false;
            }
        }
        // 左右の境界をチェックする
        for (int i = 1; i < size - 1; i++) {
            if (matrix[row + i][col] == 1) {
                return false;
            }
            if (matrix[row + i][col + size - 1] == 1) {
                return false;
            }
        }
        return true;
    }
/*
 * 前処理を行う解法: O(N^3)
 *
 * 前述の解法は正方形であるかどうかのチェックにO(N)の計算時間が必要だったため、遅かった
 * いくつかの前処理を行うことでisSquareの計算時間をO(1)に抑えることができる
 *
 * isSquareが何をしているか分析すれば、注目している頂点の右側のsquareSize個のセルと
 * 下側のsquareSize個のセルが全て黒(0)になっているかどうかが分かれば十分
 * これは動的計画法で前に計算することができる
 *
 * 正方行列を右から左、下から上に走査し、各セルで次の計算を行う
 * A[r][c]が白ならば、A[r][c].zerosRightとA[r][c].zerosBelowを0にする
 * そうでなければ、
 * A[r][c].zerosRight = A[r][c + 1].zerosRight + 1
 * A[r][c].zerosBelow = A[r + 1][c].zerosBelow + 1とする
 *
 * (右にある黒(0)の個数、下にある黒の個数)
 * |0,0|1,3|0,0|
 * |2,2|1,2|0,0|
 * |2,1|1,1|0,0|
 *
 * (Original Matrix)
 * |W|B|W|
 * |B|B|W|
 * |B|B|W|
 *
 * isSquareメソッドでO(N)の走査を行う代わりに各角に対して、zerosRightとzerosBelowの値をチェックするだけで判定できるようになる
 *
 * なお、findSquare, findSquareWithSizeは前回と同じ
 */
    boolean isSquare(SquareCell[][] matrix, int row, int col, int size) {
        SquareCell topLeft = matrix[row][col];
        SquareCell topRight = matrix[row][col + size - 1];
        SquareCell bottomRight = matrix[row + size - 1][col];
        if (topLeft.zerosRight < size) {
            // 上の辺をチェックする
            return false;
        }
        if (topLeft.zerosBelow < size) {
            return false;
        }
        if (topRight.zerosBelow < size) {
            return false;
        }
        if (topRight.zerosRight < size) {
            return false;
        }
        return true;
    }

    SquareCell[][] processSquare(int[][] matrix) {
        SquareCell[][] processed = new SquareCell[matrix.length][matrix.length];
        for (int r = matrix.length - 1; r >= 0; r--) {
            for (int c = matrix.length - 1; c >= 0; c--) {
                int rightZeros = 0;
                int belowZeros = 0;
                // 黒のセルだった場合だけ処理する必要がある
                if (matrix[r][c] == 0) {
                    rightZeros++;
                    belowZeros++;
                    // 隣の列は同じ行にある
                    if (c + 1 < matrix.length) {
                        SquareCell previous = processed[r][c + 1];
                        rightZeros += previous.zerosBelow;
                    }
                    if (r + 1 < matrix.length) {
                        SquareCell previous = processed[r + 1][c];
                        belowZeros += previous.zerosBelow;
                    }
                }
                processed[r][c] = new SquareCell(rightZeros, belowZeros);
            }
        }
        return processed;
    }
}
class SquareCell {
    public int zerosRight = 0;
    public int zerosBelow = 0;
    SquareCell(int rightZeros, int belowZeros) {
        this.zerosRight = rightZeros;
        this.zerosBelow = belowZeros;
    }
}
