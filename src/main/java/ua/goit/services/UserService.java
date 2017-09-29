package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.UserDao;
import ua.goit.entity.User;

import java.util.List;

/**
 * Service for {@link ua.goit.entity.User} which will use
 * {@link ua.goit.dao.UserDao} as data access object
 *
 * @KontarMaryna
 */
@Service
public class UserService {

    private final UserDao dao;


    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;

    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return dao.findAll();
    }

    @Transactional
    public <S extends User> S save(S entity) {
//        Contact contact = entity.getContact();
//        if (contact != null) {
//            contactDao.save(contact);//нужно для случая @OneToOne Contact; в случае @Embeddable Contact - убрать
//        }


        return dao.save(entity);
    }

    @Transactional(readOnly = true)
    public User findOne(String s) {
        return dao.findOne(s);
    }

    @Transactional(readOnly = true)
    public User getOne(String s) {
        return dao.getOne(s);
    }

    @Transactional(readOnly = true)
    public boolean exists(String s) {
        return dao.exists(s);
    }

    @Transactional
    public void delete(String s) {
        dao.delete(s);
    }

    @Transactional
    public void delete(User entity) {
        dao.delete(entity);
    }


}
