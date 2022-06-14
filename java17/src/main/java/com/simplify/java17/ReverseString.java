package com.simplify.java17;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class ReverseString {

    public static String reverse(String str) {

        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static String reverseWithBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String reverseStack(char[] s) {
        int len = s.length;

        if (len == 0)
            return null;

        for (int i = 0; i < (len / 2); i++) {
            char l = s[i];
            s[i] = s[len - i - 1];
            s[len - i - 1] = l;
        }
        return new String(s);
    }


    public static void main(String[] args) {
        String myName = "george";
        String softwareArchitect = "software-architect";

        System.out.println("reversed: " + reverseStack(myName.toCharArray()));
        System.out.println("reversed: " + reverseStack(softwareArchitect.toCharArray()));

    }

}
