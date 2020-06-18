package com.github.testo.testsuit;


import com.github.testo.annotations.After;
import com.github.testo.annotations.Before;
import com.github.testo.annotations.Test;

class TestUnit {

    @Test
    public static void test() {
        System.out.println("this is test");
    }

    @Before
    public static void beforeTest() {
        System.out.println("before test");
    }

    @After
    public static void afterTest() {
        System.out.println("After test");
    }

}