package customers.Component;

public interface EmailSender {
	void sendEmail(String email, String message);
	String getOutgoingMailServer();

}