package ua.goit.entity;

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

    @Test
    public void creationTest () {
        Address address = Mockito.mock(Address.class);
        BusinessPlan businessPlan = Mockito.mock(BusinessPlan.class);

        Project project = new Project(
                "name",
                BigDecimal.TEN,
                BigDecimal.ONE,
                Industry.AGRICULTURE,
                address,
                "desc",
                LocalDate.MAX
        );

        assertEquals(true, project.isActive());
        assertEquals("name", project.getName());
        assertEquals(BigDecimal.TEN, project.getFunds());
        assertEquals(BigDecimal.ONE, project.getMinInvestment());
        assertEquals(Industry.AGRICULTURE, project.getIndustry());
        assertEquals(address, project.getAddress());
        assertEquals("desc", project.getDescription());
        assertEquals(LocalDate.MAX, project.getLastChange());
    }

    @Test
    public void hashCodeTest () {
        Address address = Mockito.mock(Address.class);
        BusinessPlan businessPlan = Mockito.mock(BusinessPlan.class);
        User user = Mockito.mock(User.class);

        Project project1 = new Project();
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

        Project project2 = new Project();
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


        assertEquals(project1.hashCode(), project2.hashCode());
        project2.setId(2L);
        assertNotEquals(project1.hashCode(), project2.hashCode());

    }

    @Test
    public void equalsTest () {
        Address address = Mockito.mock(Address.class);
        BusinessPlan businessPlan = Mockito.mock(BusinessPlan.class);
        User user = Mockito.mock(User.class);

        Project project1 = new Project();
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

        Project project2 = new Project();
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

        assertTrue(project1.equals(project2));

        project2.setId (2L);
        assertFalse(project1.equals(project2));
    }

}