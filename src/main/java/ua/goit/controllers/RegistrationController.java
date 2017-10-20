package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.User;
import ua.goit.entity.enums.Role;
import ua.goit.services.UserService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for {@link ua.goit.entity.User} registration
 *
 * @KontarMaryna
 */
@Controller
@RequestMapping("/")
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Mapping for url ":/registration/"
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@link java.lang.String}
     * for registration form"
     */
    @GetMapping("registration/")
    public ModelAndView registrationForm() {
        LOGGER.info("Registration form");
        return new ModelAndView("registration-form");
    }

    /**
     * Mapping for url ":/registration/"
     * Saves {@link User} to database
     *
     * @param user to save
     * @return redirect link to login page
     * @throws IOException if {@link ua.goit.entity.User} was not saved in the database
     */
    @PostMapping("registration/")
    public ModelAndView save(@ModelAttribute("user") User user) throws IOException {
        List<String> usernames = userService.findAll().stream().map(User::getUsername).collect(Collectors.toList());
        if (usernames.contains(user.getUsername())) {
            LOGGER.info("Login " + user.getUsername() + " already exists");
            return new ModelAndView("redirect:/registrationAfterMissingLogin/");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(Role.USER));
        try {
            userService.save(user);
        } catch (Exception e) {
            LOGGER.info("User " + user + " didn't save to database.");
            throw new IOException("Exception during saving user to database", e);
        }
        LOGGER.info("User " + user + " saved to database. Redirecting to login page after registration and than to main page");
        return new ModelAndView("redirect:/login");
    }

    /**
     * Mapping for url ":/registrationAfterMissingLogin/"
     * @return a {@link ModelAndView} object holding the name of jsp for registration form after missing login
     */
    @GetMapping("registrationAfterMissingLogin/")
    public ModelAndView registrationAfterMissingLogin() {
        LOGGER.info("Registration form after missing login");
        return new ModelAndView("registration-form-missing-login");
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
