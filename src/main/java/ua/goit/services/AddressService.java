package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.AddressDao;
import ua.goit.entity.Address;

import java.util.List;

/**
 * Service for {@link ua.goit.entity.Address} which uses
 * {@link ua.goit.dao.AddressDao} as data access object
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Service
public class AddressService {

    private final AddressDao dao;

    @Autowired
    public AddressService(AddressDao dao) {
        this.dao = dao;
    }

    /**
     * Retrieves a {@link List} of all {@link Address}
     * @return {@link List} of {@link Address}
     */
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return dao.findAll();
    }

    /**
     * Method saves {@link Address} to repository if no {@link Address} with such id exists
     * @param entity Address to save
     */
    @Transactional
    public <S extends Address> S save(S entity) {
        return dao.save(entity);
    }

    /**
     * Retrieves an address by its id.
     * @param aLong id for searching address in repository, must not be {@literal null}.
     * @return {@link Address} from repository with given id or {@literal null} if none found
     */
    @Transactional(readOnly = true)
    public Address findOne(Long aLong) {
        return dao.findOne(aLong);
    }

    /**
     *  Returns whether an address with the given id exists.
     * @param aLong id of the address
     * @return true if an entity with the given id exists, {@literal false} otherwise
     */
    @Transactional(readOnly = true)
    public boolean exists(Long aLong) {
        return dao.exists(aLong);
    }

}
