package business;

public class FailedEmail {
    public String getReceiverName(){ return this.email.receiver.getName(); }

    private final HappyBirthdayEmail email;
    private final Exception exception;

    FailedEmail(HappyBirthdayEmail email, Exception exception) {
        this.email = email;
        this.exception = exception;
    }

}