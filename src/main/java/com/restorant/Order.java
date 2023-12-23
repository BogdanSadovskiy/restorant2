package com.restorant;

import java.util.*;

public class Order {
    private List<Dishes> orderDishes = new ArrayList<>();

    public void addDish(Dishes dish) {
        orderDishes.add(dish);
    }

    public void showOrderedDishes() {
        List<String> order = new ArrayList<>();
        int position = 1;
        order.add("      Order:");
        order.add("\n");
        for (Dishes d : orderDishes) {
            order.add("      Position #" + position);
            order.add(" " + d.name() + " " + d.getPrice() + '$');
            if (d.getAddIngredients().size() > 0) {
                for (Ingredients i : d.getAddIngredients()) {
                    order.add("    " + i.name() + ' ' + i.getPrice() + '$');
                }
            }
            order.add("   Price - " + d.getPriceWithIngredient() + '$');
            order.add("\n");
        }
        frame(order);
    }

    public Boolean isOrderEmpty() {
        return this.orderDishes.size() == 0;
    }

    public void frame(List<String> txt) {
        int maxString = 0;
        for (String str : txt) {
            if (str.length() > maxString) {
                maxString = str.length();
            }
        }
        for (int i = 0; i < maxString; i++) {
            if (i == 0 || i == maxString - 1) {
                System.out.println('+');
            } else {
                System.out.println('-');
            }
        }
        for (String str : txt) {
            System.out.println('|' + str);
            for (int i = 0; i < (maxString - str.length() - 1); i++) {
                System.out.print(' ');
            }
            System.out.println('|');
        }
        for (int i = 0; i < maxString; i++) {
            if (i == 0 || i == maxString - 1) {
                System.out.println('+');
            } else {
                System.out.println('-');
            }
        }
    }
}
