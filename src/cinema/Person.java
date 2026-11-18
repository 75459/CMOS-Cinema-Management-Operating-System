package cinema;

public abstract class Person {
    protected int id;
    protected String firstName;
    protected String lastName;

    Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    int getId() {
        return id;
    }

    abstract String getDetails();
}