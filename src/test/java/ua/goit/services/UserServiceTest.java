package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
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
        String password = "aaa";


        assertEquals("pass1", userService.findOne(1L).getPassword());
        userService.update(user, 1L, password);
        assertEquals(passwordEncoder.encode("aaa"), userService.findOne(1L).getPassword());

    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findUserByUsername() throws Exception {
    }

    @Test
    public void getOne() throws Exception {
    }

    @Test
    public void exists() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void delete1() throws Exception {
    }

    @Test
    public void deletePersonalAccount() throws Exception {
    }

}