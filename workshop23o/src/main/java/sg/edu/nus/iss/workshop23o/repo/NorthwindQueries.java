package sg.edu.nus.iss.workshop23o.repo;

public interface NorthwindQueries {
    
    public static final String GET_ORDER = 
    """
        select 
        o.id as order_id, 
        o.order_date as order_date, 
        o.customer_id as customer_id, 
        od.quantity*od.unit_price*(1 - od.discount) as total_price_of_order,
        p.standard_cost*od.quantity as cost_price
        from orders o
        left join order_details od on od.order_id = o.id
        left join products p on od.product_id = p.id
        where o.id = ?;
    
    """;

}
