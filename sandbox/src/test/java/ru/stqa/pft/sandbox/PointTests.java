package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PointTests {


    @Test
    public void test1() {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);

        Assert.assertEquals(p1.distance(p2), 1.0);

    }

    @Test
    public void test2() {

        Point p1 = new Point(1, 0);
        Point p2 = new Point(-100, 0);

        Assert.assertEquals(p1.distance(p2), 101.0);

    }

    @Test
    public void test3() {

        Point p1 = new Point(99.0, -331);
        Point p2 = new Point(99.0, 12);

        Assert.assertEquals(p1.distance(p2), 343.0);

    }

    @Test
    public void test4() {

        Point p1 = new Point(0.2, -12.003);
        Point p2 = new Point(-1, 1);

        Assert.assertEquals(p1.distance(p2),13.058254439242637);

    }

}
