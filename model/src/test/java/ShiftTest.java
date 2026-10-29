import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ShiftTest {

    private final ZonedDateTime start = ZonedDateTime.of(
            2026, 10, 29, 9, 0, 0, 0,
            ZoneId.of("Europe/Moscow")
    );

    @Test
    void calculatesDuration() {
        var shift = new Shift(start, start.plusHours(8));

        assertEquals(Duration.ofHours(8), shift.duration());
    }

    @Test
    void calculatesDurationAcrossMidnight() {
        var from = start.withHour(21);
        var to = start.plusDays(1).withHour(7);
        var shift = new Shift(from, to);

        assertEquals(Duration.ofHours(10), shift.duration());
    }

    @Test
    void springClockChangeMakesShiftShorter() {
        var zone = ZoneId.of("Europe/Berlin");
        var date = LocalDate.of(2024, 3, 31);

        var shift = new Shift(
                date.atStartOfDay(zone),
                date.atTime(6, 0).atZone(zone)
        );

        // часы переводятся вперёд, поэтому фактически проходит пять часов.
        assertEquals(Duration.ofHours(5), shift.duration());
    }

    @Test
    void autumnClockChangeMakesShiftLonger() {
        var zone = ZoneId.of("Europe/Berlin");
        var date = LocalDate.of(2024, 10, 27);

        var shift = new Shift(
                date.atStartOfDay(zone),
                date.atTime(6, 0).atZone(zone)
        );

        // один час повторяется, поэтому фактически проходит семь часов.
        assertEquals(Duration.ofHours(7), shift.duration());
    }

    @Test
    void rejectsEqualStartAndEnd() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shift(start, start)
        );
    }

    @Test
    void rejectsEndBeforeStart() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shift(start, start.minusHours(1))
        );
    }

    @Test
    void rejectsNullStart() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shift(null, start)
        );
    }

    @Test
    void rejectsNullEnd() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Shift(start, null)
        );
    }

    @Test
    void rejectsDifferentTimeZones() {
        var end = start.plusHours(8)
                .withZoneSameInstant(ZoneId.of("UTC"));

        assertThrows(
                IllegalArgumentException.class,
                () -> new Shift(start, end)
        );
    }
}