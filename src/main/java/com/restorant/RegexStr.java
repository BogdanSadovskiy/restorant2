package com.restorant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStr {
    private static Pattern searchNumber = Pattern.compile("^[1-9]{1,2}$"); // only two numbers max

    private  RegexStr() {
    }

    public static boolean containsNumbers(String str) {
        return searchNumber.matcher(str).find();
    }

}
