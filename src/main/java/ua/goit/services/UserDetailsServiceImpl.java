package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.entity.*;


/**
 * This is implementation for user {@link ua.goit.entity.User} detailed service which will use
 * {@link ua.goit.services.UserService} as users repository.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = userService.findOne(userLogin);
        if (user == null) {
            throw new UsernameNotFoundException("User " + userLogin + "is not found");
        }
        return new UserDetailsExt(user);
    }
}
