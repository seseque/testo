package com.github.testo.testsuit;


import com.github.testo.annotations.After;
import com.github.testo.annotations.Before;
import com.github.testo.annotations.Ignore;
import com.github.testo.annotations.Test;

class TestUnit3 {

    private static int number;

    @Test
    public static void test() {
        System.out.println("this is test"+ number);
    }

    @Test
    @Ignore
    public static void ignoreTest() {
        System.out.println("this is test" + number +  "! should be ignored");
    }

    @Before
    public static void beforeTest() {
        number = 3;
        System.out.println("before test" + number);
    }

    @After
    public static void afterTest() {
        System.out.println("After test" + number);
    }

}