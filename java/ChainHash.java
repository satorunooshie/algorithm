package java;

/**
 * The type Chain hash.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class ChainHash<K, V> {
    //ハッシュ表の大きさ
    private int size;
    //ハッシュ表
    private final Node<K, V>[] table;

    /**
     * 空のハッシュ表を作成するコンストラクタ
     *
     * @param capacity the capacity
     */
    public ChainHash(int capacity) {
        try {
            table = new Node[capacity];
            this.size = capacity;
        // } catch (OutOfMemeoryError e) {
        } catch (Exception e) {
            this.size = 0;
        }
    }

    /**
     * Hash data int.
     *
     * @param key the key
     * @return the int
     */
    public int hashData(Object key) {
        return key.hashCode() % size;
    }

    /**
     * ハッシュ関数によってキー値をハッシュ値に変換する
     * ハッシュ値をインデックスとするバケットに注目する
     * 着目したバケットが参照する線形リストを先頭から順に線形探索する
     * キー値と同じ値が見つかれば探索成功
     * 末尾まで操作して見つからなければ探索失敗
     *
     * @param key the key
     * @return v
     */
    public V search(K key) {
        int hash = hashData(key);
        Node<K, V> p = table[hash];

        while (p != null) {
            if (p.getKey().equals(key))
                return p.getData();
            p = p.next;
        }
        return null;
    }

    /**
     * ハッシュ値によってキー値をハッシュ値に変換する
     * ハッシュ値をインデックスとするバケットに注目する
     * バケットが参照する線形リストを先頭から順に線形探索する
     * キー値と同じ値が見つかればキー値は登録済みで挿入失敗
     * 最後まで探して見つからなければリストの先頭位置にノードを挿入
     *
     * @param key  the key
     * @param data the data
     * @return int
     */
    public int add(K key, V data) {
        int hash = hashData(key);
        Node<K, V> p = table[hash];

        while (p != null) {
            if (p.getKey().equals(key))
                return 1;
            p = p.next;
        }
        Node<K, V> temp = new Node<K, V>(key, data, table[hash]);
        table[hash] = temp;
        return 0;
    }

    /**
     * ハッシュ関数によってキー値をハッシュ値に変換する
     * ハッシュ値をインデックスとするバケットに注目する
     * バケットが参照する線形リストを先頭から順に線形探索する
     * キーと同じ値が見つかればそのノードをリストから削除そうでなければ削除失敗
     *
     * @param key the key
     * @return int
     */
    public int remove(K key) {
        //削除するデータのハッシュ値
        int hash = hashData(key);
        //着目ノード
        Node<K, V> p = table[hash];
        //前回着目ノード
        Node<K, V> pp = null;

        while (p != null) {
            if (p.getKey().equals(key)) {
                if (pp == null)
                    table[hash] = p.next;
                else
                    pp.next = p.next;
                return 0;
            }
            pp = p;
            //後続ノードに着目
            p = p.next;
        }
        return 1;
    }

    /**
     * Dump.
     */
    public void dump() {
        for (int i = 0; i < size; i++) {
            Node<K, V> p = table[i];
            System.out.printf("%02d  ", i);
            while (p != null) {
                System.out.printf("-> %s (%s)  ", p.getKey(), p.getData());
                p = p.next;
            }
            System.out.println();
        }
    }

    /**
     * The type Node.
     *
     * @param <K> the type parameter
     * @param <V> the type parameter
     */
    static class Node<K, V> {
        private final K key;
        private final V data;
        private Node<K, V> next;

        /**
         * Instantiates a new Node.
         *
         * @param key  the key
         * @param data the data
         * @param next the next
         */
        Node(K key, V data, Node<K, V> next) {
            this.key = key;
            this.data = data;
            this.next = next;
        }

        /**
         * Gets key.
         *
         * @return the key
         */
        K getKey() {
            return key;
        }

        /**
         * Gets data.
         *
         * @return the data
         */
        V getData() {
            return data;
        }

        public int hashCode() {
            return key.hashCode();
        }
    }
}