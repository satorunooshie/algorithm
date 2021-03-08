import java.util.Comparator;

public class LinkedList<E> {
    class Node<E> {
        private E data;
        private Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
    private Node<E> head;
    private Node<E> crnt;
    public LinkedList() {
        head = crnt = null;
    }
    public E search(E obj, Comparator<? super E> c) {
        Node<E> ptr = head;
        while (ptr != null) {
            if (c.compare(obj, ptr.data) == 0) {
                crnt = ptr;
                return ptr.data;
            }
            ptr = ptr.next;
        }
        return null;
    }
    public void addFirst(E obj) {
        Node<E> ptr = head;
        head = crnt = new Node<E>(obj, ptr);
    }
    public void addLast(E obj) {
        if (head == null)
            addFirst(obj);
        else {
            Node<E> ptr = head;
            while (ptr.next != null)
                ptr = ptr.next;
            ptr.next = crnt = new Node<E>(obj, null);
        }
    }
    public void removeFirst() {
        if (head != null)
            head = crnt = head.next;
    }
    public void removeLast() {
        if (head != null) {
            if (head.next == null)
                removeFirst();
            else {
                Node<E> ptr = head;
                Node<E> pre = head;
                while (ptr.next != null) {
                    pre = ptr;
                    ptr = ptr.next;
                }
                pre.next = null;
                crnt = pre;
            }
        }
    }
    @SuppressWarnings("unchecked")
    public void remove(Node p) {
        if (head != null) {
            if (p == head)
                removeFirst();
            else {
                Node<E> ptr = head;
                while (ptr.next != p) {
                    ptr = ptr.next;
                    if (ptr == null) return;
                }
                ptr.next = p.next;
                crnt = ptr;
            }
        }
    }
    public void removeCurrentNode() {
        remove(crnt);
    }
    public void clear() {
        while (head != null)
            removeFirst();
        crnt = null;
    }
    public boolean next() {
        if (crnt == null || crnt.next == null)
            return false;
        crnt = crnt.next;
        return true;
    }
    public void printCurrentNode() {
        if (crnt == null)
            System.out.println("着目ノードはありません");
        else
            System.out.println(crnt.data);
    }
    public void dump() {
        Node<E> ptr = head;
        while (ptr != null) {
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }
    /** メソッド実行後の着目ポインタcrntの値
     * construct:           null
     * search:              探索に成功すれば見つけたノード
     * addFirst:            挿入した先頭ノード
     * addLast:             挿入した末尾ノード
     * removeFirst:         削除後の先頭ノード(リストが空になればnull)
     * removeLast:          削除後の末尾ノード(リストが空になればnull)
     * remove:              削除したノードの先行ノード
     * removeCurrentNode:   削除したノードの先行ノード
     * clear:               null
     * next:                移動後の着目ノード
     * printCurrentNode:    更新しない
     * dump:                更新しない
     */
    //無理矢理実行してみる
    public static void main(String[] args) {
        StationLinkedList.init();
        StationLinkedList.print();
        StationLinkedList.insert(5, "品川", 2);
        StationLinkedList.print();
        StationLinkedList.delete(5, 2);
        StationLinkedList.print();
    }

}
//----------------------------------------------------
class StationList {
    //station name
    private String name;
    //ptr
    private int next;

    public void setName(String name) {
        this.name = name;
    }
    public void setNext(int next) {
        this.next = next;
    }
    public int getNext() {
        return next;
    }
    public String getName() {
        return name;
    }
}
class StationLinkedList {
    private static StationList[] list = new StationList[10];
    //front ptr
    private static int top;
    public static void init() {
        for (int i = 0; i < list.length; i++) {
            list[i] = new StationList();
        }
        list[0].setName("新大阪");
        list[0].setNext(-1);
        list[1].setName("名古屋");
        list[1].setNext(3);
        list[2].setName("東京");
        list[2].setNext(4);
        list[3].setName("京都");
        list[3].setNext(0);
        list[4].setName("新横浜");
        list[4].setNext(1);
        top = 2;
    }
    public static void insert(int index, String name, int prevIndex) {
        list[index].setName(name);
        list[index].setNext(list[prevIndex].getNext());
        list[prevIndex].setNext(index);
    }
    public static void delete(int index, int prevIndex) {
        list[prevIndex].setNext(list[index].getNext());
    }
    public static void print() {
        int index = top;
        while (index != -1) {
            System.out.print("[" + list[index].getName() + "]->");
            index = list[index].getNext();
        }
        System.out.println();
    }
}