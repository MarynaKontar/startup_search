package ua.goit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.User;
import ua.goit.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 21.08.2017.
 */
@Controller
@RequestMapping(value = "/user")
public class ShowUsersController {

    private final UserService usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ShowUsersController(UserService usersService, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/show")
    public ModelAndView showUsers() {
        List<String> usernames =
                usersService.findAll().stream().map(User::toString).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", usernames);
        return modelAndView;
    }



//    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public void createUser(@RequestParam("username") @NotEmpty String username,
//                           @NotEmpty String password,
//                           @RequestParam(required = false) String email
////                           ,@RequestHeader(value = "x", required = false) String x,
////                           @CookieValue(value = "y", required = false) String y
//                            ) throws IOException {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setEmail(email);
//        usersService.save(user);
//    }
//
//
//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    public String logout() {
//        // http://localhost:8080/login?logout
//        return "redirect:/login?logout";
//    }
//
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<String> handleIOException(IOException ex) {
//        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
//    }
}
