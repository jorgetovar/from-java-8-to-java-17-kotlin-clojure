package com.simplify.java17;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public static int[] twoSum(int[] ar, int n) {
        for (int i = 0; i < ar.length; i++) {
            for (int j = i + 1; j < ar.length; j++) {
                if (ar[i] + ar[j] == n) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};

    }

    public static int[] twoSum2(int[] ar, int n) {
        HashMap<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < ar.length; i++) {
            int delta = n - ar[i];
            if (numMap.containsKey(delta))
                return new int[]{i, numMap.get(delta)};

            numMap.put(ar[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] ar = {1, 2, 3, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6, 4};
        Arrays.stream(twoSum(ar, 7))
                .forEach(System.out::println);
        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;
        System.out.println("TimeElapsed: " + timeElapsed);

        System.out.println("Optimized");
        start = System.currentTimeMillis();

        Arrays.stream(twoSum2(ar, 7))
                .forEach(System.out::println);
        finish = System.currentTimeMillis();

        timeElapsed = finish - start;
        System.out.println("TimeElapsed: " + timeElapsed);

    }

}
