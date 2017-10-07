package ua.goit.entity.enums;

/**
 * List of industries
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */
public enum Industry {

    AGRICULTURE, BANKING, COMMUNICATIONS, CONSTRUCTION, ELECTRIC_SERVICES, FISHING, FORESTRY, GAS_SERVICES,
    INSURANCE, INVESTMENTS, MANUFACTURING, MINING, PUBLIC_ADMINISTRATION, REAL_ESTATE, RETAIL_TRADE,
    SANITARY_SERVICES, SERVICES, TRANSPORTATION, WHOLESALE_TRADE;

    public String getLabel(){
        return name().substring(0,1) + name().substring(1).toLowerCase();
    }

}
