
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class BirthdayGreetingsTest {


    private BirthdayGreetingsTestObjects testObjects;

    @Before
    public void  setUp() {
        this.testObjects = new BirthdayGreetingsTestObjects();
    }



    @Test
    public void test01_whenThereAreNotEmployeesNoneGreetingsIsSent() {
        List<GreetablePerson> employees = new ArrayList<>();
        BirthdayGreeting greeter = new BirthdayGreeting(new HappyBirthdayEmailSystem());

        try {
            Assert.assertEquals(greeter.greetOn(this.testObjects.today(), employees), false);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void test02_whenEmployeeMonthAndDayAreDifferentFromTodayNoneGreetingsIsSent() {
        List<GreetablePerson> employees = new ArrayList<>();
        LocalDate dateOfBirth = LocalDate.of(1980, Month.MARCH, 4);
        Employee employee = new Employee("Juan Perez", dateOfBirth );
        employees.add(employee);

        BirthdayGreeting greeter = new BirthdayGreeting(this.testObjects.successEmailSystemWithFalse());
        try {
            Assert.assertEquals(greeter.greetOn(this.testObjects.today(), employees), false);
        } catch (Exception e) {
            Assert.fail();
        }

    }


    @Test
    public void test03_whenEmployeeDateOfBirthMonthAndDayMatchesWithTodayAGreetingIsSent() {
        List<GreetablePerson> employees = new ArrayList<>();
        LocalDate dateOfBirth = this.testObjects.dateOfBirthFrom(this.testObjects.today(), 30);
        Employee employee = new Employee("Juan Perez", dateOfBirth );
        employees.add(employee);
        BirthdayGreeting greeter = new BirthdayGreeting(this.testObjects.successEmailSystemWithTrue());

        try {
            Assert.assertEquals(greeter.greetOn(this.testObjects.today(), employees), true);
        }   catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void test04_whenEmailSystemFailedThenGreeterContinueSendingAndSomeEmailsAreNotBeingSent() {
        List<GreetablePerson> employees = new ArrayList<>();
        LocalDate dateOfBirth = this.testObjects.dateOfBirthFrom(this.testObjects.today(), 30);
        Employee juanPerez = new Employee("Juan Perez", dateOfBirth );
        Employee pepeSanchez = new Employee("Pepe Sanchez", dateOfBirth );
        employees.add(juanPerez);
        employees.add(pepeSanchez);

        BirthdayGreeting greeter = new BirthdayGreeting(this.testObjects.failEmailSystem());
        try {
            greeter.greetOn(this.testObjects.today(), employees);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals(BirthdayGreeting.ERROR_SOME_EMAILS_FAILED, e.getMessage());
            Assert.assertFalse(greeter.emailWereSentFor(employees));
        }
    }

}
