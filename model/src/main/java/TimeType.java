import java.math.BigDecimal;

/**
 * тип рабочего времени с коэффициентом оплаты.
 */
public enum TimeType {

    /**
     * обычное рабочее время.
     */
    REGULAR("обычное") {
        @Override
        public BigDecimal coefficient() {
            return BigDecimal.ONE;
        }
    },

    /**
     * работа сверх установленной нормы.
     */
    OVERTIME("сверхурочное") {
        @Override
        public BigDecimal coefficient() {
            return new BigDecimal("1.5");
        }
    },

    /**
     * работа с 22:00 включительно до 06:00 следующего дня.
     */
    NIGHT("ночное") {
        @Override
        public BigDecimal coefficient() {
            return new BigDecimal("1.2");
        }
    },

    /**
     * работа в праздничный день.
     */
    HOLIDAY("праздничное") {
        @Override
        public BigDecimal coefficient() {
            return new BigDecimal("2");
        }
    };

    private final String label;

    TimeType(String label) {
        this.label = label;
    }

    /**
     * возвращает название типа времени для вывода.
     *
     * @return название типа времени
     */
    public String label() {
        return label;
    }

    /**
     * возвращает множитель базовой почасовой ставки.
     *
     * @return коэффициент оплаты
     */
    public abstract BigDecimal coefficient();
}