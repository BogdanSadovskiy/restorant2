package com.restorant;

import java.util.ArrayList;
import java.util.List;

public enum Dishes {
    ANTIVEGETERIANA(195.00, "Four types of meat", null),
    MEXICAN(160.00, "Salami, mozzarela", null);

    private double price;
    private List<Ingredients> additionalIngredients = new ArrayList<>();
    private String description;

    Dishes(double price, String description, List<Ingredients> addIngredients) {
        this.price = price;
        this.description = description;
        this.additionalIngredients = addIngredients;
    }

    public double getPrice() {
        return price;
    }

    public List<Ingredients> getAddIngredients() {
        return additionalIngredients;
    }

    public void setAddIngredients(Ingredients Ingredients) {
        this.additionalIngredients.add(Ingredients);
        price += Ingredients.getPrice();
    }

    public void removeIngredients(Ingredients removeIngredients) {
        this.additionalIngredients.remove(removeIngredients);
        price -= removeIngredients.getPrice();
    }

    public String getDescription() {
        return description;
    }
}
