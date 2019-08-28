package org.gvozdetscky.logic.detectionapi;

import org.junit.Test;

import static org.junit.Assert.*;

public class DetectionTest {

    Detection detection = new Detection();

    @Test
    public void faceIdintityTest() {

        detection.faceIdintity("C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\1.png",
                "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\2.png");

    }

    @Test
    public void faceIdintityUrlTest() {

        detection.faceIdintityUrl("C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\1.png",
                "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\2.png");

    }

    @Test
    public void revers() {
        String str = "Hello World!";

        char[] chars = str.toCharArray();

        for(int i = 0, j = str.length() - 1; i < j; i++, j--) {
            char c1 = chars[i];
            char c2 = chars[j];

            chars[j] = c1;
            chars[i] = c2;
        }

        String newStr = chars.toString();

        System.out.println(newStr);
    }

    @Test
    public void factorial() {
        System.out.println(fact(5));
    }

    int fact(int x) {
        if (x == 1) return 1;

        return x * fact(x - 1);
    }
}