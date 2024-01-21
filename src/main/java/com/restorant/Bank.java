package com.restorant;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Bank {
    private String bankName = "Monobank";


    private List<EWallet> wallets;


    public String getBankName() {
        return bankName;
    }
public EWallet sad(){
        return wallets.getFirst();
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

    private boolean isEnoughMoney(EWallet wallet, double sum) {

        return sum <= wallet.getYourMoney();
    }

    public static void paymentProcess(EWallet wallet, double sum) {

        wallet.setYourMoney(wallet.getYourMoney() + sum);
    }
    public static void paymentMessage(double sum, Wallet wallet){
        System.out.println(wallet.getWalletType());
        System.out.println("-" + sum + "$");
        System.out.println("Balance - " + wallet.getYourMoney() + "$");
    }
    public Return makePayment(double sum, String cardNumber) {
        EWallet thisWallet = isCardTrue(cardNumber);
        if (thisWallet == null) {
            Return.ERROR.setMessage("Not correct Card Number\n");
            return Return.ERROR;
        }
        if (!isEnoughMoney(thisWallet, sum)) {
            Return.ERROR.setMessage("Not Enough Money\n");
            return Return.ERROR;
        }
        paymentProcess(thisWallet, -sum);
        paymentMessage(sum, thisWallet);
        return Return.Success;
    }

}

