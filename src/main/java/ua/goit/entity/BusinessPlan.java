package ua.goit.entity;

import javax.persistence.*;

/**
 *
 */


@Entity
@Table(name = "businessplan")
public class BusinessPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idea;

    private String currentState;

    private String market;

    @ManyToOne
    private Project project;

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessPlan that = (BusinessPlan) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idea != null ? !idea.equals(that.idea) : that.idea != null) return false;
        if (currentState != null ? !currentState.equals(that.currentState) : that.currentState != null) return false;
        if (market != null ? !market.equals(that.market) : that.market != null) return false;
        return project != null ? project.equals(that.project) : that.project == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idea != null ? idea.hashCode() : 0);
        result = 31 * result + (currentState != null ? currentState.hashCode() : 0);
        result = 31 * result + (market != null ? market.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BusinessPlan{" +
                "id=" + id +
                ", idea='" + idea + '\'' +
                ", currentState='" + currentState + '\'' +
                ", market='" + market + '\'' +
                ", project=" + project.getName() +
                '}';
    }
}
