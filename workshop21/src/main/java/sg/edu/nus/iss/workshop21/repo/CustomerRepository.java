package sg.edu.nus.iss.workshop21.repo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop21.model.Customer;
import sg.edu.nus.iss.workshop21.model.Order;

@Repository
public class CustomerRepository implements Queries {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers() {

        List<Customer> result = new LinkedList<>();
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(Queries.GET_ALL_CUSTOMERS);

        while(rs.next()) {
            Customer c = new Customer();
            c.setId(rs.getInt("id"));
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));
            result.add(c);
        }

        return Collections.unmodifiableList(result);
    } 

    public List<Customer> getAllCustomersWithPagination(int limit, int offset) {
        List<Customer> result = new LinkedList<>();
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_ALL_CUSTOMERS_WITH_PAGINATION, limit, offset);

        while(rs.next()) {
            Customer c = new Customer();
            c.setId(rs.getInt("id"));
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));
            result.add(c);
        }

        return Collections.unmodifiableList(result);
    }

    public Customer getCustomerByID(int id) {
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_CUSTOMER_BY_ID, id);
        Customer c = new Customer();

        while(rs.next()) {
            
            c.setId(rs.getInt("id"));
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));
        }

        return c;
    }

    public List<Order> getOrdersByCustomerID(int id){
        System.out.println("getOrdersByCustomerID >>>");
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_ORDER_BY_CUSTOMER_ID, id);
        List<Order> result = new LinkedList<>();
        

        while(rs.next()) {
            System.out.println("testing ok");
            Order o = new Order();
            o.setId(rs.getInt("c_id"));
            System.out.println("firstname: " + rs.getString("c_fn"));
            o.setFirstName(rs.getString("c_fn"));
            o.setLastName(rs.getString("c_ln"));
            o.setTaxRate(rs.getDouble("o_trate"));
            o.setOrderDate(rs.getString("o_odate"));
            result.add(o);
        }

        return Collections.unmodifiableList(result);
    }

    public boolean isCustomerExist(int id) {
        boolean isExist = false;
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(IS_CUSTOMER_EXIST, id);
        
        System.out.print("rs: " + rs);

        if(rs.next()) {
            int x = rs.getInt("count");
            if(x > 0) isExist = true;    
        }

        return isExist;
    }
}
