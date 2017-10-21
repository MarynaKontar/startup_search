package ua.goit.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for {@link BusinessPlan}
 */
public class BusinessPlanTest {

    @Test
    public void creationTest () {
        BusinessPlan businessPlan = null;
        assertNull(businessPlan);

        businessPlan = new BusinessPlan();
        assertNotNull(businessPlan);

        businessPlan.setId(1L);
        assertEquals(1L, (long) businessPlan.getId());

        businessPlan.setIdea("a");
        assertEquals("a", businessPlan.getIdea());

        businessPlan.setMarket("m");
        assertEquals("m", businessPlan.getMarket());

        businessPlan.setCurrentState("s");
        assertEquals("s", businessPlan.getCurrentState());
    }

    @Test
    public void hashCodeTest () {
        BusinessPlan businessPlan1 = new BusinessPlan("idea1", "state1", "market1");
        businessPlan1.setId(1L);
        BusinessPlan businessPlan2 = new BusinessPlan("idea1", "state1", "market1");
        businessPlan2.setId(1L);

        assertEquals(businessPlan1.hashCode(), businessPlan2.hashCode());

    }

    @Test
    public void equalsTest () {
        BusinessPlan businessPlan1 = new BusinessPlan("idea1", "state1", "market1");
        businessPlan1.setId(1L);
        BusinessPlan businessPlan2 = new BusinessPlan("idea2", "state2", "market2");
        businessPlan2.setId(2L);

        assertNotEquals(businessPlan1, businessPlan2);

        businessPlan2.setId(1L);
        assertNotEquals(businessPlan1, businessPlan2);

        businessPlan2.setIdea("idea1");
        assertNotEquals(businessPlan1, businessPlan2);

        businessPlan2.setCurrentState("state1");
        assertNotEquals(businessPlan1, businessPlan2);

        businessPlan2.setMarket("market1");
        assertEquals(businessPlan1, businessPlan2);
    }

}