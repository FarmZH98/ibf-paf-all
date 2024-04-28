package sg.edu.nus.iss.Workshop27.exception;

public class GameIdNotFoundException extends RuntimeException {
    public GameIdNotFoundException() {
        super();
    }

    public GameIdNotFoundException(String msg) {
        super(msg);
    }
}
