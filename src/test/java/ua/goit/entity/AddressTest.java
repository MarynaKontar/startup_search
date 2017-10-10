package ua.goit.entity;

import org.junit.Before;
import org.junit.Test;
import ua.goit.entity.enums.Country;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

//import org.unitils.reflectionassert.ReflectionAssert;


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

        address = new Address();

        address.setId(1l);
        assertEquals(1L, (long)address.getId());

        address.setCity("city");
        assertEquals("city", address.getCity());

        address.setRegion("region");
        assertEquals("region", address.getRegion());

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
        Address address2 = new Address("city1", "region1" , Country.AFGHANISTAN);
        address2.setId(1L);


        assertReflectionEquals("Address fields is equals ", address1, address2);
        assertEquals("assert equals ()", address1, address2);
    }



}