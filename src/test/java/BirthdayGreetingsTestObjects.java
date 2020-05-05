import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
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

}
