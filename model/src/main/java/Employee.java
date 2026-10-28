/**
 * сотрудник с почасовой оплатой.
 */
public final class Employee extends Person {

    private final long hourlyRateKopecks;

    /**
     * создаёт сотрудника с положительной ставкой.
     *
     * @param id табельный номер
     * @param name имя сотрудника
     * @param rate ставка в копейках за час
     */
    public Employee(EmployeeId id, String name, long rate) {
        super(id, name);

        if (rate <= 0) {
            throw new IllegalArgumentException(
                    "почасовая ставка должна быть положительной"
            );
        }

        this.hourlyRateKopecks = rate;
    }

    /**
     * возвращает почасовую ставку.
     *
     * @return ставка в копейках за час
     */
    public long hourlyRateKopecks() {
        return hourlyRateKopecks;
    }

    /**
     * формирует описание сотрудника.
     *
     * @return имя, номер и почасовая ставка
     */
    @Override
    public String description() {
        return name() + " [" + id().value() + "], ставка: "
                + hourlyRateKopecks + " коп/ч";
    }
}