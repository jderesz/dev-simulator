package com.company;

public class Client {

    public enum Type {
        EASY,
        MEDIUM,
        HARD
    }

    private final String name;
    private final Type type;

    public Client(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
