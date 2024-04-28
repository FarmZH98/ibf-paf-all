package ibf2023.paf.day24.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2023.paf.day24.model.Order;
import ibf2023.paf.day24.repo.OrdersRepo;

@Service
public class OrderService {
    
    @Autowired
    private OrdersRepo repo;

    // Rollback if it is an unchecked exception
   // Will not rollback if its a checked exception
   @Transactional(rollbackFor = OrderException.class)
    public void order(Order order) throws OrderException {

        int result = repo.Order(order);

        if(result <= 0) {
            throw new OrderException ("Could not update.");
        }
    }
}
