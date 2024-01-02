package com.restorant;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public final class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static Order order = new Order();

    public static void clearConsole() {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (os.contains("win")) {
                processBuilder.command("cmd", "/c", "cls");
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                processBuilder.command("clear");
            } else {
                System.out.println("Unsupported operation for this OS.");
                return;
            }
            Process process = processBuilder.inheritIO().start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the console: " + e.getMessage());
        }
    }

    private static void ReadDishes() {
        int iterator = 1;
        for (Dishes d : Dishes.values()) {
            System.out.println(iterator + " " + d.name());
            iterator++;
        }
    }

    private static void IsOrderEmpty() {
        if (!order.isOrderEmpty()) {
            System.out.println("O - go to order");
        }
    }

    private static Return casesInput(String input) {
        if (Objects.equals(input, "e") || "E".equals(input)) {
            return Return.EXIT;
        }
        if ((Objects.equals(input, "o") || "O".equals(input)) && !order.isOrderEmpty()) {
            return Return.ORDER;
        }
        if (Objects.equals(input, "n") || "N".equals(input)) {
            return Return.NOTHING;
        }
        if (RegexStr.containsNumbers(input)) {
            Return.NUMBER.setValue(Integer.parseInt(input));
            return Return.NUMBER;
        }
        return Return.ERROR;
    }

    private static void viewIngredients() {
        int iterator = 1;
        for (Ingredients ingredient : Ingredients.values()) {
            System.out.println(iterator + " " + ingredient.name());
            iterator++;
        }
    }

    private static void add_Ingredient_Menu(Dishes dish) {
        //clearConsole(); //clear console
        while (true) {
            System.out.println("\t Add any ingredients for " + dish.name() + "?\n");
            viewIngredients();
            System.out.println("\nE - exit\n");
            String menuInput = scanner.nextLine();
            Return thisCase = casesInput(menuInput);
            if (thisCase == Return.EXIT) return;
            else if (thisCase == Return.NUMBER &&
                    (thisCase.getValue() >= 1 && thisCase.getValue() <= Ingredients.values().length)) {
                dish.AddIngredients(Ingredients.values()[thisCase.getValue() - 1]);
            } else System.out.println("Wrong input");
        }
    }

    public static void Main_Menu() {
        do {
            //clearConsole();
            System.out.println("\t\tMENU:");
            ReadDishes();
            IsOrderEmpty();
            System.out.println("\nE - exit\n");
            String menuInput = scanner.nextLine();
            if (casesInput(menuInput) == Return.EXIT) {
                return;
            } else if (casesInput(menuInput) == Return.ORDER) {
                order.showOrderedDishes();
            } else if (casesInput(menuInput) == Return.NUMBER &&
                    (Return.NUMBER.getValue() >= 1 && Return.NUMBER.getValue() <= Dishes.values().length)) {
                order.addDish(Dishes.values()[Return.NUMBER.getValue() - 1]);
                add_Ingredient_Menu(order.getOrderDishes().getLast());
            } else System.out.println("Wrong input");
        } while (true);
    }

}
