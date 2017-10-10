package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.enums.Industry;
import ua.goit.services.*;
import ua.goit.util.InitDefaultEntities;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Main controller
 *
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final ProjectService projectService;
    private final InterestService interestService;
    private final  ExperienceService experienceService;
    private final EducationService educationService;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public MainController(UserService userService, ProjectService projectService,
                          InterestService interestService, ExperienceService experienceService,
                          EducationService educationService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.projectService = projectService;
        this.interestService = interestService;
        this.experienceService = experienceService;
        this.educationService = educationService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Mapping for url ":/"
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of projects from database
     */
    @GetMapping
    public ModelAndView index() {
        LOGGER.info("Building start page");
        Map<String,? super Object> map = new HashMap<>();
        map.put("projects", projectService.findAll());
        map.put("interests", interestService.findAll());
        return new ModelAndView("index",map);
    }

    /**
     * Mapping for url ":/"
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of projects from database
     */
    @GetMapping("main")
    public ModelAndView main() {
        LOGGER.info("Building main page after login");
        Map<String,? super Object> map = new HashMap<>();
        map.put("projects", projectService.findAll());
        map.put("industries", Industry.values());
        map.put("interests", interestService.findAll());
        return new ModelAndView("main-after-login", map);
    }

//TODO запустить в первый раз для создания исходных юзеров
//    @PostConstruct
//    public void initDefaultUsers() {
//        InitDefaultEntities.initDefaultUsers(userService, projectService, experienceService,
//                educationService, passwordEncoder);
//    }
}
