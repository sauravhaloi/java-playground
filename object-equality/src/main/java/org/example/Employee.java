package org.example;

public class Employee extends Person {
    private final String role;

    public Employee(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Employee employee = (Employee) obj;
        return role.equals(employee.role);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}