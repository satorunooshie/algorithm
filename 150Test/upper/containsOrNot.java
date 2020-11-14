/*
 * 文字列sとそれより短い文字列の配列tがあるとき、配列tの各文字列に対して、文字列sに含まれているかを検索するメソッド
 */
/*
 *                          /|\
 *                        /  | \
 *                      /    |  \
 *                    b      I   S
 *                   / \      \
 *                  I   S      B
 *                 /            \
 *                B              C
 *               /
 *              S
 * まず文字列sのサフィックス木を作るとbibsという文字列では上のようになる
 * 次に配列tの各文字列をサフィックス木の中から探す
 * もしBを探すのであれば2箇所で見つかることになる
 */
public class SuffixTree {
    SuffixTreeNode root = new SuffixTreeNode();
    public SuffixTree(String s) {
        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(i);
            root.insertString(suffix, i);
        }
    }
    public ArrayList<Integer> search(String s) {
        return root.search(s);
    }
}
public class SuffixTreeNode {
    HashMap<Character, SuffixTreeNode> children = new HashMap<Character, SuffixTreeNode>();
    char value;
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    public SuffixTreeNode {}
    public void insertString(String s, int index) {
        indexes.add(index);
        if (s != null && s.length() > 0) {
            value = s.charAt(0);
            SuffixTreeNode child = null;
            if (children.containsKey(value)) {
                child = children.get(value);
            } else {
                child = new SuffixTreeNode();
                children.put(value, child);
            }
            String remainder = s.substring(1);
            child.insertString(remainder, index);
        }
    }
    public ArrayList<Integer> search(String s) {
        if (s == null || s.length() == 0) {
            return indexes;
        } else {
            char first = s.charAt(0);
            if (children.containsKey(first)) {
                String remainder = s.substring(1);
                return children.get(first).search(remainder);
            }
        }
        return null;
    }
}

