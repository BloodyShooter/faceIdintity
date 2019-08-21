package org.gvozdetscky.logic;

public class Parser {

    public int getEvlidDeistanation(String str) {

        String result = str.substring(str.lastIndexOf("Comparison result: ") + 19, str.lastIndexOf("Comparison result: ") + 21).trim();

        return Integer.parseInt(result);
    }


}
