import business.BirthdayGreeting;
import business.Employee;
import business.GreetablePerson;
import infrastructure.DataBaseSystem;
import infrastructure.EmailSystem;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class BirthdayGreetingsTestObjects {
    private LocalDate today;

    public LocalDate today() {
        if (today==null) today = LocalDate.now();
        return today;
    }

    public LocalDate dateOfBirthFrom(LocalDate date, int yearsBack) {
        return LocalDate.of(date.getYear() - yearsBack, date.getMonth(), date.getDayOfMonth());
    }

    public EmailSystem failEmailSystem() {
        return (email) -> {throw new Exception(BirthdayGreeting.ERROR_SOME_EMAILS_FAILED);};
    }

    public EmailSystem successEmailSystemWithTrue() {
        return (email) -> {return true;};
    }

    public EmailSystem successEmailSystemWithFalse() {
        return (email) -> {return false;};
    }

    public DataBaseSystem dbSystemReturnEmpty() {
        return (query) -> new ArrayList<Employee>();
    }

    public DataBaseSystem dbSystemReturnTwoEmployees() {
        return (query) -> {
            Employee maryAnn = new Employee("Mary, Ann",
                    LocalDate.of(1975, Month.SEPTEMBER, 10),
                    "mary.ann@foobar.com");
            Employee johnDoe = new Employee("John, Doe",
                    LocalDate.of(1970, Month.JANUARY, 3),
                    "john.doe@foobar.com");
            List<GreetablePerson> listOfEmployee = new ArrayList<>();
            listOfEmployee.add(maryAnn);
            listOfEmployee.add(johnDoe);
            return listOfEmployee;
        };
    }

    public DataBaseSystem dbSystemFailedNetworkConnection(){
        return (query) -> {throw new Exception(DataBaseSystem.ERROR_NETWORK_FAILED);};
    }
}
