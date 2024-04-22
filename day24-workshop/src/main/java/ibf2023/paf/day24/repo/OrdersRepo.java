package ibf2023.paf.day24.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2023.paf.day24.model.Cart;
import ibf2023.paf.day24.model.Order;

@Repository
public class OrdersRepo implements OrdersQueries{
    
    @Autowired
    private JdbcTemplate template;

    public int Order(Order order) {
        int insertResult = 0;
        insertResult = template.update(INSERT_ORDER, order.getId(), order.getName(), order.getEmail(), order.getDeliveryDate(), order.isRush(), order.getComments());
        // final SqlRowSet rs = template.queryForRowSet(GET_FORGEIGN_KEY);

        // int foreignKey = -1;

        // while(rs.next()) {
        //     foreignKey = rs.getInt("fk_order_id");
        // }

        List<Cart> cartList = order.getCart();

        for(int i=0; i<cartList.size(); ++i) {
            insertResult = template.update(INSERT_CART, cartList.get(i).getItem(), cartList.get(i).getQuantity(), order.getId());
        }
        

        return insertResult;
    }
}
