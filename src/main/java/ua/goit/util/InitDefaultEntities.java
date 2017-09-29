package ua.goit.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import ua.goit.entity.*;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;
import ua.goit.entity.enums.ModeOfStudy;
import ua.goit.entity.enums.Role;
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

/**
 * Util class for creating default entities for test
 *
 * @KontarMaryna
 */
public class InitDefaultEntities {

    public static void initDefaultUsers(UserService usersService,
                                        ProjectService projectService, PasswordEncoder passwordEncoder){

        //user
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(passwordEncoder.encode("admin"));
        Collection<Role> roles = Arrays.asList(Role.ADMIN);
        user1.setRoles(roles);
        Contact contact = new Contact("email", "0503357740",
                "Kyiv", Country.UKRAINE);
        user1.setContact(contact);
        Experience experience1 = new Experience("companyadmin1", "position1",
                "responsibility1"
                , LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));
        Experience experience2 = new Experience("companyadmin2", "position2",
                "responsibility2"
                ,LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));

        user1.setPersonalPageFotoLink("WEB-INF/jpeg/personalPageFoto/adminFoto.jpg");//TODO Как ссылку указывать?
        user1.setYoutubeLink("https://www.youtube.com/watch?v=3wBteulZaAs&index=1&list=PL6jg6AGdCNaWF-sUH2QDudBRXo54zuN1t");
        user1.setAboutMe("I'm admin!");
        user1.setSkills("JavaCore, Spring, Hibernate");
        user1.addExperience(experience1);
        user1.addExperience(experience2);

        Education education1 = new Education("institution1", "stage1",
                "faculty1","fieldOfStudy", ModeOfStudy.FULL_TIME,
                LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));
        user1.addEducation(education1);

        BusinessPlan businessPlan1 = new BusinessPlan("idea1", "currentState","market1");
        Project project1 = new Project("project1", BigDecimal.valueOf(100000),BigDecimal.valueOf(10000),
                Industry.AGRICULTURE, new Address("Kiev", null, Country.UKRAINE),
                "description for project1", null, LocalDate.of(2017, 9, 1));

        project1.addBusinessPlan(businessPlan1);
        projectService.save(project1);
        user1.addProject(project1);
        usersService.save(user1);


//user2
        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword(passwordEncoder.encode("user"));
        Collection<Role> roles1 = Arrays.asList(Role.ADMIN);
        user2.setRoles(roles1);
        Contact contact1 = new Contact("emailuser", "555", "Paris", Country.FRANCE);
        user2.setContact(contact1);
        Experience experience3 = new Experience("companyadmin3", "position3",
                "responsibility3"
                ,LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));
        Experience experience4 = new Experience("companyadmin4", "position4",
                "responsibility4"
                ,LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));
        user2.addExperience(experience3);
        user2.addExperience(experience4);
        Education education2 = new Education("institution2", "stage2",
                "faculty2","fieldOfStudy2", ModeOfStudy.EVENING
                ,LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));
        user2.addEducation(education2);

        BusinessPlan businessPlan2 = new BusinessPlan("idea2", "currentState2","market2");
        Project project2 = new Project("project2", BigDecimal.valueOf(100000),BigDecimal.valueOf(20000),
                Industry.AGRICULTURE, new Address("Kiev", null, Country.UKRAINE),
                "description for project1", null, LocalDate.of(2017, 8, 1));

        project2.addBusinessPlan(businessPlan2);
        projectService.save(project2);
        user2.addProject(project2);

        usersService.save(user2);

//user3
        User user3 = new User();
        user3.setUsername("user1");
        user3.setPassword(passwordEncoder.encode("user1"));
        Collection<Role> roles2 = Arrays.asList(Role.USER);
        user3.setRoles(roles2);
        Contact contact2 = new Contact("emailuser1", "333", "London", Country.UNITED_KINGDOM);
        user3.setContact(contact2);
        Experience experience5 = new Experience("companyadmin5", "position5",
                "responsibility5"
                ,LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));
        Experience experience6 = new Experience("companyadmin6", "position6",
                "responsibility6"
                ,LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));
        user3.addExperience(experience5);
        user3.addExperience(experience6);
        Education education3 = new Education("institution3", "stage3",
                "faculty3","fieldOfStudy3", ModeOfStudy.REMOTE
                ,LocalDate.of(2002, 9, 1),
                LocalDate.of(2007, 6, 30));
        user3.addEducation(education3);
        usersService.save(user3);
    }
}
