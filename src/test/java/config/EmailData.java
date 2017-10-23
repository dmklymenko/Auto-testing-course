package config;

import javax.mail.Address;
import java.util.Date;


public class EmailData {
    private String messageBody;
    private String subject;

    public EmailData(String subject, Address address, Address address1, Date receivedDate, Object content) {
    	this.subject = subject;
    }

    public String getMessageBody() {
        return messageBody;
    }

	public String getSubject() {
		return subject;
	}

}
