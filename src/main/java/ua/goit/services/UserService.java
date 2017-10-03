package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.EducationDao;
import ua.goit.dao.ExperienceDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Education;
import ua.goit.entity.Experience;
import ua.goit.entity.Project;
import ua.goit.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * Service for {@link ua.goit.entity.User} which will use
 * {@link ua.goit.dao.UserDao} as data access object
 *
 * @KontarMaryna
 */
@Service
public class UserService {

    private final UserDao dao;
    private final ProjectDao projectDao;
    private final EducationDao educationDao;
    private final ExperienceDao experienceDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao dao, ProjectDao projectDao, EducationDao educationDao, ExperienceDao experienceDao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.projectDao = projectDao;
        this.educationDao = educationDao;
        this.experienceDao = experienceDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return dao.findAll();
    }

    @Transactional
    public <S extends User> S save(S entity) {
        return dao.save(entity);
    }

    @Transactional
    public <S extends User> S update(S updatedUser, String username, String password) {
        User user = dao.findOne(username);

        //TODO приходится так перезаписывать пароль, потому что если не делать так, то перезаписывается пароль с солью
        if(password!= null && !password.isEmpty() && !user.getPassword().equals(password)){
            updatedUser.setPassword(passwordEncoder.encode(password));
        }
        //TODO пока не делала функционал подгрузки фото профиля, надо, чтобы не "стиралась" ссылка на фото в бд
        updatedUser.setPersonalPageFotoLink(user.getPersonalPageFotoLink());
        updatedUser.setProfileFotoLink(user.getProfileFotoLink());

        updatedUser.setProjects(user.getProjects());
        updatedUser.setExperiences(user.getExperiences());
        updatedUser.setEducations(user.getEducations());
        updatedUser.setRoles(user.getRoles());

        return dao.save(updatedUser);
    }

    @Transactional(readOnly = true)
    public User findOne(String s) {
        return dao.findOne(s);
    }

    @Transactional(readOnly = true)
    public User getOne(String s) {
        return dao.getOne(s);
    }

    @Transactional(readOnly = true)
    public boolean exists(String s) {
        return dao.exists(s);
    }

    @Transactional
    public void delete(String s) {
        dao.delete(s);
    }

    @Transactional
    public void delete(User entity) {
        dao.delete(entity);
    }

    @Transactional
    public void deletePersonalAccount(String username) {
        User user = dao.findOne(username);
        Collection<Education> educations = user.getEducations();
        Collection<Experience> experiences = user.getExperiences();
        Collection<Project> projects = user.getProjects();

//        educations.forEach(educationDao::delete);
        educationDao.delete(educations);
        //TODO кидает java.util.ConcurrentModificationException
        educations.forEach(user::removeEducatione);

//        experiences.forEach(experienceDao::delete);
        experienceDao.delete(experiences);
        experiences.forEach(user::removeExperience);

        projectDao.delete(projects);
        projects.forEach(user::removeProject);
        dao.delete(username);
    }
}
