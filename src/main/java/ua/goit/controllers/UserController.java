package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.UserService;

import java.util.*;

import static ua.goit.controllers.Validation.validateUser;
import static ua.goit.controllers.Validation.validateUserForDelete;

/**
 * Controller for {@link ua.goit.entity.User}
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserController(UserService userService,  PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Mapping for url ":/user/personalAccount/{id}"
     * Method take user with id from database and sends it to {@link User} personal account page
     * @param id the id of {@link User} from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * @throws Exception if user with id doesn't exists
     */
    @GetMapping("/personalAccount/{id}")
    public ModelAndView viewPersonalAccount(@PathVariable("id") Long id) throws Exception {
        validateUser(id, userService);
        User user = userService.findOne(id);
        LOGGER.info("Personal account page for " + user);
        return new ModelAndView("personalAccount", "user", user);
    }

    /**
     * Mapping for url ":/user/personalAccount/{user_id}/{id}/delete"
     * Method deletes {@link User} with chosen id from database
     * @param current_id the id of {@link User} that delete user from url
     * @param id the id of {@link User} to delete from url
     * @return a {@link ModelAndView} object holding the name of jsp for redirect to personal account
     * page with id=user_id
     * @throws Exception if user with user_id doesn't exists or user with id doesn't exists
     */
    @GetMapping("/personalAccount/{current_id}/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id, @PathVariable("current_id") Long current_id) throws Exception {
        validateUser(id, userService);
        validateUserForDelete(id, current_id, userService);
        userService.deletePersonalAccount(id);
        LOGGER.info("Redirecting to logout page after deleting personal account with username='{}'", id);
        if(current_id==id){
        return new ModelAndView("redirect:/logout");}
        else return new ModelAndView("redirect:/");
    }

    /**
     * Mapping for url ":/user/personalAccount/{id}/edit"
     * Method take user with id from database and sends it to {@link User} update form
     * @param id the id of {@link User} to update from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} and
     * {@link User} to update, arrays of all {@link Country} and {@link Industry}
     * @throws Exception if {@link User} with id doesn't exists
     */
    @GetMapping("/personalAccount/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) throws Exception {
        validateUser(id, userService);
        User user = userService.findOne(id);
        Map<String,? super Object> map = new HashMap<>();
        map.put("user", user);
        map.put("countries", Country.values());
        map.put("industries", Industry.values());
        ModelAndView modelAndView = new ModelAndView("personalAccount-update-form", map);
        LOGGER.info("Edit personal account page for " + user);
        return modelAndView;
    }

    /**
     * Mapping for url ":/user/personalAccount/{id}/update/"
     * Method updates {@link User} in database with parameters which come from personalAccount-update-form
     * @param id the id of {@link User} to update from url
     * @param user for updating
     * @return a {@link ModelAndView} object holding the name of jsp for redirect to personal account
     * page with id
     * @throws Exception if user with id doesn't exists
     */
    @PostMapping("/personalAccount/{id}/update")
    public ModelAndView update(@PathVariable("id") Long id
            , @ModelAttribute("user") User user
            , @RequestParam(value = "password", required = false) String password)
            throws Exception {
        validateUser(id, userService);
        user.setProfileFotoLink( "profilePhoto"+user.getId()+".jpg");
        user.setPersonalPageFotoLink( "personalPagePhoto" + user.getId() + ".jpg");
        userService.update(user, id, password);
        LOGGER.info("Redirecting to personal account page after updating personal account with username='{}'", user.getUsername());
        return new ModelAndView("redirect:/user/personalAccount/"  + id);
    }


    /**
     * Mapping for url ":/user/users"
     * Method take {@link User} with id from database and sends it to users page, accessible only to the admin
     * @return a {@link ModelAndView} object holding the name of jsp for users page
     */
    @GetMapping("/users")
    public ModelAndView viewUsers() {
        List<User> users = userService.findAll();
        return new ModelAndView("users", "users", users);
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
