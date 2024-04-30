package sg.edu.nus.iss.Workshop27.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import sg.edu.nus.iss.Workshop27.model.Review;
import sg.edu.nus.iss.Workshop27.service.GameService;
import sg.edu.nus.iss.Workshop27.service.ReviewService;

@Controller
@RequestMapping
public class GameController {
    
    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = {"/", "/index"})
    public ModelAndView loadHomePage() {
        ModelAndView mav = new ModelAndView("index.html");

        Review review = new Review();
        mav.addObject("review", review);
        
        return mav;

    }

    //edit field and add into comme
    @PostMapping("/review")
    public ModelAndView addReview(@Valid @ModelAttribute("newGame") Review newReview, BindingResult result) {
        ModelAndView mav = new ModelAndView("index");
        if(result.hasErrors()) {
            return mav;
        }

        mav.setViewName("success");
        newReview.setPosted(new Date());
        reviewService.saveNewReview(newReview);
        mav.addObject("savedReview", newReview);
        
        return mav;

    }
}
