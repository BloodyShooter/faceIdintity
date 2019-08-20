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
}