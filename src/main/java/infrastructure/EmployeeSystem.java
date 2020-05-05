package infrastructure;

import business.GreetablePerson;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeSystem {
    List<GreetablePerson> findEmployeesBornOn(LocalDate date);
}
