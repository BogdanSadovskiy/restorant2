package com.restorant;

public enum Ingredients {


    CHEESE(10.50),
    PEPPER(5.99),
    MEAT(21.99),
    OLIVES(7.75);
    private double price;
    public double getPrice( ) {
        return price;
    }

    Ingredients(double price){
        this.price = price;
    }
}
