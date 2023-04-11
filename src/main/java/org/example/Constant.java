package org.example;

import java.util.Map;

class Constant<T> extends Decision<T> {
    private T value;

    public Constant(T value) {
        this.value = value;
    }

    @Override
    public T evaluate(Map<T, Boolean> questions) {
        return null;
    }
}