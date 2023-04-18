package org.example.p87377;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
}

class Solution {
    public String[] solution(int[][] line) {
        // 교점들 구하고
        Set<Point> points = intersections(line);
        // 매트릭스로 옮긴다.
        char[][] matrix = transformToMatrix(points);

        return drawOnCoordinate(matrix);
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

    public char[][] emptyMatrix(Set<Point> points) {
        Point minPoint = getMinPoint(points);
        Point maxPoint = getMaxPoint(points);

        int width = (int) (maxPoint.x - minPoint.x + 1);
        int height = (int) (maxPoint.y - minPoint.y + 1);

        char[][] matrix = new char[height][width];

        Arrays.stream(matrix).forEach(row -> Arrays.fill(row, '.'));

        return matrix;
    }

    public Set<Point> positivePoints(Set<Point> points) {
        Point minPoint = getMinPoint(points);

        return points.stream()
                .map(p -> Point.of(p.x - minPoint.x, p.y - minPoint.y))
                .collect(Collectors.toSet());
    }

    public char[][] transformToMatrix(Set<Point> points) {
        char[][] matrix = emptyMatrix(points);
        points = positivePoints(points);

        points.forEach(p -> matrix[(int) p.y][(int) p.x] = '*');

        return matrix;
    }

    public String[] drawOnCoordinate(char[][] matrix) {
        return Ut.revRange(0, matrix.length)
                .boxed()
                .map(i -> matrix[i])
                .map(row -> new String(row))
                .toArray(String[]::new);
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
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

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

class Points {
    private final Set<Point> data;

    private Points(Set<Point> data) {
        this.data = data;
    }

    // Point... 는 Point[] 와 같은 뜻
    // Point... 의 특수기능 : 가변인자
    // Points.of(arg1);
    // Points.of(arg1, arg2);
    // Points.of(arg1, arg2, agr3);
    public static Points of(Point... pointArray) {
        // 입력받은 배열을 HashSet 형태로 하다.
        // Collectors.toSet() 를 사용하지 않는 이유 : 우리는 mutable 한것을 원한다.
        // mutable : 수정가능
        // immutable : 수정불가능(add, remove 등이 안됨)
        return new Points(Arrays.stream(pointArray)
                .collect(Collectors.toCollection(HashSet::new)));
    }
}

class Ut {
    static IntStream revRange(int from, int to) {
        return IntStream.range(from, to)
                .map(i -> to - i + from - 1);
    }
}