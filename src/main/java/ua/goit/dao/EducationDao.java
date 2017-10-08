package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.Education;

/**
 * Data access object for {@link ua.goit.entity.Education}
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
public interface EducationDao extends JpaRepository<Education, Long> {
}
