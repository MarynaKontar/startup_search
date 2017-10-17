package ua.goit.entity;

import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ua.goit.entity.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Embedded
    private Contact contact;
    private String profileFotoLink;
    private String personalPageFotoLink;
    private String youtubeLink;
    private String aboutMe;
    private String skills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Experience> experiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Education> educations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Project> projects;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Interest> interests;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles")
    @Column(name = "role")
    private Collection<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getProfileFotoLink() {
        return profileFotoLink;
    }

    public void setProfileFotoLink(String profileFotoLink) {
        this.profileFotoLink = profileFotoLink;
    }

    public String getPersonalPageFotoLink() {
        return personalPageFotoLink;
    }

    public void setPersonalPageFotoLink(String personalPageFotoLink) {
        this.personalPageFotoLink = personalPageFotoLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Collection<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Collection<Experience> experiences) {
        this.experiences = experiences;
    }

    public Collection<Education> getEducations() {
        return educations;
    }

    public void setEducations(Collection<Education> educations) {
        this.educations = educations;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Collection<Interest> interests) {
        this.interests = interests;
    }

    public void addExperience(Experience experience) {
        if (experiences == null) {
            experiences = new HashSet<>(0);
        }
        experiences.add(experience);
        experience.setUser(this);
    }

    public void removeExperience(Experience experience) {
        if (experiences != null) {
            experiences.remove(experience);
            experience.setUser(null);
        }
    }

    public void addEducation(Education education) {
        if (educations == null) {
            educations = new HashSet<>(0);
        }
        educations.add(education);
        education.setUser(this);
    }

    public void removeEducatione(Education education) {
        if (educations != null) {
            educations.remove(education);
            education.setUser(null);
        }
    }

    public void addProject(Project project) {
        if (projects == null) {
            projects = new HashSet<>(0);
        }
        projects.add(project);
        project.setUser(this);
    }

    public void removeProject(Project project) {
        if (projects != null) {
            projects.remove(project);
            project.setUser(null);
        }
    }

    public void addInterest(Interest interest) {
        if (interests == null) {
            interests = new HashSet<>(0);
        }
        interests.add(interest);
        interest.setUser(this);
    }

    public void removeInterest(Interest interest) {
        if (interests != null) {
            interests.remove(interest);
            interest.setUser(null);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
