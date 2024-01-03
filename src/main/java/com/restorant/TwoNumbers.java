package com.restorant;

import java.util.ArrayList;
import java.util.List;

public class TwoNumbers {
    private List<Integer> first;
    private List<Integer> second;

    TwoNumbers() {
        first = new ArrayList<>();
        second = new ArrayList<>();
    }

    public void setFirst(int first) {
        this.first.add(first);
    }

    public List<Integer> getFirst() {
        return first;
    }

    public void setSecond(int second) {
        this.second.add(second);
    }

    public List<Integer> getSecond() {
        return second;
    }
}
