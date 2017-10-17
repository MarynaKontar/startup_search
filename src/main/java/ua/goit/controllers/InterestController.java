package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.Interest;
import ua.goit.entity.Project;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.InterestService;
import ua.goit.services.UserService;

import java.io.IOException;

/**
 * * Controller for {@link ua.goit.entity.Interest}
 */
@Controller
@RequestMapping("/interest")
public class InterestController {

    private final InterestService interestService;
    private final UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public InterestController(InterestService interestService, UserService userService) {
        this.interestService = interestService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public ModelAndView createInterest() {
        ModelAndView modelAndView = new ModelAndView("interest-create-form");
        modelAndView.addObject("countries", Country.values());
        modelAndView.addObject("industries", Industry.values());
        LOGGER.info("Building new interest form");
        return modelAndView;
    }

    /**
     * Mapping for url ":/interest/create/"
     * Saves {@link Interest} to database
     *
     * @param interest
     * @return redirect link to personal account page
     * @throws IOException if {@link Project} was not saved in the database
     */
    @PostMapping("/create/")
    public ModelAndView save(@ModelAttribute("interest") Interest interest) throws IOException {
        try {
            interestService.save(interest);
        } catch (Exception e) {
            LOGGER.info("Interest " + interest + " didn't save to database.");
            throw new IOException("Exception during saving interest to database", e);
        }
        LOGGER.info("Interest " + interest + " saved to database. Redirecting to personal account page");
        return new ModelAndView("redirect:/user/personalAccount/" + interest.getUser().getId());
    }

    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") Long id) {
        ModelAndView projectInfo = new ModelAndView("interest-info");
        Interest interest = interestService.findOne(id);
        projectInfo.addObject("interest", interest);
        LOGGER.info("Building info page for " +interest);
        return projectInfo;
    }

    @GetMapping("{user_id}/{id}/delete")
    public ModelAndView delete(@PathVariable("user_id") Long user_id, @PathVariable("id") Long id) {
        interestService.deleteInterestFromUser(id, user_id);
        LOGGER.info("Redirecting to personal account page after deleting interest with id='{}'", id);
        return new ModelAndView("redirect:/user/personalAccount/" + user_id);
    }


    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Interest interest = interestService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("interest-update-form", "command", interest);
        modelAndView.addObject("countries", Country.values());
        modelAndView.addObject("industries", Industry.values());
        return modelAndView;
    }

    @PostMapping("/{id}/update/")
    public ModelAndView update(@PathVariable("id") Long id, @ModelAttribute("command") Interest interest) throws IOException {
        interestService.save(interest);
        return new ModelAndView("redirect:/user/personalAccount/" + interest.getUser().getId());
    }
}
