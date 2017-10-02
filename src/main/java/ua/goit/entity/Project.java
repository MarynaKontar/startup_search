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
    @LazyCollection( LazyCollectionOption.FALSE)
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
    public String toString() {
        return "";
    }
}
