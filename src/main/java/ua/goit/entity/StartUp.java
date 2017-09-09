package ua.goit.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by User on 09.09.2017.
 */
//@Entity
//@Table(name = "startUp")
public class StartUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String startupName;
    @Column(insertable = false)
    private Timestamp creationDate;
    private BigDecimal budget;
    private BigDecimal earnedMoney;
    private User owner;

    //TODO Прописать маппинг, когда точно определятся с полями
//    @ElementCollection
//    @CollectionTable(name = "investor_money")
//    @MapKeyJoinColumn(name = "investor_id", referencedColumnName = "id")
    private Map<Investor, BigDecimal> investors;

    public int getId() {
        return id;
    }

    public String getStartupName() {
        return startupName;
    }

    public void setStartupName(String startupName) {
        this.startupName = startupName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(BigDecimal earnedMoney) {
        this.earnedMoney = earnedMoney;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Map<Investor, BigDecimal> getInvestors() {
        return investors;
    }

    public void setInvestors(Map<Investor, BigDecimal> investors) {
        this.investors = investors;
    }
}
