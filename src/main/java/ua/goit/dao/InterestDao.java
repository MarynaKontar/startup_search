package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.Interest;
import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;

import java.util.List;

/**
 * Data access object for {@link ua.goit.entity.Interest}
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
public interface InterestDao extends JpaRepository<Interest, Long> {
    List<Interest> findAllByIndustry(Industry industry);

    List<Interest> findAllByCountry(Country country);

    List<Interest> findAllByIndustryAndCountry(Industry industry, Country country);

    List<Interest> findInterestsByOrderByLastChangeDesc();

}
