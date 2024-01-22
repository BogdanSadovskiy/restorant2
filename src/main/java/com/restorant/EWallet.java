package com.restorant;

public class EWallet extends Wallet {
    public EWallet(String typeOfBank,  String cardNumber) {

        setYourMoney(0,0);
        setWalletType(typeOfBank) ;
        this.cardNumber = cardNumber;
    }

    String cardNumber;

    public String getCardNumber(){
        return cardNumber;
    }

}
