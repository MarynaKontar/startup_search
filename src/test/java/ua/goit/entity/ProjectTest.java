package ua.goit.entity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.goit.entity.enums.Industry;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by User on 08.10.2017.
 */
public class ProjectTest {
    private Project project1;
    private Project project2;
    private Address address;
    private BusinessPlan businessPlan;
    private User user;

    @Test
    public void creationTest() {
        assertEquals(true, project1.isActive());
        assertEquals("name", project1.getName());
        assertEquals(BigDecimal.TEN, project1.getFunds());
        assertEquals(BigDecimal.ONE, project1.getMinInvestment());
        assertEquals(Industry.AGRICULTURE, project1.getIndustry());
        assertEquals(address, project1.getAddress());
        assertEquals("desc", project1.getDescription());
        assertEquals(LocalDate.MAX, project1.getLastChange());
    }

    @Test
    public void hashCodeTest() {
        assertEquals(project1.hashCode(), project2.hashCode());
        project2.setId(2L);
        assertNotEquals(project1.hashCode(), project2.hashCode());

    }

    @Test
    public void equalsTest() {
        assertTrue(project1.equals(project2));
        project2.setId(2L);
        assertFalse(project1.equals(project2));
    }

    @Before
    public void init() {
        address = Mockito.mock(Address.class);
        businessPlan = Mockito.mock(BusinessPlan.class);
        user = Mockito.mock(User.class);
        project1 = new Project();
        project1.setId(1L);
        project1.setName("name");
        project1.setUser(user);
        project1.setFunds(BigDecimal.TEN);
        project1.setMinInvestment(BigDecimal.ONE);
        project1.setIndustry(Industry.AGRICULTURE);
        project1.setAddress(address);
        project1.setDescription("desc");
        project1.setBusinessPlan(businessPlan);
        project1.setLastChange(LocalDate.MAX);
        project1.setActive(true);

        project2 = new Project();
        project2.setId(1L);
        project2.setName("name");
        project2.setUser(user);
        project2.setFunds(BigDecimal.TEN);
        project2.setMinInvestment(BigDecimal.ONE);
        project2.setIndustry(Industry.AGRICULTURE);
        project2.setAddress(address);
        project2.setDescription("desc");
        project2.setBusinessPlan(businessPlan);
        project2.setLastChange(LocalDate.MAX);
        project2.setActive(true);
    }
}