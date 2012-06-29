/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucasian.bpm.runtime;

import com.lucasian.bpm.ActivitiAuth;
import com.lucasian.bpm.activiti.ActivitiConverter;

import java.util.Map;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;


/**
 *
 * @author Lucasian
 */
public class ActivitiRuntimeService implements RuntimeService {    

    public void deleteProcess(String processId, String deleteReason) {
        ProcessEngines.getDefaultProcessEngine().getRuntimeService().deleteProcessInstance(processId, deleteReason);
    }

    public com.lucasian.bpm.runtime.Process findProcess(String processId) {
        ProcessInstanceQuery query = ProcessEngines.getDefaultProcessEngine().getRuntimeService().createProcessInstanceQuery();
        ProcessInstance processInstance = query.processInstanceId(processId).singleResult();
        ActivitiConverter activitiConverter = new ActivitiConverter();
        return activitiConverter.convertProcessInstance(processInstance);           
    }

    public com.lucasian.bpm.runtime.Process startProcess(String processName) {
        //ActivitiAuth.activitiLogin();
        ProcessDefinition processDefinition = getLastActiveDeployedProcess(processName);
        ProcessInstance processInstance = ProcessEngines.getDefaultProcessEngine().getRuntimeService().startProcessInstanceById(processDefinition.getId());
        ActivitiConverter activitiConverter = new ActivitiConverter();
        return activitiConverter.convertProcessInstance(processInstance);
        
    }
    public com.lucasian.bpm.runtime.Process startProcess(String processName, Map<String, Object> variables) {
        //ActivitiAuth.activitiLogin();
        ProcessDefinition processDefinition = getLastActiveDeployedProcess(processName);
        ProcessInstance processInstance = ProcessEngines.getDefaultProcessEngine().getRuntimeService().startProcessInstanceById(processDefinition.getId(),variables);
        ActivitiConverter activitiConverter = new ActivitiConverter();
        return activitiConverter.convertProcessInstance(processInstance);
    }

    public com.lucasian.bpm.runtime.Process startProcess(String userId, String processName) {
        ActivitiAuth.activitiLogin(userId);
        ProcessDefinition processDefinition = getLastActiveDeployedProcess(processName);
        ProcessInstance processInstance = ProcessEngines.getDefaultProcessEngine().getRuntimeService().startProcessInstanceById(processDefinition.getId());
        ActivitiConverter activitiConverter = new ActivitiConverter();
        return activitiConverter.convertProcessInstance(processInstance);
    }

    public com.lucasian.bpm.runtime.Process startProcess(String userId, String processName, Map<String, Object> variables) {
        ActivitiAuth.activitiLogin(userId);
        ProcessDefinition processDefinition = getLastActiveDeployedProcess(processName);
        ProcessInstance processInstance = ProcessEngines.getDefaultProcessEngine().getRuntimeService().startProcessInstanceById(processDefinition.getId(),variables);
        ActivitiConverter activitiConverter = new ActivitiConverter();
        return activitiConverter.convertProcessInstance(processInstance);        
    }
    private ProcessDefinition getLastActiveDeployedProcess(String processId){
        ProcessDefinitionQuery query =  ProcessEngines.getDefaultProcessEngine().getRepositoryService().createProcessDefinitionQuery();                
        return query.processDefinitionKey(processId).active().latestVersion().singleResult();
    }    
}
