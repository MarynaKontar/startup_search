package ua.goit.entity;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by User on 09.09.2017.
 */
//@Entity
public class Investor extends User {

    private List<StartUp> startUps;

    public List<StartUp> getStartUps() {
        return startUps;
    }

    public void setStartUps(List<StartUp> startUps) {
        this.startUps = startUps;
    }


}
