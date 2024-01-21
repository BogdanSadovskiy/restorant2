package com.restorant;

public class WellcomeInfo {

    public static void seperateMoney(double sum, CashWallet cashWallet, EWallet eWallet) {
        double eWalletSum = sum / 2 + ((sum / 2) / 4);
        double cashSum = sum - eWalletSum;
        Bank.paymentProcess(eWallet, eWalletSum);
        cashWallet.setYourMoney(cashWallet.getYourMoney() + cashSum);
    }

    private static void printHello() {
        System.out.println("\tHello \n In this app you can order a pizza and add ingredients" +
                "\n to it. Next step - you can create an order and pay \n" +
                " for it in a convenient way - in Cash or via a Bank");
    }

    public static String CardNumberWriter(String cardNumber) {
        int iterator = 1;
        String str = "";
        for (int i = 0; i < cardNumber.length(); i++) {
            str += cardNumber.charAt(i);
            if (iterator == 4) {
                str += ' ';
                iterator = 1;
                continue;
            }
            iterator++;
        }
        return str;
    }

    private static void yourStats(EWallet eWallet, CashWallet cashWallet) {
        System.out.println("\tYour starting cash - " + cashWallet.getYourMoney() + "$");
        System.out.println("\tYour bank card number: " + CardNumberWriter(eWallet.getCardNumber()));
        System.out.println("\tYour starting bank card money - " + eWallet.getYourMoney() + "$");
    }

    public static void HELLO(EWallet eWallet, CashWallet cashWallet) {
        Menu.DrawLines(1);
        printHello();
        yourStats(eWallet, cashWallet);
        Menu.DrawLines(1);
        Menu.DrawLines(2);
    }
}
