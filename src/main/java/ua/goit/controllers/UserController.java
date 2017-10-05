package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.UserService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Controller for {@link ua.goit.entity.User}
 *
 * @KontarMaryna
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("industries")
    public Industry[] industries() {
        return Industry.values();
    }

    @ModelAttribute("countries")
    public Country[] countries() {
        return Country.values();
    }

    @GetMapping("/personalAccount/{id}")
    public ModelAndView viewPersonalAccount(@PathVariable("id") Long id) {
        User user = userService.findOne(id);
        LOGGER.info("Personal account page for " + user);
        return new ModelAndView("personalAccount", "user", user);
    }

    @GetMapping("/personalAccount/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id) {
        userService.deletePersonalAccount(id);
        LOGGER.info("Redirecting to logout page after deleting personal account with username='{}'", id);
        return new ModelAndView("redirect:/logout");
    }

    @GetMapping("/personalAccount/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) {
        User user = userService.findOne(id);
        Map<String,? super Object> map = new HashMap<>();
        map.put("user", user);
        map.put("countries", countries());
        map.put("industries", industries());
        ModelAndView modelAndView = new ModelAndView("personalAccount-update-form", map);
        LOGGER.info("Edit personal account page for " + user);
        return modelAndView;
    }

    @PostMapping("/personalAccount/{id}/update")
    public ModelAndView update(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam(value = "password", required = false) String password) throws IOException {
        userService.update(user, id, password);
        LOGGER.info("Redirecting to personal account page after updating personal account with username='{}'", user.getUsername());
        return new ModelAndView("redirect:/user/personalAccount/"  + id);
    }

    @RequestMapping("/users")
    public ModelAndView viewUsers() {
        List<User> users = userService.findAll();
        return new ModelAndView("users", "users", users);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<String> handleIOException(IOException ex) {
//        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
//    }
}
