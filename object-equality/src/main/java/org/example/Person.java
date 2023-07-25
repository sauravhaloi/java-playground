package org.example;

import java.util.Objects;

public class Person {
    private final int age;
    private final String name;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Person person = (Person) obj;

        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = name.hashCode();
        result = prime * result + age;
        return result;
    }
}
