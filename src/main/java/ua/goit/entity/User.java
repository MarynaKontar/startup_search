package ua.goit.entity;

import ua.goit.entity.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Maryna Kontar on 23.08.2017.
 *
 * @KontarMaryna
 */
@Entity
@Table(name = "user")
public class User {

//для случая, если захотим хранить и id и NaturalId
//    @Id
//    Long id;
//    @NaturalId

    @Id
    private String username;
    private String password;
    @Embedded
    private Contact contact;
    private String profileFotoLink;
    private String personalPageFotoLink;
    private String youtubeLink;
    private String aboutMe;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Experience> experiences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Education> educations;

    private String skills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Project> projects;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles")
    @Column(name = "role")
    private Collection<Role> roles;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (contact != null ? !contact.equals(user.contact) : user.contact != null) return false;
        if (profileFotoLink != null ? !profileFotoLink.equals(user.profileFotoLink) : user.profileFotoLink != null)
            return false;
        if (personalPageFotoLink != null ? !personalPageFotoLink.equals(user.personalPageFotoLink) : user.personalPageFotoLink != null)
            return false;
        if (youtubeLink != null ? !youtubeLink.equals(user.youtubeLink) : user.youtubeLink != null) return false;
        if (aboutMe != null ? !aboutMe.equals(user.aboutMe) : user.aboutMe != null) return false;
        if (experiences != null ? !experiences.equals(user.experiences) : user.experiences != null) return false;
        if (educations != null ? !educations.equals(user.educations) : user.educations != null) return false;
        if (skills != null ? !skills.equals(user.skills) : user.skills != null) return false;
        if (projects != null ? !projects.equals(user.projects) : user.projects != null) return false;
        return roles != null ? roles.equals(user.roles) : user.roles == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (profileFotoLink != null ? profileFotoLink.hashCode() : 0);
        result = 31 * result + (personalPageFotoLink != null ? personalPageFotoLink.hashCode() : 0);
        result = 31 * result + (youtubeLink != null ? youtubeLink.hashCode() : 0);
        result = 31 * result + (aboutMe != null ? aboutMe.hashCode() : 0);
        result = 31 * result + (experiences != null ? experiences.hashCode() : 0);
        result = 31 * result + (educations != null ? educations.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        result = 31 * result + (projects != null ? projects.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contact=" + contact +
                ", profileFotoLink='" + profileFotoLink + '\'' +
                ", personalPageFotoLink='" + personalPageFotoLink + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
//                ", experiences=" + experiences +
//                ", educations=" + educations +
//                ", skills='" + skills + '\'' +
//                ", projects=" + projects +
                ", roles=" + roles +
                '}';
    }
}
