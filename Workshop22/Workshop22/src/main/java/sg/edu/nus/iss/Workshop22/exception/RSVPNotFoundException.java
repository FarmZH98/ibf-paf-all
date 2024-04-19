package sg.edu.nus.iss.Workshop22.exception;

public class RSVPNotFoundException extends RuntimeException{
    public RSVPNotFoundException() {
        super();
    }

    public RSVPNotFoundException(String msg) {
        super(msg);
    }
}
