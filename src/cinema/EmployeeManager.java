package cinema;

import java.util.ArrayList;

public class EmployeeManager implements Displayable {
    private ArrayList<Person> staffList = new ArrayList<>();

    public EmployeeManager() {}


    private int getLastId() {
        if (staffList.size() == 0) {
            return 0;
        } else {
            return staffList.get(staffList.size() - 1).getId();
        }
    }

    public void createEmployee(String firstName, String lastName, String position) {
        Person emp = new Employee(getLastId() + 1, firstName, lastName, position);
        staffList.add(emp);
    }

    public void createManager(String firstName, String lastName, String department) {
        Person mgr = new Manager(getLastId() + 1, firstName, lastName, department);
        staffList.add(mgr);
    }

    public String getAllInfo() {
        if (staffList.size() == 0) {
            return "Brak pracowników w bazie.";
        }
        String result = "";
        for (Person item : staffList) {
            result = result + item.getDetails() + "\n";
        }
        return result;
    }

    public String getStaffByRole(boolean onlyManagers) {
        if (staffList.size() == 0) {
            return "Brak pracowników w bazie.";
        }
        String result = "";
        for (Person item : staffList) {
            if (!onlyManagers && item instanceof Employee) {
                Employee emp = (Employee) item; // downcasting
                result = result + emp.getId() + ". " + emp.firstName + " " + emp.lastName
                        + " | Stanowisko: " + emp.getPosition() + "\n";
            } else if (onlyManagers && item instanceof Manager) {
                Manager mgr = (Manager) item; // downcasting
                result = result + mgr.getId() + ". " + mgr.firstName + " " + mgr.lastName
                        + " | Dział: " + mgr.getDepartment() + "\n";
            }
        }
        if (result.equals("")) {
            return "Brak osób spełniających kryterium.";
        }
        return result;
    }
}