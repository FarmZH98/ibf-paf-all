package sg.edu.nus.iss.workshop23o.model;

import java.util.Date;

public class Order {
    
    private int id;
    private String order_date;
    private int customerId;
    private double totalPrice=0;
    private double costPrice=0;

    public Order(){}

    public Order(int id, String order_date, int customerId, double totalPrice, double costPrice) {
        this.id = id;
        this.order_date = order_date;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.costPrice = costPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addTotalPrice(double totalPrice) {
        this.totalPrice += totalPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void addCostPrice (double costPrice) {
        this.costPrice += costPrice;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", order_date=" + order_date + ", customerId=" + customerId + ", totalPrice="
                + totalPrice + ", costPrice=" + costPrice + "]";
    }
    
}
