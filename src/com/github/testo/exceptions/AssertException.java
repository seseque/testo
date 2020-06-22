package com.github.testo.exceptions;

public class AssertException extends RuntimeException {
    public Object expected;
    public Object actual;

    public AssertException(Object expected, Object actual) {
        this.expected = expected;
        this.actual = actual;
    }
}
