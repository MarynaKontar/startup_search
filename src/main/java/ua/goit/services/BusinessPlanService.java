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
 * @GuillaumeGingembre
 * @VitaliiProskura
 */

@Service
public class BusinessPlanService {

    private final BusinessPlanDao dao;

    @Autowired
    public BusinessPlanService(BusinessPlanDao dao) {
        this.dao = dao;
    }

    /**
     * Method saves {@link BusinessPlan} to repository if no {@link BusinessPlan} with such id exists
     * @param s BusinessPlan to save
     */
    @Transactional
    public <S extends BusinessPlan> S save(S s) {
        return dao.save(s);
    }

    /**
     * Retrieves a {@link List} of all {@link BusinessPlan}
     * @return {@link List} of {@link BusinessPlan}
     */
    @Transactional(readOnly = true)
    public List<BusinessPlan> findAll() {
        return dao.findAll();
    }

    /**
     * Retrieves a business plan by its id.
     * @param s id for searching business plan in repository, must not be {@literal null}.
     * @return {@link BusinessPlan} from repository with given id or {@literal null} if none found
     */
    @Transactional(readOnly = true)
    public BusinessPlan findOne(Long s) {
        return dao.findOne(s);
    }

    /**
     *  Returns whether a business plan with the given id exists.
     * @param s id of the business plan
     * @return true if an entity with the given id exists, {@literal false} otherwise
     */
    @Transactional(readOnly = true)
    public boolean exists(Long s) {
        return dao.exists(s);
    }

}
