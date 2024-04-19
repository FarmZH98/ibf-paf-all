package sg.edu.nus.iss.workshop26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.workshop26.service.LibraryService;

@Controller
@RequestMapping
public class LibraryController {

    @Autowired
    private LibraryService service;
    
    @GetMapping(path={"/search"})
    public ModelAndView getBooksList(@RequestParam String title) {

        System.out.println("Book title detected: " + title);
        ModelAndView mav = new ModelAndView("bookList");

        mav.addObject("booksList", service.findBooksLikeTitle(title));

        return mav;
    }

    @GetMapping(path={"/book/{_id}"})
    public ModelAndView getBookDetails(@PathVariable("_id") String _id) {
        ModelAndView mav = new ModelAndView("book");

        //System.out.println("BookID: " + bookID);
        //Integer idInteger = Integer.parseInt(bookID);
        mav.addObject("book", service.findBookDetailsByBookID(_id));

        return mav;
    }
}
