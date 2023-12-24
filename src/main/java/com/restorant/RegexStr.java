package com.restorant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStr {
    private static Pattern searchNumber = Pattern.compile("\\d+");

    private  RegexStr() {
    }

    public static boolean containsNumbers(String str) {
        return searchNumber.matcher(str).find();
    }

}
