package sg.edu.nus.iss.workshop24o;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.workshop24o.model.Order;
import sg.edu.nus.iss.workshop24o.model.Product;
import sg.edu.nus.iss.workshop24o.repo.OrdersRepo;
import sg.edu.nus.iss.workshop24o.service.OrderService;

@SpringBootApplication
public class Workshop24oApplication implements CommandLineRunner {

	@Autowired
	private OrderService service;

	public static void main(String[] args) {
		SpringApplication.run(Workshop24oApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product1 = new Product(1, "apple", 2, 1, 5);
		Product product2 = new Product(2, "orange", 3, 1, 10);
		List<Product> productList = new LinkedList<>();
		productList.add(product1);
		productList.add(product2);

		Order order = new Order(1, new Date(0), "Georgia", "Bishan", "Nothing", 0.05, productList);

		service.order(order);

	}

}
