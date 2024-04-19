package sg.edu.nus.iss.workshop23.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.workshop23.service.BggService;

@Controller
@RequestMapping
public class BggController {
    
    @Autowired
    private BggService bggService;

    @GetMapping("/getGame")
    public ModelAndView getGame(String game) {
        ModelAndView mav = new ModelAndView("game");
        mav.addObject("gameList", bggService.findGame(game));

        return mav;
    }
}
