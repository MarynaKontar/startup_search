package ua.goit.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.goit.configuration.SpringSecurityConfiguration;
import ua.goit.configuration.TestControllersConfiguration;;
import ua.goit.configuration.WebConfiguration;
import ua.goit.entity.Interest;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.InterestService;
import ua.goit.services.UserService;

import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test for {@link InterestController}
 * @author Vitalii Proskura
 * @author Maryna Kontar
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        WebConfiguration.class
        , SpringSecurityConfiguration.class
        , TestControllersConfiguration.class})
public class InterestControllerTest {

    private MockMvc mvc;

    @Autowired
    private InterestService interestService;

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext context;
    private Interest interest;
    private User user;
    private Long id;

    @Before
    public void setUp() throws Exception {
        interest = mock(Interest.class);
        user = mock(User.class);
        id = 1L;
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void createInterestTest() throws Exception {
        mvc.perform(get("/interest/create").with(user("user").roles("ADMIN", "USER")))
                .andExpect(view().name("interest-create-form"))
                .andExpect(model().attribute("countries", Country.values()))
                .andExpect(model().attribute("industries", Industry.values()))
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest() throws Exception {
        Mockito.when(interest.getId()).thenReturn(id);
        Mockito.when(user.getId()).thenReturn(id);
        Mockito.when(interest.getUser()).thenReturn(user);
        mvc.perform(post("/interest/create/")
                .with(user("user").roles("ADMIN", "USER"))
                .flashAttr("interest", interest))
                .andExpect(model().attribute("interest", interest))
                .andExpect(redirectedUrl("/user/personalAccount/" + interest.getUser().getId()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void infoTest() throws Exception {
        Mockito.when(interestService.exists(id)).thenReturn(true);
        Mockito.when(interestService.findOne(id)).thenReturn(interest);
        mvc.perform(get("/interest/{id}", id)
                .with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("interest", interest))
                .andExpect(view().name("interest-info"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        Mockito.when(interestService.exists(id)).thenReturn(true);
        Mockito.when(userService.exists(id)).thenReturn(true);
        mvc.perform(get("/interest/{userId}/{id}/delete", "1","1")
                .with(user("user")
                        .roles("ADMIN", "USER")))
                .andExpect(redirectedUrl("/user/personalAccount/" + id))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void editTest() throws Exception {
        Mockito.when(interestService.findOne(id)).thenReturn(interest);
        Mockito.when(interestService.exists(id)).thenReturn(true);
        mvc.perform(get("/interest/{id}/edit", id)
                .with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("command", interestService.findOne(id)))
                .andExpect(model().attribute("countries", Country.values()))
                .andExpect(model().attribute("industries", Industry.values()))
                .andExpect(view().name("interest-update-form"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        Mockito.when(interestService.exists(id)).thenReturn(true);
        Mockito.when(interest.getId()).thenReturn(id);
        Mockito.when(user.getId()).thenReturn(id);
        Mockito.when(interest.getUser()).thenReturn(user);
        mvc.perform(post("/interest/{id}/update/", id)
                .with(user("user").roles("ADMIN", "USER"))
                .flashAttr("command", interest))
                .andExpect(model().attribute("command", interest))
                .andExpect(redirectedUrl("/user/personalAccount/" + interest.getUser().getId()))
                .andExpect(status().is3xxRedirection());
    }
}