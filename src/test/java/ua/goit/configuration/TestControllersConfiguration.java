package ua.goit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.goit.services.*;

import static org.mockito.Mockito.mock;

@Configuration
public class TestControllersConfiguration {

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public ProjectService projectService() {
        return mock(ProjectService.class);
    }

    @Bean
    public InterestService interestService() {
        return mock(InterestService.class);
    }

    @Bean
    public AddressService addressService() {
        return mock(AddressService.class);
    }

    @Bean
    public BusinessPlanService businessPlanService() {
        return mock(BusinessPlanService.class);
    }

    @Bean
    public EducationService  educationService() {
        return mock(EducationService.class);
    }

    @Bean
    public ExperienceService  experienceService() {
        return mock(ExperienceService.class);
    }

    @Bean
    public SearchService  searchService() {
        return mock(SearchService.class);
    }
}
