package ua.goit.entity;

import org.junit.Test;
import ua.goit.entity.enums.ModeOfStudy;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Test for {@link Education}
 */
public class EducationTest {

    @Test
    public void creationTest () {
        Education education = null;

        education = new Education("institution",
                "stage",
                "faculty",
                "field",
                ModeOfStudy.FULL_TIME,
                LocalDate.MAX,
                LocalDate.MIN);

        assertEquals("institution", education.getEducationalInstitution());
        assertEquals("stage", education.getEducationalStage());
        assertEquals("faculty", education.getFaculty());
        assertEquals("field", education.getFieldOfStudy());
        assertEquals(ModeOfStudy.FULL_TIME, education.getModeOfStudy());
        assertEquals(LocalDate.MAX, education.getFromDate());
        assertEquals(LocalDate.MIN, education.getUntilDate());
    }

    @Test
    public void hashCodeTest () {
        Education education1 = new Education();
        education1.setId(1L);
        education1.setEducationalInstitution("institution");
        education1.setEducationalStage("stage");
        education1.setFaculty("faculty");
        education1.setFieldOfStudy("field");
        education1.setModeOfStudy(ModeOfStudy.FULL_TIME);
        education1.setFromDate(LocalDate.MAX);
        education1.setUntilDate(LocalDate.MIN);

        Education education2 = new Education();
        education2.setId(1L);
        education2.setEducationalInstitution("institution");
        education2.setEducationalStage("stage");
        education2.setFaculty("faculty");
        education2.setFieldOfStudy("field");
        education2.setModeOfStudy(ModeOfStudy.FULL_TIME);
        education2.setFromDate(LocalDate.MAX);
        education2.setUntilDate(LocalDate.MIN);

        assertEquals(education1.hashCode(), education2.hashCode());

        education2.setId(2L);
        assertNotEquals(education1.hashCode(), education2.hashCode());

    }

    @Test
    public void equalsTest () {
        Education education1 = new Education();
        education1.setId(1L);
        education1.setEducationalInstitution("institution");
        education1.setEducationalStage("stage");
        education1.setFaculty("faculty");
        education1.setFieldOfStudy("field");
        education1.setModeOfStudy(ModeOfStudy.FULL_TIME);
        education1.setFromDate(LocalDate.MAX);
        education1.setUntilDate(LocalDate.MIN);

        Education education2 = new Education();
        education2.setId(1L);
        education2.setEducationalInstitution("institution");
        education2.setEducationalStage("stage");
        education2.setFaculty("faculty");
        education2.setFieldOfStudy("field");
        education2.setModeOfStudy(ModeOfStudy.FULL_TIME);
        education2.setFromDate(LocalDate.MAX);
        education2.setUntilDate(LocalDate.MIN);

        assertEquals(education1, education2);

        education2.setId(2L);
        assertNotEquals(education1, education2);
    }

}