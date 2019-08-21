package org.gvozdetscky.logic;

import org.gvozdetscky.logic.detectionapi.Detection;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    Parser parser = new Parser();
    Detection detection = new Detection();

    @Test
    public void getEvlidDeistanationTest() {

        String str = detection.faceIdintity("C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\1.png",
                "C:\\Users\\Yagorka\\PycharmProjects\\FindFace\\2.png");

        int evlidDeistanation = parser.getEvlidDeistanation(str);

        System.out.println("Результат теста: " + evlidDeistanation);

    }
}