package org.example.p87377;

public class Main {
}

class Solution {
    public String[] solution(int[][] line) {
        String[] answer = {};
        return answer;
    }

    public long intersection(int[] line1, int[] line2) {
        // Ax + By + E = 0
        long A = line1[0];
        long B = line1[1];
        long E = line1[2];

        // Cx + Dy + F = 0
        long C = line2[0];
        long D = line2[1];
        long F = line2[2];

        // 아래와 같은 경우 평행해서 교점이 없다.
        if (A * D - B * C == 0) {
            return null;
        }

        double x = (double) (B * F - E * D) / (A * D - B * C);
        double y = (double) (E * C - A * F) / (A * D - B * C);

        // 문제에서 정수좌표만 구하라고 했다.
        if (x != (long) x) return null;
        // 문제에서 정수좌표만 구하라고 했다.
        if (y != (long) y) return null;

        return new long[]{(long) x, (long) y};
    }
}
