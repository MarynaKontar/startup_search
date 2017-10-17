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

    @Transactional(readOnly = true)
    public List<Interest> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Interest> findAllByIndustry(Industry industry) {
        return dao.findAllByIndustry(industry);
    }

    @Transactional(readOnly = true)
    public List<Interest> findAllByCountry(Country country) {
        return dao.findAllByCountry(country);
    }

    @Transactional(readOnly = true)
    public List<Interest> findAllByIndustryAndCountry(Industry industry, Country country) {
        return dao.findAllByIndustryAndCountry(industry, country);
    }

    @Transactional(readOnly = true)
    public List<Interest> findInterestsByOrderByLastChangeDesc() {
        return dao.findInterestsByOrderByLastChangeDesc();
    }

    @Transactional
    public <S extends Interest> S save(S entity) {
        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public Interest findOne(Long aLong) {
        return dao.findOne(aLong);
    }

    @Transactional
    public void deleteInterestFromUser(Long id, Long user_id) {
        User user = userDao.findOne(user_id);
        Interest interest = dao.findOne(id);
        user.removeInterest(interest);
    }
}
