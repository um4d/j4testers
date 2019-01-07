package ru.stqa.pft.sandbox;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        double dist = Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
        return dist;
    }


}
