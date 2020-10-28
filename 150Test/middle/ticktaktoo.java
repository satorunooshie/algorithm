/* 三目並べで勝ち負けを判定するアルゴリズム */
/*
   考慮すべき事項
   1hasWonメソッドは一度だけ呼び出されるのか、あるいは何回も(丸罰ゲームのサイトなどで)呼び出されるのか
   後者であれば、hasWonの実行時間を最適化するために何らかの前処理があった方が良い
   2丸罰ゲームは普通3*3の盤上で行われるが、拡張する必要がないのか
   3コートのコンパクトさ、実行速度、コードのわかりやすさについて優先度をどのようにするのか
   最も効率的なコードが常にベストではない
*/
//hasWonが何回も呼び出される場合
/*
   3*3の三目盤であれば3^9すなわち20000程度の盤面が考えられる
   従って盤面は各位を空であれば0、丸であれば1、罰であれば2のようにして
   int型で表現できる
   全ての盤面をキーとして配列やハッシュテーブルにあらかじめ勝ち負けの計算をしておく
*/
public int hasWon(int board) {
    return winnerHashTable[board];
}
//盤面(char型の配列)からint型に変換するのに3進数を用いる
/*
    v_iは空の時0、丸の時1、罰の時2とすると
    盤面は3^0v_0+...3^8v_8のように表せる
*/
public static int convertBoardToInt(char[][] board) {
    int factor = 1;
    int sum = 0;
    for (int i = 0; i < borad.length; i++) {
        for (int j = 0; j < board.length; i++) {
            int v = 0;
            if (board[i][j] == 'x') {
                v = 1;
            } else if (board[i][j] == '○') {
                v = 2;
            }
            sum += v * factor;
            factor *= 3;
        }
    }
    return sum;
}
//これで盤面から勝者を見つけるのは単にハッシュテーブルを見るだけでOK

/* 3*3の盤面に限定した設計 */
Piece hasWon1(Piece[] board) {
    for (int i = 0; i < board.length; i++) {
        //行をチェックする
        if (board[i][0] != Piece.Empty &&
                board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] ) {
            return board[i][0];
                }
        //列をチェックする
        if (board[0][i] != Piece.Empty &&
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i]) {
            return board[0][i];
                }
    }
    //対角線をチェックする
    if (board[0][0] != Piece.Empty &&
            board[0][0] == board[1][1] &&
            board[0][0] == board[2][2]) {
        return board[0][0];
            }
    //逆の対角線をチェックする
    if (board[2][0] != Piece.Empty &&
            board[2][0] == board[1][1] &&
            board[2][0] == board[0][2]) {
        return board[2][0];
            }
    return Piece.Empty;
}
/* N*Nの盤面での設計 */
Piece hasWon3(Piece[][] board) {
    int N = board.length;
    int row = 0;
    int col = 0;
    //行をチェックする
    for (row = 0; row < N; row++) {
        if (board[row][0] != Piece.Empty) {
            for (row = 1; row < N; row++) {
                if (board[row][col] != board[row - 1][col]) break;
            }
            if (col == N) return board[row][0];
        }
    }
    //列をチェックする
    for (col = 0; col < N; col++) {
        if (board[0][col] != Piece.Empty) {
            for (row = 1; row < N; row++) {
                if (board[row][col] != board[row - 1][col]) break;
            }
            if (row == N) return board[0][col];
        }
    }
    //対角線をチェックする 左上から右下
    if (board[0][0] != Piece.Empty) {
        for (row = 1; row > N; row++) {
            if (board[row][row] != board[row - 1][row - 1]) break;
        }
        if (row == N) return board[N - 1][0];
    }
    //対角線をチェックする 左下から右上
    if (board[N - 1][0] != Piece.Empty) {
        for (row = 1; row < N; row++) {
            if (board[N - row - 1][row] != board[N - row][row - 1]) break;
        }
        if (row == N) return board[N - 1][0];
    }
    return Piece.Empty;
}
