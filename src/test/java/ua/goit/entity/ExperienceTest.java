package ua.goit.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Test for {@link Experience}
 */
@RunWith(MockitoJUnitRunner.class)
public class ExperienceTest {
    @Mock
    User user1;

    @Mock
    User user2;

    @Test
    public void creationTest () {
        Experience experience = new Experience("comp",
                "pos",
                "resp",
                LocalDate.MIN,
                LocalDate.MAX);

        assertEquals("comp", experience.getCompany());
        assertEquals("pos", experience.getPosition());
        assertEquals("resp", experience.getResponsibility());
        assertEquals(LocalDate.MIN, experience.getFromDate());
        assertEquals(LocalDate.MAX, experience.getUntilDate());

    }

    @Test
    public void hashCodeTest(){
        Experience experience1 = new Experience();
        experience1.setId(1L);
        experience1.setUser(user1);
        experience1.setPosition("pos");
        experience1.setCompany("comp");
        experience1.setResponsibility("resp");
        experience1.setFromDate(LocalDate.MIN);
        experience1.setUntilDate(LocalDate.MAX);


        Experience experience2 = new Experience();
        experience2.setId(1L);
        experience2.setUser(user1);
        experience2.setPosition("pos");
        experience2.setCompany("comp");
        experience2.setResponsibility("resp");
        experience2.setFromDate(LocalDate.MIN);
        experience2.setUntilDate(LocalDate.MAX);

        assertEquals(experience1, experience2);

        experience2.setId(2L);
        assertNotEquals(experience1.hashCode(), experience2.hashCode());
    }

    @Test
    public void equalsTest () {
        Experience experience1 = new Experience();
        experience1.setId(1L);
        experience1.setUser(user1);
        experience1.setCompany("comp");
        experience1.setPosition("pos");
        experience1.setResponsibility("resp");
        experience1.setFromDate(LocalDate.MIN);
        experience1.setUntilDate(LocalDate.MAX);


        Experience experience2 = new Experience();
        experience2.setId(1L);
        experience2.setUser(user1);
        experience2.setCompany("comp");
        experience2.setPosition("pos");
        experience2.setResponsibility("resp");
        experience2.setFromDate(LocalDate.MIN);
        experience2.setUntilDate(LocalDate.MAX);

        assertReflectionEquals(experience1, experience2);

        assertTrue(experience1.equals(experience2));

        experience2.setId(2L);
        assertFalse(experience1.equals(experience2));
        experience2.setId(1L);
        assertTrue(experience1.equals(experience2));

        experience2.setUser(user2);
        assertFalse(experience1.equals(experience2));
    }

}