/* 任意の整数に対してその整数を英語で表示する */
//19,323,984のような数は3桁ずつ区切りthousandやmillionという文字列を挿入していく
public String[] digits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
public String[] teens = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighty", "Nineteen"};
public static String[] tens = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
public static String[] bigs = {"", "Thousand", "Million"};
public static String numToString(int number) {
    if (number == 0) {
        return "Zero";
    } else if (number < 0) {
        return "Negative " + numToString(-1 * number);
    }
    int count = 0;
    String str = "";
    while (number > 0) {
        if (number % 1000 != 0) {
            str = numToString100(number % 1000) + bigs[count] + " " + str;
        }
        number /= 1000;
        count++;
    }
    return str;
}
public static String numToString100(int number) {
    String str = "";
    //100の桁を変換
    if (number >= 100) {
        str += digits[number / 100 - 1] + " Hundred ";
        number %= 100;
    }
    //10の桁を変換
    if (number >= 11 && number <= 19) {
        return str + teens[number - 11] + " ";
    } else if (number == 10 || number >= 20) {
        str += tens[number / 10 - 1] + " ";
        number %= 10;
    }
    //1の桁を変換
    if (number >= 1 && number <= 9) {
        str += digits[number - 1] + " ";
    }
    return str;
}
