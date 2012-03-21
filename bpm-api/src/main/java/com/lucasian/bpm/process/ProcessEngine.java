package com.lucasian.bpm.process;

import com.lucasian.bpm.ManagementService;
import com.lucasian.bpm.runtime.RuntimeService;
import com.lucasian.bpm.task.TaskService;

public interface ProcessEngine {

	ManagementService getManagementService();
	
	RuntimeService getRuntimeService();
	
	TaskService getTaskService();
	
}
