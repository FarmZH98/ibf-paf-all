package sg.edu.nus.iss.workshop24o.repo;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop24o.model.Order;
import sg.edu.nus.iss.workshop24o.model.Product;

@Repository
public class OrdersRepo implements OrdersQueries{
    
    @Autowired
    private JdbcTemplate template;

    public int Order(Order order) {
        KeyHolder keyholder = new GeneratedKeyHolder();

        int insertResult = 0;

        try{
            template.update(conn-> {
                PreparedStatement ps = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                ps.setDate(1, order.getOrderDate());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4,order.getNotes());
                ps.setDouble(5, order.getTax());
                return ps;
            }, keyholder);
            BigInteger primaryKeyVal = (BigInteger)keyholder.getKey();
            order.setOrderId(primaryKeyVal.intValue());

        }catch(DataIntegrityViolationException e){
            System.out.println(e.getStackTrace());
        }

        List<Product> cartList = order.getProducts();

        for(int i=0; i<cartList.size(); ++i) {
            insertResult += template.update(INSERT_CART, cartList.get(i).getProduct(), cartList.get(i).getUnitPrice(), cartList.get(i).getDiscount(), cartList.get(i).getQuantity(), order.getOrderId());
        }
        

        return insertResult;
    }
}