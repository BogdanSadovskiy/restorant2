package com.restorant;

public enum Return {
    EXIT,
    ORDER,
    NUMBER,
    NOTHING,
    ERROR;
    private int value;

    Return() {
        this.value = 0;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
