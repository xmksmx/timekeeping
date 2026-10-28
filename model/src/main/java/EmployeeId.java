/**
 * табельный номер сотрудника.
 *
 * @param value номер сотрудника
 */
public record EmployeeId(String value) {

    /**
     * проверяет номер перед сохранением.
     *
     * @param value номер сотрудника
     */
    public EmployeeId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "табельный номер не должен быть пустым"
            );
        }

        // убираем случайные пробелы при копировании номера.
        value = value.strip();
    }
}