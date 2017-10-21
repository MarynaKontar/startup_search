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
import ua.goit.configuration.TestControllersConfiguration;
import ua.goit.configuration.WebConfiguration;
import ua.goit.entity.Project;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test for {@link ProjectController}
 * @author Vitalii Proskura
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfiguration.class
        , SpringSecurityConfiguration.class
        , TestControllersConfiguration.class})
public class ProjectControllerTest {

    private MockMvc mvc;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

    private Project project;
    private User user;
    private Long id;

    @Before
    public void setUp() throws Exception {
        project = Mockito.mock(Project.class);
        user = Mockito.mock(User.class);
        id = 1L;
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void createStartupTest() throws Exception {
        mvc.perform(get("/startup/create").with(user("user").roles("ADMIN", "USER")))
                .andExpect(view().name("project-create-form"))
                .andExpect(model().attribute("countries", Country.values()))
                .andExpect(model().attribute("industries", Industry.values()))
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest() throws Exception {
        Mockito.when(user.getId()).thenReturn(id);
        Mockito.when(project.getUser()).thenReturn(user);
        mvc.perform(post("/startup/create/")
                        .with(user("user")
                        .roles("ADMIN", "USER"))
                        .flashAttr("project", project))
                .andExpect(model().attribute("project", project))
                .andExpect(redirectedUrl("/user/personalAccount/" + project.getUser().getId()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void infoTest() throws Exception {
        Mockito.when(projectService.exists(id)).thenReturn(true);
        Mockito.when(projectService.findOne(1L)).thenReturn(project);
        mvc.perform(get("/startup/{id}", 1).with(user("user").roles("ADMIN", "USER")))
                .andExpect(model().attribute("project", projectService.findOne(1L)))
                .andExpect(view().name("project-info"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        Mockito.when(projectService.exists(id)).thenReturn(true);
        Mockito.when(userService.exists(id)).thenReturn(true);
        mvc.perform(get("/startup/{user_id}/{id}/delete", id, id)
                .with(user("user")
                        .roles("ADMIN", "USER")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/personalAccount/" + id));
    }

    @Test
    public void editTest() throws Exception {
        Mockito.when(projectService.exists(id)).thenReturn(true);
        Mockito.when(projectService.findOne(id)).thenReturn(project);
        mvc.perform(get("/startup/{id}/edit", id)
                .with(user("user")
                        .roles("ADMIN", "USER")))
                .andExpect(model().attribute("command", projectService.findOne(id)))
                .andExpect(model().attribute("countries", Country.values()))
                .andExpect(model().attribute("industries", Industry.values()))
                .andExpect(view().name("startup-update-form"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        Mockito.when(projectService.exists(id)).thenReturn(true);
        Mockito.when(project.getUser()).thenReturn(user);
        Mockito.when(user.getId()).thenReturn(id);
        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(id);
        Mockito.when(project.getUser()).thenReturn(user);
        mvc.perform(post("/startup/{id}/update/", id)
                .with(user("user")
                        .roles("ADMIN", "USER"))
                .flashAttr("command", project))
                .andExpect(model().attribute("command", project))
                .andExpect(redirectedUrl("/user/personalAccount/" + project.getUser().getId()))
                .andExpect(status().is3xxRedirection());
    }
}