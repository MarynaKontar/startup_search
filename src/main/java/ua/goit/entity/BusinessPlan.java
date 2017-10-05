package ua.goit.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */


@Entity
@Table(name = "businessplan")
public class BusinessPlan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idea;

    private String currentState;

    private String market;

    public BusinessPlan() {
    }

    public BusinessPlan(String idea, String currentState, String market) {
        this.idea = idea;
        this.currentState = currentState;
        this.market = market;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessPlan that = (BusinessPlan) o;

        if (!id.equals(that.id)) return false;
        if (!idea.equals(that.idea)) return false;
        if (!currentState.equals(that.currentState)) return false;
        return market.equals(that.market);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + idea.hashCode();
        result = 31 * result + currentState.hashCode();
        result = 31 * result + market.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BusinessPlan{" +
                "id=" + id +
                ", idea='" + idea + '\'' +
                ", currentState='" + currentState + '\'' +
                ", market='" + market + '\'' +
                '}';
    }
}
