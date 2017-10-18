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
    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findAllByIndustry() throws Exception {
    }

    @Test
    public void findAllByAddress_Country() throws Exception {
    }

    @Test
    public void findAllByIndustryAndAddress_Country() throws Exception {
    }

    @Test
    public void findProjectsByOrderByLastChangeDesc() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void exists() throws Exception {
    }

    @Test
    @Transactional
    public void deleteProjectFromUser() throws Exception {
    }

}