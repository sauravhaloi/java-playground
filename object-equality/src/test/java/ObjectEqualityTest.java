import org.example.Employee;
import org.example.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectEqualityTest {

    private final Person person11 = new Person("Alice", 30);
    private final Person person12 = new Person("Alice", 30);
    private final Person person21 = new Person("Bob", 35);
    private final Person person22 = new Person("Bob", 35);

    private final Employee employee11 = new Employee("Alice", 30, "Manager");
    private final Employee employee12 = new Employee("Alice", 30, "Manager");

    private final Employee employee21 = new Employee("Bob", 35, "Developer");
    private final Employee employee22 = new Employee("Bob", 35, "Developer");


    @Test
    public void testPersonHashCode() {
        assertEquals(person11.hashCode(), person11.hashCode());
        assertEquals(person12.hashCode(), person12.hashCode());

        assertEquals(person21.hashCode(), person22.hashCode());
        assertEquals(person22.hashCode(), person21.hashCode());

        assertNotEquals(person11.hashCode(), person21.hashCode());
        assertNotEquals(person12.hashCode(), person22.hashCode());
    }

    @Test
    public void testEmployeeHashCode() {
        assertEquals(employee11.hashCode(), employee11.hashCode());
        assertEquals(employee12.hashCode(), employee12.hashCode());

        assertEquals(employee21.hashCode(), employee22.hashCode());
        assertEquals(employee22.hashCode(), employee21.hashCode());

        assertNotEquals(employee11.hashCode(), employee21.hashCode());
        assertNotEquals(employee12.hashCode(), employee22.hashCode());
    }
    @Test
    public void testPersonEquality() {
        assertTrue(person11.equals(person12));
        assertFalse(person11.equals(person21));
        assertTrue(person21.equals(person22));
        assertFalse(person12.equals(person22));
    }
    @Test
    public void testEmployeeEquality() {
        assertTrue(employee11.equals(employee12));
        assertFalse(employee11.equals(employee21));
        assertTrue(employee21.equals(employee22));
        assertFalse(employee12.equals(employee22));
    }

    @Test
    public void testPersonAndEmployeeEquality() {
        assertNotEquals(person11.hashCode(), employee11.hashCode());
        assertNotEquals(person12.hashCode(), employee21.hashCode());
        assertNotEquals(person12.hashCode(), employee12.hashCode());
        assertNotEquals(person22.hashCode(), employee22.hashCode());
    }
}
