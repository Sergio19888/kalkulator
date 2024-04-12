package org.example;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    static Scanner line = new Scanner(System.in);//создан сканнер
    static String upEnterString;//создана переменная для ввода данных
    static String zupEnterString;
    static String[] w = new String[2];//создан массив для двух чисел
    static String result;
    static String resultRim;
    static String[] rTale = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
            "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
            "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV",
            "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static void main(String[] args) {
        System.out.println("Input :");
        upEnterString = line.nextLine();//ввели выражение
        zupEnterString = upEnterString.replaceAll(" ", "");
        String enterString = zupEnterString.toUpperCase();
        if (proverkaNaFormat(zupEnterString) == false) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /,*)");
        } else {
            calc(enterString);//выделили из выражения числа
            String a = w[0];
            String b = w[1];
            if (a == null || b == null) {
                System.out.println("throws Exception //т.к. одновременно используются разные системы счисления");
            } else {

                if (proverkaVelicnini(a, b) == false) {
                    System.out.println("throws Exception //т.к. в вырвжении есть число больше десяти");
                } else {
                    isRim(enterString);//проверка на римскость чисел

                    if (isRim(enterString) == true) {    //выполнение действия если числа римские

                        operation(a, b);
                        if (proverkaNaMinus(result) == false) {
                            System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                        } else {
                            resultInRim(result);
                            System.out.println("Output :");
                            System.out.println(resultRim);
                        }
                    }
                    if (isRim(enterString) == false) {      //выполнение действия если числа арабские

                        operation(a, b);
                        System.out.println("Output :");
                        System.out.println(result);
                    }
                }
            }
        }
    }
    public static String calc(String enterString) {
        enterString.replaceAll(" ", ""); //убрали все пробелы
        String upEnterString = enterString.toUpperCase(); //на всякий случай приводим все буквы в верхний регистр не работает пока
        Pattern pattern = Pattern.compile("\\d+");//Вызвали метод Pattern для нахождения чисел
        Matcher matcher = pattern.matcher(upEnterString); //ищем по строке числа
        String t = null;
        int operationCount = 0;//переменная для переключения индекса массива
        while (matcher.find()) { //цикл до тех пор пока идёт поиск по строке
            t = matcher.group();  // переменная T равна найденному числу
            w[operationCount] = t;
            operationCount++;
        }
        if (w[0] == null && w[1] == null) {
            String[] operand = enterString.split("[+\\-*/]");//делит выражение на две части по знаку
            for (int i = 0; i < rTale.length; i++) {
                if (operand[0].equals(rTale[i])) {  // проверяем строки на соответсвие римскому числу
                    int c = i;
                    w[0] = Integer.toString(c);
                }
                if (operand[1].equals(rTale[i])) {  // проверяем строки на соответсвие римскому числу
                    int c = i;
                    w[1] = Integer.toString(c);
                }
            }
        }
        return w[0] + w[1];
    }
    public static boolean isRim(String enterString) {
        String[] operand = enterString.split("[+\\-*/]");//делит выражение на две части по знаку
        for (int i = 0; i < rTale.length; i++) {
            if (operand[0].equals(rTale[i])) {
                return true;
            }
            if (operand[1].equals(rTale[i])) {  // проверяем строки на соответсвие римскому числу
                return true;
            }
        }
        return false;
    }
    public static String operation(String a, String b) {
        int v = Integer.parseInt(a);
        int n = Integer.parseInt(b);

        for (int i = 0; i < upEnterString.length(); i++) {
            if (upEnterString.charAt(i) == '+') {
                int c = v + n;
                result = Integer.toString(c);
            }
            if (upEnterString.charAt(i) == '-') {
                int c = v - n;
                result = Integer.toString(c);
            }
            if (upEnterString.charAt(i) == '/') {
                int c = v / n;
                result = Integer.toString(c);
            }
            if (upEnterString.charAt(i) == '*') {
                int c = v * n;
                result = Integer.toString(c);
            }

        }
        return result;
    }
    public static String resultInRim (String result){
        int q = Integer.parseInt(result);
        for (int i = 0; i < rTale.length; i++) {
            if(i == q){
                resultRim = rTale[i];
                break;
            }
        }
        return resultRim;
    }
    public static boolean proverkaVelicnini (String a, String b){
        if(a == null || b == null) {
            return false;
        } else {
            int v = Integer.parseInt(a);
            int n = Integer.parseInt(b);
            if(v < 0 || v > 10 || n < 0 || n > 10)
                return false;
        }
        return true;
    }
    public static boolean proverkaIdentifi (String a, String b) {
        if(a == null || b == null){
            return false;
        }
        return true;
    }
    public static boolean proverkaNaMinus (String result){
        int a = Integer.parseInt(result);
        if (a < 0) {
            return false;
        }
        return true;
    }
    public static boolean proverkaNaFormat (String zupEnterString) {
        int a = 0;
        for (int i = 0; i < zupEnterString.length(); i++) {

            if (zupEnterString.charAt(i) == '+' || zupEnterString.charAt(i) == '-' || zupEnterString.charAt(i) == '/' || zupEnterString.charAt(i) == '*') {
                a++;
            }
        }
        if (a == 1){
            return true;
        }
        return false;
    }
}
