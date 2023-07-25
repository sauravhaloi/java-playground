package org.example;

public record Tuple<T1, T2>(T1 item1, T2 item2) {

    @Override
    public String toString() {
        return "(" + item1 + ", " + item2 + ")";
    }
}


