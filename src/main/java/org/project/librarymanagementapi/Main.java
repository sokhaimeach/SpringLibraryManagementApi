package org.project.librarymanagementapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Character, Integer> alphabets = new HashMap<>();

        for (char c = 'a'; c <= 'z'; c++) {
            alphabets.put(c, 26 - alphabets.getOrDefault(c, 0));
        }

        String input = sc.nextLine();
        int sum = 0;
        for (char c :  input.toCharArray()) {
            int value = alphabets.get(c) * alphabets.get(c);
        }
    }
}
