package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.dao.*;
import ua.goit.entity.User;
import ua.goit.entity.enums.Role;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by User on 16.10.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServicesConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    private UserDao dao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private InterestDao interestDao;
    @Autowired
    private EducationDao educationDao;
    @Autowired
    private ExperienceDao experienceDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private BusinessPlanDao businessPlanDao;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(dao,
                projectDao,
                interestDao,
                educationDao,
                experienceDao,
                addressDao,
                businessPlanDao,
                passwordEncoder);
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(3, userService.findAll().size());
    }

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setUsername("name4");
        user.setId(4L);
        user.setPassword("aaaa");

        assertEquals(3, userService.findAll().size());
        userService.save(user);
        assertEquals(4, userService.findAll().size());

    }

    @Test
    public void update() throws Exception {
        User user = userService.findOne(1L);
        String userName = user.getUsername();
        user.setUsername(userName + "aaa");
        user.setLastName("lastName");
        user.setFirstName("firstName");
        user.setAboutMe("aboutMe");

        String password = "aaa";
        assertEquals("pass1", userService.findOne(1L).getPassword());

        userService.update(user, 1L, password);
        assertTrue(passwordEncoder.matches(password, userService.findOne(1L).getPassword()));
        assertEquals(userName + "aaa", userService.findOne(1L).getUsername());
        assertEquals("lastName" , userService.findOne(1L).getLastName());
        assertEquals("firstName" , userService.findOne(1L).getFirstName());
        assertEquals("aboutMe" , userService.findOne(1L).getAboutMe());

    }

    @Test
    public void findOne() throws Exception {
        assertEquals(1, (long)userService.findOne(1L).getId());

    }

    @Test
    public void findUserByUsername() throws Exception {
        User user = userService.findUserByUsername("user1");
        assertEquals("user1", user.getUsername());
    }



    @Test
    @Transactional
    public void deletePersonalAccount() throws Exception {
        assertNotNull(userService.findOne(1L));
        assertEquals(3, interestDao.findAll().size());
        assertEquals(3, experienceDao.findAll().size());
        assertEquals(4, educationDao.findAll().size());
        userService.deletePersonalAccount(1L);
        assertNull(userService.findOne(1L));
        assertEquals(2, interestDao.findAll().size());
        assertEquals(2, experienceDao.findAll().size());
        assertEquals(2, educationDao.findAll().size());




    }

    @Test
    public void userDetailsServiceTest () {
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userService);
        UserDetails userDetails = userDetailsService.loadUserByUsername("user1");
        assertEquals("user1", userDetails.getUsername());

    }

}