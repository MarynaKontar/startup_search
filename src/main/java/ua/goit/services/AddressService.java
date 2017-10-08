package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.AddressDao;
import ua.goit.entity.Address;

import java.util.List;


/**
 * Service for {@link ua.goit.entity.Address} which will use
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

    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Address getOne(Long aLong) {
        return dao.getOne(aLong);
    }

    @Transactional
    public <S extends Address> S save(S entity) {
        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public Address findOne(Long aLong) {
        return dao.findOne(aLong);
    }

    @Transactional(readOnly = true)
    public boolean exists(Long aLong) {
        return dao.exists(aLong);
    }

    @Transactional
    public void delete(Long aLong) {
        dao.delete(aLong);
    }

    @Transactional
    public void delete(Address entity) {
        dao.delete(entity);
    }
}
