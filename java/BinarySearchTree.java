class BST {
    private int data; //要素の値
    private int left; //左側の要素にたどるポインタ
    private int right; //右側の要素にたどるポインタ

    public int getData() {
        return data;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }
}

public class BinarySearchTree {
    //二分探索木の実体となる配列
    //最大要素数は10
    private final static BST[] tree = new BST[10];
    //根の要素の添字
    //連結リストの先頭ポインタ
    private final static int rootIndex = 0;
    //次に格納する要素の添字
    private static int newIndex = 0;
    private final static int notFoundIndex = -1;
    //二分探索木に要素を追加するメソッド
    public static void add(int data) {
        int currentIndex; //現在辿っている要素の添字
        boolean addFlag; //追加が完了したことを示すフラグ
        //物理的な位置に追加する
        tree[newIndex].setData(data);
        tree[newIndex].setLeft(notFoundIndex);
        tree[newIndex].setRight(notFoundIndex);
        //根のデータでないなら論理的な位置にポインタを設定する
        if (newIndex != rootIndex) {
            currentIndex = rootIndex; //根から二分探索木をたどる
            addFlag = false; //追加は完了してない
            do {
                //より小さい場合は左側にたどる
                if (data < tree[currentIndex].getData()) {
                    //左側が末端ならそこに追加する
                    if (tree[currentIndex].getLeft() == notFoundIndex) {
                        tree[currentIndex].setLeft(newIndex);
                        addFlag = true;
                    }
                    //左側が末端ではないならさらに左側の要素を辿る
                    else {
                        currentIndex = tree[currentIndex].getLeft();
                    }
                }
                //より大きい場合は右側にたどる
                //同じ値はないとする
                else {
                    //右側が末端ならそこに追加する
                    if (tree[currentIndex].getRight() == notFoundIndex) {
                        tree[currentIndex].setRight(newIndex);
                        addFlag = true;
                    }
                    //右側が末端ではないならさらに右側の要素を辿る
                    else {
                        currentIndex = tree[currentIndex].getRight();
                    }
                }
            } while (addFlag == false);
        }
        //次に格納するための添字を一つ増やしておく
        newIndex++;
    }
    public static int search(int x) {
        //見つかった要素の添字
        int index = notFoundIndex;
        //現在たどっている要素の添字
        int currentIndex = rootIndex;
        while (currentIndex != notFoundIndex) {
            if (tree[currentIndex].getData() == x) {
                index = currentIndex;
                break;
            } else if (tree[currentIndex].getData() > x)
                currentIndex = tree[currentIndex].getLeft();
            else
                currentIndex = tree[currentIndex].getRight();
        }
        return index;
    }
    //再帰による検索
    public static int search(int x, int currentIndex) {
        if (currentIndex == notFoundIndex)
            return notFoundIndex;
        else {
            if (tree[currentIndex].getData() == x)
                return currentIndex;
            //return (tree[currentIndex].getData() > x) ? search(x, tree[currentIndex].getLeft() : search(tree[currentIndex].getRight()));
            else if (tree[currentIndex].getData() > x)
                return search(x, tree[currentIndex].getLeft());
            else
                return  search(x, tree[currentIndex].getRight());
        }
    }
    //二分探索木の実体の配列を物理的な順序で表示するメソッド
    public static void printPhysically() {
        int i;
        for (i = 0; i < newIndex; i++) {
            System.out.printf( "tree[%d]:data = %d, left = %d, right = %d\n", i, tree[i].getData(), tree[i].getLeft(), tree[i].getRight());
        }
    }
    //二分探索木を論理的な順序(深さ優先探索)で表示するメソッド
    public static void printLogically(int currentIndex) {
        if (currentIndex != notFoundIndex) {
            System.out.printf("tree[%d]: data = %d, left = %d, right = %d\n", currentIndex, tree[currentIndex].getData(), tree[currentIndex].getLeft(), tree[currentIndex].getRight());
            //再帰
            printLogically(tree[currentIndex].getLeft());
            printLogically(tree[currentIndex].getRight());
        }
    }
    public static void main(String[] args) {
        //インスタンス生成
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new BST();
        }
        //二分探索木を構築して、物理的な順序で表示する
        add(4);
        add(6);
        add(5);
        add(2);
        add(3);
        add(7);
        add(1);
        printPhysically();
        System.out.println("--------------------------------------------------------------------------------");
        printLogically(rootIndex);
        System.out.printf("5の検索結果 = %d\n", search(5));
        System.out.printf("8の検索結果 = %d\n", search(8));
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("5の検索結果(再帰) = %d\n", search(5, rootIndex));
        System.out.printf("8の検索結果(再帰) = %d\n", search(8, rootIndex));
    }
}
