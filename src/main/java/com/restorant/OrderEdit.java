package com.restorant;

import java.util.Scanner;

public class OrderEdit {
    private static Return thisCase;
    public static Return Input(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return Menu.casesInput(input);
    }
    public static void OrderEditMain(Order order) {
        while(true){
        PizzaOrdered(order);
        thisCase = Input();
        if(thisCase == Return.EXIT) break;
        if(thisCase == Return.NUMBER &&
                (thisCase.getValue() > 0 && thisCase.getValue()<= order.getOrderDishes().size())){
            DishOptions(order.getOrderDishes().get(thisCase.getValue()-1));
            break;
        }
        System.out.println("Wrong input");
        }
    }
    private static void DishOptionsMenu(Dishes dish) {
        Menu.DrawLines(1);
        System.out.println(dish.name());
        for(Ingredients i : dish.getAddIngredients()){

        }
    }
    private static void DishOptions(Dishes dish) {

    }
    private static void PizzaOrdered(Order order) {
        int iterator = 0;
        Menu.DrawLines(1);
        System.out.println("Choose order position:");
        for(Dishes dish : order.getOrderDishes()) {
            System.out.println(iterator++ + ") " + dish.name());
        }
        System.out.println("E - Exit");
        Menu.DrawLines(2);

    }
}
