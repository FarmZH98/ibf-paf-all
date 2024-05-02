package sg.edu.nus.iss.workshop23o.repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop23o.exception.OrderIdNotFoundExceptio;
import sg.edu.nus.iss.workshop23o.model.Order;

@Repository
public class NorthwindRepo implements NorthwindQueries{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Order getOrder(int orderId) throws ParseException, OrderIdNotFoundExceptio {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_ORDER, orderId);
        Order order = new Order();
        Boolean isDataEmpty = true;

        while(rs.next()) {
            isDataEmpty = false;
            System.out.println(">>>>> DON'T HAVE getOrder exeception");
            order.setId(rs.getInt("order_id"));
            String ldt = rs.getString("order_date");
            // SimpleDateFormat formatter = new SimpleDateFormat("yyyy=MM=dd", Locale.ENGLISH);
            // Date date = formatter.parse(ldt);
            order.setOrder_date(ldt);
            order.setCustomerId(rs.getInt("customer_id"));
            order.addTotalPrice(rs.getDouble("total_price_of_order"));
            order.addCostPrice(rs.getDouble("cost_price"));
        }

        if(isDataEmpty) {
            System.out.println(">>> getOrder exeception");
            throw new OrderIdNotFoundExceptio("order ID not found.");
        }

        return order;
    }
}
