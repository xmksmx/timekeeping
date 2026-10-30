import java.time.ZonedDateTime;
import java.util.Set;

/**
 * определяет типы рабочего времени для указанного момента.
 */
public interface TimeClassifier {

    /**
     * определяет типы времени по дате и местному времени.
     *
     * @param moment момент в часовом поясе смены
     * @return непустой неизменяемый набор типов времени
     */
    Set<TimeType> classify(ZonedDateTime moment);
}