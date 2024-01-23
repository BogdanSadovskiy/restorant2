package com.restorant;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Dishes> orderDishes = new ArrayList<>();
    private int orderPrice;

    public void addDish(Dishes dish) {
        orderDishes.add(dish);
        OrderPriceCalculating();
    }

    public void removeDish(Dishes dish) {
        orderDishes.remove(dish);
        OrderPriceCalculating();
    }


    public List<Dishes> getOrderDishes() {
        return this.orderDishes;
    }

    private String OrderPriceCalculating() {
        int tmpPrice = 0;
        for (Dishes d : orderDishes) {
            tmpPrice += d.getPriceWithIngredient();
        }
        this.orderPrice = tmpPrice;
        return Bank.centsReader(orderPrice);
    }

    public  List<String> CreatingOrder() {
        List<String> order = new ArrayList<>();
        int position = 1;
        order.add("      Order:");
        order.add(" ");
        for (Dishes d : orderDishes) {
            order.add("        Position #" + position);
            order.add("   " + d.name() + ".... " +
                    Bank.balanceReader(d.getPriceDollars(), d.getPriceCents()) + '$');
            if (!d.getAddIngredients().isEmpty()) {
                int numberOfIngredients = 1;
                for (Ingredients i : d.getAddIngredients()) {
                    order.add("      " + numberOfIngredients + ") " + i.name() + ".... " +
                            Bank.balanceReader(i.getPriceDollars(), i.getPriceCents()) + '$');
                    numberOfIngredients++;
                }
            }
            order.add(" Position prise - " + Bank.centsReader(d.getPriceWithIngredient()) + '$');
            order.add(" ");
            position++;
        }
        order.add(" PRICE - " + OrderPriceCalculating() + "$");
        return order;
    }

    public void showOrderedDishes() {
        frame(CreatingOrder());
    }

    public Boolean isOrderEmpty() {
        return orderDishes.isEmpty();
    }

    public int lengthOfLongestString(List<String> txt) {
        int maxString = 0;
        for (String str : txt) {
            if (str.length() > maxString) {
                maxString = str.length();
            }
        }
        return maxString;
    }

    public void borderFrame(int length) {
        System.out.print('+');
        for (int i = 0; i < length; i++) {
            System.out.print('-');
        }
        System.out.println('+');
    }

    public void contentFrame(List<String> txt, int length) {
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

    public int getOrderPrice() {
        return this.orderPrice;
    }

    public void resetIngredients(){
        for(Dishes d : orderDishes){
            d.resetIngredients();
        }
    }
}
