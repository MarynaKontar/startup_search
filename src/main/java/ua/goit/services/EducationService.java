package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.EducationDao;
import ua.goit.entity.Education;

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

    @Autowired
    public EducationService(EducationDao dao) {
        this.dao = dao;
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
    public void delete(Long aLong) {
        dao.delete(aLong);
    }

    @Transactional
    public void delete(Education entity) {
        dao.delete(entity);
    }
}
