package ua.goit.entity;

import org.junit.Before;
import org.junit.Test;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test for {@link Interest}
 */
public class InterestTest {

    private Interest interest1;
    private Interest interest2;

    private User user;
    private User user2;

    @Before
    public void init() {
        user = mock(User.class);
        user2 = mock(User.class);
        interest1 = new Interest();
        interest1.setId(1L);
        interest1.setName("nam");
        interest1.setDescription("des");
        interest1.setBudget(10);
        interest1.setCountry(Country.AFGHANISTAN);
        interest1.setIndustry(Industry.AGRICULTURE);
        interest1.setLastChange(LocalDate.MAX);
        interest1.setUser(user);

        interest2 = new Interest();
        interest2.setId(1L);
        interest2.setName("nam");
        interest2.setDescription("des");
        interest2.setBudget(10);
        interest2.setCountry(Country.AFGHANISTAN);
        interest2.setIndustry(Industry.AGRICULTURE);
        interest2.setLastChange(LocalDate.MAX);
        interest2.setUser(user);

    }

    @Test
    public void creationTest() {
        Interest interest = new Interest("nam",
                "des",
                10,
                Country.AFGHANISTAN,
                Industry.AGRICULTURE,
                LocalDate.MAX);

        assertEquals("nam", interest.getName());
        assertEquals("des", interest.getDescription());
        assertEquals(10, interest.getBudget());
        assertEquals(Country.AFGHANISTAN, interest.getCountry());
        assertEquals(Industry.AGRICULTURE, interest.getIndustry());
        assertEquals(LocalDate.MAX, interest.getLastChange());
    }

    @Test
    public void equalsTest() {
        assertTrue(interest1.equals(interest2));
        interest2.setId(2L);
        assertFalse(interest1.equals(interest2));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(interest1.hashCode(), interest2.hashCode());
        interest2.setUser(user2);
        assertNotEquals(interest1.hashCode(), interest2.hashCode());
    }
}