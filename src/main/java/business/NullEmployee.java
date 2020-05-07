package business;

import business.GreetablePerson;

import java.time.LocalDate;

public class NullEmployee implements GreetablePerson {
    public String getName() {
        return "";
    }

    @Override
    public LocalDate getDateOfBirth() {
        return LocalDate.of(1, 1, 1);
    }

    @Override
    public String getEmail() {
        return "null";
    }
}
