package cinema;

public class Manager extends Person {
    private String department; // np. "Kierownik kina", "Zarząd"

    Manager(int id, String firstName, String lastName, String department) {
        super(id, firstName, lastName);
        this.department = department;
    }

    @Override
    String getDetails() {
        return id + ". " + firstName + " " + lastName + " | Zarządca (" + department + ")";
    }

    String getDepartment() {
        return department;
    }
}