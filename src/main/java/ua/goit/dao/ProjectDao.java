package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.Project;
import ua.goit.entity.User;

import java.util.List;


/**
 * Data access object for {@link Project}
 * @KontarMaryna
 */
public interface ProjectDao extends JpaRepository<Project, Long>{
List<Project> findAllByUserUsername(String username);

    /*Project findByName(String name);
    List<Project> findByIndustry(String industry);
    List<Project> findByRegion(String region);
    List<Project> findByInvSize(long invSize);
    List<Project> findByMinIrr(long minIrr);
    List<Project> findActive();*/

}