package sg.edu.nus.iss.Workshop22.repo;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Workshop22.model.RSVP;

@Repository
public class RSVPRepository implements RSVPQueries {
    
    @Autowired
    private JdbcTemplate template;

    public int count() {
        return template.queryForObject(COUNT_RSVP, Integer.class);
    }

    public List<RSVP> findAll() {
        List<RSVP> result = new LinkedList<>();

        //to use this, we need to ensure that column name is using underscore "_", and model is using camelCase
        result = template.query(ALL_RSVP, BeanPropertyRowMapper.newInstance(RSVP.class)); //exam not allowed. we should map 1 by 1 like in workshop21
        
        return result;
    }

    public List<RSVP> findRSVPByName (String name) {
        List<RSVP> result = new LinkedList<RSVP>();

        final SqlRowSet rs = template.queryForRowSet(GET_RSVP_BY_NAME, '%' + name + '%');

        while(rs.next()) {
            RSVP r = new RSVP();
            r.setId(rs.getInt("id"));
            r.setFullName(rs.getString("full_name"));
            r.setPhone(rs.getString("phone"));
            r.setEmail(rs.getString("email"));
            r.setComments(rs.getString("comments"));
            Date ldt = (Date) rs.getObject("confirmation_date");
            r.setConfirmationDate(ldt);
            result.add(r);
        }

        return result;
    }

    public Boolean saveRSVP(RSVP rsvp){
        int insertResult  = 0;
        insertResult = template.update(INSERT_RSVP, rsvp.getFullName(), 
                            rsvp.getEmail(), rsvp.getPhone(), 
                            rsvp.getConfirmationDate(),
                            rsvp.getComments() );
        return insertResult> 0? true: false;
    }

    public Boolean updateRSVP(RSVP rsvp){
        int insertResult  = 0;
        insertResult = template.update(UPDATE_RSVP, 
                            rsvp.getPhone(), 
                            rsvp.getConfirmationDate(),
                            rsvp.getComments(),
                            rsvp.getEmail() );
        return insertResult> 0? true: false;
    }


    public boolean isRSVPExist(String email) {
        boolean isRSVPExist = false;
        final SqlRowSet rs = template.queryForRowSet(COUNT_RSVP_BY_EMAIL, email);

        while(rs.next()) {
            if(rs.getInt("count") > 0) isRSVPExist = true;
        }

        return isRSVPExist;
    }
}
