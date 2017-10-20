package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.InterestDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Interest;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;

import java.util.List;

/**
 * Service for {@link ua.goit.entity.Interest} which will use
 * {@link ua.goit.dao.InterestDao} as data access object
 *
 * @KontarMaryna
 */
@Service
public class InterestService {

    private final InterestDao dao;
    private final UserDao userDao;

    @Autowired
    public InterestService(InterestDao dao, UserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    /**
     * Retrieves a {@link List} of all {@link Interest}
     * @return {@link List} of {@link Interest}
     */
    @Transactional(readOnly = true)
    public List<Interest> findAll() {
        return dao.findAll();
    }

    /**
     * Retrieves a {@link List} of {@link Interest} with {@link Industry}
     * @return {@link List} of {@link Interest} with {@link Industry}
     */
    @Transactional(readOnly = true)
    public List<Interest> findAllByIndustry(Industry industry) {
        return dao.findAllByIndustry(industry);
    }

    /**
     * Retrieves a {@link List} of {@link Interest} with {@link Country}
     * @return {@link List} of {@link Interest} with {@link Country}
     */
    @Transactional(readOnly = true)
    public List<Interest> findAllByCountry(Country country) {
        return dao.findAllByCountry(country);
    }

    /**
     * Retrieves a {@link List} of {@link Interest} with {@link Industry} and {@link Country}
     * @return {@link List} of {@link Interest} with {@link Industry} and {@link Country}
     */
    @Transactional(readOnly = true)
    public List<Interest> findAllByIndustryAndCountry(Industry industry, Country country) {
        return dao.findAllByIndustryAndCountry(industry, country);
    }

    /**
     * Retrieves a {@link List} of {@link Interest} sorted by date of last change  in decrease order
     * @return {@link List} of {@link Interest} sorted by date of last change  in decrease order
     */
    @Transactional(readOnly = true)
    public List<Interest> findInterestsByOrderByLastChangeDesc() {
        return dao.findInterestsByOrderByLastChangeDesc();
    }

    /**
     * Method saves {@link Interest} to repository if no {@link Interest} with such id exists
     * @param entity Interest to save
     */
    @Transactional
    public <S extends Interest> S save(S entity) {
        return dao.save(entity);
    }

    /**
     * Retrieves a interest by its id.
     * @param aLong id for searching interest in repository, must not be {@literal null}.
     * @return {@link Interest} from repository with given id or {@literal null} if none found
     */
    @Transactional(readOnly = true)
    public Interest findOne(Long aLong) {
        return dao.findOne(aLong);
    }

    /**
     * Returns whether an interest with the given id exists.
     * @param aLong id of the interest plan
     * @return true if an entity with the given id exists, {@literal false} otherwise
     */
    @Transactional(readOnly = true)
    public boolean exists(Long aLong) {
        return dao.exists(aLong);
    }

    /**
     * Delete {@link Interest} with id from {@link User}
     * @param id the id of {@link Interest} to delete
     * @param user_id the id of {@link User} from which the interest is deleted
     */
    @Transactional
    public void deleteInterestFromUser(Long id, Long user_id) {
        User user = userDao.findOne(user_id);
        Interest interest = dao.findOne(id);
        user.removeInterest(interest);
    }
}
