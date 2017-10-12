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
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for {@link ua.goit.entity.User}
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

    //TODO знаю, что проверку на наличие вводимого логина надо делать на стороне view,
    // но у меня пока не вышло использовать "usernames" в <script function, поэтому сделала "костыль" в виде
    // переадресации на страницу регистрации с выводом предупреждения в случаи совпадения логинов
    @GetMapping("registration/")
    public ModelAndView registrationForm() {
        LOGGER.info("Registration form");
        return new ModelAndView("registration-form", "usernames",
                userService.findAll().stream().map(User::getUsername).collect(Collectors.toList()));
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
        //TODO сделала проверку прямо на view. Надо ли оставлять проверку здесь? Вдруг будет меняться view и это не учтут, а с моей стороны должен быть контроллер, который учитывает все
        if (user.getUsername() == null || user.getPassword() == null || user.getContact().getEmail() == null) {
            LOGGER.info("User " + user + " didn't save to database. Some fields from the form are empty.");
            //TODO redirect:/error
            return new ModelAndView("redirect:/registration/");
        }

        List<String> usernames = userService.findAll().stream().map(User::getUsername).collect(Collectors.toList());
        if (usernames.contains(user.getUsername())) {
            LOGGER.info("Login " + user.getUsername() + " already exists");
            return new ModelAndView("redirect:/registrationAfterMissingLogin/");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(Role.USER));
        try {
            userService.save(user);
        } catch (Exception e) {
            LOGGER.info("User " + user + " didn't save to database.");
            throw new IOException("Exception during saving user to database", e);
        }
        LOGGER.info("User " + user + " saved to database. Redirecting to login page after registration and than to main page");
        return new ModelAndView("redirect:/main");
    }

    //TODO знаю, что проверку на наличие вводимого логина надо делать на стороне view,
    // но у меня пока не вышло использовать "usernames" в <script function, поэтому сделала "костыль" в виде
    // переадресации на страницу регистрации с выводом предупреждения в случаи совпадения логинов
    @GetMapping("registrationAfterMissingLogin/")
    public ModelAndView registrationAfterMissingLogin() {
        LOGGER.info("Registration form after missing login");
        return new ModelAndView("registration-form-missing-login");
    }

    //TODO в чем разница между двумя способами для @PostMapping("registration/") - через @ModelAttribute и @RequestParam? Какой лучше?
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
