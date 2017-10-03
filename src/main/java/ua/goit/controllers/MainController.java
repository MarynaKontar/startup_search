package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.Project;
import ua.goit.entity.User;
import ua.goit.services.EducationService;
import ua.goit.services.ExperienceService;
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
    private final ExperienceService experienceService;
    private final EducationService educationService;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public MainController(UserService userService, ProjectService projectService,
                          ExperienceService experienceService, EducationService educationService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.projectService = projectService;
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
        return new ModelAndView("/index", "projects", projectService.findAll());
    }


    /**
     * Mapping for url ":/"
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * and {@link java.util.List} of projects from database
     */
    @GetMapping("main")
    public ModelAndView main() {
        LOGGER.info("Building main page after login");
        return new ModelAndView("main-after-login", "projects", projectService.findAll());
    }


    @PostConstruct
    public void initDefaultUsers() {
        InitDefaultEntities.initDefaultUsers(userService, projectService, experienceService, educationService, passwordEncoder);
    }
//
//@PostConstruct
//    public void deleteUser(){
//     userService.delete("user2");
//}
//    @PostConstruct
//    @Transactional
//    public void deleteProject(){
//        Project project = projectService.getOne(105L);
//        projectService.delete(project);
//    }

}
