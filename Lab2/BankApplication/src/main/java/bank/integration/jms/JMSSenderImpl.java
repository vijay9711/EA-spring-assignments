package bank.integration.jms;


public class JMSSenderImpl implements JMSSender{
	private String text;
	public void sendJMSMessage (String text){
		this.text = text;
		System.out.println("JMSSender: sending JMS message ="+text);
	}

	public String getText() {
		return text;
	}
}
