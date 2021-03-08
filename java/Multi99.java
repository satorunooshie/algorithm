public class Multi99 {
    public static void main(String[] args) {
        System.out.println("九九");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++)
                System.out.printf("%3d, ", i * j);
            System.out.println();
        }

        System.out.println("九九(外枠付き)");
        System.out.print("   |");
        for (int i = 1; i <= 9; i++)
            System.out.printf("%4d ", i);
        System.out.println("\n--+---------------------------------------------");

        for (int i = 1; i <= 9; i++) {
            System.out.printf("%2d | ", i);
            for (int j = 1; j <= 9; j++)
                System.out.printf("%3d, ", i * j);
            System.out.println();
        }
    }
}