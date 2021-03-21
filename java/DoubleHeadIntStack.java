package java;

/**
 * 両頭int型固定長スタック
 * 一つの配列を共有して二つのスタックを実現する
 * 配列の先頭側と末尾側の両側を利用する
 *      push ->     <- push
 * +-----------------------------+
 * | | | | | | | | | | | | | | | |
 * | | | |  A  | | | |  B  | | | |
 * | | | | | | | | | | | | | | | |
 * +-----------------------------+
 * bottom<->top     top<->bottom
 *      <- pop      pop ->
 */
public class DoubleHeadIntStack {
    // スタックの本体
    private int[] stk;
    // スタックの容量(A+B)
    private int capacity;
    private int ptrA;
    private int ptrB;

    /**
     * Instantiates a new Double head int stack.
     *
     * @param maxlen the maxlen
     */
    public DoubleHeadIntStack(int maxlen) {
        ptrA = 0;
        ptrB = maxlen - 1;
        try {
            stk = new int[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
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
     * Push int.
     *
     * @param sw the sw
     * @param x  the x
     * @return the int
     * @throws OverflowDoubleHeadIntStackException the overflow double head int stack exception
     */
    public int push(AorB sw, int x) throws OverflowDoubleHeadIntStackException {
        if (ptrA >= ptrB + 1)
            throw new OverflowDoubleHeadIntStackException();
        switch (sw) {
            case StackA -> stk[ptrA++] = x;
            case StackB -> stk[ptrB--] = x;
        }
        return x;
    }

    /**
     * Pop int.
     *
     * @param sw the sw
     * @return the int
     * @throws EmptyDoubleHeadIntStackException the empty double head int stack exception
     */
    public int pop(AorB sw) throws EmptyDoubleHeadIntStackException {
        int x = 0;
        switch (sw) {
            case StackA -> {
                if (ptrA <= 0)
                    throw new EmptyDoubleHeadIntStackException();
                x = stk[--ptrA];
            }
            case StackB -> {
                if (ptrB >= capacity - 1)
                    throw new EmptyDoubleHeadIntStackException();
                x = stk[++ptrB];
            }
        }
        return x;
    }

    /**
     * Peek int.
     *
     * @param sw the sw
     * @return the int
     * @throws EmptyDoubleHeadIntStackException the empty double head int stack exception
     */
    public int peek(AorB sw) throws EmptyDoubleHeadIntStackException {
        int x = 0;
        switch (sw) {
            case StackA -> {
                if (ptrA <= 0)
                    throw new EmptyDoubleHeadIntStackException();
                x = stk[ptrA - 1];
            }
            case StackB -> {
                if (ptrB >= capacity - 1)
                    throw new EmptyDoubleHeadIntStackException();
                x = stk[ptrB + 1];
            }
        }
        return x;
    }

    /**
     * Index of int.
     *
     * @param sw the sw
     * @param x  the x
     * @return the int
     */
    public int indexOf(AorB sw, int x) {
        switch (sw) {
            case StackA:
                // 頂上側から線形探索
                for (int i = ptrA - 1; i >= 0; i--)
                    if (stk[i] == x)
                        return i;
                break;
            case StackB:
                for (int i = ptrB + 1; i < capacity; i++)
                    if (stk[i] == x)
                        return i;
                break;
        }
        return -1;
    }

    /**
     * Clear.
     *
     * @param sw the sw
     */
    public void clear(AorB sw) {
        switch (sw) {
            case StackA -> ptrA = 0;
            case StackB -> ptrB = capacity - 1;
        }
    }

    /**
     * Size int.
     *
     * @param sw the sw
     * @return the int
     */
    public int size(AorB sw) {
        return switch (sw) {
            case StackA -> ptrA;
            case StackB -> capacity - ptrB - 1;
        };
    }

    /**
     * Is empty boolean.
     *
     * @param sw the sw
     * @return the boolean
     */
    public boolean isEmpty(AorB sw) {
        return switch (sw) {
            case StackA -> ptrA <= 0;
            case StackB -> ptrB >= capacity - 1;
        };
    }

    /**
     * Is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return ptrA >= ptrB + 1;
    }

    /**
     * Dump.
     *
     * @param sw the sw
     */
    public void dump(AorB sw) {
        switch (sw) {
            case StackA:
                if (ptrA <= 0)
                    System.out.println("スタックは空です");
                else {
                    for (int i = 0; i < ptrA; i++)
                        System.out.print(stk[i] + " ");
                    System.out.println();
                }
                break;
            case StackB:
                if (ptrB >= capacity - 1)
                    System.out.println("スタックは空です");
                else {
                    for (int i = capacity - 1; i > ptrB; i--)
                        System.out.print(stk[i] + " ");
                    System.out.println();
                }
                break;
        }
    }

    /**
     * The enum A or B.
     */
    public enum AorB {
        /**
         * Stack a aor b.
         */
        StackA,
        /**
         * Stack b aor b.
         */
        StackB
    }

    /**
     * The type Empty double head int stack exception.
     */
    public static class EmptyDoubleHeadIntStackException extends RuntimeException {
        /**
         * Instantiates a new Empty double head int stack exception.
         */
        public EmptyDoubleHeadIntStackException() {
        }
    }

    /**
     * The type Overflow double head int stack exception.
     */
    public static class OverflowDoubleHeadIntStackException extends RuntimeException {
        /**
         * Instantiates a new Overflow double head int stack exception.
         */
        public OverflowDoubleHeadIntStackException() {
        }
    }
}