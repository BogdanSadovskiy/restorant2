package com.restorant;

import java.util.ArrayList;
import java.util.List;

public enum Dishes {
    ANTIVEGETERIANA(195.00, "Four types of meat", new ArrayList<Ingredients>()),
    MEXICAN(160.00, "Salami, mozzarela", new ArrayList<Ingredients>());

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

    public void setAddIngredients(Ingredients Ingredients) {
        this.additionalIngredients.add(Ingredients);
        priceWithIngredient += Ingredients.getPrice();
    }

    public void removeIngredients(Ingredients removeIngredients) {
        this.additionalIngredients.remove(removeIngredients);
        priceWithIngredient -= removeIngredients.getPrice();
    }

    public String getDescription() {
        return description;
    }
}
