package com.restorant;

public class Wallet {
    private String WalletType = "";
    private double YourMoney = 0.0;

    public String getWalletType() {
        return WalletType;
    }

    public double getYourMoney() {
        return YourMoney;
    }

    public void setYourMoney(double income) {
        this.YourMoney = income;
    }
    public void setWalletType(String walletType){
        this.WalletType = walletType;
    }



}
