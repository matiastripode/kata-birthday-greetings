package infrastructure;

import business.GreetablePerson;
import business.HappyBirthdayEmail;
import business.NullEmployee;
import org.apache.commons.lang3.StringUtils;

public class HappyBirthdayEmailSystem implements EmailSystem {

    public static final String WRONG_EMAIL_FORMAT = "Error either subject or body are null";
    public static final String INVALID_RECEIVER = "Error invalid receiver format";

    private boolean lastEmailWasSent;


    public HappyBirthdayEmailSystem() {
        this.lastEmailWasSent = false;
    }

    public boolean emailWasSent() {
        return this.lastEmailWasSent;
    }

    @Override
    public boolean sendEmail(HappyBirthdayEmail email) throws Exception {
        this.lastEmailWasSent = this.sendEmail(email.subject, email.body, email.receiver);
        return this.lastEmailWasSent;
    }

    private boolean sendEmail(String emailSubject, String emailBody, GreetablePerson person) throws Exception {

        if (StringUtils.isBlank(emailSubject) || StringUtils.isBlank(emailBody)) {
            throw new Exception(WRONG_EMAIL_FORMAT);
        } else if (person.getClass() == NullEmployee.class) {
            throw new Exception(INVALID_RECEIVER);
        } else if (StringUtils.isBlank(person.getName())) {
            throw new Exception(INVALID_RECEIVER);
        }

        return true;
    }
}
