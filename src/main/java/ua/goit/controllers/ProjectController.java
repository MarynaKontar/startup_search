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
 * Created by User on 30.09.2017.
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

    @ModelAttribute("industries")
    public Industry[] industries() {
        return Industry.values();
    }

    @ModelAttribute("countries")
    public Country[] countries() {
        return Country.values();
    }

    @GetMapping("/create")
    public ModelAndView createStartup() {
        ModelAndView modelAndView = new ModelAndView("project-create-form");
        modelAndView.addObject("countries", countries());
        modelAndView.addObject("industries", industries());
        modelAndView.addObject("address", addressService.findAll());
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
        return new ModelAndView("redirect:/user/personalAccount/" + project.getUser().getUsername());
    }

    @GetMapping("/{id}")
    public ModelAndView info(@PathVariable("id") Long id) {
        ModelAndView projectInfo = new ModelAndView("project-info");
        Project project = projectService.findOne(id);
        projectInfo.addObject("project", project);
        LOGGER.info("Building info page for " + project);
        return projectInfo;
    }

    @GetMapping("{username}/{id}/delete")
    public ModelAndView delete(@PathVariable("username") String username, @PathVariable("id") Long id) {
        projectService.deleteProjectFromUser(id, username);
        LOGGER.info("Redirecting to personal account page after deleting startup with id='{}'", id);
        //TODO ссылке выходит /user/personalAccount/{username}?{все industries и все countries} . Видимо связано с @ModelAttribute
        return new ModelAndView("redirect:/user/personalAccount/" + username);
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
        Map<String,? super Object> modelMap = new HashMap<>();
        modelMap.put("command", project);
        modelMap.put("businessPlans", project.getBusinessPlan());
        ModelAndView modelAndView = new ModelAndView("startup-update-form", modelMap);
//        modelAndView.addObject("businessplans", project.getBusinessPlan());
        modelAndView.addObject("countries", countries());
        modelAndView.addObject("industries", industries());
        return modelAndView;
    }

    @PostMapping("/update/")
    public ModelAndView update(@ModelAttribute("command") Project project, @ModelAttribute("businessPlan")BusinessPlan businessPlan) throws IOException {
//        Project project = projectService.findOne(id);
//        Hibernate.initialize(project.getBusinessPlan());
        project.setBusinessPlan(businessPlan);
        projectService.save(project);
        return new ModelAndView("redirect:/user/personalAccount/" + project.getUser().getUsername());
    }
}
