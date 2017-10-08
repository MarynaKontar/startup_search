package ua.goit.entity.enums;

/**
 * List of roles
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
public enum Role {
    ADMIN, USER;

    public String getLabel(){
        return name().substring(0,1) + name().substring(1).toLowerCase();
    }
}
