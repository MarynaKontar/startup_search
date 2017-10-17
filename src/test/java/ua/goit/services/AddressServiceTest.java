package ua.goit.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ua.goit.configuration.TestServicesConfiguration;
import ua.goit.dao.*;
import ua.goit.entity.Address;
import ua.goit.entity.enums.Country;

import static org.junit.Assert.*;

/**
 * Created by Vitalii Proskura on 15.10.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration (classes = {TestServicesConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddressServiceTest {


    @Autowired
    private AddressDao addressDao;


    private AddressService addressService;


    @Before
    public void setUp() throws Exception {
        addressService = new AddressService(addressDao);
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(3, addressService.findAll().size());
    }


    @Test
    public void save() throws Exception {
        Address address = new Address("city", "region", Country.AFGHANISTAN);
        assertEquals(3, addressService.findAll().size());
        addressService.save(address);
        assertEquals(4, addressService.findAll().size());
    }

    @Test
    public void findOne() throws Exception {
        Address address = addressService.findOne(1L);
        assertEquals(1L, (long)address.getId());
    }

    @Test
    public void exists() throws Exception {
        assertTrue(addressService.exists(1L));
        assertTrue(addressService.exists(2L));
        assertTrue(addressService.exists(3L));
        assertFalse(addressService.exists(4L));
    }


}