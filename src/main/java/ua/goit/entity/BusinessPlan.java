package ua.goit.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
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



}
