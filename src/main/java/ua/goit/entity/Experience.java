package ua.goit.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Maryna Kontar on 23.08.2017.
 *
 * @KontarMaryna
 */
@Entity
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company;
    private String position;
    private String responsibility;
    private LocalDate fromDate;
    private LocalDate untilDate;

    @ManyToOne
    private User user;

    public Experience() {
    }

    public Experience(String company, String position, String responsibility, LocalDate fromDate, LocalDate untilDate) {
        this.company = company;
        this.position = position;
        this.responsibility = responsibility;
        this.fromDate = fromDate;
        this.untilDate = untilDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (responsibility != null ? !responsibility.equals(that.responsibility) : that.responsibility != null)
            return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (untilDate != null ? !untilDate.equals(that.untilDate) : that.untilDate != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (responsibility != null ? responsibility.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (untilDate != null ? untilDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", responsibility='" + responsibility + '\'' +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
//                ", user=" + user +
                '}';
    }
}
