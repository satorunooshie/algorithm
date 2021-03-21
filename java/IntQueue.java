package java;

/**
 * The type Int queue.
 */
public class IntQueue {
    // キュー用の配列
    private int[] que;
    private int capacity;
    // 先頭要素カーソル
    private int front;
    // 末尾要素カーソル
    private int rear;
    // 現在のデータ数
    // fornt/rearの値が等しくなったときにキューが空なのか満杯なのかが区別できなくなるのを防ぐため
    // キューが空の時は0で、満杯の時はcapacityと同じ値になる
    private int num;

    /**
     * Instantiates a new Int queue.
     *
     * @param maxlen the maxlen
     */
    public IntQueue(int maxlen) {
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = new int[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }

    /**
     * Enque int.
     *
     * @param x the x
     * @return the int
     * @throws OverflowIntQueueException the overflow int queue exception
     */
    public int enque(int x)
            throws OverflowIntQueueException {
        if (isFull())
            throw new OverflowIntQueueException();
        que[rear++] = x;
        num++;
        /*
         *  エンキューの前のrearが配列の物理的な末尾であるときにrearをインクリメントすると
         *  その値がcapacityと等しくなって配列のインデックスの上限を超えてしまう
         *  そこでインクリメントした後のrearの値がキューの容量capacityと等しくなった場合は
         *  rearを配列の先頭のインデックス0に戻す
         */
        if (rear == capacity)
            rear = 0;
        return x;
    }

    /**
     * Deque int.
     *
     * @return the int
     * @throws EmptyIntQueueException the empty int queue exception
     */
    public int deque()
            throws EmptyIntQueueException {
        if (isEmpty())
            throw new EmptyIntQueueException();
        int x = que[front++];
        num--;
        /*
         *  デキュー前のfrontが配列の物理的な末尾である時にfrontをインクリメントすると
         *  その値がcapacityと等しくなって配列のインデックスの上限を超えてしまう
         *  そこでインクリメントした後のfrontの値がキューの容量capacityと等しくなった場合は
         *  frontを配列の先頭インデックス0に戻す
         */
        if (front == capacity)
            front = 0;
        return x;
    }

    /**
     * Peek int.
     *
     * @return the int
     * @throws EmptyIntQueueException the empty int queue exception
     */
// 次のデキューで取り出されるデータを覗き見するメソッドでデータの取り出しは行わないので値は更新しない
    public int peek()
            throws EmptyIntQueueException {
        if (isEmpty())
            throw new EmptyIntQueueException();
        return que[front];
    }

    /**
     * Clear.
     */
// エンキューとデキューはnumとfrontとrearの値に基づいて行われるので本体用の配列要素の値の変更は不要
    public void clear() {
        num = front = rear = 0;
    }

    /**
     * Index of int.
     *
     * @param x the x
     * @return the int
     */
// 先頭から末尾側へと線形探索(キューとしての論理的な先頭要素から)
    public int indexOf(int x) {
        for (int i = 0; i < num; i++) {
            int index = (i + front) % capacity;
            if (que[index] == x) return index;
        }
        return -1;
    }

    /**
     * Search int.
     *
     * @param x the x
     * @return the int
     */
    // キューからxを探して先頭から何番目か
    public int search(int x) {
        for (int i = 0; i < num; i++)
            if (que[(i + front) % capacity] == x)
                return i + 1;
        return 0;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return num;
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return num <= 0;
    }

    /**
     * Is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return num >= capacity;
    }

    /**
     * Dump.
     */
    public void dump() {
        if (isEmpty())
            System.out.println("キューは空です");
        else {
            for (int i = 0; i < num; i++)
                System.out.print(que[(i + front) % capacity] + " ");
            System.out.println();
        }
    }

    /**
     * The type Empty int queue exception.
     */
    public static class EmptyIntQueueException extends RuntimeException {
        /**
         * Instantiates a new Empty int queue exception.
         */
        public EmptyIntQueueException() {
        }
    }

    /**
     * The type Overflow int queue exception.
     */
    public static class OverflowIntQueueException extends RuntimeException {
        /**
         * Instantiates a new Overflow int queue exception.
         */
        public OverflowIntQueueException() {
        }
    }
}