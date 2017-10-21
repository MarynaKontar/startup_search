package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.Interest;
import ua.goit.entity.Project;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main controller
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final ProjectService projectService;
    private final InterestService interestService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public MainController(ProjectService projectService, InterestService interestService) {
        this.projectService = projectService;
        this.interestService = interestService;
    }

    /**
     * Mapping for url ":/"
     *
     * @return a {@link ModelAndView} object holding the name of jsp represented by {@link java.lang.String},
     * and {@link java.util.Map} of projects and interests limit by {@link Country}
     * or {@link Industry} (or both) or ordered by last change.
     */
    @GetMapping
    public ModelAndView index(@RequestHeader HttpHeaders headers
            , @RequestParam(required = false) Industry projectIndustry
            , @RequestParam(required = false) Country projectCountry
            , @RequestParam(required = false) Industry interestIndustry
            , @RequestParam(required = false) Country interestCountry) {
        LOGGER.info("Building main page for user from " + headers.getFirst(HttpHeaders.HOST));
        Map<String, ? super Object> map = new HashMap<>();

        List<Project> projects;
        List<Interest> interests;

        if (projectIndustry != null) {
            if (projectCountry != null) {
                projects = projectService.findAllByIndustryAndAddress_Country(projectIndustry, projectCountry);
            } else projects = projectService.findAllByIndustry(projectIndustry);
        } else projects = projectService.findAllByAddress_Country(projectCountry);
        if (projectIndustry == null && projectCountry == null
                && (interestIndustry != null || interestCountry != null)
                ) {
            projects = null;}

        if (interestIndustry != null) {
            if (interestCountry != null) {
                interests = interestService.findAllByIndustryAndCountry(interestIndustry, interestCountry);
            } else interests = interestService.findAllByIndustry(interestIndustry);
        } else interests = interestService.findAllByCountry(interestCountry);
        if (interestIndustry == null && interestCountry == null
                && (projectIndustry != null || projectCountry != null)
                ) {
            interests = null;}

        if (interestIndustry == null && interestCountry == null && projectIndustry == null && projectCountry == null){
            projects = projectService.findProjectsByOrderByLastChangeDesc();
            interests = interestService.findInterestsByOrderByLastChangeDesc();
        }

        map.put("projects", projects);
        map.put("interests", interests);
        map.put("industries", Industry.values());
        map.put("countries", Country.values());
        return new ModelAndView("index", map);
    }
}
