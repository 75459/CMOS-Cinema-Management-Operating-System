package cinema;

public class Employee extends Person {
    private String position; // np. "Kasjer", "Obsługa sali"

    Employee(int id, String firstName, String lastName, String position) {
        super(id, firstName, lastName);
        this.position = position;
    }

    @Override
    String getDetails() {
        return id + ". " + firstName + " " + lastName + " | Pracownik (" + position + ")";
    }

    String getPosition() {
        return position;
    }
}