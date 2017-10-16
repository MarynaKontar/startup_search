package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.Project;
import ua.goit.entity.User;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;

import java.util.List;


/**
 * Data access object for {@link Project}
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
public interface ProjectDao extends JpaRepository<Project, Long>{
List<Project> findAllByUserUsername(String username);

//    /*Project findByName(String name);
    List<Project> findAllByIndustry(Industry industry);
    List<Project> findAllByAddress_Country(Country country);
    List<Project> findAllByIndustryAndAddress_Country(Industry industry, Country country);
    List<Project> findProjectsByOrderByLastChangeDesc();

//    List<Project> findByRegion(String region);
//    List<Project> findByInvSize(long invSize);
//    List<Project> findByMinIrr(long minIrr);
//    List<Project> findActive();*/

}
