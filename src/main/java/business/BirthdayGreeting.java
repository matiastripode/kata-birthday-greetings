package business;
import infrastructure.EmployeeSystem;
import infrastructure.EmailSystem;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;


public class BirthdayGreeting {
    public static final  String ERROR_SOME_EMAILS_FAILED = "Error some emails were not sent.";
    public static final String THERE_ARE_NOT_EMPLOYEES_TO_GREET = "There are not employees to greet";
    private final EmailSystem emailSystem;
    private final EmployeeSystem employeeSystemOfRecords;
    private List<FailedEmail> failedEmails;
    private List<HappyBirthdayEmail> sentEmails;


    public BirthdayGreeting(EmailSystem emailSystem, EmployeeSystem employeeSystemOfRecords){
        this.emailSystem = emailSystem;
        this.employeeSystemOfRecords = employeeSystemOfRecords;
        this.failedEmails = new ArrayList<>();
        this.sentEmails = new ArrayList<>();
    }


    public boolean greetOn(LocalDate date) throws Exception {
        List<GreetablePerson> employees = this.employeeSystemOfRecords.findEmployeesBornOn(date);
        assertThereIsAtLeastAnEmployee(employees);

        resetListForTrackingFailedAndSuccessfulEmails();

        this.failedEmails = sendEmailsAndTrackFailures(employees, this.failedEmails);
        return returnOrThrow(this.failedEmails);
    }

    private void resetListForTrackingFailedAndSuccessfulEmails() {
        this.failedEmails = new ArrayList<>();
        this.sentEmails = new ArrayList<>();
    }

    public boolean emailWereSentFor(List<GreetablePerson> employees) {
        List<GreetablePerson> foundsInSentEmails = new ArrayList<>();
        for (GreetablePerson person: employees) {
            for (HappyBirthdayEmail sent: this.sentEmails) {
                if (sent.receiver.getName().equalsIgnoreCase(person.getName())) {
                    foundsInSentEmails.add(person);
                }
            }
        }
        return foundsInSentEmails.size() == employees.size();
    }

    /**
     * PRIVATE
     * */

    private void assertThereIsAtLeastAnEmployee(List<GreetablePerson> employees) {
        if (employees.size() == 0) throw new RuntimeException(THERE_ARE_NOT_EMPLOYEES_TO_GREET);
    }

    private List<FailedEmail> sendEmailsAndTrackFailures(List<GreetablePerson> toGreet, List<FailedEmail> failedEmails) {

        HappyBirthdayEmail email = null;
        for(GreetablePerson person : toGreet){
            try {
                email = this.buildEmail(person);
                emailSystem.sendEmail(email);
                this.sentEmails.add(email);
            } catch (Exception ex) {
                failedEmails.add(new FailedEmail(email, ex));
                continue;
            }
        }
        return failedEmails;
    }

    private boolean returnOrThrow(List<FailedEmail> failedEmails) throws Exception {
        boolean throwException = failedEmails.size() > 0;
        if (throwException) {
            throw  new Exception(ERROR_SOME_EMAILS_FAILED);
        } else {
            return true;
        }
    }

    private HappyBirthdayEmail buildEmail(GreetablePerson employee) {
        return HappyBirthdayEmail.composeHappyBirthdayEmail(employee);
    }
}
