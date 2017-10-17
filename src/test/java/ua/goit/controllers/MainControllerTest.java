package ua.goit.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.configuration.SpringSecurityConfiguration;
import ua.goit.configuration.TestControllersConfiguration;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.configuration.WebConfiguration;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.InterestService;
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for main controller
 *
 * @MarynaKontar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class
        , SpringSecurityConfiguration.class,
        TestControllersConfiguration.class})
public class MainControllerTest {

    private MockMvc mvc;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private InterestService interestService;
    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void indexTest() throws Exception {
        mvc.perform(get("/").with(anonymous()))
                .andExpect(model().attribute("projects", projectService.findProjectsByOrderByLastChangeDesc()))
                .andExpect(model().attribute("interests", interestService.findInterestsByOrderByLastChangeDesc()))
                .andExpect(model().attribute("industries", Industry.values()))
                .andExpect(model().attribute("countries", Country.values()))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    public void loginTest() throws Exception {
        mvc.perform(get("/login").with(anonymous()))
                .andExpect(view().name("login-form"))
                .andExpect(status().isOk());
    }

    @Test
    public void logoutTest() throws Exception {
        mvc.perform(post("/logout").with(anonymous()))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }

    @Test
    public void authenticatedIndexTest() throws Exception {
        mvc.perform(get("/").with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("projects", projectService.findProjectsByOrderByLastChangeDesc()))
                .andExpect(model().attribute("interests", interestService.findInterestsByOrderByLastChangeDesc()))
                .andExpect(model().attribute("industries", Industry.values()))
                .andExpect(model().attribute("countries", Country.values()))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    public void authenticatedLogoutTest() throws Exception {
        mvc.perform(post("/logout").with(user("user").roles("ADMIN", "USER")))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().isFound());
    }


}