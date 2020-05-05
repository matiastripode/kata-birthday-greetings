import business.Employee;
import business.HappyBirthdayEmail;
import business.NullEmployee;
import infrastructure.HappyBirthdayEmailSystem;
import org.junit.Assert;
import org.junit.Test;

public class HappyBirthdayEmailSystemTest {

    /**
     The greetings email contains the following text:
     Subject: Happy birthday!
     Happy birthday, dear John!
     with the first name of the employee substituted for “John”
     * */
    @Test
    public void  test01_whenEitherEmailSubjectOrBodyAreEmptyOrNullThenEmailIsNotSent() {
        HappyBirthdayEmailSystem emailSystem = new HappyBirthdayEmailSystem();
        String emailSubject = "Happy birthday!";
        String emailBody = "";
        HappyBirthdayEmail email = new HappyBirthdayEmail(emailSubject, emailBody, new Employee("Diego, Maradona", null, ""));

        try {
            boolean result = emailSystem.sendEmail(email);
            Assert.fail();
        } catch ( Exception e) {
            Assert.assertEquals(HappyBirthdayEmailSystem.WRONG_EMAIL_FORMAT, e.getMessage());
            Assert.assertFalse(emailSystem.emailWasSent());
        }
    }

    @Test
    public void test02_whenEmailReceiverIsNullThenEmailIsNotSent() {
        HappyBirthdayEmailSystem emailSystem = new HappyBirthdayEmailSystem();
        NullEmployee nullEmployee = new NullEmployee();

        HappyBirthdayEmail email = HappyBirthdayEmail.composeHappyBirthdayEmail(nullEmployee);

        try {
            boolean result = emailSystem.sendEmail(email);
            Assert.fail();
        } catch ( Exception e) {
            Assert.assertEquals(HappyBirthdayEmailSystem.INVALID_RECEIVER, e.getMessage());
            Assert.assertFalse(emailSystem.emailWasSent());
        }
    }

    @Test
    public void test03_whenEmailHasValidReceiverSubjectAndBodyThenEmailIsSent() {
        HappyBirthdayEmailSystem emailSystem = new HappyBirthdayEmailSystem();
        Employee employee = new Employee("John, Doe", null, "john.doe@foobar.com");
        HappyBirthdayEmail email = HappyBirthdayEmail.composeHappyBirthdayEmail(employee);
        try {
            Assert.assertEquals(emailSystem.sendEmail(email), true);
        } catch (Exception e){
            Assert.fail();
        }
    }
}
