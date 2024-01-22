package com.restorant;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


public final class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static Order order;
    private static Bank bank;
    private static CashWallet yourCashWallet;

    private static void initializateOrder() {
        if (order == null) order = new Order();
    }

    public static void addPaymentsInfo(Bank bank_, CashWallet cashWallet) {
        bank = bank_;
        yourCashWallet = cashWallet;
    }



    public static void clearConsole() {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (os.contains("win")) {
                processBuilder.command("cmd", "/c", "cls");
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                processBuilder.command("clear");
            } else {
                System.out.println("Unsupported operation for this OS.");
                return;
            }
            Process process = processBuilder.inheritIO().start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the console: " + e.getMessage());
        }
    }

    public static void DrawLines(int n) {
        if (n == 1) System.out.println("\n------------------------------------------------------");
        else if (n == 2) System.out.println("------------------------------------------------------\n");
    }

    private static void ReadDishes() {
        int iterator = 1;
        for (Dishes d : Dishes.values()) {
            System.out.println(iterator + ") " + d.name() +
                    " (" + d.getDescription() + ')' + " - " +
                    Bank.balanceReader(d.getPriceDollars(),d.getPriceCents()) + "$");
            iterator++;
        }
    }

    private static void IsOrderEmpty() {
        if (!order.isOrderEmpty()) {
            System.out.println("O - go to order");
        }
    }
    private static Return executabledExit(Return case_) {
        if (case_ == Return.EXIT) {
            return Return.EXITEXECUTABLED;
        }
        return case_;
    }
    private static Return casesInput(String input) {
        if (Objects.equals(input, "e") || "E".equals(input)) {
            return Return.EXIT;
        }
        if ((Objects.equals(input, "o") || "O".equals(input)) && !order.isOrderEmpty()) {
            return Return.ORDER;
        }
        if (Objects.equals(input, "n") || "N".equals(input)) {
            return Return.NOTHING;
        }
        if (RegexStr.containsNumbers(input)) {
            Return.NUMBER.setValue(Integer.parseInt(input));
            return Return.NUMBER;
        }
        return Return.ERROR;
    }

    private static boolean isIngredientAlreadyAdded(Dishes dish, Ingredients ingredient) {
        List<Ingredients> ingredientsInDish = dish.getAddIngredients();
        for (Ingredients i : ingredientsInDish) {
            if (i.name().equals(ingredient.name())) return true;
        }
        return false;
    }

    private static int interpretIngredientChoosing(TwoNumbers tmp, int iterator) {
        int tmpI = 0;
        int actualI = 0;
        for (int visibleI : tmp.getFirst()) {
            if (iterator == visibleI) actualI = tmp.getSecond().get(tmpI);
            tmpI++;
        }
        return actualI;
    }

    private static TwoNumbers viewIngredients(Dishes dish) {
        int iterator = 1;
        int iteratorForNonAddedIngredients = iterator;
        TwoNumbers tmp = new TwoNumbers();

        for (Ingredients ingredient : Ingredients.values()) {
            if (!isIngredientAlreadyAdded(dish, ingredient)) {
                System.out.println(iteratorForNonAddedIngredients + ") " +
                        ingredient.name() + " - " +
                        Bank.balanceReader(ingredient.getPriceDollars(),ingredient.getPriceCents())+ '$');
                tmp.setFirst(iteratorForNonAddedIngredients);  //saving in first Non-added Ingredients
                iteratorForNonAddedIngredients++;
                tmp.setSecond(iterator);
            }
            iterator++;
        }
        if (tmp.getFirst().size() == 0) System.out.println("\t Nothing to add more...");
        return tmp;
    }

    private static void add_Ingredient_Menu(Dishes dish) {
        //clearConsole(); //clear console
        while (true) {
            DrawLines(1);
            System.out.println("\t Add any ingredients for " + dish.name() + "?\n");
            TwoNumbers tmp = viewIngredients(dish);
            System.out.println("\nE - exit");
            DrawLines(2);
            String menuInput = scanner.nextLine();
            Return thisCase = casesInput(menuInput);
            if (thisCase == Return.EXIT) return;
            else if (thisCase == Return.NUMBER &&
                    (thisCase.getValue() >= 1 && thisCase.getValue() <= tmp.getFirst().size())) {
                dish.AddIngredients(Ingredients.values()[interpretIngredientChoosing(tmp, thisCase.getValue()) - 1]);
                System.out.println("Added " + dish.getAddIngredients().getLast().name());
            } else System.out.println("Wrong input");
        }
    }
    private  static Return confirmPayment(){
        System.out.println(Bank.centsReader(order.getOrderPrice()) + "S\nPay - 1\nExit - E");
        DrawLines(2);
        String input = scanner.nextLine();
        Return thisCase = casesInput(input);
        if(thisCase == Return.EXIT)return Return.EXIT;
        else if (thisCase== Return.NUMBER && thisCase.getValue() == 1) return Return.Success;
        System.out.println("Wrong input"); return Return.ERROR;
    }
    private static Return inputCardNumber() {
        String input;
        boolean bool;
        do {
            DrawLines(1);
            System.out.println("Input Card Number");
            System.out.println("Exit - E");
            DrawLines(2);
            input = scanner.nextLine();
            if (casesInput(input) == Return.EXIT) return Return.EXIT;
            bool = RegexStr.isCardCorrect(input);
            if (!bool) {
                System.out.println("Card number has to be 16 digits long");
            }
        } while (!bool);
        Return.NUMBER.setMessage(input);
        return Return.NUMBER;
    }

    private static Return payProcessMenu() {
        Return menu;
        do {
            DrawLines(1);
            System.out.println(" Payment methods:");
            System.out.println(" Cash ------ 1");
            System.out.println(" Bank ------ 2");
            System.out.println(" Exit ------ E");
            DrawLines(2);
            menu = payProcessMenuButtons();
            if (menu == Return.Success) return Return.Success;
            if (menu == Return.EXIT) return  Return.EXITEXECUTABLED;
        } while (true);
    }

    private static Return payProcessMenuButtons() {
        String input = scanner.nextLine();
        Return menu = casesInput(input);
        if (menu == Return.EXIT) return Return.EXIT;
        if (menu == Return.NUMBER && menu.getValue() == 1) return executabledExit(cashMethod());
        if (menu == Return.NUMBER && menu.getValue() == 2) return executabledExit(bankMethod());
        System.out.println("Wrong input");
        Return.ERROR.setError(Errors.InputError);
        return Return.ERROR;
    }

    private static Return bankMethod() {
        Return inputCardNumber_ = inputCardNumber();
        if (inputCardNumber_ != Return.EXIT) {
            String cardNumber = inputCardNumber_.getMessage();
            while(true){
                DrawLines(1);
                System.out.println(WellcomeInfo.CardNumberWriter(cardNumber));
                Return confirmedPayment = confirmPayment();
                if(confirmedPayment == Return.EXIT) return Return.EXIT;
                if(confirmedPayment == Return.Success)break;
            }
            return bankMethodFinish(cardNumber);
        }
        return Return.EXIT;
    }
private static Return bankMethodFinish(String cardNumber){
    Return payment = bank.makePayment(order.getOrderPrice(), cardNumber);
    if (payment == Return.ERROR) {
        System.out.println(payment.getMessage());
        return Return.ERROR;
    }
    System.out.println(payment.getMessage());
    return Return.Success;
}
    private static Return cashMethod(){
        while(true) {
            DrawLines(1);
            System.out.println("Cash Payment");
            Return thisCase = confirmPayment();
            if (thisCase == Return.EXIT) return Return.EXIT;
            if(thisCase == Return.Success) break;
        }
        return cashMethodFinish();
    }

    private static  Return cashMethodFinish(){
        if(!Bank.isEnoughMoney(yourCashWallet,order.getOrderPrice())){
            System.out.println("Not enough cash");
            return Return.ERROR;
        }
        Bank.paymentProcess(yourCashWallet, order.getOrderPrice());
        System.out.println("Success");
        Bank.paymentMessage(order.getOrderPrice(),yourCashWallet);
        return  Return.Success;
    }
    private static Return OrderOptionsMenuButtons() {
        String input = scanner.nextLine();
        Return buttons = casesInput(input);
        if (buttons == Return.EXIT) return Return.EXIT;
        if (buttons == Return.NUMBER && buttons.getValue() == 1) {
            return executabledExit(payProcessMenu());
        }
        Return.ERROR.setMessage("Wrong input");
        return Return.ERROR;
    }

    private static void OrderOptionsMenu() {
        while (true) {
            DrawLines(1);
            System.out.println("Make payment - 1");
            System.out.println("Edit order --- 2");
            System.out.println("Exit --------- E");
            DrawLines(2);
            Return menu = OrderOptionsMenuButtons();
            if (menu == Return.Success) {
                order = null;
                initializateOrder();
                return;
            }
            if (menu == Return.EXIT) return;
        }
    }

    private static void OrderOptions() {
        OrderOptionsMenu();

    }

    private static boolean Menu_Buttons() {
        String menuInput = scanner.nextLine();
        if (casesInput(menuInput) == Return.EXIT) {
            return false;
        } else if (casesInput(menuInput) == Return.ORDER) {
            order.showOrderedDishes();
            OrderOptionsMenu();
        } else if (casesInput(menuInput) == Return.NUMBER &&
                (Return.NUMBER.getValue() >= 1 && Return.NUMBER.getValue() <= Dishes.values().length)) {
            order.addDish(Dishes.values()[Return.NUMBER.getValue() - 1]);
            System.out.println("You have chosen " + Dishes.values()[Return.NUMBER.getValue() - 1].name());
            add_Ingredient_Menu(order.getOrderDishes().getLast());
        } else System.out.println("Wrong input");
        return true;
    }

    public static void Main_Menu(Bank bank_, CashWallet cashWallet_) {
        addPaymentsInfo(bank_, cashWallet_);
        do {
            //clearConsole();
            initializateOrder();
            DrawLines(1);
            System.out.println("\t\tMENU:\n");
            ReadDishes();
            IsOrderEmpty();
            System.out.println("\nE - exit");
            DrawLines(2);
        } while (Menu_Buttons());
    }

}
