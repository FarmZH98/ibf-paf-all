package sg.edu.nus.iss.workshop23.repo;

public interface BggQueries {
    public static final String SEARCH_GAME =
    """
        SELECT g.name, count(c.gid) as num_of_reviews, avg(c.rating) as avg_rating
        FROM game as g
        inner join comment as c
        on g.gid=c.gid
        where g.name like ?
        group by g.gid
        order by avg_rating desc;
    """;
}
