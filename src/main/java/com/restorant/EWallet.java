package com.restorant;

public class EWallet extends Wallet {
    public EWallet(String typeOfBank,  String cardNumber) {

        setYourMoney(0);
        setWalletType(typeOfBank) ;
        this.cardNumber = cardNumber;
    }

    String cardNumber;
    public void addMoney(double income){
        this.setYourMoney(this.getYourMoney()+income);
    }
    public String getCardNumber(){
        return cardNumber;
    }

}
