package ua.goit.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Maryna Kontar on 23.08.2017.
 *
 * @KontarMaryna
 */
@Entity
@Table(name = "experience")
public class Experience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    private String company;
    private String position;
    private String responsibility;
    private LocalDate fromDate;
    private LocalDate untilDate;

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

        if (!id.equals(that.id)) return false;
//        if (!user.equals(that.user)) return false;
        if (!company.equals(that.company)) return false;
        if (!position.equals(that.position)) return false;
        if (!responsibility.equals(that.responsibility)) return false;
        if (!fromDate.equals(that.fromDate)) return false;
        return untilDate.equals(that.untilDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + company.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + responsibility.hashCode();
        result = 31 * result + fromDate.hashCode();
        result = 31 * result + untilDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
//                ", user=" + user +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", responsibility='" + responsibility + '\'' +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                '}';
    }
}
