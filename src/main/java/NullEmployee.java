import java.time.LocalDate;

public class NullEmployee implements GreetablePerson{
    public String getName() {
        return "";
    }

    @Override
    public LocalDate getDateOfBirth() {
        return null;
    }
}
