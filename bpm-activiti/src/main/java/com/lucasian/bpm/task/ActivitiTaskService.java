/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucasian.bpm.task;

import com.lucasian.bpm.ProcessEngineFactory;
import com.lucasian.bpm.ActivitiAuth;
import com.lucasian.bpm.activiti.ActivitiConverter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Lucasian
 */
public class ActivitiTaskService implements TaskService{
    
    public void addVariables(String taskId, Map<String, Object> variables) {
        //ActivitiAuth.activitiLogin();
        addVariablesToTask(taskId, variables);
    }

    public void assign(String taskId, String userId) {
        //ActivitiAuth.activitiLogin();
        ProcessEngines.getDefaultProcessEngine().getTaskService().claim(taskId, userId);
    }   

    public void claim(String taskId) {
        //ActivitiAuth.activitiLogin();
        ProcessEngines.getDefaultProcessEngine().getTaskService().claim(taskId, ProcessEngineFactory.findCurrentUser());
    }

    public void delegate(String taskId, String userId) {
        //ActivitiAuth.activitiLogin();
        ProcessEngines.getDefaultProcessEngine().getTaskService().delegateTask(taskId, userId);
    }

    public void execute(String taskId) {
        //ActivitiAuth.activitiLogin();
        start(taskId);
        finish(taskId);
    }

    public void execute(String taskId, Map<String, Object> variables) {
        //ActivitiAuth.activitiLogin();
        start(taskId, variables);
        finish(taskId);
    }

    public void finish(String taskId) {
        //ActivitiAuth.activitiLogin();
        ProcessEngines.getDefaultProcessEngine().getTaskService().complete(taskId);
    }

    public void finish(String taskId, Map<String, Object> variables) {
        //ActivitiAuth.activitiLogin();
        ProcessEngines.getDefaultProcessEngine().getTaskService().complete(taskId, variables);
    }

    public Collection<Task> listActiveTasks() {
        return listActiveTasksByUser(ProcessEngineFactory.findCurrentUser());
    }

    public Collection<Task> listActiveTasks(String processId) {
        return listActiveTasksByUser(ProcessEngineFactory.findCurrentUser(),processId);
    }

    public Collection<Task> listActiveTasksByUser(String userId) {
        ActivitiAuth.activitiLogin(userId);
        TaskQuery taskQuery = ProcessEngines.getDefaultProcessEngine().getTaskService().createTaskQuery();
        //TODO: Pendientes no tienen asignado y activos ya estan asignados
        List<org.activiti.engine.task.Task> activitiTasks = taskQuery.taskAssignee(userId).list();        
        Collection<Task> tasks = new ArrayList<Task>();
        ActivitiConverter activitiConverter = new ActivitiConverter();
        for(org.activiti.engine.task.Task task: activitiTasks){
                tasks.add(activitiConverter.convertTask(task, this));
        }
        return tasks;
    }

    public Collection<Task> listActiveTasksByUser(String userId, String processId) {
        ActivitiAuth.activitiLogin(userId);
        TaskQuery taskQuery = ProcessEngines.getDefaultProcessEngine().getTaskService().createTaskQuery();
        //TODO: preguntarle a edu si es el identificador de la instancia o de la definicion del proceso       
        List<org.activiti.engine.task.Task> activitiTasks = taskQuery.taskAssignee(userId).processInstanceId(processId).list();        
        Collection<Task> tasks = new ArrayList<Task>();
        ActivitiConverter activitiConverter = new ActivitiConverter();
        for(org.activiti.engine.task.Task task: activitiTasks){
                tasks.add(activitiConverter.convertTask(task, this));
        }
        return tasks;       
    }

    public Collection<Task> listPendingTasks() {
        return listPendingTasksByUser(ProcessEngineFactory.findCurrentUser());
    }

    public Collection<Task> listPendingTasks(String processId) {
        return listPendingTasksByUser(ProcessEngineFactory.findCurrentUser(), processId);
    }

    public Collection<Task> listPendingTasksByUser(String userId) {
        ActivitiAuth.activitiLogin(userId);
        TaskQuery taskQuery = ProcessEngines.getDefaultProcessEngine().getTaskService().createTaskQuery();
        //TODO: preguntarle a edu que condiciones debe de cumplir la consulta
        List<org.activiti.engine.task.Task> activitiTasks = taskQuery.taskUnnassigned().list();        
        Collection<Task> tasks = new ArrayList<Task>();
        ActivitiConverter activitiConverter = new ActivitiConverter();
        for(org.activiti.engine.task.Task task: activitiTasks){
                tasks.add(activitiConverter.convertTask(task, this));
        }
        return tasks;
    }

    public Collection<Task> listPendingTasksByUser(String userId, String processId) {
        ActivitiAuth.activitiLogin(userId);
        TaskQuery taskQuery = ProcessEngines.getDefaultProcessEngine().getTaskService().createTaskQuery();
        //TODO: preguntarle a edu si es el identificador de la instancia o de la definicion del proceso       
        List<org.activiti.engine.task.Task> activitiTasks = taskQuery.taskUnnassigned().processInstanceId(processId).list();        
        Collection<Task> tasks = new ArrayList<Task>();
        ActivitiConverter activitiConverter = new ActivitiConverter();
        for(org.activiti.engine.task.Task task: activitiTasks){
                tasks.add(activitiConverter.convertTask(task, this));            
        }
        return tasks;        
    }

    public void resume(String taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void start(String taskId) {
        //ActivitiAuth.activitiLogin();
        ProcessEngines.getDefaultProcessEngine().getTaskService().newTask(taskId);
        
    }

    public void start(String taskId, Map<String, Object> variables) {
        //ActivitiAuth.activitiLogin();
        org.activiti.engine.task.Task task = ProcessEngines.getDefaultProcessEngine().getTaskService().newTask(taskId);
        addVariablesToTask(task.getId(), variables);
    }

    public void suspend(String taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void unassign(String taskId) {
        //ActivitiAuth.activitiLogin();
        ProcessEngines.getDefaultProcessEngine().getTaskService().setAssignee(taskId, null);
    }
    private void addVariablesToTask(String taskId, Map<String,Object> variables){
        ProcessEngines.getDefaultProcessEngine().getTaskService().setVariables(taskId, variables);        
    }
}
