/*
   BiNodeという二つのノードへ接続するデータ構造を考える
   BiNodeは(node1が左ノード、node2が右ノードを表す)二分木と
   (node1が前ノード、node2が後ノードを表す)双方向連結リスト両方に使用することができる
   このBiNodeによって表現された二分探索木を双方向連結リストに変換するメソッドを実装しなさい
   ただしノードの値は元の順序を保ち、適切に操作できるようにしなければならない
*/
/*
                  4
            2           5
        1       3           6
    0
    変換メソッドは次のような双方向連結リストに変形しなければならない
    0 <-> 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6
    ルートからスタートしてこれを再帰的に考える

    木構造の左右部分は連結リストに変換した場合も左側-ルート-右側のように連続的になっているので
    左右の部分木について再起的な操作で双方向連結リストへ変換することが単純に連結していくだけでできる
*/
/*  擬似コード
    BiNode convert(Binode node) {
        BiNode left = convert(node.left);
        BiNode right = convert(node.right);
        mergeLists(left, node, right);
        return left; //左の先頭
    }
*/
//各連結リストにおける先頭ノードheadと末尾ノードtailが必要になる
//データ構造を追加する方法
//連結リストのheadとtailだけを持ったNodePairという新たなデータ構造を用意してNodePairを返すconvertメソッドを用意する
private class NodePair {
    BiNode head;
    BiNode tail;
    public NodePair(BiNode head, BiNode tail) {
        this.head = head;
        this.tail = tail;
    }
}
public NodePair convert(BiNode root) {
    if (root == null) {
        return null;
    }
    NodePair part1 = convert(root.node1);
    NodePair part2 = convert(root.node2);
    if (part1 != null) {
        concat(part1.tail, root);
    }
    if (part2 != null) {
        concat(root, part2.head);
    }
    return new NodePair(part1 == null ? root : part1.head,
                        part2 == null ? root : part2.tail);
}
public static void concat(BiNode x, BiNode y) {
    x.node2 = y;
    y.node1 = x;
}
//末尾のノードを取得する方法(余分なデータ構造なし)
//NodePairを用いて連結リストのheadとtailを返す代わりにheadのみを返し、そのheadを元にtailを見つける
public static BiNode convert(BiNode root) {
    if (root == null) {
        return null;
    }
    BiNode part1 = convert(root.node1);
    BiNode part2 = convert(root.node2);
    if (part1 != null) {
        concat(getTail(part1), root);
    }
    if (part2 != null) {
        concat(root, part2);
    }
    return part1 == null ? root : part1;
}
public static BiNode getTail(BiNode node) {
    if (node == null) return null;
    while (node.node2 != null) {
        node = node.node2;
    }
    return node;
}
//getTailの呼び出し以外はほとんど同じで効率的でない
//深さdの葉ノードはgetTailメソッドによりd回も触れられることになりNをノード数とすると計算時間はO(N^2)になる
//環状連結リスト
//BiNodeによる連結リストのheadとtailを返すようにする
//循環連結リストのheadとして各リストを返すことにより可能になる。tailを得るには単純にhead.node1を呼び出す
public static BiNode convertToCircular(BiNode root) {
    if (root == null) {
        return null;
    }
    BiNode part1 = convertToCircular(root.node1);
    BiNode part3 = convertToCircular(root.node2);
    if (part1 == null && part3 == null) {
        root.node1 = root;
        root.node2 = root;
        return root;
    }
    BiNode tail3 = (part3 == null) ? null : part3.node1;
    //左を根につなげる
    if (part1 == null) {
        concat(part3.node1, root);
    } else {
        concat(part1.node1, root);
    }
    //右を根につなげる
    if (part3 == null) {
        concat(root, part1);
    } else {
        concat(root, part3);
    }
    //右と左をつなげる
    if (part1 != null && part3 != null) {
        concat(tail3, part1);
    }
    return part1 == null ? root : part1;
}
//リストを循環連結リストに変換し、循環部の連結を切る
public static BiNode convert(BiNode root) {
    BiNode head = convertToCircular(root);
    head.node1.node2 = null;
    head.node1 = null;
    return head;
}
//convertメソッドは環状連結リストのheadを得るためにこのメソッドを呼び出し、環状になっている連結を切り離す
//各ノードはO(1)回のアクセスになるので実行時間はO(N)になる
