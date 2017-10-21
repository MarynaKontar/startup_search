package ua.goit.entity;

import org.junit.Test;
import ua.goit.entity.enums.Country;

import static org.junit.Assert.*;

/**
 * Test for {@link Contact}
 */
public class ContactTest {

    @Test
    public void creationTest () {
        Contact contact = null;
        assertNull(contact);

        contact = new Contact();
        assertNotNull(contact);

        contact = new Contact("e", "p", "c", Country.AFGHANISTAN);
        assertEquals("e", contact.getEmail());
        assertEquals("p", contact.getPhoneNumber());
        assertEquals("c", contact.getCity());
        assertEquals(Country.AFGHANISTAN, contact.getCountry());

        contact.setEmail("e1");
        assertEquals("e1", contact.getEmail());

        contact.setPhoneNumber("p1");
        assertEquals("p1", contact.getPhoneNumber());

        contact.setCity("c1");
        assertEquals("c1", contact.getCity());

        contact.setCountry(Country.ALBANIA);
        assertEquals(Country.ALBANIA, contact.getCountry());
    }

    @Test
    public void equalsTest()  {
        Contact contact1 = new Contact("e1", "p1", "c1", Country.AFGHANISTAN);
        Contact contact2 = new Contact("e2", "p2", "c2", Country.ALBANIA);
        assertFalse(contact1.equals(contact2));

        contact2.setEmail("e1");
        assertFalse(contact1.equals(contact2));

        contact2.setPhoneNumber("p1");
        assertFalse(contact1.equals(contact2));

        contact2.setCity("c1");
        assertFalse(contact1.equals(contact2));

        contact2.setCountry(Country.AFGHANISTAN);
        assertTrue(contact1.equals(contact2));


    }

    @Test
    public void hashCodeTest()  {
        Contact contact1 = new Contact("e1", "p1", "c1", Country.AFGHANISTAN);
        Contact contact2 = new Contact("e1", "p1", "c1", Country.AFGHANISTAN);

        assertEquals(contact1.hashCode(), contact2.hashCode());
    }

}