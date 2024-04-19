package sg.edu.nus.iss.workshop21.repo;

public interface Queries {
    public static final String GET_ALL_CUSTOMERS = "SELECT id, first_name, last_name FROM customers";

    public static final String GET_ALL_CUSTOMERS_WITH_PAGINATION = "SELECT id, first_name, last_name FROM customers limit ? offset ?";

    public static final String GET_CUSTOMER_BY_ID = "SELECT id, first_name, last_name FROM customers where id = ?";

    public static final String GET_ORDER_BY_CUSTOMER_ID = 
        """
        select c.id as c_id , c.first_name as c_fn, c.last_name as c_ln,
        o.tax_rate as o_trate, o.order_date as o_odate
        from customers c, orders o where c.id = o.customer_id
        and c.id = ?
        """;
    
    public static final String IS_CUSTOMER_EXIST = 
        """
        SELECT count(*) as count FROM customers WHERE id = ?
        """;
}
