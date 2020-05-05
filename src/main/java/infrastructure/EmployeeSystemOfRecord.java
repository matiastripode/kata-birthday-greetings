package infrastructure;
import business.GreetablePerson;
import business.NullEmployee;

import java.util.List;

public class EmployeeSystemOfRecord {
    private final DataBaseSystem database;

    public EmployeeSystemOfRecord(DataBaseSystem db) {
        this.database = db;
    }
    public GreetablePerson getEmployeeByEmail(String employeeEmail) throws Exception{
        List<GreetablePerson> employees = this.database.query(employeeEmail);
        for (GreetablePerson employee: employees) {
            if (employee.getEmail() == employeeEmail) {
                return employee;
            }
        }
        return new NullEmployee();
    }
}
