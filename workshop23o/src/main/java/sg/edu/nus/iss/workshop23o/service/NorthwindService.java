package sg.edu.nus.iss.workshop23o.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop23o.exception.OrderIdNotFoundExceptio;
import sg.edu.nus.iss.workshop23o.model.Order;
import sg.edu.nus.iss.workshop23o.repo.NorthwindRepo;

@Service
public class NorthwindService {
    
    @Autowired
    private NorthwindRepo repo;

    public Order getOrder (int order_id) throws ParseException, OrderIdNotFoundExceptio {
        return repo.getOrder(order_id);
    }
}
