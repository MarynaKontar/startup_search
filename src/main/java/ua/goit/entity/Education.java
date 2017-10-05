package ua.goit.entity;

import ua.goit.entity.enums.ModeOfStudy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Maryna Kontar on 23.08.2017.
 *
 * @KontarMaryna
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

        if (!id.equals(education.id)) return false;
        if (!user.equals(education.user)) return false;
        if (!educationalInstitution.equals(education.educationalInstitution)) return false;
        if (!educationalStage.equals(education.educationalStage)) return false;
        if (!faculty.equals(education.faculty)) return false;
        if (!fieldOfStudy.equals(education.fieldOfStudy)) return false;
        if (modeOfStudy != education.modeOfStudy) return false;
        if (!fromDate.equals(education.fromDate)) return false;
        return untilDate.equals(education.untilDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + educationalInstitution.hashCode();
        result = 31 * result + educationalStage.hashCode();
        result = 31 * result + faculty.hashCode();
        result = 31 * result + fieldOfStudy.hashCode();
        result = 31 * result + modeOfStudy.hashCode();
        result = 31 * result + fromDate.hashCode();
        result = 31 * result + untilDate.hashCode();
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
