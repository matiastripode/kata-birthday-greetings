import java.time.LocalDate;

public class Employee implements GreetablePerson {
    private final String name;
    private final LocalDate dateOfBirth;

    public Employee(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return this.name;
    }
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
}
