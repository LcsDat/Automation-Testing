package Java_Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayExercise {
    public static void main(String[] args) {
        System.out.println("Exercise 1: ");
        Exercise1_Build_Array_from_Permutation();

        System.out.println("\nExercise 2: ");
        Exercise2_Concatenation_of_Array();

        System.out.println("\nExercise 3: ");
        Exercise3_Running_Sum_of_1d_Array();

        System.out.println("\nExercise 4: ");
        Exercise4_Richest_Customer_Wealth();

        System.out.println("\nExercise 5: ");
        Exercise5_Shuffle_the_Array();

        System.out.println("\nExercise 6: ");
        Exercise6_Greatest_Number_of_Candies();

        System.out.println("\nExercise 7: ");
        Exercise7_Number_of_Good_Pairs();

        System.out.println("\nExercise 8: ");
        Exercise8_Numbers_Are_Smaller_Than_Current_Number();

        System.out.println("\nExercise 9: ");
        Exercise9_Create_Target_Array();

        System.out.println("\nExercise 10: ");
        Exercise10_Check_Sentence_Is_Pangram();

    }

    public static void Exercise1_Build_Array_from_Permutation() {
        int[] nums = {1, 6, 0, 3, 4, 5, 2};
        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }

        //in mang
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    public static void Exercise2_Concatenation_of_Array() {

        int[] nums = {1, 6, 7, 3, 4, 5, 2, 0, 8, 9};
        int numLength = nums.length;
        int[] ans = new int[nums.length * 2];

        for (int i = 0; i < numLength; i++) {

            ans[i] = nums[i];
            ans[i + numLength] = nums[i];
        }
        //in mang
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }


    public static void Exercise3_Running_Sum_of_1d_Array() {

        int[] nums = {1, 6, 7, 3, 4, 5, 2, 0, 8, 9};
        int[] ans = new int[nums.length];
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            ans[i] = nums[i] + sum;
            sum += nums[i];
        }
        //in mang
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    public static void Exercise4_Richest_Customer_Wealth() {
        int[][] accounts = {{1, 6, 7, 3, 4}, {5, 2, 0, 8, 9}, {1, 2, 3, 4, 5}};
        int sumRow;
        int maxWealth = 0;
        for (int row = 0; row < accounts.length; row++) {
            sumRow = 0;
            for (int col = 0; col < accounts[row].length; col++) {
                sumRow += accounts[row][col];
            }
            if (sumRow > maxWealth) {
                maxWealth = sumRow;
            }

        }
        System.out.println("Max = " + maxWealth);
    }


    public static void Exercise5_Shuffle_the_Array() {
        int[] nums = {1, 6, 7, 3, 4, 5, 2, 0, 8, 9};
        int[] ans = new int[nums.length];
        int n = nums.length / 2;

        for (int i = 0; i < n; i++) {
            ans[2 * i] = nums[i];
            ans[2 * i + 1] = nums[i + n];
        }

        for (int j = 0; j < ans.length; j++) {
            System.out.print(ans[j] + " ");

        }
    }

    public static void Exercise6_Greatest_Number_of_Candies() {

        int[] candies = {6, 7, 2, 9, 5, 1, 3, 8, 2};
        int extraCandies = 6;
        boolean[] results = new boolean[candies.length];
        int maxCandie = candies[0];

        for (int i = 0; i < candies.length; i++) {
            if (candies[i] > maxCandie) {
                maxCandie = candies[i];
            }
        }

        for (int j = 0; j < candies.length; j++) {
            results[j] = candies[j] + extraCandies >= maxCandie;
        }

        for (boolean b : results) {
            System.out.print(b + " ");
        }
    }

    public static void Exercise7_Number_of_Good_Pairs() {
        int[] nums = {1, 2, 3, 1, 1, 3, 3, 2, 1, 7, 7};
        int numOfGoodPairs = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    numOfGoodPairs++;

            }
        }
        System.out.print("Number of Good Pairs is " + numOfGoodPairs);

    }

    public static void Exercise8_Numbers_Are_Smaller_Than_Current_Number() {
        int[] nums = {8, 1, 2, 2, 3, 9, 7, 4};
        int[] results = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }

            }
            results[i] = count;
        }
        for (int b : results) {
            System.out.print(b + " ");
        }

    }

    public static void Exercise9_Create_Target_Array() {
        int[] nums = {0, 1, 2, 3, 4, 5};
        int[] index = {0, 1, 2, 2, 1, 3};

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];     // số cần chèn
            int position = index[i]; // vị trí cần chèn

            //dọc ham này void add(int index, E element);
            list.add(position, value); // chèn vào list
        }

        System.out.println(list);


    }

    public static void Exercise10_Check_Sentence_Is_Pangram() {
        String sentence = "tequickbrowfoxjumpsoverthelazydogjnhgfh";
        boolean[] seen = new boolean[26];
        boolean isPangram = true;

        for (char c : sentence.toCharArray()) {
            seen[c - 'a'] = true;
        }

        for (boolean b : seen) {
            if (!b) {
                isPangram = false;
                break;
            }
        }
        System.out.println(isPangram);

    }

}






