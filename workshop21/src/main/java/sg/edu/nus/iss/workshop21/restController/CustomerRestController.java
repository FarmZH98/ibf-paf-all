package sg.edu.nus.iss.workshop21.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.workshop21.exception.CustomerNotFoundException;
import sg.edu.nus.iss.workshop21.model.Customer;
import sg.edu.nus.iss.workshop21.model.Order;
import sg.edu.nus.iss.workshop21.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam("limit") int limit,
                                          @RequestParam("offset") int offset){
        return customerService.getCustomersWithPagination(limit, offset);
    }

    @GetMapping("/{id}")
    public Customer getCustomersbyId(@PathVariable("id") int id) throws CustomerNotFoundException {
        return customerService.getCustomerByID(id);
    }

    @GetMapping("/{id}/orders")
    public List<Order> getOrdersByCustomerID(@PathVariable("id") int id) throws CustomerNotFoundException{
        return customerService.getOrdersByCustomerID(id);
    }

}
