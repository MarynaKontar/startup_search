package ua.goit.entity;

import ua.goit.entity.enums.ModeOfStudy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
@Entity
@Table(name = "education")
public class Education implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String educationalInstitution;
    private String educationalStage;
    private String faculty;
    private String fieldOfStudy;

    @Enumerated(EnumType.STRING)
    private ModeOfStudy modeOfStudy;
    private LocalDate fromDate;
    private LocalDate untilDate;

    public Education() {
    }

    public Education(String educationalInstitution, String educationalStage, String faculty,
                     String fieldOfStudy, ModeOfStudy modeOfStudy, LocalDate fromDate, LocalDate untilDate) {
        this.educationalInstitution = educationalInstitution;
        this.educationalStage = educationalStage;
        this.faculty = faculty;
        this.fieldOfStudy = fieldOfStudy;
        this.modeOfStudy = modeOfStudy;
        this.fromDate = fromDate;
        this.untilDate = untilDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public String getEducationalStage() {
        return educationalStage;
    }

    public void setEducationalStage(String educationalStage) {
        this.educationalStage = educationalStage;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public ModeOfStudy getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(ModeOfStudy modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(LocalDate untilDate) {
        this.untilDate = untilDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Education education = (Education) o;

        if (id != null ? !id.equals(education.id) : education.id != null) return false;
//        if (user != null ? !user.equals(education.user) : education.user != null) return false;
        if (educationalInstitution != null ? !educationalInstitution.equals(education.educationalInstitution) : education.educationalInstitution != null)
            return false;
        if (educationalStage != null ? !educationalStage.equals(education.educationalStage) : education.educationalStage != null)
            return false;
        if (faculty != null ? !faculty.equals(education.faculty) : education.faculty != null) return false;
        if (fieldOfStudy != null ? !fieldOfStudy.equals(education.fieldOfStudy) : education.fieldOfStudy != null)
            return false;
        if (modeOfStudy != education.modeOfStudy) return false;
        if (fromDate != null ? !fromDate.equals(education.fromDate) : education.fromDate != null) return false;
        return untilDate != null ? untilDate.equals(education.untilDate) : education.untilDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (educationalInstitution != null ? educationalInstitution.hashCode() : 0);
        result = 31 * result + (educationalStage != null ? educationalStage.hashCode() : 0);
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0);
        result = 31 * result + (fieldOfStudy != null ? fieldOfStudy.hashCode() : 0);
        result = 31 * result + (modeOfStudy != null ? modeOfStudy.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (untilDate != null ? untilDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", user=" + user +
                ", educationalInstitution='" + educationalInstitution + '\'' +
                ", educationalStage='" + educationalStage + '\'' +
                ", faculty='" + faculty + '\'' +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", modeOfStudy=" + modeOfStudy +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                '}';
    }
}
