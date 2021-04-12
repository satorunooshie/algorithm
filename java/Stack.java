package java;

/**
 * The type Stack.
 *
 * ジェネリックなスタック
 * @param <E> the type parameter
 */
@SuppressWarnings("unchecked")
public class Stack<E> {
    // スタック用の配列
    private E[] stk;
    private int capacity;
    private int ptr;

    /**
     * Instantiates a new Stack.
     *
     * @param maxlen the maxlen
     */
    public Stack(int maxlen) {
        ptr = 0;
        capacity = maxlen;
        try {
            stk = (E[]) new Object[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }

    /**
     * Push e.
     *
     * @param x the x
     * @return the e
     * @throws OverflowStackException the overflow stack exception
     */
    public E push(E x) throws OverflowStackException {
        if (ptr >= capacity)
            throw new OverflowStackException();
        return stk[ptr++] = x;
    }

    /**
     * Pop e.
     *
     * @return the e
     * @throws EmptyStackException the empty stack exception
     */
    public E pop() throws EmptyStackException {
        if (ptr <= 0)
            throw new EmptyStackException();
        return stk[--ptr];
    }

    /**
     * Peek e.
     *
     * @return the e
     * @throws EmptyStackException the empty stack exception
     */
    public E peek() throws EmptyStackException {
        if (ptr <= 0)
            throw new EmptyStackException();
        return stk[ptr - 1];
    }

    /**
     * Clear.
     */
    public void clear() {
        ptr = 0;
    }

    /**
     * Index of int.
     *
     * @param x the x
     * @return the int
     */
    public int indexOf(E x) {
        // 頂上側から線形探索
        for (int i = ptr - 1; i >= 0; i--)
            if (stk[i].equals(x))
                return i;
        return -1;
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
        return ptr;
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return ptr <= 0;
    }

    /**
     * Is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return ptr >= capacity;
    }

    /**
     * Dump.
     */
    public void dump() {
        if (isEmpty())
            System.out.println("スタックは空です");
        else {
            for (int i = 0; i < ptr; i++)
                System.out.print(stk[i] + " ");
            System.out.println();
        }
    }

    /**
     * The type Empty stack exception.
     */
    public static class EmptyStackException extends RuntimeException {
        /**
         * Instantiates a new Empty stack exception.
         */
        public EmptyStackException() {
        }
    }

    /**
     * The type Overflow stack exception.
     */
    public static class OverflowStackException extends RuntimeException {
        /**
         * Instantiates a new Overflow stack exception.
         */
        public OverflowStackException() {
        }
    }
}
