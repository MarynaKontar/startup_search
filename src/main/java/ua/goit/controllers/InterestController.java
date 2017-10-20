package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.Interest;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.InterestService;
import ua.goit.services.UserService;

import java.io.IOException;

import static ua.goit.controllers.Validation.validateInterest;
import static ua.goit.controllers.Validation.validateUser;

/**
 * Controller for {@link ua.goit.entity.Interest}
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

    /**
     * Mapping for url ":/interest/create"
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@link java.lang.String},
     * and arrays of {@link ua.goit.entity.enums.Country} and {@link ua.goit.entity.enums.Industry}
     */
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
     * @param interest for saving
     * @return redirect link to personal account page
     * @throws IOException if {@link Interest} was not saved to the database
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

    /**
     * Mapping for url ":/interest/{id}"
     * @param id the id of interest from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * @throws Exception if interest with id doesn't exists
     */
    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") Long id) throws Exception {
        validateInterest(id, interestService);
        ModelAndView projectInfo = new ModelAndView("interest-info");
        Interest interest = interestService.findOne(id);
        projectInfo.addObject("interest", interest);
        LOGGER.info("Building info page for " +interest);
        return projectInfo;
    }

    /**
     * Mapping for url ":/interest/{user_id}/{id}/delete"
     * Method deletes {@link Interest} with chosen id from database
     * @param user_id the id of user that delete interest from url
     * @param id the id of interest to delete from url
     * @return a {@link ModelAndView} object holding the name of jsp for redirect to personal account
     * page with id=user_id
     * @throws Exception if user with user_id doesn't exists or interest with id doesn't exists
     */
    @GetMapping("{user_id}/{id}/delete")
    public ModelAndView delete(@PathVariable("user_id") Long user_id, @PathVariable("id") Long id) throws Exception {
        validateUser(user_id, userService);
        validateInterest(id, interestService);
        interestService.deleteInterestFromUser(id, user_id);
        LOGGER.info("Redirecting to personal account page after deleting interest with id='{}'", id);
        return new ModelAndView("redirect:/user/personalAccount/" + user_id);
    }

    /**
     * Mapping for url ":/interest/{id}/edit"
     * Method take interest with id from database and sends it to {@link Interest} update form
     * @param id the id of interest to update from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} and
     * interest to update, arrays of all {@link Country} and {@link Industry}
     * @throws Exception if interest with id doesn't exists
     */
    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) throws Exception {
        validateInterest(id, interestService);
        Interest interest = interestService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("interest-update-form", "command", interest);
        modelAndView.addObject("countries", Country.values());
        modelAndView.addObject("industries", Industry.values());
        return modelAndView;
    }

    /**
     * Mapping for url ":/interest/{id}/update/"
     * Method updates {@link Interest} in database with parameters which come from interest-update-form
     * @param id the id of interest to update from url
     * @param interest for updating
     * @return a {@link ModelAndView} object holding the name of jsp for redirect to personal account
     * page with id=user_id
     * @throws Exception if interest with id doesn't exists
     */
    @PostMapping("/{id}/update/")
    public ModelAndView update(@PathVariable("id") Long id, @ModelAttribute("command") Interest interest) throws Exception {
        validateInterest(id, interestService);
        interestService.save(interest);
        return new ModelAndView("redirect:/user/personalAccount/" + interest.getUser().getId());
    }

    /**
     * Exception handler
     * @param ex exception for handling
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} for error page
     * and exception message
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        return new ModelAndView("/error", "exception", ex.getMessage());
    }
}
