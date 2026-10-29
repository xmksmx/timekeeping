import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeIdTest {

    @Test
    void savesNumber() {
        var id = new EmployeeId("E001");

        assertEquals("E001", id.value());
    }

    @Test
    void removesSpacesAroundNumber() {
        var id = new EmployeeId("  E001  ");

        assertEquals("E001", id.value());
    }

    @Test
    void rejectsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EmployeeId(null)
        );
    }

    @Test
    void rejectsEmptyNumber() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EmployeeId("")
        );
    }

    @Test
    void rejectsWhitespaceNumber() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new EmployeeId(" \t ")
        );
    }

    @Test
    void equalNumbersFollowEqualityContract() {
        var first = new EmployeeId("E001");
        var second = new EmployeeId("E001");
        var third = new EmployeeId("E001");

        assertEquals(first, first);
        assertEquals(first, second);
        assertEquals(second, first);
        assertEquals(second, third);
        assertEquals(first, third);

        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(null, first);
        assertNotEquals("E001", first);
    }

    @Test
    void differentNumbersAreNotEqual() {
        var first = new EmployeeId("E001");
        var second = new EmployeeId("E002");

        assertNotEquals(first, second);
    }

    @Test
    void equalNumbersOccupyOnePlaceInSet() {
        var numbers = new HashSet<EmployeeId>();

        numbers.add(new EmployeeId("E001"));
        numbers.add(new EmployeeId("E001"));

        // одинаковый номер не должен становиться отдельной записью.
        assertEquals(1, numbers.size());
        assertTrue(numbers.contains(new EmployeeId("E001")));
    }
}