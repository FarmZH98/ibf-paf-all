package sg.edu.nus.iss.workshop24o.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.workshop24o.model.Order;
import sg.edu.nus.iss.workshop24o.repo.OrdersRepo;

@Service
public class OrderService {
    
    @Autowired
    private OrdersRepo repo;

    // Rollback if it is an unchecked exception
   // Will not rollback if its a checked exception
   @Transactional(rollbackFor = OrderException.class)
    public void order(Order order) throws OrderException {

        int result = repo.Order(order);

        if(result < order.getProducts().size()) {
            throw new OrderException ("Could not update.");
        }
    }
}
