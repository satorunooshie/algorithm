import java.util.Arrays;
import java.util.GregorianCalendar;
import static java.util.GregorianCalendar.*;

/**
 * GregorianCalendarクラスはComparableインターフェースを実装するとともに
 * compareToメソッドを実装しているのでGregorianCalendar型の配列をソートすることができる
 * このクラスでは1 ~ 12月が0 ~ 11として表されるので+1して表示する
 */
class SortCalendar {
    public static void main(String[] args) {
        GregorianCalendar[] x = {
                new GregorianCalendar(2022, NOVEMBER, 1),
                new GregorianCalendar(1963, OCTOBER, 18),
                new GregorianCalendar(1985, APRIL, 5),
        };
        Arrays.sort(x);
        for (int i = 0; i < x.length; i++)
            System.out.printf("%04d年%02d月%02d日\n", x[i].get(YEAR), x[i].get(MONTH) + 1, x[i].get(DATE));
    }
}