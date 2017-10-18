package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.dao.AddressDao;
import ua.goit.dao.BusinessPlanDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Project;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by User on 18.10.2017.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServicesConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProjectServiceTest {
    @Autowired
    private ProjectDao dao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private BusinessPlanDao businessPlanDao;
    @Autowired
    private UserDao userDao;

    private ProjectService projectService;

    @Before
    public void setUp() throws Exception {
        projectService = new ProjectService(dao, addressDao, businessPlanDao, userDao);

    }

    @Test
    @Transactional
    public void save() throws Exception {
        assertEquals(3, projectService.findAll().size());
        Project project = createProject();
        projectService.save(project);
        assertEquals(4, projectService.findAll().size());

    }

    @Test
    public void findAll() throws Exception {
        assertEquals(3, projectService.findAll().size());
    }

    @Test
    public void findAllByIndustry() throws Exception {
        Industry industry = Industry.AGRICULTURE;
        assertEquals(1, projectService.findAllByIndustry(industry).size());

        industry = Industry.FISHING;
        assertEquals(0, projectService.findAllByIndustry(industry).size());

    }

    @Test
    public void findAllByAddress_Country() throws Exception {
        Country country = Country.AFGHANISTAN;
        List<Project> projectList = projectService.findAllByAddress_Country(country);
        assertEquals(3, projectList.size());
    }

    @Test
    public void findAllByIndustryAndAddress_Country() throws Exception {
        Industry industry = Industry.AGRICULTURE;
        Country country = Country.AFGHANISTAN;
        List<Project> projectList = projectService.findAllByIndustryAndAddress_Country(industry, country);
        assertEquals(1, projectList.size());

    }

    @Test
    public void findProjectsByOrderByLastChangeDesc() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
        assertEquals(1, (long)projectService.findOne(1L).getId());
    }

    @Test
    public void exists() throws Exception {
        assertTrue(projectService.exists(1L));
    }

    @Test
    @Transactional
    public void deleteProjectFromUser() throws Exception {
        User user = userDao.findOne(1L);
        assertEquals(1, user.getProjects().size());
        projectService.deleteProjectFromUser(1L, 1L);
        assertEquals(0, user.getProjects().size());

    }

    private Project createProject() {
        Project project = new Project();
        project.setId(4L);
        project.setName("name4");
        project.setUser(userDao.findOne(1L));
        project.setLastChange(LocalDate.MAX);

        return project;
    }

}