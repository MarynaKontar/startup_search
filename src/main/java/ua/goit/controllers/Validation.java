package ua.goit.controllers;

import ua.goit.entity.enums.Role;
import ua.goit.services.InterestService;
import ua.goit.services.ProjectService;
import ua.goit.services.UserService;

/**
 * Util class for entities validation
 */
public class Validation {

    /**
     * Method validate if {@link ua.goit.entity.User} exists
     * @param id of {@link ua.goit.entity.User} for validation
     * @param userService service for {@link ua.goit.entity.User}
     * @throws Exception if {@link ua.goit.entity.User} doesn't exists
     */
    static void validateUser(Long id, UserService userService) throws Exception {
        if (!userService.exists(id)) {
            throw new Exception("User with id=" + id + " doesn't exists");
        }
    }

    /**
     * Method validate if {@link ua.goit.entity.Project} exists
     * @param id of {@link ua.goit.entity.Project} for validation
     * @param projectService service for {@link ua.goit.entity.Project}
     * @throws Exception if {@link ua.goit.entity.Project} doesn't exists
     */
    static void validateProject(Long id, ProjectService projectService) throws Exception {
        if (!projectService.exists(id)) {
            throw new Exception("Project with id=" + id + " doesn't exists");
        }
    }

    /**
     * Method validate if {@link ua.goit.entity.Interest} exists
     * @param id of {@link ua.goit.entity.Interest} for validation
     * @param interestService service for {@link ua.goit.entity.Interest}
     * @throws Exception if {@link ua.goit.entity.Interest} doesn't exists
     */
    static void validateInterest(Long id, InterestService interestService) throws Exception {
        if (!interestService.exists(id)) {
            throw new Exception("Interest with id=" + id + " doesn't exists");
        }
    }

    /**
     * Method validate if {@link ua.goit.entity.User} exists
     * @param id id of {@link ua.goit.entity.User} for validation
     * @param current_id id of {@link ua.goit.entity.User} in session
     * @param userService service for {@link ua.goit.entity.User}
     * @throws Exception if {@link ua.goit.entity.User} doesn't exists or if current_id != id and user with current_id
     * hasn't ADMIN {@link ua.goit.entity.enums.Role}
     */
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
