package ua.goit.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.configuration.SpringSecurityConfiguration;
import ua.goit.configuration.WebConfiguration;
import ua.goit.controllers.configuration.TestControllersConfiguration;
import ua.goit.entity.User;
import ua.goit.services.UserService;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for registration controller
 *
 * @MarynaKontar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class
        , SpringSecurityConfiguration.class
        , TestControllersConfiguration.class})
public class RegistrationControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void loginTest() throws Exception{
        mvc.perform(get("/login").with(anonymous()))
                .andExpect(view().name("login-form"))
                .andExpect(status().isOk());
    }

    @Test
    public void guestRegistrationFormTest() throws Exception {
        mvc.perform(get("/registration/").with(anonymous()))
                .andExpect(view().name("registration-form"))
                .andExpect(status().isOk());
    }

    @Test
    public void guestRegistrationTest() throws Exception {
        mvc.perform(post("/registration/").with(anonymous())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("login", "login")
                .param("password", "password"))
                .andExpect(redirectedUrl("/main"))
                .andExpect(status().isFound());
    }
}
