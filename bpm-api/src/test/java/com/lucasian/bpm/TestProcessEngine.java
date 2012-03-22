package com.lucasian.bpm;

import org.springframework.stereotype.Service;

import com.lucasian.bpm.management.ManagementService;
import com.lucasian.bpm.runtime.RuntimeService;
import com.lucasian.bpm.task.TaskService;

@Service
public class TestProcessEngine implements ProcessEngine {

	@Override
	public ManagementService getManagementService() {
		return null;
	}

	@Override
	public RuntimeService getRuntimeService() {
		return null;
	}

	@Override
	public TaskService getTaskService() {
		return null;
	}

	
	
}
