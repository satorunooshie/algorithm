/*
 * 乱数を引数にメソッドが何度か呼び出され、
 * 新しく値を受け取るたびにそれまでの数の中央値を求め、それを保持するプログラム
 * (新しい値を追加するメソッドと、それまでの中央値を返すメソッド)
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 二つのヒープを使って解くことができる
 * 中央値よりも大きい値を最小ヒープに保持し、中央値より小さい値を最大ヒープに保持する
 * そうすることで中央のに要素が各ヒープの先頭要素になるように大体2分することができて
 * 中央値を見つけるのが非常に簡単になる
 * 大体なのは要素のこすうが奇数個の場合、片方のヒープの要素の方が多くなるから
 */
public class FindMedian {
    private Comparator<Integer> maxHeapComparator;
    private Comparator<Integer> minHeapComparator;
    private static PriorityQueue<Integer> maxHeap, minHeap;
    public void addNewNumber(int randomNumber) {
        /* maxHeap.size() >= minHeap.size() always true */
        if (maxHeap.size() == minHeap.size()) {
            if ((minHeap.peek() != null) && (randomNumber > minHeap.peek())) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(randomNumber);
            } else {
                maxHeap.offer(randomNumber);
            }
        } else {
            if (randomNumber < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(randomNumber);
            } else {
                minHeap.offer(randomNumber);
            }
        }
    }
    public static double getMedian() {
        /* maxHeapは常にminHeap以上のサイズになるので、maxHeapが空ならminHeapも空になる */
        if (maxHeap.isEmpty()) {
            return 0;
        }
        if (maxHeap.size() == minHeap.size()) {
            return ((double)minHeap.peek() + (double)maxHeap.peek()) / 2;
        }
        /*
         * maxHeapとminHeapが異なる大きさならmaxheapは一つ多く要素を持っている
         * その場合はmaxHeapの先頭を返す
         */
        return maxHeap.peek();
    }
}
