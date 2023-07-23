package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Введите число от -999 999 999 999 до 999 999 999 999 : ");
        Long num = sc.nextLong();

        System.out.print("Введите род числа на кириллице: М, Ж или С : ");
        String sGender = sc.next();

        System.out.print("Введите падеж числа на кириллице: И, Р, Д, В, Т или П : ");
        String sCase = sc.next();

        System.out.println(Number.sumProp(num, sGender, sCase));
    }
}
