package com.restorant;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private static List<String> check = new ArrayList<>();

    private static List<String> setCheck() {
        List<String> checkWishPart = new ArrayList<>();
        checkWishPart.add(" ");
        checkWishPart.add("    ORDER  PAID");
        checkWishPart.add(" ");
        checkWishPart.add("     Wish for YOU:");
        checkWishPart.add(" ");
        checkWishPart.add(Wish.getWish());
        return checkWishPart;
    }

    private static void frameForCheck(Order order, List<String> check, List<String> checkWishPart) {
        int lengthOFOrder = order.lengthOfLongestString(check);
        int lengthOfWish = order.lengthOfLongestString(checkWishPart);
        int finalLength = lengthOfWish;
        if(lengthOfWish < lengthOFOrder) finalLength = lengthOFOrder;
        order.borderFrame(finalLength);
        order.contentFrame(check, finalLength);
        order.borderFrame(finalLength);
        order.contentFrame(checkWishPart, finalLength);
        order.borderFrame(finalLength);
    }

    public static void getCheck(Order order) {
        check = order.CreatingOrder();
        List<String> checkWishPart = setCheck();
        frameForCheck(order, check, checkWishPart);
    }

}
