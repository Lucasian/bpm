/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucasian.bpm.activiti;


import com.lucasian.bpm.management.ProcessDefinitionStatus;
import com.lucasian.bpm.task.TaskService;
import java.util.Date;
import java.util.Map;
import javax.annotation.PreDestroy;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.runtime.ProcessInstance;
/**
 *
 * @author Lucasian
 */
public class ActivitiConverter {

    public com.lucasian.bpm.task.Task convertTask(final Task task, final TaskService taskService) {
        class ActivitiTask implements com.lucasian.bpm.task.Task {

            public void delegate(String userId) {
                taskService.delegate(getId(), userId);
            }

            public String getAsignee() {
                return task.getAssignee();
            }

            public Date getDueDate() {
                return task.getDueDate();
            }

            public String getId() {
                return task.getId();
            }

            public String getName() {
                return task.getName();
            }

            public String getStartedBy() {
                return task.getOwner();
            }

            public Date getStartedDate() {
                return task.getCreateTime();
            }
        }
        return new ActivitiTask();
    }

    public com.lucasian.bpm.runtime.Process convertProcessInstance(final ProcessInstance process) {
        class ActivitiProcess implements com.lucasian.bpm.runtime.Process{

            public String getId() {
                return process.getProcessInstanceId();
            }

            public Map<String, Object> getVariables() {
                return ProcessEngines.getDefaultProcessEngine().getRuntimeService().getVariables(process.getProcessInstanceId());
            }

            public boolean isEnded() {
                return process.isEnded();
            }
            
        }
        return new ActivitiProcess();
    }
    public com.lucasian.bpm.management.ProcessDefinition convertProcessDefinition(final ProcessDefinition processDefinition){        
        class ActivitiProcessDefinition implements com.lucasian.bpm.management.ProcessDefinition{

            public String getDeployedBy() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public String getName() {
                return processDefinition.getName();
            }

            public ProcessDefinitionStatus getStatus() {
                if(processDefinition.isSuspended()){
                    return ProcessDefinitionStatus.INACTIVE;
                }else{
                    return ProcessDefinitionStatus.ACTIVE;
                }
            }

            public String getVersion() {
                return processDefinition.getVersion()+"";
            }
            
        }
        return new ActivitiProcessDefinition();
    }
}
