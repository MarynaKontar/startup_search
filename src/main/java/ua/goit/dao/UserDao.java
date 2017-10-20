package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.User;


/**
 * Data access object for {@link ua.goit.entity.User}
 *
 * @KontarMaryna
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
