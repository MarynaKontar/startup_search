package ua.goit.entity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.goit.entity.enums.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Created by User on 15.10.2017.
 */
public class UserTest {
    private User user1;
    private User user2;


    @Before
    public void setUp() throws Exception {
        Contact contact = Mockito.mock(Contact.class);
        Experience experience = Mockito.mock(Experience.class);
        Education education = Mockito.mock(Education.class);
        Project project = Mockito.mock(Project.class);
        Interest interest = Mockito.mock(Interest.class);
        Role role = Role.ADMIN;


        user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setPassword("password");
        user1.setFirstName("firstName1");
        user1.setLastName("lastName1");
        user1.setContact(contact);
        user1.setProfileFotoLink("photo1");
        user1.setPersonalPageFotoLink("personalPhoto1");
        user1.setYoutubeLink("youtobe1");
        user1.setAboutMe("aboutMe1");
        user1.setSkills("skills1");
        user1.setExperiences(new HashSet<>(Arrays.asList(experience)));
        user1.setEducations(new HashSet<>(Arrays.asList(education)));
        user1.setProjects(new HashSet<>(Arrays.asList(project)));
        user1.setInterests(new HashSet<>(Arrays.asList(interest)));
        user1.setRoles(new HashSet<>(Arrays.asList(role)));

        user2 = new User();
        user2.setId(1L);
        user2.setUsername("user1");
        user2.setPassword("password");
        user2.setFirstName("firstName1");
        user2.setLastName("lastName1");
        user2.setContact(contact);
        user2.setProfileFotoLink("photo1");
        user2.setPersonalPageFotoLink("personalPhoto1");
        user2.setYoutubeLink("youtobe1");
        user2.setAboutMe("aboutMe1");
        user2.setSkills("skills1");
        user2.setExperiences(Arrays.asList(experience));
        user2.setEducations(Arrays.asList(education));
        user2.setProjects(Arrays.asList(project));
        user2.setInterests(Arrays.asList(interest));
        user2.setRoles(Arrays.asList(role));

    }


    @Test
    public void interestTest() {
        Interest interest = Mockito.mock(Interest.class);
        assertEquals(1, user1.getInterests().size());
        user1.addInterest(interest);
        assertEquals(2, user1.getInterests().size());
        user1.removeInterest(interest);
        assertEquals(1, user1.getInterests().size());
    }

    @Test
    public void projectTest() {
        Project project = Mockito.mock(Project.class);
        assertEquals(1, user1.getProjects().size());
        user1.addProject(project);
        assertEquals(2, user1.getProjects().size());
        user1.removeProject(project);
        assertEquals(1, user1.getProjects().size());
    }

    @Test
    public void educationTest() {
        Education education = Mockito.mock(Education.class);
        assertEquals(1, user1.getEducations().size());
        user1.addEducation(education);
        assertEquals(2, user1.getEducations().size());
        user1.removeEducatione(education);
        assertEquals(1, user1.getEducations().size());



    }

    @Test
    public void expirienceTest() {
        Experience experience = Mockito.mock(Experience.class);
        assertEquals(1, user1.getExperiences().size());
        user1.addExperience(experience);
        assertEquals(2, user1.getExperiences().size());
        user1.removeExperience(experience);
        assertEquals(1, user1.getExperiences().size());
    }

    @Test
    public void equalsTest() throws Exception {

        assertReflectionEquals(user1, user2);

        assertTrue("equals by method", user1.equals(user2));
        user2.setId(2L);
        assertFalse(user1.equals(user2));
        user2.setId(1L);
        assertTrue("equals by method", user1.equals(user2));
        user2.setUsername("user2");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void hashCodeTest() throws Exception {
        assertEquals(user1, user2);
        user2.setId(2L);
        assertNotEquals(user1, user2);
        user2.setId(1L);
        assertEquals(user1, user2);
        user2.setUsername("user2");
        assertNotEquals(user1, user2);

    }

}