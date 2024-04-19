package sg.edu.nus.iss.Workshop22.repo;

public interface RSVPQueries {
    public static final String COUNT_RSVP = 
        """
        SELECT count(*) from rsvp;
        """;

    public static final String COUNT_RSVP_BY_EMAIL =
        """
        SELECT count(*) as count from rsvp where email =?       
        """;

    public static final String ALL_RSVP = 
        """
        SELECT * from rsvp;
        """;

    public static final String INSERT_RSVP = 
        """
        INSERT into rsvp (full_name, email, phone, confirmation_date, comments) values (?,?,?,?,?)
        """;

    public static final String UPDATE_RSVP =
        """
        UPDATE rsvp set phone=?, confirmation_date=?, comments=? where email = ?
        """;

    public static final String GET_RSVP_BY_NAME = 
        """
        SELECT * from rsvp where full_name like ?
        """;
}
