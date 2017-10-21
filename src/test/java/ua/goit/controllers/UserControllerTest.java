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
import ua.goit.configuration.TestControllersConfiguration;
import ua.goit.configuration.WebConfiguration;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.UserService;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for {@link UserController}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class
        , SpringSecurityConfiguration.class
        , TestControllersConfiguration.class})
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

    private User user;
    private Long id;

    @Before
    public void setUp() throws Exception {
        user = mock(User.class);
        id = 1L;
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void viewPersonalAccount() throws Exception {
        Mockito.when(userService.findOne(id)).thenReturn(user);
        Mockito.when(userService.exists(id)).thenReturn(true);
        mvc.perform(get("/user/personalAccount/{id}", "1")
                .with(user("user").roles("USER", "ADMIN")))
                .andExpect(model().attribute("user", userService.findOne(id)))
                .andExpect(view().name("personalAccount"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        Mockito.when(userService.exists(id)).thenReturn(true);
        mvc.perform(get("/user/personalAccount/{current_id}/{id}/delete", "1", "1")
                .with(user("user").roles("ADMIN", "USER")))
                .andExpect(redirectedUrl("/logout"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void editTest() throws Exception {
        Mockito.when(userService.findOne(id)).thenReturn(user);
        Mockito.when(userService.exists(id)).thenReturn(true);
        mvc.perform(get("/user/personalAccount/{id}/edit", id)
                .with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("user", userService.findOne(id)))
                .andExpect(model().attribute("countries", Country.values()))
                .andExpect(model().attribute("industries", Industry.values()))
                .andExpect(view().name("personalAccount-update-form"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        Mockito.when(userService.exists(id)).thenReturn(true);
        Mockito.when(user.getId()).thenReturn(id);
        Mockito.when(userService.findOne(id)).thenReturn(user);
        mvc.perform(post("/user/personalAccount/{id}/update", id)
                .with(user("user").roles("ADMIN", "USER"))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .flashAttr("user", user)
                .param("password", "password"))
                .andExpect(model().attribute("user", user))
                .andExpect(redirectedUrl("/user/personalAccount/" + user.getId()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void viewUsersTest() throws Exception {
        when(userService.findAll()).thenReturn(Collections.singletonList(user));
        mvc.perform(get("/user/users").with(user("user").roles("ADMIN")))
                .andExpect(model().attribute("users", equalTo(userService.findAll())))
                .andExpect(view().name("users"))
                .andExpect(status().isOk());
    }
}