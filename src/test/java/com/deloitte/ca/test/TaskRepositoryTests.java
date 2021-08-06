package com.deloitte.ca.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.deloitte.ca.OnlineTodoListApplication;
import com.deloitte.ca.entity.Role;
import com.deloitte.ca.entity.Task;
import com.deloitte.ca.entity.User;
import com.deloitte.ca.repository.TaskRepository;
import com.deloitte.ca.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OnlineTodoListApplication.class)
@WebAppConfiguration
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
 
    private User u;
    private Task t;
    private Task t1;
    
    @Before
    public void setUp() {       
    }
    
    @After
    public void tearDown() {
    }
      
    @Test
    public void whenGetTaskById_thenReturnTask() {
    	
    	Set<Role> rs = new HashSet<Role>();
    	u = new User("testxx", "abc", "testxx@mail.com", bCryptPasswordEncoder.encode("testabc12"), rs);     
    	userRepository.save(u);
    	
    	t = new Task();
        t.setId(1L);
        t.setTaskname("New Task 01");
        t.setCreated(new Date());
        t.setUpdated(new Date());
        t.setUser(u);
        t.setIsTaskCompleted(false);

        taskRepository.save(t);
        
    	Task ts = taskRepository.findById(1L).get();
    	assertEquals(ts.getTaskname(), "New Task 01");
    }
    
    @Test
	public void whenGetTaskByInvalidId_thenReturnEmptyList() {

    	List<Task> ts1 = taskRepository.findAllByUserId(10L);
		assertTrue(ts1.isEmpty());
	}
    
	@Test
	public void whenGetTaskStatus_thenReturnIsTaskCompleted() {
    	
		Set<Role> rs = new HashSet<Role>();
    	u = new User("testyy", "xyz", "testyy@mail.com", bCryptPasswordEncoder.encode("testabc12"), rs);     
    	userRepository.save(u);
    	
    	t = new Task();
        t.setId(1L);
        t.setTaskname("New Task 01");
        t.setCreated(new Date());
        t.setUpdated(new Date());
        t.setUser(u);
        t.setIsTaskCompleted(false);

        taskRepository.save(t);
        
    	Boolean isTaskCompleted = t.getIsTaskCompleted();
    	assertEquals(isTaskCompleted, false);
	}
	
	@Test
	public void whenFindAllByUserId_thenReturnTasks() {
		
		Set<Role> rs = new HashSet<Role>();
    	u = new User("testzz", "xyz", "testzz@mail.com", bCryptPasswordEncoder.encode("testabc12"), rs);     
    	userRepository.save(u);
    	
    	t = new Task();
        t.setId(1L);
        t.setTaskname("New Task 01");
        t.setCreated(new Date());
        t.setUpdated(new Date());
        t.setUser(u);
        t.setIsTaskCompleted(false);

        taskRepository.save(t);
        
		t1 = new Task();
	    t1.setId(2L);
	    t1.setTaskname("Do Task01");
	    t1.setCreated(new Date());
	    t1.setUpdated(new Date());
	    t1.setUser(u);
	    t1.setIsTaskCompleted(false);
	
		taskRepository.save(t1);
		
		/* whenFindAllByUserId_thenReturnTasks */
		List<Task> ts = taskRepository.findAllByUserId(u.getId());
	    assertEquals(ts.get(0).getTaskname(), "New Task 01");
	    assertEquals(ts.get(1).getTaskname(), "Do Task01");
		
	}
	
}