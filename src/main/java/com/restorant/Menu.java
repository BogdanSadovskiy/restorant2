package com.restorant;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public final class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static Order order = new Order();

    private static void ReadDishes() {
        int iterator = 1;
        for (Dishes d : Dishes.values()) {
            System.out.println(iterator + " " + d.name());
            iterator++;
        }
    }

    private static void IsOrderEmpty() {
        if (!order.isOrderEmpty()) {
            System.out.println("O - order");
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
            Return.NUMBER.value = Integer.parseInt(input);
            return Return.NUMBER;
        }
        return Return.ERROR;
    }

    private void viewIngredients() {
        int iterator = 1;
        for (Ingredients ingredient : Ingredients.values()) {
            System.out.println(iterator + " " + ingredient.name());
            iterator++;
        }
    }

    public List<Ingredients> add_Ingredients_Menu(Dishes dishes) {
        System.out.print("\033[H\033[2J"); //clear console
        System.out.println('\t' + dishes.name() + "\n Add any ingredients?\n");
        viewIngredients();
        System.out.println("N - add nothing");
        System.out.println("\nE - exit\n");
        String menuInput = scanner.nextLine();
        return null;
    }

    public static void Main_Menu() {
        System.out.print("\033[H\033[2J"); //clear console
        System.out.println("\t\tMENU:");
        ReadDishes();
        IsOrderEmpty();
        System.out.println("\nE - exit\n");
        String menuInput = scanner.nextLine();
        if (casesInput(menuInput) == Return.EXIT) {
            return;
        } else if (casesInput(menuInput) == Return.ORDER) {
            order.showOrderedDishes();
        } else if (casesInput(menuInput) == Return.ERROR) {
            System.out.println("Wrong input");
        } else if (casesInput(menuInput) == Return.NUMBER &&
                (Return.NUMBER.value >= 1 && Return.NUMBER.value <= Dishes.values().length)) {
            order.addDish(Dishes.values()[Return.NUMBER.value - 1]);
        }
        Main_Menu();
    }

}
