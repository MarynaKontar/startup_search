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
 * {@link ua.goit.dao.EducationDao} and {@link ua.goit.dao.UserDao} as data access object
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

    /**
     * Method saves {@link Education} to repository if no {@link Education} with such id exists
     * @param entity Education to save
     */
    @Transactional
    public <S extends Education> S save(S entity) {
        return dao.save(entity);
    }

    /**
     * Retrieves an education by its id.
     * @param aLong id for searching education in repository, must not be {@literal null}.
     * @return {@link Education} from repository with given id or {@literal null} if none found
     */
    @Transactional(readOnly = true)
    public Education findOne(Long aLong) {
        return dao.findOne(aLong);
    }

    /**
     * Delete {@link Education} with id from {@link User}
     * @param id the id of {@link Education} to delete
     * @param user_id the id of {@link User} from which the education is deleted
     */
    @Transactional
    public void deleteEducationFromUser(Long id, Long user_id) {
        User user = userDao.findOne(user_id);
        Education education = dao.findOne(id);
        user.removeEducatione(education);
    }
}
