package sg.edu.nus.iss.workshop23o.exception;

public class OrderIdNotFoundExceptio extends RuntimeException{
    public OrderIdNotFoundExceptio() {
        super();
    }

    public OrderIdNotFoundExceptio(String msg) {
        super(msg);
    }
}
