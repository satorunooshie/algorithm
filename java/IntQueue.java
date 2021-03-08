public class IntQueue {
    //キュー用の配列
    private int[] que;
    private int capacity;
    //先頭要素カーソル
    private int front;
    //末尾要素カーソル
    private int rear;
    //現在のデータ数
    //fornt/rearの値が等しくなったときにキューが空なのか満杯なのかが区別できなくなるのを防ぐため
    //キューが空の時は0で、満杯の時はcapacityと同じ値になる
    private int num;

    public class EmptyIntQueueException extends RuntimeException {
        public EmptyIntQueueException() { }
    }
    public class OverflowIntQueueException extends RuntimeException {
        public OverflowIntQueueException() { }
    }
    public IntQueue(int maxlen) {
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = new int[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }
    public int enque(int x)
        throws OverflowIntQueueException {
        if (isFull())
            throw new OverflowIntQueueException();
        que[rear++] = x;
        num++;
        /*
            エンキューの前のrearが配列の物理的な末尾であるときにrearをインクリメントすると
            その値がcapacityと等しくなって配列のインデックスの上限を超えてしまう
            そこでインクリメントした後のrearの値がキューの容量capacityと等しくなった場合は
            rearを配列の先頭のインデックス0に戻す
         */
        if (rear == capacity)
            rear = 0;
        return x;
    }
    public int deque()
        throws EmptyIntQueueException {
        if (isEmpty())
            throw new EmptyIntQueueException();
        int x = que[front++];
        num--;
        /*
            デキュー前のfrontが配列の物理的な末尾である時にfrontをインクリメントすると
            その値がcapacityと等しくなって配列のインデックスの上限を超えてしまう
            そこでインクリメントした後のfrontの値がキューの容量capacityと等しくなった場合は
            frontを配列の先頭インデックス0に戻す
         */
        if (front == capacity)
            front = 0;
        return x;
    }
    //次のデキューで取り出されるデータを覗き見するメソッドでデータの取り出しは行わないので値は更新しない
    public int peek()
        throws EmptyIntQueueException {
        if (isEmpty())
            throw new EmptyIntQueueException();
        return que[front];
    }
    //エンキューとデキューはnumとfrontとrearの値に基づいて行われるので本体用の配列要素の値の変更は不要
    public void clear() {
        num = front = rear = 0;
    }
    //先頭から末尾側へと線形探索(キューとしての論理的な先頭要素から)
    public int indexOf(int x) {
        for (int i = 0; i < num; i++) {
            int index = (i + front) % capacity;
            if (que[index] == x) return index;
        }
        return -1;
    }
    public int getCapacity() {
        return capacity;
    }
    public int size() {
        return num;
    }
    public boolean isEmpty() {
        return num <= 0;
    }
    public boolean isFull() {
        return num >= capacity;
    }
    public void dump() {
        if (isEmpty())
            System.out.println("キューは空です");
        else {
            for (int i = 0; i < num; i++)
                System.out.print(que[(i + front) % capacity] + " ");
            System.out.println();
        }
    }
}