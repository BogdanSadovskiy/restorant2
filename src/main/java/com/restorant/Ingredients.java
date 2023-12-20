package com.restorant;

public enum Ingredients {


    MOZZARELLA(10.50),
    PEPPER(5.99),
    MEAT(21.99);
    private double price;
    public double getPrice( ) {
        return price;
    }

    Ingredients(double price){
        this.price = price;
    }
}
