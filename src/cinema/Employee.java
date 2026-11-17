package cinema;

public class Employee {
    private int id;
    private String name;
    private String role;

    public Employee(int id, String name, String role){
        this.id = id;
        this.name = name;
        this.role = role;
    }

    int getId(){
        return id;
    }
    String getName(){
        return name;
    }
    String getRole(){
        return role;
    }

    void setRole(String role){
        if (role != null)
        {
            this.role = role;
        }
    }
}
