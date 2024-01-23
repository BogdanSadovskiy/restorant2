package com.restorant;

import java.util.Scanner;

public class OrderEdit {
    private static Return thisCase;

    public static  void emptyOrderMessage(){
        Menu.DrawLines(1);
        System.out.println("Order is empty");
        Menu.DrawLines(2);
    }
    public static boolean isOrderEmpty(Order order) {
        return order.getOrderDishes().isEmpty();
    }

    public static Return Input() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return Menu.casesInput(input);
    }

    public static void OrderEditMain(Order order) {
        while (true) {
            if(isOrderEmpty(order)){
                emptyOrderMessage();
                return;
            }
            PizzaOrdered(order);
            thisCase = Input();
            if (thisCase == Return.EXIT) break;
            if (thisCase == Return.NUMBER &&
                    (thisCase.getValue() > 0 && thisCase.getValue() <= order.getOrderDishes().size())) {
                DishOptions(order, order.getOrderDishes().get(thisCase.getValue() - 1));
            } else System.out.println("Wrong input");
        }
    }


    private static Return removeIngredientMenu(Dishes dish, Ingredients ingredient) {
        Menu.DrawLines(1);
        System.out.println("Remove " + ingredient.name() + " from " + dish.name() + "?");
        System.out.println("1 - YES\nE - Exit");
        Menu.DrawLines(2);
        return Input();
    }

    private static void removeIngredient(Dishes dish, Ingredients ingredient) {
        while (true) {
            thisCase = removeIngredientMenu(dish, ingredient);
            if (thisCase == Return.EXIT) return;
            if (thisCase == Return.NUMBER && thisCase.getValue() == 1) {
                dish.removeIngredients(ingredient);
                return;
            }
            System.out.println("Wrong input");
        }
    }

    private static void DishOptionsMenu(Dishes dish) {
        int iterator = 1;
        Menu.DrawLines(1);
        System.out.println(dish.name() + " - " +
                Bank.balanceReader(dish.getPriceDollars(), dish.getPriceCents()) + "$");
        for (Ingredients i : dish.getAddIngredients()) {
            System.out.println(" " + iterator++ + ") " + i.name() + " - " +
                    Bank.balanceReader(i.getPriceDollars(), i.getPriceCents()) + "$");
        }
        System.out.println("\nR - Remove dish from order");
        System.out.println("A - Add ingredient");
        System.out.println("Input [number] to remove ingredient");
        System.out.println("E - exit");
        Menu.DrawLines(2);
    }

    private static void DishOptions(Order order, Dishes dish) {
        while (true) {
            DishOptionsMenu(dish);
            thisCase = Input();
            if (thisCase == Return.EXIT) break;
            else if (thisCase == Return.REMOVE) {
                order.removeDish(dish);
                return;
            } else if (thisCase == Return.ADD) Menu.add_Ingredient_Menu(dish);
            else if (thisCase == Return.NUMBER &&
                    thisCase.getValue() > 0 && thisCase.getValue() <= dish.getAddIngredients().size())
                removeIngredient(dish, dish.getAddIngredients().get(thisCase.getValue() - 1));
            else System.out.println("Wrong input");
        }
    }

    private static void PizzaOrdered(Order order) {
        int iterator = 1;
        Menu.DrawLines(1);
        System.out.println("Choose order position:");
        for (Dishes dish : order.getOrderDishes()) {
            System.out.println(iterator++ + ") " + dish.name() + " - " +
                    Bank.centsReader(dish.getPriceWithIngredient()) + "$");
        }
        System.out.println("E - Exit");
        Menu.DrawLines(2);

    }
}
