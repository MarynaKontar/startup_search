package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.Address;

/**
 * Data access object for {@link ua.goit.entity.Address}
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
public interface AddressDao extends JpaRepository<Address, Long> {
}
