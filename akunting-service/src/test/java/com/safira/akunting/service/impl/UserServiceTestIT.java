package com.safira.akunting.service.impl;

import com.safira.akunting.domain.User;
import com.safira.akunting.service.SystemRestfulService;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:com/safira/akunting/**/applicationContext.xml")
public class UserServiceTestIT {

    @Autowired
    private SystemRestfulService service;

    @Test
    public void testFindById() {
        User ac = service.findUserById("admin");
        assertNotNull(ac);
        assertEquals("admin", ac.getUsername());
        assertEquals("Administrator", ac.getFullname());
        assertEquals("admin", ac.getPassword());
        assertEquals(Boolean.TRUE, ac.getActive());
        assertEquals("Super User", ac.getRole().getName());
        
        assertNull(service.findUserById(null));
        assertNull(service.findUserById(""));
    }

    @Test
    public void testFindAll() {
        Page<User> result = service.findAllUsers(new PageRequest(0, service.countAllUsers().intValue()));
        assertTrue(result.getTotalElements() > 0);
    }

    @Test
    public void testFindByUsername() {
        assertNotNull(service.findUserByUsername("admin"));
        assertNull(service.findUserByUsername("stafxx"));
    }
}
