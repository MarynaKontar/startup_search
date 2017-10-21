package ua.goit.entity;

import org.junit.Before;
import org.junit.Test;
import ua.goit.entity.enums.Country;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Test for {@link Address}
 *
 */

public class AddressTest {

    private Address address1;
    private Address address2;
    @Before
    public void setUp() throws Exception {
        address1 = new Address("city1", "region1" , Country.AFGHANISTAN);
        address1.setId(1L);

        address2 = new Address("city1", "region1" , Country.AFGHANISTAN);
        address2.setId(1L);
    }

    @Test
    public void creationTest() {
        address1.setId(1l);
        assertEquals(1L, (long)address1.getId());

        address1.setCity("city1");
        assertEquals("city1", address1.getCity());

        address1.setRegion("region1");
        assertEquals("region1", address1.getRegion());

        address1.setCountry(Country.AFGHANISTAN);
        assertEquals(Country.AFGHANISTAN, address1.getCountry());
    }

    @Test
    public void hashCodeTest () {
        assertEquals(address1.hashCode(), address2.hashCode());
       address2.setId(2L);
        assertNotEquals(address1.hashCode(), address2.hashCode());
        address2.setId(1L);
        address2.setCity("city2");
        assertNotEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    public void equalsTest () {
        assertReflectionEquals("Address fields is equals ", address1, address2);
        assertEquals("assert equals ()", address1, address2);
        assertTrue("equals by method",address1.equals(address2));
        address2.setId(2L);
        assertFalse(address1.equals(address2));
        address2.setId(1L);
        address2.setCity("city2");
        assertFalse(address1.equals(address2));
    }
}