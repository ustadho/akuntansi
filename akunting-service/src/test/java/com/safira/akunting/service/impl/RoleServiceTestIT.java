package com.safira.akunting.service.impl;

import com.safira.akunting.domain.Menu;
import com.safira.akunting.domain.Permission;
import com.safira.akunting.domain.Role;
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
public class RoleServiceTestIT {

    @Autowired
    private SystemRestfulService service;

    @Test
    public void testFindById() {
        Role role = service.findRoleById("superuser");
        assertNotNull(role);
        assertEquals("Super User", role.getName());
        assertEquals("Full Access", role.getDescription());
        
        assertNotNull(role.getMenuSet());
        
        for (Menu menu : role.getMenuSet()) {
            assertNotNull(menu.getLabel());
        }
        
        assertNotNull(role.getPermissionSet());
        
        for (Permission perm : role.getPermissionSet()) {
            assertNotNull(perm.getValue());
        }
        
        assertNull(service.findRoleById(null));
        assertNull(service.findRoleById(""));
    }

    @Test
    public void testFindAll() {
        Page<Role> result = service.findAllRoles(new PageRequest(0, service.countAllRoles().intValue()));
        assertTrue(result.getTotalElements() > 0);
    }
}
