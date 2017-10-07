package ua.goit.entity;

import org.junit.Test;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by User on 06.10.2017.
 */
public class ExperienceTest {

    @Test
    public void creationTest () {
        Experience experience = new Experience("comp",
                "pos",
                "resp",
                LocalDate.MIN,
                LocalDate.MAX);

        assertEquals("pos", experience.getPosition());
        assertEquals("resp", experience.getResponsibility());
        assertEquals(LocalDate.MIN, experience.getFromDate());
        assertEquals(LocalDate.MAX, experience.getUntilDate());
    }

    @Test
    public void hashCodeTest(){
        Experience experience1 = new Experience();
        experience1.setId(1L);
        experience1.setPosition("pos");
        experience1.setResponsibility("resp");
        experience1.setFromDate(LocalDate.MIN);
        experience1.setUntilDate(LocalDate.MAX);


        Experience experience2 = new Experience();
        experience2.setId(1L);
        experience2.setPosition("pos");
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
        experience1.setPosition("pos");
        experience1.setResponsibility("resp");
        experience1.setFromDate(LocalDate.MIN);
        experience1.setUntilDate(LocalDate.MAX);


        Experience experience2 = new Experience();
        experience2.setId(1L);
        experience2.setPosition("pos");
        experience2.setResponsibility("resp");
        experience2.setFromDate(LocalDate.MIN);
        experience2.setUntilDate(LocalDate.MAX);

        assertTrue(experience1.equals(experience2));

        experience2.setId(2L);
        assertFalse(experience1.equals(experience2));
    }

}