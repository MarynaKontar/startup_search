package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.ExperienceDao;
import ua.goit.entity.Experience;

/**
 * Service for {@link ua.goit.entity.Experience} which will use
 * {@link ua.goit.dao.ExperienceDao} as data access object
 *
 * @KontarMaryna
 */
public class ExperienceService {

    private final ExperienceDao dao;

    @Autowired
    public ExperienceService(ExperienceDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public Experience getOne(Long aLong) {
        return dao.getOne(aLong);
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
    public void delete(Long aLong) {
        dao.delete(aLong);
    }

    @Transactional
    public void delete(Experience entity) {
        dao.delete(entity);
    }
}
