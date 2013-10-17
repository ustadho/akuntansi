package com.safira.akunting.service.impl;

import com.safira.akunting.domain.Permission;
import com.safira.akunting.domain.Role;
import com.safira.akunting.service.SystemRestfulService;
import java.util.List;
import org.junit.Assert;
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
public class PermissionServiceTestIT {

    @Autowired
    private SystemRestfulService service;

    @Test
    public void testFindById() {
        Permission ac = service.findPermissionById("user-edit");
        assertNotNull(ac);
        assertEquals("Edit User", ac.getLabel());
        assertEquals("ROLE_USER_EDIT", ac.getValue());
        assertNull(service.findPermissionById(null));
        assertNull(service.findPermissionById(""));
    }

    @Test
    public void testFindAll() {
        Page<Permission> result = service.findAllPermissions(new PageRequest(0, service.countAllPermissions().intValue()));
        assertTrue(result.getTotalElements() > 0);
    }
    
    @Test
    public void testFindNotInRole() {
        Role r = new Role();
        r.setId("staff");
        
        List<Permission> hasil = service.findPermissionsNotInRole(r);
        assertEquals(new Integer(4), new Integer(hasil.size()));
        
        for (Permission permission : hasil) {
            if(permission.getId().equals("role-view")){
                Assert.fail("Seharusnya tidak ada permission untuk view");
            }
        }
    }
}
