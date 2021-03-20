package java;

import java.util.Scanner;

/**
 * PhyscExamSort.class
 * PhysExamSearch.class
 */
class PhysicalExamination {
    /**
     * The Vmax.
     */
    static final int VMAX = 21;

    /**
     * 身長の平均値を求める
     *
     * @param physcData the physc data
     * @return double
     */
    static double aveHeight(PhyscData[] physcData) {
        double sum = 0;
        for (PhyscData physcDatum : physcData) sum += physcDatum.height;
        return sum / physcData.length;
    }

    /**
     * 視力の分布を求める
     *
     * @param physcData the physc data
     * @param dist      the dist
     */
    static void distVision(PhyscData[] physcData, int[] dist) {
        int i = 0;
        dist[i] = 0;
        for (i = 0; i < physcData.length; i++)
            if (physcData[i].vision >= 0.0 && physcData[i].vision <= VMAX / 10.0)
                dist[(int) (physcData[i].vision * 10)]++;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        PhyscData[] physcData = {
                new PhyscData("加藤忠正", 162, 0.3),
                new PhyscData("赤坂忠雄", 172, 0.7),
                new PhyscData("前田淳二", 192, 1.8),
                new PhyscData("長田長信", 152, 1.5),
                new PhyscData("佐藤圭一", 168, 1.3),
                new PhyscData("西田一郎", 173, 0.8),
                new PhyscData("多田治", 159, 0.2),
                new PhyscData("後藤凛", 178, 0.4),
                new PhyscData("久保田力", 182, 2.0),
        };
        int[] vdist = new int[VMAX];
        System.out.println("**身体検査一覧表**");
        System.out.println("氏名    身長 視力");
        System.out.println("---------------");
        for (PhyscData physcDatum : physcData)
            System.out.printf("%-8s%3d%5.1f\n", physcDatum.name, physcDatum.height, physcDatum.vision);
        System.out.printf("\n平均身長: %5.1fcm\n", aveHeight(physcData));
        distVision(physcData, vdist);
        System.out.println("\n視力の分布");
        for (int i = 0; i < VMAX; i++)
            System.out.printf("%3.1f~: %2d人\n", i / 10.0, vdist[i]);
        System.out.println("\n視力の分布を表したグラフ");
        for (int i = 0; i < VMAX; i++) {
            System.out.printf("%3.1f~: ", i / 10.0);
            for (int j = 0; j < vdist[i]; j++)
                System.out.print('*');
            System.out.println();
        }
    }

    /**
     * The type Physc data.
     */
    static class PhyscData {
        /**
         * The Name.
         */
        String name;
        /**
         * The Height.
         */
        int height;
        /**
         * The Vision.
         */
        double vision;

        /**
         * Instantiates a new Physc data.
         *
         * @param name   the name
         * @param height the height
         * @param vision the vision
         */
        PhyscData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }
    }
}