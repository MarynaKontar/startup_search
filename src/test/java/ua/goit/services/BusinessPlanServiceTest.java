package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.dao.BusinessPlanDao;
import ua.goit.entity.BusinessPlan;

import static org.junit.Assert.*;

/**
 * Created by User on 15.10.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {TestServicesConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BusinessPlanServiceTest {

    @Autowired
    BusinessPlanDao businessPlanDao;

    BusinessPlanService businessPlanService;


    @Before
    public void setUp() throws Exception {
        businessPlanService = new BusinessPlanService(businessPlanDao);
    }

    @Test
    public void save() throws Exception {
        assertEquals(3, businessPlanService.findAll().size());
        BusinessPlan businessPlan = new BusinessPlan();
        businessPlanService.save(businessPlan);
        assertEquals(4, businessPlanService.findAll().size());


    }

    @Test
    public void findAll() throws Exception {
        assertEquals(3, businessPlanService.findAll().size());
    }

    @Test
    public void findOne() throws Exception {
        BusinessPlan businessPlan = businessPlanService.findOne(1L);
        assertEquals(1, (long)businessPlan.getId());
    }

    @Test
    public void exists() throws Exception {
        assertTrue(businessPlanService.exists(1L));
        assertTrue(businessPlanService.exists(2L));
        assertTrue(businessPlanService.exists(3L));
        assertFalse(businessPlanService.exists(4L));
    }





}