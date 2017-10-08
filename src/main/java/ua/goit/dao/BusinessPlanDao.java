package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.BusinessPlan;


/**
 * Data access object for {@link ua.goit.entity.BusinessPlan}
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */

public interface BusinessPlanDao extends JpaRepository<BusinessPlan, Long> {

        /*Project findByName(String name);
    List<Project> findByIndustry(String industry);
    List<Project> findByRegion(String region);
    List<Project> findByInvSize(long invSize);
    List<Project> findByMinIrr(long minIrr);
    List<Project> findActive();*/
}
