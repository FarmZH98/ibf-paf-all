package sg.edu.nus.iss.workshop25;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.workshop25.model.MessageObject;
import sg.edu.nus.iss.workshop25.service.MessageSender;

@SpringBootApplication
public class Workshop25Application implements CommandLineRunner {

	@Autowired
	private MessageSender messageSender;
	
	public static void main(String[] args) {
		SpringApplication.run(Workshop25Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Runnable poller = () -> {
			while (true) {
				String id = System.console().readLine("What is ID: ");
				String message = System.console().readLine("What is your message ");
				MessageObject messageObject = new MessageObject(id, message);
				messageSender.pushMessage(messageObject);
			}
		};

		System.out.println("*** Starting poller");
		Executors.newSingleThreadExecutor().execute(poller);


	}

}
