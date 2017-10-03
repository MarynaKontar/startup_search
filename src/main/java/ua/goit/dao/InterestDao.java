package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.Interest;

/**
 * Data access object for {@link ua.goit.entity.Interest}
 * @KontarMaryna
 */
public interface InterestDao  extends JpaRepository<Interest, Long> {
}
