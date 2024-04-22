package ibf2023.paf.day24;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2023.paf.day24.model.Cart;
import ibf2023.paf.day24.model.Order;
import ibf2023.paf.day24.service.OrderService;

@SpringBootApplication
public class Day24Application implements CommandLineRunner {

	@Autowired
	private OrderService service;

	public static void main(String[] args) {
		SpringApplication.run(Day24Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Cart> cartList = new LinkedList<>();
		
		Cart cart = new Cart();
		cart.setItem("apple");
		cart.setQuantity(10);
		cartList.add(cart);

		cart = new Cart();
		cart.setItem("orange");
		cart.setQuantity(20);
		cartList.add(cart);

		Order order = new Order();
		order.setCart(cartList);
		order.setName("Jonny");
		order.setComments("nothing");
		order.setEmail("Jonny@email.com");
		order.setRush(false);
		order.setDeliveryDate(new Date(0));
		//order.setId();

		service.order(order);
	}

}
