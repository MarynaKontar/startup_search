package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.StartUp;

/**
 * Data access object for {@link ua.goit.entity.StartUp}
 */
public interface StartupDao extends JpaRepository<StartUp, String> {
}
