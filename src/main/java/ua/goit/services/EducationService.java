package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.EducationDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Education;
import ua.goit.entity.User;

import java.util.Collection;
import java.util.Collections;

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

    @Transactional
    public <S extends Education> S save(S entity) {
        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public Education findOne(Long aLong) {
        return dao.findOne(aLong);
    }

    @Transactional (readOnly = true)
    public Collection<Education> findAll () {return dao.findAll();}

    @Transactional
    public void delete(Long aLong) {
        dao.delete(aLong);
    }

    @Transactional
    public void delete(Education entity) {
        dao.delete(entity);
    }
}
