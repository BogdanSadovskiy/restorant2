package com.restorant;

public class WellcomeInfo {

    public static void seperateMoney(int dollars, int cents, CashWallet cashWallet, EWallet eWallet) {
        int allCents = Bank.convertToCents(dollars, cents);
        int eWalletSum = allCents / 2 + ((allCents / 2) / 4);
        int cashSum = allCents - eWalletSum;
        Bank.deposit(eWallet, eWalletSum/100, eWalletSum%100);
        cashWallet.setYourMoney(cashSum/100, cashSum%100);
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
        System.out.println("\tYour starting cash - " +
                Bank.balanceReader(cashWallet.getYourDollars(),cashWallet.getYourCents()) + "$");
        System.out.println("\tYour bank card number: " + CardNumberWriter(eWallet.getCardNumber()));
        System.out.println("\tYour starting bank card money - " +
                Bank.balanceReader(eWallet.getYourDollars(),eWallet.getYourCents()) + "$");
    }

    public static void HELLO(EWallet eWallet, CashWallet cashWallet) {
        Menu.DrawLines(1);
        printHello();
        yourStats(eWallet, cashWallet);
        Menu.DrawLines(1);
        Menu.DrawLines(2);
    }
}
