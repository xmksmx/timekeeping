import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.EnumSet;
import java.util.Set;

/**
 * определяет ночное и праздничное время по календарю.
 */
public final class CalendarTimeClassifier implements TimeClassifier {

    private static final LocalTime NIGHT_START = LocalTime.of(22, 0);
    private static final LocalTime NIGHT_END = LocalTime.of(6, 0);

    private final Set<LocalDate> holidays;

    /**
     * сохраняет неизменяемую копию праздничных дат.
     *
     * @param holidays праздничные даты, допускается пустой набор
     */
    public CalendarTimeClassifier(Set<LocalDate> holidays) {
        if (holidays == null) {
            throw new IllegalArgumentException(
                    "набор праздничных дат не задан"
            );
        }

        for (var date : holidays) {
            if (date == null) {
                throw new IllegalArgumentException(
                        "праздничная дата не должна быть null"
                );
            }
        }

        this.holidays = Set.copyOf(holidays);
    }

    /**
     * определяет типы времени по местной дате и времени.
     *
     * @param moment момент в часовом поясе смены
     * @return неизменяемый набор определённых типов времени
     */
    @Override
    public Set<TimeType> classify(ZonedDateTime moment) {
        if (moment == null) {
            throw new IllegalArgumentException(
                    "момент времени не задан"
            );
        }

        var types = EnumSet.noneOf(TimeType.class);
        var time = moment.toLocalTime();

        // ночной интервал проходит через полночь.
        if (!time.isBefore(NIGHT_START) || time.isBefore(NIGHT_END)) {
            types.add(TimeType.NIGHT);
        }

        if (holidays.contains(moment.toLocalDate())) {
            types.add(TimeType.HOLIDAY);
        }

        if (types.isEmpty()) {
            types.add(TimeType.REGULAR);
        }

        return Set.copyOf(types);
    }
}