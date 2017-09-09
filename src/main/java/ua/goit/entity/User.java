package ua.goit.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by User on 23.08.2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String password;
    private String email;

    @ElementCollection(targetClass=Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="user_roles")
    @Column(name="role") // Column name in user_roles
    private Collection<Role> roles;

    //TODO 6 обсудить поля

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
