package com.restorant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Order {
    private List<Dishes> orderDishes = new ArrayList<>();
    private double orderPrice;

    public void addDish(Dishes dish) {
        orderDishes.add(dish);
    }

    public void addIngredients(Dishes dish) {

    }

    public List<Dishes> getOrderDishes() {
        return this.orderDishes;
    }

    private String OrderPriceCalculating() {
        double tmpPrice = 0;
        for (Dishes d : orderDishes) {
            tmpPrice += d.getPriceWithIngredient();
        }
        this.orderPrice = tmpPrice;
        return String.format("%.2f", tmpPrice) ;
    }

    private List<String> CreatingOrder() {
        List<String> order = new ArrayList<>();
        int position = 1;
        order.add("      Order:");
        order.add(" ");
        for (Dishes d : orderDishes) {
            order.add("        Position #" + position);
            order.add("   " + d.name() + "   " + d.getPrice() + '$');
            if (!d.getAddIngredients().isEmpty()) {
                int numberOfIngredients = 1;
                for (Ingredients i : d.getAddIngredients()) {
                    order.add("      " + numberOfIngredients + ") " + i.name() + ' ' + i.getPrice() + '$');
                    numberOfIngredients++;
                }
            }
            order.add(" Position prise - " + String.format("%.2f",d.getPriceWithIngredient()) + '$');
            order.add(" ");
            position++;
        }
        order.add(" PRICE - " + OrderPriceCalculating()+ "$");
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
        int length = lengthOfLongestString(txt) + 1;
        borderFrame(length);
        contentFrame(txt, length);
        borderFrame(length);
    }
}
