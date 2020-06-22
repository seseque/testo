package com.github.testo.testsuit;


import com.github.testo.annotations.After;
import com.github.testo.annotations.Before;
import com.github.testo.annotations.Test;
import com.github.testo.assertions.Assertion;

class TestUnit2 {

    private static int number;

    @Test
    public static void test() {
        Assertion.assertEquals("+", "-");
        System.out.println("this is test" + number);
    }

    @Before
    public static void beforeTest() {
        number = 2;
        System.out.println("before test" + number);
    }

    @After
    public static void afterTest() {
        System.out.println("After test" + number);
    }

}