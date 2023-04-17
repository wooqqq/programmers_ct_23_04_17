package org.example.p87377;

import java.util.HashSet;
import java.util.Set;

public class Main {
}

class Solution {
    public String[] solution(int[][] line) {
        String[] answer = {};
        return answer;
    }

    public Point intersection(int[] line1, int[] line2) {
        // Ax + By + E = 0
        double A = line1[0];
        double B = line1[1];
        double E = line1[2];

        // Cx + Dy + F = 0
        double C = line2[0];
        double D = line2[1];
        double F = line2[2];

        double divisor = A * D - B * C;

        // 아래와 같은 경우 평행해서 교점이 없다.
        if (divisor == 0) return null;

        double x = (B * F - E * D) / divisor;
        double y = (E * C - A * F) / divisor;

        // 문제에서 정수좌표만 구하라고 했다.
        if (x != (long) x) return null;
        // 문제에서 정수좌표만 구하라고 했다.
        if (y != (long) y) return null;

        return Point.of(x, y);
    }

    public Set<Point> intersections(int[][] line) {
        Set<Point> points = new HashSet<>();

        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                int[] line1 = line[i];
                int[] line2 = line[j];

                Point point = intersection(line1, line2);

                if (point != null) points.add(point);
            }
        }

        return points;
    }

    public Point getMinPoint(Set<Point> points) {
        long x = Long.MAX_VALUE;
        long y = Long.MAX_VALUE;

        for (Point point : points) {
            x = Math.min(x, point.x);
            y = Math.min(y, point.y);
        }

        return Point.of(x, y);
    }

    public Point getMaxPoint(Set<Point> points) {
        long x = Long.MIN_VALUE;
        long y = Long.MIN_VALUE;

        for (Point point : points) {
            x = Math.max(x, point.x);
            y = Math.max(y, point.y);
        }

        return Point.of(x, y);
    }
}

class Point {
    public final long x;
    public final long y;

    private Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    // 정수로 세팅하는 용도
    public static Point of(long x, long y) {
        return new Point(x, y);
    }

    // 실수로 세팅하는 용도
    public static Point of(double x, double y) {
        return of((long) x, (long) y);
    }

    // 객체 비교, 가독성 좋음
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;

        if (x != point.x) return false;
        return y == point.y;
    }

    // 객체 비교, 객체로부터 고유키를 뽑아낸다. (int), 대량비교 좋음, 가독성 나쁨
    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                "}";
    }
}