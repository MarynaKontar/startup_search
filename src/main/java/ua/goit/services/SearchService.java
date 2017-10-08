package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.*;
import ua.goit.entity.Project;
import ua.goit.entity.enums.Industry;

import java.util.List;

/**
 * Service for searching which will use
 * {@link ua.goit.dao.UserDao}, {@link ua.goit.dao.ProjectDao} and {@link ua.goit.dao.InterestDao}
 * as data access object
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Service
public class SearchService {

    private final UserDao userDao;
    private final ProjectDao projectDao;
    private final InterestDao interestDao;

    @Autowired
    public SearchService(UserDao userDao, ProjectDao projectDao, InterestDao interestDao) {
        this.userDao = userDao;
        this.projectDao = projectDao;
        this.interestDao = interestDao;
    }

    @Transactional(readOnly = true)
    public List<Project> findAllProjectsByIndustry(String industry) {
        return projectDao.findAllByIndustry(Industry.valueOf(industry));
    }
}
