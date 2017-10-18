package ua.goit.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.goit.entity.enums.Role;
import ua.goit.services.InterestService;
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;

/**
 * Entities validation
 */
public class Validation {


    static void validateUser(Long id, UserService userService) throws Exception {
        if (!userService.exists(id)) {
            throw new Exception("User with id=" + id + " doesn't exists");
        }
    }

    static void validateProject(Long id, ProjectService projectService) throws Exception {
        if (!projectService.exists(id)) {
            throw new Exception("Project with id=" + id + " doesn't exists");
        }
    }

    static void validateInterest(Long id, InterestService interestService) throws Exception {
        if (!interestService.exists(id)) {
            throw new Exception("Interest with id=" + id + " doesn't exists");
        }
    }

    static void validateUserForDelete(Long id, Long current_id, UserService userService) throws Exception {
        if (!userService.exists(current_id)) {
            throw new Exception("Interest with id=" + current_id + " doesn't exists");
        }
        if (!userService.exists(id)) {
            throw new Exception("Interest with id=" + id + " doesn't exists");
        }
        if ((current_id != id && !userService.findOne(current_id).getRoles().contains(Role.ADMIN))) {
            throw new Exception("You can't delete profile with id=" + id);
        }
    }

}
