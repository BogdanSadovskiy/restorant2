package com.restorant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public enum Dishes {
    ANTIVEGETERIANA(195, 50,"Four types of meat", new ArrayList<Ingredients>()),
    MEXICAN(160, 99,"Salami, mozzarela", new ArrayList<Ingredients>()),
    COUNTRY(169,1,"feta, chicken, parmesan, arugula" , new ArrayList<>()),
    QUATTROCHEESE(205,69, "Four type of cheeses", new ArrayList<>());


    private int priceDollars;
    private int priceCents;

    private int priceWithIngredient;
    private List<Ingredients> additionalIngredients = new ArrayList<>();
    private String description;

    Dishes(int priceDollars, int priceCents, String description, List<Ingredients> addIngredients) {
        this.priceDollars = priceDollars;
        this.priceCents = priceCents;
        this.priceWithIngredient = Bank.convertToCents(priceDollars,priceCents);
        this.description = description;
        this.additionalIngredients = addIngredients;
    }

    public int getPriceDollars() {
        return priceDollars;
    }
    public int getPriceCents(){return priceCents;}
    public int getPriceWithIngredient() {
        return priceWithIngredient;
    }

    public List<Ingredients> getAddIngredients() {
        return additionalIngredients;
    }

    public void AddIngredients(Ingredients ingredient) {
        this.additionalIngredients.add(ingredient);
        priceWithIngredient += Bank.convertToCents(ingredient.getPriceDollars(),ingredient.getPriceCents());
    }

    public void removeIngredients(Ingredients ingredient) {
        this.additionalIngredients.remove(ingredient);
        priceWithIngredient -= Bank.convertToCents(ingredient.getPriceDollars(),ingredient.getPriceCents());
    }

    public String getDescription() {
        return description;
    }
}
