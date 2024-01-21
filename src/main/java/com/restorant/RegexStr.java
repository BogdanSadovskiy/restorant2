package com.restorant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStr {
    private static Pattern searchNumber = Pattern.compile("^[1-9]{1,2}$"); // only two numbers max
    private static Pattern cardNumber = Pattern.compile("^[\\d]{16}$"); //16 digits

    private RegexStr() {
    }

    public static boolean containsNumbers(String str) {
        return searchNumber.matcher(str).find();
    }

    public static boolean isCardCorrect(String str) {
        return cardNumber.matcher(str).find();
    }
}
