package subramanion.patterns.builder.v1;


class Mailer{
	public static void print(String msg) {
		System.out.println(msg);
	}
	
	public void from(String from) {print("from");}
	public void to(String to) {print("to");}
	public void subject(String subject) {print("subject");}
	public void body(String body) {print("body");}
	public void send() {print("sending");}
}



public class Builder { //also known as Cascade method pattern

	public static void main(String[] args) {

		Mailer mailer = new Mailer();
		mailer.from("pavan.hp001@gmail.com");
		mailer.to("pavan.scala24@gmail.com");
		mailer.subject("subject");
		mailer.body("body");
		mailer.send();
	}
	
	/* problems with this code
	1.we keep saying mailer
	
	*/

}
