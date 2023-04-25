package org.example.p42860;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
}

class Solution {
    public static boolean isDebug = false;

    public int solution(String name) {
        return nameCost(name) + moveCost(name);
    }

    private int nameCost(String name) {
        int nameCost = 0;

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            int diff = Math.min(c - 'A', 'Z' - c + 1);

            nameCost += diff;
        }

        return nameCost;
    }

    private int moveCost(String name) {
        int moveCost = 0;

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            int diff = c - 'A';

            if (diff > 0) {
                moveCost = i;
            }
        }

        return moveCost;
    }

    public int moveCostBy1(String name) {
        int move = 0;

        for ( int i = 0; i < name.length(); i++ ) {
            if ( name.charAt(i) != 'A' ) {
                move = i;
            }
        }

        return move;
    }

    public int moveCostBy2(String name) {
        int move = 0;

        for (int i = name.length() - 1; i >= 1; i--) {
            if (name.charAt(i) != 'A') {
                move = name.length() - i;
            }
        }

        return move;
    }

    public int moveCostBy3(String name) {
        Ut.LongestCharContinuumIndexAndLength indexAndLength = Ut.getLongestCharContinuumIndexAndLength(name, 'A');

        if (indexAndLength.index == -1) return moveCostBy1(name);

        // 사막 뒤의 문자 개수
        int backCharsCount = name.length() - (indexAndLength.index + indexAndLength.length);
        int moveBack = backCharsCount * 2;

        // 사막 앞의 문자 개수
        int frontCharsCount = indexAndLength.index - 1;
        int moveFront = frontCharsCount;

        return moveBack + moveFront;
    }
}

class Ut {
    public static class LongestCharContinuumIndexAndLength {
        public int index;
        public int length;

        public LongestCharContinuumIndexAndLength(int index, int length) {
            this.index = index;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LongestCharContinuumIndexAndLength that)) return false;

            if (index != that.index) return false;
            return length == that.length;
        }

        @Override
        public String toString() {
            return "LongestCharContinuumIndexAndLength{" +
                    "index=" + index +
                    ", length=" + length +
                    '}';
        }
    }

    public static LongestCharContinuumIndexAndLength getLongestCharContinuumIndexAndLength(String str, char c) {
        String regex = c + "+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        int maxLength = 0;
        int maxIndex = -1;

        while (matcher.find()) {
            int startIndex = matcher.start();
            int length = matcher.end() - startIndex;

            if (length > maxLength) {
                maxLength = length;
                maxIndex = startIndex;
            }
        }

        return new LongestCharContinuumIndexAndLength(maxIndex, maxLength);
    }
}