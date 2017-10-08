package ua.goit.entity.enums;

/**
 * List of Mode of study
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
public enum  ModeOfStudy {
    FULL_TIME, REMOTE, EVENING, EXTRAMURAL;

    public String getLabel(){
        return name().substring(0,1) + name().substring(1).toLowerCase();
    }
}
