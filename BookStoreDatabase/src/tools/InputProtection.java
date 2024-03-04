/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Scanner;

public class InputProtection {

    private static final Scanner scanner = new Scanner(System.in);

    public static int intInput(int beginRange, int endRange) {
        int number = -1;
        do {
            try {
                //number = scanner.nextInt();
                number = Integer.parseInt(scanner.nextLine());
                //scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.print("Please enter a number: ");
                continue;
            }
            if (number >= beginRange && number <= endRange) {
                return number;
            } else {
                System.out.printf("Please enter a number (%d .. %d): ", beginRange, endRange);
                continue;
            }
        } while (true);
    }

    public static double doubleInput(double beginRange, double endRange) {
        double number = -1;
        do {
            try {
                number = Double.parseDouble(scanner.nextLine());

                if (number < beginRange || number > endRange) {
                    throw new IllegalArgumentException();
                }

                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Value out of range. Please enter a number between " + beginRange + " and " + endRange + ".");
            }
        }
        while (true);
    }
}