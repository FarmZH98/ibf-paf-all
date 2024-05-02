package sg.edu.nus.iss.workshop23o;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.workshop23o.model.Order;
import sg.edu.nus.iss.workshop23o.service.NorthwindService;

@SpringBootApplication
public class Workshop23oApplication implements CommandLineRunner {

	@Autowired
	private NorthwindService service;

	public static void main(String[] args) {
		SpringApplication.run(Workshop23oApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Order order = service.getOrder(-5);
		System.out.println(">>>>> Order info: " + order.toString());
	}

}
