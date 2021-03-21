package java;

/**
 * The type Queue.
 *
 * @param <E> the type parameter
 */
@SuppressWarnings("unchecked")
public class Queue<E> {
    // キューの本体
    private E[] que;
    // キューの容量
    private int capacity;
    // 現在のデータ数
    private int num;
    // 先頭要素のカーソル
    private int front;
    // 末尾要素のカーソル
    private int rear;

    /**
     * Instantiates a new Queue.
     *
     * @param maxlen the maxlen
     */
    public Queue(int maxlen) {
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = (E[]) new Object[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }

    /**
     * Enque e.
     *
     * @param x the x
     * @return the e
     * @throws OverflowQueueException the overflow queue exception
     */
    public E enque(E x) throws OverflowQueueException {
        if (isFull())
            throw new OverflowQueueException();
        que[rear++] = x;
        num++;
        if (rear == capacity)
            rear = 0;
        return x;
    }

    /**
     * Deque e.
     *
     * @return the e
     * @throws OverflowQueueException the overflow queue exception
     */
    public E deque() throws OverflowQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        E x = que[front++];
        num--;
        if (front == capacity)
            front = 0;
        return x;
    }

    /**
     * Peek e.
     *
     * @return the e
     * @throws EmptyQueueException the empty queue exception
     */
    public E peek() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        return que[front];
    }

    /**
     * Index of int.
     *
     * @param x the x
     * @return the int
     */
    public int indexOf(E x) {
        for (int i = 0; i < num; i++)
            if (que[(i + front) % capacity].equals(x))
                return i + 1;
        return -1;
    }

    /**
     * Search int.
     *
     * @param x the x
     * @return the int
     */
    public int search(E x) {
        for (int i = 0; i < num; i++)
            if (que[(i + front) % capacity].equals(x))
                return i + 1;
        return -1;
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
        if (isEmpty())
            System.out.println("キューは空です");
        else {
            for (int i = 0; i < num; i++)
                System.out.print(que[(i + front) % capacity].toString() + " ");
            System.out.println();
        }
    }

    /**
     * The type Empty queue exception.
     */
    public static class EmptyQueueException extends RuntimeException {
        /**
         * Instantiates a new Empty queue exception.
         */
        public EmptyQueueException() {
        }
    }

    /**
     * The type Overflow queue exception.
     */
    public static class OverflowQueueException extends RuntimeException {
        /**
         * Instantiates a new Overflow queue exception.
         */
        public OverflowQueueException() {
        }
    }
}
