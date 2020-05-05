
import business.BirthdayGreeting;
import business.Employee;
import business.GreetablePerson;
import infrastructure.HappyBirthdayEmailSystem;
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
        BirthdayGreeting greeter = new BirthdayGreeting(
                new HappyBirthdayEmailSystem(),
                this.testObjects.employeeSystemOfRecordsWithNoEmployees());
        try {
            Assert.assertEquals(greeter.greetOn(this.testObjects.today()), false);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals(BirthdayGreeting.THERE_ARE_NOT_EMPLOYEES_TO_GREET, e.getMessage());
        }
    }

    @Test
    public void test02_whenEmployeeDateOfBirthMonthAndDayMatchesWithTodayAGreetingIsSent() {
        BirthdayGreeting greeter = new BirthdayGreeting(
                this.testObjects.successEmailSystemWithTrue(),
                this.testObjects.employeeSystemOfRecordsWithTwoEmployeeBornOn(this.testObjects.today()));

        try {
            Assert.assertEquals(greeter.greetOn(this.testObjects.today()), true);
        }   catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void test03_whenEmailSystemFailedThenGreeterContinueSendingAndSomeEmailsAreNotBeingSent() {
        BirthdayGreeting greeter = new BirthdayGreeting(
                this.testObjects.failEmailSystem(),
                this.testObjects.employeeSystemOfRecordsWithTwoEmployeeBornOn(this.testObjects.today()));
        try {
            greeter.greetOn(this.testObjects.today());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals(BirthdayGreeting.ERROR_SOME_EMAILS_FAILED, e.getMessage());
            Assert.assertFalse(greeter.emailWereSentFor(this.testObjects.twoEmployeesBornOn(this.testObjects.today())));
        }
    }

}
