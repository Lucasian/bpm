/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucasian.bpm;

import com.lucasian.bpm.ProcessEngine;
import com.lucasian.bpm.management.ActivitiManagementService;
import com.lucasian.bpm.management.ManagementService;
import com.lucasian.bpm.runtime.ActivitiRuntimeService;
import com.lucasian.bpm.runtime.RuntimeService;
import com.lucasian.bpm.task.ActivitiTaskService;
import com.lucasian.bpm.task.TaskService;
import org.activiti.engine.ProcessEngines;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucasian
 */
@Service
public class ActivitiProcessEngine implements ProcessEngine{

    public ManagementService getManagementService() {
        return new ActivitiManagementService();
    }

    public RuntimeService getRuntimeService() {
        return new ActivitiRuntimeService();
    }

    public TaskService getTaskService() {
        return new ActivitiTaskService();
    }
    
}
