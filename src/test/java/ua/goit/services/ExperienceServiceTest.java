package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.dao.ExperienceDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Experience;
import ua.goit.entity.User;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by User on 16.10.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestServicesConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class ExperienceServiceTest {

    @Autowired
    private ExperienceDao experienceDao;
    @Autowired
    private  UserDao userDao;

    private ExperienceService experienceService;

    @Before
    public void setUp () {
        experienceService = new ExperienceService(experienceDao, userDao);
    }


    @Test
    public void save() throws Exception {
        Experience experience = experienceService.save(getExperience());
        assertEquals(4, (long)experience.getId());
    }

    @Test
    public void findOne() throws Exception {
        Experience experience = experienceService.findOne(1L);
        assertEquals(1L, (long)experience.getId());
    }

    @Test
    @Transactional
    public void deleteEducationFromUser() {
        User user = userDao.findOne(1L);
        assertEquals(1, user.getExperiences().size());
        experienceService.deleteEducationFromUser(1L, 1L);
        assertEquals(0, user.getExperiences().size());

    }


    private Experience getExperience () {
        User user = new User();
        user.setId(3L);
        user.setUsername("user3");
        Experience experience = new Experience();
        experience.setId(4L);
        experience.setCompany("company5");
        experience.setUser(user);
        experience.setResponsibility("responsibility5");
        experience.setPosition("position5");
        experience.setFromDate(LocalDate.MIN);
        experience.setUntilDate(LocalDate.MAX);
        return experience;
    }

}