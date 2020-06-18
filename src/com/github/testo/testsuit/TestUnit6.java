package com.github.testo.testsuit;


import com.github.testo.annotations.After;
import com.github.testo.annotations.Before;
import com.github.testo.annotations.Test;

class TestUnit6 {

    private static int number;

    @Test
    public static void test() {
        System.out.println("this is test"+ number);
    }

    @Before
    public static void beforeTest() {
        number = 6;
        System.out.println("before test" + number);
    }

    @After
    public static void afterTest() {
        System.out.println("After test" + number);
    }

}