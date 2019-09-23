package org.gvozdetscky.logic.test;

import org.junit.Test;

public class ExceptionTest {

    @Test
    public void exception3() throws Exception {
        try {
            System.out.println('a');
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println("b");
            throw new Exception();
        } catch (Exception e) {
            System.out.println('c');
        }
    }

    @Test
    public void exceptionOfMine() throws Exception {
        try {
            System.out.println('a');
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println("b");
        } catch (Exception e) {
            System.out.println('c');
        } finally {
            System.out.println("d");
        }
        System.out.println("e");
    }

    @Test
    public void exception5() throws Exception {
        int i = divide(11, 1);
        System.out.println(i);
    }

    private static int divide(int a, int b) {
        try {
            return a / b;
        } catch (Exception e) {
            return 0;
        } finally {
            return 10;
        }
    }
}
