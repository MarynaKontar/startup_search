package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.*;
import ua.goit.entity.*;

import java.util.Collection;
import java.util.List;

/**
 * Service for {@link ua.goit.entity.User} which will use
 * {@link ua.goit.dao.UserDao} as data access object
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Service
public class UserService {

    private final UserDao dao;
    private final ProjectDao projectDao;
    private final InterestDao interestDao;
    private final EducationDao educationDao;
    private final ExperienceDao experienceDao;
    private final AddressDao addressDao;
    private final BusinessPlanDao businessPlanDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao dao, ProjectDao projectDao, InterestDao interestDao,
                       EducationDao educationDao, ExperienceDao experienceDao,
                       AddressDao addressDao, BusinessPlanDao businessPlanDao,
                       PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.projectDao = projectDao;
        this.interestDao = interestDao;
        this.educationDao = educationDao;
        this.experienceDao = experienceDao;
        this.addressDao = addressDao;
        this.businessPlanDao = businessPlanDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return dao.findAll();
    }

    /**
     * Method saves {@link User} to repository if no {@link User} with such id exists
     * @param entity User to save
     */
    @Transactional
    public <S extends User> S save(S entity) {
        return dao.save(entity);
    }

    /**
     * Method updates {@link User} with id to repository
     * @param updatedUser User to update
     * @param id the id of {@link User} to update
     * @param password of {@link User} to update
     * @return updated user
     */
    @Transactional
    public <S extends User> S update(S updatedUser, Long id, String password) {
        User user = dao.findOne(id);

        //TODO приходится так перезаписывать пароль, потому что если не делать так, то перезаписывается пароль с солью
        if (password != null && !password.isEmpty() && !user.getPassword().equals(password)) {
            updatedUser.setPassword(passwordEncoder.encode(password));
        }
        updatedUser.setProjects(user.getProjects());
        updatedUser.setExperiences(user.getExperiences());
        updatedUser.setEducations(user.getEducations());
        updatedUser.setInterests(user.getInterests());
        updatedUser.setRoles(user.getRoles());

        return dao.save(updatedUser);
    }

    /**
     * Retrieves a user by its id.
     * @param s id for searching user in repository, must not be {@literal null}.
     * @return {@link User} from repository with given id or {@literal null} if none found
     */
    @Transactional(readOnly = true)
    public User findOne(Long s) {
        return dao.findOne(s);
    }

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return dao.findUserByUsername(username);
    }

    /**
     * Returns whether a user with the given id exists.
     * @param aLong id of the user
     * @return true if an entity with the given id exists, {@literal false} otherwise
     */
    @Transactional(readOnly = true)
    public boolean exists(Long aLong) {
        return dao.exists(aLong);
    }

    /**
     * Delete user (personal account) with id
     * @param id the id of {@link User} to delete
     */
    @Transactional
    public void deletePersonalAccount(Long id) {
        if (id != null) {
            User user = dao.findOne(id);
            if (user != null) {

                Collection<Education> educations = user.getEducations();
                if (educations != null) {
                    educationDao.delete(educations);
                }

                Collection<Experience> experiences = user.getExperiences();
                if (experiences != null) {
                    experienceDao.delete(experiences);
                }
                Collection<Project> projects = user.getProjects();
                if (projects != null) {
                    projects.forEach(project->{
                        Address address = project.getAddress();
                        if (address != null){ addressDao.delete(address);}
                        BusinessPlan businessPlan = project.getBusinessPlan();
                        if(businessPlan != null){businessPlanDao.delete(businessPlan);}
                    } );
                    projectDao.delete(projects);

                }
                Collection<Interest> interests = user.getInterests();
                if (interests != null) {
                    interestDao.delete(interests);
                }
            }
            dao.delete(id);
        }
    }
}
