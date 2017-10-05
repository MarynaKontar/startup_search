package ua.goit.entity;

import ua.goit.entity.enums.Industry;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 */

@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User user;

    private BigDecimal funds;

    private BigDecimal minInvestment;

    @Enumerated(EnumType.STRING)
    private Industry industry;

    @OneToOne
    private Address address;

    private String description;

    @OneToOne
    private BusinessPlan businessPlan;

    private LocalDate lastChange;

    private boolean isActive;

    public Project() {
    }

    public Project(String name, BigDecimal funds, BigDecimal minInvestment,
                   Industry industry, Address address, String description,
                   LocalDate lastChange) {
        this.name = name;
        this.funds = funds;
        this.minInvestment = minInvestment;
        this.industry = industry;
        this.address = address;
        this.description = description;
        this.lastChange = lastChange;
        this.isActive = true;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public BigDecimal getMinInvestment() {
        return minInvestment;
    }

    public void setMinInvestment(BigDecimal minInvestment) {
        this.minInvestment = minInvestment;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BusinessPlan getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(BusinessPlan businessPlan) {
        this.businessPlan = businessPlan;
    }

    public LocalDate getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDate lastChange) {
        this.lastChange = lastChange;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (isActive != project.isActive) return false;
        if (!id.equals(project.id)) return false;
        if (!name.equals(project.name)) return false;
        if (!user.equals(project.user)) return false;
        if (!funds.equals(project.funds)) return false;
        if (!minInvestment.equals(project.minInvestment)) return false;
        if (industry != project.industry) return false;
        if (!address.equals(project.address)) return false;
        if (!description.equals(project.description)) return false;
        if (!businessPlan.equals(project.businessPlan)) return false;
        return lastChange.equals(project.lastChange);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + funds.hashCode();
        result = 31 * result + minInvestment.hashCode();
        result = 31 * result + industry.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + businessPlan.hashCode();
        result = 31 * result + lastChange.hashCode();
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", funds=" + funds +
                ", minInvestment=" + minInvestment +
                ", industry=" + industry +
                ", address=" + address +
                ", description='" + description + '\'' +
                ", businessPlan=" + businessPlan +
                ", lastChange=" + lastChange +
                ", isActive=" + isActive +
                '}';
    }
}
