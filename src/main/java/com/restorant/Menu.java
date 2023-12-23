package com.restorant;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    private  Scanner scanner = new Scanner(System.in);
    private int iterator;
    private String menuInput;
    private int pursedNumber;
    private Order order = new Order();

    public void Main_Menu() {

        System.out.print("\033[H\033[2J"); //clear console
        System.out.println("\t\tMENU:");
        for (Dishes d : Dishes.values()) {
            System.out.println(iterator + " " + d.name());
            iterator++;
        }
        System.out.println("\nE - exit\n");
        iterator = 1;
        this.menuInput = scanner.nextLine();

        if (Objects.equals(menuInput, "e") || "E".equals(menuInput)) {
            return;
        }
        try {
            pursedNumber = Integer.parseInt(menuInput);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
            Main_Menu();
        }
        if (Integer.parseInt(menuInput) >= 1 && Integer.parseInt(menuInput) <= Dishes.values().length) {
            order.addDish(Dishes.values()[pursedNumber - 1]);
        }
    }


}
