package ua.goit.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.goit.entity.enums.Country;

import static org.junit.Assert.*;

/**
 * Created by User on 05.10.2017.
 */
public class AddressTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void creationTest() {
        Address address = null;
        assertNull(address);

        address = new Address();
        assertNotNull(address);

        address.setId(1l);
        assertEquals(1L, (long)address.getId());

        address.setCity("city1");
        assertEquals("city1", address.getCity());

        address.setRegion("a");
        assertEquals("a", address.getRegion());

        address.setCountry(Country.AFGHANISTAN);
        assertEquals(Country.AFGHANISTAN, address.getCountry());

    }

    @Test
    public void hashCodeTest () {
        Address address1 = new Address("city1", "region1" , Country.AFGHANISTAN);
        address1.setId(1L);

        Address address2 = new Address("city1", "region1" , Country.AFGHANISTAN);
        address2.setId(1L);

        assertEquals(address1.hashCode(), address2.hashCode());

        }

    @Test
    public void equalsTest () {
        Address address1 = new Address("city1", "region1" , Country.AFGHANISTAN);
        address1.setId(1L);
        Address address2 = new Address("city2", "region2" , Country.ALBANIA);
        address2.setId(2L);
        assertNotEquals(address1, address2);

        address2.setId(1L);
        assertNotEquals(address1, address2);

        address2.setCity("city1");
        assertNotEquals(address1, address2);

        address2.setRegion("region1");
        assertNotEquals(address1, address2);

        address2.setCountry(Country.AFGHANISTAN);
        assertEquals(address1, address2);
    }

    @After
    public void tearDown() throws Exception {
    }

}