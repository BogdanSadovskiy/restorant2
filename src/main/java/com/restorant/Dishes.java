package com.restorant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public enum Dishes {
    ANTIVEGETERIANA(195.00, "Four types of meat", new ArrayList<Ingredients>()),
    MEXICAN(160.00, "Salami, mozzarela", new ArrayList<Ingredients>()),
    COUNTRY(169.50,"feta, chicken, parmesan, arugula" , new ArrayList<>()),
    QUATTROCHEESE(205, "Four type of cheeses", new ArrayList<>());


    private double price;
    private double priceWithIngredient;
    private List<Ingredients> additionalIngredients = new ArrayList<>();
    private String description;

    Dishes(double price, String description, List<Ingredients> addIngredients) {
        this.price = price;
        this.priceWithIngredient = price;
        this.description = description;
        this.additionalIngredients = addIngredients;
    }

    public double getPrice() {
        return price;
    }
    public double getPriceWithIngredient() {
        return priceWithIngredient;
    }

    public List<Ingredients> getAddIngredients() {
        return additionalIngredients;
    }

    public void AddIngredients(Ingredients Ingredient) {
        this.additionalIngredients.add(Ingredient);
        priceWithIngredient += Ingredient.getPrice();
    }

    public void removeIngredients(Ingredients removeIngredient) {
        this.additionalIngredients.remove(removeIngredient);
        priceWithIngredient -= removeIngredient.getPrice();
    }

    public String getDescription() {
        return description;
    }
}
