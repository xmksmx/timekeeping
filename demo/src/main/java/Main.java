void main() {
    var first = new EmployeeId("E001");
    var second = new EmployeeId("E001");

    System.out.println("номер: " + first.value());
    System.out.println("номера совпадают: " + first.equals(second));
    System.out.println("хеши совпадают: " + (first.hashCode() == second.hashCode()));
}