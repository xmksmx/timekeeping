import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * рабочая смена в одном часовом поясе.
 * начало включается в смену, конец не включается.
 *
 * @param start начало смены
 * @param end конец смены
 */
public record Shift(ZonedDateTime start, ZonedDateTime end) {

    /**
     * проверяет границы смены.
     *
     * @param start начало смены
     * @param end конец смены
     */
    public Shift {
        if (start == null || end == null) {
            throw new IllegalArgumentException(
                    "начало и конец смены должны быть заданы"
            );
        }

        if (!start.getZone().equals(end.getZone())) {
            throw new IllegalArgumentException(
                    "начало и конец смены должны иметь один часовой пояс"
            );
        }

        if (!end.isAfter(start)) {
            throw new IllegalArgumentException(
                    "конец смены должен быть позже начала"
            );
        }
    }

    /**
     * возвращает длительность смены.
     *
     * @return фактически прошедшее время
     */
    public Duration duration() {
        // при переводе часов локальное время может пропускаться или повторяться.
        return Duration.between(start.toInstant(), end.toInstant());
    }
}