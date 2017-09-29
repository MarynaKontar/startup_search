package ua.goit.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.BusinessPlanDao;
import ua.goit.entity.BusinessPlan;

import java.util.List;

/**
 * Service for {@link ua.goit.entity.BusinessPlan} which will use
 * {@link ua.goit.dao.BusinessPlanDao} as data access object
 *
 * @KontarMaryna
 */

@Service
public class BusinessPlanService {

    private final BusinessPlanDao dao;

    @Autowired
    public BusinessPlanService(BusinessPlanDao dao) {
        this.dao = dao;
    }

    @Transactional
    public <S extends BusinessPlan> S save(S s) {
        return dao.save(s);
    }

    @Transactional(readOnly = true)
    public List<BusinessPlan> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public BusinessPlan findOne(Long s) {
        return dao.findOne(s);
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
    public void delete(BusinessPlan entity) {
        dao.delete(entity);
    }

}
