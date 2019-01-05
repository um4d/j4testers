package ru.stqa.pft.sandbox;

public class distanceMain {

    public static void main(String[] args) {
        Point p1 = new Point(-1,1);
        Point p2 = new Point(2,2);
        Point p3 = new Point(0.2, -12.003);

        double dist1 = Point.distance(p1, p2);
        double dist2 = Point.distance(p2, p3);
        double dist3 = Point.distance(p3, p1);

        double perimeter = dist1 + dist2 + dist3;

        System.out.println(String.format("Расстояние 1-2 : %.3f", dist1));
        System.out.println(String.format("Расстояние 2-3 : %.3f", dist2));
        System.out.println(String.format("Расстояние 3-1 : %.3f", dist3));

        System.out.println(String.format("Периметр : %.3f", perimeter));

    }

}
