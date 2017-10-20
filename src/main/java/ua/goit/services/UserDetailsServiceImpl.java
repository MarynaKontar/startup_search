package ua.goit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.entity.*;


/**
 * Implementation for user {@link ua.goit.entity.User} detailed service which will use
 * {@link ua.goit.services.UserService} as users repository.
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Implementation of {@link UserDetailsService} method
     *
     * @param username identifying the user whose data is required
     * @return a {@link UserDetailsExt} if such {@link User} exists
     * @throws UsernameNotFoundException if user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + "is not found");
        }
        return new UserDetailsExt(user);
    }
}
