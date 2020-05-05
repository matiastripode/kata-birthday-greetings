package infrastructure;

import business.HappyBirthdayEmail;

public interface EmailSystem {
    public boolean sendEmail(HappyBirthdayEmail email) throws Exception;
}
