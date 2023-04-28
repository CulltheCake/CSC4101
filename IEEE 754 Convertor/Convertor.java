package CSC4101Assignment2;

import java.util.Scanner;

public class Convertor {
    public static void IEEE754() {
    	 Scanner in = new Scanner(System.in);
         System.out.print("Input: ");
         double num = in.nextDouble();
         in.close();
        int sign = 0;
        if (num < 0) {
            sign = 1;
            num = -num;
        }

        int exponent = 0;
        int exponent64= 0;
        double mantissa = num;
        if (num >= 2) {
            while (mantissa >= 2) {
                exponent++;
                exponent64++;
                mantissa /= 2;
            }
        } else if (num < 1) {
            while (mantissa < 1) {
                exponent--;
                exponent64--;
                mantissa *= 2;
            }
        }

        mantissa-= 1;
        StringBuilder mantissa32 = new StringBuilder();
        StringBuilder mantissa64 = new StringBuilder();
        for (int i = 0; i < 23; i++) {
            mantissa *= 2;
            if (mantissa >= 1) {
                mantissa32.append("1");
                mantissa64.append("1");
                mantissa -= 1;
            } else {
                mantissa32.append("0");
                mantissa64.append("0");
            }
        }
        for (int i = 0; i < 29; i++) {
            mantissa *= 2;
            if (mantissa >= 1) {
                mantissa64.append("1");
                mantissa -= 1;
            } else {
                mantissa64.append("0");
            }
        }

        exponent += 127;
        exponent64 += 1023;
        StringBuilder exp = new StringBuilder();
        StringBuilder exp64 = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (exponent % 2 == 0) {
                exp.insert(0, "0");
            } else {
                exp.insert(0, "1");
            }
            exponent = exponent / 2;
        }
        for (int i = 0; i < 11; i++) {
            if (exponent64 % 2 == 0) {
                exp64.insert(0, "0");
            } else {
                exp64.insert(0, "1");

            }
            exponent64 = exponent64 / 2;
        }
        StringBuilder output32 = new StringBuilder();
        output32.append(sign).append(" ");
        output32.append(exp).append(" ");
        output32.append(mantissa32);

        StringBuilder output64 = new StringBuilder();
        output64.append(sign).append(" ");
        output64.append(exp64).append(" ");
        output64.append(mantissa64);

        System.out.println(output32);
        System.out.println(output64);
    }
}
