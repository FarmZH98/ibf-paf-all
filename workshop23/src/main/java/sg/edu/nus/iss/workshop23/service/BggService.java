package sg.edu.nus.iss.workshop23.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop23.model.Game;
import sg.edu.nus.iss.workshop23.repo.BggRepository;

@Service
public class BggService {
    
    @Autowired
    private BggRepository repo;

    public List<Game> findGame(String wildcard) {
        return repo.findGame(wildcard);
    }

}
