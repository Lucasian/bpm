/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucasian.bpm.management;

import com.lucasian.bpm.activiti.ActivitiConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinitionQuery;

/**
 *
 * @author Lucasian
 */
public class ActivitiManagementService implements ManagementService{

    public void activateProcessDefinition(String name, String version) {
        String id = ProcessEngines.getDefaultProcessEngine().getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(name).processDefinitionVersion(Integer.parseInt(version)).singleResult().getId();
        ProcessEngines.getDefaultProcessEngine().getRepositoryService().activateProcessDefinitionById(id);
    }

    public void deactivateProcessDefinition(String name, String version) {
        String id = ProcessEngines.getDefaultProcessEngine().getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(name).processDefinitionVersion(Integer.parseInt(version)).singleResult().getId();        
        ProcessEngines.getDefaultProcessEngine().getRepositoryService().suspendProcessDefinitionById(id);
    }

    public void deleteProcessDefinition(String name, String version) {
        String id = ProcessEngines.getDefaultProcessEngine().getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(name).processDefinitionVersion(Integer.parseInt(version)).singleResult().getDeploymentId();        
        ProcessEngines.getDefaultProcessEngine().getRepositoryService().deleteDeployment(id);
    }

    public void deployFile(String filepath){
        try{
            File file = new File(filepath);        
            InputStream inputStream = new FileInputStream(file);
            ProcessEngines.getDefaultProcessEngine().getRepositoryService().createDeployment().addInputStream(file.getName(), inputStream).deploy();
        }catch(FileNotFoundException fileNotFoundException){
            
        }
    }

    public void deployResource(String classPath) {
        ProcessEngines.getDefaultProcessEngine().getRepositoryService().createDeployment().addClasspathResource(classPath).deploy();
    }

    public Set<ProcessDefinition> listProcessDefinitions() {
        ProcessDefinitionQuery processDefinitionQuery = ProcessEngines.getDefaultProcessEngine().getRepositoryService().createProcessDefinitionQuery();
        List<org.activiti.engine.repository.ProcessDefinition> activitiProcessDefinitions =processDefinitionQuery.latestVersion().list();
        Set<ProcessDefinition> processDefinitions = new LinkedHashSet<ProcessDefinition>();        
        ActivitiConverter activitiConverter = new ActivitiConverter();
        for(org.activiti.engine.repository.ProcessDefinition processDefinition: activitiProcessDefinitions){
            processDefinitions.add(activitiConverter.convertProcessDefinition(processDefinition));
        }
        return processDefinitions;
    }
    
    
}
