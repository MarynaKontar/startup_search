package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.Experience;

/**
 * Data access object for {@link ua.goit.entity.Experience}
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
public interface ExperienceDao extends JpaRepository<Experience, Long> {
}
