package java;

/**
 * 固定長のデッククラス
 * double ended queue
 * 両方向待ち行列
 * 先頭と末尾の両方に対してデータの押し込み・取り出しが行えるデータ構造
 * enque ->                 <- enque
 * +-----------------------------------+
 * | | | | | | | | | | | | | | | | | | |
 * | | | | | | | | | | | | | | | | | | |
 * | | | | | | | | | | | | | | | | | | |
 * +-----------------------------------+
 * <- top                      bottom ->
 * <- deque                     deque ->
 */
public class IntDeque {
    private int[] que;
    private int capacity;
    private int num;
    private int front;
    private int rear;

    /**
     * Instantiates a new Int deque.
     *
     * @param maxlen the maxlen
     */
    public IntDeque(int maxlen) {
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = new int[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }

    /**
     * Enque front int.
     *
     * @param x the x
     * @return the int
     * @throws OverflowIntDequeueException the overflow int dequeue exception
     */
    public int enqueFront(int x) throws OverflowIntDequeueException {
        if (isFull())
            throw new OverflowIntDequeueException();
        num++;
        if (--front < 0)
            front = capacity - 1;
        que[front] = x;
        return x;
    }

    /**
     * Enque rear int.
     *
     * @param x the x
     * @return the int
     * @throws OverflowIntDequeueException the overflow int dequeue exception
     */
    public int enqueRear(int x) throws OverflowIntDequeueException {
        if (isFull())
            throw new OverflowIntDequeueException();
        que[rear++] = x;
        num++;
        if (rear == capacity)
            rear = 0;
        return x;
    }

    /**
     * Deque front int.
     *
     * @return the int
     * @throws EmptyIntDequeueException the empty int dequeue exception
     */
    public int dequeFront() throws EmptyIntDequeueException {
        if (isEmpty())
            throw new EmptyIntDequeueException();
        int x = que[front++];
        num--;
        if (front == capacity)
            front = 0;
        return x;
    }

    /**
     * Deque rear int.
     *
     * @return the int
     * @throws EmptyIntDequeueException the empty int dequeue exception
     */
    public int dequeRear() throws EmptyIntDequeueException {
        if (isEmpty())
            throw new EmptyIntDequeueException();
        num--;
        if (--rear < 0)
            rear = capacity - 1;
        return que[rear];
    }

    /**
     * Peek front int.
     *
     * @return the int
     * @throws EmptyIntDequeueException the empty int dequeue exception
     */
    public int peekFront() throws EmptyIntDequeueException {
        if (isEmpty())
            throw new EmptyIntDequeueException();
        return que[front];
    }

    /**
     * Peek rear int.
     *
     * @return the int
     * @throws EmptyIntDequeueException the empty int dequeue exception
     */
    public int peekRear() throws EmptyIntDequeueException {
        if (isEmpty())
            throw new EmptyIntDequeueException();
        return que[rear == 0 ? capacity - 1 : rear - 1];
    }

    /**
     * Index of int.
     *
     * @param x the x
     * @return the int
     */
    public int indexOf(int x) {
        for (int i = 0; i < num; i++) {
            int index = (i + front) % capacity;
            if (que[index] == x)
                return index;
        }
        return -1;
    }

    /**
     * Search int.
     *
     * @param x the x
     * @return the int
     */
    public int search(int x) {
        for (int i = 0; i < num; i++)
            if (que[(i + front) % capacity] == x)
                return i + 1;
        return 0;
    }

    /**
     * Clear.
     */
    public void clear() {
        num = front = rear = 0;
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
        if (num <= 0)
            System.out.println("デックは空です");
        else {
            for (int i = 0; i < num; i++)
                System.out.print(que[(i + front) % capacity] + " ");
            System.out.println();
        }
    }

    /**
     * The type Empty int dequeue exception.
     */
    public static class EmptyIntDequeueException extends RuntimeException {
        /**
         * Instantiates a new Empty int dequeue exception.
         */
        public EmptyIntDequeueException() {
        }
    }

    /**
     * The type Overflow int dequeue exception.
     */
    public static class OverflowIntDequeueException extends RuntimeException {
        /**
         * Instantiates a new Overflow int dequeue exception.
         */
        public OverflowIntDequeueException() {
        }
    }
}
