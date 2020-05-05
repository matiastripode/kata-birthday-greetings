package business;

import business.GreetablePerson;
import infrastructure.HappyBirthdayEmailSystem;

public class HappyBirthdayEmail {
    public String subject;
    public String body;
    public GreetablePerson receiver;

    public HappyBirthdayEmail(String subject, String body, GreetablePerson receiver) {
        this.subject = subject;
        this.body = body;
        this.receiver = receiver;
    }

    public static HappyBirthdayEmail composeHappyBirthdayEmail(GreetablePerson employee) {
        String emailSubject = "Happy birthday!";
        String emailBody = HappyBirthdayEmail.bodyComposer(employee.getName());
        return new HappyBirthdayEmail(emailSubject, emailBody, employee);
    }

    private static String bodyComposer(String name) {
        return " Happy birthday, dear " + name + "!";
    }

}
