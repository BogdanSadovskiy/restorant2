package com.restorant;

public enum Return {
    EXIT,
    EXITEXECUTABLED,
    ADD,
    REMOVE,
    ORDER,
    NUMBER,
    NOTHING,
    ERROR,
    Success;

    private Errors error;
    private int value;
    private String message;

    Return() {
        this.value = 0;
        this.message = "";
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getValue() {
        return this.value;
    }
    public Errors getError(){
        return error;
    }
    public void setError(Errors error_){
        error = error_;
    }

}
