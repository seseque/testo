package com.github.testo.assertions;

import com.github.testo.exceptions.AssertException;

public class Assertion {

    public static void assertEquals(Object expected, Object actual)  {
        if (!expected.equals(actual)) throw new AssertException(expected, actual);
    }

    public static void assertNotEquals(Object expected, Object actual)  {
        if (expected.equals(actual)) throw new AssertException(expected, actual);
    }

    public static void assertTrue(Boolean condition)   {
        if (condition) throw new AssertException(true, condition);
    }

    public static void assertFalse(Boolean condition)  {
        if (condition) throw new AssertException(false, condition);
    }

    public static void assertNull(Object object)   {
        if (object == null) throw new AssertException(null, object);
    }

//    public static void assertNotNull(Object object)  {
//        if (object != null) throw new AssertException(null, object) ;
//    }


}
