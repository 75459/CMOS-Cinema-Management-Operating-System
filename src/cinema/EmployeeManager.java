package cinema;

import java.util.ArrayList;

public class EmployeeManager {
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public EmployeeManager(){}
    public EmployeeManager(ArrayList<Employee> employeeList){
        if (employeeList != null)
        {
            this.employeeList = employeeList;
        }
    }

    // --------------------------------- USEFUL ---------------------------------
    private Employee getEmployeeObjById(int id){ // self-explanatory pretty much
        for (Employee item : employeeList)
        {
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }

    private boolean checkId(int id){ // check whether employee with that id exists
        for (Employee item : employeeList){
            if (item.getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    private int getLastId(){ // returns last used id (for creating new employee)
        if (employeeList.size() == 0)
        {
            return 0;
        }
        else
        {
            return employeeList.get(employeeList.size()-1).getId();
        }
    }

    // -------------------------------------------------------------------------

    public void createEmployee(String name, String role){
        Employee employee = new Employee(getLastId()+1, name, role);
        employeeList.add(employee);
    }

    public String getName(int id){
        if (checkId(id))
        {
            return getEmployeeObjById(id).getName();
        }
        return "Coś poszło nie tak!";
    }

    public String getRole(int id){
        if (checkId(id))
        {
            return getEmployeeObjById(id).getRole();
        }
        return "Coś poszło nie tak!";
    }

    public void setRole(int id,String role){
        if (checkId(id))
        {
            getEmployeeObjById(id).setRole(role);
        }
    }

    public void deleteEmployee(int id){
        if (checkId(id)){
            employeeList.remove(getEmployeeObjById(id));
        }
    }
}
