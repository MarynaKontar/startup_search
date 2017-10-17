package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.dao.InterestDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Interest;
import ua.goit.entity.User;

import java.awt.print.Pageable;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by User on 16.10.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration (classes = {TestServicesConfiguration.class})
@DirtiesContext (classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class InterestServiceTest {

    private InterestService interestService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private InterestDao interestDao;


    @Before
    public void setUp() throws Exception {
        interestService = new InterestService(interestDao, userDao);
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(3, interestService.findAll().size());
    }

    @Test
    public void findAllByPage() throws Exception {
        PageRequest page = new PageRequest(1, 2);
        assertEquals(2, interestService.findAll(page).getSize());
    }


    @Test
    public void save() throws Exception {
        assertEquals(3, interestService.findAll().size());
        interestService.save(getInterest());
        assertEquals(4, interestService.findAll().size());
    }

    @Test
    public void findOne() throws Exception {
        assertEquals(1, (long)interestService.findOne(1L).getId());
    }



    private Interest getInterest () {
        User user = new User();
        user.setId(1L);

        Interest interest = new Interest();
        interest.setName("name4");
        interest.setUser(user);
        interest.setBudget(1000);
        interest.setLastChange(LocalDate.MAX);

        return interest;
    }

}