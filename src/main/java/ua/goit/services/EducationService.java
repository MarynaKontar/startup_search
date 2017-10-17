package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.EducationDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Education;
import ua.goit.entity.User;

/**
 * Service for {@link ua.goit.entity.Education} which will use
 * {@link ua.goit.dao.EducationDao} as data access object
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Service
public class EducationService {

    private final EducationDao dao;
    private final UserDao userDao;

    @Autowired
    public EducationService(EducationDao dao, UserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    public Education getOne(Long aLong) {
        return dao.getOne(aLong);
    }

    @Transactional
    public <S extends Education> S save(S entity) {
        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public Education findOne(Long aLong) {
        return dao.findOne(aLong);
    }

    @Transactional
    public void deleteEducationFromUser(Long id, Long user_id) {
        User user = userDao.findOne(user_id);
        Education education = dao.findOne(id);
        user.removeEducatione(education);
    }
}
