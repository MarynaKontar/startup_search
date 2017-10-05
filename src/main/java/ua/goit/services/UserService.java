package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.EducationDao;
import ua.goit.dao.ExperienceDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.*;

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
    public <S extends User> S update(S updatedUser, Long id, String password) {
        User user = dao.findOne(id);

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
        updatedUser.setInterests(user.getInterests());
        updatedUser.setRoles(user.getRoles());

        return dao.save(updatedUser);
    }

    @Transactional(readOnly = true)
    public User findOne(Long s) {
        return dao.findOne(s);
    }

    public User findUserByUsername(String username) {
        return dao.findUserByUsername(username);
    }

    @Transactional(readOnly = true)
    public User getOne(Long s) {
        return dao.getOne(s);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long s) {
        return dao.exists(s);
    }

    @Transactional
    public void delete(Long s) {
        dao.delete(s);
    }

    @Transactional
    public void delete(User entity) {
        dao.delete(entity);
    }

    //TODO
    @Transactional
    public void deletePersonalAccount(Long id) {
        User user = dao.findOne(id);
        Collection<Education> educations = user.getEducations();
        Collection<Experience> experiences = user.getExperiences();
        Collection<Project> projects = user.getProjects();
        Collection<Interest> interests = user.getInterests();

//        educations.forEach(educationDao::delete);
//        educationDao.delete(educations);
        //TODO кидает java.util.ConcurrentModificationException
        educations.forEach(user::removeEducatione);

//        experiences.forEach(experienceDao::delete);
//        experienceDao.delete(experiences);
        experiences.forEach(user::removeExperience);

//        projectDao.delete(projects);
        projects.forEach(user::removeProject);
       interests.forEach(user::removeInterest);
        dao.delete(id);
    }
}
