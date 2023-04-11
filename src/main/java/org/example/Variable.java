package org.example;

import java.util.Map;

class Variable<T> extends Decision<T> {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public T evaluate(Map<T, Boolean> questions) {
        return (T) questions.get(name);
    }
}
