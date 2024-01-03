package com.restorant;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


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

    private static void DrawLines(int n) {
        if (n == 1) System.out.println("\n------------------------------------------------------");
        else if (n == 2) System.out.println("------------------------------------------------------\n");
    }

    private static void ReadDishes() {
        int iterator = 1;
        for (Dishes d : Dishes.values()) {
            System.out.println(iterator + ") " + d.name() +
                    " (" + d.getDescription() + ')' + " - " + d.getPrice() + "$");
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

    private static boolean isIngredientAlreadyAdded(Dishes dish, Ingredients ingredient) {
        List<Ingredients> ingredientsInDish = dish.getAddIngredients();
        for (Ingredients i : ingredientsInDish) {
            if (i.name().equals(ingredient.name())) return true;
        }
        return false;
    }

    private static int interpretIngredientChoosing(TwoNumbers tmp, int iterator) {
        int tmpI = 0;
        int actualI = 0;
        for (int visibleI : tmp.getFirst()) {
            if (iterator == visibleI) actualI = tmp.getSecond().get(tmpI);
            tmpI++;
        }
        return actualI;
    }

    private static TwoNumbers viewIngredients(Dishes dish) {
        int iterator = 1;
        int iteratorForNonAddedIngredients = iterator;
        TwoNumbers tmp = new TwoNumbers();

        for (Ingredients ingredient : Ingredients.values()) {
            if (!isIngredientAlreadyAdded(dish, ingredient)) {
                System.out.println(iteratorForNonAddedIngredients + ") " +
                        ingredient.name() + " - " + ingredient.getPrice() + '$');
                tmp.setFirst(iteratorForNonAddedIngredients);  //saving in first Non-added Ingredients
                iteratorForNonAddedIngredients++;
                tmp.setSecond(iterator);
            }
            iterator++;
        }
        if (tmp.getFirst().size() == 0) System.out.println("\t Nothing to add more...");
        return tmp;
    }

    private static void add_Ingredient_Menu(Dishes dish) {
        //clearConsole(); //clear console
        while (true) {
            DrawLines(1);
            System.out.println("\t Add any ingredients for " + dish.name() + "?\n");
            TwoNumbers tmp = viewIngredients(dish);
            System.out.println("\nE - exit");
            DrawLines(2);
            String menuInput = scanner.nextLine();
            Return thisCase = casesInput(menuInput);
            if (thisCase == Return.EXIT) return;
            else if (thisCase == Return.NUMBER &&
                    (thisCase.getValue() >= 1 && thisCase.getValue() <= tmp.getFirst().size())) {
                dish.AddIngredients(Ingredients.values()[interpretIngredientChoosing(tmp, thisCase.getValue()) - 1]);
                System.out.println("Added " + dish.getAddIngredients().getLast().name());
            } else System.out.println("Wrong input");
        }
    }

    private static boolean Menu_Buttons() {
        String menuInput = scanner.nextLine();
        if (casesInput(menuInput) == Return.EXIT) {
            return false;
        } else if (casesInput(menuInput) == Return.ORDER) {
            order.showOrderedDishes();
        } else if (casesInput(menuInput) == Return.NUMBER &&
                (Return.NUMBER.getValue() >= 1 && Return.NUMBER.getValue() <= Dishes.values().length)) {
            order.addDish(Dishes.values()[Return.NUMBER.getValue() - 1]);
            System.out.println("You have chosen " + Dishes.values()[Return.NUMBER.getValue() - 1].name());
            add_Ingredient_Menu(order.getOrderDishes().getLast());
        } else System.out.println("Wrong input");
        return true;
    }

    public static void Main_Menu() {
        do {
            //clearConsole();
            DrawLines(1);
            System.out.println("\t\tMENU:");
            ReadDishes();
            IsOrderEmpty();
            System.out.println("\nE - exit");
            DrawLines(2);
        } while (Menu_Buttons());
    }

}
