package com.restorant;

import java.util.*;

public class Order {
    private List<Dishes> orderDishes = new ArrayList<>();

    public void addDish(Dishes dish) {
        orderDishes.add(dish);
    }
    private List<String> CreatingOrder(){
        List<String> order = new ArrayList<>();
        int position = 1;
        order.add("      Order:");
        order.add(" ");
        for (Dishes d : orderDishes) {
            order.add("      Position #" + position);
            order.add(" " + d.name() + " " + d.getPrice() + '$');
            if (!d.getAddIngredients().isEmpty()) {
                for (Ingredients i : d.getAddIngredients()) {
                    order.add("    " + i.name() + ' ' + i.getPrice() + '$');
                }
            }
            order.add("   Price - " + d.getPriceWithIngredient() + '$');
            order.add(" ");
            position++;
        }
        return order;
    }
    public void showOrderedDishes() {
        frame(CreatingOrder());
    }

    public Boolean isOrderEmpty() {
        return orderDishes.isEmpty();
    }

    private int lengthOfLongestString(List<String> txt) {
        int maxString = 0;
        for (String str : txt) {
            if (str.length() > maxString) {
                maxString = str.length();
            }
        }
        return maxString;
    }

    private void borderFrame(int length) {
        System.out.print('+');
        for (int i = 0; i < length; i++) {
                System.out.print('-');
        }
        System.out.println('+');
    }

    private void contentFrame(List<String> txt, int length) {
        for (String str : txt) {
            System.out.print('|' + str);
            for (int i = 0; i < length - str.length(); i++) {
                System.out.print(' ');
            }
            System.out.println('|');
        }
    }

    public void frame(List<String> txt) {
        int length = lengthOfLongestString(txt);
        borderFrame(length);
        contentFrame(txt, length);
        borderFrame(length);
    }
}
