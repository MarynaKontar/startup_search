package ua.goit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.entity.User;


/**
 * Data access object for {@link ua.goit.entity.User}
 * @KontarMaryna
 */
public interface UserDao extends JpaRepository<User, Long> {


    User findUserByUsername(String username);

//    //TODO 1 Эти jpql будем хранить в БД? (надуманый запрос, просто как пример. Его можно написать и без @Query, он и так сконструируется по названию метода)
//    @Query("select user from User user where user.email like ?1 or user.username like ?2")
//    User findByEmailOrUsername(String email, String username);
}
