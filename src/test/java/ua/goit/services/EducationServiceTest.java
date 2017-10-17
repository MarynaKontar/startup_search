package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.dao.EducationDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Education;
import ua.goit.entity.User;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by User on 15.10.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {TestServicesConfiguration.class})
@DirtiesContext (classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EducationServiceTest {

    @Autowired
    private EducationDao educationDao;
    @Autowired
    private UserDao userDao;

    private EducationService educationService;

    @Before
    public void setUp() {
        educationService = new EducationService(educationDao, userDao);
    }


    @Test
    public void save() throws Exception {

        Education education = getEducation();

        assertNull(educationService.findOne(5L));
        educationService.save(education);
        assertNotNull(educationService.findOne(5L));


    }

    @Test
    public void findOne() throws Exception {
        Education education = educationService.findOne(1L);
        assertEquals(1, (long)education.getId());
    }


    private Education getEducation () {
        User user = new User();
        user.setId(1L);

        Education education = new Education();
        education.setUser(user);
        education.setUntilDate(LocalDate.MIN);
        education.setFromDate(LocalDate.MAX);

        return education;
    }

}