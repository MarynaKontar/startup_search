package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.dao.InterestDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Interest;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

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
    public void save() throws Exception {
        assertEquals(3, interestService.findAll().size());
        interestService.save(getInterest());
        assertEquals(4, interestService.findAll().size());
    }

    @Test
    public void findOne() throws Exception {
        assertEquals(1, (long)interestService.findOne(1L).getId());
    }

    @Test
    @Transactional
    public void deleteInterestFromUserTest () {
        User user = userDao.findOne(1L);
        assertEquals(1, user.getInterests().size());
        interestService.deleteInterestFromUser(1L, 1L);
        assertEquals(0, user.getInterests().size());

    }

    @Test
    public void findAllByIndustry () {
        Industry industry = Industry.AGRICULTURE;
        List<Interest> interestList = interestService.findAllByIndustry(industry);
        assertEquals(1, interestList.size());
        assertEquals(Industry.AGRICULTURE, interestList.get(0).getIndustry());
    }

    @Test
    public void findAllByCountry () {
        Country country = Country.AFGHANISTAN;
        List<Interest> interestList = interestService.findAllByCountry(country);
        assertEquals(1, interestList.size());
        assertEquals(Country.AFGHANISTAN, interestList.get(0).getCountry());
    }

    @Test
    public void findAllByIndustryAndCountry () {
        Industry industry = Industry.AGRICULTURE;
        Country country = Country.AFGHANISTAN;
        List<Interest> interestList = interestService.findAllByIndustryAndCountry(industry, country);
        assertEquals(1, interestList.size());
        assertEquals(Industry.AGRICULTURE, interestList.get(0).getIndustry());
        assertEquals(Country.AFGHANISTAN, interestList.get(0).getCountry());
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