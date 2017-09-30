package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.BusinessPlan;
import ua.goit.entity.Project;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.entity.enums.Role;
import ua.goit.services.AddressService;
import ua.goit.services.BusinessPlanService;
import ua.goit.services.ProjectService;

import java.io.IOException;

/**
 * Created by User on 30.09.2017.
 */
@Controller
@RequestMapping("/startup")
public class ProjectController {

    private final ProjectService projectService;
    private final BusinessPlanService businessPlanService;
    private final AddressService addressService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public ProjectController(ProjectService projectService, BusinessPlanService businessPlanService, AddressService addressService) {
        this.projectService = projectService;
        this.businessPlanService = businessPlanService;
        this.addressService = addressService;
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
        LOGGER.info("Project " + project + " saved to database. Redirecting to main page");
        return new ModelAndView("redirect:/user/personalAccount/" + project.getUser().getUsername());
//        return new ModelAndView("redirect:/main");
    }
}
