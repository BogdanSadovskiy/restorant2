package com.restorant;

public class Main {
    public static void main(String[] args) {
        int startMoneyDollars = 1000;
        int startMoneyCents = 0;
        Bank bank = new Bank();
        EWallet eWallet = bank.createNewCard();
        CashWallet cashWallet = new CashWallet();
        WellcomeInfo.seperateMoney(startMoneyDollars,startMoneyCents, cashWallet, eWallet);
        WellcomeInfo.HELLO(eWallet, cashWallet);
        Menu.Main_Menu(bank, cashWallet);

    }
}
