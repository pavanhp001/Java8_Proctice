package subramanion.patterns.builder.v2;

import java.util.function.Consumer;

class Mailer{
	public static void print(String msg) {
		System.out.println(msg);
	}
	
	public Mailer from(String from) {print("from"); return this;}
	public Mailer to(String to) {print("to"); return this; }
	public Mailer subject(String subject) {print("subject"); return this;}
	public Mailer body(String body) {print("body"); return this;}
	public static void send(Consumer<Mailer> consume) {print("sending");
		Mailer mailer = new Mailer();//Here we can return or don't return the mailer object
		consume.accept(mailer);
	}
}



public class Builder { //also known as Cascade method pattern

	public static void main(String[] args) {

		Mailer.send(mailer -> mailer
		.from("pavan.hp001@gmail.com")
		.to("pavan.scala24@gmail.com")
		.subject("subject")
		.body("body"));
	}
	
	/* problems with this code
	1.we keep saying mailer
	2. how can we reuse the code
	*/

}
