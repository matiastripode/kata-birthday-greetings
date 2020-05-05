
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;


class FailedEmail {
    public String getReceiverName(){ return this.email.receiver.getName(); }

    private final HappyBirthdayEmail email;
    private final Exception exception;

    FailedEmail(HappyBirthdayEmail email, Exception exception) {
        this.email = email;
        this.exception = exception;
    }

}
public class BirthdayGreeting {
    public static final  String ERROR_SOME_EMAILS_FAILED = "Error some emails were not sent.";
    private final EmailSystem emailSystem;
    private List<FailedEmail> failedEmails;


    public BirthdayGreeting(EmailSystem emailSystem){
        this.emailSystem = emailSystem;
        this.failedEmails = new ArrayList<>();
    }


    public boolean greetOn(LocalDate today, List<GreetablePerson> employees) throws Exception {
        MonthDay yearMonth = MonthDay.from(today);

        this.failedEmails = new ArrayList<>();
        if (employees.size() == 0) {
            return false;
        }

        List<GreetablePerson> toGreet = new ArrayList<>();
        for(GreetablePerson employee: employees) {
            if (yearMonth.compareTo(MonthDay.from(employee.getDateOfBirth())) == 0) {
                toGreet.add(employee);
            }
        }

        if (toGreet.size() == 0) {
            return false;
        }

        this.failedEmails = sendEmailsAndTrackFailures(toGreet, this.failedEmails);
        return returnOrThrow(this.failedEmails);

    }

    public boolean emailWereSentFor(List<GreetablePerson> employees) {
        List<GreetablePerson> foundsInFailedEmails = new ArrayList<>();
        for (GreetablePerson person: employees) {
            for (FailedEmail failed: this.failedEmails) {
                if (failed.getReceiverName() == person.getName()) {
                    foundsInFailedEmails.add(person);
                }
            }
        }
        return foundsInFailedEmails.size() == 0;
    }

    private List<FailedEmail> sendEmailsAndTrackFailures(List<GreetablePerson> toGreet, List<FailedEmail> failedEmails) {

        HappyBirthdayEmail email = null;
        for(GreetablePerson person : toGreet){
            try {
                email = this.buildEmail(person);
                emailSystem.sendEmail(email);
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
        return HappyBirthdayEmailSystem.composeHappyBirthdayEmail(employee);
    }
}
