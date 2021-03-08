import java.util.Scanner;

class Hanoi {
    static void move(int no, int x, int y) {
        //底の円盤を除いたグループを開始軸から中間軸へ移動
        if (no > 1)
            move(no - 1, x, 6 - x - y);
        //底の円盤noを開始軸から目的軸へ移動した旨を表示
        System.out.printf("円盤[%d]を%d軸から%d軸へ移動\n", no, x, y);
        //底の円盤を除いたグループを中間軸から目的軸へ移動
        if (no > 1)
            move(no - 1, 6 - x - y, y);
    }
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("ハノイの塔");
        System.out.print("円盤の枚数:");
        int n = stdIn.nextInt();
        //第一軸に積まれたn枚を第三軸に移動
        move(n, 1, 3);
    }
}