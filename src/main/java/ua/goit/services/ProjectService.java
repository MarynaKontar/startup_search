package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.AddressDao;
import ua.goit.dao.ProjectDao;
import ua.goit.entity.Address;
import ua.goit.entity.Project;

import java.util.List;

/**
 * Service for {@link ua.goit.entity.Project} which will use
 * {@link ua.goit.dao.ProjectDao} as data access object
 *
 * @KontarMaryna
 */
@Service
public class ProjectService {

    private final ProjectDao dao;
    private final AddressDao addressDao;

    @Autowired
    public ProjectService(ProjectDao dao, AddressDao addressDao) {
        this.dao = dao;
        this.addressDao = addressDao;
    }

    @Transactional
    public <S extends Project> S save(S entity) {
        Address address = entity.getAddress();
        if (address != null) {
            addressDao.save(address);
        }
        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return dao.findAll();
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
    public void delete(Long s) {
        dao.delete(s);
    }

    @Transactional
    public void delete(Project entity) {
        dao.delete(entity);
    }

}
