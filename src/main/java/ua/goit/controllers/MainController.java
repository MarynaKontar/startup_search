package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.User;
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;
import ua.goit.util.InitDefaultEntities;

import javax.annotation.PostConstruct;

/**
 * Created by User on 29.09.2017.
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final ProjectService projectService;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public MainController(UserService userService, ProjectService projectService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.projectService = projectService;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Mapping for url ":/"
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of last 10 startups from database
     */
    @GetMapping
    public ModelAndView index() {
        LOGGER.info("Building start page");
        return new ModelAndView("/index", "projects", projectService.findAll());
    }
//
//    @PostConstruct
//    public void initDefaultUsers() {
//        InitDefaultEntities.initDefaultUsers(userService, projectService, passwordEncoder);
//    }

//@PostConstruct
//    public void deleteUser(){
//     userService.delete("user1");
//}


}
