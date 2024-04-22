package ibf2023.paf.day24.repo;

public interface OrdersQueries {
    
    public final static String INSERT_ORDER = 
    """
    INSERT into order_info (id, name, email, delivery_date, rush, comments) values (?,?,?,?,?,?)         
    """;

    public final static String GET_FORGEIGN_KEY = "DECLARE @fk_order_id INT = SCOPE_IDENTITY()";

    public final static String INSERT_CART =
    """
    INSERT into order_product_info (item, quantity, order_id) values (?,?,?)     
    """;

}
