package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.ExperienceDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Experience;
import ua.goit.entity.User;

/**
 * Service for {@link ua.goit.entity.Experience} which will use
 * {@link ua.goit.dao.ExperienceDao} as data access object
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Service
public class ExperienceService {

    private final ExperienceDao dao;
    private final UserDao userDao;

    @Autowired
    public ExperienceService(ExperienceDao dao, UserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    @Transactional
    public <S extends Experience> S save(S entity) {
        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public Experience findOne(Long aLong) {
        return dao.findOne(aLong);
    }

    @Transactional
    public void deleteEducationFromUser(Long id, Long user_id) {
        User user = userDao.findOne(user_id);
        Experience experience = dao.findOne(id);
        user.removeExperience(experience);
    }
}
