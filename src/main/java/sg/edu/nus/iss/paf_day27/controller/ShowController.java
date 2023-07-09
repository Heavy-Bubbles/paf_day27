package sg.edu.nus.iss.paf_day27.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.paf_day27.model.TvShow;
import sg.edu.nus.iss.paf_day27.repository.ShowRepository;
import sg.edu.nus.iss.paf_day27.service.ShowService;

@Controller
@RequestMapping
public class ShowController {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowService showService;

    @GetMapping(path = {"/", "/index.html"})
    public ModelAndView getTypes(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("types");
        mav.addObject("types", showRepository.getAllTypes());
        mav.setStatus(HttpStatusCode.valueOf(200));

        return mav;
    }

    @GetMapping(path = "/type/{type}")
    public ModelAndView getShowsByType(@PathVariable String type){

        List<TvShow> shows = showService.getShowsByType(type, 3, 0);

        ModelAndView mav = new ModelAndView();

        mav.setViewName("shows");
        mav.addObject("type", type);
        mav.addObject("tvshows", shows);
        mav.setStatus(HttpStatusCode.valueOf(200));
        return mav;
    }
}
