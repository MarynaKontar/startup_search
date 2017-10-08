package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.entity.enums.Industry;
import ua.goit.services.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for searching
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/projectsByIndustry/{industry}")
    public ModelAndView searchProjectsByIndustry(@PathVariable("industry") String industry) {
        LOGGER.info("Building searching page for projects by industry");
        Map<String,? super Object> map = new HashMap<>();
        map.put("projectsByIndustry", searchService.findAllProjectsByIndustry(industry.toUpperCase()));
        map.put("industries", Industry.values());
        map.put("industry", industry);
        return new ModelAndView("projects-by-industry", map);
    }

}
