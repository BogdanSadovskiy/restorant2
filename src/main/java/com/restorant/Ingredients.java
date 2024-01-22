package com.restorant;

public enum Ingredients {


    CHEESE(10,50),
    PEPPER(5,99),
    MEAT(21,99),
    OLIVES(7,75);
    private int priceDollars;
    private  int priceCents;
    public int getPriceDollars( ) {
        return priceDollars;
    }
    public int getPriceCents(){return priceCents;}

    Ingredients(int priceDollars, int priceCents){

        this.priceDollars = priceDollars;
        this.priceCents = priceCents;
    }
}
