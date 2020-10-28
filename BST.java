class BST {
    public int data; //要素の値
    public int left; //左側の要素にたどるポインタ
    public int right; //右側の要素にたどるポインタ
}

public class BinarySearchTree {
    //二分探索木の実体となる配列
    //最大要素数は10
    public static BST[] tree = new BST[10];
    //根の要素の添字
    //連結リストの先頭ポインタ
    public static int rootIdx = 0;
    //次に格納する要素の添字
    public static int newIdx = 0;
    //二分探索木に要素を追加するメソッド
    public static void addBST(int data) {
        int currentIdx; //現在辿っている要素の添字
        boolean addFlag; //追加が完了したことを示すフラグ
        //物理的な位置に追加する
        tree[newIdx].data = data;
        tree[newIdx].left = -1;
        tree[newIdx].right = -1;
        //根のデータでないなら論理的な位置にポインタを設定する
        if (newIdx != rootIdx) {
            currentIdx = rootIdx; //根から二分探索木をたどる
            addFlag = false; //追加は完了してない
            do {
                //より小さい場合は左側にたどる
                if (data < tree[currentIdx].data) {
                    //左側が末端ならそこに追加する
                    if (tree[currentIdx].left == -1) {
                        tree[currentIdx].left = newIdx;
                        addFlag = true;
                    }
                    //左側が末端ではないならさらに左側の要素を辿る
                    else {
                        currentIdx = tree[currentIdx].left;
                    }
                }
                //より大きい場合は右側にたどる
                //同じ値はないとする
                else {
                    //右側が末端ならそこに追加する
                    if (tree[currentIdx].right == -1) {
                        tree[currentIdx].right = newIdx;
                        addFlag = true;
                    }
                    //右側が末端ではないならさらに右側の要素を辿る
                    else {
                        currentIdx = tree[currentIdx].right;
                    }
                }
            } while (addFlag == false);
        }
        //次に格納するための添字を一つ増やしておく
        newIdx++;
    }
    //二分探索木の実体の配列を物理的な順序で表示するメソッド
    public static void printPhysicalBST() {
        int i;
        for (i = 0; i < newIdx; i++) {
            System.out.printf(
                    "tree[%d]:data = %d, left = %d, right = %d\n",
                    i, tree[i].data, tree[i].left, tree[i].right);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new BST();
        }
        //二分探索木を構築して、物理的な順序で表示する
        addBST(4);
        addBST(6);
        addBST(5);
        addBST(2);
        addBST(3);
        addBST(7);
        addBST(1);
        printPhysicalBST();
    }
}
