package sg.edu.nus.iss.Workshop22.restController;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.Workshop22.model.RSVP;
import sg.edu.nus.iss.Workshop22.service.RSVPService;

@RestController
@RequestMapping("/api/rsvps")
public class RSVPRestController {
    @Autowired
    private RSVPService service;

    @GetMapping
    public ResponseEntity<List<RSVP>> getAll() {
        List<RSVP> result = service.findAll();

        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<RSVP>>(result, HttpStatus.OK);
    }

    @GetMapping(value="/count")
    public ResponseEntity<Integer> getCount() {
        Integer count = service.count();
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> saveRSVP(@RequestBody RSVP rsvp) {

        Boolean status = false;
        System.out.println(rsvp.getId());
        System.out.println(rsvp.getFullName());
        System.out.println(rsvp.getPhone());
        System.out.println(rsvp.getConfirmationDate());
        System.out.println(rsvp.getComments());

        status = service.saveRSVP(rsvp);
        if(status) {
            return ResponseEntity.status(HttpStatus.CREATED).body(status);
        }

        return new ResponseEntity<Boolean>(status, HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/name")
    public ResponseEntity<List<RSVP>> getRSVPByName(@RequestParam String q){
        List<RSVP> result = new LinkedList<RSVP>();
        
        result = service.findRSVPbyName(q);
        
        if(result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<List<RSVP>>(result, HttpStatus.OK);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Boolean> updateRSVP(@PathVariable String email,
                    @RequestBody RSVP rsvp){
        Boolean status = false;
        rsvp.setEmail(email);
        status = service.updateRSVP(rsvp);
        if(status){
            return ResponseEntity.status(HttpStatus.CREATED).body(status);
        }
        
        return new ResponseEntity<Boolean>(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
