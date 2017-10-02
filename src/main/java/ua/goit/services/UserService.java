package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    public UserService(UserDao dao, ProjectDao projectDao) {
        this.dao = dao;
        this.projectDao = projectDao;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return dao.findAll();
    }

    @Transactional
    public <S extends User> S save(S entity) {
        return dao.save(entity);
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

        Collection<Project> projects = user.getProjects();
        projects.forEach(user::removeProject);
        projectDao.delete(projects);

        Collection<Education> educations = user.getEducations();
        educations.forEach(user::removeEducatione);

        Collection<Experience> experiences = user.getExperiences();
        experiences.forEach(user::removeExperience);

    }
}
