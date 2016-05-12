/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucasian.bpm.activiti;

import com.lucasian.bpm.ActivitiAuth;
import com.lucasian.bpm.ProcessEngine;
import com.lucasian.bpm.ProcessEngineFactory;
import com.lucasian.bpm.management.ManagementService;
import com.lucasian.bpm.management.ProcessDefinitionStatus;
import com.lucasian.bpm.runtime.RuntimeService;
import com.lucasian.bpm.task.TaskService;
import org.activiti.engine.IdentityService;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.*;

/**
 *
 * @author Lucasian
 */
public class ActivitiTest {

    public ActivitiTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        IdentityService identityService = ProcessEngines.getDefaultProcessEngine().getIdentityService();
        ActivitiProcessUserFinder activitiProcessUserFinder = new ActivitiProcessUserFinder();
        ProcessEngineFactory.registerUserFinder(activitiProcessUserFinder);   
        Group newGroup = identityService.newGroup("activitiTest");
        newGroup.setName("activitiTest");
        identityService.saveGroup(newGroup);
        User usuario = identityService.newUser("Angel");
        identityService.saveUser(usuario);
        identityService.createMembership("Angel","activitiTest");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
             
    }
    @Test
    public void testAll(){
        ProcessEngine processEngine = ProcessEngineFactory.getProcessEngine();
        ManagementService managementService= processEngine.getManagementService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();
        ActivitiAuth.activitiLogin("Angel");
        Assert.assertEquals("Angel", ProcessEngineFactory.findCurrentUser());
        Assert.assertEquals(false, ProcessEngineFactory.isUserAdmin(ProcessEngineFactory.findCurrentUser()));
        Assert.assertEquals(true, ProcessEngineFactory.isUserValid(ProcessEngineFactory.findCurrentUser()));
        managementService.deployResource("testProcess.bpmn20.xml");
        Assert.assertEquals(1, managementService.listProcessDefinitions().size());    
        managementService.deactivateProcessDefinition("testProcess", "1");
        Assert.assertEquals(true,managementService.listProcessDefinitions().iterator().next().getStatus()==ProcessDefinitionStatus.INACTIVE);
        managementService.activateProcessDefinition("testProcess", "1");
        Assert.assertEquals(true,managementService.listProcessDefinitions().iterator().next().getStatus()==ProcessDefinitionStatus.ACTIVE);
        Assert.assertEquals(0, taskService.listPendingTasks().size());
        Assert.assertEquals(0, taskService.listActiveTasks().size());
        runtimeService.startProcess("testProcess");
        Assert.assertEquals(1, taskService.listPendingTasks().size());
        Assert.assertEquals(0, taskService.listActiveTasks().size());        
        taskService.claim(taskService.listPendingTasks().iterator().next().getId());
        Assert.assertEquals(0, taskService.listPendingTasks().size());
        Assert.assertEquals(1, taskService.listActiveTasks().size());        
        taskService.start(taskService.listActiveTasks().iterator().next().getId());
        Assert.assertEquals(0, taskService.listPendingTasks().size());
        Assert.assertEquals(1, taskService.listActiveTasks().size());        
        taskService.finish(taskService.listActiveTasks().iterator().next().getId());
        Assert.assertEquals(0, taskService.listPendingTasks().size());
        Assert.assertEquals(0, taskService.listActiveTasks().size());                
        managementService.deleteProcessDefinition("testProcess", "1");
        Assert.assertEquals(0, managementService.listProcessDefinitions().size());

        
    }
    @Test
    public void loginUser(){
       /* activitiAuth.activitiLogin("Angel");        
        Assert.assertEquals("Angel", ProcessEngineFactory.findCurrentUser()); ¨*/       
    }
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
