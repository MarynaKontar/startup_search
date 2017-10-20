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

    /**
     * Method saves {@link Project} to repository if no {@link Project} with such id exists
     * @param entity Project to save
     */
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

    /**
     * Retrieves a {@link List} of all {@link Project}
     * @return {@link List} of {@link Project}
     */
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return dao.findAll();
    }

    /**
     * Retrieves a {@link List} of {@link Project} with {@link Industry}
     * @return {@link List} of {@link Project} with {@link Industry}
     */
    @Transactional(readOnly = true)
    public List<Project> findAllByIndustry(Industry industry) {
        return dao.findAllByIndustry(industry);
    }

    /**
     * Retrieves a {@link List} of {@link Project} with {@link Country}
     * @return {@link List} of {@link Project} with {@link Country}
     */
    @Transactional(readOnly = true)
    public List<Project> findAllByAddress_Country(Country country) {
        return dao.findAllByAddress_Country(country);
    }

    /**
     * Retrieves a {@link List} of {@link Project} with {@link Industry} and {@link Country}
     * @return {@link List} of {@link Project} with {@link Industry} and {@link Country}
     */
    @Transactional(readOnly = true)
    public List<Project> findAllByIndustryAndAddress_Country(Industry industry, Country country) {
        return dao.findAllByIndustryAndAddress_Country(industry, country);
    }

    /**
     * Retrieves a {@link List} of {@link Project} sorted by date of last change  in decrease order
     * @return {@link List} of {@link Project} sorted by date of last change  in decrease order
     */
    @Transactional(readOnly = true)
    public List<Project> findProjectsByOrderByLastChangeDesc() {
        return dao.findProjectsByOrderByLastChangeDesc();
    }

    /**
     * Retrieves a project by its id.
     * @param s id for searching project in repository, must not be {@literal null}.
     * @return {@link Project} from repository with given id or {@literal null} if none found
     */
    @Transactional(readOnly = true)
    public Project findOne(Long s) {
        return dao.findOne(s);
    }

    /**
     * Returns whether a project with the given id exists.
     * @param s id of the project
     * @return true if an entity with the given id exists, {@literal false} otherwise
     */
    @Transactional(readOnly = true)
    public boolean exists(Long s) {
        return dao.exists(s);
    }

    /**
     * Delete {@link Project} with id from {@link User}
     * @param id the id of {@link Project} to delete
     * @param user_id the id of {@link User} from which the project is deleted
     */
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
