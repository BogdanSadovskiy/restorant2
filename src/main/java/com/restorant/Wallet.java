package com.restorant;

public class Wallet {
    private String WalletType = "";
    private int yourDollars = 0;
    private int yourCents = 0;

    public String getWalletType() {
        return WalletType;
    }

    public int getYourDollars() {
        return yourDollars;
    }
    public int getYourCents(){return yourCents; }

    public void setYourMoney(int dollars, int cents) {
        this.yourDollars = dollars;
        this.yourCents = cents;
    }
    public void setWalletType(String walletType){
        this.WalletType = walletType;
    }



}
