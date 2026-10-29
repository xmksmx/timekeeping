import java.time.LocalDate;
import java.time.ZoneId;

void main() {
    var first = new EmployeeId("E001");
    var second = new EmployeeId("E001");

    System.out.println("номер: " + first.value());
    System.out.println("номера совпадают: " + first.equals(second));
    System.out.println("хеши совпадают: " + (first.hashCode() == second.hashCode()));

    Person employee = new Employee(
            new EmployeeId("E001"),
            "Анна",
            50_000
    );

    System.out.println(employee.description());

    var zone = ZoneId.of("Europe/Moscow");
    var date = LocalDate.of(2026, 10, 29);

    var day = new Shift(
            date.atTime(9, 0).atZone(zone),
            date.atTime(17, 0).atZone(zone)
    );

    var night = new Shift(
            date.atTime(21, 0).atZone(zone),
            date.plusDays(1).atTime(7, 0).atZone(zone)
    );

    System.out.println("дневная смена: " + day.duration());
    System.out.println("смена через полночь: " + night.duration());

    System.out.println();
    System.out.println("типы рабочего времени:");

    for (var type : TimeType.values()) {
        System.out.println(
                type.label() + ": коэффициент " + type.coefficient()
        );
    }

    var segment = new TimeSegment(
            new Shift(
                    date.atTime(22, 0).atZone(zone),
                    date.plusDays(1).atTime(2, 0).atZone(zone)
            ),
            Set.of(TimeType.NIGHT, TimeType.HOLIDAY)
    );

    System.out.println();
    System.out.println("ночной праздничный отрезок:");
    System.out.println("длительность: " + segment.duration());

    for (var type : segment.types()) {
        System.out.println("тип времени: " + type.label());
    }
}