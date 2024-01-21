package com.restorant;

public class Main {
    public static void main(String[] args) {
        double startMoney = 1000;
        Bank bank = new Bank();
        EWallet eWallet = bank.createNewCard();
        CashWallet cashWallet = new CashWallet();
        WellcomeInfo.seperateMoney(startMoney, cashWallet, eWallet);
        WellcomeInfo.HELLO(eWallet, cashWallet);
        Menu.Main_Menu(bank, cashWallet);

    }
}
