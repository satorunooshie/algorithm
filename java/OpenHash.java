public class OpenHash<K, V> {
    enum Status {
        OCCUPIED,
        EMPTY,
        DELETED;
    };
    static class Bucket<K, V> {
        private K key;
        private V data;
        private Status stat;
        Bucket() {
            //バケットは空
            stat = Status.EMPTY;
        }
        void set(K key, V data, Status stat) {
            this.key = key;
            this.data = data;
            this.stat = stat;
        }
        void setStat(Status stat) {
            this.stat = stat;
        }
        K getKey() {
            return key;
        }
        V getData() {
            return data;
        }
        public int hashCode() {
            return key.hashCode();
        }
    }

    //ハッシュ表の大きさ
    private int size;
    //ハッシュ表
    private Bucket<K, V>[] table;
    public OpenHash(int size) {
        try {
            table = new Bucket[size];
            for (int i = 0; i < size; i++)
                table[i] = new Bucket<K, V>();
            this.size = size;
        } catch (OutOfMemoryError e) {
            this.size = 0;
        }
    }
    public int hashValue(Object key) {
        return key.hashCode() % size;
    }
    public int rehashValue(int hash) {
        return (hash + 1) % size;
    }
    private Bucket<K, V> searchNode(K key) {
        int hash = hashValue(key);
        Bucket<K, V> p = table[hash];
        for (int i = 0; p.stat != Status.EMPTY && i < size; i++) {
            if (p.stat == Status.OCCUPIED && p.getKey().equals(key))
                return p;
            hash = rehashValue(hash);
            p = table[hash];
        }
        return null;
    }
    public V search(K key) {
        Bucket<K, V> p = searchNode(key);
        if (p != null)
            return p.getData();
        else
            return null;
    }
    public int add(K key, V data) {
        if (search(key) != null)
            //登録済み
            return 1;
        int hash = hashValue(key);
        Bucket<K, V> p = table[hash];
        for (int i = 0; i < size; i++) {
            if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
                p.set(key, data, Status.OCCUPIED);
                return 0;
            }
            hash = rehashValue(hash);
            p = table[hash];
        }
        //ハッシュ表が満杯
        return 2;
    }
    public int remove(K key) {
        Bucket<K, V> p = searchNode(key);
        if (p == null)
            return 1;
        p.setStat(Status.DELETED);
        return 0;
    }
    public void dump() {
        for (int i = 0; i < size; i++) {
            System.out.printf("%02d ", i);
            switch (table[i].stat) {
                case OCCUPIED:
                    System.out.printf("%s (%s)\n", table[i].getKey(), table[i].getData());
                    break;
                case EMPTY:
                    System.out.println("-- 未登録 --");
                    break;
                case DELETED:
                    System.out.println("-- 削除済み --");
                    break;
            }
        }
    }
}