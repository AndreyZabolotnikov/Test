package org.example;

import java.util.Scanner;

public class Main {
    public static final String[] ZERO = {"ноль", "ноля", "нолю", "ноль", "нолем", "ноле"};
    public static final String[][] ONE = {{"один", "одного", "одному", "один", "одним", "одном"}, {"одна", "одной", "одной", "одну", "одной", "одной"}, {"одно", "одного", "одному", "одно", "одним", "одном"}};
    public static final String[][] TWO = {{"два", "двух", "двум", "два", "двумя", "двух"}, {"две", "двух", "двум", "две", "двумя", "двух"}, {"два", "двух", "двум", "два", "двумя", "двух"}};
    public static final String[] ROOT_NUMBER = {"тр", "четыр", "пят", "шест", "сем", "вос", "девят", "десят", "одиннадцат", "двенадцат", "тринадцат",
            "четырнадцат", "пятнадцат", "шестнадцат", "семнадцат", "восемнадцат", "девятнадцат", "двадцат", "тридцат", "сорок", "девяност", "ст"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число от -999 999 999 999 до 999 999 999 999 : ");
        Long num = sc.nextLong();

        System.out.print("Введите род числа на кириллице: М, Ж или С : ");
        String sGender = sc.next();
        sGender = sGender.toUpperCase();

        System.out.print("Введите падеж числа на кириллице: И, Р, Д, В, Т или П : ");
        String sCase = sc.next();
        sCase = sCase.toUpperCase();

        System.out.println(sumProp(num, sGender, sCase));
    }

    public static String sumProp(long nSum, String sGender, String sCase) {

        String strNum = ""; // число прописью
        int indexGender; // род индекс в массиве
        int indexCase; // падеж индекс в массиве
        String negativ = ""; //Отрицательные числа

        //Если число отрицательное
        if (nSum < 0) {
            negativ = "минус" + endingNumber(sCase, "", "а", "у", "", "ом", "е") + " ";
            nSum *= -1;
        }

        // Для выбора рода из массива
        switch (sGender) {
            case ("М"):
                indexGender = 0;
                break;
            case ("Ж"):
                indexGender = 1;
                break;
            case ("С"):
                indexGender = 2;
                break;
            default:
                return "Введите правильный род числа";
        }

        // Для выбора падежа из массива
        switch (sCase) {
            case ("И"):
                indexCase = 0;
                break;
            case ("Р"):
                indexCase = 1;
                break;
            case ("Д"):
                indexCase = 2;
                break;
            case ("В"):
                indexCase = 3;
                break;
            case ("Т"):
                indexCase = 4;
                break;
            case ("П"):
                indexCase = 5;
                break;
            default:
                return "Введите правильный падеж числа";
        }

        //Выбор числа
        if (nSum == 0)
            strNum = ZERO[indexCase];

        else if (nSum == 1)
            strNum = ONE[indexGender][indexCase];

        else if (nSum == 2)
            strNum = TWO[indexGender][indexCase];

        else if ((nSum >= 3 && nSum <= 20) || nSum == 30 || nSum == 40 || nSum == 90)
            strNum = number(nSum, sCase);

        else if (nSum > 20 && nSum < 50 || nSum >= 90 && nSum < 100) {
            String endingNumber = "";
            if ((nSum - (nSum / 10) * 10) != 0)
                endingNumber = sumProp(nSum - (nSum / 10) * 10, sGender, sCase);
            long decimal = (nSum / 10) * 10;
            strNum = number(decimal, sCase) + " " + endingNumber;

        } else if (nSum >= 50 && nSum < 90) {
            String endingNumber = "";
            if ((nSum - (nSum / 10) * 10) != 0)
                endingNumber = sumProp(nSum - (nSum / 10) * 10, sGender, sCase);
            long decimal = (nSum / 10);
            strNum = number(decimal, sCase) + "десят" + endingNumber(sCase, "", "и", "и", "", "ью", "и") + " " + endingNumber;

        } else if (nSum >= 100 && nSum < 200) {
            String endingNumber = "";
            if ((nSum - (nSum / 100) * 100) != 0)
                endingNumber = sumProp(nSum - (nSum / 100) * 100, sGender, sCase);
            strNum = number((nSum / 100) * 100, sCase) + " " + endingNumber;

        } else if (nSum >= 200 && nSum < 300) {
            String endingNumber = "";
            if ((nSum - (nSum / 100) * 100) != 0)
                endingNumber = sumProp(nSum - (nSum / 100) * 100, sGender, sCase);
            String rootNumber = sumProp(nSum / 100, "Ж", sCase);
            String endingHundred = number((nSum / 100) * 100, sCase);
            strNum = rootNumber + endingHundred + " " + endingNumber;

        } else if (nSum >= 300 && nSum < 1000) {
            String endingNumber = "";
            if ((nSum - (nSum / 100) * 100) != 0)
                endingNumber = sumProp(nSum - (nSum / 100) * 100, sGender, sCase);
            String rootNumber = sumProp(nSum / 100, sGender, sCase);
            String endingHundred = number((nSum / 100) * 100, sCase);
            strNum = rootNumber + endingHundred + " " + endingNumber;

        } else if (nSum >= 1000 && nSum < 1000000) {
            String endingNumber = "";
            String endingThousands = "";
            if ((nSum - (nSum / 1000) * 1000) != 0)
                endingNumber = sumProp(nSum - (nSum / 1000) * 1000, sGender, sCase);
            if ((nSum / 1000) * 1000 - (nSum / 10000) * 10000 != 0)
                endingThousands = number((nSum / 1000) * 1000 - (nSum / 10000) * 10000, sCase);
            else
                endingThousands = number(5000, sCase);
            String startNumber = sumProp(nSum / 1000, "Ж", sCase);
            strNum = startNumber.trim() + " " + "тысяч" + endingThousands + " " + endingNumber;

        } else if (nSum >= 1000000 && nSum < 1000000000) {
            String endingNumber = "";
            String endingMillion = "";
            if ((nSum - (nSum / 1000000) * 1000000) != 0)
                endingNumber = sumProp(nSum - (nSum / 1000000) * 1000000, sGender, sCase);
            if ((nSum / 1000000) * 1000000 - (nSum / 10000000) * 10000000 != 0)
                endingMillion = number((nSum / 1000000) * 1000000 - (nSum / 10000000) * 10000000, sCase);
            else
                endingMillion = number(5000000, sCase);
            String startNumber = sumProp(nSum / 1000000, "М", sCase);
            strNum = startNumber + " " + "миллион" + endingMillion + " " + endingNumber;

        } else if (nSum >= 1_000_000_000 && nSum < 1_000_000_000_000L) {
            String endingNumber = "";
            String endingBillion = "";
            if ((nSum - (nSum / 1_000_000_000) * 1_000_000_000) != 0)
                endingNumber = sumProp(nSum - (nSum / 1_000_000_000) * 1_000_000_000, sGender, sCase);
            if ((nSum / 1_000_000_000) * 1_000_000_000 - (nSum / 10_000_000_000L) * 10_000_000_000L != 0)
                endingBillion = number((nSum / 1_000_000_000) * 1_000_000_000 - (nSum / 10_000_000_000L) * 10_000_000_000L, sCase);
            else
                endingBillion = number(5_000_000_000L, sCase);
            String startNumber = sumProp(nSum / 1_000_000_000, "М", sCase);
            strNum = startNumber + " " + "миллиард" + endingBillion + " " + endingNumber;
        }
        return negativ + strNum;
    }

    //Метод получения простого числа и окончания для составного
    public static String number(long number, String sCase) {

        int num;

        if (number == 30)
            num = 18;
        else if (number == 40)
            num = 19;
        else if (number == 90)
            num = 20;
        else if (number == 100)
            num = 21;
        else if (number >= 200)
            num = 3;
        else
            num = (int) number - 3;

        String StrNum = ROOT_NUMBER[num];

        if (number == 3)
            StrNum += endingNumber(sCase, "и", "ёх", "ём", "и", "емя", "ёх");

        else if (number == 4)
            StrNum += endingNumber(sCase, "е", "ёх", "ём", "е", "ьмя", "ёх");

        else if (number == 8)
            StrNum += endingNumber(sCase, "емь", "ьми", "ьми", "емь", "емью", "ьми");

        else if (number == 40)
            StrNum += endingNumber(sCase, "", "а", "а", "", "а", "а");

        else if (number == 90 || number == 100)
            StrNum += endingNumber(sCase, "о", "а", "а", "о", "а", "а");

        else if (number == 200)
            StrNum = endingNumber(sCase, "сти", "сот", "стам", "сти", "стами", "стах");

        else if (number == 300 || number == 400)
            StrNum = endingNumber(sCase, "ста", "сот", "стам", "ста", "стами", "стах");

        else if (number == 500 || number == 600 || number == 700 || number == 800 || number == 900)
            StrNum = endingNumber(sCase, "сот", "сот", "стам", "сот", "стами", "стах");

        else if (number == 1000)
            StrNum = endingNumber(sCase, "а", "и", "е", "у", "ей", "е");

        else if (number == 2000 || number == 3000 || number == 4000)
            StrNum = endingNumber(sCase, "и", "", "ам", "и", "ами", "ах");

        else if (number == 5000 || number == 6000 || number == 7000 || number == 8000 || number == 9000)
            StrNum = endingNumber(sCase, "и", "", "ам", "", "ами", "ах");

        else if (number == 1000000 || number == 1_000_000_000)
            StrNum = endingNumber(sCase, "", "а", "у", "", "ом", "е");

        else if (number == 2000000 || number == 3000000 || number == 4000000 || number == 2_000_000_000 || number == 3_000_000_000L || number == 4_000_000_000L)
            StrNum = endingNumber(sCase, "а", "ов", "ам", "а", "ами", "ах");

        else if (number == 5000000 || number == 6000000 || number == 7000000 || number == 8000000 || number == 9000000 || number == 5_000_000_000L
                || number == 6_000_000_000L || number == 7_000_000_000L || number == 8_000_000_000L || number == 9_000_000_000L)
            StrNum = endingNumber(sCase, "ов", "ов", "ам", "ов", "ами", "ах");

        else
            StrNum += endingNumber(sCase, "ь", "и", "и", "ь", "ью", "и");

        return StrNum;
    }

    //Получение окончания взависимости от падежа
    public static String endingNumber(String sCase, String iCase, String rCase, String dCase, String vCase, String tCase, String pCase) {

        String StrNum = "";

        switch (sCase) {
            case ("И"):
                StrNum += iCase;
                break;
            case ("Р"):
                StrNum += rCase;
                break;
            case ("Д"):
                StrNum += dCase;
                break;
            case ("В"):
                StrNum += vCase;
                break;
            case ("Т"):
                StrNum += tCase;
                break;
            case ("П"):
                StrNum += pCase;
                break;
        }
        return StrNum;
    }
}