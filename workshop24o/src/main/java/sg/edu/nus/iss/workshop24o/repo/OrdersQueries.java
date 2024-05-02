package sg.edu.nus.iss.workshop24o.repo;

public interface OrdersQueries {
    
    public final static String INSERT_ORDER = 
    """
    INSERT into orders (order_date, customer_name, ship_address, notes, tax) values (?,?,?,?,?)         
    """;

    public final static String INSERT_CART =
    """
    INSERT into order_details (product, unit_price, discount, quantity, order_id) values (?,?,?,?,?)     
    """;

}
