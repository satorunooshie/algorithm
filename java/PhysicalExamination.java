import java.util.Scanner;

/**
 * PhyscExamSort.class
 * PhysExamSearch.class
 */
class PhysicalExamination {
    static final int VMAX = 21;
    static class PhyscData {
        String name;
        int height;
        double vision;
        PhyscData(String name, int height, double vision) {
            this.name = name;
            this.height = height;
            this.vision = vision;
        }
    }

    /**
     * 身長の平均値を求める
     * @param physcData
     * @return
     */
    static double aveHeight(PhyscData[] physcData) {
        double sum = 0;
        for (int i = 0; i < physcData.length; i++)
            sum += physcData[i].height;
        double average = sum / physcData.length;
        return average;
    }

    /**
     * 視力の分布を求める
     * @param physcData
     * @param dist
     */
    static void distVision(PhyscData[] physcData, int[] dist) {
        int i = 0;
        dist[i] = 0;
        for (i = 0; i < physcData.length; i++)
            if (physcData[i].vision >= 0.0 && physcData[i].vision <= VMAX / 10.0)
                dist[(int)(physcData[i].vision * 10)]++;
    }
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
        for (int i = 0; i < physcData.length; i++)
            System.out.printf("%-8s%3d%5.1f\n", physcData[i].name, physcData[i].height, physcData[i].vision);
        System.out.printf("\n平均身長: %5.1fcm\n", aveHeight(physcData));
        distVision(physcData, vdist);
        System.out.println("\n視力の分布");
        for (int i = 0; i < VMAX; i++)
            System.out.printf("%3.1f~: %2d人\n", i / 10.0, vdist[i]);
    }
}