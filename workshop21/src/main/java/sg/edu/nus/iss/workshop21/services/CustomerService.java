package sg.edu.nus.iss.workshop21.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop21.exception.CustomerNotFoundException;
import sg.edu.nus.iss.workshop21.model.Customer;
import sg.edu.nus.iss.workshop21.model.Order;
import sg.edu.nus.iss.workshop21.repo.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    public List<Customer> getCustomersWithPagination(int limit, int offset) {
        return customerRepository.getAllCustomersWithPagination(limit, offset);
    }

    public Customer getCustomerByID(int id) throws CustomerNotFoundException {
        boolean isCustomerExist = customerRepository.isCustomerExist(id);
        if(!isCustomerExist) {
            throw new CustomerNotFoundException("Customer with id: " + id + " not found.");
        }
        return customerRepository.getCustomerByID(id);
    }

    public List<Order> getOrdersByCustomerID(int id) throws CustomerNotFoundException {
        boolean isCustomerExist = customerRepository.isCustomerExist(id);
        if(!isCustomerExist) {
            throw new CustomerNotFoundException("Customer with id: " + id + " not found.");
        }
        return customerRepository.getOrdersByCustomerID(id);
    }


}
