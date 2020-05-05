public class HappyBirthdayEmail {
    public String subject;
    public String body;
    public  GreetablePerson receiver;

    public HappyBirthdayEmail(String subject, String body, GreetablePerson receiver) {
        this.subject = subject;
        this.body = body;
        this.receiver = receiver;
    }
}
