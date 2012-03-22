package com.lucasian.bpm;

import com.lucasian.bpm.management.ManagementService;
import com.lucasian.bpm.runtime.RuntimeService;
import com.lucasian.bpm.task.TaskService;

public interface ProcessEngine {

	ManagementService getManagementService();
	
	RuntimeService getRuntimeService();
	
	TaskService getTaskService();
	
}
