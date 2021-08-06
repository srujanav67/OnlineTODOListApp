package com.deloitte.ca.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.deloitte.ca.OnlineTodoListApplication;
import com.deloitte.ca.entity.Role;
import com.deloitte.ca.repository.RoleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OnlineTodoListApplication.class)
@WebAppConfiguration
public class RoleRepositoryTests {
	 @Autowired
    private RoleRepository roleRepository;
    
    @Before
    public void setUp() { }
    
    @Test
    public void whenFindOneByName_thenReturnRole() {
        
    	Role r1 = new Role("USER");
    	roleRepository.findOneByName("USER");
        assertEquals(r1.getName(), "USER");
    }
}
