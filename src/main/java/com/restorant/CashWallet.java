package com.restorant;

public class CashWallet extends Wallet {

    public CashWallet() {
        setYourMoney(0);
        setWalletType("ZANACHKA :D") ;

    }

    public boolean makePayment(double price) {
        if(price>getYourMoney()) return false;
        setYourMoney(getYourMoney()- price);
        return true;
    }
}
