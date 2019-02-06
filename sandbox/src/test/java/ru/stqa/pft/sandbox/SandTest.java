package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SandTest {


    @Test
    public static void test1() {


        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(1.0, 1.0);

        Assert.assertEquals(p1, p2);
//        System.out.println(p1 == p2);

    }



}
