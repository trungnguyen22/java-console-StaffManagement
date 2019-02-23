package com.john.funix.utils;

import com.sun.istack.internal.NotNull;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Trung Nguyen
 * This class helps validate inputs from console
 */
public class InputHelper {

    public static String inputForName(String message, @NotNull Scanner scanner) {
        boolean isValid = false;
        String name;
        do {
            System.out.print(message);
            name = scanner.nextLine();
            name = name.trim();
            name = name.replaceAll("\\s+", " ");
            if (!name.isEmpty() && validateLetters(name))
                isValid = true;
            else
                System.out.println("It's not a valid name. Please enter again!");
        } while (!isValid);
        return name;
    }

    public static boolean validateLetters(String txt) {
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();

    }

    public static int inputForPositiveIntegerNumber(String message, Scanner scanner) {
        boolean isValid;
        String s;
        int number = 0;
        do {
            System.out.print(message);
            s = scanner.nextLine();
            try {
                number = Integer.parseInt(s);
                if (number >= 0) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a positive number!");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("It's not a valid number");
                isValid = false;
            }
        } while (!isValid);
        return number;
    }

    public static double inputForPositiveDoubleNumber(String message, Scanner scanner) {
        boolean isValid;
        String s;
        double number = 0;
        do {
            System.out.print(message);
            s = scanner.nextLine();
            try {
                number = Double.parseDouble(s);
                if (number > 0) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a positive number!");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("It's not a valid number");
                isValid = false;
            }
        } while (!isValid);
        return number;
    }
}
