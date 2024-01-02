package com.restorant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Wish {
    private static Wish instance = null;
    private static List<String> wishes = new ArrayList<>();
    private Wish(){
        wishes.add("Endless joy, peace.");
        wishes.add("Health, love, success.");
        wishes.add("Adventure, discovery, happiness.");
        wishes.add("Prosperity, growth, fulfillment");
        wishes.add("Dreams come true.");
        wishes.add("Love, laughter, contentment.");
        wishes.add("Abundance, harmony, serenity.");
        wishes.add("Kindness, compassion, empathy.");
        wishes.add("Courage, resilience, strength.");
        wishes.add("Family, unity, gratitude");
        wishes.add("Wealth, wisdom, purpose.");
        wishes.add("Hope, faith, positivity.");
        wishes.add("Friendship, trust, loyalty.");
        wishes.add("Creativity, inspiration, innovation.");
        wishes.add("Wellness, balance, vitality.");
        wishes.add("Passion, motivation, achievement.");
        wishes.add("Wisdom, knowledge, understanding.");
        wishes.add("Generosity, altruism, contribution.");
        wishes.add("Tranquility, mindfulness, serendipity.");
        wishes.add("Growth, learning, enlightenment.");

    }

    public static Wish getInstance(){
        if (instance == null) {
            synchronized (Wish.class) {
                instance = new Wish();
            }
        }
        return instance;
    }

    protected Object readResolve() {
        return getInstance();
    }
    public static String getWish(){
        getInstance();
        Random r = new Random();
        return wishes.get( r.nextInt(wishes.size()));
    }
}
