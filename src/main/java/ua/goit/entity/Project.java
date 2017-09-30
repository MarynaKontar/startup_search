package ua.goit.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ua.goit.entity.enums.Industry;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */

@Entity
@Table(name = "project")
public class Project {

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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection( LazyCollectionOption.FALSE )
    private Set<BusinessPlan> businessPlans;

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

    public Set<BusinessPlan> getBusinessPlans() {
        return businessPlans;
    }

    public void setBusinessPlans(Set<BusinessPlan> businessPlans) {
        this.businessPlans = businessPlans;
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

    public void addBusinessPlan(BusinessPlan businessPlan) {
        if (businessPlans == null) {
            businessPlans = new HashSet<>(0);
        }
        businessPlans.add(businessPlan);
        businessPlan.setProject(this);
    }

    public void removeBusinessPlan(BusinessPlan businessPlan) {
        if (businessPlans != null) {
            businessPlans.remove(businessPlan);
            businessPlan.setProject(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (isActive != project.isActive) return false;
        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
//        if (user != null ? !user.equals(project.user) : project.user != null) return false;
        if (funds != null ? !funds.equals(project.funds) : project.funds != null) return false;
        if (minInvestment != null ? !minInvestment.equals(project.minInvestment) : project.minInvestment != null)
            return false;
        if (industry != project.industry) return false;
        if (address != null ? !address.equals(project.address) : project.address != null) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (businessPlans != null ? !businessPlans.equals(project.businessPlans) : project.businessPlans != null)
            return false;
        return lastChange != null ? lastChange.equals(project.lastChange) : project.lastChange == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (funds != null ? funds.hashCode() : 0);
        result = 31 * result + (minInvestment != null ? minInvestment.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (businessPlans != null ? businessPlans.hashCode() : 0);
        result = 31 * result + (lastChange != null ? lastChange.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", user=" + user +
                ", funds=" + funds +
                ", minInvestment=" + minInvestment +
                ", industry=" + industry +
//                ", address=" + address +
                ", description='" + description + '\'' +
//                ", businessPlans=" + businessPlans +
                ", lastChange=" + lastChange +
                ", isActive=" + isActive +
                '}';
    }
}
