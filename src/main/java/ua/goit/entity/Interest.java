package ua.goit.entity;

import ua.goit.entity.enums.Country;
import ua.goit.entity.enums.Industry;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *
 * @KontarMaryna
 */
@Entity
@Table(name = "interest")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int budget;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.STRING)
    private Industry industry;

    private LocalDate lastChange;

    public Interest() {
    }

    public Interest(String name, String description, int budget, Country country, Industry industry, LocalDate lastChange) {
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.country = country;
        this.industry = industry;
        this.lastChange = lastChange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public LocalDate getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDate lastChange) {
        this.lastChange = lastChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interest interest = (Interest) o;

        if (budget != interest.budget) return false;
        if (!id.equals(interest.id)) return false;
        if (!name.equals(interest.name)) return false;
        if (!description.equals(interest.description)) return false;
        if (!user.equals(interest.user)) return false;
        if (country != interest.country) return false;
        if (industry != interest.industry) return false;
        return lastChange.equals(interest.lastChange);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + budget;
        result = 31 * result + user.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + industry.hashCode();
        result = 31 * result + lastChange.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", budget=" + budget +
//                ", user=" + user +
                ", country=" + country +
                ", industry=" + industry +
                ", lastChange=" + lastChange +
                '}';
    }
}
