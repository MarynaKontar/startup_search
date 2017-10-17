package ua.goit.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.AddressDao;
import ua.goit.dao.BusinessPlanDao;
import ua.goit.dao.ProjectDao;
import ua.goit.dao.UserDao;
import ua.goit.entity.Address;
import ua.goit.entity.BusinessPlan;
import ua.goit.entity.Project;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;

import java.util.Collection;
import java.util.List;

/**
 * Service for {@link ua.goit.entity.Project} which will use
 * {@link ua.goit.dao.ProjectDao} as data access object
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Service
public class ProjectService {

    private final ProjectDao dao;
    private final AddressDao addressDao;
    private final BusinessPlanDao businessPlanDao;
    private final UserDao userDao;

    @Autowired
    public ProjectService(ProjectDao dao, AddressDao addressDao, BusinessPlanDao businessPlanDao, UserDao userDao) {
        this.dao = dao;
        this.addressDao = addressDao;
        this.businessPlanDao = businessPlanDao;
        this.userDao = userDao;
    }

    @Transactional
    public <S extends Project> S save(S entity) {
        Address address = entity.getAddress();
        if (address != null && ((address.getCity() != null)
                || (address.getCountry() != null)
                || (address.getRegion() != null))) {
            addressDao.save(address);
        }
        BusinessPlan businessPlan = entity.getBusinessPlan();
        if(businessPlan != null){
        businessPlanDao.save(businessPlan);}
        entity.setActive(true);
        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Project> findAllByIndustry(Industry industry) {
        return dao.findAllByIndustry(industry);
    }

    @Transactional(readOnly = true)
    public List<Project> findAllByAddress_Country(Country country) {
        return dao.findAllByAddress_Country(country);
    }

    @Transactional(readOnly = true)
    public List<Project> findAllByIndustryAndAddress_Country(Industry industry, Country country) {
        return dao.findAllByIndustryAndAddress_Country(industry, country);
    }

    @Transactional(readOnly = true)
    public List<Project> findProjectsByOrderByLastChangeDesc() {
        return dao.findProjectsByOrderByLastChangeDesc();
    }

    @Transactional(readOnly = true)
    public Project findOne(Long s) {
        return dao.findOne(s);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long s) {
        return dao.exists(s);
    }

    @Transactional
    public void deleteProjectFromUser(Long id, Long user_id){
        User user = userDao.findOne(user_id);
        Project project = dao.findOne(id);

        Address address = project.getAddress();
        if (address != null){ addressDao.delete(address);}
        BusinessPlan businessPlan = project.getBusinessPlan();
        if(businessPlan != null){businessPlanDao.delete(businessPlan);}

        user.removeProject(project);
    }
}
