package com.simplify.java8;
// HI IGOR WHAT I DID WAS TRYING TO REMOVE THE EXTRA FOR LOOPS AS I TOUGHT THEY WERE INNECESARY: // FOR THAT I ASSIGN VARIABLES IMMEDIANTELY AND CREATE DEFAULT VALUESpackage com.simplify.java8;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class EpamReshuffled {
    public int[] reshuffle(int[] arr, int target) {

        if (arr.length == 1) {
            return arr;
        }
        int[] reshuffled = new int[arr.length];
        // Remove temporal List
        int count = 0;
        for (int i = 0; i <= arr.length - 1; i++) {
            // assign a defect value
            reshuffled[i] = target;
            int value = arr[i];
            if (value != target) {
                reshuffled[count] = value;
                count++;
            }
        }


        return reshuffled;
    }

    public int[] reshuffleOld(int[] arr, int target) {

        if (arr.length == 1) {
            return arr;
        }
        int[] reshuffled = new int[arr.length];
        List<Integer> diffs = new ArrayList<>();
        for (int i = 0; i <= arr.length - 1; i++) {
            int value = arr[i];
            if (value != target) {
                diffs.add(value);
            }
        }

        for (int i = 0; i < diffs.size(); i++) {
            reshuffled[i] = diffs.get(i);
        }

        for (int i = diffs.size(); i <= arr.length - 1; i++) {
            reshuffled[i] = target;
        }


        return reshuffled;
    }

    public int solution(int A, int B) {
        int mult = A * B;
        System.out.println();
        String s = Integer.toBinaryString(mult);
        int result = (int) s.chars().filter(e -> e == '1').count();
        return result;
    }

    public String solution(String S) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < S.length(); i++) {
            Integer e = Integer.valueOf(String.valueOf(S.charAt(i)));
            set.add(e);
        }

        return String.valueOf(Collections.max(set));
    }

    public static void expand(String str, int low, int high, Set<Integer> set) {
        while (low >= 0 && high < str.length()
                && str.charAt(low) == str.charAt(high)) {
            set.add(Integer.valueOf(str.substring(low, high + 1)));
            low--;
            high++;
        }
    }


    @Test
    void str() {
        assertThat(solution("12345"), is(equalTo("5")));
        assertThat(solution("39878"), is(equalTo("898")));
        assertThat(solution("00900"), is(equalTo("9")));
    }

    @Test
    void binary() {
        assertThat(solution(3, 7), is(equalTo(3)));
    }

    @Test
    void basic() {
        assertThat(reshuffle(new int[]{1}, 1), is(equalTo(new int[]{1})));
    }

    @Test
    void example1() {
        assertThat(reshuffle(new int[]{1, 2, 1, 3, 1}, 1), is(equalTo(new int[]{2, 3, 1, 1, 1})));
    }

    @Test
    void example2() {
        assertThat(reshuffle(new int[]{1, 2, 1, 3, 1}, 8), is(equalTo(new int[]{1, 2, 1, 3, 1})));
    }

    @Test
    void example3() {
        assertThat(reshuffle(new int[]{1, 2, 1, 3, 1}, 2), is(equalTo(new int[]{1, 1, 3, 1, 2})));
    }

}