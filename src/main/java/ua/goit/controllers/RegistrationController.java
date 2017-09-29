package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Role;
import ua.goit.services.UserService;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by User on 20.09.2017.
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

    @ModelAttribute("roles")
    public Role[] industries() {
        return Role.values();
    }

    @ModelAttribute("countries")
    public Country[] countries() {
        return Country.values();
    }

    /**
     * Mapping for url ":/registration/"
     * Saves {@link User} to database
     * @param user to save
     * @return redirect link to login page and than to main page
     * @throws IOException if throws IOException during saving user to database
     */
    @PostMapping("registration/")
//    @RequestMapping(value = "registration/", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("user") User user) throws IOException {
        //TODO При пустых полях формы переходит на /save, а не на "redirect:/registration_form". Исправить
        //сюда приходит пустой user, а не null, т.е. проверку или на странице делать или здесь все поля
        if (user.getUsername() == null || user.getPassword() == null || user.getContact().getEmail() == null) {
            LOGGER.info("User " + user + " didn't save to database. Some fields from the form are empty.");
            return new ModelAndView("redirect:/registration/");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(Role.ADMIN));//TODO !!!!изменить роль на USER
        try {
            userService.save(user);
        } catch (Exception e) {
            LOGGER.info("User " + user + " didn't save to database.");
            throw new IOException("Exception during saving user to database", e);
        }
        LOGGER.info("User " + user + " saved to database. Redirecting to login page after registration and than to main page");
//            return new ModelAndView("redirect:/main");
        return new ModelAndView("redirect:/");
    }

//    /**
//     * Mapping for url ":/registration/"
//     * Saves {@link User} to database
//     * @param login    login from the form
//     * @param password password from the form
//     * @param email    email from the form
//     * @return redirect link to login page
//     */
//    @PostMapping("registration/")
//    public String registration(@RequestParam String login, @RequestParam String password, @RequestParam String email) {
//        User user = new User();
//        user.setUsername(login);
//        user.setPassword(passwordEncoder.encode(password));
//        user.getContact().setEmail(email);
//        userService.save(user);
//        LOGGER.info("User " + user + " saved to database. Redirecting to login page after registration");
//        return "redirect:/login";
//    }

//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<String> handleException(IOException ex) {
//        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
}
