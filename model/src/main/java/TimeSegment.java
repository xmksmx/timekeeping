import java.time.Duration;
import java.util.Set;

/**
 * отрезок смены с одним или несколькими типами времени.
 *
 * @param interval начало и конец отрезка
 * @param types типы рабочего времени
 */
public record TimeSegment(Shift interval, Set<TimeType> types) {

    /**
     * проверяет отрезок и сохраняет неизменяемую копию набора типов.
     *
     * @param interval начало и конец отрезка
     * @param types непустой набор типов рабочего времени
     */
    public TimeSegment {
        if (interval == null) {
            throw new IllegalArgumentException(
                    "границы отрезка не заданы"
            );
        }

        if (types == null || types.isEmpty()) {
            throw new IllegalArgumentException(
                    "нужно указать хотя бы один тип времени"
            );
        }

        for (var type : types) {
            if (type == null) {
                throw new IllegalArgumentException(
                        "тип времени не должен быть null"
                );
            }
        }

        // внешний набор может измениться после создания отрезка.
        types = Set.copyOf(types);

        if (types.contains(TimeType.REGULAR) && types.size() > 1) {
            throw new IllegalArgumentException(
                    "обычное время нельзя совмещать с другими типами"
            );
        }
    }

    /**
     * возвращает фактическую длительность отрезка.
     *
     * @return длительность отрезка
     */
    public Duration duration() {
        return interval.duration();
    }
}