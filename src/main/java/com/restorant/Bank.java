package com.restorant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Bank {
    private String bankName = "Monobank";


    private List<EWallet> wallets;


    public String getBankName() {
        return bankName;
    }
    public static String balanceReader(int dollars, int cents){
        if(cents< 10) return String.format("%d.0%d", dollars, cents);
        return String.format("%d.%d", dollars, cents);
    }
    public static String centsReader(int sum){
        int dollars = sum/100;
        int cents = sum%100;
        return balanceReader(dollars,cents);
    }
    public static int  convertToCents(int dollars, int cents){
        return cents + (dollars * 100);
    }

    public String getNewCardNumber() {
        Random r = new Random();
        int num = r.nextInt(4);
        String number = "1111222233334444";
        StringBuilder numberBuilding = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char currentChar = (char) (number.charAt(i) + num);
            numberBuilding.append(currentChar);
        }
        return numberBuilding.toString();
    }

    public EWallet createNewCard() {
        String newCardNumber = getNewCardNumber();
        EWallet eWallet = new EWallet(getBankName(), newCardNumber);
        addWallet(eWallet);
        return eWallet;
    }

    public void addWallet(EWallet wallet) {
        if (wallets == null) wallets = new ArrayList<>();
        wallets.add(wallet);
    }

    private EWallet isCardTrue(String cardNumber) {
        for (EWallet wallet : wallets) {
            if (wallet.getCardNumber().equals(cardNumber)) return wallet;
        }
        return null;
    }

    public static boolean isEnoughMoney(Wallet wallet, int sum) {

        return sum <= convertToCents(wallet.getYourDollars(), wallet.getYourCents());
    }

    public static void paymentProcess(Wallet wallet, int sum) {
        int walletSum = convertToCents(wallet.getYourDollars(), wallet.getYourCents())- sum;
        wallet.setYourMoney(walletSum/100, walletSum%100);
    }
    public static void deposit(Wallet wallet, int dollars, int cents){
        int balanceDollars = wallet.getYourDollars()+ dollars;
        int balanceCents = wallet.getYourCents() + cents;
        if(balanceCents>=100){
            balanceDollars+= balanceCents/100;
            balanceCents %=100;
        }
        wallet.setYourMoney(balanceDollars,balanceCents);
    }
    public static void paymentMessage(int sum, Wallet wallet){
        System.out.println(wallet.getWalletType());
        System.out.println("-" + centsReader(sum) + "$");
        System.out.println("Balance - " +
                balanceReader(wallet.getYourDollars(), wallet.getYourCents()) + "$");
    }
    public Return makePayment(int sum, String cardNumber) {
        EWallet thisWallet = isCardTrue(cardNumber);
        if (thisWallet == null) {
            Return.ERROR.setMessage("Not correct Card Number\n");
            return Return.ERROR;
        }
        if (!isEnoughMoney(thisWallet, sum)) {
            Return.ERROR.setMessage("Not Enough Money\nBalance - " +
                    balanceReader(thisWallet.getYourDollars(), thisWallet.getYourCents()) + "$");
            return Return.ERROR;
        }
        paymentProcess(thisWallet, sum);
        paymentMessage(sum, thisWallet);
        return Return.Success;
    }

}

