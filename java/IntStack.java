public class IntStack {
    //スタック用の配列
    private int[] stk;
    private int capacity;
    //スタックポインタ
    //スタックが空であれば0で満杯だった場合はcapcityと同じ値になる
    private int ptr;
    public class EmptyIntStackException extends RuntimeException {
        public EmptyIntStackException() { }
    }
    public class OverflowIntStackException extends RuntimeException {
        public OverflowIntStackException() { }
    }
    public IntStack(int maxlen) {
        ptr = 0;
        capacity = maxlen;
        try {
            stk = new int[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }
    public int push(int x)
        throws OverflowIntStackException {
        if (isFull())
            throw new OverflowIntStackException();
        return stk[ptr++] = x;
    }
    public int pop()
        throws EmptyIntStackException {
        if (isEmpty())
            throw new EmptyIntStackException();
        return stk[--ptr];
    }
    /**
     *  スタックの頂上のデータ(次のポップを行ったときに取り出されるデータ)を覗き見するメソッド
     *
     *  データの取り出しは行わないのでスタックポインタの更新は不要
     *
     *  コンストラクタとメソッドを利用して操作する場合、スタックポインタの値は必ず0<=capacityになるとはいえ、
     *  ptrの値が不正に書き換えられた場合、スタックポインタの値が必ずそうなるとは限らないため、
     *  不等号を使ってスタックが空であるかの判定を行っている
     */
    public int peek()
        throws EmptyIntStackException {
        if (isEmpty())
            throw new EmptyIntStackException();
        return stk[ptr - 1];
    }
    public void clear() {
        ptr = 0;
    }
    //スタックからxを探してインデックスを返す
    public int indexOf(int x) {
        //先にポップされるデータを優先的に見つけるために頂上側から線形探索
        for (int i = ptr - 1; i >= 0; i--)
            if (stk[i] == x) return i;
        return -1;
    }
    public int getCapacity() {
        return capacity;
    }
    public int size() {
        return ptr;
    }
    public boolean isEmpty() {
        return ptr <= 0;
    }
    public boolean isFull() {
        return ptr >= capacity;
    }
    public void dump() {
        if (isEmpty())
            System.out.println("スタックは空です");
        else {
            for (int i = 0; i < ptr; i++)
                System.out.print(stk[i] + " ");
            System.out.println();
        }
    }
}