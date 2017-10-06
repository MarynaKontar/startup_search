package ua.goit.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.goit.entity.enums.Role;

import java.util.ArrayList;
import java.util.Collection;

/**
* Class for hold information about {@link ua.goit.entity.User}
*/

public class UserDetailsExt implements UserDetails {
   private User user;
   private Collection<SimpleGrantedAuthority> grantedAuthorities;

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsExt.class);

   public UserDetailsExt(User user) {
       LOGGER.info("Create UserDetailsExt wrapping around {}", user);
       this.user = user;
       this.grantedAuthorities = new ArrayList<>();//use Set if need
       Collection<Role> roles = user.getRoles();
       roles.forEach(role->grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return grantedAuthorities;
   }

    @Override
   public String getPassword() {
        LOGGER.trace("Getting password");
       return user.getPassword();
   }

    @Override
    public String getUsername() {
        LOGGER.trace("Getting login");
       return user.getUsername();
    }

    @Override
   public boolean isAccountNonExpired() {
        LOGGER.trace("Setting isAccountNonExpired");
        return true;
   }

   @Override
   public boolean isAccountNonLocked() {
       LOGGER.trace("Setting isAccountNonLocked");
       return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
       LOGGER.trace("Setting isCredentialsNonExpired");
       return true;
   }

   @Override
   public boolean isEnabled() {
       LOGGER.trace("Setting isEnabled");
       return true;
   }

    public Long getId() {
        return user.getId();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public Contact getContact() {
        return user.getContact();
    }

    public String getProfileFotoLink() {
        return user.getProfileFotoLink();
    }

    public String getPersonalPageFotoLink() {
        return user.getPersonalPageFotoLink();
    }

    public String getYoutubeLink() {
        return user.getYoutubeLink();
    }

    public String getAboutMe() {
        return user.getAboutMe();
    }

    public Collection<Experience> getExperiences() {
        return user.getExperiences();
    }

    public Collection<Education> getEducations() {
        return user.getEducations();
    }

    public String getSkills() {
        return user.getSkills();
    }

    public Collection<Project> getProjects() {
        return user.getProjects();
    }

    public Collection<Interest> getInterests() {
        return user.getInterests();
    }

    //TODO 5 Добавлять геттеры для всех новых полей, которые появятся в {@link ua.goit.entity.User}
}
