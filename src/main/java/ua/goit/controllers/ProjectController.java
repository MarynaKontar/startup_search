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
import java.util.*;

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

    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") Long id) {
        ModelAndView projectInfo = new ModelAndView("project-info");
        Project project = projectService.findOne(id);
        projectInfo.addObject("project", project);
        LOGGER.info("Building info page for " + project);
        return projectInfo;
    }

    @GetMapping("{user_id}/{id}/delete")
    public ModelAndView delete(@PathVariable("user_id") Long user_id, @PathVariable("id") Long id) {
        projectService.deleteProjectFromUser(id, user_id);
        LOGGER.info("Redirecting to personal account page after deleting startup with id='{}'", id);
        return new ModelAndView("redirect:/user/personalAccount/" + user_id);
    }

//    @GetMapping("/{id}/edit")
//    public ModelAndView edit(@PathVariable("id") Long id) {
//        ModelAndView modelAndView = new ModelAndView("project-update-form");
//        modelAndView.addObject("project", projectService.findOne(id));
//        modelAndView.addObject("countries", countries());
//        modelAndView.addObject("industries", industries());
//        return modelAndView;
//    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Project project = projectService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("startup-update-form", "command",project);
        modelAndView.addObject("countries", Country.values());
        modelAndView.addObject("industries", Industry.values());
        return modelAndView;
    }

    @PostMapping("/{id}/update/")
    public ModelAndView update(@PathVariable("id") Long id, @ModelAttribute("command") Project project, @ModelAttribute("businessPlan")BusinessPlan businessPlan) throws IOException {
        project.setPhoto( "projectPhoto"+ id + ".jpg");
        projectService.save(project);
        return new ModelAndView("redirect:/user/personalAccount/" + project.getUser().getId());
    }
}
