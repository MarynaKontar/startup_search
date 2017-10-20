package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.BusinessPlan;
import ua.goit.entity.Project;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.AddressService;
import ua.goit.services.BusinessPlanService;
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;

import java.io.IOException;

import static ua.goit.controllers.Validation.validateProject;
import static ua.goit.controllers.Validation.validateUser;

/**
 * Controller for {@link ua.goit.entity.Project}
 */
@Controller
@RequestMapping("/startup")
public class ProjectController {

    private final ProjectService projectService;
    private final BusinessPlanService businessPlanService;
    private final AddressService addressService;
    private final UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public ProjectController(ProjectService projectService, BusinessPlanService businessPlanService, AddressService addressService, UserService userService) {
        this.projectService = projectService;
        this.businessPlanService = businessPlanService;
        this.addressService = addressService;
        this.userService = userService;
    }

    /**
     * Mapping for url ":/startup/create"
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@link java.lang.String},
     * and arrays of {@link ua.goit.entity.enums.Country} and {@link ua.goit.entity.enums.Industry}
     */
    @GetMapping("/create")
    public ModelAndView createStartup() {
        ModelAndView modelAndView = new ModelAndView("project-create-form");
        modelAndView.addObject("countries", Country.values());
        modelAndView.addObject("industries", Industry.values());
//        modelAndView.addObject("address", addressService.findAll());
        LOGGER.info("Building new startup form");
        return modelAndView;
    }

    /**
     * Mapping for url ":/startup/create/"
     * Saves {@link Project} to database
     *
     * @param project
     * @return redirect link to personal account page
     * @throws IOException if {@link ua.goit.entity.Project} was not saved in the database
     */
    @PostMapping("/create/")
    public ModelAndView save(@ModelAttribute("project") Project project) throws IOException {
        try {
            projectService.save(project);
        } catch (Exception e) {
            LOGGER.info("Project " + project + " didn't save to database.");
            throw new IOException("Exception during saving project to database", e);
        }
        LOGGER.info("Project " + project + " saved to database. Redirecting to personal account page");
        return new ModelAndView("redirect:/user/personalAccount/" + project.getUser().getId());
    }

    /**
     * Mapping for url ":/startup/{id}"
     * @param id the id of startup from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String},
     * @throws Exception if startup with id doesn't exists
     */
    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") Long id) throws Exception {
        validateProject(id, projectService);
        ModelAndView projectInfo = new ModelAndView("project-info");
        Project project = projectService.findOne(id);
        projectInfo.addObject("project", project);
        LOGGER.info("Building info page for " + project);
        return projectInfo;
    }

    /**
     * Mapping for url ":/startup/{user_id}/{id}/delete"
     * Method deletes {@link Project with chosen id from database
     * @param user_id the id of user that delete startup from url
     * @param id the id of startup to delete from url
     * @return a {@link ModelAndView} object holding the name of jsp for redirect to personal account
     * page with id=user_id
     * @throws Exception if user with user_id doesn't exists or startup with id doesn't exists
     */
    @GetMapping("{user_id}/{id}/delete")
    public ModelAndView delete(@PathVariable("user_id") Long user_id, @PathVariable("id") Long id) throws Exception {
        validateUser(user_id, userService);
        validateProject(id, projectService);
        projectService.deleteProjectFromUser(id, user_id);
        LOGGER.info("Redirecting to personal account page after deleting startup with id='{}'", id);
        return new ModelAndView("redirect:/user/personalAccount/" + user_id);
    }

    /**
     * Mapping for url ":/startup/{id}/edit"
     * Method take project with id from database and sends it to {@link Project} update form
     * @param id the id of startup to update from url
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@code String} and
     * interest to update, arrays of all {@link Country} and {@link Industry}
     * @throws Exception if startup with id doesn't exists
     */
    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) throws Exception {
        validateProject(id, projectService);
        Project project = projectService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("startup-update-form", "command",project);
        modelAndView.addObject("countries", Country.values());
        modelAndView.addObject("industries", Industry.values());
        return modelAndView;
    }

    /**
     * Mapping for url ":/startup/{id}/update/"
     * Method updates {@link Project} in database with parameters which come from {@link Project} update form
     * @param id the id of startup to update from url
     * @param project for updating
     * @return a {@link ModelAndView} object holding the name of jsp for redirect to personal account
     * page with id=user_id
     * @throws Exception if startup with id doesn't exists
     */
    @PostMapping("/{id}/update/")
    public ModelAndView update(@PathVariable("id") Long id, @ModelAttribute("command") Project project, @ModelAttribute("businessPlan")BusinessPlan businessPlan) throws Exception {
        validateProject(id, projectService);
        project.setPhoto( "projectPhoto"+ id + ".jpg");
        projectService.save(project);
        return new ModelAndView("redirect:/user/personalAccount/" + project.getUser().getId());
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
