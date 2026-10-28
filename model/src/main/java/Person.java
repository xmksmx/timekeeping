/**
 * общие данные человека.
 */
public abstract sealed class Person permits Employee {

    private final EmployeeId id;
    private final String name;

    /**
     * проверяет и сохраняет номер и имя.
     *
     * @param id табельный номер
     * @param name имя человека
     */
    protected Person(EmployeeId id, String name) {
        if (id == null) {
            throw new IllegalArgumentException("табельный номер не задан");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("имя не должно быть пустым");
        }

        this.id = id;
        this.name = name.strip();
    }

    /**
     * возвращает табельный номер.
     *
     * @return номер человека
     */
    public EmployeeId id() {
        return id;
    }

    /**
     * возвращает имя.
     *
     * @return имя человека
     */
    public String name() {
        return name;
    }

    /**
     * формирует описание для вывода.
     *
     * @return описание человека
     */
    public abstract String description();
}